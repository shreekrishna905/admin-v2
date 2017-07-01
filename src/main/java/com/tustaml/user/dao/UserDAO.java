package com.tustaml.user.dao;

import com.tustaml.user.modal.User;

public interface UserDAO {

	public User findByUserName(String username);
}
