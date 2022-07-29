<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1줄 메모장!!!</title>
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
<style type="text/css">
	table { width: 1000px; margin: auto; padding: 5px;}
	th {padding: 5px; border: 1px solid gray; background-color: black; color: white; text-align: center;}
	td {padding: 5px; border: 1px solid gray; text-align: center;}
	.title {border: none; font-size: 20pt; text-align: center;}
	.sub_title {border: none; text-align: right;}
</style>
<script type="text/javascript">
	$(function(){
		$("#cancelBtn").css('display','none');
	});
	
	// 메모폼 지우기
	function memoReset(){
		$("#cancelBtn").css('display','none');
		$("#idx").val(0);
		$("#mode").val("insert");
		$("#submitBtn").val("저장");
		$("#name").val("");
		$("#password").val("");
		$("#content").val("");
		$("#name").focus();
	}
	// 수정
	function memoUpdate(idx){
		$("#cancelBtn").css('display','inline');
		$("#idx").val(idx);
		$("#mode").val("update");
		$("#submitBtn").val("수정");
		$("#name").val($("#name"+idx).html());
		$("#password").val("");
		$("#content").val($("#content"+idx).html());
		$("#password").focus();
	}
	// 삭제
	function memoDelete(idx){
		$("#cancelBtn").css('display','inline');
		$("#idx").val(idx);
		$("#mode").val("delete");
		$("#submitBtn").val("삭제");
		$("#name").val($("#name"+idx).html());
		$("#password").val("");
		$("#content").val($("#content"+idx).html());
		$("#password").focus();
	}
	
	// Ajax로 호출할 함수
	function updateCall(){
		// Ajax로 호출할 값들을 모두 받는다.
		var p = $("#p").val();
		var s = $("#s").val();
		var b = $("#b").val();
		var idx = $("#idx").val();
		var mode = $("#mode").val();
		var name = $("#name").val();
		var password = $("#password").val();
		var ip = $("#ip").val();
		var content = $("#content").val();
		var obj = {
				"p" : p,
				"s" : s,
				"b" : b,
				"idx" : idx,
				"mode" : mode,
				"name" : name,
				"password" : password,
				"ip" : ip,
				"content" : content
		};
	//		alert(JSON.stringify(obj))
		$.ajax("update",
			{
			type : "POST",
			data : obj,
			success:function(data){
				alert("결과 : " + data);
				location.reload();
			},
			error : function(err){
				alert("에러");
			}
		});
	}

</script>
</head>
<body>
	<table>
		<tr>
			<td colspan="4" class="title">
				<b>공 지 사 항</b>
			</td>
		</tr>
		<tr>
			<td colspan="4" class="sub_title">
				${pv.pageInfo }
			</td>
		</tr>
		<tr>
			<th>No</th>
			<th width="55%">제목</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:if test="${pv.totalCount==0 }">
			<tr>
				<td colspan="5" style="text-align: center;">
				등록된 글이 없습니다.
				</td>
			</tr>
		</c:if>
		<c:if test="${pv.totalCount>0 }">
			<c:if test="${not empty pv.list }">
				<c:set var="no" value="${pv.totalCount - (pv.currentPage-1)*pv.pageSize }"/>
				<c:forEach var="vo" items="${pv.list }">
					<tr>
						<td>
							${no }
							<c:set var="no" value="${no - 1 }"/>
						</td>					
						<td style="text-align: left;">
							<a href="#" onclick='sendPost("view.jsp",{"p":${p } , "s": ${s }, "b":${b } , "idx":${vo.idx},"isClick":true})'>
								<c:out value="${vo.subject }"/>
							</a>
						</td>									
						<td>
							<fmt:formatDate value="${vo.regDate }" pattern="yy-MM-dd"/>
						</td>					
						<td>
							<c:out value="${vo.clickCount }"/>
						</td>					
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4" class="sub_title" style="text-align: center;">
						${pv.pageList }
					</td>
				</tr>
<!-- 					<div class="content"> -->
<!-- 						<div class="contentTitle"> -->
<%-- 							 <b><fmt:formatDate value="${vo.regDate }" pattern="yyyy-MM-dd(E) hh:mm:ss"/></b>에 남긴글 --%>
<%-- 							 <button type="button" class="btn btn-outline-danger btn-sm" onclick="memoUpdate(${vo.idx})">수정</button> --%>
<%-- 							 <button type="button" class="btn btn-outline-danger btn-sm" onclick="memoDelete(${vo.idx})">삭제</button> --%>
<!-- 						</div> -->
<%-- 						<div id="content${vo.idx }" style="display: none;">${vo.content }</div> --%>
<%-- 						<c:set var="content" value="${vo.content }"/> --%>
<%-- 						<c:set var="content" value='${fn:replace(content, "<","&lt;") }'/> --%>
<%-- 						<c:set var="content" value='${fn:replace(content, newLine, br) }'/> --%>
<%-- 						${content } --%>
<!-- 					</div> -->
<%-- 				<div>${pv.pageList }</div> --%>
			</c:if>
		</c:if>
		<tr>
			<td class="sub_title" colspan="6">
				<button class="btn btn-dark" 
				 onclick='sendPost("insert.jsp",{"p":${p } , "s": ${s }, "b":${b }})'>새글쓰기</button>
			</td>
		</tr>
	</table>
<!-- 	<div class="content" style="border: none;"> -->
<%-- 		<input type="hidden" name="p" value="${p }" id="p">  --%>
<%-- 		<input type="hidden" name="s" value="${s }" id="s">  --%>
<%-- 		<input type="hidden" name="b" value="${b }" id="b">  --%>
<!-- 		<input type="hidden" name="idx" value="0" id="idx">  -->
<!-- 		<input type="hidden" name="mode" value="insert" id="mode">  -->
<%-- 		<input type="hidden" name="ip" value="${pageContext.request.remoteAddr }" id="ip">  --%>
<!-- 		<input type="submit" id="submitBtn" class="btn btn-outline-danger btn-sm" value="저장" style="margin-bottom: 5px;" onclick="updateCall()"/> -->
<!-- 		<input type="button" id="cancelBtn" class="btn btn-outline-danger btn-sm" value="취소" style="margin-bottom: 5px;" onclick="memoReset()"/> -->
<!-- 		<br /> -->
<!-- 		<textarea rows="5" cols="120" name="content" id="content"></textarea> -->
<!-- 	</div> -->
	
</body>
</html>