package com.medicalclaim.constant;

import org.springframework.stereotype.Component;

/**
 * @description AppConstant is used for only we are maintaining the argcoded
 *              values in the whole application.
 * @author Govindasamy.C
 * @since 11-12-2019
 */
@Component
public class AppConstant {

	private AppConstant() {

	}

	// Common Httpstatus success and failure messages.
	public static final String SUCCESS = "SUCCESS";
	public static final String CLAIM_RAISED_SUCCESS = "Policy claim raised successfully";
	public static final String FAILURE = "FAILURE";
	public static final String CLAIM_NUMBER_ALREADY_EXIST = "Claim for policy number already exist";
	public static final String INITIAL_CLAIM_RAISED = "Initial Claim Raised";

	// Hospital
	public static final String NO_RECORDS_FOUND = "No Records Found";

	// Login
	public static final String LOGIN_SUCCESSFULLY = "User Login Successfully";
	public static final String INVALID_LOGIN = "Invalid Username and Password";

	// Policy Details
	public static final String NO_POLICIES_FOUND = "No Policies Found";

	// Claim Details
	public static final String CLAIM_NUMBER_PREFIX = "CN-";
	public static final String NO_CLAIMS_FOUND = "No Claims Found";

	// Claim Details
	public static final String APPROVE_CLAIM = "approve";
	public static final String APPROVE_CLAIM_SUCCESS = "Policy Approved Successfully";
	public static final String REJECT_CLAIM_SUCCESS = "Policy Rejected Successfully";

	// Claim Approval
	public static final String APPROVED = "Approved";
	public static final String REJECTED = "Rejected";
	public static final Integer APPROVE_LEVEL_1 = 1;
	public static final Integer APPROVE_LEVEL_2 = 2;
	public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";

}
