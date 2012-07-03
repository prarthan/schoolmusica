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
public class Openings {

	private int openeingId;
	private String title;
	private String description;
	private int numberOfOpeneings;
	private Department department;
	private Style style;
	private Method method;
	
	@Id
	@GeneratedValue
	public int getOpeneingId() {
		return openeingId;
	}
	public void setOpeneingId(int openeingId) {
		this.openeingId = openeingId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNumberOfOpeneings() {
		return numberOfOpeneings;
	}
	public void setNumberOfOpeneings(int numberOfOpeneings) {
		this.numberOfOpeneings = numberOfOpeneings;
	}
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Style getStyle() {
		return style;
	}
	public void setStyle(Style style) {
		this.style = style;
	}
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}	
	
}
