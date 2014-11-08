package com.iidaapp.beartter_demo.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import twitter4j.auth.AccessToken;

public class DbUtils {


	public static long selectBeartterIdFromAccessToken(long userId) throws SQLException {

		long beartterId = 0;
		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;

		try {

			// コネクション取得
			con = DbConnection.getConnection();

			// 実行
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT beartter_id FROM beartter_db.access_token where user_id = " + userId);

			if(rs.next()) {
				beartterId = rs.getInt("beartter_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
			if(con != null)
				con.close();

		}

		return beartterId;

	}


	public static int countUserInfoByBeartterId(String beartterId) throws SQLException {

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		int resultCount = 0;

		try {

			// コネクション取得
			con = DbConnection.getConnection();

			// 実行
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT COUNT(*) FROM beartter_db.userinfo where beartter_id = '" + beartterId + "'");

			if(rs.next()) {
				resultCount = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
			if(con != null)
				con.close();

		}

		return resultCount;
	}


	public static int countUserInfoByEmailAddress(String EmailAddress) throws SQLException {

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		int resultCount = 0;

		try {

			// コネクション取得
			con = DbConnection.getConnection();

			// 実行
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT COUNT(*) FROM beartter_db.userinfo where email_address = '" + EmailAddress + "'");

			if(rs.next()) {
				resultCount = rs.getInt(1);
			}

		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
			if(con != null)
				con.close();

		}

		return resultCount;
	}


	public static int insertAccessToken(AccessToken accessToken) throws SQLException {

		Statement stmt = null;
		Connection con = null;
		int num = 0;

		try {

			// コネクション取得
			con = DbConnection.getConnection();

			// 実行
			stmt = con.createStatement();
			num = stmt.executeUpdate("INSERT INTO beartter_db.access_token values (1000001, '" + accessToken.getToken() + "', '" + accessToken.getTokenSecret() + "', '" + accessToken.getUserId()
					+ "', '" + accessToken.getScreenName() + "', now(), now());");

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {

			if(stmt != null)
				stmt.close();
			if(con != null)
				con.close();

		}
		return num;

	}

}
