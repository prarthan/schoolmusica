package com.khatu.musicschool.wsresource.response;

import java.util.List;
import java.util.Map;

import com.khatu.musicschool.model.MusicSchool;

public class MusicSearchResponse {
	
	private List<MusicSchoolResponse> schools;
	private Map<String,List<String>> filters;
	private int resultCount;
	
	public List<MusicSchoolResponse> getSchools() {
		return schools;
	}
	
	public void setSchools(List<MusicSchoolResponse> schools) {
		this.schools = schools;
	}
	
	public Map<String, List<String>> getFilters() {
		return filters;
	}
	
	public void setFilters(Map<String, List<String>> filters) {
		this.filters = filters;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

}
