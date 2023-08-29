package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@RestController
public class StudentController {

	@Autowired
	StudentRepository studentRepository;

	@GetMapping("/students")
	public ResponseEntity<Object> getStudents() {
		try {

			List<Student> students = studentRepository.findAll();
			return new ResponseEntity<>(students, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/students")
	public ResponseEntity<Object> addStudent(@RequestBody Student body) {
		try {

			Student newStudent = studentRepository.save(body);

			return new ResponseEntity<>(newStudent, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/student/{studentId}")
	public ResponseEntity<Object> updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student body) {

		try {

			Optional<Student> foundStudent = studentRepository.findById(studentId);

			if (foundStudent.isPresent()) {
				Student studentEdit = foundStudent.get();

				studentEdit.setFname(body.getFname());
				studentEdit.setLname(body.getLname());
				studentEdit.setEmail(body.getEmail());

				studentRepository.save(studentEdit);

				return new ResponseEntity<>(studentEdit, HttpStatus.OK);

			} else {
				return new ResponseEntity<>("Student Not Found.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/student/{studentId}")
	public ResponseEntity<Object> deleteStudentById(@PathVariable("studentId") Integer studentId) {

		try {

			Optional<Student> foundStudent = studentRepository.findById(studentId);

			if (foundStudent.isPresent()) {
				studentRepository.delete(foundStudent.get());
				return new ResponseEntity<>("Delete Student Success.", HttpStatus.OK);

			} else {
				return new ResponseEntity<>("Student Not Found.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<>("Internal server error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
