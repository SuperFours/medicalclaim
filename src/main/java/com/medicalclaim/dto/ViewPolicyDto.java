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
	private LocalDate validFrom;
	private LocalDate validTo;
	private Double policyAmount;
	private List<PolicyStatusDto> policyStatus;

}
