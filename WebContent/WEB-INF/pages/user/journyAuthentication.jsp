<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lfc Apply</title>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

  <script type="text/javascript" src="static/js/hrms/generatePDF.js"></script>  
</head>
<body>
	<div class="container">
		<div class="row">
			<h3>Journey Completion Authentication</h3>
		</div>
		<br> <br>
		  
		<div class="card" style="width: 30rem; height: 12rem">

			<div class="card-body">
				<h4 class="card-title">File Download</h4>
				<label for="exampleInputEmail1">Emp Code</label> <input
					type="number" class="form-control" id="emp_Code"
					placeholder="Enter user id">
					 <br>
					  <input type="hidden"
					name="CSRFToken" value="${CSRFToken}" />
				<div class="box-footer" style="text-align: center">
					<button id="userFileDownload" class="btn btn-primary">Download</button>
				</div>
			</div>
		</div>

	</div>
</body>
</html>
