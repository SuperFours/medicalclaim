package com.medicalclaim.service;

import java.util.List;

import com.medicalclaim.dto.PolicyClaimRequestDto;
import com.medicalclaim.dto.PolicyClaimResponseDto;
import com.medicalclaim.dto.ViewClaimDto;

/**
 * 
 * @author akuthota.raghu
 *
 */
public interface PolicyClaimService {
	
	public PolicyClaimResponseDto raisePolocyClaim(PolicyClaimRequestDto policyClaimRequestDto);
	
	List<ViewClaimDto> claimListByUserId(Integer userId);
}
