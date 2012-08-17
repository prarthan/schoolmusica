package com.khatu.musicschool.model;

import java.util.List;   
import javax.annotation.Nonnull;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;


@XmlRootElement
@Entity
public class Department {
	private int departmentId;
	private String departmentName;
	private int musicSchoolId;
	private String keyword;
	private int satMin=0;
	private int actMin=0;
	private int greMin=0;
	private List<Faculty> faculty;
	private boolean musicMinorAvailable=false;
	private boolean graduateProgramAvailable=false;
	private boolean scholarshipsAvailable=false;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String country;
	private String departmentUrl;
	private String announcement;
	private String email;
	

	
	public Department(){
		
		
	}
	
	@Id
	@GeneratedValue
	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	
	
	@NotEmpty(message="You must provide Department name.")
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	

	@Index(name = "musicschoolindex")
	public int getMusicSchoolId() {
		return musicSchoolId;
	}

	public void setMusicSchoolId(int musicSchoolId) {
		this.musicSchoolId = musicSchoolId;
	}

	@NotEmpty(message="You must provide instrument/program.")
	@Index(name = "departmentkeywordindex")
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Column(length=5)
	@Index(name = "deptsatminindex")
	public int getSatMin() {
		return satMin;
	}
	public void setSatMin(int satMin) {
		this.satMin = satMin;
	}

	@Column(length=5)
	@Index(name = "deptactminindex")
	public int getActMin() {
		return actMin;
	}
	public void setActMin(int actMin) {
		this.actMin = actMin;
	}

	@Column(length=5)
	@Index(name = "deptgreminindex")
	public int getGreMin() {
		return greMin;
	}
	public void setGreMin(int greMin) {
		this.greMin = greMin;
	}

	
	@OneToMany(cascade=CascadeType.REMOVE,fetch=FetchType.LAZY)
	@JoinColumn(name="departmentId")
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	public List<Faculty> getFaculty() {
		return faculty;
	}

	public void setFaculty(List<Faculty> faculty) {
		this.faculty = faculty;
	}

	@Column(columnDefinition="boolean default false")
	public boolean isMusicMinorAvailable() {
		return musicMinorAvailable;
	}
	public void setMusicMinorAvailable(boolean musicMinorAvailable) {
		this.musicMinorAvailable = musicMinorAvailable;
	}
	
	@Column(columnDefinition="boolean default false")
	public boolean isGraduateProgramAvailable() {
		return graduateProgramAvailable;
	}

	public void setGraduateProgramAvailable(boolean graduateProgramAvailable) {
		this.graduateProgramAvailable = graduateProgramAvailable;
	}
	
	@Column(columnDefinition="boolean default false")
	public boolean isScholarshipsAvailable() {
		return scholarshipsAvailable;
	}

	public void setScholarshipsAvailable(boolean scholarshipsAvailable) {
		this.scholarshipsAvailable = scholarshipsAvailable;
	}
	
	@NotEmpty(message="You must provide department address.")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@NotEmpty(message="You must provide city.")
	@Column(length=20)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@NotEmpty(message="You must provide state.")
	@Column(length=20)
	@Index(name = "deptstateindex")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@NotEmpty(message="You must provide zip.")
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@NotEmpty(message="you must provide country.")
	@Column(length=30)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@URL
	@NotEmpty(message="You must provide department url.")
	public String getDepartmentUrl() {
		return departmentUrl;
	}

	public void setDepartmentUrl(String departmentUrl) {
		this.departmentUrl = departmentUrl;
	}

	public String getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}

	@NotEmpty(message="You must provide email.")
	@Email(message="Invalid email format.")
	@Column(length=30)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
