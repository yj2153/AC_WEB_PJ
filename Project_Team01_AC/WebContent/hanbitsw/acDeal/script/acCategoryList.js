/**
 * 
 */

$(document).ready(function() {
		//버튼 ui입히기
		$("#editButton").button();
		$("#delButton").button();
		$("#addButton").button();

		$("#list").jqGrid({
			//ajax 호출할 페이지
			url : '/Project_Team01_AC/acCategory.List.sw',
			mtype : "post",
			//로딩중일때 출력시킬 로딩내용
			loadtext : '로딩중..',
			//응답값
			datatype : "json",
			height : 230,
			width : 700,
			//한페이지에 출력할 데이터 갯수
			rowNum : 10,
			//페이징UI적용을 위한 속성
			pager : '#page',
			colNames : [ '계정ID', '계정명' ],
			colModel : [ {
				name : 'categoryId',
				key : true,
			}, {
				name : 'categoryName',
				editable : true
			} ],
			caption : "카테고리",
			loadError : function() {
				alert("Error~!!");
			},
			loadonce : true,
			viewrecords : true,
			editurl : 'acCategoryGrid.EditAction.sw'
		});

		$('#list').navGrid('#page',
		// the buttons to appear on the toolbar of the grid
		{
			edit : true,
			add : true,
			del : true,
			search : false,
			refresh : false,
			view : false,
			position : "left",
			cloneToTop : false
		},
		// options for the Edit Dialog
		{
			editCaption : "The Edit Dialog",
			recreateForm : true,
			closeAfterEdit : true,
			afterSubmit : function(data, postdata, oper) {
				location.href = "acCategory.ListView.sw";
			},

			errorTextFormat : function(data) {
				return 'Error: ' + data.responseText
			}
		}, {
			closeAfterAdd : true,
			recreateForm : true,
			afterSubmit : function(data, postdata, oper) {
				location.href = "acCategory.ListView.sw";
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
		});
		//숫자외에는 방지
		function validatePositive(value, column) {
			if (isNaN(value) && value < 0)
				return [ false, "Please enter a positive value" ];
			else
				return [ true, "" ];
		}

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
					closeAfterDel : true,
					reloadAfterSubmit : false
				});
			else{
				$("#dialog").html("<p>행을 선택해 주세요.</p>");
				$("#dialog").dialog("open");
				event.preventDefault();
			}
		});
		//데이터 추가
		$("#addButton").click(function() {
			jQuery("#list").jqGrid('editGridRow', "new", {
				height : 280,
				closeAfterAdd : true,
				reloadAfterSubmit : false,
				afterSubmit : function(data, postdata, oper) {
					location.href = "acCategory.ListView.sw";
				}
			});
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