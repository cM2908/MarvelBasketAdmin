package com.dao;

import java.util.List;

import com.bean.Electronic;


public interface ElectronicDao {
	
	public void save(Electronic electronic);
	public void update(Electronic electronic);
	public void delete(Electronic electronic);
	public void deleteById(Integer electronicId);
	public List<Electronic> findAll();
	public Electronic findById(Integer electronicId);
	public List<Electronic> findByProperty(String propName , Object propValue);
	
}
