package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.Course;
import com.student.entity.Student;
import com.student.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
    private StudentService studentService;

    @PutMapping("/updateProfile/{studentId}")
    public ResponseEntity<Long> updateProfile(@PathVariable Long studentId, @RequestBody Student studentDTO) {
        studentService.updateProfile(studentId, studentDTO);
        return new ResponseEntity<>(studentId, HttpStatus.OK);
    }

    @GetMapping("/getAssignedCourses/{studentId}")
    public ResponseEntity<List<Course>> getAssignedCourses(@PathVariable Long studentId) {
        List<Course> assignedCourses = studentService.getAssignedCourses(studentId);
        return new ResponseEntity<>(assignedCourses, HttpStatus.OK);
    }

    @DeleteMapping("/leaveCourse/{studentId}/{courseId}")
    public ResponseEntity<Long> leaveCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        studentService.leaveCourse(studentId, courseId);
        return new ResponseEntity<>(studentId, HttpStatus.OK);
    }
}
