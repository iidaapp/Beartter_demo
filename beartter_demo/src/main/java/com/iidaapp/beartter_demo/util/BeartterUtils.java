package com.iidaapp.beartter_demo.util;

import java.sql.Date;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.iidaapp.beartter_demo.db.DbUtils;

public class BeartterUtils {


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


	public static boolean isUniqueUserNameSignUpForm(String userName) throws SQLException {

		int count = DbUtils.countUserInfoByBeartterId(userName);

		if(count == 0)
			return true;

		return false;
	}


	public static boolean isUniqueEmailAddress(String mailAddress) throws SQLException {

		int count = DbUtils.countUserInfoByEmailAddress(mailAddress);

		if(count == 0)
			return true;

		return false;
	}
	
	public static boolean isCorrectEmailAddress(String mailAddress){
		
		String mailAddressPattern = "[0-9a-zA-Z_\\-]+@[0-9a-zA-Z_\\-]+(\\.[0-9a-zA-Z_\\-]+){1,}";

		return mailAddress.matches(mailAddressPattern);
	}


	public static boolean isCorrectBirthDate(String year, String month, String day) {

		String regYear = "^(\\d{4})$";
		String regMonth = "^(0[1-9]|1[0-2])$";
		String regDay = "^(0[1-9]|[12][0-9]|3[01])$";
		
		if(year.matches(regYear)
				&& month.matches(regMonth)
				&& day.matches(regDay)){
			
			if(isCorrectMonthDay(month, day))
				return true;
		}
		
		return false;
	}


	public static boolean isCorrectMonthDay(String month, String day) {

		if((month.equals("01") && day.equals("31"))
				|| (month.equals("02") && day.equals("28"))
				|| (month.equals("03") && day.equals("31"))
				|| (month.equals("04") && day.equals("30"))
				|| (month.equals("05") && day.equals("31"))
				|| (month.equals("06") && day.equals("30"))
				|| (month.equals("07") && day.equals("31"))
				|| (month.equals("08") && day.equals("31"))
				|| (month.equals("09") && day.equals("30"))
				|| (month.equals("10") && day.equals("31"))
				|| (month.equals("11") && day.equals("30"))
				|| (month.equals("12") && day.equals("31"))
				)
			return true;
			
		return false;
	}

	
	public static Date getBirthDate(String year, String month, String day) {
		
		Date birthDate = Date.valueOf(year + "-" + month + "-" + day);
		return birthDate;
	}

}
