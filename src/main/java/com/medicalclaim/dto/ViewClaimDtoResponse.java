package com.medicalclaim.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewClaimDtoResponse {
	
	private List<ViewClaimDto>  claims;
	
	private String message;
	private Integer statusCode;

}
