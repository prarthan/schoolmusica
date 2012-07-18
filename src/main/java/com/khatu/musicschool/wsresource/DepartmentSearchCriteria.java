package com.khatu.musicschool.wsresource;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DepartmentSearchCriteria {
	
	private String specialization;
	private String method;
	private String style;
	private String state;
	private String zip;
	private int satMin;
	private int actMin;
	private int greMin;
	private boolean musicMinorAvailable;
	private boolean graduateProgramAvailable;
	private boolean positionsAvailable;
	
	
	public String getSpecialization() {
		return specialization;
	}
	
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
		
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public int getSatMin() {
		return satMin;
	}
	public void setSatMin(int satMin) {
		this.satMin = satMin;
	}

	public int getActMin() {
		return actMin;
	}
	public void setActMin(int actMin) {
		this.actMin = actMin;
	}

	public int getGreMin() {
		return greMin;
	}
	public void setGreMin(int greMin) {
		this.greMin = greMin;
	}

	public boolean isMusicMinorAvailable() {
		return musicMinorAvailable;
	}
	public void setMusicMinorAvailable(boolean musicMinorAvailable) {
		this.musicMinorAvailable = musicMinorAvailable;
	}

	public boolean isGraduateProgramAvailable() {
		return graduateProgramAvailable;
	}

	public void setGraduateProgramAvailable(boolean graduateProgramAvailable) {
		this.graduateProgramAvailable = graduateProgramAvailable;
	}

	public boolean isPositionsAvailable() {
		return positionsAvailable;
	}

	public void setPositionsAvailable(boolean positionsAvailable) {
		this.positionsAvailable = positionsAvailable;
	}	
	
}