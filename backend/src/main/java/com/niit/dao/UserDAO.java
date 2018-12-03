package com.niit.dao;

import java.util.List;

import com.niit.model.User;

public interface UserDAO 
{
	void registerUser(User user);
	boolean isEmailUnique(String email);
	User login(User user);//i/p email and password ,o/p values for all properties if valid input,else null
	//valid credentials  -o/p user object
	//invalid credentials  - o/p null object
	void updateUser(User user);
	List<User> findAllUsers();
	User getUser(String email);
	User findUserByEmail(String email);
}
