package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.Twitter;
import twitter4j.TwitterException;

@WebServlet(name = "sendFollowServlet", urlPatterns = "/sendfollow")
public class SendFollowServlet extends HttpServlet {

	private static Logger log = LoggerFactory.getLogger(SendFollowServlet.class);
	private static final long serialVersionUID = 2814232190771384114L;


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		// 非同期処理のため、POST通信に制限
		
		// Twitter情報の取得
		Twitter twitter = (Twitter) req.getSession().getAttribute("twitter");
		// 対象ユーザーIDの取得
		Long userId = Long.parseLong(req.getParameter("userId"));

		try {
			// フォロー処理
			twitter.createFriendship(userId);
		} catch (TwitterException e) {
			log.error(e.toString());
			// エラーの場合は空のまま返却
			return;
		}

		PrintWriter writer = null;
		try {
			writer = resp.getWriter();
			writer.print("success");

		} catch (IOException e) {
			log.error(e.toString());
			return;
		} finally {
			if(writer != null)
				writer.close();
		}

	}
}
