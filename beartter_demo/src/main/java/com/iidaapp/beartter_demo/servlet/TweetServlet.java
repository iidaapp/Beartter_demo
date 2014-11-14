package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		Twitter twitter = (Twitter) req.getSession().getAttribute("twitter");
		String tweetText = req.getParameter("tweet_text");

		if(!validationTweetText(tweetText)) {
			req.getSession().setAttribute("error", "400");
			resp.sendRedirect("main");
			return;
		}

		TweetAnalysisUtil.executeAnalysis(tweetText);

		try {

			twitter.updateStatus(tweetText);

		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			if(e.getErrorCode() == 187) {

				req.getSession().setAttribute("error", "187");
				resp.sendRedirect("main");
				return;
			} else {
				resp.sendRedirect("error");
			}
		}

		resp.sendRedirect("main");
	}


	private boolean validationTweetText(String tweetText) {

		if(tweetText.length() > 140 || tweetText.length() == 0)
			return false;

		return true;

	}
}
