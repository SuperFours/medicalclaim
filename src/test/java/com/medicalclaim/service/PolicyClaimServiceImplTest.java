package com.medicalclaim.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

@RunWith(SpringJUnit4ClassRunner.class)

public class PolicyClaimServiceImplTest {

	@InjectMocks
	PolicyClaimServiceImpl policyClaimServiceImpl;

	@Mock
	PolicyRepository policyRepository;

	@Mock
	HospitalRepository hospitalRepository;
	
	@Mock
	PolicyClaimRepository policyClaimRepository;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	PolicyClaimApprovalRepository policyClaimApprovalRepository;
	
	PolicyClaimRequestDto policyClaimRequestDto = new PolicyClaimRequestDto();
	ApprovalRequestDto approvalRequestDto = new ApprovalRequestDto();
	Policy policy = new Policy();
	PolicyClaim policyClaim = new PolicyClaim();
	Hospital hospital = new Hospital();
	User user = new User();
	PolicyClaimApproval policyClaimApproval = new PolicyClaimApproval();
	@Before
	public void init() {
		policyClaimRequestDto.setPolicyNo("MED2019-0001");
		policyClaimRequestDto.setHospitalId(1);
		policyClaimRequestDto.setAdmissionDate("2019-12-11");
		policyClaimRequestDto.setDischargeDate("2019-12-11");
		
		approvalRequestDto.setApproval(AppConstant.APPROVED);
		approvalRequestDto.setApprovalId(1);
		hospital.setId(1);
		hospital.setName("Appollo");
		user.setId(1);
		user.setApprovalLevel(1);

		policyClaim.setId(1);
		policyClaim.setName("Raj");
		policyClaim.setClaimAmount(5000.00);
		

		policy.setId(1);
		policy.setPolicyNo("MED001");
		policyClaim.setHospitalId(hospital);
		policyClaim.setPolicyId(policy);
	}

	@Test
	public void test() {
		Optional<Hospital> isHospital = Optional.ofNullable(hospital);
		Optional<User> isUser = Optional.ofNullable(user);
		when(policyRepository.findByPolicyNo(policyClaimRequestDto.getPolicyNo())).thenReturn(policy);
		when(hospitalRepository.findById(policyClaimRequestDto.getHospitalId())).thenReturn(isHospital);
		when(policyClaimRepository.findByClaimNumber("CN-001")).thenReturn("CN-001");
		when(userRepository.findById(approvalRequestDto.getApprovalId())).thenReturn(isUser);
		when(policyClaimApprovalRepository.save(policyClaimApproval)).thenReturn(policyClaimApproval);
		
		PolicyClaimResponseDto response = policyClaimServiceImpl.raisePolicyClaim(policyClaimRequestDto);
		assertEquals(AppConstant.SUCCESS, response.getStatus());
	}
	
	@Test
	public void testClaimApproval() {
		policyClaimApproval.setId(1);
		Optional<PolicyClaimApproval> isClaim = Optional.ofNullable(policyClaimApproval);
		
		when(policyClaimApprovalRepository.findById(1)).thenReturn(isClaim);
		ResponseDto response = policyClaimServiceImpl.claimApproval(1, approvalRequestDto);
		assertEquals(AppConstant.SUCCESS, response.getStatus());
		
	}
	
	@Test
	public void testClaimListByUserId() {
		Optional<User> isUser = Optional.ofNullable(user);
		policyClaimApproval.setId(1);
		policyClaimApproval.setPolicyClaimId(policyClaim);
		
		
		List<PolicyClaimApproval> claims = new ArrayList<>();
		claims.add(policyClaimApproval);
		when(userRepository.findById(1)).thenReturn(isUser);
		when(policyClaimApprovalRepository.findByClaimApprovalIdIdAndStatusNull(1)).thenReturn(claims);
		
		List<ViewClaimDto> response =policyClaimServiceImpl.claimListByUserId(1);
		assertThat(response).hasSize(1);
	}

}
