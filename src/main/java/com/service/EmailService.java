package com.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bean.MailProperties;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

@Service
public class EmailService {

	@Autowired
	SendGrid sendGrid;
	
	@Value("${app.sendgrid.templateId}")
	private String templateId;
	
	@Value("${server.port}")
	private String serverPort;
	
	@Value("${host.ip}")
	private String hostIp;
	

	public String sendForgotPasswordMail(MailProperties mprops) {
		Mail mail = prepareForgotPasswordMail(mprops);
		Request request = new Request();
		request.setMethod(Method.POST);
		request.setEndpoint("mail/send");
		try {
			request.setBody(mail.build());
			Response response = sendGrid.api(request);
			if (response != null) {
				System.out.println("Response from sendGrid : " + response.getStatusCode());
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "Error sending email";
		}
		return "Email sent successfully";
	}

	private Mail prepareForgotPasswordMail(MailProperties mprops) {
		
		Mail mail = new Mail();

		Email fromEmail = new Email(); 
		fromEmail.setEmail(mprops.getSender());
		System.out.println("Sender : "+mprops.getSender());

		Email toEmail = new Email();
		toEmail.setEmail(mprops.getReceiver());
		System.out.println("Receiver : "+mprops.getReceiver());
		
		Personalization personalization = new Personalization();
		personalization.addTo(toEmail);
		personalization.setSubject(mprops.getSubject());
		personalization.addDynamicTemplateData("hostIp",hostIp);
		personalization.addDynamicTemplateData("email",mprops.getReceiver());
		personalization.addDynamicTemplateData("portNumber",serverPort);
		personalization.addDynamicTemplateData("urlValue","adminfpmailconfirmation");
		
		mail.setFrom(fromEmail);
		mail.addPersonalization(personalization);
		mail.setTemplateId(templateId);
		
		return mail;
	}
}

/* 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 */