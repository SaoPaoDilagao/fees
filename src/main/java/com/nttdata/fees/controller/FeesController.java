package com.nttdata.fees.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.fees.dto.request.FeeRequest;
import com.nttdata.fees.entity.Fee;
import com.nttdata.fees.service.FeesService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
	
	@GetMapping(value ="/find/id/{id}")
	public Mono<Fee> listByClientId(@PathVariable("id") String id){
		return feesServices.findFeeById(id);
	}
	
	@PostMapping("/create")
	@ResponseStatus(CREATED)
	Flux<Fee> createFees(@RequestBody FeeRequest request){
		return feesServices.createFees(request);
	}
	
	@DeleteMapping("/delete/{idTransaction}")
	Mono<Void> deleteFees(@PathVariable("idTransaction") String idTransaction){
		return feesServices.deleteFees(idTransaction);
	}
	
	@PutMapping("/update")
	Mono<Void> updateFee(@RequestBody FeeRequest request){
		return feesServices.updateFee(request);
	}

}
