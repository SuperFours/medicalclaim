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
public class PolicyClaimRequestDto {
	
	private String policyNo;
	private String name;
	private Integer hospitalId;
	private String diagosis;
	private String admissionDate;
	private Double claimAmount;
	private String dischargeDate;
	private String dischargeDetail;
	private String ailmentDetail;
}
