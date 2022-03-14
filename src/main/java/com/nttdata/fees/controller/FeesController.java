package com.nttdata.fees.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

import com.nttdata.fees.entity.Fee;
import com.nttdata.fees.service.FeesService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fees")
public class FeesController {
	
	private final FeesService feesServices;
	
	@GetMapping(value ="/list/idTransaction/{idTransaction}", produces = TEXT_EVENT_STREAM_VALUE)
	public Flux<Fee> listFeesByIdTransaction(@PathVariable("idTransaction") String idTransaction){
		return feesServices.listByIdTransaction(idTransaction);	
	}
	
	@GetMapping(value ="/list/productNumber/{productNumber}", produces = TEXT_EVENT_STREAM_VALUE)
	public Flux<Fee> listFeesBylistByProductNumber(@PathVariable("productNumber") String productNumber){
		return feesServices.listByProductNumber(productNumber);
	}
	
	@GetMapping(value ="/list/clientDocumentNumber/{clientDocumentNumber}", produces = TEXT_EVENT_STREAM_VALUE)
	public Flux<Fee> listByClientDocumentNumber(@PathVariable("clientDocumentNumber") String clientDocumentNumber){
		return feesServices.listByClientDocumentNumber(clientDocumentNumber);
	}

}
