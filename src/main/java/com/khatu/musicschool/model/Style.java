package com.khatu.musicschool.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Style {
	private int styleId;
	private String name;
	
	@Id
	@GeneratedValue
	public int getStyleId() {
		return styleId;
	}
	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}
	
	@Column(length=30)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
