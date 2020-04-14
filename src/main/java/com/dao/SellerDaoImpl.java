package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.Seller;
import com.rowmapper.SellerRowMapper;

@Repository
public class SellerDaoImpl implements SellerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(Seller seller) {
		String query = "INSERT INTO seller (seller_name,seller_email,seller_contact,seller_password,shop_name,shop_address,seller_status) VALUES (?,?,?,?,?,?,?)";
		Object[] parameters = new Object[] { seller.getSellerName(), seller.getSellerEmail(), seller.getSellerContact(),
				seller.getSellerPassword(), seller.getShopName(), seller.getShopAddress(), seller.getSellerStatus() };
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public void update(Seller seller) {
		String query = "UPDATE seller SET seller_name=? , seller_email=? , seller_contact=? , seller_password=? , shop_name=? , shop_address=? , seller_status=? WHERE seller_id=?";
		Object[] parameters = new Object[] { seller.getSellerName(), seller.getSellerEmail(), seller.getSellerContact(),
				seller.getSellerPassword(), seller.getShopName(), seller.getShopAddress(), seller.getSellerStatus(),
				seller.getSellerId() };
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public void updateProperty(String propName, Object propValue, Integer sellerId) {
		String query = "UPDATE seller SET " + propName + "=?  WHERE seller_id=?";
		Object[] parameters = new Object[] {propValue, sellerId};
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public void delete(Seller seller) {
		this.deleteById(seller.getSellerId());
	}

	@Override
	public void deleteById(Integer sellerId) {
		String query = "DELETE FROM seller WHERE seller_id = ?";
		jdbcTemplate.update(query, sellerId);
	}

	@Override
	public List<Seller> findAll() {
		String query = "SELECT * FROM seller";
		List<Seller> sellers = jdbcTemplate.query(query, new SellerRowMapper());
		return sellers;
	}

	@Override
	public Seller findById(Integer sellerId) {
		String query = "SELECT * FROM seller WHERE seller_id = ?";
		Object[] parameters = new Object[] { sellerId };
		Seller seller = jdbcTemplate.queryForObject(query, parameters, new SellerRowMapper());
		return seller;
	}

	@Override
	public List<Seller> findByProperty(String propName, Object propValue) {
		String query = "SELECT * FROM seller WHERE " + propName + " = ?";
		Object[] parameters = new Object[] { propValue };
		List<Seller> sellers = jdbcTemplate.query(query, parameters, new SellerRowMapper());
		return sellers;
	}

}
