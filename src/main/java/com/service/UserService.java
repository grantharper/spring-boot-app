package com.service;

import com.domain.User;

public interface UserService extends CRUDService<User> {

	User findByUsername(String username);
}
