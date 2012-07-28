package com.khatu.musicschool.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Faculty {
	
	private int facultyId;
	private String firstName;
	private String middleName;
	private String lastName;
	private int deparmentId;
	private String title;
	private String keyword;
	private String studiedWith;
	private String styles;
	private String methods;
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
	
	@Column(nullable = false)
	public int getDeparmentId() {
		return deparmentId;
	}
	public void setDeparmentId(int deparmentId) {
		this.deparmentId = deparmentId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getStudiedWith() {
		return studiedWith;
	}
	public void setStudiedWith(String studiedWith) {
		this.studiedWith = studiedWith;
	}
	public String getStyles() {
		return styles;
	}
	public void setStyles(String styles) {
		this.styles = styles;
	}
	public String getMethods() {
		return methods;
	}
	public void setMethods(String methods) {
		this.methods = methods;
	}
	public String getFacultyUrl() {
		return facultyUrl;
	}
	public void setFacultyUrl(String facultyUrl) {
		this.facultyUrl = facultyUrl;
	}

}
