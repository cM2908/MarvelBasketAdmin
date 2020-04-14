package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.General;
import com.rowmapper.GeneralRowMapper;

@Repository
public class GeneralDaoImpl implements GeneralDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private GeneralRowMapper generalRowMapper;

	@Override
	public void save(General general) {
		String query = "INSERT INTO general (product_type , product_name , product_brand , product_description , product_price , product_stock ,  product_images , seller_id , product_status) VALUES (?,?,?,?,?,?,?,?,?)";
		Object[] parameters = new Object[] { general.getProductType(), general.getProductName(),
				general.getProductBrand(), general.getProductDescription(), general.getProductPrice(),
				general.getProductStock(), general.getProductImages(), general.getSeller().getSellerId(),
				general.getProductStatus() };
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public void update(General general) {
		String query = "UPDATE general SET product_type=? , product_name=? , product_brand=? , product_description=? , product_price=? , product_stock=? , product_images=? , seller_id=? , product_status=? WHERE product_id=?";
		Object[] parameters = new Object[] { general.getProductType(), general.getProductName(),
				general.getProductBrand(), general.getProductDescription(), general.getProductPrice(),
				general.getProductStock(), general.getProductImages(), general.getSeller().getSellerId(),
				general.getProductStatus(), general.getProductId() };
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public void delete(General general) {
		this.deleteById(general.getProductId());
	}

	@Override
	public void deleteById(Integer productId) {
		String query = "DELETE FROM general WHERE product_id = ?";
		jdbcTemplate.update(query, productId);
	}

	@Override
	public List<General> findAll() {
		String query = "SELECT * FROM general";
		List<General> products = jdbcTemplate.query(query, generalRowMapper);
		return products;
	}

	@Override
	public General findById(Integer productId) {
		String query = "SELECT * FROM general WHERE product_id = ?";
		Object[] parameters = new Object[] { productId };
		General product = jdbcTemplate.queryForObject(query, parameters, generalRowMapper);
		return product;
	}

	@Override
	public List<General> findByProperty(String propName, Object propValue) {
		String query = "SELECT * FROM general WHERE " + propName + " = ?";
		Object[] parameters = new Object[] { propValue };
		List<General> products = jdbcTemplate.query(query, parameters, generalRowMapper);
		return products;
	}

}
