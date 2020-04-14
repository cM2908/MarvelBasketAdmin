package com.controller.web.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bean.Admin;
import com.bean.Login;
import com.bean.Response;
import com.service.AdminService;

@Controller
public class LoginController {

	@Autowired
	AdminService adminService;

	@RequestMapping(value = { "/", "/admin/login" }, method = RequestMethod.GET)
	public String loginForm(Model model) {
		Login login = new Login();
		model.addAttribute("loginCommand", login);
		return "login";
	}

	@RequestMapping(value = "/admin/authenticate", method = RequestMethod.POST)
	public String authenticate(@ModelAttribute("loginCommand") Login loginParam, HttpSession session,
			RedirectAttributes redirectAttr, Model model) {
		Response<Admin> response = adminService.authenticateAdmin(loginParam.getEmail(), loginParam.getPassword());
		if (response.getCode() == 1002) {
			redirectAttr.addFlashAttribute("loginError", "Invalid Email or Password");
			return "redirect:/admin/login";
		} else {
			Admin loggedInAdmin = response.getData();
			redirectAttr.addFlashAttribute("welcomeUser", "Welcome " + loggedInAdmin.getAdminName() + " to Marvel Basket");
			addAdminInSession(loggedInAdmin, session);
			return "redirect:/admin/dashboard";
		}
	}

	@RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
	public String logoutAdmin(HttpSession session) {
		session.invalidate();
		return "redirect:/admin/login";
	}

	private void addAdminInSession(Admin admin, HttpSession session) {
		session.setAttribute("admin", admin);
	}
}