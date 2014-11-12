<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="util/ImplementsJquery.jsp" />
<script src="/beartter_demo/staticcontents/js/jquery.leanModal.min.js" type="text/javascript"></script>
<script src="/beartter_demo/staticcontents/js/common.js" type="text/javascript"></script>
<link rel="stylesheet" href="/beartter_demo/staticcontents/css/Main.css" type="text/css" />

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
	TOP画面
	<br />
	<br />
	<c:if test="${!empty pagingNo and !(pagingNo eq 1)}">
		<form action="main" method="post">
			<input type="hidden" name="paging" value="${pagingNo - 1}"> <input
				type="submit" value="前のページへ" />
		</form>
	</c:if>

	<form action="main" method="post">
		<input type="hidden" name="paging" value="${pagingNo + 1}"> <input
			type="submit" value="次のページへ" />
	</form>

	<c:forEach var="timeLineList" items="${statusList}">
		<table frame="hsides" rules="rows">
			<c:forEach items="${timeLineList}" var="status">

				<!-- 通常ツイート -->
				<c:if test="${empty status.retweetedStatus}">
					<tr>
						<td><a rel="leanModal" href="#div787"
							onclick="getValue(${status.user.id});"><img
								src="${status.user.profileImageURL}" /></a></td>
						<td>${fn:escapeXml(status.text)}</td>
					</tr>
					<tr>
						<td><a rel="leanModal" href="#div787">${fn:escapeXml(status.user.screenName)}</a></td>
						<td>${fn:escapeXml(status.createdAt)}</td>
					</tr>
				</c:if>

				<!-- RT -->
				<c:if test="${!empty status.retweetedStatus}">
					<tr>
						<td><img
							src="
							${status.retweetedStatus.user.profileImageURL}" /></td>
						<td>${fn:escapeXml(status.retweetedStatus.text)}</td>
					</tr>
					<tr>
						<td>${fn:escapeXml(status.retweetedStatus.user.screenName)}</td>
						<td>${status.user.screenName}retweeted
							${fn:escapeXml(status.retweetedStatus.createdAt)}</td>
					</tr>
				</c:if>

			</c:forEach>
		</table>
	</c:forEach>
	<input id="temp" type="hidden" />

	<div id="div787">
		<table>
			<tbody>
				<tr>
					<td colspan="5" id="icon"></td>
				</tr>
				<tr>
					<td colspan="5" id="name"></td>
				</tr>
				<tr>
					<td colspan="5" id="screenname"></td>
				</tr>
				<tr>
					<td colspan="5" id="desc"></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>

	<script type="text/javascript">

function getValue(id){
	getProfile(id);
}

$( 'a[rel*=leanModal]').leanModal({
    top: 50,                     // モーダルウィンドウの縦位置を指定
    overlay : 0.5,               // 背面の透明度 
    closeButton: ".modal_close"  // 閉じるボタンのCSS classを指定
});



</script>
</body>
</html>