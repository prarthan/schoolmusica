package com.khatu.musicschool.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.khatu.musicschool.dao.TrackDao;
import com.khatu.musicschool.model.ClickTrackReport;
import com.khatu.musicschool.wsresource.response.SchoolReportResponse;

@Service
@Transactional(readOnly = true)
public class TrackService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TrackDao trackDao;
	
	
	/**
	 * increment report count
	 * @param schoolId
	 */
	@Async
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void trackClick(int schoolId){
		synchronized(this){
			ClickTrackReport report = trackDao.getReportBySchoolId(schoolId);
			if(report!=null){
				report.setClickCount(report.getClickCount()+1);
			}else{
				report = new ClickTrackReport();
				report.setSchoolId(schoolId);
			}
			
			trackDao.addClickTrackReport(report);
		}
	}
	
	
	/**
	 * get report for school
	 * @param schoolId
	 * @return
	 */
	
	public SchoolReportResponse getReport(int schoolId){
		SchoolReportResponse response = new SchoolReportResponse();
		List<ClickTrackReport> report = trackDao.getReportforWeek(schoolId);
		if(report !=null){
			List<String> graphReportValues = new ArrayList<String>();
			for(ClickTrackReport currentReport : report){
				graphReportValues.add(currentReport.getDate().toString() + ", 0" + "," + currentReport.getClickCount());
			}
			response.addReport(graphReportValues);
		}
		
		return response;
	}

}
