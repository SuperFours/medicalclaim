package com.medicalclaim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicalclaim.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	  User findByUserIdAndPassword(String userId, String password);

	  User findByApprovalLevel(Integer approvalId);
}
