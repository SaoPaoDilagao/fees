package com.nttdata.fees.service;

import com.nttdata.fees.dto.request.FeeRequest;
import com.nttdata.fees.entity.Fee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeesService {
	
	Flux<Fee> listByIdTransaction(String idTransaction);
	Flux<Fee> listByProductNumber(String productNumber);
	Flux<Fee> listByClientDocumentNumber(String clientDocumentNumber);
	Mono<Fee> findFeeById(String id);
	Mono<Void> deleteFees(FeeRequest request);
	Mono<Void> createFees(FeeRequest request);
	Mono<Void> updateFee(FeeRequest request);
  	

}
