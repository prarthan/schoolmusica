package com.khatu.musicschool.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SpecializationType {
	private int specalizationTypeId;
	private String name;
	
	@Id
	@GeneratedValue
	public int getSpecalizationTypeId() {
		return specalizationTypeId;
	}
	public void setSpecalizationTypeId(int specalizationTypeId) {
		this.specalizationTypeId = specalizationTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
