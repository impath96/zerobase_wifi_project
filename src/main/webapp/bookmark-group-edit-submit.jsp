<%@ page import="com.zerobase.wifi.service.BookmarkGroupService" %>
<%@ page import="com.zerobase.wifi.dto.BookmarkGroupDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// bookmark-group-name, bookmark-group-order, bookmark-gorup-id
	String name = request.getParameter("bookmark-group-name");
	Integer order = Integer.valueOf(request.getParameter("bookmark-group-order"));
	Long id = Long.valueOf(request.getParameter("bookmark-group-id"));
	BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
	BookmarkGroupDto bookmarkGroup = new BookmarkGroupDto();
	bookmarkGroup.setName(name);
	bookmarkGroup.setOrder(order);
	bookmarkGroup.setId(id);
	int result = bookmarkGroupService.edit(bookmarkGroup);
	
	if(result == 1) {
%>
	<script type="text/javascript">
		alert("북마크 그룹을 수정하였습니다.");
		location.href="/bookmark-group.jsp";
	</script>
<%
	}
%>