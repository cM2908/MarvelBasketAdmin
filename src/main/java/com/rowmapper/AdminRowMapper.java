package com.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.bean.Admin;

@Component
public class AdminRowMapper implements RowMapper<Admin>{

	@Override
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		Admin admin = new Admin();
		admin.setAdminId(rs.getInt("admin_id"));
		admin.setAdminEmail(rs.getString("admin_email"));
		admin.setAdminPassword(rs.getString("admin_password"));
		admin.setAdminName(rs.getString("admin_name"));
		admin.setAdminContact(rs.getString("admin_contact"));
		return admin;
	}
}
