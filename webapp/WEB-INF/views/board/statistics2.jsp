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
					
						<div>
							<select id="month-select">
								<option value="all" selected="selected">전체</option>
								<option value="01">1월</option>
								<option value="02">2월</option>
								<option value="03">3월</option>
								<option value="04">4월</option>
								<option value="05">5월</option>
								<option value="06">6월</option>
								<option value="07">7월</option>
								<option value="08">8월</option>
								<option value="09">9월</option>
								<option value="10">10월</option>
								<option value="11">11월</option>
								<option value="12">12월</option>
							</select>
							
							<button type="button" id="selectBtn">조회</button>
						</div>
						
						<table>

							<thead>
								<tr>
									<c:choose>
										<c:when test="${param.month eq 'all' || empty param.month }">
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
										</c:when>
										<c:otherwise>
											<th>작성자</th>
											<th>${param.month }월</th>
										</c:otherwise>
									</c:choose>
									
								</tr>
							</thead>

							<!-- 데이터 추가되는 부분 -->
							<tbody id="stat-table">
								<c:forEach items="${arr }" var="list">
									<tr>
										<td>${list.name }</td>
										
										<c:forEach items="${list.dataList }" var="data">
											<td>${data.CNT }</td>
										</c:forEach>
										
										<c:if test="${param.month eq 'all' || empty param.month }">
											<td>${list.total }</td>
										</c:if>
									</tr>
								</c:forEach>
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
	console.log("gd");
	
	//url 파라미터 숨기기
	//history.replaceState({}, null, location.pathname);
	
	var param = "${param.month}";
	if(param == null || param == "") {
		param = 'all';
	}
	$('select > option[value='+param+']').prop('selected', true);
	
	$('#selectBtn').on('click', function() {
		$('#stat-table').empty();
		var selected = $('#month-select').val();
		location.href = "${pageContext.request.contextPath }/board/stat2?month="+selected;
	});

});

</script>

</html>
