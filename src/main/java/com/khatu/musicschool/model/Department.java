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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Department {
	private int deparmentId;
	private MusicSchool musicSchool;
	private String departmentName;
	private String keyword;
	private int satMin;
	private int actMin;
	private int greMin;
	private List<Specialization> specialization;
	private List<Faculty> faculty;
	private boolean musicMinorAvailable;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String departmentUrl;
	
	public Department(){
		
		
	}
	
	@Id
	@GeneratedValue
	public int getDeparmentId() {
		return deparmentId;
	}
	public void setDeparmentId(int deparmentId) {
		this.deparmentId = deparmentId;
	}
	
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="musicSchoolId")
	public MusicSchool getMusicSchool() {
		return musicSchool;
	}

	public void setMusicSchool(MusicSchool musicSchool) {
		this.musicSchool = musicSchool;
	}

	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public int getSatMin() {
		return satMin;
	}
	public void setSatMin(int satMin) {
		this.satMin = satMin;
	}

	public int getActMin() {
		return actMin;
	}
	public void setActMin(int actMin) {
		this.actMin = actMin;
	}

	public int getGreMin() {
		return greMin;
	}
	public void setGreMin(int greMin) {
		this.greMin = greMin;
	}

	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="specializationId")
	public List<Specialization> getSpecialization() {
		return specialization;
	}

	public void setSpecialization(List<Specialization> specialization) {
		this.specialization = specialization;
	}
	
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="facultyId")
	public List<Faculty> getFaculty() {
		return faculty;
	}

	public void setFaculty(List<Faculty> faculty) {
		this.faculty = faculty;
	}

	public boolean isMusicMinorAvailable() {
		return musicMinorAvailable;
	}
	public void setMusicMinorAvailable(boolean musicMinorAvailable) {
		this.musicMinorAvailable = musicMinorAvailable;
	}
	
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getDepartmentUrl() {
		return departmentUrl;
	}

	public void setDepartmentUrl(String departmentUrl) {
		this.departmentUrl = departmentUrl;
	}

}
