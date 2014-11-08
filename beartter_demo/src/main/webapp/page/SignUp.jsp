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
	<form action="confirm" method="post">
		<br />
		<c:if test="${!empty ValueExist and !ValueExist}">
		空欄あり
		</c:if>
		<c:if test="${!empty SamePassword and !SamePassword}">
		パスワード不一致
		</c:if>
		<c:if test="${!empty UniqueEMailAddress and !UniqueEMailAddress}">
		メールアドレス使用済み
		</c:if>
		<c:if test="${!empty UniqueUserName and !UniqueUserName}">
		ユーザー名使用済み
		</c:if>
		<c:if test="${!empty CorrectEmailAddress and !CorrectEmailAddress}">
		Emailの形式じゃない
		</c:if>
		<div>
			Beartterユーザー名<br />
			<input type="text" name="userName" <c:if test="${!empty userName}">value="${userName}"</c:if> >
			
		</div>
		<br />
		<div>
			パスワード<br />
			<input type="password" name="password">
		</div>
		<br />
		<div>
			パスワード確認<br />
			<input type="password" name="passwordConfirm">
		</div>
		<br />
		<div>
			メールアドレス<br />
			<input type="text" name="mailAddress" <c:if test="${!empty password}">value="${password}"</c:if> >
		</div>
		<br /> <br />
		<div>
			<br />
			<input type="submit" name="submit">
		</div>
	</form>

</body>
</html>