package com.iidaapp.beartter_demo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iidaapp.beartter_demo.entity.AccessTokenEntity;
import com.iidaapp.beartter_demo.entity.UserinfoEntity;

public class DbUtils {

	public static String selectBeartterIdFromAccessToken(long userId) throws SQLException {

		String beartterId = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = "SELECT beartter_id FROM beartter_db.access_token where user_id = ?";

		try {

			// コネクション取得
			con = DbConnection.getConnection();

			// 実行
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, userId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				beartterId = rs.getString("beartter_id");
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


	public static int countUserInfoByBeartterId(String beartterId) throws SQLException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		int resultCount = 0;
		String sql = "SELECT COUNT(*) FROM beartter_db.userinfo where beartter_id = ?";

		try {

			// コネクション取得
			con = DbConnection.getConnection();

			// 実行
			stmt = con.prepareStatement(sql);
			stmt.setString(1, beartterId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				resultCount = rs.getInt(1);
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

		return resultCount;
	}


	public static int countUserInfoByEmailAddress(String EmailAddress) throws SQLException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		int resultCount = 0;
		String sql = "SELECT COUNT(*) FROM beartter_db.userinfo where email_address = ?";

		try {

			// コネクション取得
			con = DbConnection.getConnection();

			// 実行
			stmt = con.prepareStatement(sql);
			stmt.setString(1, EmailAddress);
			rs = stmt.executeQuery();

			if (rs.next()) {
				resultCount = rs.getInt(1);
			}

		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();

		}

		return resultCount;
	}


	public static int insertAccessToken(AccessTokenEntity accessTokenEntity) throws SQLException {

		PreparedStatement stmt = null;
		Connection con = null;
		int rs = 0;
		String sql = "insert into beartter_db.access_token values (?, ?, ?, ?, ?, ?, ?)";

		try {

			// コネクション取得
			con = DbConnection.getConnection();

			// 実行
			stmt = con.prepareStatement(sql);
			stmt.setString(1, accessTokenEntity.getBeartterId());
			stmt.setString(2, accessTokenEntity.getoAuthToken());
			stmt.setString(3, accessTokenEntity.getoAuthSecret());
			stmt.setLong(4, accessTokenEntity.getUserId());
			stmt.setString(5, accessTokenEntity.getScreenName());
			stmt.setTimestamp(6, accessTokenEntity.getAddDate());
			stmt.setTimestamp(7, accessTokenEntity.getModifyDate());

			rs = stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {

			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();

		}
		return rs;

	}


	public static int insertUserinfoEntity(UserinfoEntity userinfoEntity) throws SQLException {

		PreparedStatement stmt = null;
		int rs = 0;
		Connection con = null;
		String sql = "insert into beartter_db.userinfo values (?, ?, ?, ?, ?, ?)";

		try {

			// コネクション取得
			con = DbConnection.getConnection();

			// 実行
			stmt = con.prepareStatement(sql);
			stmt.setString(1, userinfoEntity.getBeartterId());
			stmt.setString(2, userinfoEntity.getEmailAddress());
			stmt.setString(3, userinfoEntity.getPassword());
			stmt.setDate(4, userinfoEntity.getBirthDate());
			stmt.setTimestamp(5, userinfoEntity.getAddDate());
			stmt.setTimestamp(6, userinfoEntity.getModifyDate());
			rs = stmt.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();

		}

		return rs;
	}


	public static List<AccessTokenEntity> selectAccessTokenFromAccessToken(String beartterId) throws SQLException{
		PreparedStatement stmt = null;
		ResultSet rs;
		Connection con = null;
		List<AccessTokenEntity> entityList = new ArrayList<AccessTokenEntity>();
		String sql = "select * from beartter_db.access_token where beartter_id = ?";
		
		try{
			con = DbConnection.getConnection();
			
			stmt = con.prepareStatement(sql);
			stmt.setString(1, beartterId);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				AccessTokenEntity entity = new AccessTokenEntity();
				entity.setBeartterId(rs.getString(1));
				entity.setoAuthToken(rs.getString(2));
				entity.setoAuthSecret(rs.getString(3));
				entity.setUserId(rs.getLong(4));
				entity.setScreenName(rs.getString(5));
				entity.setAddDate(rs.getTimestamp(6));
				entity.setModifyDate(rs.getTimestamp(7));
				
				entityList.add(entity);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();

		}

		return entityList;
	}
}
