package com.iidaapp.beartter_demo.util;

import twitter4j.ResponseList;
import twitter4j.Status;

public class StatusList{

	public ResponseList<Status> responseList;

	public StatusList(ResponseList<Status> status){
		this.responseList = status;
	}
	
	public ResponseList<Status> getResponseList() {
		return responseList;
	}

	public void setResponseList(ResponseList<Status> responseList) {
		this.responseList = responseList;
	}
	
}
