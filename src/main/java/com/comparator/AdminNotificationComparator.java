package com.comparator;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.bean.AdminNotification;

@Component
public class AdminNotificationComparator implements Comparator<AdminNotification> {

	@Override
	public int compare(AdminNotification o1, AdminNotification o2) {
		if (o1.getTime().before(o2.getTime())) {
			return 1;
		} else {
			return -1;
		}
	}
}
