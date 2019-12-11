package com.medicalclaim.service;

import com.medicalclaim.dto.PolicyClaimRequestDto;
import com.medicalclaim.dto.PolicyClaimResponseDto;

/**
 * 
 * @author akuthota.raghu
 *
 */
public interface PolicyClaimService {
	
	public PolicyClaimResponseDto raisePolocyClaim(PolicyClaimRequestDto policyClaimRequestDto);
}
