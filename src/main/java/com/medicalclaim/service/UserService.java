package com.medicalclaim.service;

import com.medicalclaim.dto.LoginDto;
import com.medicalclaim.entity.User;

public interface UserService {
	
	public User login(LoginDto loginDto);

}
