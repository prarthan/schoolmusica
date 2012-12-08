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
import com.khatu.musicschool.dao.DepartmentDao;
import com.khatu.musicschool.dao.MusicSchoolDao;
import com.khatu.musicschool.dao.SchoolAdminDao;
import com.khatu.musicschool.model.Department;
import com.khatu.musicschool.model.MusicSchool;
import com.khatu.musicschool.wsresource.SchoolSearchCriteria;
import com.khatu.musicschool.wsresource.response.MusicSchoolResponse;
import com.khatu.musicschool.wsresource.response.MusicSearchResponse;

@Service
@Transactional(readOnly = true)
public class MusicSchoolService {
	
	@Autowired
	private MusicSchoolDao musicSchoolDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	
	/**
	 * get music school
	 * @param musicSchoolId
	 * @return
	 */
	public MusicSchool getMusicSchool(int musicSchoolId){
		return musicSchoolDao.getMusicSchool(musicSchoolId);		
	}
	
	public MusicSchool getMusicSchoolByUser(final String email){
		return musicSchoolDao.getMusicSchoolByUser(email);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public MusicSchool addMusicSchool(MusicSchool musicSchool){
		List<Department> departments = departmentDao.getDepartmentBySchool(musicSchool.getMusicSchoolId());
		updateSchoolDetails(musicSchool,departments);
		return musicSchoolDao.addMusicSchool(musicSchool);
	}
	
	/**
	 * update music school values for the departments.
	 * @param musicSchool
	 * @param departments
	 */
	
	private void updateSchoolDetails(MusicSchool musicSchool, List<Department> departments){
		if(departments!=null){
			for(Department currentDepartment: departments){
				currentDepartment.setMusicSchoolValues(musicSchool);
			}
		}
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void deleteMusicSchool(final int musicSchoolId){
		musicSchoolDao.deleteMusicSchool(musicSchoolId);
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
