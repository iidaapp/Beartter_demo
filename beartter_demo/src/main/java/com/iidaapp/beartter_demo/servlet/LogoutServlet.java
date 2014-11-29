package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iidaapp.beartter_demo.util.BeartterProperties;

/**
 * ログアウト処理クラス
 * @author iida
 *
 */
@WebServlet(name = "logoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

	private static Logger log = LoggerFactory.getLogger(LogoutServlet.class);
	private static final long serialVersionUID = 7107661025445474060L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

		// 共通処理へ
		execute(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

		// 共通処理へ
		execute(req, resp);
	}


	private void execute(HttpServletRequest req, HttpServletResponse resp) {

		log.info(BeartterProperties.MESSAGE_START_LOGOUT_SERVLET);

		// beartterIdがなければ通常遷移ではないため、エラー画面へ遷移
		String beartterId = (String) req.getSession().getAttribute("beartterId");
		if (StringUtils.isEmpty(beartterId)) {

			log.error(BeartterProperties.MESSAGE_ERROR_NULL_BEARTTER_ID);
			try {
				resp.sendRedirect("error");
				return;
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		}

		try {
			// ログアウト確認画面へ遷移
			req.getRequestDispatcher("/page/LogOutConfirm.jsp").forward(req, resp);
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
