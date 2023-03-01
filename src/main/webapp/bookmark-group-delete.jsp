<%@ page import="com.zerobase.wifi.service.BookmarkGroupService" %>
<%@ page import="com.zerobase.wifi.dto.BookmarkGroupDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 파라미터 : id
	Long id = Long.valueOf(request.getParameter("id"));
	BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
	BookmarkGroupDto bookmarkGroup = bookmarkGroupService.getBookmarkGroup(id);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="resource/css/bookmark-group-form.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body>
	<h1>위치 히스토리 목록</h1>
	<hr>
	<jsp:include page="common/common_menu.jsp"></jsp:include>
	<h2>북마크 그룹 이름을 삭제하겠습니까? </h2>
	<form action="/bookmark-group-delete-submit.jsp" method="post">
		<table>
			<tbody>
				<tr>
					<td>북마크 이름</td>
					<td><input type="text" name="bookmark-group-name" value="<%=bookmarkGroup.getName()%>"></td>
				</tr>
				<tr>
					<td>순서</td>
					<td><input type="text" name="bookmark-group-order" value="<%=bookmarkGroup.getOrder()%>"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center">
						<a href="/bookmark-group.jsp">돌아가기</a> |
						<input type="submit" value="삭제">
						<input type="hidden" name="bookmark-group-id" value="<%=bookmarkGroup.getId()%>"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>	
</body>
</html>