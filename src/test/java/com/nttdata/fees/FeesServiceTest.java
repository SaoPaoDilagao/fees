package com.nttdata.fees;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nttdata.fees.entity.Fee;
import com.nttdata.fees.repository.FeesRepository;
import com.nttdata.fees.service.FeesServiceImpl;
import com.nttdata.fees.utilities.Constants;

import reactor.core.publisher.Flux;
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
			})
			.consumeNextWith( fee ->{
				Assertions.assertNotNull(fee);
				Assertions.assertEquals(fee.getExpirationDate(), dummy2.getExpirationDate());
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
			.consumeNextWith( fee ->{
				Assertions.assertNotNull(fee);
				Assertions.assertEquals(fee.getAmount(), dummy1.getAmount());
			})
			.consumeNextWith( fee ->{
				Assertions.assertNotNull(fee);
				Assertions.assertEquals(fee.getProductNumber(), dummy2.getProductNumber());
			})
			.verifyComplete();
	}

}
