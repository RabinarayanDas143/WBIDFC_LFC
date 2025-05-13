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
<script type="text/javascript">
	$(document).ready(function() {

		$('#fromdate').datepicker({
			format : 'yyyy-mm-dd',
			changeMonth : true,
			changeYear : true,
		});

		$('#todate').datepicker({
			format : 'yyyy-mm-dd',
			changeMonth : true,
			changeYear : true,

		});

	})
</script>

</head>
<body>
	<div class="container">
		<div class="row">
			<h3>Surrender Cum Encashment Authorization</h3>
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
 					<th>Total Days</th>
                      <th>Remark</th>
                      <th>Details</th>
                      <th>Action</th>
				</tr>
			</thead>
			<tbody id="Surrender_InternalAdmin_admin_table">

			</tbody>
		</table>
		</div>
	</div>
	
	<!-- Modal Box -->
	
	<div class="modal fade" id="exampleModalLong" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLongTitle"
		aria-hidden="true" >
		<div class="modal-dialog" role="document" style="width:2000px">
			<div class="modal-content" style="width:126%;">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle"><b>LFC Cum Surrender Detais</b></h5>
					<button type="button" class="close" data-bs-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
			     1.	Employee Code : <input
				type="text" readonly="readonly" id="empId" size="30"
				style="border: 0px" value=""><br>
				2.	Employee Name : <input
				type="text" readonly="readonly" id="empName" size="30"
				style="border: 0px" value=""><br>
				3.	Block Period : <input
				type="text" readonly="readonly" id="blockPeriod" size="30"
				style="border: 0px" value=""><br>
				4.	Block Start Date : <input
				type="text" readonly="readonly" id="blockStartDate" size="30"
				style="border: 0px" value=""><br>
				5.	Block End Date : <input
				type="text" readonly="readonly" id="blockEndDate" size="30"
				style="border: 0px" value=""><br>
				6.	Leave Type : <input
				type="text" readonly="readonly" id="leaveType" size="30"
				style="border: 0px" value=""><br>
 				7.	Number of Days : <input
				type="text" readonly="readonly" id="numberOfDays" size="30"
				style="border: 0px" value=""><br>
				  
				 
				<div class="row" >
								<div class="col-xl-12">
									<div class="form-group Design-table">
										<label class="form-label" for="phone-no-1"> Dependent
											List </label>
										<div id="person_allow">
											<table id="dependentTable" class="table table-striped">
												<thead class="thead-dark">
													<tr style='border: 1px solid black'>
														 
 														<th style="color: green">Dependent Name</th>
														<th style="color: green">Relation</th>
														<th style="color: green">DOB</th>
 														<th style="color: green">Annual Income</th>
														<th style="color: green">Occupation</th>
													</tr>
												</thead>
												<tbody id="SurInternalAdminModalData"></tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
 				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-defult"
						data-bs-dismiss="modal">Close</button>
 				</div>
			</div>
		</div>
	</div>
</body>
</html>