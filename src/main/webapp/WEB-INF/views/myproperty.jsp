<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/myproperty.css" />

</head>
<body>
<div data-role="page" style="background-color: #FFECD4;">
	<div data-role="header" data-theme="a" data-position="fixed">
		<jsp:include page="home_top.jsp" flush="false">
			<jsp:param name="id" value="${loginInfo.PLAYER_ID}" />
			<jsp:param name="title" value="${loginInfo.title}" />
			<jsp:param name="money" value="${money}" />
			<jsp:param name="station" value="${station}" />
		</jsp:include>
	</div>
	<div data-role="content">
		<div id="content">
		<div id="mypropertyhead">
			<h2>내정보</h2>
			<a href="GameOKAction"><img src="resources/image/btn_backX.png" id="btnx"></a>
			<hr><br>
		</div>
		<div id="myinfos">
		<fieldset class="ui-grid-a">
<%
Map<Object,Object> result = (Map<Object,Object>)request.getAttribute("myproperty");

Object asset = result.get("asset");
List<Map<Object,Object>> propertyList = (List<Map<Object,Object>>)result.get("propertyList");

for(int i=0; i<propertyList.size(); i++){
	if(propertyList.get(i).get("property").equals("주택")){
		%>
			<div class="ui-block-a" id="myinfo">
				<img src="resources/image/mpb1.png" class="mpb">
			</div>
		<%
	}
	else if(propertyList.get(i).get("property").equals("아파트")){
		%>		
			<div class="ui-block-a" id="myinfo">
				<img src="resources/image/mpb2.png" class="mpb">
			</div>
		<%
	}
	else{
		%>
			<div class="ui-block-a" id="myinfo">
				<img src="resources/image/mpb3.png" class="mpb">
			</div>
		<%
	}
%>
	<div class="ui-block-b">
		<div class="station"><%=propertyList.get(i).get("station")%></div>
		<div class="saleprice"><%=propertyList.get(i).get("saleprice") %> 만원</div>
	</div>
<%
}
%>
</fieldset>
</div>
<div id="asset">
	<h3 id="assetT">총 자산</h3><h2 id="assetM"><%=asset %> 만원</h2>
</div>
	</div>

	<div id="btn">
		<a href ="/dis/emailsetting" id="btn1"><div id="btnEmail">Email<br>Setting</div></a>
		<a href = "/dis/pwsetting" id="btn2"><div id="btnPW">PW<br>Setting</div></a>
		<a href = "logoutAction" id="btn3"><div id="btnLogout">Logout</div></a>
		<a href="deleteplayerAction" id="btn4"><div id="deleteplayer">회원<br>탈퇴</div></a>
	</div>
	
	<div data-role="footer" data-position="fixed" class="footer">
		<jsp:include page="home_bottom.jsp" flush="false" />
	</div>
</body>
</html>