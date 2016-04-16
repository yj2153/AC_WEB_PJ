<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<%
//파일다운로드
	String path = request.getParameter("path");
	if (path != null) {
		File file = new File(path);
		response.setContentType("application/download; charset=utf-8");
		response.setContentType(response.getContentType());
		response.setContentLength((int)file.length());

		// 아래 부분에서 euc-kr 을 utf-8 로 바꾸거나 URLEncoding을 안하거나 등의 테스트를
	    // 해서 한글이 정상적으로 다운로드 되는 것으로 지정한다.
		String userAgent = request.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1;
		String fileName = null;

		fileName = URLEncoder.encode(file.getName(), "utf-8");

		// 파일 링크를 클릭했을 때 다운로드 저장 화면이 출력되게 처리하는 부분[출처] [jsp] jsp 파일 다운로드 / jsp를  활용한 file download 구현 |작성자 인큐
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
	
		if (file.exists()) {
			byte b[] = new byte[(int) file.length()];
			BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
			
			out.clear();
			out = pageContext.pushBody();

			BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());

			//데이터 읽기
			int read = 0;
			while ((read = fin.read(b)) != -1) {
				outs.write(b, 0, read);
			}
			outs.close();
			fin.close();
		}
	}
%>
<!-- 페이지 이동 -->
<script type="text/javascript">
location.href = "acDeal.ListView.sw";
</script>
</head>
<body>

</body>
</html>