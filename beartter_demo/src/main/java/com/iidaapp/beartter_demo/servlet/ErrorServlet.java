package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 共通エラー処理
 * @author iida
 *
 */
@WebServlet(name = "errorServlet", urlPatterns = { "/error" })
public class ErrorServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3820104047647816529L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		execute(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		execute(req, resp);
	}


	private void execute(HttpServletRequest req, HttpServletResponse resp) {

		// セッションからエラー文言を取得
		String errorDiscription = (String) req.getSession().getAttribute("errorDescription");

		// セッションの情報をすべてクリア
		req.getSession().invalidate();
		// 画面表示用にエラー文言をセット
		req.setAttribute("errorDiscription", errorDiscription);

		try {
			// エラー画面へ遷移
			req.getRequestDispatcher("/page/Error.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {

			// TODO Servlet全体のエラー処理
			e.printStackTrace();
		}

	}
}
