package com.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bean.Login;
import com.bean.Response;
import com.bean.User;
import com.bean.UserEvent;
import com.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public ResponseEntity<Object> registertUser(@RequestBody UserEvent userEvent) {
		System.out.println();
		Response<User> response = userService.registerUser(userEvent);
		if (response.getCode() == 2031) {
			return new ResponseEntity<>(response.getData(),HttpStatus.OK);
		} else {
			return new ResponseEntity<>("User with email already exist",HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/user/auth", method = RequestMethod.POST)
	public ResponseEntity<Object> authenticateUser(@RequestBody Login login) {
		User user = userService.validate(login);
		if (user == null) {
			return new ResponseEntity<>("Invalid User Credentials", HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
	}
	
}
