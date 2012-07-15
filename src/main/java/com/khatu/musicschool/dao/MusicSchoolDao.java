package com.khatu.musicschool.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.khatu.musicschool.model.MusicSchool;

@Repository
public class MusicSchoolDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	public MusicSchool getMusicSchool(int musicSchoolId){
		
		MusicSchool musicSchool = (MusicSchool)hibernateTemplate.get(MusicSchool.class, musicSchoolId);
		return musicSchool;
	}
	
	
	/**
	 * add new musicSchool
	 * @param musicSchool
	 * @return
	 */
	public MusicSchool addMusicSchool(MusicSchool musicSchool){
		
		
		return (MusicSchool)hibernateTemplate.merge(musicSchool);
	}
	

}
