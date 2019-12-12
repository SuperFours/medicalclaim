package com.medicalclaim.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicalclaim.constant.AppConstant;
import com.medicalclaim.dto.HospitalDto;
import com.medicalclaim.dto.HospitalResponseDto;
import com.medicalclaim.service.HospitalService;

import lombok.extern.slf4j.Slf4j;

/**
 * @description HospitalController- controlling hospital entities and it has
 *              some methods under the hospital entity
 * 
 * @author Janani.v
 * @since 11-12-2019
 * @version V1
 *
 */
@RestController
@Slf4j
@RequestMapping("/hospitals")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HospitalController {

	@Autowired
	HospitalService hospitalService;

	/**
	 * @description getHospitalList method - implements the getAllHospitalList
	 *              method Api response ids returing statsu code and status message
	 * @return list of hospitalList and status code and status message
	 */

	@GetMapping
	public ResponseEntity<HospitalResponseDto> getHospitalLists() {
		List<HospitalDto> hospitalList = hospitalService.getAllHospital();
		log.info("getting hospital list");
		HospitalResponseDto hospitalResponseDto = new HospitalResponseDto();
		Optional<List<HospitalDto>> isHospitalList = Optional.ofNullable(hospitalList);
		hospitalResponseDto.setHospitals(hospitalList);
		if (isHospitalList.isPresent()) {
			hospitalResponseDto.setStatus(AppConstant.SUCCESS);
			hospitalResponseDto.setStatusCode(HttpStatus.OK.value());
		} else {
			hospitalResponseDto.setStatus(AppConstant.NO_RECORDS_FOUND);
			hospitalResponseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		}
		return new ResponseEntity<>(hospitalResponseDto, HttpStatus.OK);
	}

}
