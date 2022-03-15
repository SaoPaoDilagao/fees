package com.nttdata.fees.service;

import com.nttdata.fees.dto.request.FeeRequest;
import com.nttdata.fees.dto.request.FilterRequest;
import com.nttdata.fees.entity.Fee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeesService {
	
	Flux<Fee> listByIdTransaction(String idTransaction);
	Flux<Fee> listByProductNumber(String productNumber);
	Flux<Fee> listByClientDocumentNumber(String clientDocumentNumber);
	Mono<Fee> findFeeById(String id);
	Flux<Fee> deleteFees(String idTransaction);
	Flux<Fee> createFees(FeeRequest request);
	Mono<Fee> updateFee(String id);
	Flux<Fee> listFeesByProductNumberAndDateInterval(FilterRequest filterRequest);
  	

}
