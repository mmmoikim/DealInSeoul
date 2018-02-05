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
<link rel="stylesheet" href="resources/css/home_top.css" />
</head>
<body>
	<div data-role="navbar">
		<ul>
			<li><a href="getmypropertyAction" data-role="button" data-corners="false" data-shadow="false" id="links">${param.id}<br>${param.title}</a></li>
			<li><a href="#" data-role="button" data-corners="false" data-shadow="false" id="links"><br>${param.money}</a>
			</li>
			<li><a href="#" data-role="button" data-corners="false"	data-shadow="false" id="links"><br>${param.station}</a>
			</li>
		</ul>
	</div>
</body>
</html>