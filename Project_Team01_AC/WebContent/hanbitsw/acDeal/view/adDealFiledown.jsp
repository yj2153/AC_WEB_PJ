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
//���ϴٿ�ε�
	String path = request.getParameter("path");
	if (path != null) {
		File file = new File(path);
		response.setContentType("application/download; charset=utf-8");
		response.setContentType(response.getContentType());
		response.setContentLength((int)file.length());

		// �Ʒ� �κп��� euc-kr �� utf-8 �� �ٲٰų� URLEncoding�� ���ϰų� ���� �׽�Ʈ��
	    // �ؼ� �ѱ��� ���������� �ٿ�ε� �Ǵ� ������ �����Ѵ�.
		String userAgent = request.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1;
		String fileName = null;

		fileName = URLEncoder.encode(file.getName(), "utf-8");

		// ���� ��ũ�� Ŭ������ �� �ٿ�ε� ���� ȭ���� ��µǰ� ó���ϴ� �κ�[��ó] [jsp] jsp ���� �ٿ�ε� / jsp��  Ȱ���� file download ���� |�ۼ��� ��ť
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
	
		if (file.exists()) {
			byte b[] = new byte[(int) file.length()];
			BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
			
			out.clear();
			out = pageContext.pushBody();

			BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());

			//������ �б�
			int read = 0;
			while ((read = fin.read(b)) != -1) {
				outs.write(b, 0, read);
			}
			outs.close();
			fin.close();
		}
	}
%>
<!-- ������ �̵� -->
<script type="text/javascript">
location.href = "acDeal.ListView.sw";
</script>
</head>
<body>

</body>
</html>