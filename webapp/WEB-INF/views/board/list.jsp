<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EanBoard</title>
<!-- css -->
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
						<form action="./list" method="get">
							<div class="form-group text-right">
								<select name="kwdOpt">
									<c:choose>
										<c:when test="${param.kwdOpt eq 'title' }">
											<option value="title" selected="selected">제목</option>
											<option value="content">내용</option>
											<option value="name">글쓴이</option>
										</c:when>
										<c:when test="${param.kwdOpt eq 'content' }">
											<option value="title">제목</option>
											<option value="content" selected="selected">내용</option>
											<option value="name">글쓴이</option>
										</c:when>
										<c:when test="${param.kwdOpt eq 'name' }">
											<option value="title">제목</option>
											<option value="content">내용</option>
											<option value="name" selected="selected">글쓴이</option>
										</c:when>
										<c:otherwise>
											<option value="title" selected="selected">제목</option>
											<option value="content">내용</option>
											<option value="name">글쓴이</option>
										</c:otherwise>
									</c:choose>
									
								</select>
								<input type="text" name="keyword" value="${param.keyword }">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>
						<table>
							<colgroup>
								<col style="width: 50px;">
								<col style="width: 70px;">
								<col style="width: 430px;">
								<col style="width: 50px;">
								<col style="width: 100px;">
								<col style="width: 100px;">
								<col style="width: 50px;">
							</colgroup>
							<thead>
								<tr>
									<th>글번호</th>
									<th>카테고리</th>
									<th>제목</th>
									<th>첨부파일</th>
									<th>작성자</th>
									<th>작성일</th>
									<th>조회수</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${boardMap.postList }" var="postVo">
									<tr>
										<td>${postVo.POSTNO }</td>
										<td>${postVo.CATENAME }</td>
										<td class="text-left"><a href="./read/${postVo.POSTNO }?crtPage=${param.crtPage }&kwdOpt=${param.kwdOpt}&keyword=${param.keyword}">${postVo.TITLE }</a></td>
										<td>${postVo.FILECNT }</td>
										<td>${postVo.NAME }</td>
										<td>${postVo.REGDATE }</td>
										<td>${postVo.HIT }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
			
						<div id="paging">
							<ul>
								<c:if test="${boardMap.prev eq true }">
									<li><a href="${pageContext.request.contextPath }/board/list?crtPage=${boardMap.startPageBtnNo-1}&kwdOpt=${param.kwdOpt}&keyword=${param.keyword}">◀</a></li>
								</c:if>
								
								<c:forEach begin="${boardMap.startPageBtnNo }" end="${boardMap.endPageBtnNo }" step="1" var="page">
									<c:choose>
										<c:when test="${param.crtPage eq page }">
											<li class="active"><a href="${pageContext.request.contextPath }/board/list?crtPage=${page }&kwdOpt=${param.kwdOpt}&keyword=${param.keyword}">${page }</a></li>
										</c:when>
										<c:otherwise>
											<li><a href="${pageContext.request.contextPath }/board/list?crtPage=${page }&kwdOpt=${param.kwdOpt}&keyword=${param.keyword}">${page }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								
								<c:if test="${boardMap.next eq true }">
									<li><a href="${pageContext.request.contextPath }/board/list?crtPage=${boardMap.endPageBtnNo+1 }&kwdOpt=${param.kwdOpt}&keyword=${param.keyword}">▶</a></li>
								</c:if>
							</ul>
							
							
							<div class="clear"></div>
						</div>
						
						<a id="btn_write" href="./writeForm">글쓰기</a>
					
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
