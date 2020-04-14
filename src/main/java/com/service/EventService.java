package com.service;

import java.util.List;

import com.bean.Event;
import com.bean.Response;
import com.bean.User;

public interface EventService {

	public Response<Event> addEvent(Event event);
	public Response<String> removeEvent(Event event);
	public Response<List<Event>> allEventForUser(User user);
}
