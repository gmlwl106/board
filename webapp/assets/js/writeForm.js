/**
 * 글 작성폼 js
 */
 
 $(document).ready(function() {
	console.log("어쩔티비");
	
	//파일 리스트
	var fileList = [];
	
	/***********************파일 추가했을때***********************/
	$('input[type="file"]').change(function() {
		console.log("파일추가");
		
		//배열 초기화
		fileList = [];
		
		var $this = $(this);
		
		var maxFileCnt = 3; //첨부파일 최대 개수
		
		var curFileCnt = $this[0].files.length; //현재 선택된 파일 개수
		
		//첨부한 파일 개수가 최대 파일 개수를 넘을때
		if(curFileCnt > maxFileCnt) {
			alert("첨부파일 최대 개수는 " + maxFileCnt + "개까지 첨부 가능합니다.");
		}
		
		//파일 이름
		var fileNames = [];
		
		for (var i = 0; i < Math.min(curFileCnt, maxFileCnt); i++) {
			
			var file = $this[0].files[i];
			//첨부파일 검증
			if(validation(file)) {
				//파일 배열에 담기
				fileList.push(file);
				fileNames.push(" "+file.name);
				console.log(fileList);
			} else {
				continue;
			}
		}
		
		//파일 이름 출력
		$('.upload-name').val(fileNames);
	});
	
	
	
	
	/***********************등록 버튼 클릭했을때***********************/
	$('#btn_add').on("click", function() {
		
		//데이터 가져오기
		var userNo = $('input[name="userNo"]').val();
		var cateNo = $('select[name="cateNo"]').val();
		var title = $('#txt-title').val();
		var content = $('#txt-content').val();
		
		//formData에 값 추가
		var formData = new FormData();
		formData.append('userNo', userNo);
		formData.append('cateNo', cateNo);
		formData.append('title', title);
		formData.append('content', content);
		if(fileList.length > 0) {
			for(var i=0; i<fileList.length; i++) {
				formData.append('file', fileList[i]);
			}
		}
		
		//ajax로 데이터 전송
		$.ajax({
			//보낼때
			url : contextPath+"/board/write",
			type : "post",
			data : formData,
			processData: false,
			contentType: false,
			enctype : 'multipart/form-data',
			
			success : function(result){
				console.log(result);
				if(result === 'success') {
					location.href = contextPath+"/board/list";
				} else {
					location.href = contextPath+"/board/writeForm?result=fail";
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
});

/*첨부파일 검증*/
function validation(obj) {
	//파일 형식
	const fileTypes = ['application/pdf', 
	'image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'image/tif', 
	'application/haansofthwp', 'application/x-hwp',
	'text/plain',
	'application/vnd.openxmlformats-officedocument.wordprocessingml.document',
	'application/x-zip-compressed'];
	
	if (obj.name.lastIndexOf('.') == -1) {
        alert("확장자가 없는 파일은 제외되었습니다.");
        return false;
    } else if (!fileTypes.includes(obj.type)) {
        alert("첨부가 불가능한 파일은 제외되었습니다.");
        return false;
    } else {
        return true;
    }
}