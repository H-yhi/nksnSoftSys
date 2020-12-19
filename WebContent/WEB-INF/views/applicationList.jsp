<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="nksnSoftSys.com.bean.userInfo.UserBean,java.util.*"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% UserBean userBean = (UserBean)session.getAttribute("userBean"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一覧画面</title>
<script>
	function dips(){
		if(confirm("本当に削除しますか？") == false){
			return false;
		}
	}
</script>
<style>
table {
	width: 100%;
	border-collapse: collapse;
	border-spacing: 0;
}

table th, table td {
	padding: 10px 0;
	text-align: center;
}

table tr:nth-child(odd) {
	background-color: #eee
}
</style>
</head>
<body>
	<span class="label label-danger">${message}</span><br>
	<%= userBean.getName() %>さん、ログイン中です！
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
    		<th>安打数</th><th>本塁打</th><th>打点</th><th>出塁率</th>
    		<th>長打率</th><th>四球</th><th>死球</th><th>盗塁</th>
    		<th>犠打</th><th>犠飛</th><th>OPS</th>
    		<th>更新</th><th>試合結果反映</th><th>個人成績修正</th><th>削除</th></tr>
			<c:forEach var="list" items="${list}">
				<tr>
					<td><c:out value="${list.name }" /></td><td><c:out value="${list.posiName}" /></td>
					<td><c:out value="${list.game }" /></td><td><c:out value="${list.ave}" /></td>
					<td><c:out value="${list.hit }" /></td><td><c:out value="${list.homeRun}" /></td>
					<td><c:out value="${list.rbi }" /></td><td><c:out value="${list.onBaseAve}" /></td>
					<td><c:out value="${list.longHitAve }" /></td><td><c:out value="${list.foBall}" /></td>
					<td><c:out value="${list.deBall }" /></td><td><c:out value="${list.stBase}" /></td>
					<td><c:out value="${list.secRoll }" /></td><td><c:out value="${list.secFly}" /></td>
					<td><c:out value="${list.ops }" />
					<form action="<%=request.getContextPath()%>/CCCUserUpDellController" method="post">
					<% if(userBean.getAutFlg().equals("1")) {%>
							<td><button type="submit" name="update" value="${list.userId}">更新</button></td>
							<td><button type="submit" name="kjnGraDay" value="${list.userId}">本日の試合結果を反映</button></td>
							<td><button type="submit" name="kjnGra" value="${list.userId}">個人成績を修正する</button></td>
							<c:choose>
								<c:when test="${list.userId == userBean.getUserId()}"><td><button type="submit" name="delete" value="${list.userId}" onClick="return dips()" disabled>削除</button></td></c:when>
								<c:otherwise><td><button type="submit" name="delete" value="${list.userId}" onClick="return dips()">削除</button></td></c:otherwise>
							</c:choose>
					<%}else{%>
						<c:choose>
							<c:when test="${list.userId == userBean.getUserId() }">
								<td><button type="submit" name="update" value="${list.userId}">更新</button></td>
								<td><button type="submit" name="kjnGraDay" value="${list.userId}">本日の試合結果を反映</button></td>
								<td><button type="submit" name="kjnGra" value="${list.userId}">個人成績を修正する</button></td>
							</c:when>
							<c:otherwise>
								<td><button type="submit" name="update" value="${list.userId}" disabled>更新</button></td>
								<td><button type="submit" name="kjnGraDay" value="${list.userId}" disabled>本日の試合結果を反映</button></td>
								<td><button type="submit" name="kjnGra" value="${list.userId}" disabled>個人成績を修正する</button></td>
							</c:otherwise>
						</c:choose>
						<td><button type="submit" name="delete" value="${list.userId}" onClick="return dips()" disabled>削除</button></td>
					<% } %>
					</form>

				</tr>
			</c:forEach>
		</table>
</body>
</html>