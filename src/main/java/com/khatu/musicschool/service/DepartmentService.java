package com.khatu.musicschool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khatu.musicschool.dao.DepartmentDao;
import com.khatu.musicschool.model.Department;
import com.khatu.musicschool.wsresource.DepartmentSearchCriteria;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentDao departmentDao;

	   public List<Department> searchDepartment(DepartmentSearchCriteria searchCriteria){
		   
		  return  departmentDao.searchDepartment(searchCriteria);
	   }
	
	
	
}
