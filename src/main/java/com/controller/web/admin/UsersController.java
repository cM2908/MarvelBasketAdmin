package com.controller.web.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bean.User;
import com.dao.UserDao;

@Controller
public class UsersController {

	@Autowired
	UserDao userDao;
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public String usersTable(Model model)
	{
		List<User> userList = userDao.findAll();
		model.addAttribute("userList",userList);
		return "users_table";
	}
}
