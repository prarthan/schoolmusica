package com.khatu.musicschool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.khatu.musicschool.dao.DepartmentDao;
import com.khatu.musicschool.model.Department;
import com.khatu.musicschool.wsresource.DepartmentSearchCriteria;

@Service
@Transactional(readOnly = true)
public class DepartmentService {
	
	@Autowired
	private DepartmentDao departmentDao;

		@Transactional(readOnly = true,propagation = Propagation.REQUIRES_NEW)
	   public List<Department> searchDepartment(DepartmentSearchCriteria searchCriteria){
		   
		  return  departmentDao.searchDepartment(searchCriteria);
	   }
		
		@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
		public Department addDepartment(Department department){
			return departmentDao.addDepartment(department);
		}
	
	
	
}
