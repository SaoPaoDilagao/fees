package com.nttdata.fees.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.nttdata.fees.dto.request.FeeRequest;
import com.nttdata.fees.dto.request.FilterRequest;
import com.nttdata.fees.entity.Fee;
import com.nttdata.fees.exceptions.custom.CustomNotFoundException;
import com.nttdata.fees.repository.FeesRepository;
import com.nttdata.fees.utilities.Constants;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FeesServiceImpl implements FeesService{
    private final FeesRepository feesRepository;

	@Override
	public Flux<Fee> listByIdTransaction(String idTransaction) {
		return feesRepository.findByIdTransaction(idTransaction);
	}

	@Override
	public Flux<Fee> listByProductNumber(String productNumber) {
		return feesRepository.findByProductNumber(productNumber);
	}

	@Override
	public Flux<Fee> listByClientDocumentNumber(String clientDocumentNumber) {
		return feesRepository.findByClientDocumentNumber(clientDocumentNumber);
	}
	
	@Override
	public Mono<Fee> findFeeById(String id) {
		return feesRepository.findById(new ObjectId(id))
				.switchIfEmpty(Mono.error(new CustomNotFoundException("Client not found")));
	}

	@Override
	public Flux<Fee> deleteFees(String idTransaction) {
		List<ObjectId> ids = new ArrayList<>();
		
		return listByIdTransaction(idTransaction)
				.map( item ->{
					ids.add(item.getId());
					return item;
				}).flatMap( doc -> {
					feesRepository.deleteAllById(ids).subscribe();
					return Mono.just(doc);
				});
		
	}

	@Override
	public Flux<Fee> createFees(FeeRequest request) {
		
		Fee fee;
		
		List<Fee> feesList = new ArrayList<>();
		
		BigDecimal rateAmount = calculateRateAmount(request);
		LocalDate paymentBaseDate = calculateBaseDateRate(request.getMonthlyFeeExpirationDay());
				
		for(int i=0; i<request.getNumberOfFees(); i++) {
			fee = new Fee();
			fee.setAmount(rateAmount);
			fee.setExpirationDate(paymentBaseDate.plusMonths(i+1));
			fee.setClientDocumentNumber(request.getClientDocumentNumber());
			fee.setIdTransaction(request.getIdTransaction());
			fee.setProductNumber(request.getProductNumber());
			fee.setStatus(Constants.FeeStatus.PENDING); 
			
			feesList.add(fee);
		}
		
		Flux<Fee> result = feesRepository.saveAll(feesList);
		return result;
		
		
	}
	
	private BigDecimal calculateRateAmount(FeeRequest request) {
		
		BigDecimal singleAmount = request.getAmount()
				.divide(new BigDecimal(request.getNumberOfFees()),2, RoundingMode.HALF_DOWN);
		BigDecimal singleInterest = singleAmount
						.multiply(request.getPercentageInterestRate())
						.divide(new BigDecimal("100.0"),2, RoundingMode.HALF_DOWN);
		return singleAmount.add(singleInterest);
	}
	
	private LocalDate calculateBaseDateRate(int paymentDay) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate current = LocalDate.now();
		int month =  current.getMonthValue();
		int year = current.getYear();
		String temp = paymentDay +"/"+String.format("%02d", month)+"/"+year; 
		return LocalDate.parse(temp,formatter);
	}

	@Override
	public Mono<Fee> updateFee(String id) {		
		return findFeeById(id)
			.map(item ->{
				item.setStatus(Constants.FeeStatus.PAYED); 
				feesRepository.save(item).subscribe();
				return item;
			});
	}

	@Override
    public Flux<Fee> listFeesByProductNumberAndDateInterval(FilterRequest filterRequest) {
		return feesRepository.listFeesByProductNumberAndDateInterval(filterRequest.getProductNumber(),
				filterRequest.getStartDate(),filterRequest.getEndDate());
	}
}
