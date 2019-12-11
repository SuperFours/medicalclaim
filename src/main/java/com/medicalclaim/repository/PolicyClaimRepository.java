package com.medicalclaim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicalclaim.entity.PolicyClaim;

@Repository
public interface PolicyClaimRepository extends JpaRepository<PolicyClaim, Integer> {

	String  findByClaimNumber(String claimNumber);

}
