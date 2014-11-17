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

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import com.iidaapp.beartter_demo.db.DbUtils;
import com.iidaapp.beartter_demo.entity.AccessTokenEntity;

/**
 * メイン画面表示処理
 * @author iida
 *
 */
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
		if(session == null) {
			req.setAttribute("errorDescription", "session is null.");
			req.getRequestDispatcher("error");
			return;
		}

		String beartterId = (String) session.getAttribute("beartterId");
		if(StringUtils.isEmpty(beartterId)) {
			req.setAttribute("errorDescription", "session is clear.");
			req.getRequestDispatcher("error");
			return;
		}

		String pagingNoString = req.getParameter("paging");
		if(pagingNoString == null)
			pagingNoString = "1";
		Integer pagingNo = Integer.parseInt(pagingNoString);

		Paging paging = new Paging(pagingNo);

		AccessTokenEntity accessTokenEntity = null;

		try {
			accessTokenEntity = DbUtils.selectAccessTokenFromAccessToken(beartterId);
		} catch (SQLException e) {
			session.setAttribute("errorDescription", e.getCause());
			req.getRequestDispatcher("error");
		}

		List<ResponseList<Status>> statusList = new ArrayList<ResponseList<Status>>();
		AccessToken accessToken = new AccessToken(accessTokenEntity.getoAuthToken(), accessTokenEntity.getoAuthSecret());
		Twitter twitter = new TwitterFactory().getInstance(accessToken);

		try {
			statusList.add(twitter.getHomeTimeline(paging));
		} catch (TwitterException e) {
			e.printStackTrace();
			session.setAttribute("errorDescription", e.getCause());
			req.getRequestDispatcher("error");
			return;
		}

		// try {
		// TestStream.testStream(beartterId);
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		String error = (String) session.getAttribute("error");

		if(!StringUtils.isEmpty(error)) {
			req.setAttribute("error", error);
			session.removeAttribute("error");
		}

		req.setAttribute("statusList", statusList);
		req.setAttribute("newLine", "\n");
		session.setAttribute("pagingNo", pagingNo);
		session.setAttribute("twitter", twitter);

		// 遷移
		req.getRequestDispatcher("/page/MainTop.jsp").forward(req, resp);
		return;
	}
}
