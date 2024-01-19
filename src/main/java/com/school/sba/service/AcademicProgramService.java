package com.school.sba.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.school.sba.entity.AcademicProgram;
import com.school.sba.enums.UserRole;
import com.school.sba.requestdto.AcademicProgramRequest;
import com.school.sba.responsedto.AcademicProgramResponse;
import com.school.sba.util.ResponseStructure;

public interface AcademicProgramService {

	ResponseEntity<ResponseStructure<AcademicProgramResponse>> createAcademicProgram(int schoolId,
			AcademicProgramRequest academicProgramRequest);

	ResponseEntity<ResponseStructure<List<AcademicProgramResponse>>> findAll(int schoolId);
	

		
	
	
}
