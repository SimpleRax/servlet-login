<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
	<head></head>
	<body style="font-size:30px;">
		<form action="login.do" method="post">
			<fieldset>
				<legend>登录</legend>
				用户名:<input name="username"/>
				<%
					String mag = (String)request.getAttribute("login_failed");
				%>
				<span style="color:red;"><%=(mag ==null?" ":mag)%></span><br/>
				密码:<input type="password" name="pwd"><br/>
				验证码:<input name="number"/>
				<%String msg2 = (String)request.getAttribute("number_error"); %>
				<span style="color:red;"><%=(msg2==null?"":msg2) %></span>
				<br/>
				<img src="code" border="1" onclick="this.src='code?'+Math.random()"><br/>
				
				<input type="submit" value="确定"/>
			</fieldset>
		</form>
	</body>
</html>