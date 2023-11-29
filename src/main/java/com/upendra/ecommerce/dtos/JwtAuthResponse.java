package com.upendra.ecommerce.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JwtAuthResponse {

	private String token;

	private UserResponse user;

}
