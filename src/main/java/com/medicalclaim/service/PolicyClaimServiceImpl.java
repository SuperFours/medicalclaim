package com.medicalclaim.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.medicalclaim.dto.PolicyClaimRequestDto;
import com.medicalclaim.dto.PolicyClaimResponseDto;
import com.medicalclaim.entity.PolicyClaim;
import com.medicalclaim.repository.PolicyClaimRepository;

/**
 * 
 * @author akuthota.raghu
 *
 */
@Service
public class PolicyClaimServiceImpl implements PolicyClaimService {
	
	// created instance for PolicyClaimRepository to call predefined  JPA repository methods
	@Autowired
	private PolicyClaimRepository policyClaimRepository;

	@Override
	public PolicyClaimResponseDto raisePolocyClaim(PolicyClaimRequestDto policyClaimRequestDto) {
		
		PolicyClaim policyClaim = new PolicyClaim();
		
		PolicyClaimResponseDto response = new PolicyClaimResponseDto();
		
		BeanUtils.copyProperties(policyClaimRequestDto, policyClaim);
		
		policyClaimRepository.save(policyClaim);
		response.setMessage("Claim Raise Successfully"); // get message from  constants file
		response.setStatusCode(HttpStatus.OK.value());
		response.setStatus("SUCCESS"); // get status  from  constants file
		
		return response;
	}
}
	
