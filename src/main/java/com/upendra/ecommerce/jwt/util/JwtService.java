package com.upendra.ecommerce.jwt.util;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.upendra.ecommerce.dtos.JwtAuthRequest;
import com.upendra.ecommerce.dtos.JwtAuthResponse;
import com.upendra.ecommerce.dtos.UserResponse;
import com.upendra.ecommerce.entities.User;
import com.upendra.ecommerce.repositories.UserRepository;

@Service
public class JwtService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private AuthenticationManager authenticationManager;

	public JwtAuthResponse createJwtToken(JwtAuthRequest authRequest) throws Exception {
		String userName = authRequest.getUserName();
		String password = authRequest.getPassword();
		authenticate(userName, password);
		final UserDetails userDetails = loadUserByUsername(userName);
		Optional<User> user = userRepository.findByUserName(userName);
		String newToken = jwtUtil.generateToken(userDetails);
		JwtAuthResponse jas = new JwtAuthResponse();
		UserResponse ua = mapper.map(user.get(), UserResponse.class);
		jas.setToken(newToken);
		jas.setUser(ua);
		return jas;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(username);
		if (user.isPresent()) {
			return new org.springframework.security.core.userdetails.User(user.get().getUserName(),
					user.get().getPassword(), getAuthorities(user.get()));
		} else {
			throw new UsernameNotFoundException("UserName Not Found "+username);
		}
	}

	private void authenticate(String userName, String Password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, Password));
		} catch (DisabledException e) {
			throw new Exception("User is Disabled");
		} catch (BadCredentialsException e) {
			throw new Exception("BadCredentials Exception ");
		}
	}

	private Set getAuthorities(User user) {
		Set authorities = new HashSet<>();
		user.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;

	}

}
