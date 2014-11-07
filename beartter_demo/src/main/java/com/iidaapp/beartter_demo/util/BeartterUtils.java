package com.iidaapp.beartter_demo.util;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.iidaapp.beartter_demo.db.DbUtils;

public class BeartterUtils {

	public static boolean validateSignUpForm(SignUpForm signUpForm) throws ClassNotFoundException, SQLException {

		if(checkValueExistInSignUpForm(signUpForm) && isSamePassword(signUpForm.getPassword(), signUpForm.getPasswordConfirm()) && isUniqueUserNameSignUpForm(signUpForm.getUserName())
				&& isUniqueEMailAddress(signUpForm.getMailAddress()))

			return true;

		return false;
	}


	public static boolean checkValueExistInSignUpForm(SignUpForm signUpForm) {

		if(StringUtils.isEmpty(signUpForm.getUserName()) || StringUtils.isEmpty(signUpForm.getPassword()) || StringUtils.isEmpty(signUpForm.getPasswordConfirm())
				|| StringUtils.isEmpty(signUpForm.getMailAddress()))
			return false;

		return true;
	}


	public static boolean isSamePassword(String password, String passwordConfirm) {

		if(password.equals(passwordConfirm))
			return true;

		return false;
	}


	public static boolean isUniqueUserNameSignUpForm(String userName) throws ClassNotFoundException, SQLException {

		int count = DbUtils.countUserInfoByBeartterId(userName);

		if(count == 0)
			return true;

		return false;
	}


	public static boolean isUniqueEMailAddress(String mailAddress) throws ClassNotFoundException, SQLException {

		int count = DbUtils.countUserInfoByEmailAddress(mailAddress);

		if(count == 0)
			return true;

		return false;
	}

}