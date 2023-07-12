package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;

@RestController
public class EmployeeController {
	
	private List<Employee> data = new ArrayList<Employee>();
	
	@GetMapping("/employee")
	public List<Employee> getEmployee() {
		return data;
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
	
	@DeleteMapping("/employee/:employeeId")
	public String deleteEmployee(@RequestParam Integer employeeId) {
		for(int i = 0; i < data.size(); i++) {
			if(data.get(i).getEmployeeId() == employeeId) {
				data.remove(i);
				return "Delete Employee Success";
			}
		}
		return "Error Delete Employee";
	}
}
