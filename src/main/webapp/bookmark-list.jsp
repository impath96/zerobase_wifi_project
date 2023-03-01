<%@page import="java.util.List"%>
<%@ page import="com.zerobase.wifi.service.BookmarkService" %>
<%@ page import="com.zerobase.wifi.dto.BookmarkDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	BookmarkService bookmarkService = new BookmarkService();
	List<BookmarkDto> bookmarkList = bookmarkService.getAllBookmark();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>제로베이스 mission</title>
<link href="resource/css/index.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	<br />
	<jsp:include page="common/common_menu.jsp"></jsp:include>
	
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>북마크 이름</th>
				<th>와이파이명</th>
				<th>등록일자</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
			<%
			for(BookmarkDto bookmark: bookmarkList) {
			%>
			<tr>
				<td><%=bookmark.getBM_ID() %></td>
				<td><%=bookmark.getBM_G_NAME() %></td>
				<td><a href="/detail.jsp?mgrNo=<%=bookmark.getWF_MGR_NO()%>"><%=bookmark.getWF_NAME() %></a></td>
				<td><%=bookmark.getBM_CREATED_TIME() %></td>
				<td><a href="bookmark-delete.jsp?id=<%=bookmark.getBM_ID()%>">삭제</a></td>

			</tr>
			<% } %>
		</tbody>
	</table>
	
</body>
</html>

