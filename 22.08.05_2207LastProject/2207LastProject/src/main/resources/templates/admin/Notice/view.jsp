<%@page import="kr.human.second.service.NoticeServiceImpl"%>
<%@page import="kr.human.second.vo.NoticeVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- JSTL 추가 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- 공통코드 삽입 --%>
<%@ include file="include.jsp" %>
<%

	System.out.println(isClick);
	// 1개 분량의 글을 얻어온다.
	NoticeVO NoticeVO = NoticeServiceImpl.getInstance().selectByIdx(idx, isClick);
	if(NoticeVO==null){ //해당글이 없다면
		response.sendRedirect("index.jsp");
		return;
	}
	request.setAttribute("vo", NoticeVO);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 내용보기</title>
<%-- axicon 사용하기 --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/axicon/axicon.min.css" />
<%-- 부트스트랩을 사용하기 위한 준비 시작 --%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<%-- 부트스트랩을 사용하기 위한 준비 끝 --%>
<%-- 사용자 정의 자바스크립트 함수를 추가한다. --%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/commons.js"></script>
<script type="text/javascript">

</script>
<style type="text/css">
	table { width: 800px; margin: auto; padding: 5px;}
	th {padding: 5px; border: 1px solid gray; background-color: silver;text-align: center;}
	td {padding: 5px; border: 1px solid gray; }
	.title {border: none; font-size: 20pt; text-align: center;}
	.item { width: 100px; background-color: black; color: white; text-align: center;}
	/* 링크의 모양을 변경한다. */
	a:hover 	{ color: black; text-decoration: none; font-weight: bold;} /* 마우스오버시 모양 */
</style>
</head>
<body>
	<table>
		<tr>
			<td colspan="4" class="title"><b>공지사항 내용보기</b></td>
		</tr>
		<tr>
			<td class="item" >작성일</td>
			<td colspan="3">
				<fmt:formatDate value="${vo.regDate }" pattern="yyyy년 MM월 dd일(E요일) hh:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td class="item">제목</td>
			<td colspan="3">
				<c:out value="${vo.subject }"></c:out>
			</td>
		</tr>
		<tr>
			<td class="item" valign="top">내용</td>
			<td colspan="3">
				<c:set var="content" value="${vo.content }"/>
				<%-- 태그 무시 --%>
				<c:set var="content" value="${fn:replace(content,'<','&lt;') }"/>
				<%-- \n을 <br>로 변경 --%>
				<c:set var="content" value="${fn:replace(content, newLine, br ) }"/>
				${content }	
			</td>
		</tr>
		<tr>
			<td colspan="4" style="border: none;text-align: right;">
				<c:if test="${sessionScope.memberVO.lev == 3}">
					<button class="btn btn-dark" 
					onclick='sendPost("update.jsp",{"p":${p},"s":${s },"b":${b },"idx":${idx },"isClick":true})'>수정</button>	
					<button class="btn btn-dark" 
					onclick='sendPost("delete.jsp",{"p":${p},"s":${s },"b":${b },"idx":${idx },"isClick":true})'>삭제</button>
				</c:if>
				<button class="btn btn-dark" 
				onclick='sendPost("index.jsp",{"p":${p},"s":${s },"b":${b }})'>목록</button>
			</td>
		</tr>
	</table>
</body>
</html>