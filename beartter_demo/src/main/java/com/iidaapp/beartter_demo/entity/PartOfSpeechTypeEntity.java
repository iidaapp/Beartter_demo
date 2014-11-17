package com.iidaapp.beartter_demo.entity;

public class PartOfSpeechTypeEntity {

	private String partOfSpeech;
	private String parameterName;
	private int parameterValue;


	public String getPartOfSpeech() {
		return partOfSpeech;
	}


	public String getParameterName() {
		return parameterName;
	}


	public int getParameterValue() {
		return parameterValue;
	}


	public void setPartOfSpeech(String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}


	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}


	public void setParameterValue(int parameterValue) {
		this.parameterValue = parameterValue;
	}
}
