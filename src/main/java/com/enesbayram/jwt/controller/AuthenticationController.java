package com.enesbayram.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enesbayram.jwt.api.model.LoginRequest;
import com.enesbayram.jwt.api.model.UserDto;
import com.enesbayram.jwt.api.model.UserResponse;
import com.enesbayram.jwt.service.AuthenticationService;

@RestController
@RequestMapping(path = "/login")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping(path = "/save")
	public ResponseEntity<UserResponse> save(@RequestBody UserDto userDto) {
		return ResponseEntity.ok(authenticationService.save(userDto));
	}
	
	@PostMapping(path = "/access-token")
	public ResponseEntity<UserResponse> authenticate(@RequestBody LoginRequest loginRequest){
		return ResponseEntity.ok(authenticationService.authenticate(loginRequest));
	}

}
