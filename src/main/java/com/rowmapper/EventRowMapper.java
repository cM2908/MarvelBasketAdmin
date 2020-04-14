package com.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.bean.Event;

@Component
public class EventRowMapper implements RowMapper<Event>{

	@Override
	public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
		Event event = new Event();
		event.setEventId(rs.getInt("event_id"));
		event.setEventName(rs.getString("event_name"));
		event.setEventDate(rs.getDate("event_date"));
		event.setUserId(rs.getInt("user_id"));
		return event;
	}
	
}
