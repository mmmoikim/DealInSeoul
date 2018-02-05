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
<link rel="stylesheet" href="resources/css/searchpassword.css" />
<script type="text/javascript">
function search(){
	var email=document.querySelector("#email").value;
	location.href="searchpasswordAction?email=" + email;
	
}
window.onload =function(){
	if(<%=request.getSession().getAttribute("resultPW")%>=="1")
	alert("이메일을 잘못 입력하셨습니다");
}
</script>
</head>
<body>
<div data-role="page" style="background-image: url(resources/image/background.png);">
	<div data-role="content">
	<div id="head">
		<a href="/dis/login"><img src="resources/image/btn_back.png" id="backbtn"></a>
		<h2 id="headSearch">Search Password</h2>
	</div>
	<div id="inputs">
		<input type="text" placeholder="Enter Email" id="email"/>
	</div>
	<img src="resources/image/btn_pws.png" id="btnsearchpassword" onclick="search()">
</body>
</html>