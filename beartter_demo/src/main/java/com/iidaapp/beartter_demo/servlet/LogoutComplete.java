package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ログアウト処理完了クラス
 * @author iida
 *
 */
@WebServlet(name="logoutComplete", urlPatterns="/logoutcomplete")
public class LogoutComplete extends HttpServlet {

	private static final long serialVersionUID = 8133799937746863575L;


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


	private void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		// セッション内容をすべて破棄してTOPページに遷移
		req.getSession().invalidate();
		resp.sendRedirect("page/Top.jsp");
	}

	
}
