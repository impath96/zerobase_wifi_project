<%@ page import="com.zerobase.wifi.service.BookmarkService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// 파라미터 (wifi_mgrNo, bookmark-group)
	String wifiMgrNo = request.getParameter("wifi_mgrNo");
	Long bookmarkGroupId = Long.valueOf(request.getParameter("bookmark-group"));
	
	BookmarkService bookmarkService = new BookmarkService();
	int result = bookmarkService.save(wifiMgrNo, bookmarkGroupId);
	
	if(result == 1) {
%>
	<script type="text/javascript">
		location.href="/bookmark-list.jsp";
	</script>
<%
	}
%>
