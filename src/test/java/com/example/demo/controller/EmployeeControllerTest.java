package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.dto.Employee;
import com.example.demo.service.EmployeeService;
/*To test the Controllers, we can use @WebMvcTest. 
 * It will auto-configure the Spring MVC infrastructure for our unit tests.
In most of the cases, @WebMvcTest will be limited to bootstrap a 
single controller. It is used along with @MockBean to provide mock 
implementations for required dependencies.
@WebMvcTest also auto-configures MockMvc which offers a powerful way 
of easy testing MVC controllers without starting a full HTTP server.*/
@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

	@MockBean EmployeeService service;
	@Autowired MockMvc mvc;
	
	@Test
	public void test1() throws Exception {
		Employee emp= new Employee();
		emp.setId(1l);
		emp.setName("Payal");
		when(service.findByName("Payal")).thenReturn(emp);
		mvc.perform(get("/employees/search?name=Payal")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
		//	.andExpect(jsonPath("$",hasSize(1)))
			//.andExpect( jsonPath("$[0].name",emp.getName()));
	}
}
