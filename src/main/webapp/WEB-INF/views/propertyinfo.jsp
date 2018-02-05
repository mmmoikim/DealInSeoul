<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.net.URLDecoder"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/propertyinfo.css" />
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
		<div id="propertyimgdiv">
			
<%
	switch(request.getParameter("PROPERTY_NAME")){
		case "주택":
%>
			<img src="resources/image/b1.png" id="propertyimg">
<%
		break;
		case "아파트":
%>
			<img src="resources/image/b2.png" id="propertyimg">
<%
		break;
		case "빌딩":
%>
			<img src="resources/image/b3.png" id="propertyimg">
<%
			break;
	}
%>
	<div id="propertyname"><%=request.getParameter("PROPERTY_NAME")%></div>
	<div id="propertyinfo">
	<a href = "GameOKAction"><img src="resources/image/btn_backX.png" id="back"></a><br>
<fieldset class="ui-grid-a">
	<div class="ui-block-b"><%=request.getParameter("STATE")%></div>
	<div class="ui-block-a">투자금 :</div>
	<div class="ui-block-b"><%=request.getParameter("INVEST_COST")%></div>
	<div class="ui-block-a">투자횟수 :</div>
	<div class="ui-block-b"><%=request.getParameter("INVEST_COUNT")%></div>
	<div class="ui-block-a">매매가 :</div>
	<div class="ui-block-b"><%=request.getParameter("SALE_PRICE")%></div>
	<div class="ui-block-a">소유주 :</div>
	<div class="ui-block-b"><%=request.getParameter("PLAYER_ID")%></div>
</fieldset>
<%

HttpSession se = request.getSession();
Object myID = ((Map<Object, Object>)se.getAttribute("loginInfo")).get("PLAYER_ID");

if(!request.getParameter("STATION_NAME").equals("정류장없음")){
Map<Object,Object> info = new HashMap<Object,Object>();
info.put("EMAIL", request.getParameter("EMAIL"));
info.put("OWNER", request.getParameter("PLAYER_ID"));
info.put("PROPERTY_NAME", request.getParameter("PROPERTY_NAME"));
info.put("STATION_NAME", request.getParameter("STATION_NAME"));
info.put("STATION_ID", request.getParameter("STATION_ID"));
info.put("SALE_PRICE", request.getParameter("SALE_PRICE"));
info.put("PLAYER_ID", myID);
int investcount = Integer.parseInt(request.getParameter("INVEST_COUNT"));
if(investcount != 0)
info.put("INVEST_COST", Integer.parseInt(request.getParameter("INVEST_COST"))/Integer.parseInt(request.getParameter("INVEST_COUNT")));
info.put("INVEST_COST",0);
se.setAttribute("info", info);

String state = (String)request.getParameter("STATE");
switch(state){
case "소유": 
	%>
	<a href = "saleAction"><img src="resources/image/btn_sale.png" id="sale"></a>
	<a href = "investAction"><img src="resources/image/btn_upgrade.png" id="upgrade"></a>
	<%
	break;
case "분양": 
	%>
	<a href = "purchaseAction"><img src="resources/image/btn_buy.png" id="buy"></a>
	<%
	break;
	
default:
	%>
	<a href = "firstpurchaseAction"><img src="resources/image/btn_buy.png" id="buy"></a>
	<%
	break;
}//if
}
%>
		</div>
	</div>
</div>
	<div data-role="footer" data-position="fixed" class="footer">
		<jsp:include page="home_bottom.jsp" flush="false" />
	</div>
</div>
</body>
</html>