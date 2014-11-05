package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static String consumerKey = "ptN7vHD2pPLKSquWAcSdyHAzU";
	private static String consumerSecret = "JV22LP21h8VsRYWkouR6aACcMmX85D48C759QWgglu9CmDOLdJ";


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TwitterFactoryの設定
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret);
		TwitterFactory tf = new TwitterFactory(cb.build());

		// Twitterインスタンス取得
		Twitter twitter = tf.getInstance();

		// セッション取得
		HttpSession session = req.getSession();

		try {
			// リクエストトークン取得
			RequestToken requestToken = twitter.getOAuthRequestToken("http://127.0.0.1:8082/beartter_demo/callback");

			// セッションに各情報を格納
			session.setAttribute("RequestToken", requestToken);
			session.setAttribute("Twitter", twitter);
			session.setAttribute("RequestServlet", "login");

			// 認証URLの取得（強制ログイン）
			String url = requestToken.getAuthenticationURL() + "&force_login=true";

			// リダイレクト
			resp.sendRedirect(url);

		} catch (TwitterException e) {

			// TODO ログ出力方法
			e.printStackTrace();
			resp.sendRedirect("http://127.0.0.1:8082/beartter_demo/error");

		}
	}
}
