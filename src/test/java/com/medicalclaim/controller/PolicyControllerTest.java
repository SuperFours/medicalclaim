package com.medicalclaim.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.medicalclaim.constant.AppConstant;
import com.medicalclaim.dto.ViewPolicyDto;
import com.medicalclaim.service.PolicyService;

@RunWith(SpringJUnit4ClassRunner.class)
public class PolicyControllerTest {

	@InjectMocks
	PolicyController policyController;

	@Mock
	PolicyService policyService;
	ViewPolicyDto viewPolicyDto = new ViewPolicyDto();

	@Before
	public void init() {
		viewPolicyDto.setClaimNumber("CN-000001");
	}

	@Test
	public void testGetPolicy() {
		when(policyService.getPolicy("MED2019-0001")).thenReturn(viewPolicyDto);

		ResponseEntity<ViewPolicyDto> response = policyController.getPolicy("MED2019-0001");
		assertEquals(viewPolicyDto.getClaimNumber(), response.getBody().getClaimNumber());
	}

	@Test
	public void testGetPolicyForPresent() {
		viewPolicyDto.setStatus(AppConstant.SUCCESS);
		when(policyService.getPolicy("MED2019-0001")).thenReturn(viewPolicyDto);

		ResponseEntity<ViewPolicyDto> response = policyController.getPolicy("MED2019-0001");
		assertEquals(viewPolicyDto.getClaimNumber(), response.getBody().getClaimNumber());
	}

	@Test
	public void testGetPolicyForNotPresent() {
		viewPolicyDto.setStatus(AppConstant.FAILURE);
		when(policyService.getPolicy("MED2019-0002")).thenReturn(viewPolicyDto);

		ResponseEntity<ViewPolicyDto> response = policyController.getPolicy("MED2019-0002");
		assertEquals(viewPolicyDto.getClaimNumber(), response.getBody().getClaimNumber());
	}

}
