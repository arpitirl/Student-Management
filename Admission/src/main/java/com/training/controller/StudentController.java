package com.training.controller;

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

import com.training.entity.Student;
import com.training.payload.EnrollStudentRequestPayload;
import com.training.service.CollegeService;

@RestController
public class StudentController {
	@Autowired
	private CollegeService service;
	@PostMapping("/enrollstudent")
	public String enrollStudent(@RequestBody EnrollStudentRequestPayload student) {
		return service.enrollStudent(student);
		
	}
//	@PostMapping("/enrollAll")
//	public List<Student> enrollStudents(@RequestBody List<Student> students) {
//		return service.enrollStudents(students);
//		
//	}
	@GetMapping("/getAll")
	public List<Student> getAll(){
		return service.getStudents();
	}
	@GetMapping("/getStudent/{id}")
	public Optional<Student> getById(@PathVariable int id) {
		Optional<Student> s=service.getById(id);
		//Student st=s.get();
		//ResponsePayload response = new ResponsePayload(st.getId(), st.getEmail(), st.getFname(), st.getLname(), st.getComputer(), st.getBiology(), st.getAddress().getAddress(), st.getCourse().getCourse());
		return s;
	}
	@PutMapping("/updatestudent/{id}")
	public String updateStudent(@PathVariable int id,@RequestBody EnrollStudentRequestPayload student) {
		return service.updateStudent(id, student);
		
	}
	@DeleteMapping("/delete/{id}")
	public String deleteStudent(@PathVariable int id) {
		return service.deleteById(id);
	}
	

}
