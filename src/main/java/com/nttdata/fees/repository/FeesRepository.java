package com.nttdata.fees.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.fees.entity.Fee;

import reactor.core.publisher.Flux;

@Repository
public interface FeesRepository extends ReactiveMongoRepository<Fee, ObjectId>,
		CustomFeesRepository{
	Flux<Fee> findByIdTransaction(String idTransaction);
	Flux<Fee> findByProductNumber(String productNumber);
	Flux<Fee> findByClientDocumentNumber(String productNumber);

}
