package com.upendra.ecommerce.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/test")
public class TestContoller {

	@GetMapping("/user")
	@PreAuthorize("hasRole('" + "USER" + "')" + " && hasRole('" + "ADMIN" + "')")
	public String normalUser() {
		log.info(">> normalUser({})");
		return "This Controller Only for Normal User";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String admin() {
		log.info(">> admin({})");
		return "This Controller Only for admin User";
	}

}
