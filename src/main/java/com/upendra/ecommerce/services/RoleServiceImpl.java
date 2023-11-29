package com.upendra.ecommerce.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upendra.ecommerce.dtos.RoleRequest;
import com.upendra.ecommerce.entities.Role;
import com.upendra.ecommerce.repositories.RoleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<Role> getRoles() {
		log.info(">> getRoles()");
		return roleRepository.findAll();
	}

	@Override
	public void createRole(RoleRequest roleRequest) {
		log.info(">> createComment({})", roleRequest);
		Role role = mapper.map(roleRequest, Role.class);
		roleRepository.save(role);

	}

}
