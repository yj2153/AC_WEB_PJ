//달력에 한글 입력
$(function() {
	$("#datepicker1").datepicker(
			{
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
				onSelect : function(dateText, inst) {
					// 일자 선택된 후 이벤트 발생
					checkDate(dateText);
				}// end onSelect
			});// end 달력

	$('#acDeal_form').submit(function(e) { // e = 이벤트 객체
		e.preventDefault(); // 섭밋 기본이벤트 처리 방지
		// 폼기반 요청
		var params = $('#acDeal_form').serialize(); // 요청파라미터구성
		$.ajax("/Project_Team01_AC/acDeal.Reg.sw", {
			type : 'post', // 요청방식
			data : params, // 요청파라미터 전송
			// 정상처리
			success : function(respdata) { // respdata=응답데이터
				var r = "success";
				r = decodeURIComponent(respdata); // 한글 디코딩
				if (r.match("regForm")) {
					location.href = r;
				} else {
					var text = r.replace("+", " ");
					$("#dialog").html("<p>" + text + "</p>");
					$("#dialog").dialog("open");
					event.preventDefault();
					// alert(r);
				}
			} // ---end success
		}); // ---end ajax()
	});// -- end function(e)

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
	//UI설정
	$("#subButton").button();
});

// 구한 날짜의 값을 10개의 input에 저장하기.
function checkDate(dateText) {
	var dealDate0 = $("#dealDate0").val(dateText);
	var dealDate1 = $("#dealDate1").val(dateText);
	var dealDate2 = $("#dealDate2").val(dateText);
	var dealDate3 = $("#dealDate3").val(dateText);
	var dealDate4 = $("#dealDate4").val(dateText);
	var dealDate5 = $("#dealDate5").val(dateText);
	var dealDate6 = $("#dealDate6").val(dateText);
	var dealDate7 = $("#dealDate7").val(dateText);
	var dealDate8 = $("#dealDate8").val(dateText);
	var dealDate9 = $("#dealDate9").val(dateText);
}// checkDate

// 숫자만 입력 가능, 입력한 값에콤마 추가, 총액구하기
function formatnumber(e) {
	var objTarget = e.srcElement || e.target;
	if (objTarget.type == 'text') {
		var strNum = /^[0,1,2,3,4,5,6,7,8,9,/]/; // 숫자만 가능
		var value = objTarget.value; // 해당 input의 값
		if (!strNum.test(value)) {
			objTarget.value = objTarget.value.replace(value, ''); // g가 핵심:
																	// 빠르게 타이핑할때
																	// 여러 영문자가
																	// 입력되어 버린다.
		} else {
			// 서버로 전송할 값에 금액 넣어주기.
			var list = document.getElementsByClassName(objTarget.className);
			list[1].value = uncomma(objTarget.value);
			// 콤마 추가하여 출력
			objTarget.value = comma(uncomma(objTarget.value));
			// 총액 구하기
			getAllNum();
		}// end else
	}// end if
}// formatnumber

// 총금액 합계
function getAllNum() {
	var idList = $("#Dealbody :input");
	var dSum = 0; // 입금총액
	var wSum = 0; // 출금총액
	// 5단위로 끊어서 입금일때와 출금일때를 나눈다.
	for (var i = 0; i < idList.length; i += 6) {
		// 금액이 비어있지 않을 때
		if (idList[i + 4].value.length > 0) {
			// 구분이 입금이면 입금 총액을 구한다.
			if (idList[i].id == ("dealType") && idList[i].value == "1") {
				dSum = parseInt(uncomma(idList[i + 4].value)) + parseInt(dSum);
				// 구분이 출금이면 출금 총액을 구한다.
			} else if (idList[i].id == ("dealType") && idList[i].value == "2") {
				wSum = parseInt(uncomma(idList[i + 4].value)) + parseInt(wSum);
			}// end else if
		}// end if
	}// end for

	// 금액에 콤마를 추가하여 출력
	$("#depositSum").val(comma(dSum));
	$("#withdrawSum").val(comma(wSum));
}// getAllNum

// 콤마찍기
function comma(str) {
	str = String(str);
	return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}// comma

// 콤마풀기
function uncomma(str) {
	str = String(str);
	return str.replace(/[^\d]+/g, '');
}// uncomma

