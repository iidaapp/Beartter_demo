<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="util/ImplementsJquery.jsp" />

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
<c:forEach var="statusList" items="${statusList}">
	<c:forEach var="responseList" items="${statusList.responseList}">
		<c:forEach items="${statusList.responseList}">
		
		</c:forEach>
		
	</c:forEach>

</c:forEach>
	<div id="result"></div>
</body>
</html>