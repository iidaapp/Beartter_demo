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
import com.iidaapp.beartter_demo.util.SignUpFormValidateResults;

public class SignUpConfirm extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("SignUpConfirm Start");
		ServletContext sc = getServletContext();

		String userName = (String) req.getParameter("userName");
		String password = (String) req.getParameter("password");
		String passwordConfirm = (String) req.getParameter("passwordConfirm");
		String mailAddress = (String) req.getParameter("mailAddress");

		SignUpForm signUpForm = new SignUpForm(userName, password, passwordConfirm, mailAddress);

		SignUpFormValidateResults results = null;
		try {
			results = validateSignUpForm(signUpForm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(!results.isCheckAllValueExistInSignUpForm()){
			req.getSession().setAttribute("ValueExist", false);
			sc.getRequestDispatcher("/page/SignUp.jsp").forward(req, resp);
			return;
		}
		req.getSession().setAttribute("userName", userName);
		req.getSession().setAttribute("mailAddress", mailAddress);

		if(results.successAllValidate()){
			req.getSession().setAttribute("password", password);
			sc.getRequestDispatcher("/page/SignUpConfirm.jsp");
			return;
		}
		
		req.getSession().setAttribute("NotSamePassword", !results.isSamePassword());
		req.getSession().setAttribute("NotUniqueEMailAddress", !results.isUniqueEMailAddress());
		req.getSession().setAttribute("NotUniqueUserName", !results.isUniqueUserNameSignUpForm());

		sc.getRequestDispatcher("/page/SignUp.jsp");
		return;

	}


	private static SignUpFormValidateResults validateSignUpForm(SignUpForm signUpForm) throws SQLException {

		SignUpFormValidateResults results = new SignUpFormValidateResults();
		if (!BeartterUtils.checkValueExistInSignUpForm(signUpForm)) {
			results.setCheckValueExistInSignUpForm(false);
			return results;
		}

		results.setCheckValueExistInSignUpForm(true);
		results.setSamePassword(BeartterUtils.isSamePassword(signUpForm.getPassword(), signUpForm.getPasswordConfirm()));
		results.setUniqueEMailAddress(BeartterUtils.isUniqueEMailAddress(signUpForm.getMailAddress()));
		results.setUniqueUserNameSignUpForm(BeartterUtils.isUniqueUserNameSignUpForm(signUpForm.getUserName()));

		return results;
	}
}
