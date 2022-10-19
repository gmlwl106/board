/**
 * 게시글 수정 js
 */
 
$(document).ready(function() {
	
	/*수정 버튼 클릭했을때*/
	$('#btn_modify').on("submit", function() {
		var title = $('#txt-title').val();
		var content = $('#txt-content').val();
		
		if(title == null || title == "") {
			alert('제목을 입력해주세요.');
			return false;
		} else if(content == null || content == "") {
			alert('내용을 입력해주세요.');
			return false;
		}
	});
});