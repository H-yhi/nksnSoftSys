<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="nksnSoftSys.com.bean.con.*,java.util.*,nksnSoftSys.com.bean.userInfo.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% UserBean userBean = (UserBean)request.getAttribute("userBean"); %>
<% String message = (String)request.getAttribute("message"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>個人成績反映画面</title>
<style>
	table{
  width: 50%;
  border-collapse: collapse;
  border-spacing: 0;
}

table th,table td{
  padding: 10px 0;
  text-align: center;
}

table tr:nth-child(odd){
  background-color: #eee
}
</style>
<script>
	function dips() {
		if (window.confirm('本当にいいんですね？') == false) {
			return false;
		}
	}
</script>
</head>
<body>
	<form action="<%=request.getContextPath()%>/EEEupKjnGraController" method="post">
		<span class="label label-danger">${message}</span>
		<button name="userId" value="${userBean.getUserId()}" onClick="return dips()">反映する</button>
		<input type="button" value="戻る" onClick="history.back()">
		<table>
			<tr><th>名前</th><th>打席</th><th>打数</th>
	    		<th>単打</th><th>二塁打</th><th>三塁打</th><th>本塁打</th>
	    		<th>打点</th><th>盗塁</th><th>四球</th><th>死球</th>
	    		<th>犠打</th><th>犠飛</th>
	    	</tr>
	    		<tr>
	    			<% if(message != null){ %>
	    				<td><c:out value="${userBean.getName()}"/></td>
		    			<td><input name="atBat" value="${atBat}" size="5" maxlength="2"></td>
		    			<td><input name="batCon" value="${batCon}" size="5" maxlength="2"></td>
		    			<td><input name="hit" value="${hit}" size="5" maxlength="2"></td>
		    			<td><input name="secHit" value="${secHit}" size="5" maxlength="2"></td>
		    			<td><input name="thrHit" value="${thrHit}" size="5" maxlength="2"></td>
		    			<td><input name="homeRun" value="${homeRun}" size="5" maxlength="2"></td>
		    			<td><input name="rbi" value="${rbi}" size="5" maxlength="2"></td>
		    			<td><input name="stBase" value="${stBase}" size="5" maxlength="2"></td>
		    			<td><input name="foBall" value="${foBall}" size="5" maxlength="2"></td>
		    			<td><input name="deBall" value="${deBall}" size="5" maxlength="2"></td>
		    			<td><input name="sacRoll" value="${sacRoll}" size="5" maxlength="2"></td>
		    			<td><input name="sacFly" value="${sacFly}" size="5" maxlength="2"></td>
	    			<% }else{ %>
		    			<td><c:out value="${userBean.getName()}"/></td>
		    			<td><input name="atBat" value="0" size="5" maxlength="2"></td>
		    			<td><input name="batCon" value="0" size="5" maxlength="2"></td>
		    			<td><input name="hit" value="0" size="5" maxlength="2"></td>
		    			<td><input name="secHit" value="0" size="5" maxlength="2"></td>
		    			<td><input name="thrHit" value="0" size="5" maxlength="2"></td>
		    			<td><input name="homeRun" value="0" size="5" maxlength="2"></td>
		    			<td><input name="rbi" value="0" size="5" maxlength="2"></td>
		    			<td><input name="stBase" value="0" size="5" maxlength="2"></td>
		    			<td><input name="foBall" value="0" size="5" maxlength="2"></td>
		    			<td><input name="deBall" value="0" size="5" maxlength="2"></td>
		    			<td><input name="sacRoll" value="0" size="5" maxlength="2"></td>
		    			<td><input name="sacFly" value="0" size="5" maxlength="2"></td>
	    			<% } %>
	    		</tr>
		</table>
	</form>
</body>
</html>