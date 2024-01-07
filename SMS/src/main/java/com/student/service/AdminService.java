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
public class AdminService {

	@Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public void admitStudent(Student studentDTO) {
        Student student = new Student();
        studentRepository.save(student);
    }

    public void uploadCourse(Course courseDTO) {
        Course course = new Course();
        courseRepository.save(course);
    }

    public void assignCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));

        student.getCourses().add(course);
        studentRepository.save(student);
    }

    public List<Student> getStudentsByName(String name) {
        List<Student> students = studentRepository.findByName(name);
        return students.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));

        return course.getStudents().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Student convertToDTO(Student student) {
        Student studentDTO = new Student();
        BeanUtils.copyProperties(student, studentDTO);
        return studentDTO;
    }

}
