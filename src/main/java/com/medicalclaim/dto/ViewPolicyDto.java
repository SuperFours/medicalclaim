package com.medicalclaim.dto;

import java.time.LocalDateTime;
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
	private LocalDateTime claimDate;
	private Double claimAmount;
	private List<PolicyStatusDto> policyStatus;

}
