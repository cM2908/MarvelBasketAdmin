package com.dao;

import java.util.List;
import com.bean.Admin;

public interface AdminDao {
	
	public void save(Admin admin);
	public void update(Admin admin);
	public void delete(Admin admin);
	public void deleteById(Integer adminId);
	public List<Admin> findAll();
	public Admin findById(Integer adminId);
	public List<Admin> findByProperty(String propName , Object propValue);
	
}
