package com.medicalclaim.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ViewPolicyDto extends ResponseDto {

	private String policyNo;
	private String policyHolderName;
	private String policyDependentName;
	private Double policyAmount;
	private String diagosis;
	private LocalDate claimDate;
	private LocalDate admissionDate;
	private String claimNumber;
	private Double claimAmount;
	private LocalDate dischargeDate;
	private String dischargeDetail;
	private List<PolicyStatusDto> policyStatus;

}
