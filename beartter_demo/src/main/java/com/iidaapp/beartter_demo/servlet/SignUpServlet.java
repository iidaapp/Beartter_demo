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

		try {
			HttpSession session = req.getSession(false);
			if (session == null)
				resp.sendRedirect("http://127.0.0.1:8082/beartter_demo/error");

			AccessToken accessToken = (AccessToken) session.getAttribute("AccessToken");
			Twitter twitter = (Twitter) session.getAttribute("Twitter");
			User user;

			user = twitter.verifyCredentials();

			session.setAttribute("screenName", accessToken.getScreenName());
			session.setAttribute("profileImageUrl", user.getProfileImageURL());

			// リダイレクト
			resp.sendRedirect("http://127.0.0.1:8082/beartter_demo/signup_detail");

		} catch (TwitterException e) {
			// TODO ログ出力方法
			e.printStackTrace();
			resp.sendRedirect("http://127.0.0.1:8082/beartter_demo/error");
		}

	}
}
