package com.iidaapp.beartter_demo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.iidaapp.beartter_demo.db.DbUtils;
import com.iidaapp.beartter_demo.entity.CharacterParamEntity;

public class BeartterUtils {

	public static Date getBirthDate(String year, String month, String day) {

		Date birthDate = Date.valueOf(year + "-" + month + "-" + day);
		return birthDate;
	}


	public static String encodePassdigiest(String password) throws NoSuchAlgorithmException {

		byte[] enclyptedHash = null;
		// MD5で暗号化したByte型配列を取得する
		MessageDigest md5 = null;

		md5 = MessageDigest.getInstance("SHA-256");
		md5.update(password.getBytes());
		enclyptedHash = md5.digest();
		// 暗号化されたByte型配列を、16進数表記文字列に変換する

		return bytesToHexString(enclyptedHash);
	}


	private static String bytesToHexString(byte[] fromByte) {

		StringBuilder hexStrBuilder = new StringBuilder();
		for (int i = 0; i < fromByte.length; i++) {

			// 16進数表記で1桁数値だった場合、2桁目を0で埋める
			if ((fromByte[i] & 0xff) < 0x10) {
				hexStrBuilder.append("0");
			}
			hexStrBuilder.append(Integer.toHexString(0xff & fromByte[i]));
		}

		return hexStrBuilder.toString();
	}


	public static String getCharacterName(String beartterId) throws SQLException {

		CharacterParamEntity characterParamEntity = new CharacterParamEntity();

		characterParamEntity = DbUtils.selectCharacterParamByBeartterId(beartterId);

		if (StringUtils.isEmpty(characterParamEntity.getBeartterId())) {
			throw new RuntimeException();
		}

		int art = characterParamEntity.getArt();
		int cheerful = characterParamEntity.getCheerful();
		int knowledge = characterParamEntity.getKnowledge();
		int nerd = characterParamEntity.getNerd();
		int pretty = characterParamEntity.getPretty();

		if ((art < 5 && cheerful < 5 && knowledge < 5 && nerd < 5 && pretty < 5)) {
			return "default_character";
		}

		if (art > cheerful && art > knowledge && art > pretty && art > nerd) {
			return "art_character";
		}

		if (cheerful > art && cheerful > knowledge && cheerful > pretty && cheerful > nerd) {
			return "cheerful_character";
		}

		if (knowledge > cheerful && knowledge > art && knowledge > pretty && knowledge > nerd) {
			return "knowledge_character";
		}

		if (pretty > cheerful && pretty > knowledge && pretty > art && pretty > nerd) {
			return "pretty_character";
		}

		if (nerd > cheerful && nerd > knowledge && nerd > art && nerd > pretty) {
			return "nerd_character";
		}

		return "default_character";
	}

}
