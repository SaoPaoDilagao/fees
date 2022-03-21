package com.nttdata.fees;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nttdata.fees.dto.request.FeeRequest;
import com.nttdata.fees.dto.request.FilterRequest;
import com.nttdata.fees.entity.Fee;
import com.nttdata.fees.repository.FeesRepository;
import com.nttdata.fees.service.FeesServiceImpl;
import com.nttdata.fees.utilities.Constants;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
public class FeesServiceTest {
	
	@Mock
	private FeesRepository feesRepository;
	
	@InjectMocks
	private FeesServiceImpl feesServiceImpl;
	
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
		
		String idTransaction = "1";
		
		when(feesRepository.findByIdTransaction(idTransaction)).thenReturn(dummyFlux);
		
		var result = feesServiceImpl.listByIdTransaction(idTransaction);
		
		StepVerifier
			.create(result)
			.consumeNextWith( fee ->{
				Assertions.assertNotNull(fee);
				Assertions.assertEquals(fee.getClientDocumentNumber(), dummy1.getClientDocumentNumber());
				Assertions.assertEquals(fee.getExpirationDate(), dummy1.getExpirationDate());
				Assertions.assertEquals(fee.getProductNumber(), dummy1.getProductNumber());
				Assertions.assertEquals(fee.getAmount(), dummy1.getAmount());
			})
			.consumeNextWith( fee ->{
				Assertions.assertNotNull(fee);
				Assertions.assertEquals(fee.getClientDocumentNumber(), dummy2.getClientDocumentNumber());
				Assertions.assertEquals(fee.getExpirationDate(), dummy2.getExpirationDate());
				Assertions.assertEquals(fee.getProductNumber(), dummy2.getProductNumber());
				Assertions.assertEquals(fee.getAmount(), dummy2.getAmount());
			})
			.verifyComplete();
	}
	
	
	@Test
	public void testlistByProductNumber() {
		
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
		
		String productNumber = "00001";
		
		when(feesRepository.findByProductNumber(productNumber)).thenReturn(dummyFlux);
		
		var result = feesServiceImpl.listByProductNumber(productNumber);
		
		StepVerifier
			.create(result)
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
		
		String clientDocumentNumber = "0123456";
		
		when(feesRepository.findByClientDocumentNumber(clientDocumentNumber)).thenReturn(dummyFlux);
		
		var result = feesServiceImpl.listByClientDocumentNumber(clientDocumentNumber);
		
		StepVerifier
			.create(result)
			.expectSubscription()
			.expectNext(dummy1)
			.expectNext(dummy2)
			.verifyComplete();
	}
	
	@Test
	public void testfindFeeById() {
		
		Fee dummy1 = new Fee();
		dummy1.setId(new ObjectId("623238284e26e51430f045bf"));
		dummy1.setIdTransaction("1");
		dummy1.setProductNumber("00001");
		dummy1.setAmount(new BigDecimal("100"));
		dummy1.setExpirationDate(LocalDate.now().plusMonths(1));
		dummy1.setClientDocumentNumber("0123456");
		dummy1.setStatus(Constants.FeeStatus.PENDING);
		
		String id = "623238284e26e51430f045bf";
		
		given(feesRepository.findById(new ObjectId(id))).willReturn(Mono.just(dummy1));
		
		var result = feesServiceImpl.findFeeById(id);
		
		StepVerifier
		.create(result)
		.consumeNextWith( fee ->{
			Assertions.assertNotNull(fee);
			Assertions.assertEquals(fee.getClientDocumentNumber(), dummy1.getClientDocumentNumber());
			Assertions.assertEquals(fee.getExpirationDate(), dummy1.getExpirationDate());
			Assertions.assertEquals(fee.getProductNumber(), dummy1.getProductNumber());
			Assertions.assertEquals(fee.getAmount(), dummy1.getAmount());
		})
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
		
		when(feesRepository.deleteAllById(any())).thenReturn(Mono.empty());
		
		when(feesRepository.findByIdTransaction(any())).thenReturn(dummyFlux);
		
		var result = feesServiceImpl.deleteFees(idTransaction);
		
		StepVerifier
			.create(result)
			.expectSubscription()
			.expectNext(dummy1)
			.expectNext(dummy2)
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
		
		when(feesRepository.saveAll((List<Fee>)any())).thenReturn(dummyFlux);
		
		var result = feesServiceImpl.createFees(request);
		
		StepVerifier
			.create(result)
			.expectSubscription()
			.expectNext(dummy1)
			.expectNext(dummy2)
			.verifyComplete();
	}
	
	@Test
	public void testUpdateFee() {
		
		Fee dummy1 = new Fee();
		dummy1.setId(new ObjectId("623238284e26e51430f045bf"));
		dummy1.setIdTransaction("1");
		dummy1.setProductNumber("00001");
		dummy1.setAmount(new BigDecimal("100"));
		dummy1.setExpirationDate(LocalDate.now().plusMonths(1));
		dummy1.setClientDocumentNumber("0123456");
		dummy1.setStatus(Constants.FeeStatus.PAYED);
		
		String id = "623238284e26e51430f045bf";
		
		when(feesRepository.findById(new ObjectId(id))).thenReturn(Mono.just(dummy1));
		
		when(feesRepository.save(dummy1)).thenReturn(Mono.just(dummy1));
		
		var result = feesServiceImpl.updateFee(id);
		
		StepVerifier
		.create(result)
		.expectSubscription()
		.expectNext(dummy1)
		.verifyComplete();
		
	}
	
	@Test
	public void testlListFeesByProductNumberAndDateInterval() {
		
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
		
		when(feesRepository.listFeesByProductNumberAndDateInterval(any(),any(),any())).thenReturn(dummyFlux);
		
		var result = feesServiceImpl.listFeesByProductNumberAndDateInterval(filterReq);
		
		StepVerifier
		.create(result)
		.expectSubscription()
		.expectNext(dummy1)
		.expectNext(dummy2)
		.verifyComplete();
	}
	
	@Test
	public void testCheckIfExistFeesExpired(){
		
		String productNumber = "00001";
		
		given(feesRepository.countFeesExpired(productNumber)).willReturn(Mono.just(1L));
		
		var result = feesServiceImpl.checkIfExistFeesExpired(productNumber);
		
		StepVerifier
		.create(result)
		.consumeNextWith( data ->{
			Assertions.assertNotNull(data);
			Assertions.assertEquals(data, 1L);
		})
		.verifyComplete();
		
	}
}
