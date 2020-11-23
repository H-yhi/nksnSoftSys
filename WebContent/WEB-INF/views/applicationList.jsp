<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="nksnSoftSys.com.bean.userInfo.UserBean,java.util.*"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一覧画面</title>
<script>
	function dips(){
		confirm("本当に削除しますか？");
	}
</script>
</head>
<body>
	<span class="label label-danger">${message}</span>
	<% UserBean userBean = (UserBean)session.getAttribute("userBean"); %>
	<%= userBean.getName() %>さん、ようこそ！
	<% if(userBean.getAutFlg().equals("1")) {%>
    	<form action="<%=request.getContextPath()%>/BBBUserRegController" method="get">
			<input type="submit" value="登録画面へ">
    	</form>
    <%}%>
    <form action="<%=request.getContextPath()%>/AAALoginController" method="get">
    	<input type="submit" value="ログアウト">
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
					<% if(userBean.getAutFlg().equals("1")) {%>
						<form action="<%=request.getContextPath()%>/CCCUserUpDellController" method="post">
							<td><button type="button" name="update" value="${list.userId}">更新</button></td>
							<td><button type="submit" name="delete" value="${list.userId}" onClick="dips()">削除</button></td>
						</form>
					<%}%>

				</tr>
			</c:forEach>
		</table>
</body>
</html>