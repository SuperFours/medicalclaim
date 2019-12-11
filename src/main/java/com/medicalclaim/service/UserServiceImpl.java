package com.medicalclaim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicalclaim.dto.LoginDto;
import com.medicalclaim.entity.User;
import com.medicalclaim.repository.UserRepository;

/**
 * @description hospital Service Implementation is responsible for the methods under the User entity
 * @author Janani.V
 * @since 11-12-2019
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	/**
	 * login with userId and password
	 */
	@Override
	public User login(LoginDto loginDto) {
		return userRepository.findByUserIdAndPassword(loginDto.getUserId(), loginDto.getPassword());
		 
	}
}
