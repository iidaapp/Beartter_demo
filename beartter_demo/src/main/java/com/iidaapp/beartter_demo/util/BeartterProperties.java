package com.iidaapp.beartter_demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class BeartterProperties {

	private static final String propsName = "beartter.properties";

	// Value Properties
	public static String VALUE_CHARACTER_ENCODING;
	public static String VALUE_TWITTER_CALLBACK_URL;

	// Message Properties
	public static String MESSAGE_START_LOGIN_SERVLET;

	// Error Message Properties
	public static String MESSAGE_ERROR_NULL_ACCESS_TOKEN;
	public static String MESSAGE_ERROR_NULL_BEARTTER_ID;
	public static String MESSAGE_ERROR_NULL_SESSION;

	static{

		Properties properties = getResourceAsProperties();

		// Value Properties
		VALUE_CHARACTER_ENCODING = properties.getProperty("property.value.character_encoding");
		VALUE_TWITTER_CALLBACK_URL = properties.getProperty("property.value.twitter.callback_url");

		// Message Properties
		MESSAGE_START_LOGIN_SERVLET = properties.getProperty("property.message.start_login_servlet");

		// Error Message Properties
		MESSAGE_ERROR_NULL_ACCESS_TOKEN = properties.getProperty("property.message.error.null_access_token");
		MESSAGE_ERROR_NULL_BEARTTER_ID = properties.getProperty("property.message.error.null_beartter_id");
		MESSAGE_ERROR_NULL_SESSION = properties.getProperty("property.message.error.null_session");

		// SQL Properties
		
	}

	
	/**
	 * プロパティファイルからプロパティを読み込む
	 * @return
	 */
	private static Properties getResourceAsProperties() {

		Properties properties = new Properties();
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propsName);
		try {
			properties.load(is);
			is.close();
		} catch (IOException e) {
			// プロパティファイルが見つからないため、RuntimeExceptionとしてthrow
			e.printStackTrace();
			throw new RuntimeException();
		}

		return properties;
	}



}
