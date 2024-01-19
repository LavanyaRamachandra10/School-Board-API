package com.school.sba.requestdto;

import java.time.Duration;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ScheduleRequest {
	
	
	private LocalTime opensAt;
	 private LocalTime closesAt;
	 private int classHoursPerDay;
	 private int classHourLengthInMins;
	 private LocalTime breakTime;
	 private int breakLengthInMins;
	 private LocalTime lunchTime;
	 private int lunchLengthInMins;
	 

}
