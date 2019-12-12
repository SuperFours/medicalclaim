package com.medicalclaim.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicalclaim.constant.AppConstant;
import com.medicalclaim.dto.ApprovalRequestDto;
import com.medicalclaim.dto.PolicyClaimRequestDto;
import com.medicalclaim.dto.PolicyClaimResponseDto;
import com.medicalclaim.dto.ResponseDto;
import com.medicalclaim.dto.ViewClaimDto;
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

/**
 * 
 * @author akuthota.raghu
 * @since 11-12-2019 This service is used to do the implementation logic for the
 *        Raise Claim and other medical claim related things
 */
@Service
public class PolicyClaimServiceImpl implements PolicyClaimService {

	/*
	 * created instance for PolicyClaimRepository to call predefined JPA repository
	 * methods
	 */
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
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(AppConstant.DATE_FORMAT_PATTERN);

	/**
	 * @param PolicyClaimRequestDto
	 * @return PolicyClaimResponseDto This method is used to raise a claim and send
	 *         it to first level approval
	 */
	@Override
	@Transactional
	public PolicyClaimResponseDto raisePolicyClaim(PolicyClaimRequestDto policyClaimRequestDto) {

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

			policyClaim.setClaimDate(LocalDate.now());
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
			// convert String to LocalDate
			LocalDate admissionDate = LocalDate.parse(policyClaimRequestDto.getAdmissionDate(), formatter);
			LocalDate dischargeDate = LocalDate.parse(policyClaimRequestDto.getDischargeDate(), formatter);

			policyClaim.setAdmissionDate(admissionDate);
			policyClaim.setDischargeDate(dischargeDate);

			PolicyClaim savedPolicyClaim = policyClaimRepository.save(policyClaim);

			PolicyClaimApproval policyClaimApproval = new PolicyClaimApproval();

			policyClaimApproval.setPolicyClaimId(savedPolicyClaim);

			User approvalLevelResponse = userRepository.findByApprovalLevel(AppConstant.APPROVE_LEVEL_1);
			policyClaimApproval.setClaimApprovalId(approvalLevelResponse);

			policyClaimApprovalRepository.save(policyClaimApproval);

			response.setMessage(AppConstant.CLAIM_RAISED_SUCCESS);
			response.setStatus(AppConstant.SUCCESS);

		} else {
			response.setMessage(AppConstant.NO_RECORDS_FOUND);
		}

		return response;
	}

	@Override
	public ResponseDto claimApproval(Integer claimId, ApprovalRequestDto approvalRequestDto) {
		ResponseDto responseDto = new ResponseDto();
		Optional<PolicyClaimApproval> policyClaimApproval = policyClaimApprovalRepository.findById(claimId);
		if (policyClaimApproval.isPresent()) {
			PolicyClaimApproval approval = policyClaimApproval.get();
			approval.setComments(approvalRequestDto.getComments());
			if (approvalRequestDto.getApproval().equals(AppConstant.APPROVE_CLAIM)) {
				responseDto.setStatus(AppConstant.SUCCESS);
				responseDto.setMessage(AppConstant.APPROVE_CLAIM_SUCCESS);
				approval.setStatus(AppConstant.APPROVED);
			} else {
				responseDto.setStatus(AppConstant.SUCCESS);
				responseDto.setMessage(AppConstant.REJECT_CLAIM_SUCCESS);
				approval.setStatus(AppConstant.REJECTED);
			}
			policyClaimApprovalRepository.save(approval);

			// Check and Send Second Level Approval
			Optional<User> user = userRepository.findById(approvalRequestDto.getApprovalId());
			if (user.isPresent()) {
				Integer approvalLevelId = user.get().getApprovalLevel();
				// Check ClaimAmount Value
				Double policyCapAmount = approval.getPolicyClaimId().getPolicyId().getMaxClaimAmount();
				Double claimAmount = approval.getPolicyClaimId().getClaimAmount();

				if (approvalLevelId.equals(AppConstant.APPROVE_LEVEL_1) && (claimAmount > policyCapAmount)) {
					User secondLevelUser = userRepository.findByApprovalLevel(AppConstant.APPROVE_LEVEL_2);
					PolicyClaimApproval ciaimApproval = new PolicyClaimApproval();
					ciaimApproval.setClaimApprovalId(secondLevelUser);
					ciaimApproval.setPolicyClaimId(approval.getPolicyClaimId());
					policyClaimApprovalRepository.save(ciaimApproval);
				}
			}
		} else {
			responseDto.setStatus(AppConstant.FAILURE);
			responseDto.setMessage(AppConstant.NO_CLAIMS_FOUND);
		}
		return responseDto;
	}

	/**
	 * This below method will generate the random number with length of 8
	 * 
	 * @return Long
	 */
	private Long generatePolicyClaimNumber() {
		String number = RandomStringUtils.random(8, false, true);
		return Long.valueOf(number);
	}

	@Override
	public List<ViewClaimDto> claimListByUserId(Integer userId) {
		List<ViewClaimDto> claimLists = new ArrayList<>();
		Optional<User> userResponse = userRepository.findById(userId);

		if (userResponse.isPresent()) {

			List<PolicyClaimApproval> response = null;

			response = policyClaimApprovalRepository.findByClaimApprovalIdIdAndStatusNull(userResponse.get().getId());

			claimLists = response.stream().map(this::convertDto).collect(Collectors.toList());
		}

		return claimLists;
	}

	/**
	 * 
	 * @param policyClaimApproval
	 * @return
	 */
	private ViewClaimDto convertDto(PolicyClaimApproval policyClaimApproval) {
		ViewClaimDto viewClaimDto = new ViewClaimDto();
		viewClaimDto.setClaimNumber(policyClaimApproval.getPolicyClaimId().getClaimNumber());
		viewClaimDto.setName(policyClaimApproval.getPolicyClaimId().getName());
		viewClaimDto.setPolicyNo(policyClaimApproval.getPolicyClaimId().getPolicyId().getPolicyNo());
		viewClaimDto.setHospitalName(policyClaimApproval.getPolicyClaimId().getHospitalId().getName());
		viewClaimDto.setClaimAmount(policyClaimApproval.getPolicyClaimId().getClaimAmount());
		viewClaimDto.setClaimDate(policyClaimApproval.getPolicyClaimId().getAdmissionDate());
		viewClaimDto.setId(policyClaimApproval.getId());
		return viewClaimDto;
	}
}
