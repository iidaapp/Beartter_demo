package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.auth.AccessToken;

public class SignUpServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// ログ出力
		// TODO ログ出力方法
		System.out.println("SignUpServlet Start");

		// セッションの取得
		HttpSession session = req.getSession(false);
		// NULLのとき、セッション無効のため、エラー画面へ遷移
		if(session == null)
			req.getRequestDispatcher("/error").forward(req, resp);

		try {
			// アクセストークンを取得し、Twitterオブジェクトからユーザ情報を取得
			AccessToken accessToken = (AccessToken) session.getAttribute("AccessToken");
			Twitter twitter = (Twitter) session.getAttribute("Twitter");
			User user = twitter.verifyCredentials();

			// 念のため、以降使うAttributeの破棄
			session.removeAttribute("ValueExist");
			session.removeAttribute("NotSamePassword");
			session.removeAttribute("NotUniqueEMailAddress");
			session.removeAttribute("NotUniqueUserName");

			// 表示するためのユーザ情報の格納
			session.setAttribute("screenName", accessToken.getScreenName());
			session.setAttribute("profileImageUrl", user.getProfileImageURL());

			// 遷移
			req.getRequestDispatcher("/page/SignUp.jsp").forward(req, resp);
			return;

		} catch (TwitterException e) {
			// TODO ログ出力方法
			e.printStackTrace();
			req.getRequestDispatcher("/error").forward(req, resp);
		}

	}
}
