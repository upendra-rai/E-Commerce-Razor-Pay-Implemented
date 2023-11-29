package com.upendra.ecommerce.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upendra.ecommerce.dtos.JwtAuthRequest;
import com.upendra.ecommerce.dtos.JwtAuthResponse;
import com.upendra.ecommerce.dtos.UserRequest;
import com.upendra.ecommerce.dtos.UserResponse;
import com.upendra.ecommerce.jwt.util.JwtService;
import com.upendra.ecommerce.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthContoller {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/authenticate")
	public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtAuthRequest request) throws Exception {
		log.info(">> createToken({})",request);
		return  ResponseEntity.ok(jwtService.createJwtToken(request));
	}

	@PostMapping("/register")
	public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest userRequest) {
		log.info(">> registerUser({})", userRequest);
		UserResponse registeredUser = userService.registerNewUser(userRequest);
		return new ResponseEntity<UserResponse>(registeredUser, HttpStatus.CREATED);
	}

}
