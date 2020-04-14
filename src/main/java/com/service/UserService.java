package com.service;

import java.sql.SQLException;

import org.springframework.dao.DuplicateKeyException;

import com.bean.Login;
import com.bean.Response;
import com.bean.User;
import com.bean.UserEvent;

public interface UserService {
	
	public Response<User> registerUser(UserEvent userEvent);

	public void updateUserProfile(User user) throws DuplicateKeyException, SQLException;

	public User validate(Login login);
	
}
