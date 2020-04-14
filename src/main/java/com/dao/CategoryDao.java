package com.dao;

import java.util.Collection;
import java.util.List;

import com.bean.Category;

public interface CategoryDao {
	
	public void save(Category category);
	public void update(Category category);
	public void updateProperty(String propName , Object propValue , Integer categoryId);
	public void delete(Category category);
	public void deleteById(Integer categoryId);
	public List<Category> findAll();
	public Category findById(Integer categoryId);
	public List<Category> findByProperty(String propName , Object propValue);
}
