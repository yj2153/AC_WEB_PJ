<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/hanbitsw/acDeal/css/reset.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/hanbitsw/acDeal/css/adDealIndex.css"/>" />

<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.1.0.min.js"></script>

</head>
<body>
	<div id="acDealIndex_menu">
		<ul class="menu_box">
			<li><a class="title">거래내역 관리</a>
				<ul>
					<li><a class="li_menu" href="acDeal.regForm.sw">- 거래내역 등록</a></li>
					<li><a class="li_menu" href="acDeal.ListView.sw">- 거래내역 조회</a></li>
				</ul></li>
		</ul>
		<ul class="menu_box">
			<li><a class="title">기타</a>
				<ul>
					<li><a class="li_menu" href="acCategory.ListView.sw">- 계정코드</a></li>
				</ul></li>
		</ul>

	</div>
	<div id="acDealIndex_view">
		<!-- 페이지 이동이 없다면 기본 페이지로 설정 -->
		<c:if test="${not empty includeName }">
			<jsp:include page="${includeName }" flush="true"/>
		</c:if>
	</div>
</body>
</html>
