package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

@WebServlet(name = "sendFollowServlet", urlPatterns = "/sendfollow")
public class SendFollowServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2814232190771384114L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Twitter twitter = (Twitter) req.getSession().getAttribute("twitter");
		Long userId = Long.parseLong(req.getParameter("userId"));
		User user = null;

		try {
			user = twitter.createFriendship(userId);
		} catch (TwitterException e) {
			e.printStackTrace();
			return;
		}

		PrintWriter writer = null;
		try {
			writer = resp.getWriter();
			writer.print("success");

		} finally {
			if(writer != null)
				writer.close();
		}

	}
}
