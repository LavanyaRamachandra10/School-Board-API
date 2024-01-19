package com.school.sba.util;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;



@Component
@Getter
@Setter
public class ExceptionResponse {
	
	private int status;
	private String message;
	private String rootCause;
}
