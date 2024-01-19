package com.school.sba.serviceimpl;

import java.util.Optional;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.sba.entity.User;
import com.school.sba.entity.User.UserBuilder;
import com.school.sba.enums.UserRole;
import com.school.sba.exception.AdminAlreadyExistsException;
import com.school.sba.exception.UserNotFoundByIdException;
import com.school.sba.repository.UserRepository;
import com.school.sba.requestdto.UserRequest;
import com.school.sba.responsedto.UserResponse;
import com.school.sba.service.UserService;
import com.school.sba.util.ResponseStructure;

@Service
public class  UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ResponseStructure<UserResponse> responseStructure;
	
	
private User mapToUser(UserRequest userRequest) {
		
		return User.builder()
				.userName(userRequest.getUserName())
				.userEmail(userRequest.getUserEmail())
				.userPassword(userRequest.getUserPassword())
				.firstName(userRequest.getFirstName())
				.lastName(userRequest.getLastName())
				.userContact(userRequest.getUserContact())
				.userRole(userRequest.getUserRole())
				.build();	
	}
	private UserResponse mapToUserResponse(User user)
	{
		return UserResponse.builder()
				.userId(user.getUserId())
				.userName(user.getUserName())
				.userId(user.getUserId())
				.userEmail(user.getUserEmail())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.userContact(user.getUserContact())
				.userRole(user.getUserRole())
				.build();
	}
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequest userRequest) {
      User user = userRepo.save(mapToUser(userRequest));
    		  
      responseStructure.setStatus(HttpStatus.OK.value());
	  responseStructure.setMessage("user registered successfully");
	  responseStructure.setData(mapToUserResponse(user));
      
      return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.CREATED);
	}
	
	public boolean isPresent(UserRole userRole)
	{
		boolean existsByUserRole=userRepo.existsByUserRole(userRole.ADMIN);
		if(existsByUserRole==true)
		{
			throw new AdminAlreadyExistsException("We can have only one ADMIN");
		}
		return true;
	}
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> getRegisterUser( UserRequest userRequest) {
		try {
			if(isPresent(UserRole.ADMIN)==false)
			{
				User getregisteruser=userRepo.save(mapToUser(userRequest));
				responseStructure.setStatus(HttpStatus.OK.value());
				  responseStructure.setMessage("User data registered successfully!!!");
				  responseStructure.setData(mapToUserResponse(getregisteruser));
				
			}
			else {
				throw new Exception("we can have only one admin");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseStructure<UserResponse>> (responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findUserById(int userId) {
        User user=  userRepo.findById(userId)
        		.orElseThrow(()->new RuntimeException("User not found by id"));
        responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
        responseStructure.setMessage("user found successfully!!!");
        responseStructure.setData(mapToUserResponse(user));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.NOT_FOUND);
	}
	
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId) {
		User user=userRepo.findById(userId).orElseThrow(()->new UserNotFoundByIdException("Failed to delete the user data!!"));
		user.setIsDeleted(true);
		userRepo.delete(user);
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("User data Deleted  successfully!!!");
			responseStructure.setData(mapToUserResponse(user));
			return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.FOUND);
		} 
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	

