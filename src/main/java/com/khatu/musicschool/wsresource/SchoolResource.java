package com.khatu.musicschool.wsresource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.khatu.musicschool.model.MusicSchool;
import com.khatu.musicschool.service.MusicSchoolService;



@Component
@Path("/school")
public class SchoolResource {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MusicSchoolService musicSchoolService;
	

	@GET
	@Path("/{musicSchoolId}")
	@Produces({MediaType.APPLICATION_JSON })
	public MusicSchool getMusicSchool(@PathParam("musicSchoolId")int musicSchoolId){
		
//		return 	musicSchoolService.getMusicSchool(musicSchoolId);
	    return null;
	}
	
	@POST
	@Path("/search")
	@Produces({MediaType.APPLICATION_JSON })
	public List<MusicSchool> searchMusicSchool(SchoolSearchCriteria schoolSearchCriteria){
		
		List<MusicSchool> schools = new ArrayList<MusicSchool>();
		schools = musicSchoolService.searchMusicSchool(schoolSearchCriteria);
		return schools;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MusicSchool addMusicSchool(MusicSchool musicSchool){
		return musicSchoolService.addMusicSchool(musicSchool);
	}

}
