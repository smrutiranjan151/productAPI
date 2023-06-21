package com.springboot.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.web.entities.Student;
import com.springboot.web.repository.StudentRepository;

@RestController
public class StudentRestController {

	@Autowired
	StudentRepository studentRepository;

	@RequestMapping(value = "/getStudentDetails/", method = RequestMethod.GET)
	public List<Student> getStudentDetails() {
		return studentRepository.findAll();
	}

	@RequestMapping(value = "/getStudentDetails/{id}", method = RequestMethod.GET)
	public Student getStudentDetailsById(@PathVariable("id") int id) {
		return studentRepository.findById(id).get();
	}
	
	@RequestMapping(value="/createStudentDetails/", method=RequestMethod.POST)
	public Student createStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	 
	@RequestMapping(value="/createStudentDetails/", method=RequestMethod.PUT)
	public void updateStudent(@RequestBody Student student) {
		studentRepository.save(student);
	}
	
	@RequestMapping(value = "/deleteStudentDetails/{id}", method = RequestMethod.DELETE)
	public void deleteStudentDetailsById(@PathVariable("id") int id) {
		studentRepository.deleteById(id);
	}

}
