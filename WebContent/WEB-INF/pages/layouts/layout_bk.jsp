<!DOCTYPE html>
<html lang="en">
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="_csrf" content="${CSRFToken}" />
<meta name="_csrf_header" content="CSRFToken" />

<link href="static/css/jquery-ui.min.css" rel="stylesheet"
	type="text/css" />
<script src="static/js/jquery-3.6.1.min.js"></script>
<script src="static/js/jquery-ui.min.js"></script>
<script src="static/js/security/href_token.js"></script>
</head>
<body>
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="menu" />
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer" />
</body>
<div align="center" id="Loader"
	style="background-color: #ffffff; position: fixed; opacity: 0.8; z-index: 99999; height: 100%; width: 100%; left: 0px; top: 0px; display: none">

	<img style="margin-left: 20px; margin-top: 200px;"
		src="static/assets/images/hourglass.gif" alt="loader">
	<div style="font-size: 17px; color: #695fa9;">Loading...Please
		Wait...</div>
</div>
</html>
