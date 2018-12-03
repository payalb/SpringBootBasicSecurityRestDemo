package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.dto.Employee;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {
	/*Spring Boot provides @TestConfiguration annotation that 
	 * can be used on classes in src/test/java to indicate that 
	 * they should not be picked up by scanning.*/	
	@TestConfiguration
	static class TestConfig{
		 @Bean
	        public EmployeeService employeeService() {
	            return new EmployeeServiceImpl();
	        }
	}

	   @Autowired
	    private EmployeeService employeeService;
	   /*. It creates a Mock for the EmployeeRepository which 
	    * can be used to bypass the call to the actual EmployeeRepository:*/
	   @MockBean EmployeeRepository rep;
	   
	   @Test
	   public void testFindByName() {
		   Employee emp= new Employee();
		   emp.setId(1l);
		   emp.setName("Payal");
		   Mockito.when(rep.findByName("Payal")).thenReturn(emp);
		   Employee e=employeeService.findByName("Payal");
		   assertNotNull(e);
		   assertEquals(1l, e.getId().longValue());
		   assertEquals("Payal", e.getName());
	   }
}
