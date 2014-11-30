package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iidaapp.beartter_demo.db.DbUtils;
import com.iidaapp.beartter_demo.util.BeartterProperties;
import com.iidaapp.beartter_demo.util.BeartterUtils;
import com.iidaapp.beartter_demo.util.ValidationUtils;

@WebServlet(name = "secedeServlet", urlPatterns = "/secede")
public class SecedeServlet extends HttpServlet {

	private static Logger log = LoggerFactory.getLogger(SecedeServlet.class);
	private static final long serialVersionUID = -6490181083278582428L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

		req.getRequestDispatcher("error");
		return;
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

		execute(req, resp);
	}


	private void execute(HttpServletRequest req, HttpServletResponse resp) {

		try {
			// 文字コード設定
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e2) {
			log.error(e2.toString());
			try {
				resp.sendRedirect("error");
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		}

		// セッションが空の場合はエラー
		HttpSession session = req.getSession(false);
		if (session == null) {
			log.error(BeartterProperties.MESSAGE_ERROR_NULL_SESSION);
			try {
				resp.sendRedirect("error");
				return;
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		}

		// beartterIdがNULLの場合はエラー
		String beartterId = (String) session.getAttribute("beartterId");
		if (StringUtils.isEmpty(beartterId)) {

			log.error(BeartterProperties.MESSAGE_ERROR_NULL_BEARTTER_ID);
			try {
				resp.sendRedirect("/beartter/");
				return;
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		}

		String password = (String) req.getParameter("password");
		String passwordEncode = null;
		String errorMessage = null;

		if (StringUtils.isEmpty(password)) {
			// error
			errorMessage = BeartterProperties.MESSAGE_ERROR_SECEDE_NULL_PASSWORD;
			req.setAttribute("errorMessage", errorMessage);
			try {

				req.getRequestDispatcher("/page/Settings.jsp").forward(req, resp);

			} catch (ServletException e) {
				log.error(e.toString());
				try {
					resp.sendRedirect("error");
					return;
				} catch (IOException e1) {
					log.error(e1.toString());
					return;
				}
			} catch (IOException e) {
				log.error(e.toString());
				return;
			}
			return;
		}

		if (!ValidationUtils.isCorrectPassword(password)) {
			// error
			errorMessage = BeartterProperties.MESSAGE_ERROR_SECEDE_NOT_CORRECT_PASSWORD;
			req.setAttribute("errorMessage", errorMessage);
			try {

				req.getRequestDispatcher("/page/Settings.jsp").forward(req, resp);

			} catch (ServletException e) {
				log.error(e.toString());
				try {
					resp.sendRedirect("error");
					return;
				} catch (IOException e1) {
					log.error(e1.toString());
					return;
				}
			} catch (IOException e) {
				log.error(e.toString());
				return;
			}
			return;
		}

		String passwordUserinfo = null;
		try {
			passwordUserinfo = DbUtils.selectPasswordFromUserinfoByBeatterId(beartterId);
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

		if (StringUtils.isEmpty(passwordUserinfo)) {
			// error
			throw new RuntimeException();
		}

		try {
			passwordEncode = BeartterUtils.encodePassdigiest(password);
		} catch (NoSuchAlgorithmException e) {
			log.error(e.toString());
			try {
				resp.sendRedirect("error");
				return;
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		}
		
		if(!passwordUserinfo.equals(passwordEncode)){
			// error
			errorMessage = BeartterProperties.MESSAGE_ERROR_SECEDE_NOT_SAME_PASSWORD;
			req.setAttribute("errorMessage", errorMessage);
			try {

				req.getRequestDispatcher("/page/Settings.jsp").forward(req, resp);

			} catch (ServletException e) {
				log.error(e.toString());
				try {
					resp.sendRedirect("error");
					return;
				} catch (IOException e1) {
					log.error(e1.toString());
					return;
				}
			} catch (IOException e) {
				log.error(e.toString());
				return;
			}
			return;
		}

		// beartterIdをキーにすべての個人情報を削除
		try {
			DbUtils.removeAllData(beartterId);
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

		// すべてのセッションの削除
		req.getSession().invalidate();

		try {
			// 退会完了ページへ遷移
			req.getRequestDispatcher("/page/Secede.jsp").forward(req, resp);
			return;

		} catch (ServletException | IOException e) {

			log.error(e.toString());
			try {
				resp.sendRedirect("error");
				return;
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		}
	}

}
