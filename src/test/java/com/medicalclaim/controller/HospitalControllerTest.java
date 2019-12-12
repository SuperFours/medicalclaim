package com.medicalclaim.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.medicalclaim.dto.HospitalDto;
import com.medicalclaim.dto.HospitalResponseDto;
import com.medicalclaim.service.HospitalService;

@RunWith(SpringJUnit4ClassRunner.class)
public class HospitalControllerTest {

	@InjectMocks
	HospitalController hospitalController;

	@Mock
	HospitalService hospitalService;
	HospitalDto hospitalDto = new HospitalDto();

	@Before
	public void init() {

		hospitalDto.setId(1);
		hospitalDto.setName("Appollo Hospitals");
		hospitalDto.setPlace("Bangalore");
	}

	@Test
	public void testGetHospitals() {
		List<HospitalDto> hospitals = new ArrayList<>();
		hospitals.add(hospitalDto);

		when(hospitalService.getAllHospital()).thenReturn(hospitals);
		ResponseEntity<HospitalResponseDto> response = hospitalController.getHospitalLists();
		assertThat(response.getBody().getHospitals()).hasSize(1);
	}

}
