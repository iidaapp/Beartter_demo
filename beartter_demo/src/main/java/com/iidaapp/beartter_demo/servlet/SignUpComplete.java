package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import twitter4j.auth.AccessToken;

import com.iidaapp.beartter_demo.db.DbUtils;
import com.iidaapp.beartter_demo.entity.AccessTokenEntity;
import com.iidaapp.beartter_demo.entity.UserinfoEntity;
import com.iidaapp.beartter_demo.util.BeartterUtils;

public class SignUpComplete extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		if (session == null) {
			System.out.println("session is null");
			req.getRequestDispatcher("error");
		}

		// Twitter情報、ユーザー情報のDBへの登録
		AccessToken accessToken = (AccessToken) session.getAttribute("AccessToken");
		UserinfoEntity userinfoEntity = new UserinfoEntity();
		AccessTokenEntity accessTokenEntity = new AccessTokenEntity();

		String beartterId = (String) session.getAttribute("userName");
		String emailAddress = (String) session.getAttribute("mailAddress");
		String password = (String) session.getAttribute("password");
		String year = (String) session.getAttribute("year");
		String month = (String) session.getAttribute("month");
		String day = (String) session.getAttribute("day");
		Date birthDate = BeartterUtils.getBirthDate(year, month, day);

		userinfoEntity.setBeartterId(beartterId);
		userinfoEntity.setEmailAddress(emailAddress);
		userinfoEntity.setPassword(password);
		userinfoEntity.setBirthDate(birthDate);
		userinfoEntity.setAddDate(new Timestamp(System.currentTimeMillis()));
		userinfoEntity.setModifyDate(new Timestamp(System.currentTimeMillis()));

		accessTokenEntity.setBeartterId(beartterId);
		accessTokenEntity.setoAuthToken(accessToken.getToken());
		accessTokenEntity.setoAuthSecret(accessToken.getTokenSecret());
		accessTokenEntity.setUserId(accessToken.getUserId());
		accessTokenEntity.setScreenName(accessToken.getScreenName());
		accessTokenEntity.setAddDate(new Timestamp(System.currentTimeMillis()));
		accessTokenEntity.setModifyDate(new Timestamp(System.currentTimeMillis()));

		try {
			DbUtils.insertUserinfoEntity(userinfoEntity);
			DbUtils.insertAccessToken(accessTokenEntity);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.getRequestDispatcher("error");
		}

		session.removeAttribute("userName");
		session.removeAttribute("mailAddress");
		session.removeAttribute("password");
		session.removeAttribute("year");
		session.removeAttribute("month");
		session.removeAttribute("day");

		session.setAttribute("beartterId", beartterId);
		req.getRequestDispatcher("/page/SignUpComplete.jsp").forward(req, resp);

	}
}
