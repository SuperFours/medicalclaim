package com.medicalclaim.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicalclaim.constant.AppConstant;
import com.medicalclaim.dto.ApprovalRequestDto;
import com.medicalclaim.dto.PolicyClaimRequestDto;
import com.medicalclaim.dto.PolicyClaimResponseDto;
import com.medicalclaim.dto.ResponseDto;
import com.medicalclaim.dto.ViewClaimDto;
import com.medicalclaim.dto.ViewClaimDtoResponse;
import com.medicalclaim.service.PolicyClaimService;

/**
 * 
 * @author akuthota.raghu
 * @since 11-12-2019 This ClaimController - is use to handle the all the REST
 *        API calls for Claim related things
 *
 */
@RestController
@RequestMapping("/claims")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class PolicyClaimController {

	// creating reference for ClaimService

	@Autowired
	private PolicyClaimService policyClaimService;

	/**
	 * @param claim
	 * @return response entity of PolicyClaimResponseDto
	 */
	@PostMapping
	public ResponseEntity<PolicyClaimResponseDto> createPolicyClaim(
			@RequestBody PolicyClaimRequestDto policyClaimRequestDto) {

		PolicyClaimResponseDto policyClaimResponsetDto = policyClaimService.raisePolicyClaim(policyClaimRequestDto);

		Optional<String> responseDto = Optional.ofNullable(policyClaimResponsetDto.getStatus());
		if (responseDto.isPresent()) {
			policyClaimResponsetDto.setStatusCode(HttpStatus.OK.value());
		} else {
			policyClaimResponsetDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
		}

		return new ResponseEntity<>(policyClaimResponsetDto, HttpStatus.OK);
	}

	/**
	 * claim approval with userId
	 * 
	 * @param approvalRequestDto
	 * @return
	 */
	@PostMapping("/{claimId}/approval")
	public ResponseEntity<ResponseDto> claimApproval(@PathVariable Integer claimId,
			@Valid @RequestBody ApprovalRequestDto approvalRequestDto) {
		ResponseDto responseDto = policyClaimService.claimApproval(claimId, approvalRequestDto);
		if (responseDto.getStatus().equals(AppConstant.SUCCESS)) {
			responseDto.setStatusCode(HttpStatus.OK.value());
		} else {
			responseDto.setStatusCode(HttpStatus.OK.value());
		}
		responseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<ViewClaimDtoResponse> claimListByUser(@PathVariable Integer userId) {
		List<ViewClaimDto> viewClaimDto = policyClaimService.claimListByUserId(userId);

		ViewClaimDtoResponse viewClaimDtoResponse = new ViewClaimDtoResponse();

		viewClaimDtoResponse.setMessage(AppConstant.SUCCESS);
		viewClaimDtoResponse.setStatusCode(HttpStatus.OK.value());
		viewClaimDtoResponse.setClaims(viewClaimDto);

		return new ResponseEntity<>(viewClaimDtoResponse, HttpStatus.OK);
	}
}
