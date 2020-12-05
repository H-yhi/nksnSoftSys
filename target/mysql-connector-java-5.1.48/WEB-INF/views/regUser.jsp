<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="nksnSoftSys.com.bean.posi.PosiBean,java.util.*,nksnSoftSys.com.bean.userInfo.UserBean,nksnSoftSys.com.bean.hand.HandBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録画面</title>
<script>
	function dips(){
		if(window.confirm('本当にいいんですね？') == false){
			return false;
		}
	}
</script>
</head>
<body>
	<span class="label label-danger">${message}</span>
	<form action="<%=request.getContextPath()%>/BBBUserRegController" method="post">
		ユーザーID：<input type="text" name="userId" maxlength="4" value="${userId}" placeholder="4文字固定の数字"><br>
		パスワード：<input type="text" name="pass" maxlength="4" value="${pass}" placeholder="4文字固定の数字"><br>
		名前：<input type="text" name="name" maxlength="10" value="${name}" placeholder="10文字以内"><br>
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
		<input type="submit" value="登録" onClick="return dips()">
		<input type="reset" value="クリア">
		<input type="button" value="戻る" onClick="history.back()">
	</form>
</body>
</html>