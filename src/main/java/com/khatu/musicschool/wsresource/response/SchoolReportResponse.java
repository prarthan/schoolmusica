package com.khatu.musicschool.wsresource.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.khatu.musicschool.model.SchoolReport;


@XmlRootElement
public class SchoolReportResponse {
	
	private List<String> report= null;
	
	
    public  SchoolReportResponse() {
		super();
		report = new ArrayList<String>();
		report.add("day, search, click");
	}

	public List<String> getReport() {
		return report;
	}

	public void addReport(List<String> report) {
		this.report.addAll(report);
	}
	
}
