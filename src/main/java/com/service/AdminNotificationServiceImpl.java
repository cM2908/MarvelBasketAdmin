package com.service;

import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.AdminNotification;
import com.bean.Book;
import com.bean.Electronic;
import com.bean.Fashion;
import com.bean.General;
import com.bean.Seller;
import com.comparator.AdminNotificationComparator;
import com.dao.AdminNotificationDao;

@Service
public class AdminNotificationServiceImpl implements AdminNotificationService {

	@Autowired
	AdminNotificationDao adminNotificationDao;
	@Autowired
	AdminNotificationComparator adminNotificationComparator;

	@Override
	public TreeSet<AdminNotification> unreadNotifications() {
		List<AdminNotification> notificationList = adminNotificationDao.findByProperty("visibility", 0);
		TreeSet<AdminNotification> notificationSet = new TreeSet<>(adminNotificationComparator);
		notificationSet.addAll(notificationList);
		return notificationSet;
	}

	@Override
	public void changeVisibility() {
		List<AdminNotification> notificationList = adminNotificationDao.findAll();
		for (AdminNotification notification : notificationList) {
			notification.setVisibility(1);
			adminNotificationDao.update(notification);
		}
	}

	@Override
	public TreeSet<AdminNotification> allNotifications() {
		List<AdminNotification> notificationList = adminNotificationDao.findAll();
		TreeSet<AdminNotification> notificationSet = new TreeSet<>(adminNotificationComparator);
		notificationSet.addAll(notificationList);
		return notificationSet;
	}

	@Override
	public void addNotificationForSellerRequest(Seller seller) {
		AdminNotification adminNotification = new AdminNotification();
		adminNotification.setFrom(seller);
		adminNotification.setDescription("want to be part of Marvel Basket seller community");
		adminNotification.setVisibility(0);
		adminNotificationDao.save(adminNotification);
	}

	@Override
	public void addNotificationForProductRequest(Object object) {
		AdminNotification adminNotification = new AdminNotification();
		if (object instanceof Electronic) {
			Electronic electronic = (Electronic) object;
			adminNotification.setFrom(electronic.getSeller());
			adminNotification.setDescription(
					"want to add electronic product \"" + electronic.getElectronicName() + "\" in Marvel Basket shop");
		} else if (object instanceof Fashion) {
			Fashion fashion = (Fashion) object;
			adminNotification.setFrom(fashion.getSeller());
			adminNotification
					.setDescription("want to add fashion product \"" + fashion.getFashionName() + "\" in Marvel Basket shop");
		} else if (object instanceof Book) {
			Book book = (Book) object;
			adminNotification.setFrom(book.getSeller());
			adminNotification.setDescription("want to add book \"" + book.getBookName() + "\" in Marvel Basket shop");
		} else if (object instanceof General) {
			General general = (General) object;
			adminNotification.setFrom(general.getSeller());
			adminNotification
					.setDescription("want to add general product \"" + general.getProductName() + "\" in Marvel Basket shop");
		}
		adminNotification.setVisibility(0);
		adminNotificationDao.save(adminNotification);
	}
}
