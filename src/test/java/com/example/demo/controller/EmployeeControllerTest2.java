package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.dto.Employee;
import com.example.demo.service.EmployeeService;

//Without using spring runner
@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest2 {
  //  @Autowired WebApplicationContext wac; 
	@Mock
	EmployeeService service;
	MockMvc mvc;
	@InjectMocks
	EmployeeController controller = new EmployeeController();

	@Before
	public void setup() {
		//does not use spring application context
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	//	MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void test1() throws Exception {
		Employee emp = new Employee();
		emp.setId(1l);
		emp.setName("Payal");
		when(service.findByName("Payal")).thenReturn(emp);
		mvc.perform(get("/employees/search?name=Payal").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		// .andExpect(jsonPath("$",hasSize(1)))
		// .andExpect( jsonPath("$[0].name",emp.getName()));
	}
}
