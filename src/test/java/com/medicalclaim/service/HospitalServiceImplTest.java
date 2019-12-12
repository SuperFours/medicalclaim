package com.medicalclaim.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.medicalclaim.dto.HospitalDto;
import com.medicalclaim.entity.Hospital;
import com.medicalclaim.repository.HospitalRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class HospitalServiceImplTest {

	@InjectMocks
	HospitalServiceImpl hospitalServiceImpl;

	@Mock
	HospitalRepository hospitalRepository;

	Hospital hospital = new Hospital();
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		hospital.setId(1);
	}

	
	@Test
	public void testGetAllHospital() {
		List<Hospital> hospitals = new ArrayList<>();
		hospitals.add(hospital);
		
		when(hospitalRepository.findAll()).thenReturn(hospitals);
		List<HospitalDto> response = hospitalServiceImpl.getAllHospital();
		assertThat(response).hasSize(1);
	}

}
