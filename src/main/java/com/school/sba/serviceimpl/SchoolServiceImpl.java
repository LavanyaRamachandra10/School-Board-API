package com.school.sba.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.School;
import com.school.sba.enums.UserRole;
import com.school.sba.exception.UnautorizedException;
import com.school.sba.exception.UserNotFoundByIdException;
import com.school.sba.repository.SchoolRepository;
import com.school.sba.repository.UserRepository;
import com.school.sba.requestdto.SchoolRequest;
import com.school.sba.responsedto.SchoolResponse;
import com.school.sba.service.SchoolService;
import com.school.sba.util.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class SchoolServiceImpl implements SchoolService {
	
	@Autowired
	private SchoolRepository schoolRepo;
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ResponseStructure<SchoolResponse> responseStructure;
	
private School mapToSchool(SchoolRequest schoolRequest) {
	return School.builder()
			.schoolName(schoolRequest.getSchoolName())
			.schoolContact(schoolRequest.getSchoolContact())
			.schoolEmail(schoolRequest.getSchoolEmail())
			.schoolLocation(schoolRequest.getSchoolLocation())
			.build();	
}

		
		
	private SchoolResponse mapToSchoolResponse(School school)
	{
		return SchoolResponse.builder()
				.schoolId(school.getSchoolId())
				.schoolName(school.getSchoolName())
				.schoolEmail(school.getSchoolEmail())
				.schoolLocation(school.getSchoolLocation())
				.schoolContact(school.getSchoolContact())
				.build();
	}
	

	@Override
	public ResponseEntity<ResponseStructure<SchoolResponse>> saveSchool(@Valid SchoolRequest schoolRequest, int userId) {
		return userRepo.findById(userId)
				.map(u -> {
					if(u.getUserRole().equals(UserRole.ADMIN))
					{
						if(u.getSchool() == null)
						{
							School school=mapToSchool(schoolRequest);
							school=schoolRepo.save(school);
							u.setSchool(school);
							userRepo.save(u);
							responseStructure.setStatus(HttpStatus.CREATED.value());
							responseStructure.setMessage("School saved successfully!!!");
							responseStructure.setData(mapToSchoolResponse(school));
							return new ResponseEntity<ResponseStructure<SchoolResponse>>(responseStructure, HttpStatus.CREATED);
						}
						else {
							throw new IllegalArgumentException("Cannot create more than one school");
						}
					}
						else {
							throw new UnautorizedException("Only admin can create school");
						}
							}).orElseThrow(() -> new UserNotFoundByIdException("Faild to create School"));
	}


}
		
		
		
		
		
	





	