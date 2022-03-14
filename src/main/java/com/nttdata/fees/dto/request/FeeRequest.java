package com.nttdata.fees.dto.request;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeeRequest {
	
	private String idTransaction;
	private String clientDocumentNumber;
	private String productNumber;
	private int numberOfFees;
	@Field(targetType = FieldType.DECIMAL128)
	private BigDecimal amount;

}
