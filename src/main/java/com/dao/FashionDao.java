package com.dao;

import java.util.List;

import com.bean.Fashion;

public interface FashionDao {
	
	public void save(Fashion fashion);
	public void update(Fashion fashion);
	public void delete(Fashion fashion);
	public void deleteById(Integer fashionId);
	public List<Fashion> findAll();
	public Fashion findById(Integer fashionId);
	public List<Fashion> findByProperty(String propName , Object propValue);
	
}
