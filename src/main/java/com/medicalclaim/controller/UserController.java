package com.medicalclaim.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicalclaim.constant.AppConstants;
import com.medicalclaim.dto.LoginDto;
import com.medicalclaim.dto.LoginResponseDto;
import com.medicalclaim.entity.User;
import com.medicalclaim.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @description User controller handles the logincheck method 
 * under the hospital entity
 * 
 * @author Janani.v
 * @since 11-12-2019
 * @version V1
 *
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * @Description Login method handles admin can login to aprroval
	 * @param loginDto
	 * @return
	 */
	@PostMapping(value = "/login")
	public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginDto) {
		log.info("user login checks...");
		LoginResponseDto response;
		User user = userService.login(loginDto);
		Optional<User> isUser = Optional.ofNullable(user);
		if (isUser.isPresent()) {
			response = new LoginResponseDto(AppConstants.SUCCESS, HttpStatus.OK.value(), AppConstants.LOGIN_SUCCESSFULLY);
		} else {
			response = new LoginResponseDto(AppConstants.FAILURE, HttpStatus.NOT_FOUND.value(), AppConstants.INVALID_LOGIN);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
