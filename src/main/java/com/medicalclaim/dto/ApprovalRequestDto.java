package com.medicalclaim.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApprovalRequestDto {

	private String comments;
	private String approval;
	private Integer approvalId; 
}
