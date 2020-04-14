package com.dao;

import java.util.List;

import com.bean.SubFashion;

public interface SubFashionDao {
	
	public void save(SubFashion subFashion,Integer fashionId);
	public void update(SubFashion subFashion);
	public void delete(SubFashion subFashion);
	public void deleteById(Integer subFashionId);
	public List<SubFashion> findAll();
	public SubFashion findById(Integer subFashionId);
	public List<SubFashion> findByProperty(String propName , Object propValue);
	
}
