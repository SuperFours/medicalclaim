package com.medicalclaim.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicalclaim.dto.HospitalDto;
import com.medicalclaim.entity.Hospital;
import com.medicalclaim.repository.HospitalRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
/**
 * @description HospitalServiceImpl-hospital Service Implementation is responsible for the methods under the hospital entity
 * @author Janani.V
 * @since 11-12-2019
 *
 */
public class HospitalServiceImpl implements HospitalService {
	
	@Autowired
	HospitalRepository hospitalRepository;

	/**
	 * getAllHospital- In this method ,we will get all the hospitalList from database.
	 * @return List of the hospitalList using HospitalDto 
	 * hospitalRepository is used to get the findall method from the jpa repository 
	 */
	@Override
	public List<HospitalDto> getAllHospital() {
		List<HospitalDto> hospitalList=new ArrayList<>();
		List<Hospital> hospital=hospitalRepository.findAll();
		log.info(" getting city details");
		hospitalList = hospital.stream().map(this::convertEntityToDto).collect(Collectors.toList());
		return hospitalList;
		
	}
	
	/**
	 * Here, convertEntityToDto is used to convert Hospital entity into hospitalDto
	 * Beanutils is used for copy the properties of source to target
	 */
	
	private HospitalDto convertEntityToDto(Hospital hospital) {
		HospitalDto hospitalDto = new HospitalDto();
		BeanUtils.copyProperties(hospital, hospitalDto);
		log.info("converting entity into Dto");
		return hospitalDto;
	}

}
