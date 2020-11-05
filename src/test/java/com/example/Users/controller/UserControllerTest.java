package com.example.Users.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.Users.entity.Users;
import com.example.Users.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
public class UserControllerTest {

	
	private MockMvc mockMvc;
	
	@InjectMocks
	private UserController userController;
	
	 @Mock
	 UserService service;
	
	
	@Before
	public void setUp() throws Exception{
		mockMvc= MockMvcBuilders.standaloneSetup(userController).build();
	}
	

	
	@Test
	public void deleteUserTest() throws Exception{
		
		Users user = new Users();
		Mockito.when(service.getById(Mockito.anyString())).thenReturn(user);
		Mockito.doNothing().when(service).deleteById(user);

		mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", "anita")).andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	
	@Test
	public void getUserByIdTest() throws Exception{
		
		Users user = new Users();
		Mockito.when(service.getById(Mockito.anyString())).thenReturn(user);

		mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", "anita")).andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	
	
	@Test
	public void getAllUsersTest() throws Exception{
		
		Users user = new Users();
		Mockito.when(service.getAllUsers(Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyString(), Mockito.anyInt())).thenReturn(Mockito.anyList());

		mockMvc.perform(MockMvcRequestBuilders.get("/users")).andExpect(MockMvcResultMatchers.status().isOk());
		
	}
}
