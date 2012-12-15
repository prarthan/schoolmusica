package com.khatu.musicschool.wsresource;

import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.expressme.openid.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.khatu.musicschool.authentication.OpenIdServiceManager;
import com.khatu.musicschool.exception.InvalidOperationException;
import com.khatu.musicschool.exception.InvalidParameterException;
import com.khatu.musicschool.model.MusicSchool;
import com.khatu.musicschool.service.DepartmentService;
import com.khatu.musicschool.service.MusicSchoolService;
import com.khatu.musicschool.wsresource.response.MusicSearchResponse;



@Component
@Path("/school")
public class SchoolResource {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MusicSchoolService musicSchoolService;
	
	@Autowired
	private DepartmentService departmentService;
	

	@Autowired
	private OpenIdServiceManager openIdServicemanager;
	
    private static final String ATTR_MAC = "openid_mac";
    private static final String ATTR_ALIAS = "openid_alias";
	
	
	@GET
	@Path("/{musicSchoolId}")
	@Produces({MediaType.APPLICATION_JSON })
	public MusicSchool getMusicSchool(@PathParam("musicSchoolId")int musicSchoolId){
		try{
			return 	musicSchoolService.getMusicSchool(musicSchoolId);
		}catch(Exception e){
			logger.error("Can not get music school {}",musicSchoolId,e.getMessage());
			throw new InvalidOperationException(e.getMessage());
		}
	}
	
	@DELETE
	@Path("/{musicSchoolId}")
	@Produces({MediaType.APPLICATION_JSON })
	public Response deleteMusicSchool(@PathParam("musicSchoolId")int musicSchoolId){
		try{
			musicSchoolService.deleteMusicSchool(musicSchoolId);
			return Response.ok().build();
		}catch(Exception e){
			logger.error("Can not delete music school {}",musicSchoolId,e.getMessage());
			throw new InvalidOperationException(e.getMessage());
		}
	}
	
	@POST
	@Path("/search")
	@Consumes({MediaType.APPLICATION_JSON })
	@Produces({MediaType.APPLICATION_JSON })
	public MusicSearchResponse searchMusicSchool(DepartmentSearchCriteria departmentSearchCriteria){
		try{
			return departmentService.searchSchool(departmentSearchCriteria);
		}catch(Exception e){
			logger.error("Can not search music school {}",departmentSearchCriteria.toString(),e.getMessage());
			throw new InvalidOperationException(e.getMessage());
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MusicSchool addMusicSchool(MusicSchool musicSchool,@Context HttpServletRequest request){
		validateSchool(musicSchool);
		try{
			String email = getEmail(request);
			musicSchool.setAdmin(email);
			return musicSchoolService.addMusicSchool(musicSchool);
		}catch(Exception e){
			logger.error("Can not add music school {}",musicSchool.toString(),e.getMessage());
			throw new InvalidOperationException(e.getMessage());
		}
	}
	
	private String getEmail(HttpServletRequest request){
		HttpSession session = request.getSession();       
	    String email = (String) session.getAttribute("email");
	    return email;
	    
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
