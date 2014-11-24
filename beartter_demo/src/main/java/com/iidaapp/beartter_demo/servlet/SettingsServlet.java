package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "settingsServlet", urlPatterns = "/settings")
public class SettingsServlet extends HttpServlet {

	private static final long serialVersionUID = -1178343842456615740L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 共通処理へ
		execute(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 共通処理へ
		execute(req, resp);
	}


	private void execute(HttpServletRequest req, HttpServletResponse resp) {

		HttpSession session = req.getSession(false);
		if (session == null) {
			System.out.println("session is null");
			req.setAttribute("errorDescription", "session is null");
			req.getRequestDispatcher("error");
		}

		String beartterId = (String) session.getAttribute("beartterId");
		if (StringUtils.isEmpty(beartterId)) {
			req.setAttribute("errorDescription", "session is clear.");
			req.getRequestDispatcher("error");
			return;
		}

		try {
			req.getRequestDispatcher("/page/Settings.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			req.getRequestDispatcher("error");
			return;
		}
	}

}
