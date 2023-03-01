<%@page import="java.util.List"%>
<%@ page import="com.zerobase.wifi.service.HistoryService" %>
<%@ page import="com.zerobase.wifi.dto.HistoryDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	HistoryService historyService = new HistoryService();
	List<HistoryDto> historyList = historyService.getHistoryList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="resource/css/history.css" rel="stylesheet" type="text/css" />

<title>Insert title here</title>
</head>
<body>
	<h1>위치 히스토리 목록</h1>
	<br/>
	<jsp:include page="common/common_menu.jsp"></jsp:include>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>조회일자</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
			<%
			if(historyList == null) {
			%>
				<tr>
					<td height = "36px" style="text-align: center;" colspan="5" >저장되어 있는 위치 히스토리가 없습니다.</td>
				</tr>
			<%	
			}else {
				for(HistoryDto history: historyList) {		
				%>
				<tr>
					<td><%= history.getId() %></td>
					<td><%= history.getX_coordinate() %></td>
					<td><%= history.getY_coordinate() %></td>
					<td><%= history.getCreated_datetime() %></td>
					<td><button>삭제</button></td>
				</tr>
				<%
				}
			}
			%>
		</tbody>
	</table>
</body>
</html>