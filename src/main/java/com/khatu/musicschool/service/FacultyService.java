package com.khatu.musicschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khatu.musicschool.dao.FacultyDao;
import com.khatu.musicschool.model.Faculty;

@Service
public class FacultyService {
	
	@Autowired
	private FacultyDao facultyDao;
	
	
	public Faculty getFaculty(int facultyId){
		return facultyDao.getFaculty(facultyId);
	}
	
	public Faculty addFaculty(Faculty faculty){
		 return facultyDao.addFaculty(faculty);
	}
	
	
	
	
}
