<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="nksnSoftSys.com.bean.con.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>個人成績反映画面</title>
</head>
<body>
	<table>
		<tr><th>名前</th><th>試合数</th><th>打席</th><th>打数</th>
    		<th>安打</th><th>二塁打</th><th>三塁打</th><th>本塁打</th>
    		<th>打点</th><th>盗塁</th><th>四球</th><th>死球</th>
    		<th>犠打</th><th>犠飛</th>
    	</tr>
    	<c:forEach var="userBeanList" items="${userBeanList}">
    			<tr>
    				<td><c:out value="${userBeanList.name}" /></td>
    			</tr>
    	</c:forEach>
    	<select name="game">
    	<c:forEach var="conBeanList" items="${conBeanList}">
    	<tr>
    					<option value="${conBeanList.conId}"><c:out value="${conBeanList.con}" /></option>
    	</tr>
    	</c:forEach>
    	</select>
	</table>
</body>
</html>