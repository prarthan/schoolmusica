package com.khatu.musicschool.wsresource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.khatu.musicschool.common.Filters;
import com.khatu.musicschool.model.Department;
import com.khatu.musicschool.model.Faculty;
import com.khatu.musicschool.model.Method;
import com.khatu.musicschool.model.Specialization;
import com.khatu.musicschool.model.SpecializationType;
import com.khatu.musicschool.model.Style;
import com.khatu.musicschool.wsresource.response.DepartmentResponse;

@Component
@Path("/department")
public class DepartmentResource {
	
	@POST
	@Path("/search")
	@Produces({MediaType.APPLICATION_JSON })
	@Consumes({MediaType.APPLICATION_JSON })
	public DepartmentResponse searchDepartment(final DepartmentSearchCriteria searchCriteria){
		return getdummydata();		
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
		
		
		SpecializationType specializationType = new SpecializationType();
		specializationType.setSpecalizationTypeId(1);
		specializationType.setName("guitar");
		
		
		SpecializationType specializationType2 = new SpecializationType();
		specializationType2.setSpecalizationTypeId(2);
		specializationType2.setName("harp");
		
		
		//Specilization	
		List<Specialization> specializationList = new ArrayList<Specialization>();
		Specialization  specialization = new Specialization();
		specialization.setSpecializationType(specializationType);
		specialization.setMethod(method);
		specialization.setStyles(style);
		specialization.setNumberOfOpenings(2);
		
		Specialization  specialization2 = new Specialization();
		specialization2.setSpecializationType(specializationType2);
		specialization2.setMethod(method);
		specialization2.setStyles(style);
		specialization2.setNumberOfOpenings(3);
		
		
		specializationList.add(specialization);
		specializationList.add(specialization2);
		
		
		
		//list of department
		List<Department> departments = new ArrayList<Department>();
		Department dept1 = new Department();
		dept1.setDepartmentName("String, Harp & Guitar");
	    dept1.setDepartmentUrl("http://www.esm.rochester.edu/strings/");
	    dept1.setSpecialization(specializationList);
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
	    dept2.setSpecialization(specializationList);
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
