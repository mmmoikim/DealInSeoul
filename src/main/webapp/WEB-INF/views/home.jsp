<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<html>
<head>
<link rel="stylesheet" href="resources/css/home.css" />
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
<%
    HttpSession se = request.getSession();
    if(se==null || se.getAttribute("loginInfo") == null){
    	response.sendRedirect("loginError.jsp");
    }
    List<Map<Object,Object>> resultList = new ArrayList();//캐스팅해서 담을곳
    
    Map<Object,Object> map = (Map<Object,Object>)se.getAttribute("loginInfo");//로긴 정보
    
    List<Map<Object, Object>> propertylist = (List<Map<Object, Object>>)se.getAttribute("propertylist");//건물 3개 Maplist
   
    for(int i = 0; i<3; i++){
		Map<Object, Object> Property = new HashMap<Object,Object>();
		Property.put("STATE", "미분양");
		Property.put("STATION_NAME", request.getSession().getAttribute("station"));
		Property.put("STATION_ID", request.getSession().getAttribute("stationid"));
		Property.put("INVEST_COST", 0);
		Property.put("INVEST_COUNT", 0);
		switch(i){
    	case 0 :
    		Property.put("PROPERTY_NAME", "주택");
    		Property.put("SALE_PRICE", 1000);
    		break;
    	case 1 :
    		Property.put("PROPERTY_NAME", "아파트");
    		Property.put("SALE_PRICE", 3000);
    		break;
    	case 2:
    		Property.put("PROPERTY_NAME", "빌딩");
    		Property.put("SALE_PRICE", 15000);
    		break;
    	}//switch
		resultList.add(i, Property);
	} //for
    if(propertylist != null)
    for(int i = 0; i<propertylist.size(); i++){
    	Map<Object,Object> eachProperty = (Map<Object,Object>)propertylist.get(i);
    	String name = (String)eachProperty.get("PROPERTY_NAME");
    	String player = (String)map.get("PLAYER_ID");
    	
    	if(player.equals((String)eachProperty.get("PLAYER_ID")))
    			eachProperty.put("STATE", "소유");
    	else
    		eachProperty.put("STATE", "분양");
    	
    	switch(name){
    	case "주택" :
    		resultList.set(0, eachProperty);
    		break;
    	case "아파트" :
    		resultList.set(1, eachProperty);
    		break;
    	case "빌딩" :
    		resultList.set(2, eachProperty);
    		break;
    	default:
    		resultList.get(i).put("STATE", "미분양");
    	}//switch
    }//for

    for(int i=0; i<resultList.size(); i++){
    	String statestr = (String)resultList.get(i).get("STATE");
    	String playerstr = (String)resultList.get(i).get("PLAYER_ID");
    	String stationstr = (String)resultList.get(i).get("STATION_NAME");
    	if(statestr != null)
    	statestr = URLEncoder.encode(statestr,"UTF-8");
    	if(playerstr != null)
    	playerstr = URLEncoder.encode(playerstr,"UTF-8");
    	if(stationstr != null)
    	stationstr = URLEncoder.encode(stationstr,"UTF-8");
    	
    	
    	String url = "/dis/propertyinfo?" + 
    				"PROPERTY_NAME=" + URLEncoder.encode((String)resultList.get(i).get("PROPERTY_NAME"),"UTF-8")+ 
    	    	 "&STATE=" + statestr +
    	    	 "&INVEST_COST=" + resultList.get(i).get("INVEST_COST") + 
    	    	 "&INVEST_COUNT=" + resultList.get(i).get("INVEST_COUNT")+
    	    	 "&SALE_PRICE=" + resultList.get(i).get("SALE_PRICE")+
    	    	 "&PLAYER_ID=" + playerstr +
    	    	 "&STATION_NAME=" + stationstr +
    	    	 "&STATION_ID=" + resultList.get(i).get("STATION_ID")+
    	    	 "&EMAIL=" + map.get("EMAIL");
    				
    	 %>
    		<h3 id="infoname"><%=resultList.get(i).get("PROPERTY_NAME")%></h3>
    	   	<a href = <%=url%>>
	    	 <fieldset class="ui-grid-a ui-btn">
	    	 	<div class="ui-block-a"><img src="resources/image/b<%=i+1%>.png" id="img"></div>
				<div class="ui-block-b" id="info">
			    	<%=resultList.get(i).get("STATE")%><br>
			    	<%=resultList.get(i).get("SALE_PRICE")%><br>
			    	<% 
			    		if (resultList.get(i).get("STATE") != "미분양"){
			    			%>
			    				<%=resultList.get(i).get("PLAYER_ID")%>
			    			<%
			    		}
			    	%>
			    </div>
			</fieldset>
    	</a>
    	 <%
    }
    %>
    </div>
	<div data-role="footer" data-position="fixed" class="footer">
		<jsp:include page="home_bottom.jsp" flush="false" />
	</div>
	<% 
    if(!map.get("lottery").equals("0"))
    {
    	%>
    	<script type="text/javascript">
    	alert('오늘의 복권\n' + <%=map.get("lottery")%> + '원에 당첨 되셨습니다!');
    	</script>
    	<%
    	map.replace("lottery", "0");
    }
%>
<script type="text/javascript">
<%
if(request.getParameter("signal") != null)
{
	if(request.getParameter("signal").equals("purchaseError"))
	{
	%>
	alert("purchaseError");
	<%
	}
	else if(request.getParameter("signal").equals("purchaseOK"))
	{
	%>
	alert("purchaseOK");
	<%
	}
	else if(request.getParameter("signal").equals("saleOK"))
	{
	%>
	alert("saleOK");
	<%
	}
	else if(request.getParameter("signal").equals("investOK"))
	{
	%>
	alert("investOK");
	<%
	}
	else if(request.getParameter("signal").equals("loginOK"))
	{
	%>
	alert("loginOK");
	<%
	}
}
%>
</script>
</div>
</body>
</html>