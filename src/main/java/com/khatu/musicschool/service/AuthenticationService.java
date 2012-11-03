package com.khatu.musicschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.khatu.musicschool.dao.SchoolAdminDao;
import com.khatu.musicschool.model.MusicSchool;
import com.khatu.musicschool.model.SchoolAdmin;

@Service
@Transactional(readOnly = true)
public class AuthenticationService {

	@Autowired
	private SchoolAdminDao adminDao;
	
	@Autowired
	private MusicSchoolService musicSchoolService;
	
	
	/**
	 * authenticate and create empty place holder for user
	 * @param email
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int authenticateAndGetSchool(final String email){
		
		SchoolAdmin admin = adminDao.get(email);
		
		if(admin == null){
			// add new user
			admin = new SchoolAdmin();
			admin.setEmail(email);
			adminDao.updateSchoolAdmin(admin);
			
			return -1;
		}
		else{
			MusicSchool school = musicSchoolService.getMusicSchoolByUser(email);
			
			if(school==null)
				return -1;
			else
				return school.getMusicSchoolId();
		}
		
		
	}
	
	
	
	
}
