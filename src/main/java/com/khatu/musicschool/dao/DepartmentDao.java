package com.khatu.musicschool.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.khatu.musicschool.model.Department;
import com.khatu.musicschool.model.Faculty;
import com.khatu.musicschool.wsresource.DepartmentSearchCriteria;
import com.khatu.musicschool.wsresource.response.MusicSchoolResponse;
import com.khatu.musicschool.wsresource.response.MusicSearchResponse;

@Repository
public class DepartmentDao {
	
	@Autowired
    private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private FacultyDao facultyDao;
	
	/**
	 * search department by criteria
	 * @param departmentSearchCriteria
	 * @return
	 */
	
	public List<Department> searchDepartment(DepartmentSearchCriteria departmentSearchCriteria){
		
		List<Department> departments= null;
		
		DetachedCriteria query = DetachedCriteria.forClass(Department.class);
				
		
		Conjunction conjuctionCriteria = Restrictions.conjunction();

		if(departmentSearchCriteria.getInstrument()!=null && !departmentSearchCriteria.getInstrument().isEmpty())
			conjuctionCriteria.add(Restrictions.like("keyword", "%" + departmentSearchCriteria.getInstrument() +"%"));
		
		
		if(departmentSearchCriteria.getState()!=null){
			conjuctionCriteria.add(Restrictions.eq("state", departmentSearchCriteria.getState()));
		}
		
		if(departmentSearchCriteria.getSatMin()>0){
			conjuctionCriteria.add(Restrictions.le("satMin", departmentSearchCriteria.getSatMin()));

		}
		
		if(departmentSearchCriteria.getGreMin()>0){
			conjuctionCriteria.add(Restrictions.le("greMin", departmentSearchCriteria.getGreMin()));
		}
		
		if(departmentSearchCriteria.getActMin()>0){
			conjuctionCriteria.add(Restrictions.le("actMin", departmentSearchCriteria.getActMin()));
		}
		
		if(departmentSearchCriteria.getGraduateProgramAvailable()!=null){
			conjuctionCriteria.add(Restrictions.eq("graduateProgramAvailable", (departmentSearchCriteria.getGraduateProgramAvailable().equalsIgnoreCase("true"))?true:false));
		}
		
		if(departmentSearchCriteria.getMusicMinorAvailable()!=null){
			conjuctionCriteria.add(Restrictions.eq("musicMinorAvailable", (departmentSearchCriteria.getMusicMinorAvailable().equalsIgnoreCase("true"))?true:false));
		}
		
		if(departmentSearchCriteria.getScholarshipsAvailable()!=null){
			conjuctionCriteria.add(Restrictions.eq("scholarshipsAvailable", (departmentSearchCriteria.getScholarshipsAvailable().equalsIgnoreCase("true"))?true:false));
		}
		
		if(departmentSearchCriteria.getStyle()!=null){
			conjuctionCriteria.add(Restrictions.eq("faculty.styles", departmentSearchCriteria.getStyle()));
		}
		
		if(departmentSearchCriteria.getMethod() != null){
			conjuctionCriteria.add(Restrictions.eq("faculty.methods", departmentSearchCriteria.getMethod()));
		}
		
		
		if(departmentSearchCriteria.getInstrument()!=null && !departmentSearchCriteria.getInstrument().isEmpty()){
			query.add(conjuctionCriteria);
			departments = this.hibernateTemplate.findByCriteria(query,departmentSearchCriteria.getFirstResult() , departmentSearchCriteria.getMaxResult());
			this.hibernateTemplate.setCacheQueries(true);
			this.hibernateTemplate.setQueryCacheRegion("query.searchquery");
		}
		
		return departments;
	}
	
	/**
	 * get search result
	 * @param department
	 * @return
	 */
	
	private MusicSearchResponse getSearchResult(List<Department> department){

		List<MusicSchoolResponse> responseMusic = new ArrayList<MusicSchoolResponse>();
    	// generate return response
    	for(Department currentDept: department){
    		responseMusic.add(new MusicSchoolResponse(currentDept));
    	}
    	
    	MusicSearchResponse response = new MusicSearchResponse();
    	response.setSchools(responseMusic);
    	return response;
	}
	
	
	
	
	public Department addDepartment(Department department){
//		List<Faculty> faculties = facultyDao.getFacultyByDepartment(department.getDepartmentId());
//		department.setFaculty(faculties);
		return (Department)hibernateTemplate.merge(department);
	}
	
	public Department getDepartment(final int departmentId){
		return (Department) hibernateTemplate.get(Department.class, departmentId);
	}
	
	public List<Department> getDepartmentBySchool(final int musicSchoolId){
		String hql = "FROM Department D WHERE D.musicSchoolId = " + musicSchoolId;
		List<Department> departments = hibernateTemplate.find(hql);
		return departments;
	}
	
	public void deleteDepartment(int departmentId){
		Department dept = getDepartment(departmentId);
		if(dept!=null){
			dept.setFaculty(null);
			hibernateTemplate.delete(dept);
			hibernateTemplate.flush();
		}
	}
	
	

}
