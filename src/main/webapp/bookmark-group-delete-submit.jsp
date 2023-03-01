<%@ page import="com.zerobase.wifi.service.BookmarkGroupService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// bookmark-gorup-id
	Long id = Long.valueOf(request.getParameter("bookmark-group-id"));
	BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
	int result = bookmarkGroupService.delete(id);
	
	if(result == 1) {
%>
	<script type="text/javascript">
		alert("북마크 그룹을 삭제하였습니다.");
		location.href="/bookmark-group.jsp";
	</script>
<%
	}
%>