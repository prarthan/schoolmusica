package com.khatu.musicschool.wsresource;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.khatu.musicschool.exception.InvalidParameterException;
import com.khatu.musicschool.model.Department;
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
