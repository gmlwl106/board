<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- css -->
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
<!-- js -->
<script type="text/javascript">
	window.contextPath = '${pageContext.request.contextPath}';
</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/writeForm.js"></script>
</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //header -->

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>게시판</h2>
				<ul>
					<li><a href="">일반게시판</a></li>
					<li><a href="">댓글게시판</a></li>
				</ul>
			</div>
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
					<div id="writeForm">
						<!-- <form action="./write" method="get"> -->
							<!-- 작성자 no -->
							<input type="hidden" name="userNo" value="${authUser.userNo }">
							<!-- 카테고리 -->
							<div class="form-group">
								<label class="form-text" for="category">카테고리</label>
								<select name="cateNo">
									<c:forEach items="${cateList }" var="cateVo">
					      				<option value="${cateVo.cateNo }">${cateVo.cateName }</option>
				      				</c:forEach>
								</select>
							</div>
							<!-- 제목 -->
							<div class="form-group">
								<label class="form-text" for="txt-title">제목</label>
								<input type="text" id="txt-title" name="title" value="" placeholder="제목을 입력해 주세요" maxlength="30">
							</div>
						
							<!-- 내용 -->
							<div class="form-group">
								<textarea id="txt-content" name="content" value=""></textarea>
							</div>
							
							<!-- 첨부파일 -->
							<div class="form-group">
								<label class="form-text">첨부파일</label>
								<div class="filebox">
								    <input type="text" class="upload-name" value="첨부파일" placeholder="첨부파일" readonly="readonly">
								    <label for="file">파일찾기</label> 
								    <input type="file" id="file" multiple>
								</div>
							</div>
							
							<a id="btn_cancel" href="./list">취소</a>
							<button id="btn_add" type="button" >등록</button>
							
						<!-- </form> -->
						<!-- //form -->
					</div>
					<!-- //writeForm -->
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
