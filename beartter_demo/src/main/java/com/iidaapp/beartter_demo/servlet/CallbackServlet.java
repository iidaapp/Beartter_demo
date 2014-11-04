package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class CallbackServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Twitter twitter = (Twitter) req.getSession().getAttribute("Twitter");
		RequestToken requestToken = (RequestToken) req.getSession().getAttribute("RequestToken");
		String verifier = req.getParameter("oauth_verifier");

		AccessToken accessToken = null;

		try {
			accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
			req.getSession().removeAttribute("RequestToken");
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		if (accessToken == null) {
			System.out.println("no accesstoken");

		}
	}
}
