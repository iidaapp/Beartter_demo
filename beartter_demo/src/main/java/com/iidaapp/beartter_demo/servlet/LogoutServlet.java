package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * ログアウト処理クラス
 * @author iida
 *
 */
@WebServlet(name = "logoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {


	private static final long serialVersionUID = 7107661025445474060L;


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

		// beartterIdがなければ通常遷移ではないため、エラー画面へ遷移
		String beartterId = (String) req.getSession().getAttribute("beartterId");
		if(StringUtils.isEmpty(beartterId)){
			req.setAttribute("errorDescription", "session is clear.");
			req.getRequestDispatcher("error");
			return;
		}

		try {
			// ログアウト確認画面へ遷移
			req.getRequestDispatcher("/page/LogOutConfirm.jsp").forward(req, resp);
			return;

		} catch (ServletException | IOException e) {
			// TODO サーブレットエラー処理
			e.printStackTrace();
			try {
				resp.sendRedirect("error");
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}

	}
}
