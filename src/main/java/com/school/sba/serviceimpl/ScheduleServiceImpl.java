package com.school.sba.serviceimpl;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.Schedule;
import com.school.sba.entity.School;
import com.school.sba.entity.User;
import com.school.sba.exception.ScheduleIsPresentException;
import com.school.sba.exception.ScheduleNotFoundException;
import com.school.sba.exception.SchoolNotFoundException;
import com.school.sba.repository.ScheduleRepository;
import com.school.sba.repository.SchoolRepository;
import com.school.sba.requestdto.ScheduleRequest;
import com.school.sba.responsedto.ScheduleResponse;
import com.school.sba.responsedto.UserResponse;
import com.school.sba.service.ScheduleService;
import com.school.sba.util.ResponseStructure;


@Service
public class ScheduleServiceImpl implements ScheduleService{
	@Autowired
	private SchoolRepository schoolRepo;
	@Autowired
	private ScheduleRepository scheduleRepo;
	@Autowired
	private ResponseStructure<ScheduleResponse> responseStructure;
	
	
	
	private Schedule mapToScheduleRequest(ScheduleRequest scheduleRequest) {
		return Schedule.builder()
				.opensAt(scheduleRequest.getOpensAt())
				.closesAt(scheduleRequest.getClosesAt())
				.classHourLength(Duration.ofMinutes(scheduleRequest.getClassHourLengthInMins()))
				.lunchTime(scheduleRequest.getLunchTime())
				.lunchLength(Duration.ofMinutes(scheduleRequest.getLunchLengthInMins()))
				.breakTime(scheduleRequest.getBreakTime())
				.breakLength(Duration.ofMinutes(scheduleRequest.getBreakLengthInMins()))
				.classHoursPerDay(scheduleRequest.getClassHoursPerDay())
				.build();
	}
	private ScheduleResponse mapToScheduleResponse(Schedule schedule) {
        return ScheduleResponse.builder()
                .ScheduleId(schedule.getScheduleId())
                .opensAt(schedule.getOpensAt())
                .closesAt(schedule.getClosesAt())
                .classHoursPerDay(schedule.getClassHoursPerDay())
                .classHourLengthInMins((int) schedule.getClassHourLength().toMinutes())
                .breakTime(schedule.getBreakTime())
                .breakLengthInMins((int) schedule.getBreakLength().toMinutes())
                .lunchTime(schedule.getLunchTime())
                .lunchLengthInMins((int) schedule.getLunchLength().toMinutes())
                .build();
    }
	
	@Override
	public ResponseEntity<ResponseStructure<ScheduleResponse>> saveSchedule(ScheduleRequest scheduleRequest, int schoolId) {
		School school =schoolRepo.findById(schoolId)
				.orElseThrow(()-> new SchoolNotFoundException("Can't find any school in the given Id"));
		if(school.getSchedule()== null) {
			Schedule  schedule=scheduleRepo.save(mapToScheduleRequest(scheduleRequest));
			school.setSchedule(schedule);
			schoolRepo.save(school);
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("New Schedule is created");
			responseStructure.setData(mapToScheduleResponse(schedule));
		}
		else {
			throw new ScheduleIsPresentException("Schedule is already present for this school");
		}
		return new ResponseEntity<ResponseStructure<ScheduleResponse>>(responseStructure,HttpStatus.CREATED);
	}
	
	
	@Override
	public ResponseEntity<ResponseStructure<ScheduleResponse>> findSchedule(int scheduleId) {
		 Schedule sch=  scheduleRepo.findById(scheduleId)
	        		.orElseThrow(()->new ScheduleNotFoundException("Schedule not found"));
		 
	        responseStructure.setStatus(HttpStatus.FOUND.value());
	        responseStructure.setMessage("Schedule found successfully!!!");
	        responseStructure.setData(mapToScheduleResponse(sch));
			return new ResponseEntity<ResponseStructure<ScheduleResponse>>(responseStructure,HttpStatus.FOUND);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<ScheduleResponse>> updateScheduleById(int scheduleId, ScheduleRequest scheduleRequest) {
		Schedule updateSchedule=scheduleRepo.findById(scheduleId)
				.map(u -> {
					return scheduleRepo.save(mapToScheduleRequest(scheduleRequest));
				})
				.orElseThrow(()->new RuntimeException("Invalid UserId"));
		
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Schedule not updated");
		responseStructure.setData(mapToScheduleResponse(updateSchedule));
		return new ResponseEntity<ResponseStructure<ScheduleResponse>>(responseStructure, HttpStatus.OK);
		
		
	}
	
	

}
