package com.khatu.musicschool.wsresource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.khatu.musicschool.model.Faculty;
import com.khatu.musicschool.service.FacultyService;


@Component
@Path("/faculty")
public class FacultyResource {
	
	@Autowired
	private FacultyService facultyService;
//	
//	
//	@GET
//	@Path("/{facultyId}")
//	@Produces({MediaType.APPLICATION_JSON })
//	public Faculty getFaculty(@PathParam("facultyId")int facultyId){
//		return facultyService.getFaculty(facultyId);
//		
//	}
//	
//	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Faculty addFaculy(Faculty faculty){
		return facultyService.addFaculty(faculty);
	}
	
}
