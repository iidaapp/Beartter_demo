<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	<div>TwitterName : ${ fn:escapeXml(screenName)} </div>
	<div>userName : ${fn:escapeXml(signUpForm.userName)}</div>
	<div>mailAddress : ${signUpForm.mailAddress}</div>
	<div>password : 入力されたパスワード</div>
	<div>birthDate : ${signUpForm.year} 年 ${signUpForm.month} 月 ${signUpForm.day} 日</div>
	<p>以上の内容で登録してよろしいですか？</p>
	<form name="send" action="complete">
		<input type="submit" value="登録">
	</form>
	<form name="return" action="signup">
		<input type="submit" value="戻る">
	</form>
</body>
<script type="text/javascript">

</script>
</html>