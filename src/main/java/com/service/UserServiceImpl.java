package com.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bean.Event;
import com.bean.Login;
import com.bean.Response;
import com.bean.User;
import com.bean.UserEvent;
import com.dao.EventDao;
import com.dao.UserDao;
import com.rowmapper.UserRowMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private EventDao eventDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Response<User> registerUser(UserEvent userEvent) {
		List<User> user = userDao.findByProperty("email", userEvent.getUser().getEmail());
		if (user.size() > 0) {
			return new Response<User>(null, "Email exist", 2032);
		} else {
			userDao.save(userEvent.getUser());
			List<User> dbUser = userDao.findByProperty("email", userEvent.getUser().getEmail());
			Event event = userEvent.getEvents().get(0);
			event.setUserId(dbUser.get(0).getUserId());
			eventDao.save(event);
			return new Response<User>(dbUser.get(0), "Success", 2031);
		}
	}

	public void updateUserProfile(User user) throws DuplicateKeyException, SQLException {

	}

	@Override
	public User validate(Login login) {
		String query = "SELECT * FROM user WHERE email=? and password=?";
		Object parameters[] = new Object[] { login.getEmail(), login.getPassword() };
		List<User> users = jdbcTemplate.query(query, parameters, new UserRowMapper());
		return users.size() > 0 ? users.get(0) : null;
	}

}
