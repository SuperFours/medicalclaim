package com.medicalclaim.dto;

import java.time.LocalDateTime;

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
	
	private Integer policyId;
	private Integer hospitalId;
	private String claimNumber;
	private LocalDateTime claimDate;
	private Double claimAmount;
	private String policyClaimApproval;

}
