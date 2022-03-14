package com.nttdata.fees.service;

import org.springframework.stereotype.Service;

import com.nttdata.fees.entity.Fee;
import com.nttdata.fees.repository.FeesRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class FeesServiceImpl implements FeesService{
	private final FeesRepository feesRepository;

	@Override
	public Flux<Fee> listByIdTransaction(String idTransaction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Fee> listByProductNumber(String productNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Fee> listByClientDocumentNumber(String clientDocumentNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
