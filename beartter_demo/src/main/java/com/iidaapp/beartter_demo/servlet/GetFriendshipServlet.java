package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.Relationship;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

import com.iidaapp.beartter_demo.util.BeartterProperties;

/**
 * Twitterフォロー関係を取得するクラス
 * @author iida
 *
 */
@WebServlet(name = "getFriendshipServlet", urlPatterns = { "/getfriendship" })
public class GetFriendshipServlet extends HttpServlet {

	private static Logger log = LoggerFactory.getLogger(GetFriendshipServlet.class);
	private static final long serialVersionUID = -2070897874066192558L;


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		// 非同期で処理を行うため、ここはPOSTでのみ受け取る

		log.info(BeartterProperties.MESSAGE_START_GET_FRIENDSHIP_SERVLET);

		// Twitter情報を取得
		Twitter twitter = (Twitter) req.getSession().getAttribute("twitter");
		if(twitter == null) {

			log.error(BeartterProperties.MESSAGE_ERROR_NULL_TWITTER);
			return;
		}

		// 対象のユーザIDを取得
		Long userId = Long.parseLong(req.getParameter("userId"));
		User user = null;

		// ユーザ情報を取得
		try {
			user = twitter.showUser(userId);
		} catch (TwitterException e) {
			// エラーが起こった場合はログに出力して空で結果を返す
			log.error(e.toString());
			return;
		}

		// 念のため、ユーザ情報がNULLの場合は結果を空で返す
		if(user == null)
			return;

		// フォローリクエストを送っているかの情報を取得
		boolean isFollowRequestSent = user.isFollowRequestSent();

		Relationship relation = null;
		try {
			// ユーザと対象ユーザのTwitter関係情報を取得
			relation = twitter.showFriendship(twitter.getId(), userId);
		} catch (IllegalStateException | TwitterException e) {
			// エラーが起こった場合はログに出力して空で結果を返す
			log.error(e.toString());
			return;
		}

		// 念のため、Twitter関係情報がNULLの場合は結果を空で返す
		if(relation == null)
			return;

		// ユーザが対象ユーザをフォローしているかを取得
		boolean isSourceFollowingTarget = relation.isSourceFollowingTarget();

		// 結果をJSON形式に変換して返却
		String json = "{\"isFollowRequestSent\":" + Boolean.toString(isFollowRequestSent) + ", \"isSourceFollowingTarget\":" + Boolean.toString(isSourceFollowingTarget) + "}";
		PrintWriter writer = null;
		try {
			writer = resp.getWriter();
			writer.print(json);

		} catch (IOException e) {

			log.error(e.toString());
			return;

		} finally {
			if(writer != null)
				writer.close();
		}

		return;
	}

}
