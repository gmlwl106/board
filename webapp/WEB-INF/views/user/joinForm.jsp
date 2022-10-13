<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- css -->
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">

<!-- js -->
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //header -->


		<div id="container" class="clearfix">
			<div id="aside">
				<h2>회원</h2>
				<ul>
					<li>회원정보</li>
					<li>로그인</li>
					<li>회원가입</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
			
				<div id="content-head">
					<h3>회원가입</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>회원</li>
							<li class="last">회원가입</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="user">
					<div id="joinForm">
						<form id="join-form" action="./join" method="get">
	
							<!-- 아이디 -->
							<div class="form-group">
								<label class="form-text" for="input-uid">아이디</label> 
								<input type="text" id="input-uid" name="id" value="" placeholder="아이디를 입력하세요">
								<button type="button" id="idCk">중복체크</button>
								<p id="idCkResult"></p>
							</div>
	
							<!-- 비밀번호 -->
							<div class="form-group">
								<label class="form-text" for="input-pass">패스워드</label> 
								<input type="password" id="input-pass" name="password" value="" placeholder="비밀번호를 입력하세요"	>
							</div>
	
							<!-- 이름 -->
							<div class="form-group">
								<label class="form-text" for="input-name">이름</label> 
								<input type="text" id="input-name" name="name" value="" placeholder="이름을 입력하세요">
							</div>
	
							<!-- 성별 -->
							<div class="form-group">
								<span class="form-text">성별</span> 
								
								<label for="rdo-male">남</label> 
								<input type="radio" id="rdo-male" name="gender" value="male" > 
								
								<label for="rdo-female">여</label> 
								<input type="radio" id="rdo-female" name="gender" value="female" > 
	
							</div>
	
							<!-- 약관동의 -->
							<div class="form-group">
								<span class="form-text">약관동의</span> 
								
								<input type="checkbox" id="chk-agree" value="" name="">
								<label for="chk-agree">서비스 약관에 동의합니다.</label> 
							</div>
							
							<!-- 버튼영역 -->
							<div class="button-area">
								<button type="submit" id="btn-submit">회원가입</button>
							</div>
							
						</form>
					</div>
					<!-- //joinForm -->
				</div>
				<!-- //user -->
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

	var idCk = 0;
	
	//회원가입 버튼
	$("#join-form").on("submit", function() {
		
		var id = $("#input-uid").val();
		var pw = $("#input-pass").val();
		var agree = $("#chk-agree").is(":checked");
		
		if(id == "" || id == null) {
			alert("아이디를 입력해주세요.");
			return false;
		} else {
			if(idCk == 0) {
				alert("아이디 중복 체크를 해주세요.");
				return false;
			}
		}
		
		if(pw == "" || pw == null) {
			alert("비밀번호를 입력해주세요.")
			return false;
		}
		
		if(agree == false) {
			alert("약관에 동의해주세요.")
			return false;
		}
		
		
		
	});

	
	//중복체크
	$("#idCk").on("click", function() {
		
		var id = $("#input-uid").val();
		console.log(id);
		
		if(id != "" && id != null && id != " ") {
			var userVo = {
					id : id
			};
			
			$.ajax({
				url : "${pageContext.request.contextPath }/user/idCheck",
				type : "post",
				data : userVo,
				success : function(result){
					console.log(result);
					if(result == "success") {
						$("#idCkResult").html("사용할 수 있는 아이디입니다.");
						idCk = 1;
					} else {
						$("#idCkResult").html("<font color='red'>사용할 수 없는 아이디입니다.</font>");
					};
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});
		}
		
	});
	
</script>


</html>