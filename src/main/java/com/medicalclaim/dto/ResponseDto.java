package com.medicalclaim.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseDto {

	private String status;
	private String message;
	private Integer statusCode;
}
