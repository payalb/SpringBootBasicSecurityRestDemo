package com.example.demo.service;

import com.example.demo.dto.Employee;

public interface EmployeeService {

	Employee findByName(String name);

	Employee save(Employee emp);

}