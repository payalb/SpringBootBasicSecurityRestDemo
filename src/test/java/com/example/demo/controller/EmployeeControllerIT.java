package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.dto.Employee;

//Need to bring up app context
@RunWith(SpringRunner.class)
/*The @SpringBootTest annotation can be used when we need to bootstrap the entire container. The annotation works by creating the ApplicationContext that will be utilized in our tests.*/
/*We can use the webEnvironment attribute of @SpringBootTest to configure our runtime environment; we’re using WebEnvironment.MOCK here – so that the container will operate in a mock servlet environment*/
@SpringBootTest(webEnvironment=WebEnvironment.MOCK)
@AutoConfigureMockMvc
/*We can use the @TestPropertySource annotation to configure locations of properties files specific to our tests. Please note that the property file loaded with @TestPropertySource will override the existing application.properties file.*/
/*@TestPropertySource(
  locations = "classpath:application-integrationtest.properties")
  
  . 
  *
  */
public class EmployeeControllerIT {

	@Autowired MockMvc mvc;
	@Autowired EmployeeController controller;


	@Test
	public void test1() throws Exception {
		Employee emp = new Employee();
		emp.setId(1l);
		emp.setName("Payal");
		mvc.perform(get("/employees/search?name=Payal").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		// .andExpect(jsonPath("$",hasSize(1)))
		// .andExpect( jsonPath("$[0].name",emp.getName()));
	}
}
