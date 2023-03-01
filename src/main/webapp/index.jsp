<%@ page import="java.util.List"%>
<%@ page import="com.zerobase.wifi.dto.WifiInfoDto" %>
<%@ page import="com.zerobase.wifi.dto.Position" %>
<%@ page import="com.zerobase.wifi.service.WifiService" %>
<%@ page import="com.zerobase.wifi.dto.HistoryDto" %>
<%@ page import="com.zerobase.wifi.service.HistoryService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% 
	String lnt = request.getParameter("lnt");
	String lat = request.getParameter("lat");
	
	lnt = lnt == null ? "0.0" : lnt;
	lat = lat == null ? "0.0" : lat;
	
	List<WifiInfoDto> wifiInfoList = null;
	Position curPos = new Position(Double.valueOf(lat), Double.valueOf(lnt));
	
	if(!curPos.isEmpty()) {
		
		WifiService wifiService = new WifiService();
		wifiInfoList = wifiService.getList(curPos); 
		
		HistoryDto history = new HistoryDto();
		history.setX_coordinate(curPos.getLat());
		history.setY_coordinate(curPos.getLnt());
		HistoryService historyService = new HistoryService();
		historyService.saveHistory(history);
	}

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
	<div>
		<form action="/">
			<span>LAT(위도) : </span> 
			<input type="text" name="lat" id="lat" value="<%=lat %>" />
			<span>, LNT(경도) : </span> 
			<input type="text" name="lnt" id="lnt" value="<%=lnt %>" />
			<input type="button" value="내 위치 가져오기" onclick="fetchPosition()" />
			<input type="submit" value="근처 WIPI 정보 보기"/> <br />
		</form>

		<table>
			<thead>
				<tr>
					<th>거리(km)</th>
					<th>관리번호</th>
					<th>자치구</th>
					<th>와이파이명</th>
					<th>도로명 주소</th>
					<th>상세 주소</th>
					<th>설치위치(층)</th>
					<th>설치유형</th>
					<th>설치기관</th>
					<th>서비스구분</th>
					<th>망종류</th>
					<th>설치년도</th>
					<th>실내외구분</th>
					<th>WIFI접속환경</th>
					<th>X좌표</th>
					<th>Y좌표</th>
					<th>작업일자</th>
				</tr>
			</thead>
			<tbody>
				<% if(wifiInfoList == null || wifiInfoList.isEmpty()) { %>
					<tr >
						<td height = "36px" style="text-align: center;" colspan="17" >위치 정보를 입력한 후에 조회해 주세요.</td>
					</tr>
				<%}else { 
				
					for(WifiInfoDto wifi: wifiInfoList) {
						
				%>
					<tr style="font-size: small;">
						<td><%=wifi.getDISTANCE() %></td>
						<td><%=wifi.getX_SWIFI_MGR_NO() %></td>
						<td><%=wifi.getX_SWIFI_WRDOFC() %></td>
						<td><a href="${pageContext.request.contextPath }/detail.jsp?mgrNo=<%=wifi.getX_SWIFI_MGR_NO()%>"><%=wifi.getX_SWIFI_MAIN_NM() %></a></td>
						<td><%=wifi.getX_SWIFI_ADRES1() %></td>
						<td><%=wifi.getX_SWIFI_ADRES2() %></td>
						<td><%=wifi.getX_SWIFI_INSTL_FLOOR() %></td>
						<td><%=wifi.getX_SWIFI_INSTL_TY() %></td>
						<td><%=wifi.getX_SWIFI_INSTL_MBY() %></td>
						<td><%=wifi.getX_SWIFI_SVC_SE() %></td>
						<td><%=wifi.getX_SWIFI_CMCWR() %></td>
						<td><%=wifi.getX_SWIFI_CNSTC_YEAR() %></td>
						<td><%=wifi.getX_SWIFI_INOUT_DOOR() %></td>
						<td><%=wifi.getX_SWIFI_REMARS3() %></td>
						<td><%=wifi.getLAT() %></td>
						<td><%=wifi.getLNT() %></td>
						<td><%=wifi.getWORK_DTTM() %></td>
					</tr>
				<% }
				} %>
			</tbody>
		</table>
	</div>
	<script src="resource/js/index.js"></script>
</body>
</html>

