package com.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.bean.Electronic;
import com.bean.Seller;
import com.dao.SellerDao;

@Component
public class ElectronicRowMapper implements RowMapper<Electronic> {

	@Autowired
	SellerDao sellerDao;
	
	@Override
	public Electronic mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Electronic electronic = new Electronic();
		
		electronic.setElectronicId(rs.getInt("electronic_id"));
		electronic.setElectronicType(rs.getString("electronic_type"));
		electronic.setElectronicName(rs.getString("electronic_name"));
		electronic.setElectronicBrand(rs.getString("electronic_brand"));
		electronic.setElectronicDescription(rs.getString("electronic_description"));
		electronic.setElectronicPrice(rs.getDouble("electronic_price"));
		electronic.setElectronicStock(rs.getLong("electronic_stock"));
		electronic.setElectronicSize(rs.getString("electronic_size"));
		electronic.setElectronicColor(rs.getString("electronic_color"));
		electronic.setElectronicWeight(rs.getDouble("electronic_weight"));
		electronic.setElectronicSpecification(rs.getString("electronic_specification"));
		electronic.setElectronicImages(rs.getString("electronic_images"));
		electronic.setElectronicStatus(rs.getString("electronic_status"));
		
		int sellerId = rs.getInt("seller_id");
		Seller seller = sellerDao.findById(sellerId);
		electronic.setSeller(seller);
		
		return electronic;
	}

}
