package com.dao;

import java.util.List;

import com.bean.Seller;

public interface SellerDao {
	
	public void save(Seller seller);
	public void update(Seller seller);
	public void updateProperty(String propName , Object propValue , Integer sellerId);
	public void delete(Seller seller);
	public void deleteById(Integer sellerId);
	public List<Seller> findAll();
	public Seller findById(Integer sellerId);
	public List<Seller> findByProperty(String propName , Object propValue);
	
}
