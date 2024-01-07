package com.student.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.entity.Course;
import com.student.entity.Student;
import com.student.repository.CourseRepository;
import com.student.repository.StudentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {
	 @Autowired
	    private StudentRepository studentRepository;

	    @Autowired
	    private CourseRepository courseRepository;

	    public void updateProfile(Long studentId, Student updatedStudent) {
	        Student student = studentRepository.findById(studentId)
	                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

	       
	        student.setName(updatedStudent.getName());
	        student.setDateOfBirth(updatedStudent.getDateOfBirth());
	        student.setGender(updatedStudent.getGender());
	     

	        studentRepository.save(student);
	    }

	   
	    public List<Course> getAssignedCourses(Long studentId) {
	        Student student = studentRepository.findById(studentId)
	                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

	        return student.getCourses().stream()
	                .collect(Collectors.toList());
	    }

	    public void leaveCourse(Long studentId, Long courseId) {
	        Student student = studentRepository.findById(studentId)
	                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

	        Course course = courseRepository.findById(courseId)
	                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));

	        student.getCourses().remove(course);
	        studentRepository.save(student);
	    }

//	    private Course convertToDTO(Course course) {
//	        Course courseDTO = new Course();
//	        BeanUtils.copyProperties(course, courseDTO);
//	        return courseDTO;
//	    }
}
