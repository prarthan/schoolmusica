package com.khatu.musicschool.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.khatu.musicschool.model.Department;
import com.khatu.musicschool.model.MusicSchool;
import com.khatu.musicschool.wsresource.SchoolSearchCriteria;

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
	
	public List<MusicSchool> searchMusicSchool(SchoolSearchCriteria schoolSearchCriteria){
		
		DetachedCriteria query = DetachedCriteria.forClass(MusicSchool.class,"school")
				.createAlias("department", "dept")
				.add(Restrictions.like("dept.keyword", "%" + schoolSearchCriteria.getInstrument() +"%"));

		List<MusicSchool> schools = this.hibernateTemplate.findByCriteria(query);
		
		return schools;
	}
	

}
