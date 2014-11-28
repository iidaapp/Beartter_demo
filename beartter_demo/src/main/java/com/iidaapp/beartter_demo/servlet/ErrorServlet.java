package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iidaapp.beartter_demo.util.BeartterProperties;

/**
 * 共通エラー処理
 * @author iida
 *
 */
@WebServlet(name = "errorServlet", urlPatterns = { "/error" })
public class ErrorServlet extends HttpServlet {

	private static Logger log = LoggerFactory.getLogger(ErrorServlet.class);
	private static final long serialVersionUID = -3820104047647816529L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp){

		execute(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp){

		execute(req, resp);
	}


	private void execute(HttpServletRequest req, HttpServletResponse resp) {

		log.info(BeartterProperties.MESSAGE_START_ERROR_SERVLET);

		// セッションの情報をすべてクリア
		req.getSession().invalidate();

		try {
			// エラー画面へ遷移
			req.getRequestDispatcher("/page/error/Error.jsp").forward(req, resp);
			return;
		} catch (ServletException | IOException e) {

			log.error(e.toString());
			return;
		}

	}
}
