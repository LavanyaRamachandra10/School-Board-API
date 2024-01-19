package com.school.sba.service;

import org.springframework.http.ResponseEntity;

import com.school.sba.requestdto.ScheduleRequest;
import com.school.sba.responsedto.ScheduleResponse;
import com.school.sba.util.ResponseStructure;

public interface ScheduleService {

	ResponseEntity<ResponseStructure<ScheduleResponse>> saveSchedule(ScheduleRequest scheduleRequest, int schoolId);


	ResponseEntity<ResponseStructure<ScheduleResponse>> updateScheduleById(int scheduleId, ScheduleRequest scheduleRequest);



	ResponseEntity<ResponseStructure<ScheduleResponse>> findSchedule(int scheduleId);

}
