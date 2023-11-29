package com.upendra.ecommerce.services;

import java.util.List;

import com.upendra.ecommerce.dtos.RoleRequest;
import com.upendra.ecommerce.entities.Role;

public interface RoleService {

	void createRole(RoleRequest roleRequest);

	List<Role> getRoles();

}
