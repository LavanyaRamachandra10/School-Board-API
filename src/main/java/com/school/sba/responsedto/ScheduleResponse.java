package com.school.sba.responsedto;

import java.time.Duration;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ScheduleResponse {
	private int ScheduleId;
	private LocalTime opensAt;
	 private LocalTime closesAt;
	 private int classHoursPerDay;
	 private int classHourLengthInMins;
	 private LocalTime breakTime;
	 private int breakLengthInMins;
	 private LocalTime lunchTime;
	 private int lunchLengthInMins;
	 

}
