<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- css -->
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
<!-- js -->
<script type="text/javascript">
	window.contextPath = '${pageContext.request.contextPath}';
</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/read.js"></script>

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
							<li>일반게시판</li>
							<li class="last">${pMap.post.CATENAME }</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="board">
					<div id="read">
						<form action="#" method="get">
							<!-- 글번호 -->
							<input type="hidden" id="postNo" value="${pMap.post.POSTNO }">
						
							<!-- 작성자 -->
							<div class="form-group">
								<span class="form-text">작성자</span>
								<span class="form-value">${pMap.post.NAME }</span>
								<input type="hidden" id="userNo" value="${pMap.post.USERNO }">
							</div>
							
							<!-- 조회수 -->
							<div class="form-group">
								<span class="form-text">조회수</span>
								<span class="form-value">${pMap.post.HIT }</span>
							</div>
							
							<!-- 작성일 -->
							<div class="form-group">
								<span class="form-text">작성일</span>
								<span class="form-value">${pMap.post.REGDATE }</span>
							</div>
							
							<!-- 제목 -->
							<div class="form-group">
								<span class="form-text">제 목</span>
								<span class="form-value">${pMap.post.TITLE }</span>
							</div>
							
							<!-- 첨부파일 -->
							<div class="form-group">
								<span class="form-text">첨부파일</span>
								<c:choose>
									<c:when test="${empty pMap.fileList }">
										<span class="form-value">파일없음</span>
									</c:when>
									
									<c:otherwise>
										<c:forEach items="${pMap.fileList }" var="fileVo">
											<a href="${pageContext.request.contextPath }/download/${fileVo.fileNo}">${fileVo.saveName }</a>
										</c:forEach>
									</c:otherwise>
									
								</c:choose>
								
							</div>
						
							<!-- 내용 -->
							<div id="txt-content">
								<span class="form-value" >${pMap.post.CONTENT }</span>
							</div>
							
							
							<a id="btn_modify" href="${pageContext.request.contextPath }/board/modifyForm/${pMap.post.POSTNO}">수정</a>
							<a id="btn_delete" href="">삭제</a>
							<a id="btn_list" href="${pageContext.request.contextPath }/board/list?crtPage=${crtPage }&kwdOpt=${kwdOpt }&keyword=${keyword}">목록</a>
							
						</form>
						<!-- //form -->
					</div>
					<!-- //read -->
					
					<div id="comment">	
						<h3>댓글</h3>				
						
						<!-- 댓글 등록 -->
						<div id="cmtWrite">
							<span class="form-text">${authUser.name }</span>
							<input type="hidden" id="authUserNo" value="${authUser.userNo }">
							
							<input type="text" id="cmtContent" maxlength="30">
							<button type="button" id="cmtBtn">등록</button>
						</div>
						
						<!-- 댓글 리스트 출력 -->
						<div id="cmtRead">
							<%-- <div class="comments">
								<span class="form-text">박깜이</span>
								<span class="cmt-content">
									안녕하세요. 댓글입니다^^
									<button type="button" class="cmt-modify">수정</button>
									<button type="button" class="cmt-delete">삭제</button>
									<button class="replyBtn" id="replyBtn">답글</button>
								</span>
								<span>2022-10-17 16:44</span>
							</div>
							
							<div class="reply">
								<img src="${pageContext.request.contextPath }/assets/image/reply.png">
								<input type="text" class="replytxt" maxlength="30">
								<button type="button" class="replySubmit">답글 등록</button>
							</div> --%>
						</div>
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
	
<!-- 댓글 수정 모달창 -->
<div class="modal" tabindex="-1" role="dialog" id="modifyCmt">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">댓글 수정</h5>
      </div>
      <div class="modal-body">
      	<input type="hidden" id="cmtNo" name="cmtNo" value="">
        <input type="text" id="modContent" name="modContent" value="" maxlength="30">
      </div>
      <div class="modal-footer">
      	<button type="button" id="modCmt-btn" class="btn btn-primary">수정</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
      </div>
    </div><!-- modal-content -->
  </div><!-- modal-dialog -->
</div><!-- modal -->
</body>

</html>
