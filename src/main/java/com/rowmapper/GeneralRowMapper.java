package com.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.bean.General;
import com.bean.Seller;
import com.dao.SellerDao;

@Component
public class GeneralRowMapper implements RowMapper<General> {

	@Autowired
	SellerDao sellerDao;
	
	@Override
	public General mapRow(ResultSet rs, int rowNum) throws SQLException {
		General general = new General();
		
		general.setProductId(rs.getInt("product_id"));
		general.setProductType(rs.getString("product_type"));
		general.setProductName(rs.getString("product_name"));
		general.setProductBrand(rs.getString("product_brand"));
		general.setProductDescription(rs.getString("product_description"));
		general.setProductPrice(rs.getDouble("product_price"));
		general.setProductStock(rs.getLong("product_stock"));
		general.setProductImages(rs.getString("product_images"));
		general.setProductStatus(rs.getString("product_status"));
		
		int sellerId = rs.getInt("seller_id");
		Seller seller = sellerDao.findById(sellerId);
		general.setSeller(seller);
		
		return general;
	}

}
