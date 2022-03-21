package com.nttdata.fees.repository;

import java.time.LocalDate;

import com.nttdata.fees.entity.Fee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomFeesRepository {
	
	Flux<Fee> listFeesByProductNumberAndDateInterval(String productNumber,
													LocalDate startDate, LocalDate endDate);
	
	Mono<Long> countFeesExpired(String productNumber);

}
