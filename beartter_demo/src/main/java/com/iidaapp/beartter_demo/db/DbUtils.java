package com.iidaapp.beartter_demo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iidaapp.beartter_demo.entity.AccessTokenEntity;
import com.iidaapp.beartter_demo.entity.CharacterParamEntity;
import com.iidaapp.beartter_demo.entity.CnameTypeEntity;
import com.iidaapp.beartter_demo.entity.PartOfSpeechTypeEntity;
import com.iidaapp.beartter_demo.entity.UserinfoEntity;
import com.iidaapp.beartter_demo.util.BeartterProperties;

public class DbUtils {

	private static Logger log = LoggerFactory.getLogger(DbUtils.class);


	public static String selectBeartterIdFromAccessToken(long userId) throws SQLException {

		String beartterId = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = BeartterProperties.SQL_SELECT_BEARTTER_ID_FROM_ACCESS_TOKEN;

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

		} catch (SQLException e) {

			log.error(e.toString());
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
		String sql = BeartterProperties.SQL_COUNT_USERINFO_BY_BEARTTER_ID;

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

		} catch (SQLException e) {

			log.error(e.toString());
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
		String sql = BeartterProperties.SQL_COUNT_USERINFO_BY_EMAIL_ADDRESS;

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

		} catch (SQLException e) {

			log.error(e.toString());
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
		String sqlCharacterParam = BeartterProperties.SQL_INSERT_CHARACTER_PARAM;
		String sqlUserinfo = BeartterProperties.SQL_INSERT_USERINFO;
		String sqlAccessToken = BeartterProperties.SQL_INSERT_ACCESS_TOKEN;

		try {

			// コネクション取得
			con = DbConnection.getConnection();
			con.setAutoCommit(false);

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

			con.commit();

		} catch (SQLException e) {

			log.error(e.toString());
			con.rollback();
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
		String sql = BeartterProperties.SQL_SELECT_ACCESS_TOKEN_FROM_ACCESS_TOKEN;

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
		} catch (SQLException e) {

			log.error(e.toString());
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
		String sql = BeartterProperties.SQL_SELECT_ACCESS_TOKEN_FROM_ACCESS_TOKEN;

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
		} catch (SQLException e) {

			log.error(e.toString());
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
		String sql = BeartterProperties.SQL_SELECT_CNAME_TYPE_BY_CNAME;

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

		} catch (SQLException e) {

			log.error(e.toString());
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
		String sql = BeartterProperties.SQL_UPDATE_CHARACTER_PARAM;
		String sqlReplaced = MessageFormat.format(sql, parameterName);

		try {
			con = DbConnection.getConnection();

			stmt = con.prepareStatement(sqlReplaced);
			stmt.setInt(1, parameterValue);
			stmt.setString(2, beartterId);

			stmt.executeUpdate();

		} catch (SQLException e) {

			log.error(e.toString());
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
		String sql = BeartterProperties.SQL_SELECT_PART_OF_SPEECH_TYPE;

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

		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();

		}

		return entity;
	}


	public static void removeAllData(String beartterId) throws SQLException {

		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		Connection con = null;
		String deleteAccessToken = BeartterProperties.SQL_DELETE_ACCESS_TOKEN;
		String deleteCharacterParam = BeartterProperties.SQL_DELETE_CHARACTER_PARAM;
		String deleteUserinfo = BeartterProperties.SQL_DELETE_USERINFO;

		try {
			con = DbConnection.getConnection();

			con.setAutoCommit(false);

			stmt1 = con.prepareStatement(deleteAccessToken);
			stmt1.setString(1, beartterId);
			stmt1.executeUpdate();

			stmt2 = con.prepareStatement(deleteCharacterParam);
			stmt2.setString(1, beartterId);
			stmt2.executeUpdate();

			stmt3 = con.prepareStatement(deleteUserinfo);
			stmt3.setString(1, beartterId);
			stmt3.executeUpdate();

			con.commit();

		} catch (SQLException e) {

			log.error(e.toString());
			con.rollback();
			throw new RuntimeException();
		} finally {
			if (stmt1 != null)
				stmt1.close();
			if (stmt2 != null)
				stmt2.close();
			if (stmt3 != null)
				stmt3.close();

			if (con != null)
				con.close();

		}
	}


	public static String selectPasswordFromUserinfoByBeatterId(String beartterId) throws SQLException {

		String password = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = BeartterProperties.SQL_SELECT_PASSWORD_BY_BEARTTER_ID;

		try {

			// コネクション取得
			con = DbConnection.getConnection();

			// 実行
			stmt = con.prepareStatement(sql);
			stmt.setString(1, beartterId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				password = rs.getString("password");
			}

		} catch (SQLException e) {

			log.error(e.toString());
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();

		}

		return password;
	}


	public static CharacterParamEntity selectCharacterParamByBeartterId(String beartterId) throws SQLException {

		CharacterParamEntity characterParamEntity = new CharacterParamEntity();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = BeartterProperties.SQL_SELECT_CHARACTER_PARAM_BY_BEARTTER_ID;

		try {

			// コネクション取得
			con = DbConnection.getConnection();

			// 実行
			stmt = con.prepareStatement(sql);
			stmt.setString(1, beartterId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				characterParamEntity.setBeartterId(rs.getString("beartter_id"));
				characterParamEntity.setPretty(rs.getInt("pretty"));
				characterParamEntity.setKnowledge(rs.getInt("knowledge"));
				characterParamEntity.setArt(rs.getInt("art"));
				characterParamEntity.setCheerful(rs.getInt("cheerful"));
				characterParamEntity.setNerd(rs.getInt("nerd"));
			}

		} catch (SQLException e) {

			log.error(e.toString());
			throw e;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();

		}

		return characterParamEntity;
	}

}
