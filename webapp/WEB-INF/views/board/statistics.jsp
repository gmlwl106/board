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
<!-- js -->
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/board/statistics.js"></script>
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
					<h3>통계</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">통계보기</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="board">
					<div id="stat">
						
						<table>

							<thead>
								<tr>
									<th>작성자</th>
									<th>01월</th>
									<th>02월</th>
									<th>03월</th>
									<th>04월</th>
									<th>05월</th>
									<th>06월</th>
									<th>07월</th>
									<th>08월</th>
									<th>09월</th>
									<th>10월</th>
									<th>11월</th>
									<th>12월</th>
									<th>총합계</th>
								</tr>
							</thead>

							<!-- 데이터 추가되는 부분 -->
							<tbody id="stat-table">
							
							</tbody>
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

<script type="text/javascript">

$(document).ready(function() {
	
	//url 파라미터 숨기기
	history.replaceState({}, null, location.pathname);
	
});

</script>

</html>
