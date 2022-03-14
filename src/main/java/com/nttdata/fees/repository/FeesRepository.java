package com.nttdata.fees.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.fees.entity.Fees;

@Repository
public interface FeesRepository extends ReactiveMongoRepository<Fees, ObjectId>{

}
