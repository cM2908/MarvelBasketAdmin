package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.Event;
import com.rowmapper.EventRowMapper;

@Repository
public class EventDaoImpl implements EventDao {

	@Autowired
	EventRowMapper eventRowMapper;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void save(Event event) {
		String query = "INSERT INTO event (event_name,event_date,user_id) VALUES (?,?,?)";
		Object[] parameters = new Object[] {event.getEventName(),event.getEventDate(),event.getUserId()};
		jdbcTemplate.update(query,parameters);
	}

	@Override
	public void update(Event event) {
		String query = "UPDATE event SET event_name=? , event_date=? , user_id=? WHERE event_id = ?";
		Object[] parameters = new Object[] {event.getEventName(),event.getEventDate(),event.getUserId(),event.getEventId()};
		jdbcTemplate.update(query,parameters);

	}

	@Override
	public void delete(Event event) {
		this.deleteById(event.getEventId());
	}

	@Override
	public void deleteById(Integer eventId) {
		String query = "DELETE FROM event WHERE event_id = ?";
		jdbcTemplate.update(query,eventId);
	}

	@Override
	public List<Event> findAll() {
		String query = "SELECT * FROM event";
		List<Event> events = jdbcTemplate.query(query, eventRowMapper);
		return events;
	}

	@Override
	public Event findById(Integer eventId) {
		String query = "SELECT * FROM event WHERE event_id = ?";
		Object[] parameters = new Object[] { eventId };
		Event event = jdbcTemplate.queryForObject(query, parameters, eventRowMapper);
		return event;
	}

	@Override
	public List<Event> findByProperty(String propName, Object propValue) {
		String query = "SELECT * FROM event WHERE " + propName + " =?";
		Object[] parameters = new Object[] { propValue };
		List<Event> events = jdbcTemplate.query(query, parameters, eventRowMapper);
		return events;
	}

}
