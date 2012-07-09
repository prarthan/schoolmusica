package com.khatu.musicschool.wsresource.response;

import java.util.List;
import java.util.Map;

import com.khatu.musicschool.model.Department;

public class DepartmentResponse {
	
	private List<Department> department;
	private Map<String,List<String>> filters;

	public List<Department> getDepartment() {
		return department;
	}
	public void setDepartment(List<Department> department) {
		this.department = department;
	}
	public Map<String, List<String>> getFilters() {
		return filters;
	}
	public void setFilters(Map<String, List<String>> filters) {
		this.filters = filters;
	}

}
