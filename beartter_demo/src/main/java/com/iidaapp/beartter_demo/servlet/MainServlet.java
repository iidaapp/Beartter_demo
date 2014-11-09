package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iidaapp.beartter_demo.TestStream;

public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		if (session == null)
			req.getRequestDispatcher("error");

		String beartterId = (String) session.getAttribute("beartterId");

		try {
			TestStream.testStream(beartterId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 遷移
		req.getRequestDispatcher("/page/MainTop.jsp").forward(req, resp);
		return;
	}
}
