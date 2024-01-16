package com.school.sba.serviceimpl;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.sba.entity.School;
import com.school.sba.util.ResponseStructure;

public interface SchoolServiceImpl 
{
	ResponseEntity<ResponseStructure<School>>saveSchool(School school);
	
	ResponseEntity<ResponseStructure<List<School>>> getAllSchool();
	
	ResponseEntity<ResponseStructure<School>>getSchoolById(School school);
	
	ResponseEntity<ResponseStructure<School>>updateSchool(int id,School updateschool);

	ResponseEntity<ResponseStructure<School>> deleteSchoolById(School school);
	

}
