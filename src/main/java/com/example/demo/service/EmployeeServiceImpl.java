package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.dto.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository rep;

	@Override
	public Employee findByName(String name) {
		return rep.findByName(name);
	}

	@Override
	public Employee save(Employee emp) {
		return rep.save(emp);
	}
}
