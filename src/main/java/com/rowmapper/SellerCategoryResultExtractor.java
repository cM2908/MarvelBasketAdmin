package com.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.bean.Category;
import com.dao.CategoryDao;
import com.dao.SellerDao;

@Component
public class SellerCategoryResultExtractor implements ResultSetExtractor<Map<Integer, List<Category>>>{
	
	@Autowired
	SellerDao sellerDao;
	@Autowired
	CategoryDao categoryDao;

	@Override
	public Map<Integer, List<Category>> extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		Map<Integer, List<Category>> sellerCategoryMap = new HashMap<>();

		while (rs.next()) {
			
			int sellerId = rs.getInt("seller_id");
			System.out.println("sellerId : "+sellerId);
			int categoryId = rs.getInt("category_id");
			System.out.println("categoryId : "+categoryId);
			Category category = categoryDao.findById(categoryId); 
			
			List<Category> categories = sellerCategoryMap.get(sellerId);
			System.out.println("categories : "+categories);
			if (categories == null) {
				System.out.println("in if");
				List<Category> newCategory = new ArrayList<>();
				System.out.println("newCategory : "+newCategory);
				newCategory.add(category);
				sellerCategoryMap.put(sellerId,newCategory);
			} else {
				System.out.println("in else");
				categories.add(category);
			}
		}
		return sellerCategoryMap;
	}
}
