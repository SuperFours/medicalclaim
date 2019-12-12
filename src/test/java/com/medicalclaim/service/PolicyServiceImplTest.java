package com.medicalclaim.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.medicalclaim.constant.AppConstant;
import com.medicalclaim.dto.ViewPolicyDto;
import com.medicalclaim.entity.Policy;
import com.medicalclaim.entity.PolicyClaim;
import com.medicalclaim.entity.PolicyStatus;
import com.medicalclaim.repository.PolicyRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class PolicyServiceImplTest {

	@InjectMocks
	PolicyServiceImpl policyServiceImpl;

	@Mock
	PolicyRepository policyRepository;
	
	Policy policy = new Policy();
	PolicyClaim policyClaim = new PolicyClaim();
	PolicyStatus policyStatus = new PolicyStatus();
	@Before
	public void init() {
		policy.setId(1);
		
		policyClaim.setId(1);
		policyStatus.setId(1);
		policy.setPolicyClaim(policyClaim);
	}

	@Test
	public void testGetPolicy() {
        Set<PolicyStatus> policyStatuses = new HashSet<>();
        policyStatuses.add(policyStatus);
        
		policy.setPolicyStatus(policyStatuses);
		
		when(policyRepository.findByPolicyNo("MED2019-00001")).thenReturn(policy);
		ViewPolicyDto response = policyServiceImpl.getPolicy("MED2019-00001");
		assertEquals(AppConstant.SUCCESS, response.getMessage());
	}

}
