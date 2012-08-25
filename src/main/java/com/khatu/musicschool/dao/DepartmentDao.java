package com.khatu.musicschool.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.khatu.musicschool.model.Department;
import com.khatu.musicschool.model.Faculty;
import com.khatu.musicschool.wsresource.DepartmentSearchCriteria;

@Repository
public class DepartmentDao {
	
	@Autowired
    private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private FacultyDao facultyDao;
	
	
	public List<Department> searchDepartment(DepartmentSearchCriteria departmentSearchCriteria){
		DetachedCriteria query = DetachedCriteria.forClass(Department.class)
				.add((Criterion) Property.forName("keyword").like("%" + departmentSearchCriteria.getInstrument() +"%"));
//		if(departmentSearchCriteria.getState()!=null)
//			query.add((Criterion) Property.forName("state").like("%" + departmentSearchCriteria.getInstrument() +"%"))
		List<Department> departments = this.hibernateTemplate.findByCriteria(query);

		
		return departments;
	}
	
	
	public Department addDepartment(Department department){
		List<Faculty> faculties = facultyDao.getFacultyByDepartment(department.getDepartmentId());
		department.setFaculty(faculties);
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
