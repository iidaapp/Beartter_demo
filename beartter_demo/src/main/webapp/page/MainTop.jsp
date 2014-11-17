<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="util/ImplementsJquery.jsp" />
<script src="/beartter_demo/staticcontents/js/jquery.leanModal.min.js"
	type="text/javascript"></script>
<script src="/beartter_demo/staticcontents/js/common.js"
	type="text/javascript"></script>
<script src="/beartter_demo/staticcontents/js/bootstrap.min.js"></script>
<script type="text/javascript" script-name="syncopate"
	src="http://use.edgefonts.net/syncopate.js"></script>
<link rel="stylesheet" href="/beartter_demo/staticcontents/css/Main.css"
	type="text/css" />
<link href="/beartter_demo/staticcontents/css/bootstrap.min.css"
	rel="stylesheet">

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
<body class="tl">

	<!-- コンテナ開始 -->
	<div id="container_tl">


		<!-- ヘッダ開始 -->
		<div id="header_tl">
			TOP画面 <br /> <br />

			<form action="main" method="post">
				<input type="hidden" name="paging" value="${pagingNo + 1}">
				<input type="submit" value="次のページへ" />
			</form>
			<c:if test="${!empty pagingNo and !(pagingNo eq 1)}">
				<form action="main" method="post">
					<input type="hidden" name="paging" value="${pagingNo - 1}">
					<input type="submit" value="前のページへ" />
				</form>
			</c:if>
			<form action="logout" method="post">
				<input type="submit" value="ログアウト" />
			</form>
		</div>
		<!-- ヘッダ終了 -->

		<!-- ナビゲーション開始 -->
		<div id="nav_tl">
			<form action="tweet" method="post">
				<textarea name="tweet_text" id="tweet_text"></textarea>
				<input type="submit" value="Tweet" />
			</form>
			<img alt="character"
				src="/beartter_demo/staticcontents/img/default_character.jpg">
			<c:if test="${!empty error and (error eq 187) }">
				<div id="error_msg">二重投稿は出来ません</div>
			</c:if>
			<c:if test="${!empty error and (error eq 400) }">
				<div id="error_msg">Tweetは1文字以上140文字以下でお願いします</div>
			</c:if>


		</div>
		<!-- ナビゲーション終了 -->

		<!-- メインカラム開始 -->
		<div id="content_tl">
			<c:forEach var="timeLineList" items="${statusList}">
				<table frame="hsides" rules="rows" id="timeline">
					<c:forEach items="${timeLineList}" var="status">

						<c:if test="${empty status.retweetedStatus}">
							<!-- 通常ツイート -->
							<tr>
								<td id="icon_tl"><a rel="leanModal" href="#prof"
									onclick="getValue(${status.user.id});"><img
										src="${status.user.profileImageURL}" /></a></td>
								<td>${fn:replace(fn:escapeXml(status.text),newLine, "<br />")}</td>
							</tr>
							<tr>
								<td><a rel="leanModal" href="#prof">${fn:escapeXml(status.user.name)}</a></td>
								<td>${fn:escapeXml(status.createdAt)}</td>
							</tr>
						</c:if>

						<c:if test="${!empty status.retweetedStatus}">
							<!-- RT -->
							<tr>
								<td><a rel="leanModal" href="#prof"
									onclick="getValue(${status.retweetedStatus.user.id});"> <img
										src="${status.retweetedStatus.user.profileImageURL}" />
								</a></td>
								<td>${fn:escapeXml(status.retweetedStatus.text)}</td>
							</tr>
							<tr>
								<td><a rel="leanModal" href="#prof"
									onclick="getValue(${status.retweetedStatus.user.id});">
										${fn:escapeXml(status.retweetedStatus.user.name)} </a></td>
								<td>${status.user.screenName}retweeted
									${fn:escapeXml(status.retweetedStatus.createdAt)}</td>
							</tr>
						</c:if>

					</c:forEach>
				</table>
			</c:forEach>

		</div>
		<!-- メインカラム終了 -->

		<!-- フッタ開始 -->
		<div id="footer_tl"></div>
		<!-- フッタ終了 -->

		<!-- ユーザ情報 -->
		<input id="temp" type="hidden" />
		<div id="prof">
			<table id="prof_table">
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
						<td><input type="button" id="friendshipbutton" /></td>
					</tr>
					<tr>
						<td class="modal_close"></td>
					</tr>
				</tbody>
			</table>
		</div>

		<script type="text/javascript">

function getValue(id){
	getProfile(id);
	getFriendship(id);
}

$( 'a[rel*=leanModal]').leanModal({
    top: 50,                     // モーダルウィンドウの縦位置を指定
    overlay : 0.5,               // 背面の透明度 
    closeButton: ".modal_close"  // 閉じるボタンのCSS classを指定
});

</script>
	</div>
	<!-- コンテナ終了 -->


</body>
</html>