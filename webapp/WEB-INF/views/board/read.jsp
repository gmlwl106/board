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
					<div id="read">
						<form action="#" method="get">
							<!-- 작성자 -->
							<div class="form-group">
								<span class="form-text">작성자</span>
								<span class="form-value">박깜이</span>
							</div>
							
							<!-- 조회수 -->
							<div class="form-group">
								<span class="form-text">조회수</span>
								<span class="form-value">1</span>
							</div>
							
							<!-- 작성일 -->
							<div class="form-group">
								<span class="form-text">작성일</span>
								<span class="form-value">2022-10-12</span>
							</div>
							
							<!-- 제목 -->
							<div class="form-group">
								<span class="form-text">제 목</span>
								<span class="form-value">제목입니다</span>
							</div>
							
							<!-- 첨부파일 -->
							<div class="form-group">
								<span class="form-text">첨부파일</span>
								<span class="form-value">다운로드</span>
							</div>
						
							<!-- 내용 -->
							<div id="txt-content">
								<span class="form-value" >글입니다.글입니다.글입니다.글입니다.글입니다.글입니다.글입니다.글입니다.글입니다.글입니다.글입니다.글입니다.글입니다.</span>
							</div>
							
							<a id="btn_modify" href="">수정</a>
							<a id="btn_list" href="">목록</a>
							
						</form>
						<!-- //form -->
					</div>
					<!-- //read -->
					
					<div id="comment">	
						<h3>댓글</h3>				
						<table id="cmtWrite">
							<colgroup>
								<col style="width: 10%;">
								<col style="width: 80%;">
								<col style="width: 30%;">
							</colgroup>
							
							<tr>
								<td>박깜이</td>
								<td><input type="text" id="cmtContent"></td>
								<td><button id="cmtBtn">등록</button></td>
							</tr>
						</table>
					
						<table id="cmtRead">
							<colgroup>
								<col style="width: 10%;">
								<col style="width: 100%;">
							</colgroup>
							<tr>
								<td>박깜이</td>
								<td>답글답글답글답글</td>
							</tr>
						</table>
					</div>
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
