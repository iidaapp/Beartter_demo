package com.iidaapp.beartter_demo.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.auth.AccessToken;

import com.iidaapp.beartter_demo.db.DbUtils;
import com.iidaapp.beartter_demo.entity.AccessTokenEntity;
import com.iidaapp.beartter_demo.entity.CharacterParamEntity;
import com.iidaapp.beartter_demo.entity.UserinfoEntity;
import com.iidaapp.beartter_demo.util.BeartterProperties;
import com.iidaapp.beartter_demo.util.BeartterUtils;
import com.iidaapp.beartter_demo.util.SignUpForm;

@WebServlet(name = "signUpComplete", urlPatterns = "/complete")
public class SignUpComplete extends HttpServlet {

	private static Logger log = LoggerFactory.getLogger(SignUpComplete.class);
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

		execute(req, resp);
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

		if(!StringUtils.isEmpty((String) session.getAttribute("beartterId"))) {
			try {
				req.getRequestDispatcher("/page/SignUpComplete.jsp").forward(req, resp);
				return;
			} catch (ServletException e) {
				log.error(e.toString());
				try {
					resp.sendRedirect("error");
					return;
				} catch (IOException e1) {
					log.error(e1.toString());
					return;
				}
			} catch (IOException e) {
				log.error(e.toString());
				return;
			}
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

		String passwordEncode = null;
		try {
			passwordEncode = BeartterUtils.encodePassdigiest(password);
		} catch (NoSuchAlgorithmException e) {
			log.error(e.toString());
			try {
				resp.sendRedirect("error");
				return;
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		}
		userinfoEntity.setBeartterId(beartterId);
		userinfoEntity.setEmailAddress(emailAddress);
		userinfoEntity.setPassword(passwordEncode);
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

		CharacterParamEntity characterParamEntity = new CharacterParamEntity();
		characterParamEntity.setBeartterId(beartterId);

		try {

			DbUtils.insertSignUpData(userinfoEntity, accessTokenEntity, characterParamEntity);

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

		session.removeAttribute("signUpForm");
		session.removeAttribute("screenName");

		session.setAttribute("beartterId", beartterId);
		try {
			req.getRequestDispatcher("/page/SignUpComplete.jsp").forward(req, resp);
			return;
		} catch (ServletException e) {
			log.error(e.toString());
			try {
				resp.sendRedirect("error");
				return;
			} catch (IOException e1) {
				log.error(e1.toString());
				return;
			}
		} catch (IOException e) {
			log.error(e.toString());
			return;
		}
	}
}
