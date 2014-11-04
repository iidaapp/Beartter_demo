package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class SignUpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static String consumerKey = "ptN7vHD2pPLKSquWAcSdyHAzU";
	private static String consumerSecret = "JV22LP21h8VsRYWkouR6aACcMmX85D48C759QWgglu9CmDOLdJ";


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();

		req.getSession().setAttribute("twitter", twitter);

		try {
			RequestToken requestToken = twitter.getOAuthRequestToken("http://127.0.0.1:8082/beartter_demo/callback");

			HttpSession session = req.getSession();
			session.setAttribute("RequestToken", requestToken);
			session.setAttribute("Twitter", twitter);

			String url = requestToken.getAuthenticationURL();

			resp.sendRedirect(url);

		} catch (TwitterException e) {

			e.printStackTrace();
		}
	}
}
