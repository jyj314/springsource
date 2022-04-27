<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>
<div>
	age: <%=request.getParameter("age") %>
</div>
<div>
	num: ${num}
</div>
<div>
	<%-- 모든 요청은 컨트롤러로 가야 함 --%>
	<P><a href="/sample/basic">basic</a></P>
	<P><a href="/sample/doA">doA</a></P>
	<P><a href="/sample/login">login</a></P>
	<P><a href="/insert">insert</a></P>
</div>
<div>
	<%-- 모든 요청은 컨트롤러로 가야 함 --%>
	<P><a href="/board/insert">board insert</a></P>
	<P><a href="/board/read">board read</a></P>
	<P><a href="/board/modify">board modify</a></P>
	<P><a href="/board/list">board list</a></P>
</div>
</body>
</html>
