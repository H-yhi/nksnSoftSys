<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="nksnSoftSys.com.bean.userInfo.UserBean"
    pageEncoding="UTF-8"%>
<% UserBean userBean = (UserBean)request.getAttribute("userBean"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新画面</title>
</head>
<body>
	パスワード：<input type="text" name="pass" maxlength="4" value="${userBean.getPass()}" placeholder="4文字固定の数字"><br>
	名前：<input type="text" name="name" maxlength="10" value="${userBean.getName()}" placeholder="10文字以内"><br>
	守備位置：<select name="posiId">
					<c:forEach var="list" items="${posiBean}">
						<option value="${list.posiId}"><c:out value="${list.posiName}" /></option>
					</c:forEach>
				</select>
	投打：<select name="handId">
				<c:forEach var="list1" items="${handBean}">
					<option value="${list1.handId}"><c:out value="${list1.hand}" /></option>
				</c:forEach>
		</select>
	権限：<select name="autFlg">
				<c:forEach var="list2" items="${autBean}">
					<option value="${list2.autId}"><c:out value="${list2.aut}" /></option>
				</c:forEach>
			</select>

</body>
</html>