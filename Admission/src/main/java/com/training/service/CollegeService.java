package com.training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.entity.Address;
import com.training.entity.Course;
import com.training.entity.Student;
import com.training.payload.EnrollStudentRequestPayload;
import com.training.repository.AddressRepository;

import com.training.repository.StudentRepository;

@Service
public class CollegeService {
	@Autowired
	private StudentRepository repository;
	@Autowired
	private AddressRepository addressrepository;
	public String saving(EnrollStudentRequestPayload student) {
		System.out.println("Hi I m");
		Student s= new Student(student.getEmail(),student.getName(), student.getComputer(), student.getBiology());
		if(s.getBiology()>s.getComputer()) {
			s.setCourse(new Course(1, "MBBS"));
		}
		else {
			s.setCourse(new Course(2, "B.TECH"));
		}
		System.out.println("Hi I m1");
		Optional<Address> address=addressrepository.findByAddress(student.getAddress());
		System.out.println("Hi I m2");
		if(address.isEmpty()) {
			System.out.println("Hi I m here");
			s.setAddress(addressrepository.save(new Address(student.getAddress())));
		}
		else {
			System.out.println("Hi I m there");
			s.setAddress(address.get());
		}
		System.out.println("Hi I m there1");
		Student s1=repository.save(s);
		System.out.println("Hi I m there2");
		return("Student Enrolled");
	}

	public String enrollStudent(EnrollStudentRequestPayload student) {
		
		
;		if(repository.existsByEmail(student.getEmail())) {
			return("Invalid");
		}
		else {

			return(saving(student));
		}
		
	}
	public String updateStudent(int id, EnrollStudentRequestPayload student) {
		if(!repository.existsById(id)) {
			return("Invalid Request");
		}
		System.out.println("Hi I m");
		Student s= new Student(student.getEmail(),student.getName(), student.getComputer(), student.getBiology());
		s.setId(id);
		if(s.getBiology()>s.getComputer()) {
			s.setCourse(new Course(1, "MBBS"));
		}
		else {
			s.setCourse(new Course(2, "B.TECH"));
		}
		System.out.println("Hi I m1");
		Optional<Address> address=addressrepository.findByAddress(student.getAddress());
		System.out.println("Hi I m2");
		if(address.isEmpty()) {
			System.out.println("Hi I m here");
			s.setAddress(addressrepository.save(new Address(student.getAddress())));
		}
		else {
			System.out.println("Hi I m there");
			s.setAddress(address.get());
		}
		System.out.println("Hi I m there1");
		Student s1=repository.save(s);
		System.out.println("Hi I m there2");
		//return("Student enrolled");
		
		repository.save(s);
		return("Update Successfull");
	}
	public String deleteById(int id) {
		
		repository.deleteById(id);
		return("Transaction Successfull");
	}
	public Optional<Student> getById(int id) {
		return repository.findById(id);
	}
	public List<Student> getStudents() {
		return repository.findAll();
	}
	
	

}
