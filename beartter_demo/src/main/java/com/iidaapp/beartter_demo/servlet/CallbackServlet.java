package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import com.iidaapp.beartter_demo.db.DbUtils;
import com.iidaapp.beartter_demo.util.BeartterProperties;

/**
 * TwitterOAuth認証からのコールバック処理
 * @author iida
 *
 */
@WebServlet(name = "twitterCallbackServlet", urlPatterns = "/callback")
public class CallbackServlet extends HttpServlet {

	private static Logger log = LoggerFactory.getLogger(CallbackServlet.class);
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

		// 共通処理へ
		execute(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

		// 共通処理へ
		execute(req, resp);
	}


	private void execute(HttpServletRequest req, HttpServletResponse resp) {

		log.info(BeartterProperties.MESSAGE_START_CALLBACK_SERVLET);

		HttpSession session = req.getSession(false);
		if(session == null) {
			log.error(BeartterProperties.MESSAGE_ERROR_NULL_SESSION);
			try {
				resp.sendRedirect("error");
			} catch (IOException e1) {
				log.error(e1.toString(), e1);
			}
			return;
		}

		// Twitterオブジェクトの取得
		Twitter twitter = (Twitter) session.getAttribute("Twitter");
		// リクエストトークンの取得
		RequestToken requestToken = (RequestToken) session.getAttribute("RequestToken");
		// oauth_verifierの取得
		String verifier = req.getParameter("oauth_verifier");

		// アクセストークンの取得
		AccessToken accessToken = null;

		try {
			accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
		} catch (TwitterException e) {
			log.error(e.toString());
			try {
				resp.sendRedirect("error");
				return;
			} catch (IOException e1) {
				log.error(e.toString());
				return;
			}
		}
		// リクエストトークンの破棄
		session.removeAttribute("RequestToken");

		// アクセストークンがNULLの場合は取得ミスにより、ログを出力してエラー画面へ遷移
		if(accessToken == null) {
			log.error(BeartterProperties.MESSAGE_ERROR_NULL_ACCESS_TOKEN);
			try {
				resp.sendRedirect("error");
				return;
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		}

		// アクセストークン情報が登録済みか判定
		String beartterId;
		try {
			beartterId = DbUtils.selectBeartterIdFromAccessToken(accessToken.getUserId());
		} catch (SQLException e) {
			log.error(BeartterProperties.MESSAGE_ERROR_NULL_ACCESS_TOKEN);
			try {
				resp.sendRedirect("error");
				return;
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		}

		// beartterIdが0の場合、SELECT取得なし。会員登録画面へ遷移
		if(StringUtils.isEmpty(beartterId)) {

			// アクセストークン、Twitterインスタンスをセッションに再格納
			session.setAttribute("AccessToken", accessToken);
			session.setAttribute("Twitter", twitter);

			// 会員登録画面へ
			try {
				resp.sendRedirect("signup");
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
			return;
		}

		// 取得ありの場合、認証完了。ログイン完了としてトップ画面へ遷移
		User user = null;

		try {
			user = twitter.verifyCredentials();
		} catch (TwitterException e) {

			log.error(e.toString());

			if(e.getErrorCode() == 88) {
				int secondsUntilReset = e.getRateLimitStatus().getSecondsUntilReset();
				session.setAttribute("secondsUntilReset", secondsUntilReset);
				try {
					resp.sendRedirect("limit");
				} catch (IOException e1) {
					log.error(e1.toString());
					return;
				}
				return;
			}

			try {
				resp.sendRedirect("error");
				return;
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		}

		// ProfileImageURLの取得
		session.setAttribute("profileImageUrl", user.getProfileImageURL());

		// beartterIdの格納
		session.setAttribute("beartterId", beartterId);
		// Main画面へ遷移
		try {
			resp.sendRedirect("main");
		} catch (IOException e1) {
			log.error(e1.toString());
			return;
		}
		return;

	}
}
