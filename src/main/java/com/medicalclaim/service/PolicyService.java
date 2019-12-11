package com.medicalclaim.service;

import com.medicalclaim.dto.ViewPolicyDto;

@FunctionalInterface
public interface PolicyService {

	public ViewPolicyDto getPolicy(String policyNo);
	
}
