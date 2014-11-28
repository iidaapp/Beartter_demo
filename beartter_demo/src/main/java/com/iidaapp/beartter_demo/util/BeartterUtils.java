package com.iidaapp.beartter_demo.util;

import java.sql.Date;

public class BeartterUtils {


	public static Date getBirthDate(String year, String month, String day) {

		Date birthDate = Date.valueOf(year + "-" + month + "-" + day);
		return birthDate;
	}


}
