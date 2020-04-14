package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bean.Category;
import com.bean.Seller;
import com.bean.SellerCategory;
import com.dao.CategoryDao;
import com.dao.SellerDao;
import com.rowmapper.SellerCategoryResultExtractor;

@Service
public class SellerCategoryServiceImpl implements SellerCategoryService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private SellerDao sellerDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private SellerCategoryResultExtractor sellerCategoryResultExtractor;
	
	@Override
	//returns list of categories which is new for the seller
	public List<Category> findNewCategories(int sellerId) { 
		String query = "SELECT * FROM sellercategory WHERE seller_id = ?";
		Object[] parameters = new Object[] { sellerId };
		Map<Integer, List<Category>> sellerCategoryMap = jdbcTemplate.query(query,parameters,sellerCategoryResultExtractor);
		
		List<Category> categories = sellerCategoryMap.get(sellerId); 
		List<Category> allCategories = categoryDao.findAll(); 		
		allCategories.removeAll(categories);	
		return allCategories; 
	}
	
	@Override
	//returns list of categories provided by seller
	public SellerCategory findBySellerId(Integer sellerId) {
		String query = "SELECT * FROM sellercategory WHERE seller_id = ?";
		Object[] parameters = new Object[] { sellerId };
		Map<Integer, List<Category>> sellerCategoryMap = jdbcTemplate.query(query,parameters,sellerCategoryResultExtractor);
		
		Seller seller = sellerDao.findById(sellerId);
		List<Category> categories = sellerCategoryMap.get(sellerId);
		
		//System.out.println(sellerCategoryMap.get(sellerId));
		
		SellerCategory sellercategory = new SellerCategory();
		sellercategory.setSeller(seller);
		sellercategory.setCategory(categories);
		
		return sellercategory;
	}

	@Override
	//returns number of sellers for given category
	public Long sellersOfCategory(Integer categoryId) {
		
		String query = "SELECT COUNT(DISTINCT(seller_id)) FROM sellercategory WHERE category_id = ?";
		Object[] parameters = new Object[] { categoryId };
		Long sellers = jdbcTemplate.queryForObject(query,parameters,Long.class);
		return sellers;
	}
	
}
