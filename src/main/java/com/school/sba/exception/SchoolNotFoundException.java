package com.school.sba.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class SchoolNotFoundException extends RuntimeException{
	
	private String message;

}
