package com.khatu.musicschool.wsresource.response;

import java.util.List;
import java.util.Map;

import com.khatu.musicschool.model.MusicSchool;

public class MusicSearchResponse {
	
	private List<MusicSchool> schools;
	private Map<String,List<String>> filters;
	
	public List<MusicSchool> getSchools() {
		return schools;
	}
	
	public void setSchools(List<MusicSchool> schools) {
		this.schools = schools;
	}
	
	public Map<String, List<String>> getFilters() {
		return filters;
	}
	
	public void setFilters(Map<String, List<String>> filters) {
		this.filters = filters;
	}

}
