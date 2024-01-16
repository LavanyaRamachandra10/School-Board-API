package com.school.sba.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class School {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int SchoolId;
	private String SchoolName;
	private long SchoolContact;
	private String SchoolLocation;
	private String SchoolEmail;
	public int getSchoolId() {
		return SchoolId;
	}
	public void setSchoolId(int schoolId) {
		SchoolId = schoolId;
	}
	public String getSchoolName() {
		return SchoolName;
	}
	public void setSchoolName(String schoolName) {
		SchoolName = schoolName;
	}
	public long getSchoolContact() {
		return SchoolContact;
	}
	public void setSchoolContact(long schoolContact) {
		SchoolContact = schoolContact;
	}
	public String getSchoolLocation() {
		return SchoolLocation;
	}
	public void setSchoolLocation(String schoolLocation) {
		SchoolLocation = schoolLocation;
	}
	public String getSchoolEmail() {
		return SchoolEmail;
	}
	public void setSchoolEmail(String schoolEmail) {
		SchoolEmail = schoolEmail;
	}
	
	@OneToOne
	private Schedule schedule;
	
	
	
	

	
}
