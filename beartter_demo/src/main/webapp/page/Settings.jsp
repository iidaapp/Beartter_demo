<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<jsp:include page="/page/util/ImplementsJquery.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Settings</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap -->
<link href="/beartter/staticcontents/css/bootstrap.css" rel="stylesheet">
<link href="/beartter/staticcontents/css/bootstrap-theme.css" rel="stylesheet">
<script src="/beartter/staticcontents/js/bootstrap.min.js" type="text/javascript"></script>
<!-- mine -->
<link href="/beartter/staticcontents/css/main.css" rel="stylesheet">
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.0/jquery-ui.min.js"></script>
<link rel="stylesheet" href="/beartter/staticcontents/css/setting.css" type="text/css" />

</head>
<body>

	<!-- container -->
	<div class="container">

		<!-- navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
						data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span
							class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/beartter/main"> <img
						src="/beartter/staticcontents/img/icon.png" height="20" width="20" alt="icon"></a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
							role="button" aria-expanded="false"> Config <span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li class="dropdown-header">
									${fn:escapeXml(beartterId)}
								</li>
								<li class="divider"></li>
								<li>Settings</li>
								<li><a href="#logoutModal" data-toggle="modal">Logout</a></li>
							</ul></li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
		<!-- end navigation -->

	<div class="row">
	<c:if test="${!empty errorMessage }">
		<div class="alert alert-danger">
			<a class="close" data-dismiss="alert">×</a> 
			${errorMessage}
		</div>
	</c:if>
		<h3>登録情報変更</h3>
			<div>
				<p>（未実装）</p>
			</div>
		<hr>
		<h3>退会</h3>
		<div class="modal-button">
			<a data-toggle="modal" href="#deleteModal" class="btn btn-danger">退会する</a>
		</div>
		<hr />
			<form action="main">
			<div>
				<button type="submit" class="btn btn-default">戻る</button>
			</div>
			</form>
		<hr>
	</div>

	</div>

		<!-- delete modal -->
		<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
			aria-labelledby="deleteModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="deleteModalLabel">確認</h4>
					</div>
					<form action="/beartter/secede" method="post" id="custom_form" class="custom_form">
						<div class="modal-body">
							<ul class="list-unstyled" style="text-align: left;">
								<li>・保存されている情報はすべて消去されます。</li>
								<li>・退会の取り消しはできません。</li>
								<li></li>
							</ul>
							<p>本当に退会いたしますか？</p>
							<p><img src="${profileImageUrl}" /></p>

							<p>確認のためにパスワードを入力してください。</p>
							<div class="form-group">
							<input type="password" id="password" name="password" class="form-control" maxlength="45">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">back</button>
							<button type="submit" class="btn btn-danger validate">退会</button>
						</div>
					</form>

				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- end tweet modal -->
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js" type="text/javascript"></script>
	<script src="/beartter/staticcontents/js/secede.js" type="text/javascript"></script>
</body>
</html>