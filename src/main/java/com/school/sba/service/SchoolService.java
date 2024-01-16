package com.school.sba.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.School;
import com.school.sba.repository.SchoolRepository;
import com.school.sba.serviceimpl.SchoolServiceImpl;
import com.school.sba.util.ResponseStructure;

@Service
public class SchoolService implements SchoolServiceImpl{
	
	@Autowired
	private SchoolRepository schoolrepository;
	@Autowired
	ResponseStructure<School> responsestructure;
	
	ResponseStructure<List<School>> listresponsestructure;

	

	@Override
	public ResponseEntity<ResponseStructure<List<School>>> getAllSchool() {
		List<School> findAll=schoolrepository.findAll();
		if(!findAll.isEmpty()) {
			listresponsestructure.setStatus(HttpStatus.OK.value());
			listresponsestructure.setMessage("School data Found!!!");
			listresponsestructure.setData(findAll);
			return new ResponseEntity<ResponseStructure<List<School>>>(listresponsestructure, HttpStatus.OK);
		} else {
			listresponsestructure.setStatus(HttpStatus.NOT_FOUND.value());
			listresponsestructure.setMessage("School data Not Found !!!");
			return new ResponseEntity<ResponseStructure<List<School>>>(listresponsestructure, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<School>> saveSchool(School school) {
		School sch = schoolrepository.save(school);
		if ((sch != null)) {
			responsestructure.setStatus(HttpStatus.FOUND.value());
			responsestructure.setMessage("School data saved !!!");
			responsestructure.setData(sch);
			return new ResponseEntity<ResponseStructure<School>>(responsestructure, HttpStatus.FOUND);
		} else
			return null;
	}
	
	@Override
	public ResponseEntity<ResponseStructure<School>> getSchoolById(School school) {
		Optional<School> findById = schoolrepository.findById(school.getSchoolId());
		if (findById.isPresent()) {
			responsestructure.setStatus(HttpStatus.OK.value());
			responsestructure.setMessage("School data Found!!!");
			responsestructure.setData(findById.get());
			return new ResponseEntity<ResponseStructure<School>>(responsestructure, HttpStatus.OK);
		} else {
			responsestructure.setStatus(HttpStatus.NOT_FOUND.value());
			responsestructure.setMessage("School data Not Found !!!");
			return new ResponseEntity<ResponseStructure<School>>(responsestructure, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<School>> updateSchool(int id, School updateschool) {
		Optional<School> findById = schoolrepository.findById(id);
		if (findById.isPresent()) {
			School exStd = findById.get();
			exStd = mapBySchool(exStd, updateschool);
			schoolrepository.save(exStd);
			responsestructure.setStatus(HttpStatus.OK.value());
			responsestructure.setMessage("School data Updated !!!");
			responsestructure.setData(exStd);
			return new ResponseEntity<ResponseStructure<School>>(responsestructure, HttpStatus.OK);
		} else {
			responsestructure.setStatus(HttpStatus.NOT_FOUND.value());
			responsestructure.setMessage("School data Not Found !!!");
			return new ResponseEntity<ResponseStructure<School>>(responsestructure, HttpStatus.NOT_FOUND);
		}
	}

	private School mapBySchool(School exStd, School updateSchool) {
		exStd.setSchoolName(updateSchool.getSchoolName());
		exStd.setSchoolEmail(updateSchool.getSchoolEmail());
		//exStd.setschedule(updateSchool.getSchedule());
		exStd.setSchoolContact(updateSchool.getSchoolContact());
		exStd.setSchoolLocation(updateSchool.getSchoolLocation());
		return exStd;
	}

	@Override
	public ResponseEntity<ResponseStructure<School>> deleteSchoolById(School school) {
		Optional<School> findById = schoolrepository.findById(school.getSchoolId());
		if (findById.isPresent()) {
			responsestructure.setStatus(HttpStatus.GONE.value());
			responsestructure.setMessage("School data Deleted !!!");
			responsestructure.setData(findById.get());
			schoolrepository.deleteById(school.getSchoolId());
			return new ResponseEntity<ResponseStructure<School>>(responsestructure, HttpStatus.GONE);
		} else {
			responsestructure.setStatus(HttpStatus.NOT_FOUND.value());
			responsestructure.setMessage("School data Not Found !!!");
			return new ResponseEntity<ResponseStructure<School>>(responsestructure, HttpStatus.NOT_FOUND);
		}
	}

			
	
	
	
	
	
	

}
