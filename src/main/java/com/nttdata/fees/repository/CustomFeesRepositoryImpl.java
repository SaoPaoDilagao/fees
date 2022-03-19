package com.nttdata.fees.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.nttdata.fees.entity.Fee;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class CustomFeesRepositoryImpl  implements CustomFeesRepository{
	
	private final ReactiveMongoTemplate mongoTemplate;
		
	@Override
	public Flux<Fee> listFeesByProductNumberAndDateInterval(String productNumber, LocalDate startDate,
			LocalDate endDate) {
		
		Query query = new Query(where("productNumber").is(productNumber)
		        .and("expirationDate").gte(startDate).lt(endDate.plusDays(1)));
		    return mongoTemplate.find(query, Fee.class);
	}

}
