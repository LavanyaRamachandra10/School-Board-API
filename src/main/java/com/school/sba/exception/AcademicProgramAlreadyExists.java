package com.school.sba.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicProgramAlreadyExists extends RuntimeException {

	private String message;
	
}
