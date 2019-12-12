package com.medicalclaim.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewClaimDto {

	private Integer id;
	private String name;
	private String policyNo;
	private String hospitalName;
	private String claimNumber;
	private LocalDate claimDate;
	private Double claimAmount;
}
