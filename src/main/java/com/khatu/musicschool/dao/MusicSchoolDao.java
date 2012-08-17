package com.khatu.musicschool.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.khatu.musicschool.model.Department;
import com.khatu.musicschool.model.MusicSchool;
import com.khatu.musicschool.wsresource.SchoolSearchCriteria;
import com.khatu.musicschool.wsresource.response.MusicSchoolResponse;

@Repository
public class MusicSchoolDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
		
	public MusicSchool getMusicSchool(int musicSchoolId){
		
		MusicSchool musicSchool = (MusicSchool)hibernateTemplate.get(MusicSchool.class, musicSchoolId);
		return musicSchool;
	}
	
	public void deleteMusicSchool(final int musicSchoolId){
		MusicSchool school = getMusicSchool(musicSchoolId);
		if(school !=null)
			school.setDepartment(null);
			hibernateTemplate.delete(school);
	}
	
	
	/**
	 * add new musicSchool
	 * @param musicSchool
	 * @return
	 */
	public MusicSchool addMusicSchool(MusicSchool musicSchool){
		return (MusicSchool)hibernateTemplate.merge(musicSchool);
	}
	
	public List<MusicSchoolResponse> searchMusicSchool(SchoolSearchCriteria schoolSearchCriteria){
		
		List<Department> departments = new ArrayList<Department>();
		
		List<MusicSchool> musicSchool = new ArrayList<MusicSchool>();
		
		Conjunction conjuctionCriteria = Restrictions.conjunction();
		
		DetachedCriteria query = DetachedCriteria.forClass(MusicSchool.class,"MusicSchool").createAlias("department","dept", DetachedCriteria.INNER_JOIN);
	
		
		if(schoolSearchCriteria.getInstrument()!=null && !schoolSearchCriteria.getInstrument().isEmpty())
			conjuctionCriteria.add(Restrictions.like("dept.keyword", "%" + schoolSearchCriteria.getInstrument() +"%"));
		
		
		if(schoolSearchCriteria.getState()!=null){
			conjuctionCriteria.add(Restrictions.eq("state", schoolSearchCriteria.getState()));
		}
		
		if(schoolSearchCriteria.getSatMin()>0){
			conjuctionCriteria.add(Restrictions.ge("satMin", schoolSearchCriteria.getSatMin()));

		}
		
		if(schoolSearchCriteria.getGreMin()>0){
			conjuctionCriteria.add(Restrictions.ge("greMin", schoolSearchCriteria.getGreMin()));
		}
		
		if(schoolSearchCriteria.getActMin()>0){
			conjuctionCriteria.add(Restrictions.ge("actMin", schoolSearchCriteria.getActMin()));
		}
		
		if(schoolSearchCriteria.getGraduateProgramAvailable()!=null){
			conjuctionCriteria.add(Restrictions.eq("dept.graduateProgramAvailable", (schoolSearchCriteria.getGraduateProgramAvailable().equalsIgnoreCase("true"))?true:false));
		}
		
		if(schoolSearchCriteria.getMusicMinorAvailable()!=null){
			conjuctionCriteria.add(Restrictions.eq("dept.musicMinorAvailable", (schoolSearchCriteria.getMusicMinorAvailable().equalsIgnoreCase("true"))?true:false));
		}
		
		if(schoolSearchCriteria.getScholarshipsAvailable()!=null){
			conjuctionCriteria.add(Restrictions.eq("dept.scholarshipsAvailable", (schoolSearchCriteria.getScholarshipsAvailable().equalsIgnoreCase("true"))?true:false));
		}
		
		if(schoolSearchCriteria.getStyle()!=null){
			conjuctionCriteria.add(Restrictions.eq("dept.faculty.styles", schoolSearchCriteria.getStyle()));
		}
		
		if(schoolSearchCriteria.getMethod() != null){
			conjuctionCriteria.add(Restrictions.eq("dept.faculty.methods", schoolSearchCriteria.getMethod()));
		}
		
		
		if(schoolSearchCriteria.getInstrument()!=null && !schoolSearchCriteria.getInstrument().isEmpty()){
			query.add(conjuctionCriteria);
			musicSchool = this.hibernateTemplate.findByCriteria(query);
		}
		
		List<MusicSchoolResponse> schools = new ArrayList<MusicSchoolResponse>();
		
		MusicSchoolResponse schoolResponse = null;
		
		for(MusicSchool currentSchool: musicSchool){
			if(currentSchool!=null){
				schoolResponse = new MusicSchoolResponse();
				schoolResponse.setMusicSchoolId(currentSchool.getMusicSchoolId());
				schoolResponse.setName(currentSchool.getName());
				schoolResponse.setDepartment(currentSchool.getDepartment());
				schools.add(schoolResponse);
			}
		}
		
		
		return schools;
	}
	

}
