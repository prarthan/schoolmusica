package com.khatu.musicschool.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
public class SchoolAdmin {
	private int schoolAdmingId;
	private String email;
	
	
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

}
