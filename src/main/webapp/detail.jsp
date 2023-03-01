<%@page import="java.util.List"%>
<%@ page import="com.zerobase.wifi.service.WifiService" %>
<%@ page import="com.zerobase.wifi.dto.WifiInfoDto" %>
<%@ page import="com.zerobase.wifi.service.BookmarkGroupService" %>
<%@ page import="com.zerobase.wifi.dto.BookmarkGroupDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String mgrNo = request.getParameter("mgrNo");
	WifiService wifiService = new WifiService();
	WifiInfoDto wifiDetail = wifiService.getWifiDetail(mgrNo);
	BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
	List<BookmarkGroupDto> bookmarkGroupList = bookmarkGroupService.getAllBookmarkGroup();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resource/css/detail.css" rel="stylesheet" type="text/css" />
</head>
<body>
<h1>와아파이 정보 구하기</h1>
<br>
	<jsp:include page="common/common_menu.jsp"></jsp:include>
	
	<form action="/bookmark-add-submit.jsp">
	<select name="bookmark-group" id="bookmark-group">
	    <option value="default">북마크 그룹 이름 선택</option>
	    <%for(BookmarkGroupDto bookmarkGroup: bookmarkGroupList) {%>
	    <option value="<%=bookmarkGroup.getId()%>"><%=bookmarkGroup.getName() %></option>
	    <%} %>
	</select>
	<input type="hidden" name="wifi_mgrNo" value="<%=wifiDetail.getX_SWIFI_MGR_NO()%>">
	<input type="submit" value="북마크 추가하기">
	</form>

	<table>
		<tbody>
			<tr>
				<td>거리(Km)</td>
				<td><%=wifiDetail.getDISTANCE() %></td>
			</tr>
			<tr>
				<td>관리번호</td>
				<td><%=wifiDetail.getX_SWIFI_MGR_NO() %></td>
			</tr>
			<tr>
				<td>자치구</td>
				<td><%=wifiDetail.getX_SWIFI_WRDOFC() %></td>
			</tr>
			<tr>
				<td>와이파이명</td>
				<td><a href="/detail.jsp?mgrNo=<%=wifiDetail.getX_SWIFI_MGR_NO()%>"><%=wifiDetail.getX_SWIFI_MAIN_NM() %></a></td>
			</tr>
			<tr>
				<td>도로명주소</td>
				<td><%=wifiDetail.getX_SWIFI_ADRES1() %></td>
			</tr>
			<tr>
				<td>상세주소</td>
				<td><%=wifiDetail.getX_SWIFI_ADRES2() %></td>
			</tr>
			<tr>
				<td>설치유형</td>
				<td><%=wifiDetail.getX_SWIFI_INSTL_TY() %></td>
			</tr>
			<tr>
				<td>설치기관</td>
				<td><%=wifiDetail.getX_SWIFI_INSTL_MBY() %></td>
			</tr>
			<tr>
				<td>서비스구분</td>
				<td><%=wifiDetail.getX_SWIFI_SVC_SE() %></td>
			</tr>
			<tr>
				<td>망종류</td>
				<td><%=wifiDetail.getX_SWIFI_CMCWR() %></td>
			</tr>
			<tr>
				<td>설치년도</td>
				<td><%=wifiDetail.getX_SWIFI_CNSTC_YEAR() %></td>
			</tr>
			<tr>
				<td>실내외구분</td>
				<td><%=wifiDetail.getX_SWIFI_INOUT_DOOR() %></td>
			</tr>
			<tr>
				<td>WIFI접속환경</td>
				<td><%=wifiDetail.getX_SWIFI_REMARS3() %></td>
			</tr>
			<tr>
				<td>X좌표</td>
				<td><%=wifiDetail.getLAT() %></td>
			</tr>
			<tr>
				<td>Y좌표</td>
				<td><%=wifiDetail.getLNT() %></td>
			</tr>
			<tr>
				<td>작업일자</td>
				<td><%=wifiDetail.getWORK_DTTM() %></td>
			</tr>
		</tbody>
	</table>
</body>
</html>