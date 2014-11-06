package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import com.iidaapp.beartter_demo.db.DbUtils;

public class CallbackServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		if (session == null) {
			resp.sendRedirect("error");
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
			// リクエストトークンの破棄
			session.removeAttribute("RequestToken");

			// アクセストークンがNULLの場合は取得ミスにより、ログを出力してエラー画面へ遷移
			if (accessToken == null) {
				// TODO ログ出力方法
				System.out.println("no accesstoken");
				resp.sendRedirect("error");
				return;
			}

			// アクセストークン、Twitterインスタンスをセッションに再格納
			session.setAttribute("AccessToken", accessToken);
			session.setAttribute("Twitter", twitter);

			// アクセストークン情報が登録済みか判定
			long beatterId = DbUtils.selectBeartterIdFromAccessToken(accessToken.getUserId());

			// beartterIdが0の場合、SELECT取得なし。会員登録画面へ遷移
			if (beatterId == 0) {
				resp.sendRedirect("signup");
				return;
			}

			// 取得ありの場合、認証完了。ログイン完了としてトップ画面へ遷移
			resp.sendRedirect("main");
			return;

		} catch (TwitterException | ClassNotFoundException | SQLException e) {

			// Extentionをキャッチした場合、ログを出力してエラー画面へ遷移。
			e.printStackTrace();
			resp.sendRedirect("error");
			return;
		}
	}
}
