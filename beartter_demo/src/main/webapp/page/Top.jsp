<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Top</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="/beartter/staticcontents/css/bootstrap.css" rel="stylesheet">
<link href="/beartter/staticcontents/css/bootstrap-theme.css" rel="stylesheet">
<!-- mine -->
<link href="/beartter/staticcontents/css/top.css" rel="stylesheet">
<link rel="icon" type="image/gif" href="/beartter/staticcontents/img/icon.png">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>script>
      <![endif]-->

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
				<a class="navbar-brand" href="#mainimage"><img src="/beartter/staticcontents/img/icon.png"
					height="20" width="20" alt="icon"></a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">

					<li><a href="#howtouse">これなに？</a></li>
					<li><a href="#contact">お問い合わせ</a></li>

				</ul>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<div class="mainimage " id="mainimage">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2">
					<img src="/beartter/staticcontents/img/default_character.png" alt="character"
						class="img-responsive center-block">
				</div>
			</div>
			<h1 class="text-center">Beartter(β)</h1>
			<p class="catchcopy lead text-center">Tweet something for you and me.</p>
			<a href="/beartter/login"><img class="center-block" alt="twitter"
				src="/beartter/staticcontents/img/sign-in-with-twitter-gray.png"></a>
		</div>
	</div>

	<section id="howtouse" class="howtouse">
		<div class="worker">
			<div class="container">
				<h3 id="about">&nbsp;Beartter（β）</h3>
				<p>
					ブラウザからアクセスするTwitterクライアントです。<br />
					Beartterからツイートした内容に影響を受けて、あなたのアシスタントが変化していきます。
					現在は開発と平行して試用稼働していますので、アシスタントの変化の度合いや機能などが頻繁に変更されます。ご了承下さい。<br />
					
				</p>

				<h3 id="about">&nbsp;使い方</h3>
				<p>
					試用稼働中のため、使用できるTwitterとしての機能は限られています。<br />
					「タイムライン取得（20件ずつ）」<br />
					「ツイート」<br />
					「ユーザープロフィール閲覧」<br />
					「フォロー（リムーブ）」<br />
					のみ可能となります。<br />
				</p>
				<h3 id="about">&nbsp;推奨環境</h3>
				<p>
					PCのWebブラウザ（HTML5、CSS3以上動作）からのアクセスを想定しています。<br />
				</p>
			</div>
		</div>
		<div class="client">
			<div class="container">
				<div class="image">
				<img alt="main" src="/beartter/staticcontents/img/Main.png" class="img-responsive" />
				</div>
			</div>
		</div>
	</section>

	<section id="contact" class="contact">
		<div class="container">
			<h4>Contact me</h4>
			<p>ご意見、ご要望等ありましたら以下のTwitterアカウントまでご連絡ください。</p>
			<div class="copyright text-center">製作：@iidaapp</div>
		</div>
		<!-- /.container -->
	</section>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://code.jquery.com/jquery.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="/beartter/staticcontents/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function() {

			$('a[href*=#]').click(function() {
				$(this.hash).each(function() {
					$('html,body').animate({
						scrollTop : $(this).offset().top - 50
					}, 800);
				});
				return false;
			});

		});
	</script>
</body>
</html>