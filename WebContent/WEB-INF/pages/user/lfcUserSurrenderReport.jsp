<%@page import="org.springframework.ui.Model"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.servlet.http.HttpSession"%>

<%@page import=" com.emp.model.LfcModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="sttlesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxpg6fy4IWvTNh0E263XmFcJlSAwiGgAW/dAiS6JXm"
	crossorigin="anonymous">

<script type="text/javascript" src="static/js/hrms/lfcSurrenderDetails.js"></script>

</head>
<body>
<!-- 	<div class="container">
		<div class="row">
			<h1>Report</h1>
		</div> -->
		
		
		<div class="nk-content ">
	<div class="container-fluid">
		<div class="nk-content-inner">
			<div class="nk-content-body">
				<div class="components-preview wide-lg mx-auto">
					<div class="nk-block-head nk-block-head-lg wide-sm"></div>
					<!-- .nk-block -->
					<div class="nk-block nk-block-lg">
						<div class="nk-block-head">
							<div class="nk-block-head-content">
								<h4 class="title nk-block-title">Surrender Cum Encashment Report</h4>
								<div class="nk-block-des">
									<p>LFC Surrender Report details.</p>
								</div>
							</div>
						</div>
						<div class="card card-bordered">
							<div class="card-inner">
								<div class="row">
									<div class="col-lg-12">

		
		<br>
		<table class="table table-striped table-borderd">
			<thead class="table-dark">
				<tr>
					<th>Emp ID</th>
					<th>Name</th>
					<th>Dependent</th>
					<th>Occupation</th>
					<th>Annual Income</th>
 					<th>Leave Type</th>
 					<th>Total Days</th>
                     <th>HR Status</th>
                    <th>Internal Audit Status</th>
                    <th>CS Status</th>
				</tr>
			</thead>
			 <tbody id="user_Surrender_Report_table">

			</tbody>
		</table></div>


						</div>
					</div>
					<!-- .nk-block -->
				</div>
				<!-- .components-preview wide-lg mx-auto -->
			</div>
		</div>
	</div>
</div>
<!-- content @e -->
</div>
</div>
</body>
</html>

