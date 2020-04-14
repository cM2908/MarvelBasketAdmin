package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.Category;
import com.bean.SellerCategory;

@Repository
public class SellerCategoryDaoImpl implements SellerCategoryDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void save(SellerCategory sellerCategory) {
		for(Category category : sellerCategory.getCategory())
		{
			String query = "INSERT INTO sellercategory (seller_id,category_id) VALUES (?,?)";
			Object[] parameters = new Object[] { sellerCategory.getSeller().getSellerId(),
				category.getcategoryId() };
			jdbcTemplate.update(query, parameters);
		}
	}

}
