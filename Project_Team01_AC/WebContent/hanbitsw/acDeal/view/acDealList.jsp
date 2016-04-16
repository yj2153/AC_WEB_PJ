<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="org.springframework.util.FileCopyUtils"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.OutputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- <link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"> -->
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
	href="<c:url value="/hanbitsw/acDeal/css/acDealList.css"/>" />

<script type="text/javascript">
	//구분 값을 서버에서 받아서 출력
	var tValue = ":[ALL];${typeValue}";
	var cValue = ":[ALL];${categoryValue}";
	var etValue = "${typeValue}";
	var ecValue = "${categoryValue}";
</script>
<script src="<c:url value="/hanbitsw/acDeal/script/acDealList.js"/>"></script>
</head>
<body>
	<h1>거래내역 조회</h1>
	<div>
		<table border="1" id="acDealList_sumBox">
			<tr id="sumBox_tr">
				<td id="sumBox_th">${bankname }</td>
				<td><fmt:formatNumber pattern="###,###,###" value="${balance }" />원</td>
			</tr>
		</table>
	</div>
	<table id="list">
	</table>
	<div id="page"></div>

	<div id="DealList_button">
		<button id="editButton">수정</button>
		<button id="delButton">삭제</button>
		<button id="excelButton">인쇄</button>
	</div>
	<div id="dialog" title="error message"></div>
	<c:if test="${not empty path }">
		<a href="${path }" id="pathxls">-</a>
	</c:if>
</body>
</html>
