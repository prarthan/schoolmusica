package com.khatu.musicschool.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Faculty {
	
	private int facultyId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String title;
    private Department department;
    private String facultyUrl;
  
    
    @Id
	@GeneratedValue
	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="departmentId",nullable=false,updatable=false,insertable=false)
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public String getFacultyUrl() {
		return facultyUrl;
	}
	public void setFacultyUrl(String facultyUrl) {
		this.facultyUrl = facultyUrl;
	}
	
	
    

}
