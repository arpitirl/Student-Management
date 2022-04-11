package com.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
