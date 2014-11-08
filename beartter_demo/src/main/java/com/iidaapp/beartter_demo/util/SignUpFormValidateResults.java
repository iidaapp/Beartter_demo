package com.iidaapp.beartter_demo.util;

public class SignUpFormValidateResults {

	private boolean checkAllValueExistInSignUpForm;
	private boolean isSamePassword;
	private boolean isUniqueUserNameSignUpForm;
	private boolean isUniqueEMailAddress;
	private boolean isCorrectEmailAddress;
	private boolean isCorrectBirthDate;


	public boolean successAllValidate() {
		if (checkAllValueExistInSignUpForm && isSamePassword && isUniqueUserNameSignUpForm && isUniqueEMailAddress && isCorrectEmailAddress)
			return true;

		return false;
	}


	public boolean isCheckAllValueExistInSignUpForm() {
		return checkAllValueExistInSignUpForm;
	}


	public boolean isSamePassword() {
		return isSamePassword;
	}


	public boolean isUniqueUserNameSignUpForm() {
		return isUniqueUserNameSignUpForm;
	}


	public boolean isUniqueEMailAddress() {
		return isUniqueEMailAddress;
	}


	public void setCheckValueExistInSignUpForm(boolean checkAllValueExistInSignUpForm) {
		this.checkAllValueExistInSignUpForm = checkAllValueExistInSignUpForm;
	}


	public void setSamePassword(boolean isSamePassword) {
		this.isSamePassword = isSamePassword;
	}


	public void setUniqueUserNameSignUpForm(boolean isUniqueUserNameSignUpForm) {
		this.isUniqueUserNameSignUpForm = isUniqueUserNameSignUpForm;
	}


	public void setUniqueEMailAddress(boolean isUniqueEMailAddress) {
		this.isUniqueEMailAddress = isUniqueEMailAddress;
	}


	public boolean isCorrectEmailAddress() {
		return isCorrectEmailAddress;
	}


	public void setCorrectEmailAddress(boolean isCorrectEmailAddress) {
		this.isCorrectEmailAddress = isCorrectEmailAddress;
	}


	public boolean isCorrectBirthDate() {
		return isCorrectBirthDate;
	}


	public void setCheckAllValueExistInSignUpForm(boolean checkAllValueExistInSignUpForm) {
		this.checkAllValueExistInSignUpForm = checkAllValueExistInSignUpForm;
	}


	public void setCorrectBirthDate(boolean isCorrectBirthDate) {
		this.isCorrectBirthDate = isCorrectBirthDate;
	}

}
