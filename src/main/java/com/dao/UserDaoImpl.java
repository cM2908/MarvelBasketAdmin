package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.User;
import com.rowmapper.UserRowMapper;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(User user) {
		String query = "INSERT INTO user (first_name,last_name, email , password , address , contact) VALUES (?,?,?,?,?,?)";
		Object[] parameters = new Object[]{ user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getPassword(), user.getAddress(), user.getContact()};
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public void update(User user) {
		String query = "UPDATE user SET first_name=? , last_name=? , email=? , password=? , address=? , contact=? WHERE user_id=?";
		Object[] parameters = new Object[]{ user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getPassword(), user.getAddress(), user.getContact()};
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public void delete(User user) {
		this.deleteById(user.getUserId());
	}

	@Override
	public void deleteById(Integer userId) {
		String query = "DELETE FROM user WHERE user_id = ?";
		jdbcTemplate.update(query,userId);
	}

	@Override
	public List<User> findAll() {
		String query = "SELECT * FROM user";
		List<User> users = jdbcTemplate.query(query, new UserRowMapper());
		return users;
	}

	@Override
	public User findById(Integer userId) {
		String query = "SELECT * FROM user WHERE user_id = ?";
		Object[] parameters = new Object[] {userId};
		User user = jdbcTemplate.queryForObject(query,parameters, new UserRowMapper());
		return user;
	}

	@Override
	public List<User> findByProperty(String propName, Object propValue) {
		String query = "SELECT * FROM user WHERE " + propName + " = ?";
		Object[] parameters = new Object[] {propValue};
		List<User> users = jdbcTemplate.query(query, parameters, new UserRowMapper());
		return users;
	}
}
