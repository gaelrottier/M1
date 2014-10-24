<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href = "Style/main.css"/>

	<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js'
	type='text/javascript'></script>
<script type = "text/javascript">
	function showMap(){
		$("#smokeScreen").css("display","block");
		$("#mapG").css("display","block");
	}
</script>
<title>XMLHôtels</title>
</head>
<body>
<div id = "smokeScreen"></div>
<div id = "main">

	<jsp:include page="menu.html"></jsp:include>
	<jsp:include page="content.html"></jsp:include>
	<div id="mapG">
			<jsp:include page="map.html"></jsp:include>
	</div>

</div>

</body>
</html>