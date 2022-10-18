/**
 * 게시글 상세보기 js
 */
 
$(document).ready(function() {
	
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
	
	/* *****************************첨부파일 이름 클릭했을때***************************** */
	$('.files').on("click", function() {
		var $this = $(this);
		var path = $this.data("path");
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
		var authUserNo = $('#authUserNo').val();
		var content = $('#cmtContent').val();
		
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
				render(cmtVo);
				$('#cmtContent').val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
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
	
	var authUserNo = $('#authUserNo').val(); //로그인한 사용자
	var userNo = $('#userNo').val(); //댓글 작성자
	
	var str = '';
	str += '<tr>';
	str += '	<td>'+cmtVo.NAME+'</td>';
	str += '	<td>';
	str += '		<div id="cmt-content'+cmtVo.CMTNO+'">';
	str += '			<span id="content-'+cmtVo.CMTNO+'">'+cmtVo.CONTENT+'</span>';
	//댓글 작성자일때 수정 삭제버튼 출력
	if(authUserNo == cmtVo.USERNO) {
		str += '		<button class="cmt-modify" name="cmt_modify" data-no="'+cmtVo.CMTNO+'">수정</button>';
		str += '		<button class="cmt-delete" name="cmt-delete" data-no="'+cmtVo.CMTNO+'">삭제</button>';
	}
	//게시글 작성자가 로그인 했을 때 답글 버튼 출력
	if(authUserNo == userNo) {
		str += '		<button class="replyBtn" id="replyBtn" data-no="'+cmtVo.CMTNO+'">답글</button>';
	}
	str += '		</div>';
	str += '	</td>';
	str += '	<td>'+cmtVo.REGDATE+'</td>';
	str += '</tr>';
	
	$('#cmtRead').append(str);
}
