package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class CallbackServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		Twitter twitter = (Twitter) session.getAttribute("Twitter");
		RequestToken requestToken = (RequestToken) session.getAttribute("RequestToken");
		String verifier = req.getParameter("oauth_verifier");

		AccessToken accessToken = null;
		try {
			accessToken = twitter.getOAuthAccessToken(requestToken, verifier);

			session.removeAttribute("RequestToken");
			session.setAttribute("AccessToken", accessToken);

		} catch (TwitterException e) {

			e.printStackTrace();
			resp.sendRedirect("http://127.0.0.1:8082/beartter_demo/error");
		}

		if (accessToken == null) {
			System.out.println("no accesstoken");
			resp.sendRedirect("http://127.0.0.1:8082/beartter_demo/error");

		}

		if(session.getAttribute("RequestServlet").equals("signup"))
			resp.sendRedirect("http://127.0.0.1:8082/beartter_demo/signup/detail");
	}
}
