package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import twitter4j.auth.AccessToken;

import com.iidaapp.beartter_demo.db.DbUtils;
import com.iidaapp.beartter_demo.entity.AccessTokenEntity;
import com.iidaapp.beartter_demo.entity.UserinfoEntity;
import com.iidaapp.beartter_demo.util.BeartterUtils;
import com.iidaapp.beartter_demo.util.SignUpForm;

@WebServlet(name="signUpComplete", urlPatterns="/complete")
public class SignUpComplete extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		execute(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		execute(req, resp);
	}


	private void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		HttpSession session = req.getSession(false);
		if(session == null) {
			System.out.println("session is null");
			req.setAttribute("errorDescription","session is null");
			req.getRequestDispatcher("error");
		}

		// Twitter情報、ユーザー情報のDBへの登録
		AccessToken accessToken = (AccessToken) session.getAttribute("AccessToken");
		SignUpForm signUpForm = (SignUpForm) session.getAttribute("signUpForm");
		UserinfoEntity userinfoEntity = new UserinfoEntity();
		AccessTokenEntity accessTokenEntity = new AccessTokenEntity();

		String beartterId = signUpForm.getUserName();
		String emailAddress = signUpForm.getMailAddress();
		String password = signUpForm.getPassword();
		String year = signUpForm.getYear();
		String month = signUpForm.getMonth();
		String day = signUpForm.getDay();
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
			session.setAttribute("errorDescription", e.getCause());
			req.getRequestDispatcher("error");
		}

		session.removeAttribute("signUpForm");
		session.removeAttribute("AccessToken");
		session.removeAttribute("Twitter");
		session.removeAttribute("screenName");
		session.removeAttribute("profileImageUrl");

		session.setAttribute("beartterId", beartterId);
		req.getRequestDispatcher("/page/SignUpComplete.jsp").forward(req, resp);
	}
}
