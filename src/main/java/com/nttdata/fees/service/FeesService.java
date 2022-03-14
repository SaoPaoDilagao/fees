package com.nttdata.fees.service;

import com.nttdata.fees.entity.Fee;

import reactor.core.publisher.Flux;

public interface FeesService {
	
	Flux<Fee> listByIdTransaction(String idTransaction);
	Flux<Fee> listByProductNumber(String productNumber);
	Flux<Fee> listByClientDocumentNumber(String clientDocumentNumber);
	

}
