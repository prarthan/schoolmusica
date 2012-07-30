package com.khatu.musicschool.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.khatu.musicschool.common.Filters;
import com.khatu.musicschool.dao.MusicSchoolDao;
import com.khatu.musicschool.model.MusicSchool;
import com.khatu.musicschool.wsresource.SchoolSearchCriteria;
import com.khatu.musicschool.wsresource.response.MusicSchoolResponse;
import com.khatu.musicschool.wsresource.response.MusicSearchResponse;

@Service
@Transactional(readOnly = true)
public class MusicSchoolService {
	
	@Autowired
	private MusicSchoolDao musicSchoolDao;
	
	
	/**
	 * get music school
	 * @param musicSchoolId
	 * @return
	 */
	public MusicSchool getMusicSchool(int musicSchoolId){
		return musicSchoolDao.getMusicSchool(musicSchoolId);		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public MusicSchool addMusicSchool(MusicSchool musicSchool){
		return musicSchoolDao.addMusicSchool(musicSchool);
	}
	
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public MusicSearchResponse searchMusicSchool(SchoolSearchCriteria schoolSearchCriteria){
		List<MusicSchoolResponse> schools = musicSchoolDao.searchMusicSchool(schoolSearchCriteria);
		MusicSearchResponse response = new MusicSearchResponse();
		response.setSchools(schools);
		response.setFilters(getFilters());
		return response;
	}
	
	
	public Map<String,List<String>> getFilters(){
		Map<String,List<String>> filters = new HashMap<String,List<String>>();
		filters.put(Filters.METHODS, new ArrayList<String>());
		filters.put(Filters.STYLES, new ArrayList<String>());
		return filters; 
	}
	

}
