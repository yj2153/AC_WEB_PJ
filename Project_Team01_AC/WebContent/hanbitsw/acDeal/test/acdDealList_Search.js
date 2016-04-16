$(document).ready(function() {
		var typeValue =":[ALL];입금:입금";
		var typeValue2=":[ALL];입금:입금;출금:출금";
		$.ajax("/Project_Team01_AC/acDealJSonType.List.sw", {
			type : 'post', //요청방식
			success : function(respdata) {					
				typeValue = decodeURIComponent(respdata); //한글 디코딩				
				alert(typeValue);
			}
		});
		
		$("#list").jqGrid({
			//ajax 호출할 페이지
			url : '/Project_Team01_AC/acDealJSon.List.sw',
			//로딩중일때 출력시킬 로딩내용
			loadtext : '로딩중..',
			//응답값
			datatype : "json",
			height : 250,
			//한페이지에 출력할 데이터 갯수
			rowNum : 10,
			//페이징UI적용을 위한 속성
			pager : '#page',
			colNames : [ '구분', '카테고리', '상세내용', '금액', '거래일' ],
			colModel : [ {
				name : 'typeName',
				stype : 'select',
				searchoptions : {//value:":[ALL];입금:입금;출금:출금"}
					value : typeValue
					}
			}, {
				name : 'categoryName'
			}, {
				name : 'dealNote'
			}, {
				name : 'dealSum'
			}, {
				name : 'dealDate'
			} ],
			caption : "입금/출금 조회",
			loadError : function() {
				alert("Error~!!");
			},
			loadonce : true,
		});
		$('#list').jqGrid('filterToolbar');
	});
	
	