package com.iidaapp.beartter_demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeartterProperties {

	private static Logger log = LoggerFactory.getLogger(BeartterProperties.class);
	private static final String propsName = "beartter.properties";

	// Value Properties
	public static String VALUE_CHARACTER_ENCODING;
	public static String VALUE_TWITTER_CALLBACK_URL;

	// Message Properties
	public static String MESSAGE_START_LOGIN_SERVLET;
	public static String MESSAGE_START_LOGOUT_SERVLET;
	public static String MESSAGE_START_LOGOUT_COMPLETE_SERVLET;
	public static String MESSAGE_START_CALLBACK_SERVLET;
	public static String MESSAGE_START_SIGNUP_SERVLET;
	public static String MESSAGE_START_SIGNUP_CONFIRM_SERVLET;
	public static String MESSAGE_START_MAIN_SERVLET;
	public static String MESSAGE_START_GET_FRIENDSHIP_SERVLET;
	public static String MESSAGE_START_GET_PROFILE_SERVLET;
	public static String MESSAGE_START_ERROR_SERVLET;
	public static String MESSAGE_START_LIMIT_SERVLET;

	// Error Message Properties
	public static String MESSAGE_ERROR_NULL_ACCESS_TOKEN;
	public static String MESSAGE_ERROR_NULL_BEARTTER_ID;
	public static String MESSAGE_ERROR_NULL_SESSION;
	public static String MESSAGE_ERROR_NULL_TWITTER;

	public static String MESSAGE_ERROR_SECEDE_NULL_PASSWORD;
	public static String MESSAGE_ERROR_SECEDE_NOT_CORRECT_PASSWORD;
	public static String MESSAGE_ERROR_SECEDE_NOT_SAME_PASSWORD;

	// SQL Properties
	public static String SQL_SELECT_BEARTTER_ID_FROM_ACCESS_TOKEN;
	public static String SQL_COUNT_USERINFO_BY_BEARTTER_ID;
	public static String SQL_COUNT_USERINFO_BY_EMAIL_ADDRESS;
	public static String SQL_INSERT_CHARACTER_PARAM;
	public static String SQL_INSERT_USERINFO;
	public static String SQL_INSERT_ACCESS_TOKEN;
	public static String SQL_SELECT_ACCESS_TOKEN_FROM_ACCESS_TOKEN;
	public static String SQL_SELECT_CNAME_TYPE_BY_CNAME;
	public static String SQL_UPDATE_CHARACTER_PARAM;
	public static String SQL_SELECT_PART_OF_SPEECH_TYPE;
	public static String SQL_DELETE_ACCESS_TOKEN;
	public static String SQL_DELETE_CHARACTER_PARAM;
	public static String SQL_DELETE_USERINFO;
	public static String SQL_SELECT_PASSWORD_BY_BEARTTER_ID;
	public static String SQL_SELECT_CHARACTER_PARAM_BY_BEARTTER_ID;

	static {

		Properties properties = getResourceAsProperties();

		// Value Properties
		VALUE_CHARACTER_ENCODING = properties.getProperty("property.value.character_encoding");
		VALUE_TWITTER_CALLBACK_URL = properties.getProperty("property.value.twitter.callback_url");

		// Message Properties
		MESSAGE_START_LOGIN_SERVLET = properties.getProperty("property.message.start_login_servlet");
		MESSAGE_START_LOGOUT_SERVLET = properties.getProperty("property.message.start_logout_servlet");
		MESSAGE_START_LOGOUT_COMPLETE_SERVLET = properties.getProperty("property.message.start_logout_complete_servlet");
		MESSAGE_START_CALLBACK_SERVLET = properties.getProperty("property.message.start_callback_servlet");
		MESSAGE_START_SIGNUP_SERVLET = properties.getProperty("property.message.start_singup_servlet");
		MESSAGE_START_SIGNUP_CONFIRM_SERVLET = properties.getProperty("property.message.start_singup_confirm_servlet");
		MESSAGE_START_MAIN_SERVLET = properties.getProperty("property.message.start_main_servlet");
		MESSAGE_START_GET_FRIENDSHIP_SERVLET = properties.getProperty("property.message.start_get_friendship_servlet");
		MESSAGE_START_GET_PROFILE_SERVLET = properties.getProperty("property.message.start_get_profile_servlet");
		MESSAGE_START_ERROR_SERVLET = properties.getProperty("property.message.start_error_servlet");
		MESSAGE_START_LIMIT_SERVLET = properties.getProperty("property.message.start_limit_servlet");

		// Error Message Properties
		MESSAGE_ERROR_NULL_ACCESS_TOKEN = properties.getProperty("property.message.error.null_access_token");
		MESSAGE_ERROR_NULL_BEARTTER_ID = properties.getProperty("property.message.error.null_beartter_id");
		MESSAGE_ERROR_NULL_SESSION = properties.getProperty("property.message.error.null_session");
		MESSAGE_ERROR_NULL_TWITTER = properties.getProperty("property.message.error.null_twitter");
		
		MESSAGE_ERROR_SECEDE_NULL_PASSWORD = properties.getProperty("property.message.error.secede.null_password");
		MESSAGE_ERROR_SECEDE_NOT_CORRECT_PASSWORD = properties.getProperty("property.message.error.secede.not_correct_password");
		MESSAGE_ERROR_SECEDE_NOT_SAME_PASSWORD = properties.getProperty("property.message.error.secede.not_same_password");

		// SQL Properties
		SQL_SELECT_BEARTTER_ID_FROM_ACCESS_TOKEN = properties.getProperty("property.sql.select_beartter_id_from_access_token");
		SQL_COUNT_USERINFO_BY_BEARTTER_ID = properties.getProperty("property.sql.count_userinfo_by_beartter_id");
		SQL_COUNT_USERINFO_BY_EMAIL_ADDRESS = properties.getProperty("property.sql.count_userinfo_by_email_address");
		SQL_INSERT_CHARACTER_PARAM = properties.getProperty("property.sql.insert_character_param");
		SQL_INSERT_USERINFO = properties.getProperty("property.sql.insert_userinfo");
		SQL_INSERT_ACCESS_TOKEN = properties.getProperty("property.sql.insert_access_token");
		SQL_SELECT_ACCESS_TOKEN_FROM_ACCESS_TOKEN = properties.getProperty("property.sql.select_access_token_from_access_token");
		SQL_SELECT_CNAME_TYPE_BY_CNAME = properties.getProperty("property.sql.select_cname_type_by_cname");
		SQL_UPDATE_CHARACTER_PARAM = properties.getProperty("property.sql.udpate_character_param");
		SQL_SELECT_PART_OF_SPEECH_TYPE = properties.getProperty("property.sql.select_part_of_speech_type");
		SQL_DELETE_ACCESS_TOKEN = properties.getProperty("property.sql.delete_access_token");
		SQL_DELETE_CHARACTER_PARAM = properties.getProperty("property.sql.delete_character_param");
		SQL_DELETE_USERINFO = properties.getProperty("property.sql.delete_userinfo");
		SQL_SELECT_PASSWORD_BY_BEARTTER_ID = properties.getProperty("property.sql.select_password_by_beartter_id");
		SQL_SELECT_CHARACTER_PARAM_BY_BEARTTER_ID = properties.getProperty("property.sql.select_character_param_by_beartter_id");
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
			log.error(e.toString());
			throw new RuntimeException();
		}

		return properties;
	}

}
