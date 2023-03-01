<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>제로베이스 mission</title>
<link href="resource/css/bookmark-group-form.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	<br />
	<jsp:include page="common/common_menu.jsp"></jsp:include>
	
	<form action="/bookmark-group-add-submit.jsp" method="post">
		<table>
			<tbody>
				<tr>
					<td>북마크 이름</td>
					<td><input type="text" name="bookmark-group-name"></td>
				</tr>
				<tr>
					<td>순서</td>
					<td><input type="text" name="bookmark-group-order"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center"><input type="submit" value="추가"></td>
				</tr>
			</tbody>
		</table>
	</form>	
	
</body>
</html>

