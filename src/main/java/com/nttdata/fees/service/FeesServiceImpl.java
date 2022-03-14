package com.nttdata.fees.service;

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
	public Mono<Void> deleteFees(FeeRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> createFees(FeeRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> updateFee(FeeRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
