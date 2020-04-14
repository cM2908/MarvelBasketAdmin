package com.controller.web.admin;


import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bean.AdminNotification;
import com.service.AdminNotificationService;

@Controller
public class NotificationController {
	
	@Autowired
	AdminNotificationService adminNotificationService; 
	
	@RequestMapping(value = "/admin/allnotifications",method = RequestMethod.GET)
	public String allNotifications(Model model)
	{
		TreeSet<AdminNotification> notifications = adminNotificationService.allNotifications();
		model.addAttribute("notifications", notifications);
		model.addAttribute("notificationCount", notifications.size());
		return "notification";
	}
}
