package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

@WebServlet(name = "sendRemoveServlet", urlPatterns = { "/sendremove" })
public class SendRemoveServlet extends HttpServlet {

	private static Logger log = LoggerFactory.getLogger(SendRemoveServlet.class);
	private static final long serialVersionUID = -6693745201793650897L;


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

		Twitter twitter = (Twitter) req.getSession().getAttribute("twitter");
		Long userId = Long.parseLong(req.getParameter("userId"));

		try {
			twitter.destroyFriendship(userId);
		} catch (TwitterException e) {
			e.printStackTrace();
			return;
		}

		PrintWriter writer = null;
		try {
			writer = resp.getWriter();
			writer.print("success");

		} catch (IOException e) {

			log.error(e.toString());
			return;

		} finally {
			if(writer != null)
				writer.close();
		}
	}

}
