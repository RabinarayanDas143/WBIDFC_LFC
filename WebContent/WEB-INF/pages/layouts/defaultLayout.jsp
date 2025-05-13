<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%-- <%@ page isELIgnored="true"%> --%>




<!DOCTYPE html>
<html lang="zxx" class="js">
<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
%>
<head>

<meta charset="utf-8">
<meta name="author" content="Softnio">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description"
	content="A powerful and conceptual apps base dashboard template that especially build for developers and programmers.">
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

<!-- Fav Icon  -->
<link rel="shortcut icon" href="static/resources/images/favicon.png">
<!-- Page Title  -->
<title>i-HRMS</title>
<!-- StyleSheets  -->
<link rel="stylesheet"
	href="static/resources/assets/css/dashlite.css?ver=3.1.1">
<link id="skin-default" rel="stylesheet"
	href="static/resources/assets/css/theme.css?ver=3.1.1">
</head>


<body class="nk-body bg-lighter ">

	<div class="nk-app-root">
	
		<!-- wrap @s -->
		<div class="nk-wrap ">

			<tiles:insertAttribute name="header" />
			<tiles:insertAttribute name="menu" />
			<tiles:insertAttribute name="body" />
			<tiles:insertAttribute name="footer" />
			<!-- footer @s -->
			<div class="nk-footer bg-white">
				<div class="container-fluid">
					<div class="nk-footer-wrap">
						<div class="nk-footer-copyright">
							&copy; 2023 i-HRMS | Design and developed by <a
								href="https://idbiintech.com" target="_blank">IDBI Intech
								Ltd.</a>
						</div>

					</div>
				</div>
			</div>
			<!-- footer @e -->
		</div>
		<!-- wrap @e -->
	</div>
	<!-- app-root @e -->
	<!-- Loader -->
	<div align="center" id="Loader"
		style="background-color: #ffffff; position: fixed; opacity: 0.8; z-index: 99999; height: 100%; width: 100%; left: 0px; top: 0px; display: none">

		<img style="margin-left: 20px; margin-top: 200px;"
			src="static\resources\assets\images\Loader.gif" alt="loader">
		<div style="font-size: 17px; color: #695fa9;">Loading...Please
			Wait...</div>
	</div>
	<!-- Loader -->
	<!-- JavaScript -->
	<script src="static/resources/assets/js/bundle.js?ver=3.1.1"></script>
	<script src="static/resources/assets/js/scripts.js?ver=3.1.1"></script>
	<script src="static/resources/assets/js/charts/gd-invest.js?ver=3.1.1"></script>
</body>