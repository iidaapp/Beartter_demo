package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.xmlrpc.XmlRpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.iidaapp.beartter_demo.util.TweetAnalysisUtil;

@WebServlet(name = "tweetServlet", urlPatterns = "/tweet")
public class TweetServlet extends HttpServlet {

	private static Logger log = LoggerFactory.getLogger(TweetServlet.class);
	private static final long serialVersionUID = 1757900361778544777L;


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

		try {
			// 文字コード設定
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e2) {
			log.error(e2.toString());
			try {
				resp.sendRedirect("error");
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		}

		String beartterId = (String) req.getSession().getAttribute("beartterId");
		if(StringUtils.isEmpty(beartterId)) {
			req.setAttribute("errorDescription", "session is clear.");
			req.getRequestDispatcher("error");
			return;
		}

		Twitter twitter = (Twitter) req.getSession().getAttribute("twitter");
		String tweetText = req.getParameter("tweet_text");

		if(!validationTweetText(tweetText)) {

			req.getSession().setAttribute("error", "400");
			try {
				resp.sendRedirect("main");
			} catch (IOException e) {
				log.error(e.toString());
				return;
			}
			return;
		}

		try {
			TweetAnalysisUtil.executeAnalysis(tweetText, beartterId);
		} catch (MalformedURLException | XmlRpcException | SQLException e2) {

			log.error(e2.toString());
			try {
				resp.sendRedirect("error");
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
			return;
		}

		try {
			twitter.updateStatus(tweetText);

		} catch (TwitterException e) {

			e.printStackTrace();

			if(e.getErrorCode() == 187) {

				req.getSession().setAttribute("error", "187");
				try {
					resp.sendRedirect("main");
				} catch (IOException e1) {
					log.error(e1.toString());
					return;
				}
				return;
			} else {
				try {
					resp.sendRedirect("error");
				} catch (IOException e1) {
					log.error(e1.toString());
					return;
				}
			}

		}

		try {
			resp.sendRedirect("main");
		} catch (IOException e) {
			log.error(e.toString());
			return;
		}
	}


	private boolean validationTweetText(String tweetText) {

		if(tweetText.length() > 140 || tweetText.length() == 0)
			return false;

		return true;

	}
}
