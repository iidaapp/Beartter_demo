<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<jsp:include page="/page/util/ImplementsJquery.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SignUp</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap -->
<link href="/beartter/staticcontents/css/bootstrap.css" rel="stylesheet">
<link href="/beartter/staticcontents/css/bootstrap-theme.css" rel="stylesheet">
<!-- mine -->
<link href="/beartter/staticcontents/css/signup.css" rel="stylesheet">
<link href="/beartter/staticcontents/css/animate.css" rel="stylesheet">

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
				<a class="navbar-brand" href="/beartter/"><img
					src="/beartter/staticcontents/img/icon.png" height="20" width="20" alt="icon"></a>
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


	<h3 class="">Sign up for Beartter(β)</h3>
	<h4>登録完了！</h4>
	<img src="/beartter/staticcontents/img/default_character.png" alt="character" class="img-responsive center-block animated bounceInDown">
	<p class="">ようこそ！</p>

	<form action="main">
		<input type="submit" class="validate btn btn-success" id="submit" value="始める">
	</form>
</body>
</html>