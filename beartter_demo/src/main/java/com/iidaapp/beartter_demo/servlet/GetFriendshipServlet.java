package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Relationship;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

@WebServlet(name = "getFriendshipServlet", urlPatterns = { "/getfriendship" })
public class GetFriendshipServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2070897874066192558L;

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

		boolean isFollowRequestSent = user.isFollowRequestSent();

		Relationship relation = null;
		try {
			relation = twitter.showFriendship(twitter.getId(), userId);
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		if(relation == null)
			return;


		boolean isSourceFollowingTarget = relation.isSourceFollowingTarget();

		String json = "{\"isFollowRequestSent\":" + Boolean.toString(isFollowRequestSent) + ", \"isSourceFollowingTarget\":" + Boolean.toString(isSourceFollowingTarget) + "}";
		PrintWriter writer = null;
		try{
			writer = resp.getWriter();
			writer.print(json);

		}finally{
			if(writer != null)
				writer.close();
		}
		
		return;
	}
}
