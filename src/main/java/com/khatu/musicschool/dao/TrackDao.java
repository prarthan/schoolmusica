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
	
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	
	/**
	 * add/update record
	 * @param report
	 */
	
	public void addClickTrackReport(ClickTrackReport report){
		report.setDate(formateDate(report.getDate()));
		hibernateTemplate.merge(report);
	}
	
	/**
	 * format date
	 * @param currentDate
	 * @return
	 */
	private Date formateDate(Date currentDate){
		String dateS = df.format(new Date());
		Date date=null;
		try {
			date = df.parse(dateS);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	
	/**
	 * get report for school on the date
	 * @param schoolId
	 * @return
	 */
	
	public ClickTrackReport getReportBySchoolId(int schoolId){
		Object[] params = null;
		Date currentDate = formateDate(new Date());
		params = new Object[] { schoolId,currentDate};
		List<ClickTrackReport> reports = hibernateTemplate.find("from ClickTrackReport where schoolId = ? and date = ?",params);
		if(reports!=null && reports.size()==1){
			return reports.get(0);
		}
		
		return null;
	}
	
	/**
	 * get report for last week
	 * @param schoolId
	 * @return
	 */
	public List<ClickTrackReport> getReportforWeek(int schoolId){
		
		DetachedCriteria query = DetachedCriteria.forClass(ClickTrackReport.class);
		
		Conjunction conjuctionCriteria = Restrictions.conjunction();

		conjuctionCriteria.add(Restrictions.eq("schoolId", schoolId));
		
		Calendar c  = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 7);
		
		Date startDate = c.getTime();
		
		conjuctionCriteria.add(Restrictions.gt("date", startDate));
		
		List<ClickTrackReport> reports = hibernateTemplate.findByCriteria(query);
		
		return reports;
	}
	
	

}
