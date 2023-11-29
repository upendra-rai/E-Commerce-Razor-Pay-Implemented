package com.upendra.ecommerce.services;

import com.upendra.ecommerce.dtos.UserRequest;
import com.upendra.ecommerce.dtos.UserResponse;

public interface UserService {

	UserResponse registerNewUser(UserRequest userRequest);

}
