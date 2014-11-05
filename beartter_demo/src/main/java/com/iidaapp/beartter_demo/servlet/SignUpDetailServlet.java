package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iidaapp.beartter_demo.db.DbUtils;

import twitter4j.auth.AccessToken;

public class SignUpDetailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		AccessToken accessToken = (AccessToken) session.getAttribute("AccessToken");
		long beartterId = 0;

		try {
			beartterId = DbUtils.selectBeartterIdByUserId(accessToken.getUserId());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(beartterId == 0){
			try {
				DbUtils.insertAccessToken(accessToken);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(Long.toString(beartterId));
	}
}
