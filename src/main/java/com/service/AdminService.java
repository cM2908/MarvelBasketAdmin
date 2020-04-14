package com.service;


import com.bean.Admin;
import com.bean.Response;

public interface AdminService {
	public Response<Admin> authenticateAdmin(String email, String password);
	public boolean isNewEmailUnique(Admin admin);
	public Response<Admin> updateAdminProfile(Admin admin);
}
