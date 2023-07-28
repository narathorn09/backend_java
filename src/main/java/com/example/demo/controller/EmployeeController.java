package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping("/employee")
	public List<Employee> getEmployee() {
		
		return employeeRepository.findAll();
		
	}
	
	@GetMapping("/employee/{employeeId}")
	public Optional<Employee> getEmployeeById(@PathVariable("employeeId") Integer employeeId) {
		
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		return employee;
		
	}
	
	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee body) {
		
		return employeeRepository.save(body);
		
	}
	
	@PutMapping("/employee/{employeeId}")
	public Employee updateEmployee(@PathVariable("employeeId") Integer employeeId, @RequestBody Employee body) {
		
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		
		if(employee.isPresent()) {
			Employee employeeEdit = employee.get();
			
			employeeEdit.setFristName(body.getFristName());
			employeeEdit.setLastName(body.getLastName());
			employeeEdit.setSalary(body.getSalary());
			
			employeeRepository.save(employeeEdit);
			
			return employee.get();
		}else {
			return null;
		}
		
	}
	
	@DeleteMapping("/employee/{employeeId}")
	public String deleteEmployee(@PathVariable("employeeId") Integer employeeId) {
		
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		
		if(employee.isPresent()) {
			employeeRepository.delete(employee.get());
			return "Delete Employee Success";
		}else {
			return "Employee Not Found";
		}
		
		
	}
}
