package com.student.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	private String gender;

	@Column(name = "unique_student_code", unique = true)
	private String uniqueStudentCode;

	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StudentAddress> addresses = new ArrayList<>();

	@ManyToMany(mappedBy = "students")
	private List<Course> courses = new ArrayList<>();
	
	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student( String name, LocalDate dateOfBirth, String gender, String uniqueStudentCode,
			List<StudentAddress> addresses, List<Course> courses) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.uniqueStudentCode = uniqueStudentCode;
		this.addresses = addresses;
		this.courses = courses;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUniqueStudentCode() {
		return uniqueStudentCode;
	}

	public void setUniqueStudentCode(String uniqueStudentCode) {
		this.uniqueStudentCode = uniqueStudentCode;
	}

	public List<StudentAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<StudentAddress> addresses) {
		this.addresses = addresses;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
}
