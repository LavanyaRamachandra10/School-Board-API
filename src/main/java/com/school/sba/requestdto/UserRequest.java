package com.school.sba.requestdto;

import com.school.sba.enums.UserRole;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class UserRequest {
	
	private String userName;
	private String userPassword;
	private String firstName;
	private String lastName;
	@Max(value = 9999999999l)
	@Min(value= 9999999999l)
	private long userContact;
	private String userEmail;
	private UserRole userRole;

}
