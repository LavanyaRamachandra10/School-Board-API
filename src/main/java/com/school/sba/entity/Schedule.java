package com.school.sba.entity;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Schedule {
	 private int SchduleId;
	 private LocalTime opensAt;
	 private LocalTime closesAt;
	 private int classHoursPerDay;
	 private LocalTime classHourLength;
	 private LocalTime breatTime;
	 private LocalTime breakLength;
	 private LocalTime lunchTime;
	 private LocalTime lunchLength;
	 
	 
	 
	 
	 

}
