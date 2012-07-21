package com.khatu.musicschool.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;


@Entity
public class Faculty {
	
	private int facultyId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String title;
    private String facultyUrl;
  
    
    @Id
	@GeneratedValue
	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
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
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
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

	
	public String getFacultyUrl() {
		return facultyUrl;
	}
	public void setFacultyUrl(String facultyUrl) {
		this.facultyUrl = facultyUrl;
	}
	
	
    

}
