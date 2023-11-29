package com.upendra.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upendra.ecommerce.dtos.RoleRequest;
import com.upendra.ecommerce.entities.Role;
import com.upendra.ecommerce.services.RoleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class ProfileController {

	@Autowired
	private RoleService roleService;

	@GetMapping
	public ResponseEntity<List<Role>> getRoles() {
		log.info(">> getRoles()");
		return ResponseEntity.ok(roleService.getRoles());
	}

	@PostMapping
	public ResponseEntity<Void> createComment(@RequestBody RoleRequest roleRequest) {
		log.info(">> createComment({})", roleRequest);
		roleService.createRole(roleRequest);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
