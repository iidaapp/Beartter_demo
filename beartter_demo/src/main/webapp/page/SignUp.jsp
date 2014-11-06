<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/page/util/ImplementsJquery.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div><img src="${profileImageUrl}" /></div>
<div>TwitterName : ${screenName}</div>
<form action="signup/confirm" method="post">
<br />
<div>Beartterユーザー名<br /><input type="text" name="userName"></div>
<br />
<div>パスワード<br /><input type="password" name="password"></div>
<br />
<div>パスワード確認<br /><input type="password" name="passwordConfirm"></div>
<br />
<div>メールアドレス<br /><input type="text" name="mailAddress"></div>
<br />
<br />
<div><br /><input type="submit" name="submit"></div>
</form>

</body>
</html>