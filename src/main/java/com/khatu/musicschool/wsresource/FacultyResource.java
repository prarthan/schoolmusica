package com.khatu.musicschool.wsresource;

import java.util.Set;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.khatu.musicschool.exception.InvalidOperationException;
import com.khatu.musicschool.exception.InvalidParameterException;
import com.khatu.musicschool.model.Department;
import com.khatu.musicschool.model.Faculty;
import com.khatu.musicschool.service.FacultyService;


@Component
@Path("/faculty")
public class FacultyResource {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
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
	public Faculty addFaculty(Faculty faculty){
		validateFaculty(faculty);
		try{
			return facultyService.addFaculty(faculty);
		}catch(Exception e){
			logger.error("Can not add faculty {}",faculty.toString(),e.getMessage());
			throw new InvalidOperationException(e.getMessage());
		}
	}
	
	@DELETE
	@Path("/{facultyId}")
	@Produces({MediaType.APPLICATION_JSON })
	public Response deleteFaculty(@PathParam("facultyId") int facultyId){
		try{
			facultyService.deleteFaculty(facultyId);
			return Response.ok().build();
		}catch(Exception e){
			logger.error("Can not delete faculty {}",facultyId,e.getMessage());
			throw new InvalidOperationException(e.getMessage());
		}
	}
	
	private void validateFaculty(Faculty faculty){
		 ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	     Validator  validator = factory.getValidator();
		 Set<ConstraintViolation<Faculty>> constraintViolations =
		            validator.validate(faculty);

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
