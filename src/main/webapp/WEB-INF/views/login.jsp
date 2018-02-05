<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
$(document).bind("mobileinit", function() {
    $.mobile.ajaxLinksEnabled = false;
    $.mobile.ajaxFormsEnabled = false;
    $.mobile.ajaxEnabled = false;
 });
</script>
<script src="resources/jqmobile/jquery.mobile-1.4.5.js"></script>
<link rel="stylesheet" href="resources/jqmobile/jquery.jquery.mobile.external-png-1.4.5.css" />
<link rel="stylesheet" href="resources/jqmobile/jquery.mobile.icons-1.4.5.css" />
<link rel="stylesheet" href="resources/jqmobile/jquery.mobile.inline-png-1.4.5.css" />
<link rel="stylesheet" href="resources/jqmobile/jquery.mobile.structure-1.4.5.css" />
<link rel="stylesheet" href="resources/jqmobile/jquery.mobile.theme-1.4.5.css" />
<link rel="stylesheet" href="resources/jqmobile/jquery.mobile-1.4.5.css" />
<link rel="stylesheet" href="resources/css/login.css" />
<script type="text/javascript">
function locationSearch() {
	var gps = navigator.geolocation;
	if (gps) {
		gps.getCurrentPosition(locationResult, function(error) {
			alert("error code: " + error.code + " message: "
					+ error.message);
		});
	} else {
		function locationResult(position) {
			if (position) {
				lat = position.coords.latitude;
				lon = position.coords.longitude;
				var input = document.querySelectorAll("input");
				location.href = "loginAction?lat=" + lat + "&lon=" + lon
						+ "&email=" + input[0].value + "&pw="
						+ input[1].value;
			}
		}
	}
}
</script>
</head>
<body>
<div data-role="page" style="background-image: url(resources/image/background.png);">
	<div data-role="content">
	<div id="logo"><img src="resources/image/DealInSeoul.png" id="logoimg"></div>
	<div id="input1">
		<input type="text" placeholder="Enter Email Adress" name="email" id="inputE" data-role="button">
		<input type="password" placeholder="Enter password" name="pw" id="inputP">
	</div>
	<div id="input2">
		<fieldset class="ui-grid-a">
			<div class="ui-block-a">
				<input type="checkbox" id="checkbox" name="checkbox-0"class="custom" data-mini="true" />
				<label id="checkboxlabel">ID,PW저장</label>
			</div>
			<div class="ui-block-b">
				<a href="/dis/searchpassword" id="linksearchpassword">
				<img src="resources/image/searchpassword.png" name="searchpassword" id="searchpassword"><label id="searchpasswordlabel">패스워드 찾기</label></a>
			</div>
		</fieldset>	
	</div>
<script type="text/javascript">
	var inputs = document.querySelectorAll("input");
	aClickHandling = function() {
		if (inputs[2].checked) {
			localStorage.loginEmail = inputs[0].value;
			localStorage.loginPw = inputs[1].value;
		}
	};
	window.onload = function() {
		//저장소에 데이터 여부를 확인해서 활용
	if (localStorage.loginEmail && localStorage.loginPw) {
		inputs[0].value = localStorage.loginEmail;
		inputs[1].value = localStorage.loginPw;
	}
	if(<%=request.getSession().getAttribute("resultPW")%>!=null&&<%=request.getSession().getAttribute("resultPW")%>!="1"){
		alert("당신의 비밀번호는 : " + <%=request.getSession().getAttribute("resultPW")%>+" 입니다");
	}
};
document.querySelector("#checkbox").addEventListener("click", aClickHandling);
</script>
	<div id="input3">
		<img src="resources/image/btn1.png" id="btnLogin" onclick="locationSearch()">
		<a href="/dis/signup"><img src="resources/image/btn2.png" id="btnSingup"></a>
	</div>
</div>
<script type="text/javascript">
<%
if(request.getParameter("signal") != null)
{
   if(request.getParameter("signal").equals("loginError"))
   {
   %>
   alert("loginError");
   <%
   }
}
%>
</script>
</body>
</html>