package com.medicalclaim.constant;

/**
 * @description AppConstant is used for only we are maintaining the argcoded
 *              values in the whole application.
 * @author Govindasamy.C
 * @since 11-12-2019
 */
public class AppConstant {

	private AppConstant() {

	}

	// Common Httpstatus success and failure messages.
	public static final String SUCCESS = "SUCCESS";
	public static final String CLAIM_RAISED_SUCCESS = "Policy Claim Raised Successfully and send to First level approval";
	public static final String FAILURE = "FAILURE";
	public static final String CLAIM_NUMBER_ALREADY_EXIST = "Claim Number Already exist";
	public static final String INITIAL_CLAIM_RAISED = "Initial Claim Raised";

	// Hospital
	public static final String NO_RECORDS_FOUND = "No Records Found";

	// Login
	public static final String LOGIN_SUCCESSFULLY = "User Login Successfully";
	public static final String INVALID_LOGIN = "Invalid Username and Password";

	// Policy Details
	public static final String NO_POLICIES_FOUND = "No Policies Found";
	
	public static final String CLAIM_NUMBER_PREFIX = "CN-";

	public static final Integer APPROVAL_ID_1 = 1;
	public static final Integer APPROVAL_ID_2 = 2;
	

}
