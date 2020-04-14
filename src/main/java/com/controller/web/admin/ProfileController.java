package com.controller.web.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bean.Admin;
import com.bean.Response;
import com.service.AdminService;

@Controller
public class ProfileController {

	@Autowired
	AdminService adminService;

	@RequestMapping(value = "/admin/profile", method = RequestMethod.GET)
	public String profilePage(HttpSession session, Model model) {
		model.addAttribute("adminCommand", session.getAttribute("admin"));
		return "profile";
	}

	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	public String updateProfile(@ModelAttribute("adminCommand") Admin adminCommand, Model model, HttpSession session) {
		Response<Admin> response = adminService.updateAdminProfile(adminCommand);
		if (response.getCode() == 1007) {
			model.addAttribute("updateSuccess", "Profile updated successfully");
			session.setAttribute("admin", adminCommand);
		} else if (response.getCode() == 1006) {
			model.addAttribute("emailError", "Email already exists");
			model.addAttribute("adminCommand", session.getAttribute("admin"));
		}
		return "profile";
	}
}
