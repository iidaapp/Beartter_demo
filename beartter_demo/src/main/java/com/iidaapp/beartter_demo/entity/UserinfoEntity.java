package com.iidaapp.beartter_demo.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class UserinfoEntity {
	
	private String beartterId;
	private String emailAddress;
	private String password;
	private Date birthDate;
	private Timestamp addDate;
	private Timestamp modifyDate;


	public String getBeartterId() {
		return beartterId;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public String getPassword() {
		return password;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public Timestamp getAddDate() {
		return addDate;
	}


	public Timestamp getModifyDate() {
		return modifyDate;
	}


	public void setBeartterId(String beartterId) {
		this.beartterId = beartterId;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}


	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

}
