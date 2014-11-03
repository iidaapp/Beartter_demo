package com.iidaapp.beartter_demo;

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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Twitter twitter = (Twitter) req.getSession().getAttribute("Twitter");
		String verifier = req.getParameter("oauth_verifier");
		AccessToken accessToken = null;
		try {
			accessToken = twitter.getOAuthAccessToken((RequestToken)req.getSession().getAttribute("RequestToken"), verifier);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (accessToken != null) {
			// AccessTokenをセッションに保持
//			session.setAttribute("AccessToken", accessToken.getToken());
//			session.setAttribute("AccessTokenSecret", accessToken.getTokenSecret());
			
			System.out.print(accessToken.getToken());
			System.out.println(accessToken.getTokenSecret());
		} else {
			resp.setContentType("text/plain");
			resp.getWriter().println("AccessTokenがNullってます！");
		}
	}
}
