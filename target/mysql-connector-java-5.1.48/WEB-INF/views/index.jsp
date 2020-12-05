<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
	<span class="label label-danger">${message}</span>
	<form action="<%=request.getContextPath()%>/AAALoginController" method="post">
		ユーザーID：<input type="text" name="userId" value="${userId}"><br>
		パスワード：<input type="password" name="pass" value="${pass}"><br>
		<input type="submit" value="ログイン">
	</form>
</body>
</html>