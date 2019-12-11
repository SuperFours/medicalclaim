package com.medicalclaim.dto;

import java.time.LocalDate;

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
public class PolicyClaimRequestDto {
	
	private String policyNo;
	private String name;
	private Integer hospitalId;
	private String diagosis;
	private LocalDate admissionDate;
	private Double claimAmount;
	private LocalDate dischargeDate;
	private String dischargeDetail;
	private String ailmentDetail;
}
