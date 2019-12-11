package com.medicalclaim.service;

import java.util.List;

import com.medicalclaim.dto.ApprovalRequestDto;
import com.medicalclaim.dto.PolicyClaimRequestDto;
import com.medicalclaim.dto.PolicyClaimResponseDto;
import com.medicalclaim.dto.ResponseDto;
import com.medicalclaim.dto.ViewClaimDto;

/**
 * 
 * @author akuthota.raghu
 *
 */
public interface PolicyClaimService {

	public PolicyClaimResponseDto raisePolicyClaim(PolicyClaimRequestDto policyClaimRequestDto);

	public ResponseDto claimApproval(Integer claimId, ApprovalRequestDto approvalRequestDto);
		
	List<ViewClaimDto> claimListByUserId(Integer userId);
}
