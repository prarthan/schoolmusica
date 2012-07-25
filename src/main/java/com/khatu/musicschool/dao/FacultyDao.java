package com.khatu.musicschool.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.khatu.musicschool.model.Faculty;


@Repository
public class FacultyDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public Faculty getFaculty(int facultyId){
		  return (Faculty) hibernateTemplate.get(Faculty.class, facultyId);
	}
	
	
	public Faculty addFaculty(Faculty faculty){
		return (Faculty) hibernateTemplate.merge(faculty);
	}

}
