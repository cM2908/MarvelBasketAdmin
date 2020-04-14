package com.dao;

import java.util.List;

import com.bean.General;

public interface GeneralDao {
	
	public void save(General general);
	public void update(General general);
	public void delete(General general);
	public void deleteById(Integer generalId);
	public List<General> findAll();
	public General findById(Integer generalId);
	public List<General> findByProperty(String propName , Object propValue);
	
}
