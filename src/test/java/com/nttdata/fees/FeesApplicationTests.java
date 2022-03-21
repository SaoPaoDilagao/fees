package com.nttdata.fees;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.nttdata.fees.controller.FeesController;
import com.nttdata.fees.dto.request.FeeRequest;
import com.nttdata.fees.dto.request.FilterRequest;
import com.nttdata.fees.entity.Fee;
import com.nttdata.fees.service.FeesService;
import com.nttdata.fees.utilities.Constants;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@WebFluxTest(FeesController.class)
class FeesApplicationTests {
	
	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private FeesService feesService;

	@Test
	public void testListByIdTransaction() {
		
		Fee dummy1 = new Fee();
		dummy1.setId(new ObjectId("623238284e26e51430f045bf"));
		dummy1.setIdTransaction("1");
		dummy1.setProductNumber("00001");
		dummy1.setAmount(new BigDecimal("100"));
		dummy1.setExpirationDate(LocalDate.now().plusMonths(1));
		dummy1.setClientDocumentNumber("0123456");
		dummy1.setStatus(Constants.FeeStatus.PENDING);
		
		Fee dummy2 = new Fee();
		dummy2.setId(new ObjectId("623238284e26e51430f045bf"));
		dummy2.setIdTransaction("1");
		dummy2.setProductNumber("00001");
		dummy2.setAmount(new BigDecimal("100"));
		dummy2.setExpirationDate(LocalDate.now().plusMonths(2));
		dummy1.setClientDocumentNumber("0123456");
		dummy2.setStatus(Constants.FeeStatus.PENDING);
		
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
	public void testListByProductNumber() {
		
		Fee dummy1 = new Fee();
		dummy1.setId(new ObjectId("623238284e26e51430f045bf"));
		dummy1.setIdTransaction("623238284e26e51430f045bf");
		dummy1.setProductNumber("00001");
		dummy1.setAmount(new BigDecimal("100"));
		dummy1.setExpirationDate(LocalDate.now().plusMonths(1));
		dummy1.setClientDocumentNumber("0123456");
		dummy1.setStatus(Constants.FeeStatus.PENDING);
		
		Fee dummy2 = new Fee();
		dummy2.setId(new ObjectId("623238284e26e51430f045bf"));
		dummy2.setIdTransaction("1");
		dummy2.setProductNumber("00001");
		dummy2.setAmount(new BigDecimal("100"));
		dummy2.setExpirationDate(LocalDate.now().plusMonths(2));
		dummy1.setClientDocumentNumber("0123456");
		dummy2.setStatus(Constants.FeeStatus.PENDING);
		
		Flux<Fee> dummyFlux = Flux.just(dummy1, dummy2);
		
		when(feesService.listByProductNumber("00001")).thenReturn(dummyFlux);
		
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
	public void testListByClientDocumentNumber() {
		
		Fee dummy1 = new Fee();
		dummy1.setId(new ObjectId("623238284e26e51430f045bf"));
		dummy1.setIdTransaction("1");
		dummy1.setProductNumber("00001");
		dummy1.setAmount(new BigDecimal("100"));
		dummy1.setExpirationDate(LocalDate.now().plusMonths(1));
		dummy1.setClientDocumentNumber("0123456");
		dummy1.setStatus(Constants.FeeStatus.PENDING);
		
		Fee dummy2 = new Fee();
		dummy2.setId(new ObjectId("623238284e26e51430f045bf"));
		dummy2.setIdTransaction("1");
		dummy2.setProductNumber("00001");
		dummy2.setAmount(new BigDecimal("100"));
		dummy2.setExpirationDate(LocalDate.now().plusMonths(2));
		dummy1.setClientDocumentNumber("0123456");
		dummy2.setStatus(Constants.FeeStatus.PENDING);
		
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
	
	@Test
	public void testListByProductAndIntervalDate() {
		
		Fee dummy1 = new Fee();
		dummy1.setId(new ObjectId("623238284e26e51430f045bf"));
		dummy1.setIdTransaction("1");
		dummy1.setProductNumber("00001");
		dummy1.setAmount(new BigDecimal("100"));
		dummy1.setExpirationDate(LocalDate.now().plusMonths(1));
		dummy1.setClientDocumentNumber("0123456");
		dummy1.setStatus(Constants.FeeStatus.PENDING);
		
		Fee dummy2 = new Fee();
		dummy2.setId(new ObjectId("623238284e26e51430f045bf"));
		dummy2.setIdTransaction("1");
		dummy2.setProductNumber("00001");
		dummy2.setAmount(new BigDecimal("100"));
		dummy2.setExpirationDate(LocalDate.now().plusMonths(2));
		dummy1.setClientDocumentNumber("0123456");
		dummy2.setStatus(Constants.FeeStatus.PENDING);
		
		Flux<Fee> dummyFlux = Flux.just(dummy1, dummy2);
		
		FilterRequest filterReq = new FilterRequest();
		filterReq.setProductNumber("00001");
		filterReq.setStartDate(LocalDate.now());
		filterReq.setEndDate(LocalDate.now().plusMonths(3));
		
		when(feesService.listFeesByProductNumberAndDateInterval(filterReq)).thenReturn(dummyFlux);
		
		var responseBody = webTestClient
				.method(HttpMethod.GET)
				.uri("/fees/listByProductAndIntervalDate")
				.contentType(APPLICATION_JSON)
				.body(Mono.just(filterReq), FilterRequest.class)
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
	public void testListByClientId() {
		
		Fee dummy1 = new Fee();
		dummy1.setId(new ObjectId("623238284e26e51430f045bf"));
		dummy1.setIdTransaction("1");
		dummy1.setProductNumber("00001");
		dummy1.setAmount(new BigDecimal("100"));
		dummy1.setExpirationDate(LocalDate.now().plusMonths(1));
		dummy1.setClientDocumentNumber("0123456");
		dummy1.setStatus(Constants.FeeStatus.PENDING);
		
		String id = "623238284e26e51430f045bf";
		
		when(feesService.findFeeById(id)).thenReturn(Mono.just(dummy1));
		
		var responseBody = webTestClient
				.method(HttpMethod.GET)
				.uri("/fees/find/{id}",id)
				.exchange()
				.expectStatus().isOk()
				.returnResult(Fee.class)
				.getResponseBody();
		
		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNext(dummy1)
				.verifyComplete();
	}
	
	@Test
	public void testCreateFees() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		Fee dummy1 = new Fee();
		dummy1.setId(new ObjectId("623238284e26e51430f045bf"));
		dummy1.setIdTransaction("1");
		dummy1.setProductNumber("00001");
		dummy1.setAmount(new BigDecimal("525"));
		dummy1.setExpirationDate(LocalDate.parse("27/04/2022",formatter));
		dummy1.setClientDocumentNumber("0123456");
		dummy1.setStatus(Constants.FeeStatus.PENDING);
		
		Fee dummy2 = new Fee();
		dummy2.setId(new ObjectId("623238284e26e51430f045bf"));
		dummy2.setIdTransaction("1");
		dummy2.setProductNumber("00001");
		dummy2.setAmount(new BigDecimal("525"));
		dummy2.setExpirationDate(LocalDate.parse("27/05/2022",formatter));
		dummy1.setClientDocumentNumber("0123456");
		dummy2.setStatus(Constants.FeeStatus.PENDING);
		
		Flux<Fee> dummyFlux = Flux.just(dummy1, dummy2);
		
		FeeRequest request = new FeeRequest();
		request.setIdTransaction("1");
		request.setProductNumber("00001");
		request.setClientDocumentNumber("0123456");
		request.setAmount(new BigDecimal(1000));
		request.setNumberOfFees(2);
		request.setPercentageInterestRate(new BigDecimal(5));
		request.setMonthlyFeeExpirationDay(27);
		
		when(feesService.createFees(request)).thenReturn(dummyFlux);
		
		var responseBody = webTestClient
				.post()
				.uri("/fees/create")
				.contentType(APPLICATION_JSON)
				.body(Mono.just(request), FeeRequest.class)
				.exchange()
				.expectStatus().isCreated()
				.returnResult(Fee.class)
				.getResponseBody();
		
		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNext(dummy1)
				.expectNext(dummy2)
				.verifyComplete();
	
	}
	
	@Test
	public void testDeleteFees() {
		
		Fee dummy1 = new Fee();
		dummy1.setId(new ObjectId("623238284e26e51430f045bf"));
		dummy1.setIdTransaction("1");
		dummy1.setProductNumber("00001");
		dummy1.setAmount(new BigDecimal("100"));
		dummy1.setExpirationDate(LocalDate.now().plusMonths(1));
		dummy1.setClientDocumentNumber("0123456");
		dummy1.setStatus(Constants.FeeStatus.PENDING);
		
		Fee dummy2 = new Fee();
		dummy2.setId(new ObjectId("623238284e26e51430f045bf"));
		dummy2.setIdTransaction("1");
		dummy2.setProductNumber("00001");
		dummy2.setAmount(new BigDecimal("100"));
		dummy2.setExpirationDate(LocalDate.now().plusMonths(2));
		dummy1.setClientDocumentNumber("0123456");
		dummy2.setStatus(Constants.FeeStatus.PENDING);
		
		Flux<Fee> dummyFlux = Flux.just(dummy1, dummy2);
		
		String idTransaction = "1";
		
		when(feesService.deleteFees(idTransaction)).thenReturn(dummyFlux);
		
		var responseBody = webTestClient
				.delete()
				.uri("/fees/delete/{idTransaction}",idTransaction)
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
	public void testupdateFee() {
		
		Fee dummy1 = new Fee();
		dummy1.setId(new ObjectId("623238284e26e51430f045bf"));
		dummy1.setIdTransaction("1");
		dummy1.setProductNumber("00001");
		dummy1.setAmount(new BigDecimal("100"));
		dummy1.setExpirationDate(LocalDate.now().plusMonths(1));
		dummy1.setClientDocumentNumber("0123456");
		dummy1.setStatus(Constants.FeeStatus.PAYED);
		
		String id = "623238284e26e51430f045bf";
		
		when(feesService.updateFee(id)).thenReturn(Mono.just(dummy1));
		
		var responseBody = webTestClient
				.put()
				.uri("/fees/update/{id}",id)
				.exchange()
				.expectStatus().isOk()
				.returnResult(Fee.class)
				.getResponseBody();
		
		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNext(dummy1)
				.verifyComplete();
		
	}
	
	@Test
	public void testCheckIfExistFeesExpired(){
		
		String productNumber = "00001";
		
		when(feesService.checkIfExistFeesExpired(productNumber)).thenReturn(Mono.just(1L));
		
		var responseBody = webTestClient.get().uri("/fees//checkIfExistFeesExpired/{productNumber}",productNumber)
				.exchange()
				.expectStatus().isOk()
				.returnResult(Long.class)
				.getResponseBody();
		
		StepVerifier.create(responseBody)
				.expectSubscription()
				.consumeNextWith( data ->{
					Assertions.assertNotNull(data);
					Assertions.assertEquals(data, 1L);
				})
				.verifyComplete();
		
	}
}
