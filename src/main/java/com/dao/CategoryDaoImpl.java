package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.Category;
import com.rowmapper.CategoryRowMapper;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	CategoryRowMapper categoryRowMapper;
	
	@Override
	public void save(Category category) {
		String query = "INSERT INTO category (category_name) VALUES (?)";
		Object[] parameters = new Object[] {category.getcategoryName()};
		jdbcTemplate.update(query,parameters);
	}

	@Override
	public void update(Category category) {
		String query = "UPDATE category SET category_name=? WHERE category_id=?";
		Object[] parameters = new Object[] {category.getcategoryName(),category.getcategoryId()};
		jdbcTemplate.update(query,parameters);
	}

	@Override
	public void updateProperty(String propName, Object propValue, Integer categoryId) {
		String query = "UPDATE category SET ?=? WHERE category_id=?";
		Object[] parameters = new Object[] {propName,propValue,categoryId};
		jdbcTemplate.update(query,parameters);
	}

	@Override
	public void delete(Category category) {
		this.deleteById(category.getcategoryId());
	}

	@Override
	public void deleteById(Integer categoryId) {
		String query = "DELETE FROM category WHERE category_id = ?";
		jdbcTemplate.update(query, categoryId);
	}

	@Override
	public List<Category> findAll() {
		String query = "SELECT * FROM category";
		List<Category> categories = jdbcTemplate.query(query,categoryRowMapper);
		return categories;
	}

	@Override
	public Category findById(Integer categoryId) {
		String query = "SELECT * FROM category WHERE category_id = ?";
		Object[] parameters = new Object[] {categoryId};
		Category category = jdbcTemplate.queryForObject(query, parameters,categoryRowMapper);
		return category;
	}

	@Override
	public List<Category> findByProperty(String propName, Object propValue) {
		String query = "SELECT * FROM category WHERE "+propName+" = ?";
		Object[] parameters = new Object[] { propValue };
		List<Category> categories = jdbcTemplate.query(query, parameters, categoryRowMapper);
		return categories;
	}

	

}
