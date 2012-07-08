package com.khatu.musicschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khatu.musicschool.dao.MusicSchoolDao;
import com.khatu.musicschool.model.MusicSchool;

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
	
	public MusicSchool addMusicSchool(MusicSchool musicSchool){
		return musicSchoolDao.addMusicSchool(musicSchool);
	}
	
	
	

}
