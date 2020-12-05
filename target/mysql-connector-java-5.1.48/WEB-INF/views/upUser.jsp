<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="nksnSoftSys.com.bean.userInfo.UserBean"
    pageEncoding="UTF-8"%>
<% UserBean userBean = (UserBean)request.getAttribute("userBean"); %>
<% String message = (String)request.getAttribute("message"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新画面</title>
<script>
	function dips() {
		if (window.confirm('本当にいいんですね？') == false) {
			return false;
		}
	}
</script>
</head>
<body>
	<% if(message != null){ %>
		<span class="label label-danger">${message}</span>
		<form action="<%=request.getContextPath()%>/DDDUserUpController"
		method="post">
		パスワード：<input type="text" name="pass" maxlength="4"
			value="${pass}" placeholder="4文字固定の数字"><br>
		名前：<input type="text" name="name" maxlength="10"
			value="${name}" placeholder="10文字以内"><br>
		守備位置：<select name="posiId">
					<c:forEach var="list" items="${posiBean}">
						<c:choose>
							<c:when test="${list.posiId == posiId}"><option value="${list.posiId}" selected><c:out value="${list.posiName}" /></option></c:when>
							<c:otherwise><option value="${list.posiId}"><c:out value="${list.posiName}" /></option></c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
		投打：<select name="handId">
				<c:forEach var="list1" items="${handBean}">
					<c:choose>
						<c:when test="${list1.handId == handId}"><option value="${list1.handId}" selected><c:out value="${list1.hand}" /></option></c:when>
						<c:otherwise><option value="${list1.handId}" ><c:out value="${list1.hand}" /></option><c:out value="${list1.hand}" /></c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		権限：<select name="autFlg">
				<c:forEach var="list2" items="${autBean}">
					<c:choose>
						<c:when test="${list2.autId == autFlg}"><option value="${list2.autId}" selected><c:out value="${list2.aut}" /></option></c:when>
						<c:otherwise><option value="${list2.autId}"><c:out value="${list2.aut}" /></c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		<td><button type="submit" name="userId" value="${userId}" onClick="return dips()">更新</button></td><br>
		<input type="button" value="戻る" onClick="history.back()">
	</form>
	<% }else{ %>
	<form action="<%=request.getContextPath()%>/DDDUserUpController"
		method="post">
		パスワード：<input type="text" name="pass" maxlength="4"
			value="${userBean.getPass()}" placeholder="4文字固定の数字"><br>
		名前：<input type="text" name="name" maxlength="10"
			value="${userBean.getName()}" placeholder="10文字以内"><br>
		守備位置：<select name="posiId">
					<c:forEach var="list" items="${posiBean}">
						<c:choose>
							<c:when test="${list.posiId == userBean.getPosiId()}"><option value="${list.posiId}" selected><c:out value="${list.posiName}" /></option></c:when>
							<c:otherwise><option value="${list.posiId}"><c:out value="${list.posiName}" /></option></c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
		投打：<select name="handId">
				<c:forEach var="list1" items="${handBean}">
					<c:choose>
						<c:when test="${list1.handId == userBean.getHandId()}"><option value="${list1.handId}" selected><c:out value="${list1.hand}" /></option></c:when>
						<c:otherwise><option value="${list1.handId}" ><c:out value="${list1.hand}" /></option><c:out value="${list1.hand}" /></c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		権限：<select name="autFlg">
				<c:forEach var="list2" items="${autBean}">
					<c:choose>
						<c:when test="${list2.autId == userBean.getAutFlg()}"><option value="${list2.autId}" selected><c:out value="${list2.aut}" /></option></c:when>
						<c:otherwise><option value="${list2.autId}"><c:out value="${list2.aut}" /></c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		<td><button type="submit" name="userId" value="${userBean.getUserId()}" onClick="return dips()">更新</button></td><br>
		<input type="button" value="戻る" onClick="history.back()">
	</form>
	<% } %>

</body>
</html>