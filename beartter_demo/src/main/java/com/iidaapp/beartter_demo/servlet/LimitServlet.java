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

@WebServlet(name = "limitServlet", urlPatterns = "/limit")
public class LimitServlet extends HttpServlet {

	private static Logger log = LoggerFactory.getLogger(LimitServlet.class);
	private static final long serialVersionUID = 6227050180349462765L;


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

		log.info(BeartterProperties.MESSAGE_START_LIMIT_SERVLET);

		Integer secondsUntilReset = (Integer) req.getSession().getAttribute("secondsUntilReset");

		if(secondsUntilReset == null) {
			log.error("secondsUntilReset is null");
			try {
				resp.sendRedirect("/beartter/");
				return;
			} catch (IOException e) {
				log.error(e.toString());
				return;
			}
		}

		req.getSession().invalidate();
		req.setAttribute("secondsUntilReset", secondsUntilReset);
		try {
			req.getRequestDispatcher("page/error/Limit.jsp").forward(req, resp);
		} catch (IOException e) {
			log.error(e.toString());
			return;
		} catch (ServletException e) {
			log.error(e.toString());
			return;
		}
	}
}
