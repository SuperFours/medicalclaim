package com.medicalclaim.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.medicalclaim.constant.AppConstant;
import com.medicalclaim.dto.ApprovalRequestDto;
import com.medicalclaim.dto.PolicyClaimRequestDto;
import com.medicalclaim.dto.PolicyClaimResponseDto;
import com.medicalclaim.dto.ResponseDto;
import com.medicalclaim.dto.ViewClaimDto;
import com.medicalclaim.dto.ViewClaimDtoResponse;
import com.medicalclaim.service.PolicyClaimService;

@RunWith(SpringJUnit4ClassRunner.class)
public class PolicyClaimControllerTest {

	@InjectMocks
	PolicyClaimController policyClaimController;

	@Mock
	PolicyClaimService policyClaimService;

	PolicyClaimRequestDto policyClaimRequestDto = new PolicyClaimRequestDto();
	PolicyClaimResponseDto policyClaimResponseDto = new PolicyClaimResponseDto();
	ApprovalRequestDto approvalRequestDto = new ApprovalRequestDto();
	ResponseDto responseDto = new ResponseDto();

	@Before
	public void init() {
		policyClaimRequestDto.setHospitalId(1);
		policyClaimRequestDto.setName("Moorthy");
		policyClaimRequestDto.setPolicyNo("MED2019-001");

		policyClaimResponseDto.setStatusCode(200);
		policyClaimResponseDto.setStatus(AppConstant.SUCCESS);

		approvalRequestDto.setApproval(AppConstant.APPROVED);
		approvalRequestDto.setApprovalId(1);
		approvalRequestDto.setComments("Approved");
		
		responseDto.setStatus(AppConstant.SUCCESS);
	}

	@Test
	public void testCreatePolicyClaim() {
		when(policyClaimService.raisePolicyClaim(policyClaimRequestDto)).thenReturn(policyClaimResponseDto);
		ResponseEntity<PolicyClaimResponseDto> response = policyClaimController
				.createPolicyClaim(policyClaimRequestDto);
		assertEquals(200, response.getBody().getStatusCode());
	}

	@Test
	public void testCreatePolicyClaimForNotPresent() {

		policyClaimResponseDto.setStatus(null);
		when(policyClaimService.raisePolicyClaim(policyClaimRequestDto)).thenReturn(policyClaimResponseDto);
		ResponseEntity<PolicyClaimResponseDto> response = policyClaimController
				.createPolicyClaim(policyClaimRequestDto);
		assertEquals(400, response.getBody().getStatusCode());
	}

	@Test
	public void testClaimApproval() {
		responseDto.setStatusCode(200);
		when(policyClaimService.claimApproval(1, approvalRequestDto)).thenReturn(responseDto);
		ResponseEntity<ResponseDto> response = policyClaimController.claimApproval(1, approvalRequestDto);
		assertEquals(200, response.getBody().getStatusCode());
	}

	@Test
	public void testClaimListByUser() {
		List<ViewClaimDto> viewClaims = new ArrayList<>();
		ViewClaimDto viewClaimDto = new ViewClaimDto();
		viewClaimDto.setId(1);
		viewClaimDto.setPolicyNo("MED2019-0001");
		viewClaims.add(viewClaimDto);
		
		when(policyClaimService.claimListByUserId(1)).thenReturn(viewClaims);
		ResponseEntity<ViewClaimDtoResponse> response = policyClaimController.claimListByUser(1);
		assertEquals(200, response.getBody().getStatusCode());
	}
}
