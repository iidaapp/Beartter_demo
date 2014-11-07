package com.iidaapp.beartter_demo.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class DbConnection {

	private static DataSource ds;

	static {
		try {

			Properties properties = new Properties();
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("dbcp.properties");
			properties.load(is);
			ds = BasicDataSourceFactory.createDataSource(properties);

		} catch (Exception e) {
			// ドライバが見つからないため、RuntimeExceptionとしてthrow
			throw new RuntimeException();
		}
	}


	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

}
