package com.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.bean.Seller;

@Component
public class SellerRowMapper implements RowMapper<Seller>{

	@Override
	public Seller mapRow(ResultSet rs, int rowNum) throws SQLException {
		Seller seller = new Seller();
		seller.setSellerId(rs.getInt("seller_id"));
		seller.setSellerName(rs.getString("seller_name"));
		seller.setSellerEmail(rs.getString("seller_email"));
		seller.setSellerContact(rs.getString("seller_contact"));
		seller.setSellerPassword(rs.getString("seller_password"));
		seller.setShopName(rs.getString("shop_name"));
		seller.setShopAddress(rs.getString("shop_address"));
		seller.setSellerStatus(rs.getString("seller_status"));
		return seller;
	}
	
}
