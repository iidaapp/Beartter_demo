<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<jsp:include page="/page/util/ImplementsJquery.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap -->
<link href="/beartter_demo/staticcontents/css/bootstrap.css"
	rel="stylesheet">
<link href="/beartter_demo/staticcontents/css/bootstrap-theme.css"
	rel="stylesheet">
<!-- mine -->
<link href="/beartter_demo/staticcontents/css/signup.css"
	rel="stylesheet">

</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/beartter_demo/"><img
					src="../staticcontents/img/icon.png" height="20" width="20"
					alt="icon"></a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">新規登録</a></li>
				</ul>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>


	<h3 class="">Sign up for Beartter（仮）</h3>

	<div class="container">

		<p>
			<img
				src="http://abs.twimg.com/sticky/default_profile_images/default_profile_3_normal.png" />
			<%-- 				<img src="${profileImageUrl}" /> --%>
		</p>

		<div class="row">
			<div class="col-sm-8 col-sm-offset-2 col-xs-8 col-xs-offset-2">
				<ul class="list-unstyled">
					<li>
						<h3 class="panel-title">TwitterName</h3>
					</li>
					<li>
					<p>${screenName}${screenName}${screenName}${screenName}${screenName}${screenName}${screenName}${screenName}${screenName}${screenName}
					</p>
					</li>

					<li>
						<h3 class="panel-title">Beartterユーザー名</h3>
					</li>
					<li>${fn:escapeXml(signUpForm.userName)}</li>

					<li>
						<h3 class="panel-title">メールアドレス</h3>
					</li>
					<li>${fn:escapeXml(signUpForm.mailAddress)}</li>

					<li>
						<h3 class="panel-title">誕生日</h3>
					</li>
					<li>${signUpForm.year}年
						${signUpForm.month} 月 ${signUpForm.day} 日
					</li>

					<li>
						<h3 class="panel-title">パスワード</h3>
					</li>
					<li>(入力されたパスワード)</li>
				</ul>
			</div>
		</div>
	</div>

	<p>以上の内容で登録してよろしいですか？</p>
	<form name="send" action="complete">
		<input type="submit" class="validate btn btn-default" id="submit"
			value="登録">
	</form>
	<form name="return" action="signup">
		<input type="submit" class="validate btn btn-default" id="submit"
			value="戻る">
	</form>
</body>
</html>