package com.medicalclaim.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HospitalResponseDto extends LoginResponseDto{

	public HospitalResponseDto(int statusCode, String message) {
		this.setStatusCode(statusCode);
		this.setMessage(message);
	}
	private List<HospitalDto> hospitals;
	
	
}
