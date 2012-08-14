package com.khatu.musicschool.wsresource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.khatu.musicschool.common.Filters;
import com.khatu.musicschool.exception.InvalidParameterException;
import com.khatu.musicschool.model.Department;
import com.khatu.musicschool.model.Faculty;
import com.khatu.musicschool.model.Method;
import com.khatu.musicschool.model.MusicSchool;
import com.khatu.musicschool.model.Program;
import com.khatu.musicschool.model.Style;
import com.khatu.musicschool.service.DepartmentService;
import com.khatu.musicschool.wsresource.response.DepartmentResponse;

@Component
@Path("/department")
public class DepartmentResource {
	
	
	@Autowired
	private DepartmentService departmentService;
	
	
	@POST
	@Path("/search")
	@Produces({MediaType.APPLICATION_JSON })
	@Consumes({MediaType.APPLICATION_JSON })
	public DepartmentResponse searchDepartment(final DepartmentSearchCriteria searchCriteria){
		
		List<Department> departments = departmentService.searchDepartment(searchCriteria);
		DepartmentResponse response = getdummydata();
		response.setDepartment(departments);
		return response;
	}
	
	@DELETE
	@Path("/{departmentId}")
	@Produces({MediaType.APPLICATION_JSON })
	public Response deleteDepartment(@PathParam("departmentId") int departmentId){
		departmentService.deleteDepartment(departmentId);
		return Response.ok().build();
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON })
	@Consumes({MediaType.APPLICATION_JSON })
	public Department addDepartment(Department department){
		validateDepartment(department);
		return departmentService.addDepartment(department);
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
	
	
	private DepartmentResponse getdummydata(){
		//Style
		Style style = new Style();
		style.setStyleId(1);
		style.setName("classical");
		
		
		//method
		
		Method method = new Method();
		method.setMethodId(1);
		method.setName("French");
		
		
		Program program1 = new Program();
		program1.setProgramId(1);
		program1.setName("guitar");
		
		//list of department
		List<Department> departments = new ArrayList<Department>();
		Department dept1 = new Department();
		dept1.setDepartmentName("String, Harp & Guitar");
	    dept1.setDepartmentUrl("http://www.esm.rochester.edu/strings/");
	    dept1.setGreMin(800);
	    dept1.setAddress("26 Gibbs St.");
	    dept1.setCity("Rochester");
	    dept1.setState("NY");
	    dept1.setZip("14604");
	    

		//faculty
	    
	    List<Faculty> facultyList = new ArrayList<Faculty>();
	    
		Faculty faculty = new Faculty();
		faculty.setFacultyId(1);
		faculty.setFirstName("ALAN");
		faculty.setLastName("HARRIS");
		faculty.setTitle("Distinguished Professor of Violoncello Co-Chair, Strings, Harp, and Guitar Department");
	    faculty.setFacultyUrl("http://www.esm.rochester.edu/faculty/harris_alan/");
	    
	    Faculty faculty1 = new Faculty();
		faculty1.setFacultyId(1);
		faculty1.setFirstName("KATHLEEN");
		faculty1.setLastName("BRIDE");
		faculty1.setTitle("Professor of Harp");
	    faculty1.setFacultyUrl("http://www.esm.rochester.edu/faculty/bride_kathleen/");
	    
	    facultyList.add(faculty);
	    facultyList.add(faculty1);
	    

	    dept1.setFaculty(facultyList);
	    departments.add(dept1);
	    
	    
	    
		Department dept2 = new Department();
		dept2.setDepartmentName("Voice & Opera");
	    dept2.setDepartmentUrl("http://www.esm.rochester.edu/departments/voice/");
	    dept2.setGreMin(800);
	    dept2.setAddress("26 Gibbs St.");
	    dept2.setCity("Rochester");
	    dept2.setState("NY");
	    dept2.setZip("14604");

		//faculty
	    
	    List<Faculty> facultyList2 = new ArrayList<Faculty>();
	    
		Faculty faculty2 = new Faculty();
		faculty2.setFacultyId(1);
		faculty2.setFirstName("STEVEN");
		faculty2.setLastName("DAIGLE");
		faculty2.setTitle("Professor of Opera Chair, Voice and Opera Department Head of Eastman Opera Program Dramatic Director, Eastman Opera Theatre");
	    faculty2.setFacultyUrl("http://www.esm.rochester.edu/faculty/daigle_steven/");
	    
	    Faculty faculty21 = new Faculty();
		faculty21.setFacultyId(1);
		faculty21.setFirstName("Katherine");
		faculty21.setLastName("Ciesinski");
		faculty21.setTitle("Professor of Voice ECMS Collegiate Instructor in Voice");
	    faculty21.setFacultyUrl("http://www.esm.rochester.edu/faculty/ciesinski_katherine/");
	    
	    facultyList2.add(faculty2);
	    facultyList2.add(faculty21);
	    
	    dept2.setFaculty(facultyList2);
	    
	    departments.add(dept2);
	    
	    
	    Map<String,List<String>> filters = new HashMap<String,List<String>>();
	    
	    List<String> states = new ArrayList<String>();
	    states.add("CA");
	    states.add("NY");
	    
	    filters.put(Filters.STATES, states);
	    
	    List<String> methods = new ArrayList<String>();
	    methods.add("French");
	    methods.add("Italian");
	    
	    filters.put(Filters.METHODS, methods);
	    
	    
	    List<String> styles = new ArrayList<String>();
	    styles.add("classical");
	    
	    filters.put(Filters.STYLES, styles);
	    
	    
	    List<String> facultyFilter = new ArrayList<String>();
	    for(Faculty currentFaculty: facultyList){
	    	facultyFilter.add(currentFaculty.getFirstName());
	    }
	    
	    filters.put(Filters.FACULTY,facultyFilter);
	    
	   
	    DepartmentResponse response = new DepartmentResponse();
	    response.setDepartment(departments);
	    response.setFilters(filters);
	    
	    return response;
	    
	}

}
