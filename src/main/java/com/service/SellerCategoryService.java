package com.service;

import java.util.List;

import com.bean.Category;
import com.bean.SellerCategory;

public interface SellerCategoryService {

	public List<Category> findNewCategories(int sellerId);
	public SellerCategory findBySellerId(Integer sellerId);
	public Long sellersOfCategory(Integer categoryId);
}
