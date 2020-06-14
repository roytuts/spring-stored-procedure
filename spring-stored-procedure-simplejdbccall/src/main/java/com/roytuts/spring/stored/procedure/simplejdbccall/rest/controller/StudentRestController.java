package com.roytuts.spring.stored.procedure.simplejdbccall.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.roytuts.spring.stored.procedure.simplejdbccall.dao.StudentDao;
import com.roytuts.spring.stored.procedure.simplejdbccall.model.Student;

@RestController
public class StudentRestController {

	@Autowired
	private StudentDao dao;

	@GetMapping("/student/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable Integer id) {
		System.out.println("id: " + id);
		// Student student = dao.getStudentUsingProcMethod1(id);
		Student student = dao.getStudentUsingProcMethod2(id);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> students = dao.getAllStudentUsingProcMethod1();

		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}

}
