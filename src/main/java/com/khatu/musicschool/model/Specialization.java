package com.khatu.musicschool.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Specialization {
	
	private int specializationId;
	private Department department;
	private String name;
	private SpecializationType specialization;
	private Style styles;
	private Method method;
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
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
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
	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="specalizationTypeId")
	public SpecializationType getInstrument() {
		return specialization;
	}
	public void setInstrument(SpecializationType instrument) {
		this.specialization = instrument;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="styleId")
	public Style getStyles() {
		return styles;
	}
	public void setStyles(Style styles) {
		this.styles = styles;
	} 
	 
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="methodId")
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
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
