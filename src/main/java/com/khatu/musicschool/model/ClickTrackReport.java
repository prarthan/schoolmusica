package com.khatu.musicschool.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Index;

@XmlRootElement
@Entity
public class ClickTrackReport {
	private int trackId;
	private int schoolId;
	private Date date= new Date();
	private long clickCount=1;
	
	@Id
	@GeneratedValue
	public int getTrackId() {
		return trackId;
	}
	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}
	
	@Index(name = "trackschoolindex")
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	
	@Index(name = "trackschoolindex")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(length=5,columnDefinition="int default 1")
	public long getClickCount() {
		return clickCount;
	}
	public void setClickCount(long clickCount) {
		this.clickCount = clickCount;
	}
}
