package com.khatu.musicschool.wsresource;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DepartmentSearchCriteria {
	
	private String instrument;
	private String method;
	private String style;
	private String state;
	private String zip;
	private int satMin;
	private int actMin;
	private int greMin;
	private String musicMinorAvailable;
	private String graduateProgramAvailable;
	private String scholarshipsAvailable;
	private int firstResult=0;
	private int maxResult=20;
	
	public String getInstrument() {
		return instrument;
	}
	public void setInstrument(String instrument) {
		this.instrument = instrument;
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
	public String getMusicMinorAvailable() {
		return musicMinorAvailable;
	}
	public void setMusicMinorAvailable(String musicMinorAvailable) {
		this.musicMinorAvailable = musicMinorAvailable;
	}
	public String getGraduateProgramAvailable() {
		return graduateProgramAvailable;
	}
	public void setGraduateProgramAvailable(String graduateProgramAvailable) {
		this.graduateProgramAvailable = graduateProgramAvailable;
	}
	public String getScholarshipsAvailable() {
		return scholarshipsAvailable;
	}
	public void setScholarshipsAvailable(String scholarshipsAvailable) {
		this.scholarshipsAvailable = scholarshipsAvailable;
	}
	public int getFirstResult() {
		return firstResult;
	}
	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}
	public int getMaxResult() {
		return maxResult;
	}
	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	
	
}
