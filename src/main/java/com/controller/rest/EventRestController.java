package com.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bean.Event;
import com.bean.Response;
import com.bean.User;
import com.service.EventService;

@RestController
public class EventRestController {

	@Autowired
	EventService eventService;
	
	@RequestMapping(value = "/event/add", method = RequestMethod.POST)
	public ResponseEntity<Object> addEvent(@RequestBody Event event) {
		Response<Event> response = eventService.addEvent(event);
		if (response.getCode() == 3031) {
			return new ResponseEntity<>(response.getData(),HttpStatus.OK);
		} else {
			return new ResponseEntity<>("User not found",HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = "/event/delete", method = RequestMethod.POST)
	public ResponseEntity<Object> deleteEvent(@RequestBody Event event) {
		Response<Event> response = eventService.addEvent(event);
		if (response.getCode() == 3031) {
			return new ResponseEntity<>(response.getData(),HttpStatus.OK);
		} else {
			return new ResponseEntity<>("User not found",HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = "/event/all", method = RequestMethod.POST)
	public ResponseEntity<Object> findAllEvents(@RequestBody User user) {
		Response<List<Event>> response = eventService.allEventForUser(user); 
		System.out.println(response.getData().get(0).getEventDate());
		if (response.getCode() == 3031) {
			return new ResponseEntity<>(response.getData(),HttpStatus.OK);
		} else {
			return new ResponseEntity<>("User not found",HttpStatus.CONFLICT);
		}
	}
}
