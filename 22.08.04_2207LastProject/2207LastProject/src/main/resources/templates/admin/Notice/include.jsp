<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
// 여러개의 파일에 공통으로 들어가는 코드는 별도로 만들어 놓고 필요시 포함해서 사용하면 좋다.

// POST전송시 한글깨짐 방지
request.setCharacterEncoding("UTF-8");

// 현재 페이지 번호 받기
int currentPage = 1;
try{
	currentPage = Integer.parseInt(request.getParameter("p"));
}catch(Exception e){
	;
}

// 페이지 사이즈 받기
int pageSize = 10;
try{
	pageSize = Integer.parseInt(request.getParameter("s"));
}catch(Exception e){
	;
}

// 페이지 개수 받기
int blockSize = 10;
try{
	blockSize = Integer.parseInt(request.getParameter("b"));
}catch(Exception e){
	;
}

// 글번호 받기
int idx = 0;
try{
	idx = Integer.parseInt(request.getParameter("idx"));
}catch(Exception e){
	;
}


// 조회수 증가여부를 판단
boolean isClick = false;
try{
	isClick = Boolean.parseBoolean(request.getParameter("isClick"));
}catch(Exception e){
	;
}

request.setAttribute("idx", idx);
request.setAttribute("p", currentPage);
request.setAttribute("s", pageSize);
request.setAttribute("b", blockSize);
request.setAttribute("isClick", isClick);

request.setAttribute("newLine", "\n");
request.setAttribute("br", "<br>");


%>