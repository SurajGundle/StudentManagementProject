package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.Course;
import com.student.entity.Student;
import com.student.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
    private AdminService adminService;

    @PostMapping("/admitStudent")
    public ResponseEntity<Student> admitStudent(@RequestBody Student studentDTO) {
        adminService.admitStudent(studentDTO);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @PostMapping("/uploadCourse")
    public ResponseEntity<Course> uploadCourse(@RequestBody Course courseDTO) {
        adminService.uploadCourse(courseDTO);
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    @PostMapping("/assignCourse/{studentId}/{courseId}")
    public ResponseEntity<Long> assignCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        adminService.assignCourse(studentId, courseId);
        return new ResponseEntity<>(courseId, HttpStatus.OK);
    }

    @GetMapping("/getStudentsByName/{name}")
    public ResponseEntity<List<Student>> getStudentsByName(@PathVariable String name) {
        List<Student> students = adminService.getStudentsByName(name);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/getStudentsByCourse/{courseId}")
    public ResponseEntity<List<Student>> getStudentsByCourse(@PathVariable Long courseId) {
        List<Student> students = adminService.getStudentsByCourse(courseId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
