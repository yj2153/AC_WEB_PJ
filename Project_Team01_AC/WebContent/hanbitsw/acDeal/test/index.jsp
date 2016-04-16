<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<head>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="index.css" />
<script type="text/javascript">

var includeName = "test2.html";
function changePage(name){ 
	location.href="index.jsp?includeName="+name;	 
}
</script>
</head>
<body>

<div id = "title"><h1>이미지</h1>누구누구님
</div>
<div id="mainmenu">
<ul class="nav">
<li><a href="#">회계</a>
<ul class="sub">
<li>
<a href="acDealRegForm.sw" >매입/매출거래</a>
</li>      
<li><a href="#" onclick="">조회</a></li>
</ul>
</li>

<li><a href="#">관리</a>
<ul class="sub sub2">
<li><a href="#">테스트1</a></li>      
<li><a href="#">테스트2</a></li>
<li><a href="#">테스트3</a></li>
</ul>
</li>

<li><a href="#">아무개1</a>
<ul class="sub sub3">
<li><a href="#">테스트1</a></li>      
<li><a href="#">테스트2</a></li>
<li><a href="#">테스트3</a></li>
</ul>
</li>

<li><a href="#">아무개2</a>
<ul class="sub sub4">
<li><a href="#">테스트1</a></li>      
<li><a href="#">테스트2</a></li>
<li><a href="#">테스트3</a></li>
</ul>
</li>

<li><a href="#">아무개3</a>
<ul class="sub sub5">
<li><a href="#">테스트1</a></li>      
<li><a href="#">테스트2</a></li>
<li><a href="#">테스트3</a></li>
</ul>
</li>

<li><a href="#">테스트1</a></li>
<li><a href="#" class="mm">테스트(핑크)</a></li>
</ul>
</div>
<div id = "mainmenu_sub"></div>
<div id = "content">
<!-- 페이지 이동이 없다면 기본 페이지로 설정 -->
<%if(request.getParameter("includeName")==null){%>	
	<%-- <jsp:include page="index2.jsp" flush="true"/> --%>
<%}else{ %>	
<!-- 페이지 이동이 있다면 이동페이지로 -->
	<jsp:include page='<%=request.getParameter("includeName") %>' flush="true"/>
<%} %> 
</div>

</body>
</html>
