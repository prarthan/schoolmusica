package com.khatu.musicschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.khatu.musicschool.dao.FacultyDao;
import com.khatu.musicschool.model.Faculty;

@Service
public class FacultyService {
	
	@Autowired
	private FacultyDao facultyDao;
	
//	
//	public Faculty getFaculty(int facultyId){
//		return new Faculty();
////		return facultyDao.getFaculty(facultyId);
//	}
//	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Faculty addFaculty(Faculty faculty){
		 return facultyDao.addFaculty(faculty);
	}
	
	
}
