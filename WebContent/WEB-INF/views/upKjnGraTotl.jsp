<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="nksnSoftSys.com.bean.kjnGra.*" pageEncoding="UTF-8"%>
<%
	KjnGraBean kjnGraBean = (KjnGraBean) request.getAttribute("kjnGraBean");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	width: 50%;
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
	<form action="<%=request.getContextPath()%>/FFFupKjnGraController" method="post">
		<button name="userId" value="${kjnGraBean.getUserId()}">反映する</button>
		<table>
			<tr>
				<th>名前</th>
				<th>試合数</th>
				<th>打席</th>
				<th>打数</th>
				<th>単打</th>
				<th>二塁打</th>
				<th>三塁打</th>
				<th>本塁打</th>
				<th>打点</th>
				<th>盗塁</th>
				<th>四球</th>
				<th>死球</th>
				<th>犠打</th>
				<th>犠飛</th>
			</tr>
			<tr>
				<td><c:out value="${kjnGraBean.getName()}" /></td>
				<td><input name="game" value="${kjnGraBean.getGame()}" size="5"></td>
				<td><input name="atBat" value="${kjnGraBean.getAtBat()}"
					size="5"></td>
				<td><input name="batCon" value="${kjnGraBean.getBatCon()}"
					size="5"></td>
				<td><input name="hit" value="${kjnGraBean.getHit()}" size="5"></td>
				<td><input name="secHit" value="${kjnGraBean.getSecHit()}"
					size="5"></td>
				<td><input name="thrHit" value="${kjnGraBean.getThrHit()}"
					size="5"></td>
				<td><input name="homeRun" value="${kjnGraBean.getHomeRun()}"
					size="5"></td>
				<td><input name="rbi" value="${kjnGraBean.getRbi()}" size="5"></td>
				<td><input name="stBase" value="${kjnGraBean.getStBase()}"
					size="5"></td>
				<td><input name="foBall" value="${kjnGraBean.getFoBall()}"
					size="5"></td>
				<td><input name="deBall" value="${kjnGraBean.getDeBall()}"
					size="5"></td>
				<td><input name="sacRoll" value="${kjnGraBean.getSacRoll()}"
					size="5"></td>
				<td><input name="sacFly" value="${kjnGraBean.getSacFly()}"
					size="5"></td>
			</tr>
		</table>
	</form>
</body>
</html>