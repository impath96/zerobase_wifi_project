<%@ page import="com.zerobase.wifi.service.BookmarkService" %>
<%@ page import="com.zerobase.wifi.dto.BookmarkDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 파라미터 : id
    Long id = Long.valueOf(request.getParameter("id"));
    BookmarkService bookmarkGroupService = new BookmarkService();
    BookmarkDto bookmark = bookmarkGroupService.getBookmark(id);

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
<form action="/bookmark-delete-submit.jsp" method="post">
    <table>
        <tbody>
        <tr>
            <td>북마크 이름</td>
            <td><input type="text" name="bookmark-name" value="<%=bookmark.getBM_G_NAME()%>"></td>
        </tr>
        <tr>
            <td>와이파이명</td>
            <td><input type="text" name="wifi-name" value="<%=bookmark.getWF_NAME()%>"></td>
        </tr>
        <tr>
            <td>등록일자</td>
            <td><input type="text" name="bookmark-created-datetime" value="<%=bookmark.getBM_CREATED_TIME()%>"></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <a href="/bookmark-list.jsp">돌아가기</a> |
                <input type="submit" value="삭제">
                <input type="hidden" name="bookmark-id" value="<%=bookmark.getBM_ID()%>"/>
            </td>
        </tr>
        </tbody>
    </table>
</form>
</body>
</html>
