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

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import com.iidaapp.beartter_demo.db.DbUtils;
import com.iidaapp.beartter_demo.entity.AccessTokenEntity;
import com.iidaapp.beartter_demo.util.StatusList;

@WebServlet(name = "mainServlet", urlPatterns = "/main")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		execute(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		execute(req, resp);
	}


	private void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		HttpSession session = req.getSession(false);
		if (session == null)
			req.getRequestDispatcher("error");

		String beartterId = (String) session.getAttribute("beartterId");
		List<AccessTokenEntity> accessTokenEntity = null;
		try {
			accessTokenEntity = DbUtils.selectAccessTokenFromAccessToken(beartterId);
			if (accessTokenEntity.size() == 0) {
				throw new Exception();
			}
		} catch (SQLException e) {
			req.getRequestDispatcher("error");
		} catch (Exception e) {
			req.getRequestDispatcher("error");
		}

		List<StatusList> statusList = new ArrayList<StatusList>();

		for (AccessTokenEntity entity : accessTokenEntity) {

			AccessToken accessToken = new AccessToken(entity.getoAuthToken(), entity.getoAuthSecret());
			Twitter twitter = new TwitterFactory().getInstance(accessToken);
			try {
				StatusList status = new StatusList(twitter.getHomeTimeline());
				statusList.add(status);
			} catch (TwitterException e) {
				req.getRequestDispatcher("error");
			}
		}
		// try {
		// TestStream.testStream(beartterId);
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		session.setAttribute("statusList", statusList);

		// 遷移
		req.getRequestDispatcher("/page/MainTop.jsp").forward(req, resp);
		return;
	}
}
