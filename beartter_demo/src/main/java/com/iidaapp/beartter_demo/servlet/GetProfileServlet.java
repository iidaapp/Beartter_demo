package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;
import twitter4j.User;

/**
 * ユーザプロフィール情報を取得するクラス
 * @author iida
 *
 */
@WebServlet(name = "getProfileServlet", urlPatterns = { "/getprofile" })
public class GetProfileServlet extends HttpServlet {

	private static final long serialVersionUID = -5488423071560251931L;


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 非同期で処理を行うため、ここはPOSTでのみ受け取る

		// Twitter情報を取得
		Twitter twitter = (Twitter) req.getSession().getAttribute("twitter");

		// 対象のユーザIDを取得
		Long userId = Long.parseLong(req.getParameter("userId"));
		User user = null;

		// ユーザ情報を取得
		try {
			user = twitter.showUser(userId);
		} catch (TwitterException e) {
			// エラーが起こった場合はログに出力して空で結果を返す
			e.printStackTrace();
			return;
		}

		// 念のため、ユーザ情報がNULLの場合は結果を空で返す
		if (user == null)
			return;

		// 結果を元のJSONデータで取得する
		String json = TwitterObjectFactory.getRawJSON(user);

		// 万が一JSONデータがNULLの場合は、結果を空で返す
		if (StringUtils.isEmpty(json))
			return;

		// 文字コード設定
		resp.setCharacterEncoding("UTF-8");
		// 結果を返す
		PrintWriter writer = null;

		try {

			writer = resp.getWriter();
			writer.print(json);

		} finally {

			if (writer != null)
				writer.close();
		}

		return;
	}
}
