package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iidaapp.beartter_demo.util.BeartterUtils;
import com.iidaapp.beartter_demo.util.SignUpForm;
import com.iidaapp.beartter_demo.util.SignUpFormValidateResults;

@WebServlet(name = "signUpConfirm", urlPatterns = "/confirm")
public class SignUpConfirm extends HttpServlet {

	private static Logger log = LoggerFactory.getLogger(SignUpConfirm.class);
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

		execute(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

		execute(req, resp);

	}


	private void execute(HttpServletRequest req, HttpServletResponse resp) {

		// TODO ログ出力方法
		System.out.println("SignUpConfirm Start");

		// セッションの取得
		HttpSession session = req.getSession();

		// Formの入力情報の取得
		String userName = (String) req.getParameter("userName");
		String mailAddress = (String) req.getParameter("mailAddress");
		String year = (String) req.getParameter("year");
		String month = (String) req.getParameter("month");
		String day = (String) req.getParameter("day");
		String password = (String) req.getParameter("password");
		String passwordConfirm = (String) req.getParameter("passwordConfirm");

		// Form入力オブジェクト生成
		SignUpForm signUpForm = new SignUpForm(userName, password, passwordConfirm, mailAddress, year, month, day);

		// バリデーション結果オブジェクトにバリデーション結果の格納
		SignUpFormValidateResults results = null;
		try {
			results = validateSignUpForm(signUpForm);
		} catch (SQLException e) {
			log.error(e.toString());
			try {
				resp.sendRedirect("error");
				return;
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		}

		// バリデーション結果情報をセッションに格納
		setAttributeByValidationResults(req, results);

		// 入力情報をセッションに格納
		session.setAttribute("signUpForm", signUpForm);
		resp.setCharacterEncoding("UTF-8");

		// ひとつでも空欄の入力がある場合、入力画面へ遷移
		if(!results.isCheckAllValueExistInSignUpForm()) {

			try {
				req.getRequestDispatcher("signup").forward(req, resp);
			} catch (ServletException e) {
				log.error(e.toString());
				try {
					resp.sendRedirect("error");
				} catch (IOException e1) {
					log.error(e1.toString());
					return;
				}
			} catch (IOException e) {
				log.error(e.toString());
			}
			return;
		}

		// すべてのバリデーションがOKの場合確認画面へ遷移
		if(results.successAllValidate()) {

			try {
				req.getRequestDispatcher("/page/SignUpConfirm.jsp").forward(req, resp);
			} catch (ServletException e) {
				log.error(e.toString());
				try {
					resp.sendRedirect("error");
				} catch (IOException e1) {
					log.error(e1.toString());
					return;
				}
			} catch (IOException e) {
				log.error(e.toString());
			}
			return;
		}

		// ひとつでもバリデーションがNGの場合、入力画面へ遷移
		try {
			req.getRequestDispatcher("signup").forward(req, resp);
		} catch (ServletException e) {
			log.error(e.toString());
			try {
				resp.sendRedirect("error");
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		} catch (IOException e) {
			log.error(e.toString());
		}
		return;
	}


	/**
	 * バリデーション結果情報を元に、セッションに情報を格納する
	 * @param session
	 * @param results
	 */
	private void setAttributeByValidationResults(HttpServletRequest req, SignUpFormValidateResults results) {

		if(!results.isCheckAllValueExistInSignUpForm()) {
			req.setAttribute("ValueExist", false);
			return;
		}

		if(results.successAllValidate()) {

			return;
		}

		req.setAttribute("SamePassword", results.isSamePassword());
		req.setAttribute("UniqueEMailAddress", results.isUniqueEMailAddress());
		req.setAttribute("UniqueUserName", results.isUniqueUserNameSignUpForm());
		req.setAttribute("CorrectEmailAddress", results.isCorrectEmailAddress());
		req.setAttribute("CorrectBirthDate", results.isCorrectBirthDate());
		req.setAttribute("CorrectDigit", results.isCorrectDigit());

		return;
	}


	private SignUpFormValidateResults validateSignUpForm(SignUpForm signUpForm) throws SQLException {

		SignUpFormValidateResults results = new SignUpFormValidateResults();
		if(!BeartterUtils.checkValueExistInSignUpForm(signUpForm)) {
			results.setCheckValueExistInSignUpForm(false);
			return results;
		}

		results.setCheckValueExistInSignUpForm(true);
		results.setSamePassword(BeartterUtils.isSamePassword(signUpForm.getPassword(), signUpForm.getPasswordConfirm()));
		results.setUniqueEMailAddress(BeartterUtils.isUniqueEmailAddress(signUpForm.getMailAddress()));
		results.setUniqueUserNameSignUpForm(BeartterUtils.isUniqueUserNameSignUpForm(signUpForm.getUserName()));
		results.setCorrectEmailAddress(BeartterUtils.isCorrectEmailAddress(signUpForm.getMailAddress()));
		results.setCorrectBirthDate(BeartterUtils.isCorrectBirthDate(signUpForm.getYear(), signUpForm.getMonth(), signUpForm.getDay()));
		results.setCorrectDigit(BeartterUtils.isCorrectDigit(signUpForm));

		return results;
	}
}
