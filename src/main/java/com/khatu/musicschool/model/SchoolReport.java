package com.khatu.musicschool.model;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class SchoolReport {
	
	private int schoolId;
	private String key;
	private int clickCount = 0;
	private int searchCount =0;
	
	
	public SchoolReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public  SchoolReport(int schoolId, int clickCount, int searchCount) {
		super();
		this.schoolId = schoolId;
		this.clickCount = clickCount;
		this.searchCount = searchCount;
	}
	
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public int getClickCount() {
		return clickCount;
	}
	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}
	public int getSearchCount() {
		return searchCount;
	}
	public void setSearchCount(int searchCount) {
		this.searchCount = searchCount;
	}

	@Override
	public String toString() {
		return "SchoolReport [schoolId=" + schoolId + ", key=" + key
				+ ", clickCount=" + clickCount + ", searchCount=" + searchCount
				+ "]";
	}

}
