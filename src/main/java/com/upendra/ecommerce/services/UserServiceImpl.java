package com.upendra.ecommerce.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.upendra.ecommerce.dtos.UserRequest;
import com.upendra.ecommerce.dtos.UserResponse;
import com.upendra.ecommerce.entities.Role;
import com.upendra.ecommerce.entities.User;
import com.upendra.ecommerce.repositories.RoleRepository;
import com.upendra.ecommerce.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper mapper;

	@Override
	public UserResponse registerNewUser(UserRequest userRequest) {
		log.info(">> registerUser({})", userRequest);
		User user = mapper.map(userRequest, User.class);
		String password = passwordEncoder.encode(userRequest.getPassword());
		user.setUserName(userRequest.getEmail());
		Optional<Role> role = roleRepository.findByRoleName("USER");
		if (role.isEmpty()) {
			role = Optional.ofNullable(new Role());
			role.get().setRoleName("USER");
			roleRepository.save(role.get());
		}
		//user.getRole().add(role.get());
		user.setPassword(password);
		userRepository.save(user);
		return null;
	}

}
