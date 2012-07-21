package com.khatu.musicschool.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.khatu.musicschool.model.Program;


@Repository
public class ProgramDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	@SuppressWarnings("unchecked")
	public List<Program> getAllProgram(){
		return (List<Program>)hibernateTemplate.loadAll(Program.class);
	}
}
