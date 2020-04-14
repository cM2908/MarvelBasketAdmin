package com.controller.web.admin;

import java.net.MalformedURLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bean.Admin;
import com.bean.MailProperties;
import com.dao.AdminDao;
import com.service.EmailService;

@Controller
public class ForgotPasswordController {

	@Autowired
	AdminDao adminDao;
	@Autowired
	EmailService emailService;
	
	@RequestMapping(value = "/admin/fpform", method = RequestMethod.GET)
	private String forgotPasswordForm(Model model) {
		Admin admin = new Admin();
		model.addAttribute("adminCommand", admin);
		return "forgotpassword";
	}

	@RequestMapping(value = "/admin/fpmail", method = RequestMethod.POST)
	private String forgotPasswordMail(@ModelAttribute("adminCommand") Admin adminParam, RedirectAttributes redirectAttr)
			throws MalformedURLException {
		List<Admin> list = adminDao.findByProperty("admin_email", adminParam.getAdminEmail().trim());
		if (list.size() == 0) {
			redirectAttr.addFlashAttribute("emailError", "User not found");
			return "redirect:/admin/fpform";
		} else {
			Admin fpAdmin = list.get(0);
			sendMail(fpAdmin);
			redirectAttr.addFlashAttribute("emailSuccess", "Email sent successfully");
			return "redirect:/admin/login";
		}
	}

	private void sendMail(Admin admin) throws MalformedURLException {

		MailProperties mprops = new MailProperties();
		mprops.setSender("cm2981999@gmail.com");
		mprops.setReceiver(admin.getAdminEmail());
		mprops.setSubject("Reset Password");
		
		emailService.sendForgotPasswordMail(mprops);
	}
}
