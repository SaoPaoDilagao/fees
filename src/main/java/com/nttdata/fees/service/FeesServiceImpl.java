package com.nttdata.fees.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.nttdata.fees.dto.request.FeeRequest;
import com.nttdata.fees.entity.Fee;
import com.nttdata.fees.exceptions.custom.CustomNotFoundException;
import com.nttdata.fees.repository.FeesRepository;

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
	public Mono<Void> deleteFees(String idTransaction) {
		Flux<Fee> result = listByIdTransaction(idTransaction);
		
		List<ObjectId> ids = new ArrayList<ObjectId>();
		
		result.flatMap(item -> {
			
			ids.add(item.getId());
			
			return Mono.empty();
			
		});
		
		
		feesRepository.deleteAllById(ids);
		
		return Mono.empty();
	}

	@Override
	public Mono<Void> createFees(FeeRequest request) {
		
		Fee fee;
		
		List<Fee> feesList = new ArrayList<Fee>();
		
		int paymentdayOfFee = 26;
		BigDecimal rateAmount = calculateRateAmount(request);
		LocalDateTime paymentBaseDate = calculateBaseDateRate(paymentdayOfFee);
				
		for(int i=0; i<request.getNumberOfFees(); i++) {
			fee = new Fee();
			fee.setAmount(rateAmount);
			fee.setExpirationDate(paymentBaseDate.plusMonths((long)(i+1)));
			fee.setClientDocumentNumber(request.getClientDocumentNumber());
			fee.setIdTransaction(request.getIdTransaction());
			fee.setProductNumber(request.getProductNumber());
			fee.setStatus(0); // pending
			
			feesList.add(fee);
		}
		
		feesRepository.saveAll(feesList);
		
		return Mono.empty();
	}
	
	private BigDecimal calculateRateAmount(FeeRequest request) {
		
		BigDecimal singleAmount = request.getAmount()
				.divide(new BigDecimal(request.getNumberOfFees()));
		BigDecimal singleInterest = singleAmount
						.multiply(request.getPercentageInterestRate())
						.divide(new BigDecimal("100.0"));
		return singleAmount.add(singleInterest);
	}
	
	private LocalDateTime calculateBaseDateRate(int paymentDay) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime current = LocalDateTime.now();
		current.format(formatter);
		int month =  current.getMonthValue();
		int year = current.getYear();
		String temp = String.valueOf(paymentDay) +"/"+month+"/"+year;
		return LocalDateTime.parse(temp,formatter);
	}

	@Override
	public Mono<Void> updateFee(FeeRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
