package com.tustaml.user.dao;

import java.util.List;

import com.tustaml.user.modal.User;

public interface UserDAO {

	public List<User> findAll();
	public User findByUserName(String username);
	public void saveOrUpdate(User user);
	public boolean isUserExist(User user);
}
