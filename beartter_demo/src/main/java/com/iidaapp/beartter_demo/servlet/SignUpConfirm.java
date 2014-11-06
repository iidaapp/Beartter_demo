package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iidaapp.beartter_demo.util.BeartterUtils;
import com.iidaapp.beartter_demo.util.SignUpForm;

public class SignUpConfirm extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ServletContext sc = getServletContext();

		String userName = (String) req.getParameter("userName");
		String password = (String) req.getParameter("password");
		String passwordConfirm = (String) req.getParameter("passwordConfirm");
		String mailAddress = (String) req.getParameter("mailAddress");

		SignUpForm signUpForm = new SignUpForm(userName, password, passwordConfirm, mailAddress);

		try {

			if(!BeartterUtils.validateSignUpForm(signUpForm)){

				sc.getRequestDispatcher("/page/SignUp.jsp").forward(req, resp);

			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			resp.sendRedirect("error");
		} catch (SQLException e) {

			e.printStackTrace();
			resp.sendRedirect("error");
		}

		

	}
}
