package com.medicalclaim.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicalclaim.constant.AppConstant;
import com.medicalclaim.dto.PolicyClaimRequestDto;
import com.medicalclaim.dto.PolicyClaimResponseDto;
import com.medicalclaim.entity.Hospital;
import com.medicalclaim.entity.Policy;
import com.medicalclaim.entity.PolicyClaim;
import com.medicalclaim.entity.PolicyClaimApproval;
import com.medicalclaim.entity.User;
import com.medicalclaim.repository.HospitalRepository;
import com.medicalclaim.repository.PolicyClaimApprovalRepository;
import com.medicalclaim.repository.PolicyClaimRepository;
import com.medicalclaim.repository.PolicyRepository;
import com.medicalclaim.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author akuthota.raghu
 * @since 11-12-2019 This service is used to do the implementation logic for the
 *        Raise Claim and other medical claim related things
 */
@Service
@Slf4j
public class PolicyClaimServiceImpl implements PolicyClaimService {

	/* created instance for PolicyClaimRepository
		to call predefined JPA repository methods */
	@Autowired
	private PolicyRepository policyRepository;

	@Autowired
	private HospitalRepository hospitalRepository;

	@Autowired
	private PolicyClaimRepository policyClaimRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PolicyClaimApprovalRepository policyClaimApprovalRepository;

	/**
	 * @param PolicyClaimRequestDto
	 * @return PolicyClaimResponseDto This method is used to raise a claim and send
	 *         it to first level approval
	 */
	@Override
	@Transactional
	public PolicyClaimResponseDto raisePolocyClaim(PolicyClaimRequestDto policyClaimRequestDto) {

		PolicyClaim policyClaim = new PolicyClaim();

		PolicyClaimResponseDto response = new PolicyClaimResponseDto();
		Policy policy = policyRepository.findByPolicyNo(policyClaimRequestDto.getPolicyNo());
		Optional<Hospital> hospital = hospitalRepository.findById(policyClaimRequestDto.getHospitalId());
		Optional<Policy> isPolicy = Optional.ofNullable(policy);

		if (isPolicy.isPresent() && hospital.isPresent()) {
			policyClaim.setPolicyId(policy);
			policyClaim.setHospitalId(hospital.get());

			String claimNumber = AppConstant.CLAIM_NUMBER_PREFIX + generatePolicyClaimNumber();
			String claimNumberResponse = policyClaimRepository.findByClaimNumber(claimNumber);

			if (claimNumberResponse == null) {
				policyClaim.setClaimNumber(claimNumber);
			} else {

				if (!claimNumberResponse.equalsIgnoreCase(claimNumber)) {
					policyClaim.setClaimNumber(claimNumber);
				} else {
					response.setMessage(AppConstant.CLAIM_NUMBER_ALREADY_EXIST);
				}
			}

			BeanUtils.copyProperties(policyClaimRequestDto, policyClaim);

			PolicyClaim savedPolicyClaim = policyClaimRepository.save(policyClaim);

			PolicyClaimApproval policyClaimApproval = new PolicyClaimApproval();

			policyClaimApproval.setPolicyClaimId(savedPolicyClaim);
			policyClaimApproval.setApproval(false);
			policyClaimApproval.setComments(AppConstant.INITIAL_CLAIM_RAISED);

			User approvalLevelResponse = userRepository.findByApprovalLevel(AppConstant.APPROVAL_ID_1);
			policyClaimApproval.setClaimApprovalId(approvalLevelResponse);

			policyClaimApprovalRepository.save(policyClaimApproval);

			response.setMessage(AppConstant.CLAIM_RAISED_SUCCESS);
			response.setStatus(AppConstant.SUCCESS);

		} else {
			response.setMessage(AppConstant.NO_RECORDS_FOUND);
		}

		return response;
	}

	/**
	 * This below method will generate the random number with length of 8
	 * @return Long
	 */
	private Long generatePolicyClaimNumber() {
		String number = RandomStringUtils.random(8, false, true);
		return Long.valueOf(number);
	}
}
