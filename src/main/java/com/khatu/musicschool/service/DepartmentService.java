package com.khatu.musicschool.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.khatu.musicschool.dao.DepartmentDao;
import com.khatu.musicschool.dao.MusicSchoolDao;
import com.khatu.musicschool.model.Department;
import com.khatu.musicschool.model.MusicSchool;
import com.khatu.musicschool.wsresource.DepartmentSearchCriteria;
import com.khatu.musicschool.wsresource.response.MusicSchoolResponse;
import com.khatu.musicschool.wsresource.response.MusicSearchResponse;

@Service
@Transactional(readOnly = true)
public class DepartmentService {
	
    @Autowired
	private DepartmentDao departmentDao;
    
    @Transactional(readOnly = true,propagation = Propagation.REQUIRES_NEW)
    public List<Department> searchDepartment(DepartmentSearchCriteria searchCriteria){
	   
	  return  departmentDao.searchDepartment(searchCriteria);
    }
    
    /**
     * search schools and generate response
     * @param searchCriteria
     * @return
     */
    public MusicSearchResponse searchSchool(DepartmentSearchCriteria searchCriteria){
    	List<Department> dept = departmentDao.searchDepartment(searchCriteria);
    	List<MusicSchoolResponse> responseMusic = new ArrayList<MusicSchoolResponse>();
    	// generate return response
    	for(Department currentDept: dept){
    		responseMusic.add(new MusicSchoolResponse(currentDept));
    	}
    	
    	MusicSearchResponse response = new MusicSearchResponse();
    	response.setSchools(responseMusic);
    	response.setResultCount(departmentDao.getResultCount(searchCriteria));
    	
    	return response;
    }
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Department addDepartment(Department department){
		return departmentDao.addDepartment(department);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void deleteDepartment(final int departmentId){
		departmentDao.deleteDepartment(departmentId); 
	}
}
