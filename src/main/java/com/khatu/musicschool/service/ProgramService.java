package com.khatu.musicschool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khatu.musicschool.dao.ProgramDao;
import com.khatu.musicschool.model.Program;
import com.khatu.musicschool.model.Style;


@Service
@Transactional(readOnly = true)
public class ProgramService {
	
	@Autowired
	private ProgramDao programDao;
	
	
	public List<Program> getAllProgram(){
		return programDao.getAllProgram();
	}
	
	public List<Style> getAllStylesById(List<Integer> instrumentIds){
		return programDao.getAllStylesById(instrumentIds);
	}
}
