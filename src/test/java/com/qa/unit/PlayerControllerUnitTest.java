package com.qa.unit;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;



@SpringBootTest
public class PlayerControllerUnitTest {

	protected MockMvc mvc;
	

	@Test
	void testPlayerController() throws Exception {
		
	}

}
