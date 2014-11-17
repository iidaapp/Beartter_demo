<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<jsp:include page="/page/util/ImplementsJquery.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="/beartter_demo/staticcontents/js/bootstrap.min.js"></script>
<link href="/beartter_demo/staticcontents/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container" align="center">
		<div class="form-horizontal">
			<div id="errorMessage">
				<c:if test="${!empty ValueExist and !ValueExist}">
					空欄あり
				</c:if>
				<c:if test="${!empty UniqueEMailAddress and !UniqueEMailAddress}">
					メールアドレス使用済み
				</c:if>
				<c:if test="${!empty UniqueUserName and !UniqueUserName}">
					ユーザー名使用済み
				</c:if>
				<c:if test="${!empty CorrectEmailAddress and !CorrectEmailAddress}">
					Emailの形式じゃない
				</c:if>
				<c:if test="${!empty CorrectBirthDate and !CorrectBirthDate}">
					年月日の形式じゃない
				</c:if>
			</div>

			<div>
				<img src="${profileImageUrl}" />
			</div>

			<div>TwitterName : ${screenName}</div>

			<form action="confirm" method="post" role="form"
				class="form-horizontal">

				<div class="form-group">
					<label for="inputUserName">Beartterユーザー名</label> <input
						class="form-control" id="inputUserName" type="text"
						name="userName" size="50"
						<c:if test="${!empty signUpForm}">value="${signUpForm.userName}"</c:if>>
				</div>


				<div class="form-group">
					<label for="inputEmailAddress">メールアドレス<br /></label> <input
						type="text" name="mailAddress" class="form-control" size="200"
						id="inputEmailAddress"
						<c:if test="${!empty signUpForm}">value="${signUpForm.mailAddress}"</c:if>>
				</div>

				<div class="form-inline form-group">
					<div class="form-group">
						<label for="birth_day">誕生日</label> <input type="text" name="year"
							size="4" class="form-control"
							<c:if test="${!empty signUpForm}">value="${signUpForm.year}"</c:if>>年
						<input type="text" name="month" size="2" class="form-control"
							<c:if test="${!empty signUpForm}">value="${signUpForm.month}"</c:if>>月
						<input type="text" name="day" size="2" class="form-control"
							<c:if test="${!empty signUpForm}">value="${signUpForm.day}"</c:if>>日
					</div>
				</div>

				<c:if test="${!empty SamePassword and !SamePassword}">
				パスワード不一致
					<div class="form-group has-error">
						<label for="password" class="control-label">パスワード</label>
						<input type="password" name="password" class="form-control" size="45">
					</div>
	
					<div class="form-group has-error">
						<label for="passwordretry" class="control-label">パスワード確認</label> <input type="password"
							name="passwordConfirm" class="form-control" size="45">
					</div>
				</c:if>

				<c:if test="${empty SamePassword or SamePassword}">
					<div class="form-group">
						<label for="password">パスワード</label> <input type="password"
							name="password" class="form-control" size="45">
					</div>
	
					<div class="form-group">
						<label for="passwordretry">パスワード確認</label> <input type="password"
							name="passwordConfirm" class="form-control" size="45">
					</div>
				</c:if>



				<div class="form-group">
					<input type="submit" name="submit" class="btn btn-default">
				</div>

			</form>
		</div>
	</div>
</body>
</html>