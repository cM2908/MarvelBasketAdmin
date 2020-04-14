package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.Admin;
import com.rowmapper.AdminRowMapper;

@Repository
public class AdminDaoImpl implements AdminDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(Admin admin) throws DuplicateKeyException {
		String query = "INSERT INTO admin (admin_email,admin_password,admin_name,admin_contact) VALUES (?,?,?,?)";
		Object[] parameters = new Object[]{admin.getAdminEmail().trim(),admin.getAdminPassword().trim(),admin.getAdminName().trim(),admin.getAdminContact().trim()};
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public void update(Admin admin){
		String query = "UPDATE admin SET admin_password=? , admin_name=? , admin_contact=? , admin_email=? WHERE admin_id=?";
		Object[] parameters = new Object[]{admin.getAdminPassword().trim(),admin.getAdminName().trim(),admin.getAdminContact().trim(),admin.getAdminEmail().trim(),admin.getAdminId()};
		jdbcTemplate.update(query, parameters);
	}

	@Override
	public void delete(Admin admin) {
		this.deleteById(admin.getAdminId());
	}

	@Override
	public void deleteById(Integer adminId) {
		String query = "DELETE FROM admin WHERE admin_id = ?";
		jdbcTemplate.update(query,adminId);
	}

	@Override
	public List<Admin> findAll() {
		String query = "SELECT * FROM admin";
		List<Admin> admins = jdbcTemplate.query(query, new AdminRowMapper());
		return admins;
	}

	@Override
	public Admin findById(Integer adminId) {
		String query = "SELECT * FROM admin WHERE admin_id = ?";
		Object[] parameters = new Object[] {adminId};
		Admin admin = jdbcTemplate.queryForObject(query,parameters, new AdminRowMapper());
		return admin;
	}

	@Override
	public List<Admin> findByProperty(String propName, Object propValue) {
		String query = "SELECT * FROM admin WHERE " + propName + " = ?";
		Object[] parameters = new Object[] {propValue};
		List<Admin> admins = jdbcTemplate.query(query, parameters, new AdminRowMapper());
		return admins;
	}
}
