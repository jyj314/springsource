<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>basic</h1>
<div>
<!-- 
	경로를 시작할 때 / 로 시작한다는 의미는
	http://localhost:9090/ 뒤에서 주소를 붙여나감
	
	/없이 시작하면 현재 주소에서 뒷부분만 변경한다는 의미
 -->
 
 	<p>${page}</p>
	<%-- 모든 요청은 컨트롤러로 가야 함 --%>
	<P><a href="/">home</a></P>
	<P><a href="doA">doA</a></P><!-- /sample/doA -->
	<P><a href="login">login</a></P>
	<P><a href="insert">insert</a></P>
</div>
</body>
</html>