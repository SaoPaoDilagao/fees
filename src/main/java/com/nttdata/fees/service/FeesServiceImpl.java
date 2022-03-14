package com.nttdata.fees.service;

import org.springframework.stereotype.Service;

import com.nttdata.fees.repository.FeesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeesServiceImpl implements FeesService{
	private final FeesRepository feesRepository;

}
