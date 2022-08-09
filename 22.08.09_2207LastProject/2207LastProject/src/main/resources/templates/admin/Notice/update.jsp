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
	NoticeVO NoticeVO = NoticeServiceImpl.getInstance().selectByIdx(idx, isClick);
	if(NoticeVO==null){
		response.sendRedirect("index.jsp");
	return;
	}
	request.setAttribute("vo", NoticeVO);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정하기</title>
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

		var obj = $("#subject");
		if(obj.val()==null || obj.val().trim().length==0){
			alert("제목은 반드시 입력해야 합니다.");
			obj.val("");
			obj.focus();
			return false;
		}
		var obj = $("#content");
		if(obj.val()==null || obj.val().trim().length==0){
			alert("내용은 반드시 입력해야 합니다.");
			obj.val("");
			obj.focus();
			return false;
		}
	}

</script>
<style type="text/css">
	table { width: 800px; margin: auto; padding: 5px;}
	th {padding: 5px; border: 1px solid gray; background-color: silver;text-align: center;}
	td {padding: 5px; border: 1px solid gray; }
	.title {border: none; font-size: 20pt; text-align: center;}
	.item { width: 100px; background-color: black; color: white; text-align: center;}
	/* 링크의 모양을 변경한다. */
	
	a:hover { color: black; text-decoration: none; font-weight: bold;} /* 마우스오버시 모양 */

	
</style>
</head>
<body>
	<form action="updateOk.jsp" method="post" onsubmit="return formCheck();">
	<%-- 여기에서 몇개를 숨겨서 가지고 가자 --%>
	<input type="hidden" name="p" value="${p }" />
	<input type="hidden" name="s" value="${s }" />
	<input type="hidden" name="b" value="${b }" />
	<input type="hidden" name="idx" value="${idx }" />
	<input type="hidden" name="mode" value="2" />
	<input type="hidden" name="isClick" value="false"/>
	<table>
		<tr>
			<td colspan="4" class="title"><b>공지사항 수정하기</b></td>
		</tr>
		<tr>
			<td class="item">제목</td>
			<td colspan="3">
				<input type="text" name="subject" id="subject" required="required" size="90" maxlength="100" value="${vo.subject }"/>
			</td>
		</tr>
		<tr>
			<td class="item" valign="top">내용</td>
			<td colspan="3">
				<textarea name="content" id="content" cols="93" rows="10">${vo.content }</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="4" style="border: none;text-align: right;">
				<input type="submit" value="수정하기" class="btn btn-dark" />
				<input type="button" class="btn btn-dark" 
				onclick='sendPost("view.jsp",{"p":${p},"s":${s },"b":${b },"idx":${vo.idx }})' value="내용보기">
				<input type="button" class="btn btn-dark" 
				onclick='sendPost("index.jsp",{"p":${p},"s":${s },"b":${b }})' value="목록으로">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>