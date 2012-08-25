package com.khatu.musicschool.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;





@XmlRootElement
@Entity
@Cache(region = "program", usage = CacheConcurrencyStrategy.READ_ONLY)
public class Program {
	
	private int programId;
	private String name;
	private List<Style> styles;
	private List<Method> method;
	
	
	@Id
	@GeneratedValue
	public int getProgramId() {
		return programId;
	}
	public void setProgramId(int programId) {
		this.programId = programId;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="programId")
	public List<Style> getStyles() {
		return styles;
	}
	
	public void setStyles(List<Style> styles) {
		this.styles = styles;
	}
	
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="programId")
	public List<Method> getMethod() {
		return method;
	}
	public void setMethod(List<Method> method) {
		this.method = method;
	}

}
