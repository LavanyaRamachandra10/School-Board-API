package com.school.sba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.school.sba.requestdto.UserRequest;
import com.school.sba.responsedto.UserResponse;
import com.school.sba.service.UserService;
import com.school.sba.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	
	@PostMapping(value="/users/register")
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody UserRequest userrequest)
	{
		return userservice.registerUser(userrequest);
	}
	
	@GetMapping(value="/users/register")
	public ResponseEntity<ResponseStructure<UserResponse>> getRegisterUser( UserRequest userRequest)
	
	{
		return userservice.getRegisterUser( userRequest);
	}
	
	@GetMapping(value="/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@PathVariable int userId)
	{
		return userservice.findUserById(userId);
	}
	
	@DeleteMapping(value="/users/{userId}")
   public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(@PathVariable int userId)
   {
	return userservice.deleteUser(userId);
   }
	
	
	
	
}
