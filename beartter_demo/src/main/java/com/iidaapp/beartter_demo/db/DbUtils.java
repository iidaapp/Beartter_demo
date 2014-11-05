package com.iidaapp.beartter_demo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import twitter4j.auth.AccessToken;

public class DbUtils {

	private static String URL = "jdbc:mysql://localhost:3306/beartter_db";
	private static String USER = "root";
	private static String PASSWORD = "";
	private static String DRIVER = "com.mysql.jdbc.Driver";


	public static long selectBeartterIdByUserId(long userId) throws ClassNotFoundException, SQLException {

		long beartterId = 0;
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			// ドライバ設定
			Class.forName(DRIVER);
			// データベースとの接続
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			// 実行
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT beartter_id FROM beartter_db.access_token where user_id = " + userId);

			if (rs.next()) {
				beartterId = rs.getInt("beartter_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();

		}

		return beartterId;

	}


	public static int insertAccessToken(AccessToken accessToken) throws ClassNotFoundException, SQLException {

		Statement stmt = null;
		Connection con = null;
		int num = 0;

		try {
			// ドライバ設定
			Class.forName(DRIVER);
			// データベースとの接続
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			// 実行
			stmt = con.createStatement();
			num = stmt.executeUpdate("INSERT INTO beartter_db.access_token values (1000001, '" + accessToken.getToken() + "', '" + accessToken.getTokenSecret() + "', '" + accessToken.getUserId() + "', '"
					+ accessToken.getScreenName() + "', now(), now());");

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {

			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();

		}
		return num;

	}

}
