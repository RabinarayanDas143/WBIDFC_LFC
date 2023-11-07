<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script type="text/javascript" src="static/js/hrms/lfcDetail.js"></script>
<style>
#help {
	background-color: grey;
	width: 300px;
	height: 300px;
	display: none;
	position: fixed;
	top: 50%;
	left: 50%;
	/* margin: -150px 0 0 -150px; */
}

table, th, td {
	border: 1px solid black
}

.popup-overlay {
	position: fixed;
	top: 0px;
	left: 0px;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.5);
	display: none;
}

.popup {
	position: fixed;
	top: -150px;
	left: 50px;
	transform: translate(-50%, -50%);
	width: 400px;
	background: #f5f5f5;
	font-family: "Raleway";
	padding: 20px
}

.popup .popup.close-btn {
	position: absolute;
	right: 10px;
	top: 10px;
	font-size: 20px;
	background: #555;
	padding: 10px;
	border-radius: 10px;
	color: #eee;
	font-weight: 600;
}

.popup.active .overlay {
	top: 50%;
}

.popup.active .content {
	display: block;
}

input[type='radio'] {
	width: 25px;
	height: 25px;
}

.form-control {
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
  width: 80%;
}

.form-control:hover {
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}

.Design-table{
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  transition: 0.3s;
  width: 90%;
}

.Design-table:hover {
  box-shadow: 0 9px 16px 0 rgba(0,0,0,0.2);
}

</style>

<script type="text/javascript">
	$(document).ready(function() {

		$('#LT').hide();
		$('#frdt').hide();
		$('#todt').hide();
		$('#encashmentleave_type').hide();
		$('#encashment_leave_fromdate').hide();
		$('#encashment_leave_todate').hide();
		$('#leave_balance').hide();
		$('#leave_count').hide();

		$('#LA').on('change', function() {
			var opt = (this.value);
			console.log(opt);
			if (this.value == 'Yes') {
				$("#LT").toggle();
				$('#frdt').toggle();
				$('#todt').toggle();
			} else {
				$("#LT").hide();
				$('#frdt').hide();
				$('#todt').hide();
			}

		})

		$('#pl_encashment').on('change', function() {
			var fieldvalue = (this.value);
			if (this.value == 'Yes') {
				$('#encashmentleave_type').show();
				$('#encashment_leave_fromdate').show();
				$('#encashment_leave_todate').show();
				$('#leave_balance').show();
				$('#leave_count').show();
			} else {
				$('#encashmentleave_type').hide();
				$('#encashment_leave_fromdate').hide();
				$('#encashment_leave_todate').hide();
				$('#leave_balance').hide();
				$('#leave_count').hide();
			}
		})

		var year = (new Date).getFullYear();
		var y = year - 2;
		$('#frmdatepicker').datepicker({
			format : 'dd-mm-yyyy',
			startDate : new Date(y, 0, 1),
			endDate : '+3M',

		});

		$('#todatepic').datepicker({
			format : 'dd-mm-yyyy',
			changeMonth : true,
			changeYear : true,

		});

		$('#fodatepic').datepicker({

			format : 'dd-mm-yyyy',
			changeMonth : true,
			changeYear : true,
		});

		$('#travelfromdate').datepicker({
			format : 'dd-mm-yyyy',
			changeMonth : true,
			changeYear : true,

		});

		$('#traveltodate').datepicker({
			format : 'dd-mm-yyyy',
			changeMonth : true,
			changeYear : true,

		});

		$('#help_button').click(function() {
			$('#help').slideToggle(1000, function() {
				if ($('#help_button').val() == "close") {
					$('#help_button').val("show table");
				} else {
					$('#help_button').val("close");
				}
			})
		})
		/////////////////////////////////////////////////////////////////////////////////		
		var usererror = false;
		function validatetext() {
			var fieldvalue = $('#destinplace').val();
			var exp = /[A-Za-z]/
			if (fieldvalue == '') {
				$('#usererror').show();
				$('#usererror').html("required");
				usererror = false;
			} else if (!exp.test(fieldvalue)) {
				$('#usererror').show();
				$('#usererror').html("Not allowed");
				usererror = false;
			} else {
				$('#usererror').hide();
				usererror = true;
			}
		}

		$('#destinplace').keyup(function() {
			validatetext();
		})
		//////////////////////////////////////////////////////////////////////////////////
		var openPopupBtn = document.querySelector("#open-popup-btn");
		var closePopupBtn = document.querySelector('.popup-close-btn');
		openPopupBtn.addEventListener("click", function() {
			document.body.classList.add("popup-active");
		})

	});//document ready end here

	/* function myFunction() {
		debugger;
		var frmdate = $('#frmdatepicker').val();
		console.log(frmdate);

		var year = frmdate.substr(6, 9);//-4
		console.log(year);
		var expect = frmdate.substr(0, 6);
		console.log(expect);

		var num1 = parseInt(year);
		console.log(num1);

		var bloclApply = $('#BA').val(); //5
		var num2 = parseInt(bloclApply);
		console.log(num2);
		var num3 = num1 + num2;
		console.log(num3);
		var result = expect + num3;

		$('#todatepicker').val(result);

	} */

	function togglePopup() {
		document.getElementById("popup-1").classList.toggle("active");

	}

	function surrenderDataShow() {
		$('#isleaveApplied').hide();
		$('#placeofDestination').hide();
		$('#amountofAdvance').hide();
		$('#leaveencashment_block').hide();
		$('#number_days').hide();
		$('#leave_available').hide();
		$('#travel_fromdate').hide();
		$('#travel_todate').hide();
		$('#LT').hide();
		$('#frdt').hide();
		$('#todt').hide();

		//$('#surrender').val()
	}

	function lfcDataShow() {
		$('#isleaveApplied').show();
		$('#placeofDestination').show();
		$('#travel_fromdate').show();
		$('#travel_todate').show();
		$('#amountofAdvance').show();
		$('#leaveencashment_block').show();
		$('#number_days').show();
		$('#leave_available').show();
	}
</script>
</head>
<body>
	<div class="container">
		<div class="nk-block nk-block-lg">
			<div class="nk-block-head">
				<div class="nk-block-head-content"></div>
			</div>

			 <div class="card"> 
				<div class="card-header bg-secondary text-white text-center">
					<h5>Employee Details</h5>
				</div>
				<div class="card-inner" style="">

					<form action="#">
						<div class="row g-6">
							<div class="card-head">
								<h5 class="h5">Basic Details</h5>
							</div>

							<div class="row" style="padding-bottom: 30px">
								<div class="col-xl-4">
									<div class="alert alert-primary" role="alert">
										<label class="form-label" for="phone-no-1"> Employee
											Code</label>
										<div class="form-control-wrap">
											<input type="number" class="form-control required"
												placeholder="Employee code" id="empcd" name="empcode"
												value="<%-- <%=a%> --%>" readonly>
										</div>
									</div>
								</div>

								<div class="col-xl-4">
									<div class="alert alert-primary" role="alert">
										<label class="form-label" for="full-name-1"> Employee
											Name</label>
										<div class="form-control-wrap">
											<input type="text" class="form-control required"
												placeholder="Employee name" id="emply_name" name="empname"
												value="" readonly>
										</div>
									</div>
								</div>

								<div class="col-xl-4">
									<div class="alert alert-primary" role="alert">
										<label class="form-label" for="full-name-1">
											Designation</label>
										<div class="form-control-wrap">
											<input type="text" class="form-control required"
												placeholder="Designation" id="emply_desig" value="" readonly>
										</div>
									</div>
								</div>
							</div>
							<br>


							<div class="row" style="padding: 20px;">
								<div class="col-xl-6" align="centre">
									<div class="form-group, alert alert-primary">
										<input type='radio' name='group' ng-model='mValue' 
											onclick="lfcDataShow()" id="encashmentApply"
											style="height: 25px; width: 25px; vertical-align: middle;"
											checked /> <label class="form-label" for="full-name-1">
											LFC cum Encashment</label>
									</div>
								</div>


								<div class="col-xl-6">
									<div class="form-group, alert alert-primary">
										<input type='radio' name='group' ng-model='mValue'
											onclick="surrenderDataShow()" id="encashmentSurrender"
											style="height: 25px; width: 25px; vertical-align: middle;" />
										<label align="centre" class="form-label" for="full-name-1">
											LFC Surrender/Encashment cum leave encashment</label>
									</div>
								</div>
							</div>

							<div class="row" style="padding: 10px">
								<div class="col-xl-4 ">
									<div class="form-group">
										<label class="form-label" for="full-name-1">Block
											Period</label>
										<div class="form-control-wrap">
                                            
											<div class="form-control-wrap ">
												<input type="text" class="form-control required" id="BA"
													placeholder="Block applied" name="BA" readonly>
											</div>
											 
										</div>
									</div>
								</div>

								<div class="col-xl-4">
									<div class="form-group">
										<label class="form-label" for="full-name-1">Block
											Start Date</label>
										<div class="form-control-wrap">

											<input type="text" class="form-control required"
												name="frmdatepicker" id="frmdatepicker"
												placeholder="Block Start Date" onchange="dateChangeFunction()">
										</div>
									</div>
								</div>

								<div class="col-xl-4">
									<div class="form-group">
										<label class="form-label" for="full-name-1">Block End
											Date</label>
										<div class="form-control-wrap">
											<input type="text" class="form-control required"
												placeholder="Block End Date" id="todatepicker" value=""
												readonly>
										</div>
									</div>
								</div>

							</div>



							<div class="row" style="padding: 10px">
								<div class="col-lg-4" id="isleaveApplied">
									<div class="form-group">
										<label class="form-label" for="full-name-1">Is Leave
											Applied</label>
										<div class="form-control-wrap">
											<select name="LA" class="form-control required" id="LA">
												<option selected="selected" value="1">---Select---</option>
												<option value="Yes">Yes</option>
												<option value="No">No</option>
											</select>
										</div>
									</div>
								</div>
							</div>

							<div class="row" style="padding: 10px">
								<div class="col-xl-4" id="LT" name="LT">
									<div class="form-group">
										<label class="form-label" for="full-name-1">Leave Type</label>
										<div class="form-control-wrap">
											<select class="form-control required" id="leave_type"
												onchange="leaveTypeFunction()">
											</select>
										</div>
									</div>
								</div>

								<div class="col-xl-4" id="frdt">
									<div class="form-group">
										<label class="form-label" for="full-name-1">From Date</label>
										<div class="form-control-wrap">

											<input type="text" class="form-control required"
												name="fodatepic" id="fodatepic" placeholder="From Date">
										</div>
									</div>
								</div>

								<div class="col-xl-4" id="todt">
									<div class="form-group">
										<label class="form-label" for="full-name-1">To Date</label>
										<div class="form-control-wrap">
											<input type="text" class="form-control required"
												id="todatepic" value="" readonly onchange="dateDiffCount()" placeholder="To Date">
										</div>
									</div>
								</div>

							</div>

							<div class="row" style="padding: 10px">
								<div class="col-xl-4" id="placeofDestination">
									<div class="form-group">
										<label class="form-label" for="email-address-1"> Place
											of Destination</label>
										<div class="form-control-wrap">
											<input type="text" maxlength="30"
												class="form-control required" id="destinplace"
												placeholder="Place of Destination"
												onkeypress="validatetext()"> <span id="usererror"
												style.display="none"></span>
										</div>
									</div>
								</div>

								<div class="col-xl-4" id="travel_fromdate">
									<div class="form-group">
										<label class="form-label" for="full-name-1">Date of
											Commencement </label>
										<div class="form-control-wrap">

											<input type="text" class="form-control required"
												name="fromdate" id="travelfromdate" placeholder="Date of Commencement">
										</div>
									</div>
								</div>

								<div class="col-xl-4" id="travel_todate">
									<div class="form-group">
										<label class="form-label" for="full-name-1">Date of
											Completion</label>
										<div class="form-control-wrap">
											<input type="text" class="form-control required"
												id="traveltodate" value="" readonly placeholder="Date of Completion">
										</div>
									</div>
								</div>
							</div>
							<!-- onchange="encashmentLeavedateDiffCount()" -->
							<div class="row" style="padding: 10px">
								<div class="col-xl-4" id="amountofAdvance">
									<div class="form-group">
										<label class="form-label" for="phone-no-1"> Amount of
											Advance</label>
										<div class="form-control-wrap">
											<input type="number" class="form-control required"
												id="amount_advance" placeholder="Amount of advance"
												onKeyPress="if(this.value.length==6) return false">
										</div>
									</div>
								</div>


								<div class="col-xl-4" id="leaveencashment_block">
									<div class="form-group">
										<label class="form-label" for="full-name-1">Leave
											Encashment Block</label>
										<div class="form-control-wrap">
											<input type="text" id="leaveEncashBlock"
												placeholder="Leave Encashment Block" class="form-control" />

										</div>
									</div>
								</div>
								<div class="col-xl-4" id="number_days">
									<div class="form-group">
										<label class="form-label" for="phone-no-1"> Number of
											Days</label>
										<div class="form-control-wrap">
											<input type="number" class="form-control required" id="NOD"
												placeholder="Number of Days"
												onKeyPress="if(this.value.length==2) return false">
										</div>
									</div>
								</div>
							</div>
							<div class="row" style="padding: 10px">
								

								<div class="col-xl-4" id="leave_available">
									<div class="form-group">
										<label class="form-label" for="phone-no-1"> Leaves
											Available</label>
										<div class="form-control-wrap">
											<input type="text" class="form-control required"
												placeholder="Leave balance" id="count" readonly>
										</div>
									</div>
								</div>
								<div class="col-lg-4">
									<div class="form-group">
										<label class="form-label" for="full-name-1">Leave
											Encashment Needed</label>
										<div class="form-control-wrap">
											<select class="form-control required" id="pl_encashment">
												<option selected="selected" value="1">---Select---</option>
												<option value="Yes">Yes</option>
												<option value="No">No</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							<!-- <div class="row" style="padding: 10px">
								
							</div> -->
							<div class="row" style="padding: 10px">
								<div class="col-xl-4" id="encashmentleave_type">
									<div class="form-group">
										<label class="form-label" for="phone-no-1"> Leaves
											Type</label>
										<div class="form-control-wrap">
											<select class="form-control required" id="EncasmentleaveType"
												onchange="encashmentLeaveTypeFunction()">
											</select>
										</div>
									</div>
								</div>
								<div class="col-xl-4" id="leave_balance">
									<div class="form-group">
										<label class="form-label" for="phone-no-1"> Leaves
											Balance</label>
										<div class="form-control-wrap">
											<input type="text" class="form-control required"
												placeholder="Leave balance" id="Encasmentleavebalance"
												readonly>
										</div>
									</div>
								</div>
								<div class="col-xl-4" id="leave_count">
									<div class="form-group">
										<label class="form-label" for="phone-no-1"> Encashment
											number of Days</label>
										<div class="form-control-wrap">
											<input type="text" class="form-control required"
												placeholder="Number of Days" id="Encasmentleavecount"
												onchange="checknumberofDays()">
										</div>
									</div>
								</div>
							</div>
							<div class="row" style="padding: 10px">
								<div class="col-xl-12">
									<div>
										<br> <input type="button" id="distancepermis"
											class="btn btn-lg btn-info" value="Distance Permisable">
									</div>
								</div>
							</div> 
							<div class="row" style="padding-left :100px">
								<div class="col-xl-12">
									<div class="form-group Design-table">
										<label class="form-label" for="phone-no-1"> Dependent
											List </label>
										<div id="person_allow">
											<table id="dependentTable" class="table table-striped">
												<thead class="thead-dark">
													<tr style='border: 1px solid black'>
														<th><input type="checkbox" class="selectall"
															id="checkerAll" style='border: 1px solid black'></th>
														<th style="color: green">Sr.No</th>
														<th style="color: green">Dependent Name</th>
														<th style="color: green">Relation</th>
														<th style="color: green">DOB</th>
														<th style="color: green">Gender</th>
														<th style="color: green">Annual Income</th>
														<th style="color: green">Occupation</th>
													</tr>
												</thead>
												<tbody id="dependentTableBody"></tbody>
											</table>
										</div>
									</div>
								</div>
							</div>

							<div align="center">
								<div class="form-group">

									<br> <br> <input type="button" id="empDetailSubmit"
										class="btn btn-lg btn-primary" onclick="saveinfo()"
										value="Save Informations">
								</div>
							</div>

						</div>
					</form>
				</div>
			 </div> 
		</div>
	</div>
	</div>
</body>
<!-- content @e -->
</html>

<div class="modal fade" id="modalWindow" role="dialog">
	<div class="modal-dialog modal-lg vertical-align-center">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-bs-dismiss="modal">&times;</button>
				<h4 class="modal-title">Distance Permissible</h4>
			</div>
			<div class="modal-body">
				<p>For availment of Leave Fare Concession, the maximum
					permissible distance for reimbursement and mode & class by which
					the employee and the members of his family would be entitled shall
					be as given under:</p>
				<table>
					<thead>
						<th>Sr.No</th>
						<th>Employee Type</th>
						<th>Maximum Permissible Distance</th>
						<th>Entitlement Mode & Class of Travel</th>
					</thead>
					<tbody class="table">
						<tr>
							<td>1</td>
							<td>Managing Director</td>
							<td>Any Place in India</td>
							<td>By Air-Executive Class (Normally by Executive Class.
								However class of Air travel is to be determined by guidelines
								issued by the Govt. of West Bengal from time to time.) By Train:
								AC 1st Class By Ship / Steamer: Deluxe Cabin</td>
						<tr>
						<tr>
							<td>2</td>
							<td>CFO, Company Secretary and Head of the Departments.</td>
							<td>Any Place in India</td>
							<td>By Air – Economy Class By Train – AC 1st Class By
								Steamer – Deluxe Cabin</td>
						<tr>
						<tr>
							<td>3</td>
							<td>Officer having completed 15 years of service and have
								additional statutory responsibility like Internal Auditor, CRO,
								CISO, etc.</td>
							<td>Any Place in India</td>
							<td>By Train – AC 1st Class By Steamer – Deluxe Cabin</td>
						<tr>
						<tr>
							<td>4</td>
							<td>Other Officers</td>
							<td>3000 Kms</td>
							<td>By Train – AC 2 Tier By Steamer – 1 st Class Cabin</td>
						<tr>
						<tr>
							<td>5</td>
							<td>Clerk</td>
							<td>2700 Kms</td>
							<td>By Train – AC 2 Tier By Steamer – 1 st Class Cabin</td>
						<tr>
						<tr>
							<td>6</td>
							<td>Subordinate Staff</td>
							<td>2200 Kms</td>
							<td>By Train - AC 3 Tier By Steamer – II Class Cabin</td>
						<tr>
					</tbody>
				</table>
				<p>However if the declared place of visit is not already
					connected by entitled mode of travel (air or railway or steamer)
					and provided that distance does not exceed 300 kms from the nearest
					airport/railway station/port, the employee may travel to the
					declared destination by road/other means but the maximum
					reimbursement per member shall be restricted to peak time train
					fare of the actual distance or 300 kms whichever is less as per
					entitlement given under:</p>

				<table>
					<thead>
						<th>Sr.No</th>
						<th>Employee Type</th>
						<th>Entitlement Class of Train fare for reimbursement if the
							place of visit is not connected by air/rail/steamer</th>
					</thead>
					<tbody class="table">
						<tr>
							<td>1</td>
							<td>Managing Director</td>
							<td>Actual peak time railway fare for AC 1 st class for
								Rajdhani</td>
						<tr>
						<tr>
							<td>2</td>
							<td>CFO, Company Secretary and Head of the Departments.</td>
							<td>Actual peak time railway fare for AC 1 st class for
								mail/express train</td>
						<tr>
						<tr>
							<td>3</td>
							<td>Officer having completed 15 years of service and have
								additional statutory responsibility like Internal Auditor, CRO,
								CISO, etc.</td>
							<td>Actual peak time railway fare for AC 1 st class for
								mail/express train.</td>
						<tr>
						<tr>
							<td>4</td>
							<td>Other Officers</td>
							<td>Actual peak time railway fare for AC2 Tier for
								mail/express train</td>
						<tr>
						<tr>
							<td>5</td>
							<td>Clerk</td>
							<td>Actual peak time railway fare for AC2 Tier for
								mail/express train.</td>
						<tr>
						<tr>
							<td>6</td>
							<td>Subordinate Staff</td>
							<td>Actual railway fare for AC-3 Tier for mail/express
								train.</td>
						<tr>
					</tbody>
				</table>
			</div>
 			<div class="modal-footer">
				<button type="button" data-bs-dismiss="modal"
					class="btn btn-default">Close</button>
			</div>
		</div>
	</div>
</div>
