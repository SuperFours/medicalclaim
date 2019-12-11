package com.medicalclaim.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicalclaim.constant.AppConstant;
import com.medicalclaim.dto.PolicyStatusDto;
import com.medicalclaim.dto.ViewPolicyDto;
import com.medicalclaim.entity.Policy;
import com.medicalclaim.entity.PolicyClaim;
import com.medicalclaim.entity.PolicyStatus;
import com.medicalclaim.repository.PolicyRepository;

/**
 * 
 * @author Govindasamy.C
 * @since 11-12-2019
 *
 */
@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	PolicyRepository policyRepository;

	/**
	 * 
	 */
	@Override
	public ViewPolicyDto getPolicy(String policyNo) {
		ViewPolicyDto viewPolicyDto = new ViewPolicyDto();
		Policy policy = policyRepository.findByPolicyNo(policyNo);
		Optional<Policy> isPolicy = Optional.ofNullable(policy);
		if (isPolicy.isPresent()) {
			BeanUtils.copyProperties(policy, viewPolicyDto);

			PolicyClaim policyClaim = policy.getPolicyClaim();
			BeanUtils.copyProperties(policyClaim, viewPolicyDto);
			viewPolicyDto.setClaimDate(policyClaim.getAdmissionDate());
			viewPolicyDto.setClaimAmount(policyClaim.getClaimAmount());

			Set<PolicyStatus> policyStatus = policy.getPolicyStatus();
			List<PolicyStatusDto> policyStatusDto = policyStatus.stream().map(this::convertEntityToDto)
					.collect(Collectors.toList());

			viewPolicyDto.setPolicyStatus(policyStatusDto);
			viewPolicyDto.setMessage(AppConstant.SUCCESS);
		} else {
			viewPolicyDto.setMessage(AppConstant.NO_POLICIES_FOUND);
		}
		return viewPolicyDto;
	}

	/**
	 * convert the policy status values to policy status dto values with using
	 * beanutils copyproperties.
	 * 
	 * @param policyStatus
	 * @return
	 */
	private PolicyStatusDto convertEntityToDto(PolicyStatus policyStatus) {
		PolicyStatusDto policyStatusDto = new PolicyStatusDto();
		BeanUtils.copyProperties(policyStatus, policyStatusDto);
		return policyStatusDto;
	}

}
