package com.bean;

public class Admin {
	
	private Integer adminId;
	private String adminEmail;
	private String adminPassword;
	private String adminName;
	private String adminContact;
	
	public Admin() {
		//Do nothing
	}

	public Admin(Integer adminId, String adminEmail, String adminPassword, String adminName, String adminContact) {
		super();
		this.adminId = adminId;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
		this.adminName = adminName;
		this.adminContact = adminContact;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminContact() {
		return adminContact;
	}

	public void setAdminContact(String adminContact) {
		this.adminContact = adminContact;
	}
}
