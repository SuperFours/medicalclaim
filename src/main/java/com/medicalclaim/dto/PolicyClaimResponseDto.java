package com.medicalclaim.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PolicyClaimResponseDto {
	
	private String message;
	private Integer statusCode;
	private String status;
}
