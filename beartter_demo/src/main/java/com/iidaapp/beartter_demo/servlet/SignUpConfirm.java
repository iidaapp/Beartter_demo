package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iidaapp.beartter_demo.util.BeartterUtils;
import com.iidaapp.beartter_demo.util.SignUpForm;
import com.iidaapp.beartter_demo.util.SignUpFormValidateResults;

public class SignUpConfirm extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// ログ出力
		// TODO ログ出力方法
		System.out.println("SignUpConfirm Start");

		// セッションの取得
		HttpSession session = req.getSession();

		// Formの入力情報の取得
		String userName = (String) req.getParameter("userName");
		String password = (String) req.getParameter("password");
		String passwordConfirm = (String) req.getParameter("passwordConfirm");
		String mailAddress = (String) req.getParameter("mailAddress");

		// Form入力オブジェクト生成
		SignUpForm signUpForm = new SignUpForm(userName, password, passwordConfirm, mailAddress);

		// バリデーション結果オブジェクトにバリデーション結果の格納
		SignUpFormValidateResults results = null;
		try {
			results = validateSignUpForm(signUpForm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// バリデーション結果情報をセッションに格納
		setAttributeByValidationResults(session, results);

		// 入力情報をセッションに格納
		session.setAttribute("userName", userName);
		session.setAttribute("mailAddress", mailAddress);

		// ひとつでも空欄の入力がある場合、入力画面へ遷移
		if (!results.isCheckAllValueExistInSignUpForm()) {

			req.getRequestDispatcher("/page/SignUp.jsp").forward(req, resp);
			return;
		}

		// すべてのバリデーションがOKの場合、パスワードもセッションに格納して確認画面へ遷移
		if (results.successAllValidate()) {
			session.setAttribute("password", password);
			req.getRequestDispatcher("/page/SignUpConfirm.jsp").forward(req, resp);
			return;
		}

		// ひとつでもバリデーションがNGの場合、入力画面へ遷移
		req.getRequestDispatcher("/page/SignUp.jsp").forward(req, resp);
		return;

	}


	/**
	 * バリデーション結果情報を元に、セッションに情報を格納する
	 * @param session
	 * @param results
	 */
	private void setAttributeByValidationResults(HttpSession session, SignUpFormValidateResults results) {

		if (!results.isCheckAllValueExistInSignUpForm()) {
			session.setAttribute("ValueExist", false);
			session.removeAttribute("SamePassword");
			session.removeAttribute("UniqueEMailAddress");
			session.removeAttribute("UniqueUserName");
			session.removeAttribute("CorrectEmailAddress");
			return;
		}

		session.removeAttribute("ValueExist");

		if (results.successAllValidate()) {
			session.removeAttribute("SamePassword");
			session.removeAttribute("UniqueEMailAddress");
			session.removeAttribute("UniqueUserName");
			session.removeAttribute("CorrectEmailAddress");

			return;
		}

		session.setAttribute("SamePassword", results.isSamePassword());
		session.setAttribute("UniqueEMailAddress", results.isUniqueEMailAddress());
		session.setAttribute("UniqueUserName", results.isUniqueUserNameSignUpForm());
		session.setAttribute("CorrectEmailAddress", results.isCorrectEmailAddress());

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
		results.setUniqueEMailAddress(BeartterUtils.isUniqueEmailAddress(signUpForm.getMailAddress()));
		results.setUniqueUserNameSignUpForm(BeartterUtils.isUniqueUserNameSignUpForm(signUpForm.getUserName()));
		results.setCorrectEmailAddress(BeartterUtils.isCorrectEmailAddress(signUpForm.getMailAddress()));

		return results;
	}
}
