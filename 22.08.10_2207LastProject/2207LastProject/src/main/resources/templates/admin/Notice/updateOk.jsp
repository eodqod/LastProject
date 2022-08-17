<%@page import="kr.human.second.service.NoticeServiceImpl"%>
<%@page import="kr.human.second.vo.NoticeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 데이터 받기 --%>
	<jsp:useBean id="vo" class="kr.human.second.vo.NoticeVO"/>
	<jsp:setProperty property="*" name="vo"/>
	<%
		System.out.println("넘어온 값 : "+ vo);
		switch(vo.getMode()){
		case 1:
			NoticeServiceImpl.getInstance().insert(vo);
			response.sendRedirect("index.jsp?p="+currentPage+"&s="+pageSize+"&b=" + blockSize);
			return;
		case 2:
			NoticeServiceImpl.getInstance().update(vo);
			response.sendRedirect("view.jsp?p="+currentPage+"&s="+pageSize+"&b=" + blockSize + "&idx=" + idx + "&isClick=false");
			return;
		case 3:
			NoticeServiceImpl.getInstance().delete(vo);
			response.sendRedirect("index.jsp?p="+currentPage+"&s="+pageSize+"&b=" + blockSize);
			return;
		}
	%>
</body>
</html>