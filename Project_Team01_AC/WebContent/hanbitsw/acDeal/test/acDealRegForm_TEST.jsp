<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/hanbitsw/acDeal/jqgrid/css/ui.jqgrid.css"/>" />
<script src="<c:url value="/hanbitsw/acDeal/jqgrid/js/jquery.jqGrid.min.js"/>"></script>
<script src="<c:url value="/hanbitsw/acDeal/jqgrid/src/i18n/grid.locale-kr.js"/>"></script>

 <script type="text/javascript">
 $(document).ready(function(){
	 alert("TEST start");
		$("#list").jqGrid({  
			//ajax 호출할 페이지
			url:'/Project_Team01_AC/acDealJSonList',
			//로딩중일때 출력시킬 로딩내용
			loadtext : '로딩중..',
			//응답값
			datatype: "json",
			height: 250,
			//한페이지에 출력할 데이터 갯수
			rowNum : 10,
			//페이징UI적용을 위한 속성
			pager: '#page',
			colNames:['구분','카테고리', '상세내용', '금액','거래일'],
			colModel:[
				   		{name:'typeName'},
				   		{name:'categoryName'},
				   		{name:'dealNote'},
				   	 	{name:'dealSum' }	,
				   		{name:'dealDate'}
				   	],
		 	caption: "입금/출금 조회",
		 	loadError:function(){alert("Error~!!");},
		});
	})
</script>
</head>
<body>
<h1>입금/출금 조회</h1>
<table id="list">
</table>
	<div id="page"></div>
</body>
</html>
