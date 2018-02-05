<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/ranking.css" />
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
<%

HttpSession se = request.getSession();
if(se==null || se.getAttribute("loginInfo") == null){
	response.sendRedirect("loginError.jsp");
}  
String img = "<ul>";
String userid = "<ul>";
String moneys="<ul>";
String title = "";


List<Map<Object, Object>> rankinglist = (List<Map<Object, Object>>)request.getAttribute("rankinglist");//건물 3개 Maplist
	
for(int i = 0 ; i < rankinglist.size() ; i++)
{
	switch((String)rankinglist.get(i).get("TITLE_ID"))
	{
		case "1":
			img += "<li><img src='resources/image/1.png' class='rank'></li>";
			break;
		case "2":
			img += "<li><img src='resources/image/2.png' class='rank'></li>";
			break;
		case "3":
			img += "<li><img src='resources/image/3.png' class='rank'></li>";
			break;
		case "4":
			img += "<li><img src='resources/image/4.png' class='rank'></li>";
			break;
		case "5":
			img += "<li><img src='resources/image/5.png' class='rank'></li>";
			break;
		case "6":
			img += "<li><img src='resources/image/6.png' class='rank'></li>";
			break;
	}
	userid += "<li class='rank1'>"+rankinglist.get(i).get("PLAYER_ID")+"</li>";
	moneys += "<li class='rank2'>"+rankinglist.get(i).get("ASSET")+"</li>";
	
}
img += "</ul>";
	userid += "</ul>";
	moneys +="</ul>";
	double percent = 100-Math.floor((double)rankinglist.get(0).get("PERCENT")*100);
	if (percent >= 0 && percent < 20)
		title = "지나가는 사람";
	else if(percent >= 20 && percent < 50)
		title = "소시민";
	else if(percent >= 50 && percent < 70)
		title = "동네부자";
	else if(percent >= 70 && percent < 90)
		title = "졸부";
	else if(percent >= 90 && percent < 97)
		title = "갑부";
	else if(percent >= 97 && percent <= 100)
		title = "재벌";
%>
		<div id="rankhead">
			<h2>Ranking</h2>
			<a href="GameOKAction"><img src="resources/image/btn_backX.png" id="btnx"></a>
		</div>
			<fieldset class="ui-grid-a">
			<hr>
				<div class="ui-block-a" id="top"><h3>Percent</h3><h2><%= percent %> %</h2></div>
				<div class="ui-block-b" id="top"><h3>다음주 계급</h3><h2><%= title %></h2></div>
				<hr>
			</fieldset>
			<fieldset class="ui-grid-b">
			<div class="ui-block-a">
				<% out.print(img); %>
			</div>
			<div class="ui-block-b">
				<% out.print(userid); %>
			</div>
			<div class="ui-block-c">
				<% out.print(moneys); %>
			</div>
			</fieldset>
		</div>
	</div>
	<div data-role="footer" data-position="fixed" class="footer">
		<jsp:include page="home_bottom.jsp" flush="false" />
	</div>
</div>
</body>
</html>