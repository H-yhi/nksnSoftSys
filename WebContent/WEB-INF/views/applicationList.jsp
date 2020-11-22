<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="nksnSoftSys.com.bean.userInfo.UserBean,java.util.*"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一覧画面</title>
</head>
<body>
	<span class="label label-danger">${message}</span>
	<% UserBean userBean = (UserBean)session.getAttribute("userBean"); %>
	<%= userBean.getName() %>さん、ようこそ！
    <form action="<%=request.getContextPath()%>/BBBUserRegController" method="get">
		<input type="submit" value="登録画面へ">
    </form>
    	<table>
    		<tr><th>名前</th><th>守備</th><th>試合数</th><th>打率</th>
    		<th>安打数</th><th>本塁打</th><th>打点</th><th>出塁率</th></tr>
			<c:forEach var="list" items="${list}">
				<tr>
					<td><c:out value="${list.name }" /></td><td><c:out value="${list.posiName}" /></td>
					<td><c:out value="${list.game }" /></td><td><c:out value="${list.ave}" /></td>
					<td><c:out value="${list.hit }" /></td><td><c:out value="${list.homeRun}" /></td>
					<td><c:out value="${list.rbi }" /></td><td><c:out value="${list.onBaseAve}" /></td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>