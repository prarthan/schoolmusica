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
	private List<Faculty> faculty;
	private boolean musicMinorAvailable=false;
	private boolean graduateProgramAvailable=false;
	private boolean scholarshipsAvailable=false;
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
