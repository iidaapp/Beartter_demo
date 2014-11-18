<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<jsp:include page="/page/util/ImplementsJquery.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap -->
<link href="../staticcontents/css/bootstrap.css" rel="stylesheet">
<link href="../staticcontents/css/bootstrap-theme.css" rel="stylesheet">

<!-- Mine -->
<link href="../staticcontents/css/signup.css" rel="stylesheet">

</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/beartter_demo/"><img src="../staticcontents/img/icon.png"
					height="20" width="20" alt="icon"></a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">新規登録 <span class="sr-only">(current)</span></a></li>
				</ul>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>


	<h3 class="">Sign up for Mapbox</h3>
	<div>
		<img src="http://abs.twimg.com/sticky/default_profile_images/default_profile_3_normal.png" />
		<%-- 				<img src="${profileImageUrl}" /> --%>
	</div>


	<div>TwitterName : ${screenName}</div>

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

	<div class="form-block">
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">

					<form method="post" id="custom_form" name="custom_form" action="/beartter_demo/confirm" role="form">
						<div class="form-group">
							<label for="userName">Beartterユーザー名</label> <input type="text" id="userName" name="userName"
								class="form-control" <c:if test="${!empty signUpForm}">value="${signUpForm.userName}"</c:if>>
						</div>
						<div class="form-group">
							<label for="mailAddress">メールアドレス</label> <input type="text" id="mailAddress"
								name="mailAddress" class="form-control">
						</div>

						<div class="form-group">
							<div class="form-group">
								<label for="birth_day">誕生日</label>
								<div class="form-inline">
									<input type="text" name="year" size="4" class="form-control" <c:if test="${!empty signUpForm}">value="${signUpForm.year}"</c:if>>年 
									<input type="text" name="month" size="2" class="form-control" <c:if test="${!empty signUpForm}">value="${signUpForm.month}"</c:if>>月 
									<input type="text" name="day" size="2" class="form-control" <c:if test="${!empty signUpForm}">value="${signUpForm.day}"</c:if>>日
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="password">パスワード</label>
							<input type="password" name="password" class="form-control" size="45">
						</div>

						<div class="form-group">
							<label for="passwordretry">パスワード確認</label> <input type="password" name="passwordConfirm" class="form-control" size="45">
						</div>

						<button type="submit" class="btn btn-default" id="submit">Submit</button>
					</form>
				</div>

			</div>
		</div>
	</div>

</body>
</html>