package com.upendra.ecommerce.dtos;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserResponse {

	private String uuid;

	private String userName;

	private String firstName;

	private String lastName;

	private Set<RoleResponse> role;

}
