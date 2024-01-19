package com.school.sba.serviceimpl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.AcademicProgram;
import com.school.sba.entity.School;
import com.school.sba.exception.AcademicProgramAlreadyExists;
import com.school.sba.exception.SchoolNotFoundException;
import com.school.sba.repository.AcademicProgramRepository;
import com.school.sba.repository.SchoolRepository;
import com.school.sba.requestdto.AcademicProgramRequest;
import com.school.sba.responsedto.AcademicProgramResponse;
import com.school.sba.service.AcademicProgramService;
import com.school.sba.util.ResponseStructure;

@Service
public class AcademicProgramServiceImpl implements AcademicProgramService {
	@Autowired
	private AcademicProgramRepository academicprogramRepo;
	
	@Autowired
	private SchoolRepository schoolRepo;

	@Autowired
	private ResponseStructure<AcademicProgramResponse> responseStructure;
	
	
	
	private AcademicProgram mapToAcademicProgramRequest(AcademicProgramRequest academicProgramRequest) {
		return AcademicProgram.builder()
				.programName(academicProgramRequest.getProgramName())
				.beginsAt(academicProgramRequest.getBeginsAt())
				.endsAt(academicProgramRequest.getEndsAt())
				.programType(academicProgramRequest.getProgramType())
				.build();
		}
	
	private AcademicProgramResponse mapToAcademicProgramResponse(AcademicProgram academicProgram)
	{
		return AcademicProgramResponse.builder()
				.programId(academicProgram.getProgramId())
				.beginsAt(academicProgram.getBeginsAt())
				.endsAt(academicProgram.getEndsAt())
				.programType(academicProgram.getProgramType())
				.programName(academicProgram.getProgramName())
				.build();
	}
	
	@Override
	public ResponseEntity<ResponseStructure<AcademicProgramResponse>> createAcademicProgram(int schoolId,
			AcademicProgramRequest academicProgramRequest) {
		
		School school =schoolRepo.findById(schoolId)
				.orElseThrow(()-> new SchoolNotFoundException("Can't find any school in the given Id"));
			AcademicProgram academicprogram=mapToAcademicProgramRequest(academicProgramRequest);
			academicprogram.setSchool(school);
			academicprogramRepo.save(academicprogram);
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("New Academic Program is created");
			responseStructure.setData(mapToAcademicProgramResponse(academicprogram));
		return new ResponseEntity<ResponseStructure<AcademicProgramResponse>>(responseStructure,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<AcademicProgramResponse>>> findAll(int schoolId) {
		
		return schoolRepo.findById(schoolId).map(school ->{
			List<AcademicProgram> list=school.getAcademicprogramlist();
			ResponseStructure<List<AcademicProgramResponse>> rslist=new ResponseStructure<>();
			List<AcademicProgramResponse> aplist=new ArrayList<>();
			
			
			for(AcademicProgram obj: list) {
				aplist.add(mapToAcademicProgramResponse(obj));
			}
			rslist.setStatus(HttpStatus.FOUND.value());
			rslist.setMessage("Academic Programs found Successfully!!");
			rslist.setData(aplist);
			return new ResponseEntity<ResponseStructure<List<AcademicProgramResponse>>>(rslist,HttpStatus.FOUND);
		}).orElseThrow(()-> new SchoolNotFoundException("School not found in the given Id"));
	}

	
		
		
		
		
	}


