package com.khatu.musicschool.wsresource;

import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.khatu.musicschool.exception.InvalidParameterException;
import com.khatu.musicschool.model.MusicSchool;
import com.khatu.musicschool.service.MusicSchoolService;
import com.khatu.musicschool.wsresource.response.MusicSearchResponse;



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
		return 	musicSchoolService.getMusicSchool(musicSchoolId);
	}
	
	@DELETE
	@Path("/{musicSchoolId}")
	@Produces({MediaType.APPLICATION_JSON })
	public Response deleteMusicSchool(@PathParam("musicSchoolId")int musicSchoolId){
		musicSchoolService.deleteMusicSchool(musicSchoolId);
		return Response.ok().build();
	}
	
	@POST
	@Path("/search")
	@Consumes({MediaType.APPLICATION_JSON })
	@Produces({MediaType.APPLICATION_JSON })
	public MusicSearchResponse searchMusicSchool(SchoolSearchCriteria schoolSearchCriteria){

		return musicSchoolService.searchMusicSchool(schoolSearchCriteria);

	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MusicSchool addMusicSchool(MusicSchool musicSchool,@Context HttpServletRequest request){
		validateSchool(musicSchool);
		String email = getMail(request.getCookies());
		musicSchool.setAdmin(email);
		return musicSchoolService.addMusicSchool(musicSchool);
	}
	
	
	private String getMail(Cookie [] cookies){
		if(cookies !=null){
			for(Cookie currentCookie: cookies){
				if(currentCookie.getName().equalsIgnoreCase("schoolmusicausermail")){
					return currentCookie.getValue();
				}
			}
			
		}
		return null;
	}
	
	private void validateSchool(MusicSchool musicSchool){
		 ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	     Validator  validator = factory.getValidator();
		 Set<ConstraintViolation<MusicSchool>> constraintViolations =
		            validator.validate(musicSchool);

		 StringBuffer errorMessage = new StringBuffer();
		 if(constraintViolations.size() !=0){
			for(ConstraintViolation violation: constraintViolations){
				errorMessage.append(violation.getMessage());
				errorMessage.append("<br>");
			}
			
			if(errorMessage.length()!=0){
				throw new InvalidParameterException(errorMessage.toString());
			}
		 }
		
	}

}
