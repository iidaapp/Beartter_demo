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

@WebServlet(name = "getProfileServlet", urlPatterns = { "/getprofile" })
public class GetProfileServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5488423071560251931L;


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Twitter twitter = (Twitter) req.getSession().getAttribute("twitter");
		Long userId = Long.parseLong(req.getParameter("userId"));
		User user = null;
		try {
			user = twitter.showUser(userId);
		} catch (TwitterException e) {

			e.printStackTrace();
			return;
		}

		if(user == null)
			return;

		String json = TwitterObjectFactory.getRawJSON(user);

		if(StringUtils.isEmpty(json))
			return;

		PrintWriter writer = resp.getWriter();

		writer.print(json);
		writer.close();

		return;
	}
}
