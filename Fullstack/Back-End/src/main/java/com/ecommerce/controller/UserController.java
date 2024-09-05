package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.AddUserRequest;
import com.ecommerce.dto.UserLoginRequest;
import com.ecommerce.dto.UserResponse;
import com.ecommerce.dto.UserVerifyRegisterRequest;
import com.ecommerce.resource.UserResource;

@RestController
@RequestMapping("api/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserResource userResource;

	@PostMapping("register")
	public ResponseEntity<UserResponse> registerUser(@RequestBody AddUserRequest userRequest) {
		return this.userResource.registerUser(userRequest);
	}

	@PostMapping("verify/register")
	public ResponseEntity<?> verifyAndRegister(@RequestBody UserVerifyRegisterRequest request) {
		return this.userResource.verifyAndRegister(request);
	}

	@PostMapping("login")
	public ResponseEntity<UserResponse> loginUser(@RequestBody UserLoginRequest loginRequest) {
		return this.userResource.loginUser(loginRequest);
	}

//	@GetMapping("deliveryperson/all")
//	public ResponseEntity<UserResponse> getAllDeliveryPersons() {
//		return this.userResource.getAllDeliveryPersons();
//	}

	@PostMapping("forget-password")
	public ResponseEntity<UserResponse> forgetPassword(@RequestBody UserLoginRequest request) {
		return this.userResource.forgetPassword(request);
	}
	
	@PostMapping("verify/forget")
	public ResponseEntity<?> verifyAndChangePassword(@RequestBody UserVerifyRegisterRequest request) {
		return this.userResource.verifyAndChangePassword(request);
	}	
}
