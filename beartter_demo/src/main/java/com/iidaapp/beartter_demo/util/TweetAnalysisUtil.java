package com.iidaapp.beartter_demo.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;

import com.iidaapp.beartter_demo.db.DbUtils;
import com.iidaapp.beartter_demo.entity.CharacterParamEntity;
import com.iidaapp.beartter_demo.entity.CnameTypeEntity;
import com.iidaapp.beartter_demo.entity.PartOfSpeechTypeEntity;

public class TweetAnalysisUtil {

	public static void executeAnalysis(String tweetText, String beartterId) throws MalformedURLException, XmlRpcException, SQLException {

		Tokenizer tokenizer = Tokenizer.builder().build();
		List<Token> tokens = tokenizer.tokenize(tweetText);

		// URL設定
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL("http://d.hatena.ne.jp/xmlrpc"));

		// XmlRpcClient生成
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);

		// パラメータ設定
		Map<Object, Object> params = new HashMap<Object, Object>();
		params.put("body", tweetText);
		params.put("mode", "lite");
		params.put("score", 10); 

		HashMap<Object, Object> result = (HashMap<Object, Object>) client.execute("hatena.setKeywordLink", new Object[] { params });

		Object[] wordlist = (Object[]) result.get("wordlist");

		for (Object word : wordlist) {
			String wordString = word.toString();
			int cname = wordString.indexOf("cname");
			int refcount = wordString.indexOf("refcount");
			String afterCname = wordString.substring(cname, refcount);

			int equals = afterCname.indexOf("=");
			int comma = afterCname.indexOf(",");

			String cnameValue = afterCname.substring(equals + 1, comma);

			try {
				cnameAnalyse(cnameValue, beartterId);
			} catch (SQLException e) {

				e.printStackTrace();
				return;
			}

			System.out.println(cnameValue);
			System.out.println(word);
		}

		for (Token token : tokens) {
			partOfSpeechAnalyse(token.getPartOfSpeech(), beartterId);
		}
	}


	private static void cnameAnalyse(String cname, String beartterId) throws SQLException {

		if (StringUtils.isEmpty(cname)) {
			return;
		}

		CnameTypeEntity cnameEntity = DbUtils.selectCnameTypeByCname(cname);
		if (StringUtils.isEmpty(cnameEntity.getCname())) {
			return;
		}

		String parameterName = cnameEntity.getParameterName();
		int parameterValue = cnameEntity.getParameterValue();

		CharacterParamEntity characterParamEntity = new CharacterParamEntity();

		characterParamEntity = DbUtils.selectCharacterParamByBeartterId(beartterId);

		if (StringUtils.isEmpty(characterParamEntity.getBeartterId())) {
			throw new RuntimeException();
		}

		if(parameterName.equals("pretty"))
			parameterValue = parameterValue + characterParamEntity.getPretty();
		else if(parameterName.equals("knowledge"))
			parameterValue = parameterValue + characterParamEntity.getKnowledge();
		else if(parameterName.equals("art"))
			parameterValue = parameterValue + characterParamEntity.getArt();
		else if(parameterName.equals("cheerful"))
			parameterValue = parameterValue + characterParamEntity.getCheerful();
		else if(parameterName.equals("nerd"))
			parameterValue = parameterValue + characterParamEntity.getNerd();

		
		// TODO パラメータのカンスト値が必要
		DbUtils.updateCharacterParam(parameterName, parameterValue, beartterId);

		return;
	}

	
	private static void partOfSpeechAnalyse(String partOfSpeech, String beartterId) throws SQLException {
		
		int comma = partOfSpeech.indexOf(",");
		String partOfSpeechSubstring = partOfSpeech.substring(0, comma);

		if(StringUtils.isEmpty(partOfSpeechSubstring))
			return;
		
		PartOfSpeechTypeEntity entity = DbUtils.selectPartOfSpeechType(partOfSpeechSubstring);

		if(StringUtils.isEmpty(entity.getPartOfSpeech()))
			return;
		
		String parameterName = entity.getParameterName();
		int parameterValue = entity.getParameterValue();

		// TODO パラメータのカンスト値が必要
		DbUtils.updateCharacterParam(parameterName, parameterValue, beartterId);

		return;
	}
}
