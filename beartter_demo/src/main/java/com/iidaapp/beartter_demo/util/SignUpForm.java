package com.iidaapp.beartter_demo.util;

public class SignUpForm {

	private String userName;
	private String password;
	private String passwordConfirm;
	private String mailAddress;


	public SignUpForm(String userName, String password, String passwordConfirm, String mailAddress) {

		this.userName = userName;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.mailAddress = mailAddress;

	}


	public String getUserName() {
		return userName;
	}


	public String getPassword() {
		return password;
	}


	public String getPasswordConfirm() {
		return passwordConfirm;
	}


	public String getMailAddress() {
		return mailAddress;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}


	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
}
