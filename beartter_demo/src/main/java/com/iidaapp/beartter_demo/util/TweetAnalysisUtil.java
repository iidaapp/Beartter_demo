package com.iidaapp.beartter_demo.util;

import java.util.Arrays;
import java.util.List;

import org.atilika.kuromoji.Token;
import org.atilika.kuromoji.Tokenizer;

public class TweetAnalysisUtil {

	public static void executeAnalysis(String tweetText) {
		
		Tokenizer tokenizer = Tokenizer.builder().build();
		List<Token> tokens = tokenizer.tokenize(tweetText);
		
		// 結果を出力してみる
		for (Token token : tokens) {
		    System.out.println("==================================================");
		    System.out.println("allFeatures : " + token.getAllFeatures());
		    System.out.println("partOfSpeech : " + token.getPartOfSpeech());
		    System.out.println("position : " + token.getPosition());
		    System.out.println("reading : " + token.getReading());
		    System.out.println("surfaceFrom : " + token.getSurfaceForm());
		    System.out.println("allFeaturesArray : " + Arrays.asList(token.getAllFeaturesArray()));
		    System.out.println("辞書にある言葉? : " + token.isKnown());
		    System.out.println("未知語? : " + token.isUnknown());
		    System.out.println("ユーザ定義? : " + token.isUser());
		}
	}

}
