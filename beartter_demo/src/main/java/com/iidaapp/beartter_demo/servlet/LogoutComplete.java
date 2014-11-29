package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iidaapp.beartter_demo.util.BeartterProperties;

/**
 * ログアウト処理完了クラス
 * @author iida
 *
 */
@WebServlet(name="logoutComplete", urlPatterns="/logoutcomplete")
public class LogoutComplete extends HttpServlet {

	private static Logger log = LoggerFactory.getLogger(LogoutComplete.class);
	private static final long serialVersionUID = 8133799937746863575L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp){

		// 共通処理へ
		execute(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp){

		// 共通処理へ
		execute(req, resp);
	}


	private void execute(HttpServletRequest req, HttpServletResponse resp){

		log.info(BeartterProperties.MESSAGE_START_LOGOUT_COMPLETE_SERVLET);

		// セッション内容をすべて破棄してTOPページに遷移
		req.getSession().invalidate();
		try {
			resp.sendRedirect("page/Top.jsp");
			return;
		} catch (IOException e) {
			log.error(e.toString());
			return;
		}
	}

	
}
