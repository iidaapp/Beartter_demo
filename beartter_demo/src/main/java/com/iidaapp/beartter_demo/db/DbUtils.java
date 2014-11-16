package com.iidaapp.beartter_demo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iidaapp.beartter_demo.entity.AccessTokenEntity;
import com.iidaapp.beartter_demo.entity.CharacterParamEntity;
import com.iidaapp.beartter_demo.entity.UserinfoEntity;
import com.iidaapp.beartter_demo.util.CnameTypeEntity;
import com.iidaapp.beartter_demo.util.PartOfSpeechTypeEntity;

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


	public static void insertSignUpData(UserinfoEntity userinfoEntity, AccessTokenEntity accessTokenEntity, CharacterParamEntity characterParamEntity) throws SQLException {

		PreparedStatement stmtCharacterParam = null;
		PreparedStatement stmtUserinfo = null;
		PreparedStatement stmtAccessToken = null;

		Connection con = null;
		String sqlCharacterParam = "insert into beartter_db.character_param values (?, 0, 0, 0, 0, 0)";
		String sqlUserinfo = "insert into beartter_db.userinfo values (?, ?, ?, ?, ?, ?)";
		String sqlAccessToken = "insert into beartter_db.access_token values (?, ?, ?, ?, ?, ?, ?)";

		try {

			// コネクション取得
			con = DbConnection.getConnection();

			// 実行
			stmtCharacterParam = con.prepareStatement(sqlCharacterParam);
			stmtCharacterParam.setString(1, characterParamEntity.getBeartterId());

			// 実行
			stmtUserinfo = con.prepareStatement(sqlUserinfo);
			stmtUserinfo.setString(1, userinfoEntity.getBeartterId());
			stmtUserinfo.setString(2, userinfoEntity.getEmailAddress());
			stmtUserinfo.setString(3, userinfoEntity.getPassword());
			stmtUserinfo.setDate(4, userinfoEntity.getBirthDate());
			stmtUserinfo.setTimestamp(5, userinfoEntity.getAddDate());
			stmtUserinfo.setTimestamp(6, userinfoEntity.getModifyDate());

			// 実行
			stmtAccessToken = con.prepareStatement(sqlAccessToken);
			stmtAccessToken.setString(1, accessTokenEntity.getBeartterId());
			stmtAccessToken.setString(2, accessTokenEntity.getoAuthToken());
			stmtAccessToken.setString(3, accessTokenEntity.getoAuthSecret());
			stmtAccessToken.setLong(4, accessTokenEntity.getUserId());
			stmtAccessToken.setString(5, accessTokenEntity.getScreenName());
			stmtAccessToken.setTimestamp(6, accessTokenEntity.getAddDate());
			stmtAccessToken.setTimestamp(7, accessTokenEntity.getModifyDate());

			stmtCharacterParam.executeUpdate();
			stmtUserinfo.executeUpdate();
			stmtAccessToken.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException();

		} finally {

			if (stmtCharacterParam != null)
				stmtCharacterParam.close();
			if (stmtUserinfo != null)
				stmtUserinfo.close();
			if (stmtAccessToken != null)
				stmtAccessToken.close();
			if (con != null)
				con.close();

		}

		return;
	}


	public static List<AccessTokenEntity> selectAccessTokenListFromAccessToken(String beartterId) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs;
		Connection con = null;
		List<AccessTokenEntity> entityList = new ArrayList<AccessTokenEntity>();
		String sql = "select * from beartter_db.access_token where beartter_id = ?";

		try {
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


	public static AccessTokenEntity selectAccessTokenFromAccessToken(String beartterId) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs;
		Connection con = null;
		AccessTokenEntity entity = new AccessTokenEntity();
		String sql = "select * from beartter_db.access_token where beartter_id = ?";

		try {
			con = DbConnection.getConnection();

			stmt = con.prepareStatement(sql);
			stmt.setString(1, beartterId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				entity.setBeartterId(rs.getString(1));
				entity.setoAuthToken(rs.getString(2));
				entity.setoAuthSecret(rs.getString(3));
				entity.setUserId(rs.getLong(4));
				entity.setScreenName(rs.getString(5));
				entity.setAddDate(rs.getTimestamp(6));
				entity.setModifyDate(rs.getTimestamp(7));

			}
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();

		}

		return entity;
	}


	public static CnameTypeEntity selectCnameTypeByCname(String cname) throws SQLException {

		PreparedStatement stmt = null;
		ResultSet rs;
		Connection con = null;
		CnameTypeEntity entity = new CnameTypeEntity();
		String sql = "select * from beartter_db.cname_type where cname = ?";

		try {
			con = DbConnection.getConnection();

			stmt = con.prepareStatement(sql);
			stmt.setString(1, cname);
			rs = stmt.executeQuery();
			if (rs.next()) {
				entity.setCname(rs.getString(1));
				entity.setParameterName(rs.getString(2));
				entity.setParameterValue(rs.getInt(3));
			}

		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();

		}

		return entity;
	}


	public static void updateCharacterParam(String parameterName, int parameterValue, String beartterId) throws SQLException {

		PreparedStatement stmt = null;
		Connection con = null;
		String sql = "update beartter_db.character_param set " + parameterName + " = ? where beartter_id = ?";

		try {
			con = DbConnection.getConnection();

			stmt = con.prepareStatement(sql);
			stmt.setInt(1, parameterValue);
			stmt.setString(2, beartterId);

			stmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException();

		} finally {
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();

		}
	}


	public static PartOfSpeechTypeEntity selectPartOfSpeechType(String partOfSpeech) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs;
		Connection con = null;
		PartOfSpeechTypeEntity entity = new PartOfSpeechTypeEntity();
		String sql = "select * from beartter_db.part_of_speech_type where part_of_speech = ?";

		try {
			con = DbConnection.getConnection();

			stmt = con.prepareStatement(sql);
			stmt.setString(1, partOfSpeech);
			rs = stmt.executeQuery();
			if (rs.next()) {
				entity.setPartOfSpeech(rs.getString(1));
				entity.setParameterName(rs.getString(2));
				entity.setParameterValue(rs.getInt(3));
			}

		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();

		}

		return entity;
	}

}
