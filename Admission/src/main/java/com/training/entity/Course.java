package com.training.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity

@Table(name="Course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String course;
	@OneToMany(mappedBy = "course")
	private List<Student> students;
	public Course(int id, String course) {
		super();
		this.id = id;
		this.course = course;
	}
	public Course() {
		super();
	}
	

}
