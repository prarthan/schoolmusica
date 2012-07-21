package com.khatu.musicschool.wsresource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.khatu.musicschool.model.Program;
import com.khatu.musicschool.service.ProgramService;

@Component
@Path("/program")
public class ProgramResource {
	
	
	@Autowired
	private ProgramService programService;
	
	
	@GET
	@Path("/all")
	@Produces({MediaType.APPLICATION_JSON })
	public List<Program> getAllProgram(){
		return programService.getAllProgram();
	}

}
