<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<jsp:include page="/page/util/ImplementsJquery.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SignUp</title>
<!-- Bootstrap -->
<link href="/beartter_demo/staticcontents/css/bootstrap.css" rel="stylesheet">
<link href="/beartter_demo/staticcontents/css/bootstrap-theme.css" rel="stylesheet">
<!-- mine -->
<link href="/beartter_demo/staticcontents/css/signup.css" rel="stylesheet">

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
				<a class="navbar-brand" href="/beartter_demo/"><img
					src="/beartter_demo/staticcontents/img/icon.png" height="20" width="20" alt="icon"></a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">新規登録</a></li>
				</ul>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>


	<h3 class="">Sign up for Beartter（仮）</h3>

	<p id="errorMessage">
		<c:if test="${!empty ValueExist and !ValueExist}">
			<span id="exist-error" class="has-error">空欄に値を入力して下さい。<br /></span>
		</c:if>
		<c:if test="${!empty UniqueEMailAddress and !UniqueEMailAddress}">
			<span id="mailaddress-unique-error" class="has-error">メールアドレスは既に別のユーザーが使用しています。<br /></span>
		</c:if>
		<c:if test="${!empty UniqueUserName and !UniqueUserName}">
			<span id="username-error" class="has-error">ユーザー名は既に別のユーザーが使用しています。<br /></span>
		</c:if>
		<c:if test="${!empty CorrectEmailAddress and !CorrectEmailAddress}">
			<span id="mailaddress-error" class="has-error">正しいメールアドレスの形式で入力して下さい。<br /></span>
		</c:if>
		<c:if test="${!empty CorrectBirthDate and !CorrectBirthDate}">
			<span id="birthdate-error" class="has-error">正しい年月日の形式で入力して下さい。<br /></span>
		</c:if>
		<c:if test="${!empty CorrectDigit and !CorrectDigit}">
			<span id="birthdate-error" class="has-error">正しい桁数で入力して下さい。<br /></span>
		</c:if>

	</p>

	<p>
		<img src="${profileImageUrl}" />
	</p>



	<div class="form-block">
		<div class="container">
			<div class="row">

				<form class="form-horizontal" method="post" id="custom_form" name="custom_form"
					action="/beartter_demo/confirm" role="form">

					<div class="form-group">
						<label class="twitterName col-sm-4">TwitterName</label>
						<div class="screenName col-sm-4">${screenName}</div>
					</div>

					<div class="form-group">
						<label for="userName" class="control-label col-sm-4">Beartterユーザー名</label>
						<div class="col-sm-4">
							<input type="text" id="userName" name="userName" class="form-control" maxlength="50"
								<c:if test="${!empty signUpForm}">value="${signUpForm.userName}"</c:if>>
						</div>
						<div class="description col-sm-4">(半角英数字 50字以内)</div>
					</div>

					<div class="form-group">
						<label for="mailAddress" class="control-label col-sm-4">メールアドレス</label>
						<div class="col-sm-4">
							<input type="text" id="mailAddress" name="mailAddress" class="form-control" maxlength="200"
							<c:if test="${!empty signUpForm}">value="${signUpForm.mailAddress}"</c:if>>
						</div>
						<div class="description col-sm-4">(200字以内)</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-4">誕生日</label>
						<div class="col-sm-4 form-date">
							<input type="text" class="form-control form-year" placeholder="yyyy" name="year"
								maxlength="4" <c:if test="${!empty signUpForm}">value="${signUpForm.year}"</c:if>> <span>年</span>
							<input type="text" class="form-control form-monthday" placeholder="MM" name="month"
								maxlength="2" <c:if test="${!empty signUpForm}">value="${signUpForm.month}"</c:if>>
							<span>月</span> <input type="text" class="form-control form-monthday" placeholder="dd"
								name="day" maxlength="2" <c:if test="${!empty signUpForm}">value="${signUpForm.day}"</c:if>>
							<span>日</span>
							<p class="myError"></p>
						</div>
						<div class="description-date col-sm-8 col-sm-offset-2">ex. 2014年08月32日</div>
					</div>

					<div class="form-group">
						<label for="password" class="control-label col-sm-4">パスワード</label>
						<div class="col-sm-4">
							<input type="password" id="password" name="password" class="form-control" maxlength="45">
						</div>
						<div class="description col-sm-4">(45字以内)</div>
					</div>

					<div class="form-group">
						<label for="passwordretry" class="control-label col-sm-4">パスワード確認</label>
						<div class="col-sm-4">
							<input type="password" id="passwordConfirm" name="passwordConfirm" class="form-control"
								maxlength="45">
						</div>
					</div>

					<input type="submit" class="validate btn btn-default" id="submit" value="次へ">
				</form>
			</div>

		</div>
	</div>

	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"
		type="text/javascript"></script>
	<script src="/beartter_demo/staticcontents/js/signup.js" type="text/javascript"></script>
</body>
</html>