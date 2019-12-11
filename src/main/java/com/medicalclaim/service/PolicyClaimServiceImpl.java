package com.medicalclaim.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.medicalclaim.constant.AppConstant;
import com.medicalclaim.dto.PolicyClaimRequestDto;
import com.medicalclaim.dto.PolicyClaimResponseDto;
import com.medicalclaim.entity.Hospital;
import com.medicalclaim.entity.Policy;
import com.medicalclaim.entity.PolicyClaim;
import com.medicalclaim.repository.HospitalRepository;
import com.medicalclaim.repository.PolicyClaimRepository;
import com.medicalclaim.repository.PolicyRepository;

/**
 * 
 * @author akuthota.raghu
 *
 */
@Service
public class PolicyClaimServiceImpl implements PolicyClaimService {

	// created instance for PolicyClaimRepository to call predefined JPA repository
	// methods
	@Autowired
	private PolicyRepository policyRepository;

	@Autowired
	private HospitalRepository hospitalRepository;

	@Autowired
	private PolicyClaimRepository policyClaimRepository;

	@Override
	public PolicyClaimResponseDto raisePolocyClaim(PolicyClaimRequestDto policyClaimRequestDto) {

		PolicyClaim policyClaim = new PolicyClaim();

		PolicyClaimResponseDto response = new PolicyClaimResponseDto();
		Policy policy = policyRepository.findByPolicyNo(policyClaimRequestDto.getPolicyNo());
		Optional<Hospital> hospital = hospitalRepository.findById(policyClaimRequestDto.getHospitalId());
		Optional<Policy> isPolicy = Optional.ofNullable(policy);

		if (isPolicy.isPresent() && hospital.isPresent()) {
			policyClaim.setPolicyId(policy);
			policyClaim.setHospitalId(hospital.get());

			// Generate Claim Reference Number
			policyClaim.setClaimNumber("CN2019-00001");

			BeanUtils.copyProperties(policyClaimRequestDto, policyClaim);

			policyClaimRepository.save(policyClaim);
			response.setMessage("Claim Raise Successfully"); // get message from constants file
			response.setStatus("SUCCESS"); // get status from constants file

		} else {
			response.setMessage(AppConstant.NO_RECORDS_FOUND);
		}

		return response;
	}
}
