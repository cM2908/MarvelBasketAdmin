package com.bean;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Event{
	
	private int eventId;
	private String eventName;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date eventDate;
	private int userId;
	
	public Event() {
		//Do nothing
	}
	public Event(int eventId, String eventName, Date eventDate, int userId) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.userId = userId;
	}
	
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
