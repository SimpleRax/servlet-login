<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
	<head></head>
	<body style="font-size:30px;">
	<%
		Object obj = session.getAttribute("user");
		if(obj == null){
			//没有登录，重定向到登录页面。
			response.sendRedirect("login.jsp");
			return;
		}
	%>
		这是首页
		<%
			System.out.println("重定向之后的代码");
		%>
	</body>
</html>