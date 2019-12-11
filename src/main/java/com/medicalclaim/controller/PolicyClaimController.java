package com.medicalclaim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicalclaim.dto.PolicyClaimRequestDto;
import com.medicalclaim.dto.PolicyClaimResponseDto;
import com.medicalclaim.service.PolicyClaimService;
/**
 * 
 * @author akuthota.raghu
 * This ClaimController - is use to handle the all the REST API calls for Claim related things
 *
 */
@RestController
@RequestMapping("/claims")
public class PolicyClaimController {
	
	
	// creating reference for ClaimService 
	
	@Autowired
	private PolicyClaimService policyClaimService;
	
	
	/**
	 * 
	 * @param claim
	 * @return
	 */
	@PostMapping
	public ResponseEntity<PolicyClaimResponseDto> createPolicyClaim(@RequestBody PolicyClaimRequestDto policyClaimRequestDto) {
		
		PolicyClaimResponseDto policyClaimResponsetDto =  policyClaimService.raisePolocyClaim(policyClaimRequestDto);
		
		if(policyClaimResponsetDto != null) {
			policyClaimResponsetDto.setStatus("Success");
			policyClaimResponsetDto.setStatusCode(HttpStatus.OK.value());
		}
		
		return new ResponseEntity<>(policyClaimResponsetDto, HttpStatus.OK);
		
	}

}