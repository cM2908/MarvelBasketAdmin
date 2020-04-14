package com.bean;

import java.util.List;

public class UserEvent {
	private User user;
	private List<Event> events;
	
	public UserEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserEvent(User user, List<Event> events) {
		super();
		this.user = user;
		this.events = events;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
}
