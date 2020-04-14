package com.controller.rest;

import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bean.AdminNotification;
import com.service.AdminNotificationService;

@RestController
public class AdminNotificationRestController {

	@Autowired
	AdminNotificationService adminNotificationService;

	@RequestMapping(value = "/admin/notifications", method = RequestMethod.GET)
	public TreeSet<AdminNotification> notifications() {
		TreeSet<AdminNotification> set = adminNotificationService.unreadNotifications();
		return set;
	}

	@RequestMapping(value = "/admin/readnotifications", method = RequestMethod.GET)
	public void readNotifications() {
		adminNotificationService.changeVisibility();
	}

}
