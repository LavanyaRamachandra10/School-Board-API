package com.school.sba.entity;

import java.time.Duration;
import java.time.LocalTime;

import org.hibernate.annotations.GeneratorType;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int ScheduleId;
	 private LocalTime opensAt;
	 private LocalTime closesAt;
	 private int classHoursPerDay;
	 private Duration classHourLength;
	 private LocalTime breakTime;
	 private Duration breakLength;
	 private LocalTime lunchTime;
	 private Duration lunchLength;
	 
	 
	 
	 
	 

}
