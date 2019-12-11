package com.medicalclaim.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicalclaim.constant.AppConstant;
import com.medicalclaim.dto.ViewPolicyDto;
import com.medicalclaim.service.PolicyService;

/**
 * @description
 * @author Govindasamy.C
 * @since 11-12-2019
 */
@RestController
@RequestMapping("/policies")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PolicyController {

	@Autowired
	PolicyService policyService;

	/**
	 * 
	 * @param policyNo
	 * @return
	 */
	@GetMapping("/{policyNo}")
	public ResponseEntity<ViewPolicyDto> getPolicy(@PathVariable String policyNo) {
		ViewPolicyDto viewPolicyDto = policyService.getPolicy(policyNo);
		Optional<String> response = Optional.ofNullable(viewPolicyDto.getMessage());
		if (response.isPresent()) {
			if (viewPolicyDto.getMessage().equals(AppConstant.SUCCESS)) {
				viewPolicyDto.setStatusCode(HttpStatus.OK.value());
			} else {
				viewPolicyDto.setStatusCode(HttpStatus.NOT_FOUND.value());
			}
		}
		return new ResponseEntity<>(viewPolicyDto, HttpStatus.OK);
	}
}
