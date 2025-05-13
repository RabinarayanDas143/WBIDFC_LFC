<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lfc Apply</title>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 
</head>
<script type="text/javascript">
$("document").ready(function() {
	$('#PrevDataTable').hide();
	});
</script>
<body>
	<div class="container">
		<div class="row">
			<h3>Journey Completion Form Upload & Download of LFC Cum
				Encashment</h3>
		</div>
		<br> <br>
		<!-- <form id="uploadForm" action="SampleFileDownload.do" method="POST"> -->
		<div class="container mt-3">
			<div class="card" style="width: 80%">
				<div class="card-header" style="width: 100%">
					<h3 class="text-center">File Upload & Download</h3>
				</div>
				<div class="card-body" style="width: 50%">
					<!-- Upload Form -->
					<form id="uploadForm" enctype="multipart/form-data" method="POST">
						<div class="form-group">
							<label for="file">Choose a file to upload:</label> <input
								type="file" class="form-control-file" name="file" id="file"
								required>
						</div>
						<input type="hidden" name="CSRFToken" value="${CSRFToken}" /> <input
							type="button" class="btn btn-primary" id="docDownload"
							value="Sample file Download">
						<button type="submit" id="uploadDocument" class="btn btn-primary">Upload</button>
						<button onclick="UploadView()" id="prev_view"
							class="btn btn-primary">View</button>

					</form>
				</div>
			</div>
			<table id="PrevDataTable" class="table table-striped table-borderd"
				style="width: 30%;">
				<thead class="table-dark">
					<tr>
						<th>Date</th>
						<th>File</th>
					</tr>
				</thead>
				<tbody id="PrevDataTable">
				</tbody>
			</table>
		</div>
		<!-- </form> -->

		<!-- Bootstrap JS (Assuming Bootstrap is used) -->
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="static/js/hrms/generatePDF.js"></script>

	</div>
</body>
</html>
