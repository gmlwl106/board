<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="aside">
	<h2>게시판</h2>
	<ul>
		<li><a href="${pageContext.request.contextPath }/board/list">일반게시판</a></li>
		<li><a href="${pageContext.request.contextPath }/board/stat">통계보기</a></li>
		<li><a href="${pageContext.request.contextPath }/board/stat2">통계보기2</a></li>
		<li><a href="${pageContext.request.contextPath }/board/stat3">통계보기3</a></li>
	</ul>
</div>
<!-- //aside -->