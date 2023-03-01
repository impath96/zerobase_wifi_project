<%@ page import="com.zerobase.wifi.service.BookmarkService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Long id = Long.valueOf(request.getParameter("bookmark-id"));
  BookmarkService bookmarkService = new BookmarkService();
  int result = bookmarkService.delete(id);

  if(result == 1) {
%>
<script type="text/javascript">
  alert("북마크 그룹을 삭제하였습니다.");
  location.href="/bookmark-list.jsp";
</script>
<%
  }
%>
