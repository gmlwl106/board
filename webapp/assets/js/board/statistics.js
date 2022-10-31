/**
 * 통계 js
 */
 
$(document).ready(function() {
	console.log("gd");
	
	getData("all");
	
	$('#selectBtn').on('click', function() {
		$('#stat-table').empty();
		var month = $('#month-select').val();
		getData(month);

	});

});


/*JOA 프로젝트에서 통계 데이터 가져오기*/
function getData(month) {
	
	for(var i=2; i<15; i++) {
		$('th:nth-child('+i+')').show();
	}
	
	$.ajax({
		url : "http://192.168.0.17:8088/JOA/api/stat/",
		type : "get",
		data : {month},
		contentType : "application/json",
		dataType : "json",
		
	
		success : function(data){
			
			//데이터 행 그리기
			for(var i=0; i<data.length; i++) {
				render(data[i]);
			}

			if(month !== "all") {
				for(var i=2; i<14; i++) {
					if(month == (i-1)) {
						console.log(i-1);
						continue;
					}
					$('th:nth-child('+i+')').hide();
				}
				$('th:nth-child(14), td:nth-child(3)').hide();
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
}

/*데이터 행 그리기*/
function render(data) {
	//console.log("render");
	
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