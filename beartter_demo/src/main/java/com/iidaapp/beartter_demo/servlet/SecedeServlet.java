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

import com.iidaapp.beartter_demo.db.DbUtils;

@WebServlet(name="secedeServlet", urlPatterns="/secede")
public class SecedeServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6490181083278582428L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("error");
		return;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		execute(req, resp);
	}

	private void execute(HttpServletRequest req, HttpServletResponse resp) {
		

		HttpSession session = req.getSession(false);
		if (session == null) {
			req.setAttribute("errorDescription", "session is null.");
			req.getRequestDispatcher("error");
			return;
		}

		String beartterId = (String) session.getAttribute("beartterId");
		if (StringUtils.isEmpty(beartterId)) {
			req.setAttribute("errorDescription", "session is clear.");
			req.getRequestDispatcher("error");
			return;
		}

		try {
			DbUtils.removeAllData(beartterId);
		} catch (SQLException e) {
			// TODO Error処理
			e.printStackTrace();
			req.getRequestDispatcher("error");
			return;
		}
		
		// すべてのセッションの削除
		req.getSession().invalidate();
		
		try {
			req.getRequestDispatcher("/page/Secede.jsp").forward(req, resp);
			return;
		} catch (ServletException | IOException e) {
			// Error処理
			e.printStackTrace();
			req.getRequestDispatcher("error");
			return;
		}
	}

}
