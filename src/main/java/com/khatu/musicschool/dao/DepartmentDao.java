package com.khatu.musicschool.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.khatu.musicschool.model.Department;
import com.khatu.musicschool.wsresource.DepartmentSearchCriteria;

@Repository
public class DepartmentDao {
	
	@Autowired
    private HibernateTemplate hibernateTemplate;
	
	
	public List<Department> searchDepartment(DepartmentSearchCriteria departmentSearchCriteria){
		DetachedCriteria query = DetachedCriteria.forClass(Department.class)
				.add((Criterion) Property.forName("keyword").like("%" + departmentSearchCriteria.getInstrument() +"%"));
		List<Department> departments = this.hibernateTemplate.findByCriteria(query);

		
		return departments;
	}
	
	
	

}
