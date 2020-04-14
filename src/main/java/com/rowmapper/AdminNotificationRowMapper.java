package com.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.bean.AdminNotification;
import com.bean.Seller;
import com.dao.SellerDao;

@Component
public class AdminNotificationRowMapper implements RowMapper<AdminNotification>{
	
	@Autowired
	SellerDao sellerDao;
	
	@Override
	public AdminNotification mapRow(ResultSet rs, int rowNum) throws SQLException {
		AdminNotification adminNotification = new AdminNotification();
		
		adminNotification.setNotificationId(rs.getInt("notification_id"));
		adminNotification.setTime(rs.getTimestamp("time"));
		adminNotification.setVisibility(rs.getInt("visibility"));
		adminNotification.setDescription(rs.getString("description"));
		
		int from_id =rs.getInt("from_id");
		Seller seller = sellerDao.findById(from_id);
		adminNotification.setFrom(seller);
		
		return adminNotification;
	}
}
