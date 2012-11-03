package com.khatu.musicschool.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.khatu.musicschool.model.SchoolAdmin;


@Repository
public class SchoolAdminDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	/**
	 * update user.
	 * @param admin
	 * @return
	 */
	public SchoolAdmin updateSchoolAdmin(SchoolAdmin admin){
		
		return (SchoolAdmin)hibernateTemplate.merge(admin);
		
	}
	
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	public SchoolAdmin get(final String email){
		Object [] params = new Object [] {email};
		List<SchoolAdmin> admin = (List<SchoolAdmin>)hibernateTemplate.find("FROM SchoolAdmin admin WHERE admin.email=?",params);
		
		if(admin!=null && admin.size()==1)
			return admin.get(0);
		else 
			return null;
	}

}
