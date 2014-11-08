<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/page/util/ImplementsJquery.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<img src="${profileImageUrl}" />
	</div>
	<div>TwitterName : ${screenName}</div>
	<div>userName : ${userName}</div>
	<div>mailAddress : ${mailAddress}</div>
	<div>password : 入力されたパスワード</div>
	<div>birthDate : ${year} 年 ${month} 月 ${day} 日</div>
	<p>以上の内容で登録してよろしいですか？</p>
	<form name="send" action="complete">
		<input type="submit" value="登録">
		<input
			type="button" value="戻る">
	</form>
</body>
<script type="text/javascript">

</script>
</html>