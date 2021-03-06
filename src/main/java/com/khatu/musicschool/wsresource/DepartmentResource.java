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
import com.khatu.musicschool.model.MusicSchool;
import com.khatu.musicschool.service.DepartmentService;
import com.khatu.musicschool.service.MusicSchoolService;

@Component
@Path("/department")
public class DepartmentResource {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MusicSchoolService musicSchoolService;
	
	@Autowired
	private DepartmentService departmentService;

	
	@GET
	@Path("/delete/{departmentId}")
	@Produces({MediaType.APPLICATION_JSON })
	public Response deleteDepartment(@PathParam("departmentId") int departmentId){
		try{
			departmentService.deleteDepartment(departmentId);
			return Response.ok().build();
		}catch(Exception e){
			logger.error("Can not delete department {}",departmentId,e.getMessage());
			throw new InvalidOperationException(e.getMessage());
		}
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON })
	@Consumes({MediaType.APPLICATION_JSON })
	public Department addDepartment(Department department){
		try{
			MusicSchool school = musicSchoolService.getMusicSchool(department.getMusicSchoolId());
			department.setMusicSchoolValues(school);
		}catch(Exception e){
			logger.error("Can not add department {}",department.toString(),e.getMessage());
			throw new WebApplicationException(400);
		}
		validateDepartment(department);
		try{
			return departmentService.addDepartment(department);
		}catch(Exception e){
			logger.error("Can not add department {}",department.toString(),e.getMessage());
			throw new InvalidOperationException(e.getMessage());
		}
	}
	
	private void validateDepartment(Department department){
		 ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	     Validator  validator = factory.getValidator();
		 Set<ConstraintViolation<Department>> constraintViolations =
		            validator.validate(department);

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
