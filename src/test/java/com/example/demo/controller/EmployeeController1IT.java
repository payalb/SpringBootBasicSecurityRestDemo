package com.example.demo.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
/**The TestRestTemplate is configured automatically when you use the @SpringBootTest annotation, and is configured to resolve relative paths to http://localhost:${local.server.port}
*/public class EmployeeController1IT {

	@Autowired TestRestTemplate template;


	@Test
	public void test1() throws Exception {
		
		ResponseEntity<String> response=template.getForEntity("/employees/search?name=Payal", String.class);
		assertTrue(response.getStatusCode().is2xxSuccessful());
		// .andExpect(jsonPath("$",hasSize(1)))
		// .andExpect( jsonPath("$[0].name",emp.getName()));
	}
}
