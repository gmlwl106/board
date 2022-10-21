<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="aside">
	<h2>회원</h2>
	<ul>
		<li><a href="${pageContext.request.contextPath }/user/loginForm">로그인</a></li>
		<li><a href="${pageContext.request.contextPath }/user/joinForm">회원가입</a></li>
	</ul>
</div>
<!-- //aside -->