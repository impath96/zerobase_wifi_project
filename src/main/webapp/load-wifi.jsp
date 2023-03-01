<%@ page import="com.zerobase.wifi.service.WifiLoadService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	WifiLoadService wifiLoadService = new WifiLoadService();
	int cnt = wifiLoadService.loadWifi();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>
	<h1><%= cnt %> 개 데이터 모두 저장 완료</h1>
	<a href="/">홈으로 가기</a>
</body>
</html>