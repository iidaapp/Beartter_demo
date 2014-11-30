package com.iidaapp.beartter_demo.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbConnection {

	private static Logger log = LoggerFactory.getLogger(DbConnection.class);
	private static DataSource ds;

	static {
		try {

			Properties properties = new Properties();
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("dbcp.properties");
			properties.load(is);
			ds = BasicDataSourceFactory.createDataSource(properties);

		} catch (Exception e) {
			// ドライバが見つからないため、RuntimeExceptionとしてthrow
			log.error(e.toString());
			throw new RuntimeException();
		}
	}


	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

}
