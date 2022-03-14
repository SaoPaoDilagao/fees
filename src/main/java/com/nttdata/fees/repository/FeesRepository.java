package com.nttdata.fees.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.fees.entity.Fee;

@Repository
public interface FeesRepository extends ReactiveMongoRepository<Fee, ObjectId>{

}
