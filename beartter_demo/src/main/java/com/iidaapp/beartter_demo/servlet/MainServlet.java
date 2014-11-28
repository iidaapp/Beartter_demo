package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

import com.iidaapp.beartter_demo.db.DbUtils;
import com.iidaapp.beartter_demo.entity.AccessTokenEntity;
import com.iidaapp.beartter_demo.util.BeartterProperties;

/**
 * メイン画面表示処理
 * @author iida
 *
 */
@WebServlet(name = "mainServlet", urlPatterns = "/main")
public class MainServlet extends HttpServlet {

	private static Logger log = LoggerFactory.getLogger(MainServlet.class);
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


		HttpSession session = req.getSession(false);
		if (session == null) {
			log.error(BeartterProperties.MESSAGE_ERROR_NULL_SESSION);
			try {
				resp.sendRedirect("error");
			} catch (IOException e1) {
				log.error(e1.toString());
			}
			return;
		}

		String beartterId = (String) session.getAttribute("beartterId");

		if(StringUtils.isEmpty(beartterId)) {

			log.error(BeartterProperties.MESSAGE_ERROR_NULL_BEARTTER_ID);
			try {
				resp.sendRedirect("error");
				return;
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		}

		
		String pagingNoString = req.getParameter("paging");
		if (pagingNoString == null)
			pagingNoString = "1";
		Integer pagingNo = Integer.parseInt(pagingNoString);

		Paging paging = new Paging(pagingNo);

		AccessTokenEntity accessTokenEntity = null;

		try {
			accessTokenEntity = DbUtils.selectAccessTokenFromAccessToken(beartterId);
		} catch (SQLException e) {
			
			log.error(e.toString());
			try {
				resp.sendRedirect("error");
				return;
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		}

		List<ResponseList<Status>> statusList = new ArrayList<ResponseList<Status>>();
		Twitter twitter = (Twitter) session.getAttribute("Twitter");

		try {
			statusList.add(twitter.getHomeTimeline(paging));
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


		// TODO ストリームによるTLの取得処理の実装

		// try {
		// TestStream.testStream(beartterId);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }

		String error = (String) session.getAttribute("error");

		if (!StringUtils.isEmpty(error)) {
			req.setAttribute("error", error);
			session.removeAttribute("error");
		}

		req.setAttribute("statusList", statusList);
		req.setAttribute("newLine", "\n");
		session.setAttribute("pagingNo", pagingNo);
		session.setAttribute("twitter", twitter);

		// 遷移
		try {
			req.getRequestDispatcher("/page/MainTop.jsp").forward(req, resp);
		} catch (ServletException e) {
			log.error(e.toString());
			try {
				resp.sendRedirect("error");
				return;
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		} catch (IOException e) {
			log.error(e.toString());
			return;
		}
		return;
	}
}
