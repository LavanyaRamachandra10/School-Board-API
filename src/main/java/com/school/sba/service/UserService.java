package com.school.sba.service;

import org.springframework.http.ResponseEntity;

import com.school.sba.entity.User;
import com.school.sba.requestdto.UserRequest;
import com.school.sba.responsedto.UserResponse;
import com.school.sba.util.ResponseStructure;

public interface UserService {


	ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequest userrequest);

	ResponseEntity<ResponseStructure<UserResponse>> getRegisterUser(UserRequest userRequest);




	ResponseEntity<ResponseStructure<UserResponse>> findUserById(int userId);


	ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId);
	
}