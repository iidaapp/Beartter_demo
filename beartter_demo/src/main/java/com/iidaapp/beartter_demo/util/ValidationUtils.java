package com.iidaapp.beartter_demo.util;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iidaapp.beartter_demo.db.DbUtils;

public class ValidationUtils {

	private static Logger log = LoggerFactory.getLogger(ValidationUtils.class);


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


	public static boolean isCorrectEmailAddress(String mailAddress) {

		String mailAddressPattern = "[0-9a-zA-Z_\\-.+]+@[0-9a-zA-Z_\\-]+(\\.[0-9a-zA-Z_\\-]+){1,}";

		return mailAddress.matches(mailAddressPattern);
	}


	public static boolean isCorrectBirthDate(String year, String month, String day) throws ParseException {

		String regYear = "^(\\d{4})$";
		String regMonth = "^(0[1-9]|1[0-2])$";
		String regDay = "^(0[1-9]|[12][0-9]|3[01])$";

		if(year.matches(regYear) && month.matches(regMonth) && day.matches(regDay)) {

			if(isCorrectMonthDay(year, month, day))
				return true;
		}

		return false;
	}


	public static boolean isCorrectMonthDay(String year, String month, String day) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		format.setLenient(false);
		try {
			format.parse(year + month + day);
		} catch (ParseException e) {
			log.error(e.toString());
			throw e;
		}

		return true;
	}


	public static boolean isCorrectDigit(SignUpForm signUpForm) {
		if(signUpForm.getUserName().length() <= 50 
				&& signUpForm.getMailAddress().length() <= 200 
				&& signUpForm.getYear().length() == 4 
				&& signUpForm.getMonth().length() == 2
				&& signUpForm.getDay().length() == 2 
				&& signUpForm.getPassword().length() <= 45 
				&& signUpForm.getPasswordConfirm().length() <= 45)

			return true;

		return false;
	}


	public static boolean isCorrectPassword(String password) {

		if(password.getBytes().length == password.length())
			return true;

		return false;
	}
}
