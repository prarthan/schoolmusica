package com.khatu.musicschool.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.expressme.openid.Authentication;


@XmlRootElement
@Entity
public class SchoolAdmin {
	private int schoolAdmingId;
	private String email;
	private String firstName;
	private String lastname;
	private String language;
	private String gender;
	private boolean admin=false;
	
	
	
	public SchoolAdmin() {

	}
	
	public SchoolAdmin(Authentication authentication){
		email = authentication.getEmail();
		firstName = authentication.getFirstname();
		lastname = authentication.getLastname();
		language = authentication.getLanguage();
		gender = authentication.getGender();
	}

	@Id
	@GeneratedValue
	public int getSchoolAdmingId() {
		return schoolAdmingId;
	}
	
	public void setSchoolAdmingId(int schoolAdmingId) {
		this.schoolAdmingId = schoolAdmingId;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

    

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
