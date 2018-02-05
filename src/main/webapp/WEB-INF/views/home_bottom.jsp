<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/home_bottom.css" />
<script type="text/javascript">
function locationSearch() {
		var gps = navigator.geolocation;
		if (gps) {
			gps.getCurrentPosition(locationResult, function(error) {
				alert("error code: " + error.code + " message: "+ error.message);
			});
		} else {
			function locationResult(position) {
				if (position) {
					lat = position.coords.latitude;
					lon = position.coords.longitude;
					location.href="searchlocationAction?lat="+lat+"&lon="+lon;
				}
			}
		}
	}
</script>
</head>
<body>
	<div data-role="navbar" id="navbar">
		<ul>
			<li>
				<a href="rankingAction" data-role="button" data-corners="flse"	data-shadow="false" id="link1">
				<img src="resources/image/rank.png" id="img1"></a>
			</li>
			<li><a href="javascript:locationSearch()" data-role="button" data-corners="false" data-shadow="false" id="link2">
			    <img src="resources/image/search.png" class="ui-li-icon" id="img2"><label id="searchlabel">Search Station</label></a></li>
		</ul>
	</div>
</body>
</html>