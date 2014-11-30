package com.iidaapp.beartter_demo.util;

import org.apache.commons.lang3.StringUtils;

public class SignUpForm {

	private String userName;
	private String password;
	private String passwordConfirm;
	private String mailAddress;
	private String year;
	private String month;
	private String day;


	public SignUpForm(String userName, String password, String passwordConfirm, String mailAddress, String year, String month, String day) {

		this.userName = userName;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.mailAddress = mailAddress;
		this.year = year;
		this.month = month;
		this.day = day;
	}


	public boolean checkValueExistInSignUpForm() {

		if(StringUtils.isEmpty(getUserName()) 
				|| StringUtils.isEmpty(getPassword()) 
				|| StringUtils.isEmpty(getPasswordConfirm())
				|| StringUtils.isEmpty(getMailAddress()))
			return false;

		return true;
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


	public String getYear() {
		return year;
	}


	public String getMonth() {
		return month;
	}


	public String getDay() {
		return day;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public void setDay(String day) {
		this.day = day;
	}
}
