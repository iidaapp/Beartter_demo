package com.iidaapp.beartter_demo.util;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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


	public static boolean isCorrectEmailAddress(String mailAddress) {

		String mailAddressPattern = "[0-9a-zA-Z_\\-.+]+@[0-9a-zA-Z_\\-]+(\\.[0-9a-zA-Z_\\-]+){1,}";

		return mailAddress.matches(mailAddressPattern);
	}


	public static boolean isCorrectBirthDate(String year, String month, String day) {

		String regYear = "^(\\d{4})$";
		String regMonth = "^(0[1-9]|1[0-2])$";
		String regDay = "^(0[1-9]|[12][0-9]|3[01])$";

		if(year.matches(regYear) && month.matches(regMonth) && day.matches(regDay)) {

			if(isCorrectMonthDay(year, month, day))
				return true;
		}

		return false;
	}


	public static boolean isCorrectMonthDay(String year, String month, String day) {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		format.setLenient(false);
		try {
			format.parse(year + month + day);
		} catch (ParseException e) {
			// TODO ログ出力方法
			e.printStackTrace();
			return false;
		}

		return true;
	}


	public static Date getBirthDate(String year, String month, String day) {

		Date birthDate = Date.valueOf(year + "-" + month + "-" + day);
		return birthDate;
	}

}
