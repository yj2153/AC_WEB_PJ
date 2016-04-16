<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="<c:url value ="/hanbitsw/acDeal/jquery/jquery-ui.css"/>">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/hanbitsw/acDeal/jqgrid/css/ui.jqgrid.css"/>" />
<script
	src="<c:url value="/hanbitsw/acDeal/jqgrid/js/jquery.jqGrid.min.js"/>"></script>
<script
	src="<c:url value="/hanbitsw/acDeal/jqgrid/src/i18n/grid.locale-kr.js"/>"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/hanbitsw/acDeal/css/acCategoryList.css"/>" />
<script
	src="<c:url value="/hanbitsw/acDeal/script/acCategoryList.js"/>"></script>
</head>
<body>
	<h1>계정코드 관리</h1>
	<table id="list">
	</table>
	<div id="page"></div>
	<div id="DealList_button">
		<button id="addButton">추가</button>
		<button id="editButton">수정</button>
		<button id="delButton">삭제</button>
	</div>
	<div id="dialog" title="error message"></div>
</body>
</html>