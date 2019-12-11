package com.medicalclaim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicalclaim.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Integer> {

	Policy findByPolicyNo(String policyNo);
}
