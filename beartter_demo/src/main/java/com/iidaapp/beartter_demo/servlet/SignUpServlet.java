package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.auth.AccessToken;

import com.iidaapp.beartter_demo.util.BeartterProperties;

@WebServlet(name = "signUpServlet", urlPatterns = "/signup")
public class SignUpServlet extends HttpServlet {

	private static Logger log = LoggerFactory.getLogger(SignUpServlet.class);
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

		execute(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

		execute(req, resp);
	}


	private void execute(HttpServletRequest req, HttpServletResponse resp) {

		// ログ出力
		log.info(BeartterProperties.MESSAGE_START_SIGNUP_SERVLET);

		// セッションの取得
		HttpSession session = req.getSession(false);
		// NULLのとき、セッション無効のため、エラー画面へ遷移
		if(session == null) {
			log.error(BeartterProperties.MESSAGE_ERROR_NULL_SESSION);
			try {
				resp.sendRedirect("error");
				return;
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		}

		AccessToken accessToken = null;
		Twitter twitter = null;
		User user = null;
		try {
			// アクセストークンを取得し、Twitterオブジェクトからユーザ情報を取得
			accessToken = (AccessToken) session.getAttribute("AccessToken");
			twitter = (Twitter) session.getAttribute("Twitter");
			user = twitter.verifyCredentials();
		} catch (TwitterException e) {
			log.error(e.toString());
			try {
				resp.sendRedirect("error");
				return;
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		}

		// 表示するためのユーザ情報の格納
		session.setAttribute("screenName", accessToken.getScreenName());
		session.setAttribute("profileImageUrl", user.getProfileImageURL());

		try {
			// 遷移
			req.getRequestDispatcher("/page/SignUp.jsp").forward(req, resp);
			return;

		} catch (ServletException e) {
			log.error(e.toString());
			try {
				resp.sendRedirect("error");
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		} catch (IOException e) {
			log.error(e.toString());
		}
	}
}
