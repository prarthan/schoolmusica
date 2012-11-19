package com.khatu.musicschool.wsresource.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.khatu.musicschool.model.Department;
import com.khatu.musicschool.model.MusicSchool;

public class MusicSchoolResponse {
	
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
	
	public MusicSchoolResponse() {

	}

	
	@SuppressWarnings("unused")
	public MusicSchoolResponse(int musicSchoolId, String name, int satMin,
			int actMin, int greMin, String address, String city, String state,
			String zip, String country, List<Department> department,
			int sponsorWeight) {
		super();
		this.musicSchoolId = musicSchoolId;
		this.name = name;
		this.satMin = satMin;
		this.actMin = actMin;
		this.greMin = greMin;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
		this.department = department;
		this.sponsorWeight = sponsorWeight;
	}

	
	public MusicSchoolResponse(MusicSchool musicSchool){
		this.musicSchoolId = musicSchool.getMusicSchoolId();
		this.name = musicSchool.getName();
		this.satMin = musicSchool.getSatMin();
		this.actMin = musicSchool.getActMin();
		this.greMin = musicSchool.getGreMin();
		this.address = musicSchool.getAddress();
		this.city = musicSchool.getCity();
		this.state = musicSchool.getState();
		this.zip = musicSchool.getZip();
		this.country = musicSchool.getCountry();
		this.department = musicSchool.getDepartment();
		this.sponsorWeight = musicSchool.getSponsorWeight();
		
	}
	
	public MusicSchoolResponse(Department department){
		this.musicSchoolId = department.getMusicSchoolId();
		this.name = department.getName();
		this.satMin = department.getSatMin();
		this.actMin = department.getActMin();
		this.greMin = department.getGreMin();
		this.address = department.getAddress();
		this.city = department.getCity();
		this.state = department.getState();
		this.zip = department.getZip();
		this.country = department.getCountry();
		this.department = Arrays.asList(department);
		this.sponsorWeight = department.getSponsorWeight();
		
	}

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


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public List<Department> getDepartment() {
		return department;
	}
	
	public void addDepartment(Department department){
		if(this.department==null)
			this.department = new ArrayList<Department>();
		this.department.add(department);
		
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
