package com.iidaapp.beartter_demo.entity;

import java.sql.Timestamp;

public class AccessTokenEntity {

	private String beartterId;
	private String oAuthToken;
	private String oAuthSecret;
	private long userId;
	private String screenName;
	private Timestamp addDate;
	private Timestamp modifyDate;


	public String getBeartterId() {
		return beartterId;
	}


	public String getoAuthToken() {
		return oAuthToken;
	}


	public String getoAuthSecret() {
		return oAuthSecret;
	}


	public long getUserId() {
		return userId;
	}


	public String getScreenName() {
		return screenName;
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


	public void setoAuthToken(String oAuthToken) {
		this.oAuthToken = oAuthToken;
	}


	public void setoAuthSecret(String oAuthSecret) {
		this.oAuthSecret = oAuthSecret;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}


	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}


	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
