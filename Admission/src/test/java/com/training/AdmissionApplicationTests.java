package com.training;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.util.RequestPayload;
import com.training.entity.Address;
import com.training.entity.Course;
import com.training.entity.Student;
import com.training.payload.EnrollStudentRequestPayload;
import com.training.payload.ResponsePayload;
import com.training.repository.AddressRepository;
import com.training.repository.CourseRepository;
import com.training.repository.StudentRepository;
import com.training.repository.StudentRepository;
import com.training.service.CollegeService;


import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdmissionApplicationTests {
	@MockBean
	StudentRepository repository;
	@Autowired
	private CollegeService service;

	@Test
	public void getStudentTest() {
		List<Student> students=new ArrayList<Student>();
		Student s=new Student();
		Address address=new Address();
		address.setAddress("Ranchi");
		Course course=new Course();
		course.setCourse("Medical");
		Address address1=new Address();
		address1.setAddress("Jamshedpur");
		Course course1=new Course();
		course1.setCourse("Engineering");
		s.setAddress(address);
		s.setBiology(90);
		s.setComputer(100);
		s.setEmail("arpit@zee.com");
		s.setName("Arpit");
		
		s.setCourse(course);
		
		Student s1=new Student();
		s1.setAddress(address);
		s1.setBiology(90);
		s1.setComputer(80);
		s1.setEmail("arpan@zee.com");
		s1.setName("Arpan");
		
		s1.setCourse(course);
		students.add(s1);
		students.add(s);
		
		
		when(repository.findAll()).thenReturn(students);
		assertEquals(2, service.getStudents().size());
		verify(repository,times(1)).findAll();
	}
	@Test
	public void getStudentByIdTest() {
		Optional<Student> student=Optional.ofNullable(new Student());
		Student s=new Student();
		Address address=new Address();
		address.setAddress("Ranchi");
		Course course=new Course();
		course.setCourse("Medical");
		s.setId(1);
		s.setAddress(address);
		s.setBiology(90);
		s.setComputer(100);
		s.setEmail("arpit@zee.com");
		s.setName("Arpit");
		
		s.setCourse(course);
		student=Optional.ofNullable(s);
		when(repository.findById(1)).thenReturn(student);
		
		assertThat(service.getById(1)).isNotEmpty();
		
		
	}
	@Test
	public void deleteByIdTest() {
		String s="Transaction Successfull";
		
		assertNotNull(service.deleteById(1));
		verify(repository,times(1)).deleteById(1);
		
	}
	@Test
	public void updateTrueTest() {
		EnrollStudentRequestPayload s1=new EnrollStudentRequestPayload();
		s1.setAddress("Ranchi");
		s1.setBiology(90);
		s1.setComputer(80);
		s1.setEmail("arpan@zee.com");
		s1.setName("Arpan");
		when(repository.existsById(1)).thenReturn(true);
		assertEquals(service.updateStudent(1, s1),"Update Successfull");
	}
	@Test
	public void updateFalseTest() {
		EnrollStudentRequestPayload s1=new EnrollStudentRequestPayload();
		s1.setAddress("Ranchi");
		s1.setBiology(90);
		s1.setComputer(80);
		s1.setEmail("arpan@zee.com");
		s1.setName("Arpan");
		when(repository.existsById(1)).thenReturn(false);
		assertEquals(service.updateStudent(1, s1),"Invalid Request");
	}
	@Test
	public void updateSecTrueTest() {
		EnrollStudentRequestPayload s1=new EnrollStudentRequestPayload();
		s1.setAddress("Bihar");
		s1.setBiology(90);
		s1.setComputer(80);
		s1.setEmail("arpan@zee.com");
		s1.setName("Arpan");
		when(repository.existsById(1)).thenReturn(true);
		assertEquals(service.updateStudent(1, s1),"Update Successfull");
	}
	@Test
	public void enrollTrueTest() {
		EnrollStudentRequestPayload s1=new EnrollStudentRequestPayload();
		s1.setAddress("Ranchi");
		s1.setBiology(90);
		s1.setComputer(80);
		s1.setEmail("arpan@zee.com");
		s1.setName("Arpan");
		when(repository.existsByEmail("arpan@zee.com")).thenReturn(false);
		assertEquals(service.enrollStudent(s1),"Student Enrolled");
		
	}
	@Test
	public void enrollSecTrueTest() {
		EnrollStudentRequestPayload s1=new EnrollStudentRequestPayload();
		s1.setAddress("Bihiya");
		s1.setBiology(90);
		s1.setComputer(80);
		s1.setEmail("arpan@zee.com");
		s1.setName("Arpan");
		when(repository.existsByEmail("arpan@zee.com")).thenReturn(false);
		assertEquals(service.enrollStudent(s1),"Student Enrolled");
		
	}
	@Test
	public void enrollFalseTest() {
		EnrollStudentRequestPayload s1=new EnrollStudentRequestPayload();
		s1.setAddress("Ranchi");
		s1.setBiology(90);
		s1.setComputer(80);
		s1.setEmail("arpan@zee.com");
		s1.setName("Arpan");
		when(repository.existsByEmail("arpan@zee.com")).thenReturn(true);
		assertEquals(service.enrollStudent(s1),"Invalid");
		
	}

}
