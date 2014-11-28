package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;
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

		HttpSession session = req.getSession(false);
		if(session == null) {
			log.error(BeartterProperties.MESSAGE_ERROR_NULL_SESSION);
			try {
				resp.sendRedirect("error");
				return;
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		}

		String beartterId = (String) session.getAttribute("beartterId");
		if(StringUtils.isEmpty(beartterId)) {

			log.error(BeartterProperties.MESSAGE_ERROR_NULL_BEARTTER_ID);
			try {
				resp.sendRedirect("/beartter/");
				return;
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		}

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
