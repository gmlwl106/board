<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //header -->

		<div id="container" class="clearfix">
			<c:import url="/WEB-INF/views/includes/boardAside.jsp"></c:import>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">일반게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="board">
					<div id="list">
						<form action="./list4" method="get">
							<div class="form-group text-right">
								<input type="text" name="keyword" value="${param.keyword }">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>
						<table >
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>관리</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${boardMap.boardList }" var="boardVo">			
								<tr>
									<td>${boardVo.no }</td>
									<td class="text-left"><a href="./read/${boardVo.no }">${boardVo.title }</a></td>
									<td>${boardVo.name }</td>
									<td>${boardVo.hit }</td>
									<td>${boardVo.regDate }</td>
									
									<c:if test="${authUser.no eq boardVo.userNo }">
										<td><a href="./delete/${boardVo.no }">[삭제]</a></td>
									</c:if>
								</tr>
							</c:forEach>
							</tbody>
						</table>
			
						<div id="paging">
							<ul>
								<c:if test="${boardMap.prev eq true }">
									<li><a href="${pageContext.request.contextPath }/board/list4?crtPage=${boardMap.startPageBtnNo-1}">◀</a></li>
								</c:if>
								
								<c:forEach begin="${boardMap.startPageBtnNo }" end="${boardMap.endPageBtnNo }" step="1" var="page">
									<c:choose>
										<c:when test="${param.crtPage eq page }">
											<li class="active"><a href="${pageContext.request.contextPath }/board/list4?crtPage=${page }">${page }</a></li>
										</c:when>
										<c:otherwise>
											<li><a href="${pageContext.request.contextPath }/board/list4?crtPage=${page }">${page }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								
								<c:if test="${boardMap.next eq true }">
									<li><a href="${pageContext.request.contextPath }/board/list4?crtPage=${boardMap.endPageBtnNo+1 }">▶</a></li>
								</c:if>
								
							</ul>
							
							
							<div class="clear"></div>
						</div>
						<c:if test="${not empty authUser }">
							<a id="btn_write" href="./writeForm">글쓰기</a>
						</c:if>
					
					</div>
					<!-- //list -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->
		

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>
