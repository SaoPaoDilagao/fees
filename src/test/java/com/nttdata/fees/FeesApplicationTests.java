package com.nttdata.fees;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;


import com.nttdata.fees.controller.FeesController;
import com.nttdata.fees.entity.Fee;
import com.nttdata.fees.service.FeesService;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@WebFluxTest(FeesController.class)
class FeesApplicationTests {
	
	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private FeesService feesService;

	@Test
	void testListByIdTransaction() {
		
		Fee dummy1 = new Fee();
		//dummy1.setId(new ObjectId("1"));
		dummy1.setIdTransaction("1");
		dummy1.setProductNumber("00001");
		dummy1.setAmount(new BigDecimal("100"));
		//dummy1.setExpirationDate(LocalDateTime.now().plusMonths(1));
		dummy1.setClientDocumentNumber("0123456");
		dummy1.setStatus(0);
		
		Fee dummy2 = new Fee();
		//dummy2.setId(new ObjectId("2"));
		dummy2.setIdTransaction("1");
		dummy2.setProductNumber("00001");
		dummy2.setAmount(new BigDecimal("100"));
		//dummy2.setExpirationDate(LocalDateTime.now().plusMonths(2));
		dummy1.setClientDocumentNumber("0123456");
		dummy2.setStatus(0);
		
		Flux<Fee> dummyFlux = Flux.just(dummy1, dummy2);
		
		when(feesService.listByIdTransaction("1")).thenReturn(dummyFlux);
		
		var responseBody = webTestClient.get().uri("/fees/list/idTransaction/1")
				.exchange()
				.expectStatus().isOk()
				.returnResult(Fee.class)
				.getResponseBody();
		
		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNext(dummy1)
				.expectNext(dummy2)
				.verifyComplete();
		
	}
	
	@Test
	void testListByProductNumber() {
		
		Fee dummy1 = new Fee();
		//dummy1.setId(new ObjectId("1"));
		dummy1.setIdTransaction("1");
		dummy1.setProductNumber("00001");
		dummy1.setAmount(new BigDecimal("100"));
		//dummy1.setExpirationDate(LocalDateTime.now().plusMonths(1));
		dummy1.setClientDocumentNumber("0123456");
		dummy1.setStatus(0);
		
		Fee dummy2 = new Fee();
		//dummy2.setId(new ObjectId("2"));
		dummy2.setIdTransaction("1");
		dummy2.setProductNumber("00001");
		dummy2.setAmount(new BigDecimal("100"));
		//dummy2.setExpirationDate(LocalDateTime.now().plusMonths(2));
		dummy1.setClientDocumentNumber("0123456");
		dummy2.setStatus(0);
		
		Flux<Fee> dummyFlux = Flux.just(dummy1, dummy2);
		
		when(feesService.listByProductNumber("0001")).thenReturn(dummyFlux);
		
		var responseBody = webTestClient.get().uri("/fees/list/productNumber/00001")
				.exchange()
				.expectStatus().isOk()
				.returnResult(Fee.class)
				.getResponseBody();
		
		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNext(dummy1)
				.expectNext(dummy2)
				.verifyComplete();
		
	}
	
	@Test
	void testListByClientDocumentNumber() {
		
		Fee dummy1 = new Fee();
		//dummy1.setId(new ObjectId("1"));
		dummy1.setIdTransaction("1");
		dummy1.setProductNumber("00001");
		dummy1.setAmount(new BigDecimal("100"));
		//dummy1.setExpirationDate(LocalDateTime.now().plusMonths(1));
		dummy1.setClientDocumentNumber("0123456");
		dummy1.setStatus(0);
		
		Fee dummy2 = new Fee();
		//dummy2.setId(new ObjectId("2"));
		dummy2.setIdTransaction("1");
		dummy2.setProductNumber("00001");
		dummy2.setAmount(new BigDecimal("100"));
		//dummy2.setExpirationDate(LocalDateTime.now().plusMonths(2));
		dummy1.setClientDocumentNumber("0123456");
		dummy2.setStatus(0);
		
		Flux<Fee> dummyFlux = Flux.just(dummy1, dummy2);
		
		when(feesService.listByClientDocumentNumber("0123456")).thenReturn(dummyFlux);
		
		var responseBody = webTestClient.get().uri("/fees/list/clientDocumentNumber/0123456")
				.exchange()
				.expectStatus().isOk()
				.returnResult(Fee.class)
				.getResponseBody();
		
		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNext(dummy1)
				.expectNext(dummy2)
				.verifyComplete();
		
	}
	
}
