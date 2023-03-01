<%@page import="java.util.List"%>
<%@ page import="com.zerobase.wifi.service.BookmarkGroupService" %>
<%@ page import="com.zerobase.wifi.dto.BookmarkGroupDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
	List<BookmarkGroupDto> bookmarkGroupList = bookmarkGroupService.getAllBookmarkGroup();
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
	
	<button type="button" onclick="location.href='/bookmark-group-add.jsp'">북마크 그룹 이름 추가</button>
	
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>북마크 이름</th>
				<th>순서</th>
				<th>등록일자</th>
				<th>수정일자</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
			<%
			for(BookmarkGroupDto bookmarkGroup: bookmarkGroupList) {
			%>
			<tr>
				<td><%=bookmarkGroup.getId() %></td>
				<td><%=bookmarkGroup.getName() %></td>
				<td><%=bookmarkGroup.getOrder() %></td>
				<td><%=bookmarkGroup.getCreatedDatetime() %></td>
				<td><%=bookmarkGroup.getUpdatedDatetime() == null ? "" :  bookmarkGroup.getUpdatedDatetime()%></td>
				<td>
					<a href="${pageContext.request.contextPath }/bookmark-group-edit.jsp?id=<%=bookmarkGroup.getId() %>">수정</a>
					<a href="${pageContext.request.contextPath }/bookmark-group-delete.jsp?id=<%=bookmarkGroup.getId() %>">삭제</a>
				</td>
			</tr>
			<% } %>
		</tbody>
	</table>
	
	<script src="js/index.js"></script>
</body>
</html>

