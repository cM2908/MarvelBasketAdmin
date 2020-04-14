package com.dao;

import java.util.List;

import com.bean.User;

public interface UserDao {
	
	public void save(User user);
	public void update(User user);
	public void delete(User user);
	public void deleteById(Integer userId);
	public List<User> findAll();
	public User findById(Integer userId);
	public List<User> findByProperty(String propName , Object propValue);
	
}
