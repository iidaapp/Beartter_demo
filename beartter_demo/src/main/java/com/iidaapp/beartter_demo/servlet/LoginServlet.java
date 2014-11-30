package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

import com.iidaapp.beartter_demo.util.BeartterProperties;

/**
 * ログイン処理クラス
 * @author iida
 *
 */
@WebServlet(name="loginServlet", urlPatterns="/login")
public class LoginServlet extends HttpServlet {

	private static Logger log = LoggerFactory.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp){

		// 共通処理へ
		execute(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp){

		// 共通処理へ
		execute(req, resp);
	}
	
	
	private void execute(HttpServletRequest req, HttpServletResponse resp){


		log.info(BeartterProperties.MESSAGE_START_LOGIN_SERVLET);

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

			log.error(e.toString());
			try {
				resp.sendRedirect("error");
			} catch (IOException e1) {
				log.error(e1.toString());
			}

		}catch (IOException e) {

			log.error(e.toString());
			try {
				resp.sendRedirect("error");
			} catch (IOException e1) {
				log.error(e1.toString());
			}

		}
	}
}
