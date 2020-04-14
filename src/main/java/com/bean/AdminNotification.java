package com.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class AdminNotification {
	
	private int notificationId;
	private Seller from;
	private Timestamp time;
	private int visibility;
	private String description;
	private ArrayList<Object> notification_time;
	
	public int getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}
	public Seller getFrom() {
		return from;
	}
	public void setFrom(Seller from) {
		this.from = from;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getVisibility() {
		return visibility;
	}
	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ArrayList<Object> getNotification_time() {
		notification_time = new ArrayList<Object>();
		
		Date today = new Date();
		long todayMili = today.getTime();
		long notificationMili = time.getTime();
		long difference = todayMili-notificationMili;
		
		int days=0;
		int hours=0;
		int minutes=0;
		int seconds=0;
		
		if(difference>=84600000)
		{
			days=(int)difference/84600000;
			notification_time.add("Days");
			notification_time.add(days);
		}
		else if(difference>=3600000 && difference<84600000)
		{
			hours=(int)difference/3600000;
			notification_time.add("Hours");
			notification_time.add(hours);
		}
		else if(difference>=60000 && difference<3600000)
		{
			minutes=(int)difference/60000;
			notification_time.add("Minutes");
			notification_time.add(minutes);
		}
		else if(difference>=1000 && difference<60000)
		{
			seconds=(int)difference/1000;
			notification_time.add("Seconds");
			notification_time.add(seconds);
		}
		return notification_time;
	}
}
