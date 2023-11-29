package com.upendra.ecommerce.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JwtAuthRequest {

	private String userName;

	private String password;

}
