package com.khatu.musicschool.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
public class MusicSchool {
	private int musicSchoolId;
	private String name;
	private List<Department> department;
	private int sponsorWeight;
	
	
	public MusicSchool(){
		
	}

	@Id
	@GeneratedValue
	public int getMusicSchoolId() {
		return musicSchoolId;
	}


	public void setMusicSchoolId(int musicSchoolId) {
		this.musicSchoolId = musicSchoolId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="musicSchoolId",nullable=false)
	public List<Department> getDepartment() {
		return department;
	}

	public void setDepartment(List<Department> department) {
		this.department = department;
	}

	public int getSponsorWeight() {
		return sponsorWeight;
	}

	public void setSponsorWeight(int sponsorWeight) {
		this.sponsorWeight = sponsorWeight;
	}	
	
}
