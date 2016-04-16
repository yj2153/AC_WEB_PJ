<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 
// 전체 리스트 요청
	@RequestMapping("/acDeal.List.sw")
	public String acDealShow(String page, Model model) {
		int MAXSIZE = 10; // 10개씩 화면에 노출
		int listCnt = adService.getDealCount(); // 총 리스트 갯수 구하기
		int maxPage = (int) (listCnt / MAXSIZE + 0.95); // 페이지로 구하면 몇 페이지?
		int nowPage = 0; // 현재 페에지 기본 0페이지
		// 현재 페이지가 있다면
		if (page != null) {
			nowPage = Integer.parseInt(page);
		} // end if

		//int startPage = (((int) ((double) nowPage / MAXSIZE + 0.9)) - 1) * 10 + 1;
		//List<AcDealVO> list = adService.getDealList(startPage);

		model.addAttribute("nowPage", nowPage); // 현재페이지.
		model.addAttribute("maxPage", maxPage); // 최대페이지
		//model.addAttribute("list", list);
		return "/hanbitsw/acDeal/view/adDealList.jsp";
	}// acDealShow -->

</head>
<body>
	<h1>입금/출금 조회</h1>
	<table border="1" style="width: 960px;">
		<thead>
			<tr>
				<th>구분</th>
				<th>계정코드</th>
				<th>상세내역</th>
				<th>금액</th>
				<th>거래날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="list">
				<tr>
					<td>${list.getTypeName()}</td>
					<td>${list.getCategoryName()}</td>
					<td>${list.getDealNote()}</td>
					<td><fmt:formatNumber value="${list.getDealSum()}" pattern="###,###,###" /></td>
					<td><fmt:formatDate value="${list.getDealDate()}" pattern="yyyy/MM/dd" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:choose>
		<c:when test="${requestScope.nowPage <= 1}">	[이전]&nbsp;	</c:when>
		<c:otherwise>
			<a href="acDeal.List.sw?page=${requestScope.page -1}"> [이전]</a>
		</c:otherwise>
	</c:choose>
	<c:out value="${ requestScope.nowPage}" default="-1"/>
	<c:choose>
		<c:when test="${requestScope.nowPage >= requestScope.maxPage }">	[다음]&nbsp;	</c:when>
		<c:otherwise>
			<a href="acDeal.List.sw?page=${requestScope.nowPage + 1}"> [다음]</a>
		</c:otherwise>
	</c:choose>
</body>
</html>