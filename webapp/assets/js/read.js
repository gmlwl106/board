/**
 * 게시글 상세보기 js
 */
 
$(document).ready(function() {
	
	/* *****************************글 삭제 전 확인***************************** */
	$("#btn_delete").on("click", function() {
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
	
	/* *****************************첨부파일 이름 클릭했을때***************************** */
	$('.files').on("click", function() {
		var $this = $(this);
		var path = $this.attr("data-path");
		console.log(path);
		
		$.ajax({
			//보낼때
			url : contextPath+"/download",
			type : "post",
			data : {path},
	
			success : function(result){
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
	/* *****************************댓글***************************** */
	
	/*댓글 리스트 그리기*/
	fetchList();
	
	/*댓글 등록 버튼 클릭 했을때*/
	$('#cmtBtn').on("click", function() {
		var postNo = $('#postNo').val();
		var userNo = $('#userNo').val();
		var content = $('#cmtContent').val();
		
		var cmtVo = {
			postNo : postNo,
			userNo : userNo,
			content : content
		};
		
		$.ajax({
			url : contextPath+"/comment/write",
			type : "post",
			data : cmtVo,
	
			success : function(cmtVo){
				console.log(cmtVo);
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
	
	/*댓글 수정 버튼 클릭했을때*/
	$('.cmt-modify').on("click", function() {
		alert();
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
			/*댓글 하나씩 그리기*/
			for(var i=0; i<cmtList.length; i++) {
				render(cmtList[i]);
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
}

/*댓글 그리기*/
function render(cmtVo) {
	
	var userNo = $('#userNo').val();
	
	var str = '';
	str += '<tr>';
	str += '	<td>'+cmtVo.NAME+'</td>';
	str += '	<td>';
	str += '		'+cmtVo.CONTENT;
	//댓글 작성자일때 수정 삭제버튼 출력
	if(userNo == cmtVo.USERNO) {
		str += '		<button type="button" class="cmt-modify" name="cmt-modify" data-no="'+cmtVo.CMTNO+'">수정</button>';
		str += '		<button type="button" class="cmt-delete" name="cmt-delete" data-no="'+cmtVo.CMTNO+'">삭제</button>';
	}
	//로그인 했을때 답글 버튼 출력
	if(userNo == null || userNo == "") {
		str += '';
	} else {
		str += '		<button type="button" class="replyBtn" id="replyBtn" data-no="'+cmtVo.CMTNO+'">답글</button>';
	}
	
	str += '	</td>';
	str += '	<td>'+cmtVo.REGDATE+'</td>';
	str += '</tr>';
	
	$('#cmtRead').append(str);
}
