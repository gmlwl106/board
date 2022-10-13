/**
 * 게시글 상세보기 js
 */
 
$(document).ready(function() {
	
	//글 삭제 전 확인
	$("#btn_delete").on("click", function() {
		var result = confirm("글을 삭제 하시겠습니까?");
		
		if(result == true) {
			
			var postNo = $("#postNo").val();
			console.log(postNo);
			
			$.ajax({
				//보낼때
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
	
});
