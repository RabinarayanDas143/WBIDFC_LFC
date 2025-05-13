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
<script type="text/javascript" src="static/js/hrms/hr_Details.js"></script>
<script src="${pageContext.request.contextPath}/static/js/xlsx.full.min.js"></script>


</head>
<body>
	<div class="container">
		<div class="row">
			<h3>LFC Cum Encashment Report</h3>
		</div>

		<br>
		<div style="overflow: scroll">
			<table class="table table-striped table-borderd">
				<thead class="table-dark">
					<tr>
						<th>Emp ID</th>
						<th>Name</th>
						<th>Dependent</th>
						<th>Occupation</th>
						<th>Annual Income</th>
						<th>Leave Type</th>
						<th>Leave From Date</th>
						<th>Leave To Date</th>
						<th>Total Days</th>
						<th>Place of Destination</th>
						<th>Amount of Advance</th>
						<th>Lfc Final Amount</th>
						<th>HR Status</th>
						<th>Internal Auditor Status</th>
						<th>CS Status</th>
					</tr>
				</thead>
				<tbody id="HrReport_table">

				</tbody>
			</table>
		</div>
		<div class="d-flex justify-content-end">
			<button type="button" class="btn btn-primary btn-lg" onclick="HrReport()">Export
				To Excel</button>
		</div>

	</div>
</body>
</html>