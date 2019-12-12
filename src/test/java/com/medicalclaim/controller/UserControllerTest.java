package com.medicalclaim.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medicalclaim.constant.AppConstant;
import com.medicalclaim.dto.LoginDto;
import com.medicalclaim.dto.LoginResponseDto;
import com.medicalclaim.entity.User;
import com.medicalclaim.exception.CustomExceptionHandler;
import com.medicalclaim.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;
	User user = new User();
	private MockMvc mockMvc;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

		user.setId(1);
		user.setUserId("moorthy127@gmail.com");
		user.setPassword("start@123");
	}

	@Test
	public void testLogin() {
		LoginDto userDto = new LoginDto();
		userDto.setUserId("moorthy127@gmail.com");
		userDto.setPassword("start@123");
		when(userService.login(userDto)).thenReturn(user);

		ResponseEntity<LoginResponseDto> response = userController.login(userDto);
		assertEquals(AppConstant.SUCCESS, response.getBody().getStatus());
		assertEquals(200, response.getBody().getStatusCode());
	}

	@Test
	public void testInvalidLogin() {
		LoginDto userDto = new LoginDto();
		userDto.setUserId("moorthy127@gmail.com");
		userDto.setPassword("start");
		when(userService.login(userDto)).thenReturn(null);
		ResponseEntity<LoginResponseDto> response = userController.login(userDto);
		assertEquals(AppConstant.FAILURE, response.getBody().getStatus());
	}

	@Test
	public void testInvalidData() throws Exception {

		WebRequest webrequest = null;
		LoginDto userDto = new LoginDto();
		userDto.setUserId("moorthy127");
		userDto.setPassword("start");

		// MvcResult for mockmvc performed
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login").content(asJsonString(userDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();
		assertThat(result).isNotNull();

		new CustomExceptionHandler().handleException(result.getResolvedException(), webrequest);
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static String asJsonString(final Object obj) throws Exception {
		return new ObjectMapper().writeValueAsString(obj);
	}

}
