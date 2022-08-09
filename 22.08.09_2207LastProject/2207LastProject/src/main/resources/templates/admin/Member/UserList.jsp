<%@page import="com.google.gson.Gson"%>
<%@page import="kr.human.second.service.TrainerServiceImpl"%>
<%@page import="java.util.List"%>
<%@page import="kr.human.second.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	List<MemberVO> ALLlist = TrainerServiceImpl.getInstance().SelectByAllUserList();
	request.setAttribute("Alllist", ALLlist);
%>
<%
	String trainerid = ((MemberVO)session.getAttribute("memberVO")).getId();
	List<MemberVO> list = TrainerServiceImpl.getInstance().SelectByUserList(trainerid);
	request.setAttribute("myUserList", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
<link href="${pageContext.request.servletContext.contextPath }/webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.servletContext.contextPath }/webjars/jquery/3.6.0/jquery.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.servletContext.contextPath }/webjars/bootstrap/5.1.3/js/bootstrap.min.js" ></script>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">

</script>
<style type="text/css">
/*  	table { margin: auto; border: none;}  */
/* 	th{ padding: 5px; text-align: center; background-color: silver; border: 1px solid gray;} */
/* 	td{ padding: 5px; text-align: center;  border: 1px solid gray;} */
 	.title{ padding: 5px; text-align: left; border: none; font-size: 18pt;}
</style>
</head>
<body>
	<table class="table table-dark table-hover" style="margin-top: 20px">
		<thead>
			<tr>
				<td class="title" colspan="8">전체 회원 목록</td>
			</tr>
			<tr>
				<th scope="col">이름</th>
				<th scope="col">이메일</th>
				<th scope="col">성별</th>
				<th scope="col">pt이용권</th>
				<th scope="col">등록일</th>
				<th scope="col">만기일</th>
				<th scope="col">트레이너</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty Alllist }">
				<tr>
					<td align="center" colspan="8">등록된 회원이 없습니다.</td>
				</tr>		
			</c:if>
			<c:if test="${not empty Alllist }">
				<c:forEach var="vo" items="${Alllist }">
					<tr>
						<td>${vo.name }</td>
						<td>${vo.email }</td>
						<td>${vo.gender }</td>
						<td>${vo.pt }</td>
						<td><fmt:formatDate value="${vo.startDay }" pattern="yyyy-MM-dd"/></td>
						<td><fmt:formatDate value="${vo.endDay }" pattern="yyyy-MM-dd"/></td>
						<td>${vo.myTrainer }</td>
						<td>
						<c:url var="url" value="PTupdateForm.jsp">
							<c:param name="id" value="${vo.id }"/>
						</c:url>
						<button onclick="location.href='${url}'" class="btn btn-outline-light" id="submitBtn" >등록 및 수정</button>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	
	<table class="table table-dark table-hover" style="margin: auto">
		<thead>
			<tr>
				<td class="title" colspan="8">나의 회원 목록</td>
			</tr>
			<tr>
				<th scope="col">이름</th>
				<th scope="col">이메일</th>
				<th scope="col">성별</th>
				<th scope="col">pt이용권</th>
				<th scope="col">등록일</th>
				<th scope="col">만기일</th>
				<th scope="col">트레이너</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty myUserList }">
				<tr>
					<td align="center" colspan="8">등록된 회원이 없습니다.</td>
				</tr>		
			</c:if>
			<c:if test="${not empty myUserList }">
			
				<c:forEach var="myUserList" items="${myUserList }">					
					<c:if test="${myUserList.lev == 1}">
						<tr>
							<td>${myUserList.name }</td>
							<td>${myUserList.email }</td>
							<td>${myUserList.gender }</td>
							<td>${myUserList.pt }</td>
							<td><fmt:formatDate value="${myUserList.startDay }" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${myUserList.endDay }" pattern="yyyy-MM-dd"/></td>
							<td>${myUserList.myTrainer }</td>
							<td>
							<c:url var="url" value="PTupdateForm.jsp">
								<c:param name="id" value="${myUserList.id }"/>
							</c:url>
							<button onclick="location.href='${url}'" class="btn btn-outline-light" id="submitBtn" >등록 및 수정</button>
							</td>
						</tr>			
					</c:if>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</body>
</html>