package com.khatu.musicschool.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.khatu.musicschool.model.Department;
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
	
	public List<Faculty> getFacultyByDepartment(final int departmetnId){
		String hql = "FROM Faculty F WHERE F.departmentId = " + departmetnId;
		List<Faculty> faculties = hibernateTemplate.find(hql);
		return faculties;
	}
	
	public void deleteFaculty(final int facultyId){
		Faculty faculty = getFaculty(facultyId);
		if(faculty!=null)
			hibernateTemplate.delete(faculty);
	}

}
