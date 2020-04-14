package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bean.Event;
import com.bean.Response;
import com.bean.User;
import com.dao.EventDao;
import com.dao.UserDao;
import com.rowmapper.EventRowMapper;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	UserDao userDao;
	@Autowired
	EventDao eventDao;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	EventRowMapper eventRowMapper;

	public Response<Event> addEvent(Event event) {
		User user = userDao.findById(event.getUserId());
		if (user == null) {
			return new Response<Event>(null, "User not found", 3032);
		} else {
			eventDao.save(event);
			String query = "SELECT * FROM event WHERE event_name = ? and event_date = ? and user_id = ?";
			Object[] parameters = new Object[] {event.getEventName(),event.getEventDate(),event.getUserId()};
			Event eventDB = jdbcTemplate.queryForObject(query,parameters,eventRowMapper);
			return new Response<Event>(eventDB, "Success", 3031);
		}
	}
	
	public Response<String> removeEvent(Event event) {
		User user = userDao.findById(event.getUserId());
		if (user == null) {
			return new Response<String>("User not found", "User not found", 3032);
		} else {
			eventDao.delete(event);
			return new Response<String>("Success", "Success", 3031);
		}
	}

	public Response<List<Event>> allEventForUser(User user) {
		User userDB = userDao.findById(user.getUserId());
		if (userDB == null) {
			return new Response<>(null, "User not found", 3032);
		} else {
			List<Event> eventList = eventDao.findByProperty("user_id",userDB.getUserId());
			System.out.println(eventList.get(0).getEventDate());
			return new Response<>(eventList, "Success", 3031);
		}
	}
}
