<%@ page import="com.zerobase.wifi.dto.BookmarkGroupDto" %>
<%@ page import="com.zerobase.wifi.service.BookmarkGroupService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// parameter 처리(북마크 그룹 이름, 순서)
	String bookmarkGroupName = request.getParameter("bookmark-group-name");
	Integer bookmarkGroupOrder = Integer.valueOf(request.getParameter("bookmark-group-order"));
	BookmarkGroupDto bookmarkGroup = new BookmarkGroupDto();
	bookmarkGroup.setName(bookmarkGroupName);
	bookmarkGroup.setOrder(bookmarkGroupOrder);
	
	BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
	int result = bookmarkGroupService.save(bookmarkGroup);
	
%>
<%
	if(result == 1) {
		%>
		<script type="text/javascript">
			alert("북마크 그룹 정보를 저장하였습니다.");
			location.href = "/bookmark-group.jsp";
		</script>
		<%
	}
%>
