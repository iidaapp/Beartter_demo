<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<jsp:include page="/page/util/ImplementsJquery.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap -->
<link href="/beartter/staticcontents/css/bootstrap.css" rel="stylesheet">
<link href="/beartter/staticcontents/css/bootstrap-theme.css" rel="stylesheet">
<script src="/beartter/staticcontents/js/bootstrap.min.js" type="text/javascript"></script>
<!-- mine -->
<link href="/beartter/staticcontents/css/animate.css" rel="stylesheet">
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.0/jquery-ui.min.js"></script>
<script type="text/javascript" script-name="syncopate" src="http://use.edgefonts.net/syncopate.js"></script>
<link rel="stylesheet" href="/beartter/staticcontents/css/main.css" type="text/css" />

<script src="/beartter/staticcontents/js/common.js"
	type="text/javascript"></script>



<title>Insert title here</title>
<!--         <script type="text/javascript">
            $(function(){
                connect();
                $("#btn").click(button)
            });
            /* 接続 */
            function connect(){
                $.ajax({
                    url:"../polling", 
                    type:"GET",
                    data:{}, 
                    complete: fin,
                    success:pushed
                });
                
            }
            /* 投稿 */
            function pushed(data, status, hxr){
                $t = $("<div>").wrapInner(data);
                $("#result").append($t);
            }
            /* 接続終了の再接続 */
            function fin(hxr, status){
                connect();
            }
            /* ボタンが押されたときの処理 */
            function button(){
                $.post("../push", {text: $("#txt").val()});
                $("#txt").val("");
            }
        </script> -->
</head>
<body>

	<!-- container -->
	<div class="container">

		<!-- navigation -->
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
					<a class="navbar-brand" href="/beartter/main"> <img
						src="/beartter/staticcontents/img/icon.png" height="20"
						width="20" alt="icon"></a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">

					<ul class="nav navbar-nav navbar-right">
						<li>
							<div class="modal-button">
								<a data-toggle="modal" href="#tweetModal"
									class="btn btn-primary">Tweet</a>
							</div>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">
								Config <span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li class="dropdown-header">${fn:escapeXml(beartterId)}</li>
								<li class="divider"></li>
								<li><a href="settings">Settings</a></li>
								<li><a href="#logoutModal" data-toggle="modal">Logout</a></li>
							</ul></li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
		<!-- end navigation -->

		<!-- tweet modal -->
		<div class="modal fade" id="tweetModal" tabindex="-1" role="dialog"
			aria-labelledby="tweetModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="tweetModalLabel">New Tweet</h4>
					</div>
					<form action="/beartter/tweet" method="post">
						<div class="modal-body">
							<textarea name="tweet_text" id="tweet_text"></textarea>

						</div>
						<div class="modal-footer">
							<img src="${profileImageUrl}" />
							<button type="button" class="btn btn-default"
								data-dismiss="modal">back</button>
							<button type="submit" class="btn btn-primary">Tweet</button>
						</div>
					</form>

				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- end tweet modal -->

		<!-- user modal -->
		<div class="modal fade" id="userModal" tabindex="-1" role="dialog"
			aria-labelledby="userModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="tweetModalLabel">User Profile</h4>
					</div>

					<input id="temp" type="hidden" />

					<div>
						<table id="prof">
							<tbody>
								<tr>
									<td id="icon"></td>
								</tr>
								<tr>
									<td id="name"></td>
								</tr>
								<tr>
									<td id="screenname"></td>
								</tr>
								<tr>
									<td id="desc"></td>
								</tr>
								<tr>
									<td id="relation"></td>
								</tr>
								<tr>
									<td><input class="btn btn-primary" type="button"
										id="friendshipbutton" /></td>
								</tr>
								<tr>
									<td class="modal_close"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- end user modal -->

		<!-- logout modal -->
		<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
			aria-labelledby="logoutModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="logoutModalLabel">ログアウトしますか？</h4>
					</div>

					<div class="modal-footer">
						<form action="logoutcomplete">
							<button type="submit" class="btn btn-primary">Logout</button>
						</form>
					</div>

				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- end tweet modal -->

		<!-- character -->
		<div id="div1">
			<table>
				<tr>
					<td>
<!-- 						<div class="arrow_box" id="talk">
							<p>何について調べますか？</p>
							<div id="talk-span">
								<input type="text" />
							</div>
							<button id="talk-button" type="button" class="btn btn-default">検索</button>
						</div> -->
					</td>
					<td>
						<img alt="character" src="/beartter/staticcontents/img/${characterName}.png" class="img-responsive" onclick="changeArrowBoxDisp()">
					</td>
			</table>
		</div>





		<!-- timeline -->
		<div class="row">

	<c:if test="${!empty error and (error eq 187) }">
		<div class="alert alert-danger">
			<a class="close" data-dismiss="alert">×</a> 
			二重投稿は出来ません
		</div>
	</c:if>
	<c:if test="${!empty error and (error eq 400) }">
		<div class="alert alert-danger">
			<a class="close" data-dismiss="alert">×</a>
			Tweetは1文字以上140文字以下でお願いします
		</div>
	</c:if>


			<div class="col-xs-2" id="back">

			<form action="main" method="post">
					<button type="submit" class="btn btn-default <c:if test="${pagingNo == 1}">disabled</c:if>">
						<img alt="back" src="/beartter/staticcontents/img/rewind44.png">
					<span>back</span>
				</button>
			<input type="hidden" name="paging" value="${pagingNo - 1}" />
			</form>
			</div>
			<div class=" col-xs-8" id="timeline">

				<c:forEach var="timeLineList" items="${statusList}">

					<!-- table -->
					<table class="table table-bordered table-striped">

						<c:forEach items="${timeLineList}" var="status">

							<c:if test="${empty status.retweetedStatus}">
								<!-- normal -->
								<tr>
									<td id="icon_tl" class="left"><a data-toggle="modal"
										href="#userModal" onclick="getValue(${status.user.id});">
											<img src="${status.user.profileImageURL}" />
									</a></td>
									<td id="text_tl">${fn:replace(fn:escapeXml(status.text),newLine, "<br />")}</td>
								</tr>
								<tr>
									<td class="left"><a data-toggle="modal" href="#userModal"
										onclick="getValue(${status.user.id});">
											${fn:escapeXml(status.user.name)} </a></td>
									<td id="desc_tl">${fn:escapeXml(status.createdAt)}</td>
								</tr>
							</c:if>

							<c:if test="${!empty status.retweetedStatus}">
								<!-- RT -->
								<tr>
									<td class="left"><a data-toggle="modal" href="#userModal"
										onclick="getValue(${status.retweetedStatus.user.id});"> <img
											src="${status.retweetedStatus.user.profileImageURL}" />
									</a></td>
									<td id="text_tl">${fn:escapeXml(status.retweetedStatus.text)}</td>
								</tr>
								<tr>
									<td class="left"><a data-toggle="modal" href="#userModal"
										onclick="getValue(${status.retweetedStatus.user.id});">
											${fn:escapeXml(status.retweetedStatus.user.name)} </a></td>
									<td id="desc_tl">${status.user.screenName}retweeted
										${fn:escapeXml(status.retweetedStatus.createdAt)}</td>
								</tr>
							</c:if>

						</c:forEach>
					</table>
				</c:forEach>

			</div>
		<div class="col-xs-2" id="back">
			<form action="main" method="post">
				<button type="submit" class="btn btn-default">
					<span>next</span>
					<img alt="back" src="/beartter/staticcontents/img/fast44.png">
				</button>
			<input type="hidden" name="paging" value="${pagingNo + 1}" />
			</form>
		</div>

		</div>

	</div>

	<script type="text/javascript">

function getValue(id){
	getProfile(id);
	getFriendship(id);
}

/* function changeArrowBoxDisp(){
	var element = document.getElementById('talk');
	if((element.currentStyle || document.defaultView.getComputedStyle(element, '')).visibility == "hidden"){
		element.style.visibility = "visible";
	}else{
		element.style.visibility = "hidden";
	}
} */

$(function() {
    // #div1をdrag可能に
$("#div1").draggable();

});

</script>
	



</body>
</html>