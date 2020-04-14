package com.dao;

import java.util.List;

import com.bean.AdminNotification;

public interface AdminNotificationDao {
	
	public void save(AdminNotification adminNotification);
	public void update(AdminNotification adminNotification);
	public void delete(AdminNotification adminNotification);
	public void deleteById(Integer adminNotificationId);
	public List<AdminNotification> findAll();
	public AdminNotification findById(Integer adminNotificationId);
	public List<AdminNotification> findByProperty(String propName , Object propValue);

}
