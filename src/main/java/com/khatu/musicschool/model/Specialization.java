package com.khatu.musicschool.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Specialization {
	
	private int specializationId;
	private Department department;
	private String name;
	private boolean graduateProgramAvailable;
	private boolean privateLessonAvailable;
	
	
	
	@Id
	@GeneratedValue
	public int getSpecializationId() {
		return specializationId;
	}
	public void setSpecializationId(int specializationId) {
		this.specializationId = specializationId;
	}
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isGraduateProgramAvailable() {
		return graduateProgramAvailable;
	}
	public void setGraduateProgramAvailable(boolean graduateProgramAvailable) {
		this.graduateProgramAvailable = graduateProgramAvailable;
	}
	public boolean isPrivateLessonAvailable() {
		return privateLessonAvailable;
	}
	public void setPrivateLessonAvailable(boolean privateLessonAvailable) {
		this.privateLessonAvailable = privateLessonAvailable;
	}

}
