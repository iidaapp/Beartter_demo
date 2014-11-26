package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iidaapp.beartter_demo.util.BeartterProperties;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 * ログイン処理クラス
 * @author iida
 *
 */
@WebServlet(name="loginServlet", urlPatterns="/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 共通処理へ
		execute(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 共通処理へ
		execute(req, resp);
	}
	
	
	private void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {


		System.out.println(BeartterProperties.MESSAGE_START_LOGIN_SERVLET);

		// TwitterFactoryの設定
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		TwitterFactory tf = new TwitterFactory(cb.build());

		// Twitterインスタンス取得
		Twitter twitter = tf.getInstance();

		// セッション取得
		HttpSession session = req.getSession();

		try {
			// リクエストトークン取得
			RequestToken requestToken = twitter.getOAuthRequestToken(BeartterProperties.VALUE_TWITTER_CALLBACK_URL);

			// セッションに各情報を格納
			session.setAttribute("RequestToken", requestToken);
			session.setAttribute("Twitter", twitter);
			session.setAttribute("RequestServlet", "login");

			// 念のため、以降使うAttributeの破棄
			session.removeAttribute("ValueExist");
			session.removeAttribute("SamePassword");
			session.removeAttribute("UniqueEMailAddress");
			session.removeAttribute("UniqueUserName");
			session.removeAttribute("CorrectEmailAddress");

			// 認証URLの取得（強制ログイン）
			String url = requestToken.getAuthenticationURL() + "&force_login=true";

			// リダイレクト
			resp.sendRedirect(url);

		} catch (TwitterException e) {

			// TODO ログ出力方法
			e.printStackTrace();
			session.setAttribute("errorDescription", e.getCause());
			resp.sendRedirect("error");

		}
	}
}
