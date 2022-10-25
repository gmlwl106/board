/**
 * 통계 js
 */
 
$(document).ready(function() {
	console.log("gd");
	
	/*JOA 프로젝트에서 통계 데이터 가져오기*/
	
	$.ajax({
		url : "http://192.168.0.17:8088/JOA/api/stat/",
		type : "get",
		contentType : "application/json",
		//data : ,
		dataType : "json",
		
	
		success : function(data){
			console.log(data);
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});

});