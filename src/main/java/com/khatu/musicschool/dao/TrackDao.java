package com.khatu.musicschool.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.khatu.musicschool.model.ClickTrackReport;
import com.khatu.musicschool.model.Department;

@Repository
public class TrackDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	private DateFormat df = new SimpleDateFormat("dd-mm-YYYY");
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	
	/**
	 * add/update record
	 * @param report
	 */
	public void addClickTrackReport(ClickTrackReport report){
		hibernateTemplate.merge(report);
	}
	
	
	/**
	 * get report for school on the date
	 * @param schoolId
	 * @return
	 */
	
	public ClickTrackReport getReportBySchoolId(int schoolId){
		Object[] params = null;
		try {
			params = new Object[] { schoolId, df.parse(df.format(new Date()))};
		} catch (ParseException e) {
			logger.error("can not parse date");
		}
		List<ClickTrackReport> reports = hibernateTemplate.find("from ClickTrackReport where schoolId = ? and date = ?",params);
		if(reports!=null && reports.size()==1){
			return reports.get(0);
		}
		
		return null;
	}
	
	public List<ClickTrackReport> getReportforWeek(int schoolId){
		
		DetachedCriteria query = DetachedCriteria.forClass(ClickTrackReport.class);
		
		Conjunction conjuctionCriteria = Restrictions.conjunction();

		conjuctionCriteria.add(Restrictions.eq("schoolId", schoolId));
		
//		conjuctionCriteria.add(Restrictions.gt("date", (Calendar.getInstance().add(Calendar.DAY_OF_MONTH, 7))));
		
		return null;
	}
	
	

}
