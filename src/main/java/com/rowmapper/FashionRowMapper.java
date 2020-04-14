package com.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.bean.Fashion;
import com.bean.Seller;
import com.dao.SellerDao;

@Component
public class FashionRowMapper implements RowMapper<Fashion> {

	@Autowired
	SellerDao sellerDao;
	
	@Override
	public Fashion mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Fashion fashion = new Fashion();
		
		fashion.setFashionId(rs.getInt("fashion_id"));
		fashion.setFashionType(rs.getString("fashion_type"));
		fashion.setFashionBuyerType(rs.getString("fashion_buyertype"));
		fashion.setFashionName(rs.getString("fashion_name"));
		fashion.setFashionBrand(rs.getString("fashion_brand"));
		fashion.setFashionColor(rs.getString("fashion_color"));
		fashion.setFashionDescription(rs.getString("fashion_description"));
		fashion.setFashionImages(rs.getString("fashion_images"));
		fashion.setFashionStatus(rs.getString("fashion_status"));
		
		int sellerId = rs.getInt("seller_id");
		Seller seller = sellerDao.findById(sellerId);
		fashion.setSeller(seller);
		
		return fashion;
	}

}
