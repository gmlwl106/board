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
		dataType : "json",
		
	
		success : function(data){
			console.log(data);
			
			//데이터 행 그리기
			for(var i=0; i<data.length; i++) {
				render(data[i]);
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});

});

/*데이터 행 그리기*/
function render(data) {
	console.log("render");
	
	var str = '';
	str += '<tr>';
	//작성자
	str += '	<td>'+data.name+'</td>';
	//작성글 갯수
	for(var i=0; i<data.dataList.length; i++) {
		str += '	<td>'+data.dataList[i].CNT+'</td>';
	}
	//작성글 총갯수
	str += '	<td>'+data.total+'</td>';
	str += '</tr>';
	
	$('#stat-table').append(str);
}