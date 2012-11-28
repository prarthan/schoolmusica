package com.khatu.musicschool.service;

import org.expressme.openid.Authentication;
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
	public int authenticateAndGetSchool(final Authentication authentication){
		
		SchoolAdmin admin = adminDao.get(authentication.getEmail());
		
		if(admin == null){
			// add new user
			admin = new SchoolAdmin(authentication);
			adminDao.updateSchoolAdmin(admin);
			
			return -1;
		}
		else{
			MusicSchool school = musicSchoolService.getMusicSchoolByUser(authentication.getEmail());
			
			if(school==null)
				return -1;
			else
				return school.getMusicSchoolId();
		}
		
		
	}
	
	/**
	 * verify if school can be edited by user
	 * @param email
	 * @return
	 */
	
	public boolean canEdit(final String email, int musicSchoolId){
		SchoolAdmin admin = adminDao.get(email);
		if(!admin.isAdmin()){
			MusicSchool school = musicSchoolService.getMusicSchool(musicSchoolId);
			if(school!=null && school.getAdmin().equals(email))
				return true;
			else
				return false;
		}
		
		
		return true;
		
	}
	
	
	
	
}
