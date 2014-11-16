package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.xmlrpc.XmlRpcException;

import com.iidaapp.beartter_demo.util.TweetAnalysisUtil;

import twitter4j.Twitter;
import twitter4j.TwitterException;

@WebServlet(name = "tweetServlet", urlPatterns = "/tweet")
public class TweetServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1757900361778544777L;


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String beartterId = (String) req.getSession().getAttribute("beartterId");
		if (StringUtils.isEmpty(beartterId)) {
			req.setAttribute("errorDescription", "session is clear.");
			req.getRequestDispatcher("error");
			return;
		}

		Twitter twitter = (Twitter) req.getSession().getAttribute("twitter");
		String tweetText = req.getParameter("tweet_text");

		if (!validationTweetText(tweetText)) {

			req.getSession().setAttribute("error", "400");
			resp.sendRedirect("main");
			return;
		}

		try {
			TweetAnalysisUtil.executeAnalysis(tweetText, beartterId);

			twitter.updateStatus(tweetText);

		} catch (TwitterException e) {

			e.printStackTrace();

			if (e.getErrorCode() == 187) {

				req.getSession().setAttribute("error", "187");
				resp.sendRedirect("main");
				return;
			} else {
				resp.sendRedirect("error");
			}

		} catch (XmlRpcException e1) {

			e1.printStackTrace();
			resp.sendRedirect("error");

		} catch (SQLException e) {

			e.printStackTrace();
			resp.sendRedirect("error");
		}

		resp.sendRedirect("main");
	}


	private boolean validationTweetText(String tweetText) {

		if (tweetText.length() > 140 || tweetText.length() == 0)
			return false;

		return true;

	}
}
