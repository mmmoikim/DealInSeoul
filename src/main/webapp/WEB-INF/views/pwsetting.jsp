<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/pwsetting.css" />
<script type="text/javascript">
function pwcheck(){
	var inputs = document.querySelectorAll("input");
	var message="<img src='resources/image/error.png' id='error'>";
	if(inputs[1].value==inputs[2].value)
		message="<img src='resources/image/ok.png' id='ok'>";
		document.getElementById("pwcheck").innerHTML=message;
}
</script>
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
	</div>
	<div data-role="content">
		<div id="content">
			<div id="pwsettinghead">
				<h2>Password Setting</h2>
				<a href="getmypropertyAction"><img src="resources/image/btn_backX.png" id="btnx"></a>
			</div>
			<hr><br>		
	<div id="inputs">
	 <form name = "changepw" action="changepwAction" method="post">
		<div id="input1d"><input id="input1" type="text" name="currentpw" placeholder="Current Password"/></div>
		<div id="input2d"><input id="input2" type="password" name="newpw" placeholder="New Password"/></div>
		<div id="input3d"><input id="input3" type="password" name="retypepw" placeholder="re_type Password" onchange="pwcheck()"/></div>
		<div id="pwcheck"></div>
		<div id="align">  
			<div id="btn">  
				<a href="javascript:document.changepw.submit();">
					<img src="resources/image/btn_change.png" id="changebtn">
				</a>
			</div>  
		</div> 
	</form>
</div>
		</div>
	</div><!-- content -->
	<div data-role="footer" data-position="fixed" class="footer">
		<jsp:include page="home_bottom.jsp" flush="false" />
	</div>
</div>
<script type="text/javascript">
<%
if(request.getParameter("signal") != null)
{
	if(request.getParameter("signal").equals("changepwOK"))
	{
	%>
	alert("changepwOK");
	<%
	}
	if(request.getParameter("signal").equals("changepwError"))
	{
	%>
	alert("changepwError");
	<%
	}
}
%>
</script>
</body>
</html>