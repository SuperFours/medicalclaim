package com.medicalclaim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicalclaim.entity.PolicyClaimApproval;

/**
 * 
 * @author akuthota.raghu
 * @since 11-12-2019
 * PolicyClaimApprovalRepository for handling JPA operations
 *
 */

@Repository
public interface PolicyClaimApprovalRepository  extends JpaRepository<PolicyClaimApproval, Integer>{
	
	List<PolicyClaimApproval> findByClaimApprovalIdIdAndStatusNull(Integer userId);

}
