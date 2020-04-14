package com.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.bean.Category;

@Component
public class CategoryRowMapper implements RowMapper<Category>{
	
	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		Category category = new Category();
		category.setcategoryId(rs.getInt("category_id"));
		category.setcategoryName(rs.getString("category_name"));
		return category;
	}
	
}
