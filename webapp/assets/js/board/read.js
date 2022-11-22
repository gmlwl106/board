/**
 * 게시글 상세보기 js
 */
 
$(document).ready(function() {
	
	//url 파라미터 숨기기
	history.replaceState({}, null, location.pathname);
	
	
	/* *****************************글 삭제 전 확인***************************** */
	$("#btn_delete").on("click", function() {
		//확인창 띄움
		var result = confirm("글을 삭제 하시겠습니까?");
		
		if(result == true) {
			
			var postNo = $("#postNo").val();
			
			$.ajax({
				url : contextPath+"/board/delete",
				type : "post",
				data : {postNo},
		
				success : function(result){
					if(result === "success") {
						location.href = contextPath+"/board/list";
					}
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});
		} else {
			console.log("삭제 ㄴㄴ");
		}
	});
	
	/* *****************************댓글***************************** */
	
	/*댓글 리스트 그리기*/
	fetchList();
	
	/*댓글 등록 버튼 클릭 했을때*/
	$('#cmtBtn').on("click", function() {
		var postNo = $('#postNo').val();
		var authUserNo = $('#authUserNo').val();
		var content = $('#cmtContent').val();
		
		if(content == null || content == "") {
			alert('내용을 입력해주세요');
		} else {
			var cmtVo = {
				postNo : postNo,
				userNo : authUserNo,
				content : content
			};
			
			$.ajax({
				url : contextPath+"/comment/write",
				type : "post",
				data : cmtVo,
		
				success : function(cmtVo){
					console.log(cmtVo);
					render(cmtVo, $('#cmtRead'));
					$('#cmtContent').val("");
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});
		}
		
	});
	
	
	/*댓글 수정 버튼 클릭했을때*/
	$('#cmtRead').on("click", '.cmt-modify', function() {
		var $this = $(this);
		var cmtNo = $this.data("no"); //선택한 댓글 번호
		var content = $('#content-'+cmtNo).text(); //선택한 댓글 내용
		
		$('#cmtNo').val(cmtNo);
		$('#modContent').val(content);
		
		//모달창 띄우기
		$('#modifyCmt').modal('show');
		
	});
	
	/*모달 댓글 수정 버튼 클릭했을 때*/
	$('#modCmt-btn').on("click", function() {
		var cmtNo = $('#cmtNo').val();
		var content = $('#modContent').val();
		
		if(content == null || content == "") {
			alert('내용을 입력해주세요');
		} else {
			
			var cmtVo = {
				cmtNo: cmtNo,
				content: content
			};
			
			$.ajax({
				url : contextPath+"/comment/modifyCmt",
				type : "post",
				data : cmtVo,
		
				success : function(result){
					console.log(result);
					
					if(result === 'success') {
						//댓글 내용 수정
						$('#content-'+cmtNo).text(content);
						//모달 숨기기
						$('#modifyCmt').modal('hide');
					}
					
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});
		}
		
		
	});
	
	
	/*댓글 삭제 버튼 클릭했을때*/
	$('#cmtRead').on("click", '.cmt-delete', function() {
		//확인창 띄움
		var result = confirm("글을 삭제 하시겠습니까?");
		
		if(result == true) {
			var $this = $(this);
			var cmtNo = $this.data("no"); //선택한 댓글 번호
			
			$.ajax({
				url : contextPath+"/comment/deleteCmt",
				type : "post",
				data : {cmtNo},
		
				success : function(result){
					console.log(result);
					
					if(result == 'success') {
						location.href = location.href;
					}
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});
		} else {
			console.log("삭제 ㄴㄴ");
		}
		
	});
	
	
	
	
	/* *****************************답글***************************** */
	
	/*답글 버튼 클릭 했을 때*/
	$('#cmtRead').on("click", '.replyBtn', function() {
		var $this = $(this);
		var cmtNo = $this.data("no"); //선택한 댓글 번호
		//console.log("답글버튼 "+cmtNo);
		
		//답글 입력창 토글
		$('#reply-'+cmtNo).toggle('fast');
		$this.text($this.text() == '답글'? '닫기':'답글');
	});
	
	/*답글 등록 버튼 클릭 했을 때*/
	$('#cmtRead').on("click", '.replySubmit', function() {
		var $this = $(this);
		var cmtNo = $this.data("no"); //선택한 댓글 번호
		console.log("답글등록버튼 "+cmtNo);
		var content = $('#re-txt-'+cmtNo).val();
		var postNo = $('#postNo').val();
		var authUserNo = $('#authUserNo').val();
		
		if(content == null || content == "") {
			alert('내용을 입력해주세요');
		} else {
			var cmtVo = {
				postNo : postNo,
				userNo : authUserNo,
				content : content,
				groupNo : cmtNo,
				depth : 1
			};
			
			
			$.ajax({
				url : contextPath+"/comment/writeReply",
				type : "post",
				data : cmtVo,
		
				success : function(replyVo){
					
					$('#reply-'+cmtNo).toggle(0);
					render(replyVo,$('#comments-'+cmtNo));
					$('#replyBtn-'+cmtNo).remove();
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});
		}
	});
	
});


/*댓글 리스트 그리기*/
function fetchList() {
	var postNo = $('#postNo').val();
	
	$.ajax({
		url : contextPath+"/comment/getCmtList",
		type : "post",
		data : {postNo},

		success : function(cmtList){
			
			if(cmtList.length > 0) {
				/*댓글 하나씩 그리기*/
				for(var i=0; i<cmtList.length; i++) {
					render(cmtList[i], $('#cmtRead'));
				}
			}
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
}

/*댓글 그리기*/
function render(cmtVo, obj) {
	
	var authUserNo = $('#authUserNo').val(); //로그인한 사용자
	var userNo = $('#userNo').val(); //댓글 작성자
	
	var str = '';
	str += '<div id="comments-'+cmtVo.CMTNO+'">'
	str += '<div class="comments">';
	//답글일 때 화살표 이미지 표시
	if(cmtVo.DEPTH == 1) {
	str += '	<img src="'+contextPath+'/assets/image/reply.png">';	
	}
	str += '	<span class="form-text">'+cmtVo.NAME+'</span>';
	str += '	<span class="cmt-content">';
	str += '		<span id="content-'+cmtVo.CMTNO+'">'+cmtVo.CONTENT+'</span>';
	
	//댓글 작성자일때 수정, 삭제버튼 출력
	if(authUserNo == cmtVo.USERNO) {
	str += '		<button type="button" class="cmt-modify" data-no="'+cmtVo.CMTNO+'">수정</button>';
	str += '		<button type="button" class="cmt-delete" data-no="'+cmtVo.CMTNO+'">삭제</button>';
	}
	
	//게시글 작성자가 로그인 했을 때 답글 버튼 출력
	if(authUserNo == userNo && cmtVo.DEPTH == 0 && cmtVo.CNT == 1) {
	str += '		<button class="replyBtn" id="replyBtn-'+cmtVo.CMTNO+'" data-no="'+cmtVo.CMTNO+'">답글</button>';
	}
	str += '	</span>';
	str += '	<span>'+cmtVo.REGDATE+'</span>';
	str += '</div>';
	
	//게시글 작성자가 로그인 했을 때 답글 입력창 출력
	if(authUserNo == userNo) {
	str += '<div class="reply" id="reply-'+cmtVo.CMTNO+'">';
	str += '	<img src="'+contextPath+'/assets/image/reply.png">';
	str += '	<input type="text" class="replytxt" id="re-txt-'+cmtVo.CMTNO+'" name="content" value="" maxlength="30">';
	str += '	<button type="button" class="replySubmit" data-no="'+cmtVo.CMTNO+'">답글 등록</button>';
	str += '</div>';
	str += '</div>'
	}
	
	
	$(obj).append(str);
}

/* 공유하기 */
function shareTwitter() {
	var sendText = $('#title').text(); // 전달할 텍스트
	var sendUrl = $(location).attr('href'); // 전달할 URL
	window.open("https://twitter.com/intent/tweet?text=" + sendText + "&url=" + sendUrl);
}

function shareFacebook() {
	var sendUrl = "devpad.tistory.com/"; // 전달할 URL
	window.open("http://www.facebook.com/sharer/sharer.php?u=" + sendUrl);
}

function shareLink() {
	var dummy   = document.createElement("input");
	var text    = location.href;
	
	document.body.appendChild(dummy);
	dummy.value = text;
	dummy.select();
	document.execCommand("copy");
	document.body.removeChild(dummy);

	alert('URL 주소가 복사 되었습니다');
}