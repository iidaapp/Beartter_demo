package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "errorServlet", urlPatterns = { "/error" })
public class ErrorServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		execute(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		execute(req, resp);
	}


	private void execute(HttpServletRequest req, HttpServletResponse resp) {

		String errorDiscription = (String) req.getSession().getAttribute("errorDescription");
		req.getSession().invalidate();
		req.setAttribute("errorDiscription", errorDiscription);
		
		req.getRequestDispatcher("/page/Error.jsp");

	}
}
