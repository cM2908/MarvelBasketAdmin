package com.dao;

import java.util.List;

import com.bean.Event;

public interface EventDao {
	
	public void save(Event event);
	public void update(Event event);
	public void delete(Event event);
	public void deleteById(Integer eventId);
	public List<Event> findAll();
	public Event findById(Integer eventId);
	public List<Event> findByProperty(String propName , Object propValue);
	
}
