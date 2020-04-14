package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bean.Admin;
import com.bean.Response;
import com.dao.AdminDao;
import com.rowmapper.AdminRowMapper;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Check Admin credentials for login
	 * 
	 * @param email    admin email for authentication
	 * @param password admin password for authentication
	 * @return {@link Response} with code - '1001' for 'Admin data found' , '1002'
	 *         for 'Invalid email or password'
	 * @see Response
	 */
	@Override
	public Response<Admin> authenticateAdmin(String email, String password) {
		String query = "SELECT * FROM admin WHERE admin_email = ? and admin_password = ?";
		Object[] parameters = new Object[] { email, password };
		Admin admin = null;
		try {
			admin = jdbcTemplate.queryForObject(query, parameters, new AdminRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return new Response<Admin>(admin, "Invalid email or password", 1002);
		}
		return new Response<Admin>(admin, "Admin data found", 10001);
	}

	/**
	 * Checks whether the email is unique or not while updating seller profile
	 * 
	 * @param seller seller object having new email
	 * @return true if this email is unique; false otherwise
	 */
	@Override
	public boolean isNewEmailUnique(Admin admin) {
		String query = "SELECT * FROM admin WHERE admin_email = ? and admin_id != ?";
		Object[] parameters = new Object[] { admin.getAdminEmail(), admin.getAdminId() };
		try {
			jdbcTemplate.queryForObject(query, parameters, new AdminRowMapper());
			return false;
		} catch (EmptyResultDataAccessException ex) {
			return true;
		}
	}

	/**
	 * Update the admin profile
	 * 
	 * @param admin the admin which we have to update
	 * @return {@link Response} with code - '1007' for 'Successful admin update' ,
	 *         '1006' for 'Email taken error'
	 * @see Response
	 */
	@Override
	public Response<Admin> updateAdminProfile(Admin admin) {
		if (isNewEmailUnique(admin)) {
			adminDao.update(admin);
			return new Response<Admin>(admin, "Admin data updated succesfully", 1007);
		} else {
			return new Response<Admin>(admin, "Email already taken", 1006);
		}
	}
}
