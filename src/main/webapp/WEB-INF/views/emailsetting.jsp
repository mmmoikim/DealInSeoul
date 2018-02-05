<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/emailsetting.css" />
</head>
<body>
<div data-role="page" class="page" style="background:#FFECD4;">
	<div data-role="header"data-position="fixed">
		<jsp:include page="home_top.jsp" flush="false">
		<jsp:param name="id" value="${loginInfo.PLAYER_ID}"/>
		<jsp:param name="title" value="${loginInfo.title}"/>
		<jsp:param name="money" value="${money}"/>
		<jsp:param name="station" value="${station}"/>
		</jsp:include>
	</div><!-- header -->
	
	<div data-role="content">
		<div id="content">
			<div id="emailsettinghead">
				<h2>Email Setting</h2>
				<a href="getmypropertyAction"><img src="resources/image/btn_backX.png" id="btnx"></a>
			</div>
			<hr><br>
	
	<div id="inputs">
		<form name = "changeemail" action="changeemailAction" method="post">
			<input id="input1" readonly="readonly" value="<%=request.getSession().getAttribute("email") %>" type="text" name="email" placeholder="Current Email"/>
			<input id="input2" type="text" name="newemail" placeholder="New Email"/>
			<input id="input3" type="password" name="pw" placeholder="Password"/>
			<a href="javascript:document.changeemail.submit();">
				<div id="align">  
					<div id="btn">  
						<img id="changebtn" src="resources/image/btn_change.png">
					</div>  
				</div> 
			</a>
		</form>
	</div>
		</div>
	</div>
	<script type="text/javascript">
	<%
	if(request.getParameter("signal") != null)
	{
		if(request.getParameter("signal").equals("changeemailOK"))
		{
		%>
		alert("changeemailOK");
		<%
		}
		if(request.getParameter("signal").equals("changeemailError"))
		{
		%>
		alert("changeemailError");
		<%
		}
	}
	%>
	</script>
	<div data-role="footer" data-position="fixed" class="footer">
		<jsp:include page="home_bottom.jsp" flush="false" />
	</div>
	
</div>
</body>
</html>