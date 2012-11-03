package com.khatu.musicschool.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@XmlRootElement
@Entity
public class MusicSchool {
	private int musicSchoolId;
	private String name;
	private int satMin=0;
	private int actMin=0;
	private int greMin=0;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String country;
	private List<Department> department;
	private int sponsorWeight;
	private int verified;
	private String admin;
	
	
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

	@NotEmpty(message="You must provide school name.")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(length=5,columnDefinition="int default 0")
	@Index(name = "deptsatminindex")
	public int getSatMin() {
		return satMin;
	}
	public void setSatMin(int satMin) {
		this.satMin = satMin;
	}

	@Column(length=5,columnDefinition="int default 0")
	@Index(name = "deptactminindex")
	public int getActMin() {
		return actMin;
	}
	public void setActMin(int actMin) {
		this.actMin = actMin;
	}

	@Column(length=5,columnDefinition="int default 0")
	@Index(name = "deptgreminindex")
	public int getGreMin() {
		return greMin;
	}
	public void setGreMin(int greMin) {
		this.greMin = greMin;
	}

	@NotEmpty(message="You must provide department address.")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@NotEmpty(message="You must provide city.")
	@Column(length=20)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@NotEmpty(message="You must provide state.")
	@Column(length=20)
	@Index(name = "deptstateindex")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@NotEmpty(message="You must provide zip.")
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@NotEmpty(message="you must provide country.")
	@Column(length=30)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="musicSchoolId")
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

	@Column(columnDefinition="int default 0")
	public int getVerified() {
		return verified;
	}

	public void setVerified(int verified) {
		this.verified = verified;
	}

	
	
	@Column(length=30)
	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
		
}
