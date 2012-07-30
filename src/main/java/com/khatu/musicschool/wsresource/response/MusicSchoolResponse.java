package com.khatu.musicschool.wsresource.response;

import java.util.ArrayList;
import java.util.List;

import com.khatu.musicschool.model.Department;

public class MusicSchoolResponse {
	
	private int musicSchoolId;
	private String name;
	private List<Department> department;
	private int sponsorWeight;
	
	public MusicSchoolResponse() {

	}

	public MusicSchoolResponse(int musicSchoolId, String name,
			List<Department> department, int sponsorWeight) {
		super();
		this.musicSchoolId = musicSchoolId;
		this.name = name;
		this.department = department;
		this.sponsorWeight = sponsorWeight;
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
