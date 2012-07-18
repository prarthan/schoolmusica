package com.khatu.musicschool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.khatu.musicschool.dao.MusicSchoolDao;
import com.khatu.musicschool.model.MusicSchool;
import com.khatu.musicschool.wsresource.SchoolSearchCriteria;

@Service
@Transactional(readOnly = true)
public class MusicSchoolService {
	
	@Autowired
	private MusicSchoolDao musicSchoolDao;
	
	
	/**
	 * get music school
	 * @param musicSchoolId
	 * @return
	 */
	public MusicSchool getMusicSchool(int musicSchoolId){
		return musicSchoolDao.getMusicSchool(musicSchoolId);		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public MusicSchool addMusicSchool(MusicSchool musicSchool){
		return musicSchoolDao.addMusicSchool(musicSchool);
	}
	
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public List<MusicSchool> searchMusicSchool(SchoolSearchCriteria schoolSearchCriteria){
		return musicSchoolDao.searchMusicSchool(schoolSearchCriteria);
	}
	
	
	

}
