/**
 * 글 작성폼 js
 */
 
 $(document).ready(function() {
	console.log("어쩔티비");
	
	var fileList = [];
	
	/*파일 추가했을때*/
	$('input[type="file"]').change(function() {
		console.log("파일추가");
		
		var $this = $(this);
		
		var maxFileCnt = 3; //첨부파일 최대 개수
		
		var curFileCnt = $this[0].files.length; //현재 선택된 파일 개수
		
		//첨부한 파일 개수가 최대 파일 개수를 넘을때
		if(curFileCnt > maxFileCnt) {
			alert("첨부파일 최대 개수는 " + maxFileCnt + "개까지 첨부 가능합니다.");
		}
		
		for (var i = 0; i < curFileCnt; i++) {
			
			var file = $this[0].files[i];
			//첨부파일 검증
			if(validation(file)) {
				//파일 배열에 담기
				fileList.push(file);
			} else {
				continue;
			}
			
		}
	});
});

/*첨부파일 검증*/
function validation(obj) {
	//파일 형식
	const fileTypes = ['application/pdf', 
	'image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'image/tif', 
	'application/haansofthwp', 'application/x-hwp'];
	
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