<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="${rootPath}/css/naver.css">
</head>
<body>
	<header>
		<h3>Naver Search</h3>
	</header>
	<nav>
		<a href="#">Home</a>
		<form action="naver" method="post" id="naver">
			<input type="text" id="search" name="search" placeholder="input text and enter">
			<select name="cate">
				<option value="BOOK">Book</option>
				<option value="MOVIE">Movie</option>
				<option value="NEWS">News</option>
			</select>
		</form>
	</nav>
	<section id="main-container">
		<c:forEach items="${NAVER}" var="items">
			<article class="book-items na-col s1 m1 c1">
				<h4>${items.title}</h4>
				<img alt="BookImg" src="${items.image}">
				<p>${items.description}</p>
			</article>
		</c:forEach>
	</section>
	<footer>
		<address>Copyright</address>
	</footer>
</body>
</html>










