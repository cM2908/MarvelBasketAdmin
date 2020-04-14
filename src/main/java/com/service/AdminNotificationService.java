package com.service;

import java.util.TreeSet;

import com.bean.AdminNotification;
import com.bean.Seller;

public interface AdminNotificationService {

	public TreeSet<AdminNotification> unreadNotifications();

	public void changeVisibility();

	public TreeSet<AdminNotification> allNotifications();

	public void addNotificationForSellerRequest(Seller seller);
	
	public void addNotificationForProductRequest(Object object);
}
