package com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;

@Configuration
public class SendgridConfig {
	
	@Value("${app.sendgrid.key}")
	private String apiKey; 
	
	@Bean
	public SendGrid getSendGrid()
	{
		return new SendGrid(apiKey);
	}
}
