package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;

@RestController
public class EmployeeController {
	
	private List<Employee> data = new ArrayList<Employee>();
	
	@GetMapping("/employee")
	public List<Employee> getEmployee() {
		return data;
	}
	
	@GetMapping("/employee/{employeeId}")
	public Employee getEmployeeById(@PathVariable("employeeId") Integer employeeId) {
		for(int i = 0; i < data.size(); i++) {
			if(data.get(i).getEmployeeId().equals(employeeId)) {
				return data.get(i);
			}
		}
		return null;
	}
	
	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee body) {
		for(int i = 0; i < data.size(); i++) {
			if(data.get(i).getEmployeeId() == body.getEmployeeId()) {
				return null;
			}
		}
		data.add(body);
		return body;
	}
	
	@PutMapping("/employee/{employeeId}")
	public String updateEmployee(@PathVariable("employeeId") Integer employeeId, @RequestBody Employee body) {
		for(int i = 0; i < data.size(); i++) {
			if(data.get(i).getEmployeeId().equals(employeeId)) {
				body.setEmployeeId(employeeId);
	            data.set(i, body);
				return "Update Employee Success";
			}
		}
		return "Error Update Employee";
	}
	
	@DeleteMapping("/employee/{employeeId}")
	public String deleteEmployee(@PathVariable("employeeId") Integer employeeId) {
		for(int i = 0; i < data.size(); i++) {
			if(data.get(i).getEmployeeId().equals(employeeId)) {
				data.remove(i);
				return "Delete Employee Success";
			}
		}
		return "Error Delete Employee";
	}
}
