/**
 * 
 */

	$(document).ready(function() {
		$("#editButton").button();
		$("#delButton").button();
		$("#excelButton").button();

		$("#list").jqGrid({
			//ajax 호출할 페이지
			url : '/Project_Team01_AC/acDeal.List.sw',
			mtype : "post",
			//로딩중일때 출력시킬 로딩내용
			loadtext : '로딩중..',
			//응답값
			datatype : "json",
			height : 230,
			//한페이지에 출력할 데이터 갯수
			rowNum : 10,
			//페이징UI적용을 위한 속성
			pager : '#page',
			
			colNames : [ 'row', 'dealId', '구분', '계정코드', '상세내용', '금액', '거래일' ],
			colModel : [ {
				//test용
				name : 'row',				hidden : true
			}, {
				//거래 아이디 고유 키
				name : 'dealId',
				key : true,				hidden : true,				editable : true
			}, {
				//구분
				name : 'typeName',
				editable : true,				stype : 'select', align: 'center',
				searchoptions : {
					value : tValue
				},
				edittype : "select",
				editoptions : {
					value : etValue
				},
			}, {
				//카테고리
				name : 'categoryName',
				stype : 'select',				editable : true,	 align: 'center',
				searchoptions : {
					value : cValue
				},
				edittype : "select",
				editoptions : {
					value : ecValue
				},
			}, {
				//상세내용
				name : 'dealNote',
				editable : true,	 align: 'center',
			}, {
				//금액
				name : 'dealSum',
				editable : true,
				editrules : {
					custom_func : validatePositive,
					number : true,
					minValue : 0
				},
				align: 'right',
				 formatter: 'currency',
				 formatoptions : {	thousandsSeparator: ",", defaultValue: '0', decimalPlaces:0,      }
			}, {
				//날짜
				name : 'dealDate',
				editable : true,				sorttype : 'date',
				align: 'right',
				searchoptions : {
					dataInit : function(element) {
						$(element).datepicker({
							dateFormat : 'yy/mm/dd',
							prevText : '이전 달',
							nextText : '다음 달',
							monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월',
									'9월', '10월', '11월', '12월' ],
							monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
									'8월', '9월', '10월', '11월', '12월' ],
							dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
							dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
							dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
							showMonthAfterYear : true,
							changeMonth : true,
							changeYear : true,
							yearSuffix : '년',
							
							autoclose : true,
							orientation : 'bottom',
						});
					},
					sopt : [ "ge", "le", "eq" ]
				},
				editoptions : {
					dataInit : function(element) {
						$(element).datepicker({
							dateFormat : 'yy/mm/dd',
							prevText : '이전 달',
							nextText : '다음 달',
							monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월',
									'9월', '10월', '11월', '12월' ],
							monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
									'8월', '9월', '10월', '11월', '12월' ],
							dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
							dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
							dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
							showMonthAfterYear : true,
							changeMonth : true,
							changeYear : true,
							yearSuffix : '년',
							autoclose : true,
							orientation : 'bottom'
						});
					}
				}
			} ],
			caption : "입금/출금 조회",
			loadError : function() {
				alert("Error~!!");
			},
			loadonce : true,
			viewrecords : true,
			editurl : 'acDealGrid.EditAction.sw'
		});

		$('#list').navGrid('#page',
		// the buttons to appear on the toolbar of the grid
		{
			edit : true,
			add : false,
			del : true,
			search : false,
			refresh : false,
			view : false,
			position : "left",
			cloneToTop : false
		},
		// options for the Edit Dialog
		{
			height : 'auto',
			width : 620,
			editCaption : "The Edit Dialog",
			recreateForm : true,
			closeAfterEdit : true,
			afterSubmit : function(data, postdata, oper) {
				location.href = "acDeal.ListView.sw";
				//<a href="원하는_주소" download>download 속성 예제</a>
			},

			errorTextFormat : function(data) {
				return 'Error: ' + data.responseText
			}
		},
		// options for the Delete Dailog
		{
			errorTextFormat : function(data) {
				return 'Error: ' + data.responseText
			}
		}
		);
		//숫자외에는 방지
		function validatePositive(value, column) {
			if (isNaN(value) && value < 0)
				return [ false, "Please enter a positive value" ];
			else
				return [ true, "" ];
		}
		//툴바 생성
		$('#list').jqGrid('filterToolbar', {
			stringResult : true,
			searchOperators : true
		});

		//데이터 수정
		$("#editButton").click(function() {
			var gr = jQuery("#list").jqGrid('getGridParam', 'selrow');
			if (gr != null)
				jQuery("#list").jqGrid('editGridRow', gr, {
					height : 280,
					closeAfterEdit : true,
					reloadAfterSubmit : false
				});
			else{
				$("#dialog").html("<p>행을 선택해 주세요.</p>");
				$("#dialog").dialog("open");
				event.preventDefault();
			}
		});
		//데이서 삭제
		$("#delButton").click(function() {
			var gr = jQuery("#list").jqGrid('getGridParam', 'selrow');
			if (gr != null)
				jQuery("#list").jqGrid('delGridRow', gr, {
					closeAfterEdit : true,
					reloadAfterSubmit : false
				});
			else{
				$("#dialog").html("<p>행을 선택해 주세요.</p>");
				$("#dialog").dialog("open");
				event.preventDefault();
			}
		});
		//엑셀 파일
		$("#excelButton").click(function(){
			 $("#list").jqGrid('excelExport', {
				 url:'acDealGrid.Excel.sw'}
			 );
		});
		
		$("#dialog").dialog({
			autoOpen : false,
			width : 400,
			buttons : [ {
				text : "Ok",
				click : function() {
					$(this).dialog("close");
				}
			} ]
		});
	});