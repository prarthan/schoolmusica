package com.khatu.musicschool.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Department {
	private int deparmentId;
	private MusicSchool musicSchool;
	private String departmentName;
	private Address address;
	private int satMin;
	private int satMax;
	private int actMin;
	private int actMax;
	private int greMin;
	private int greMax;
	private boolean musicMinorAvailable;
	
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
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="addressid")
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getSatMin() {
		return satMin;
	}
	public void setSatMin(int satMin) {
		this.satMin = satMin;
	}
	public int getSatMax() {
		return satMax;
	}
	public void setSatMax(int satMax) {
		this.satMax = satMax;
	}
	public int getActMin() {
		return actMin;
	}
	public void setActMin(int actMin) {
		this.actMin = actMin;
	}
	public int getActMax() {
		return actMax;
	}
	public void setActMax(int actMax) {
		this.actMax = actMax;
	}
	public int getGreMin() {
		return greMin;
	}
	public void setGreMin(int greMin) {
		this.greMin = greMin;
	}
	public int getGreMax() {
		return greMax;
	}
	public void setGreMax(int greMax) {
		this.greMax = greMax;
	}
	public boolean isMusicMinorAvailable() {
		return musicMinorAvailable;
	}
	public void setMusicMinorAvailable(boolean musicMinorAvailable) {
		this.musicMinorAvailable = musicMinorAvailable;
	}
	
	
	
	

}
