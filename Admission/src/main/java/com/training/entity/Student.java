package com.training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private int computer;
	private int biology;
	private String email;
	@ManyToOne
	@JoinColumn(name = "addressId")
	private Address address;
	@ManyToOne
	@JoinColumn(name = "courseId")
	private Course course;
	public Student(String email,String name, int computer, int biology) {
		super();
		this.email=email;
		this.name = name;
		this.computer = computer;
		this.biology = biology;
	}

}
