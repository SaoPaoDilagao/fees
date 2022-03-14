package com.nttdata.fees.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Fees {
	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId id;
	private String idTransaction;
	private String clientDocumentNumber;
	private String productNumber;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime expirationDate;
	private int status; 

}
