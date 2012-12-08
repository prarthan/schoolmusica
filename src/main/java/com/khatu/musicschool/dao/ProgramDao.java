package com.khatu.musicschool.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.khatu.musicschool.model.Department;
import com.khatu.musicschool.model.Program;
import com.khatu.musicschool.model.Style;


@Repository
public class ProgramDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	@SuppressWarnings("unchecked")
	public List<Program> getAllProgram(){
		this.hibernateTemplate.setCacheQueries(true);
		this.hibernateTemplate.setQueryCacheRegion("allprogram");
		return (List<Program>)hibernateTemplate.loadAll(Program.class);
	}
	
	public List<Style> getAllStylesById(List<Integer> instrumentIds){
		DetachedCriteria query = DetachedCriteria.forClass(Style.class).add(Restrictions.in("programId", instrumentIds));

		this.hibernateTemplate.setCacheQueries(true);
		this.hibernateTemplate.setQueryCacheRegion("alltylesbyId"); 
		@SuppressWarnings("unchecked")
		List<Style> styles = (List<Style>)this.hibernateTemplate.findByCriteria(query);
		return styles;
	}
}
