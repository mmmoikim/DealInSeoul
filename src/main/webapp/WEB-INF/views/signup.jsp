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
<link rel="stylesheet" href="resources/css/signup.css" />
<script type="text/javascript">
var xmlRequest;
function pwcheck(){
	var pw1=document.getElementById("pw").value;
	var pw2=document.getElementById("pw1").value;
	var message="<img src='resources/image/error.png' id='error'>";
	if(pw1==pw2){
		message="<img src='resources/image/ok.png' id='ok'>";
	}
	document.getElementById("pwData").innerHTML= message;
}
function emailcheck() {
	xmlRequest = xmlRequest || new XMLHttpRequest();
	xmlRequest.onreadystatechange = function() {
		if (xmlRequest == 4 || xmlRequest.status == 200) {
			document.getElementById("emailData").innerHTML = xmlRequest.responseText;
		}
	}
	var input = document.getElementById("email").value;
	var url = "emailcheckAction"
	xmlRequest.open("POST", url, "true");
	xmlRequest.send(input);
}

function idcheck() {
	xmlRequest = xmlRequest || new XMLHttpRequest();
	xmlRequest.onreadystatechange = function() {
		if (xmlRequest == 4 || xmlRequest.status == 200) {
			document.getElementById("idData").innerHTML = xmlRequest.responseText;
		}
	}
	var input = document.getElementById("id").value;
	var url = "idcheckAction"
	xmlRequest.open("POST", url, "true");
	xmlRequest.send(input);
}

function signup() {
	document.getElementById("signup").submit();
}
</script>
</head>
<body>
<div data-role="page" style="background-image: url(resources/image/background.png);">
	<div data-role="content">
	<div id="head">
		<a href="/dis/login"><img src="resources/image/btn_back.png" id="backbtn"></a>
		<h2 id="headsignup">SIGN UP</h2>
	</div>
	<form action="signupAction" method="post" id="signup">
		<div id="emaild"><input type="text" name="email" id="email" placeholder="Enter email" onchange="emailcheck()" /></div>
		<div id="emailData"></div>
		<div id="pwd"><input type="password" name="pw" id="pw" placeholder="Enter PW" /></div>
		<div id="pw1d"><input type="password" name="pw1" id="pw1" placeholder="Enter Re PW" onchange="pwcheck()" /></div>
		<div id="pwData"></div>
		<div id="idd"><input type="text" name="id" id="id" placeholder="Enter PlayerID" onchange="idcheck()" /></div>
		<div id="idData"></div>
	</form>
	<img src="resources/image/btn_Signup.png" id="btnsignup" onclick="signup()">
</body>
</html>