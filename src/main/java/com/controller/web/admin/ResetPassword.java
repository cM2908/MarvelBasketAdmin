package com.controller.web.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bean.Admin;
import com.bean.Login;
import com.bean.Response;
import com.dao.AdminDao;
import com.service.AdminService;

@Controller
public class ResetPassword {

	@Autowired
	AdminDao adminDao;
	@Autowired
	AdminService adminservice;

	@RequestMapping(value = "/adminfpmailconfirmation/{email}", method = RequestMethod.GET)
	private String confirmEmail(@PathVariable("email") String email, Model model) {
		List<Admin> list = adminDao.findByProperty("admin_email", email.trim());
		if (list.size() == 0) {
			return "fpmail_error";
		} else {
			Admin admin = list.get(0);
			Login login = new Login();
			login.setEmail(admin.getAdminEmail());
			login.setPassword(null);
			model.addAttribute("loginCommand", login);
			return "resetpassword";
		}
	}

	@RequestMapping(value = "/admin/resetpassword", method = RequestMethod.POST)
	public String resetPassword(@ModelAttribute("adminCommand") Login loginParam, RedirectAttributes redirectAttr) {
		List<Admin> list = adminDao.findByProperty("admin_email", loginParam.getEmail());
		if (list.size() > 0) {
			Admin admin = list.get(0);
			admin.setAdminPassword(loginParam.getPassword());
			Response<Admin> response = adminservice.updateAdminProfile(admin);
			if (response.getCode() == 1007) {
				redirectAttr.addFlashAttribute("resetSuccess", "Password reset successfully");
			} else if (response.getCode() == 1006) {
				redirectAttr.addFlashAttribute("resetError", "Something Went wrong ! can't reset password");
			}
		}
		return "redirect:/admin/login";
	}
}
