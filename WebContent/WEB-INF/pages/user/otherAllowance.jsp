<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="static/js/hrms/festival.js?v="+ Date.now() + Math.random()"></script> 
<script type="text/javascript" src="static/js/hrms/otherAllowance.js?v="+ Date.now() + Math.random()"></script>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h3 align="center">
				Other Allowances		
			</h3>
			<!-- <ol class="breadcrumb">
				<li><a href="#"> Home</a></li>
				<li class="active">Telephone Allowance</li>
			</ol> -->
		</section>

	<!-- Main content -->
	<section class="content">  
	    <div class="row">
			<div class="col-xs-12">
	         	<div class="box box-solid box-primary">
                  <div class="box-header">
                      <h3 class="box-title">Employee Details</h3>
                      <div class="box-tools pull-right">
                          <button style="text-align: right;" class="btn btn-primary btn-sm" data-widget="collapse"><i class="fa fa-minus"></i></button>
                      </div>
                  </div>
                  <div class="box-body" style="display: block;">
                      <table class="table table-bordered table-striped table-hover " cellpadding="0" cellspacing="0" width="100%">

                          <tbody><tr class="bg">
                              <td class="first style2" width="22%">Employee Code&nbsp;:</td>
                              <td align="left" width="28%">&nbsp;
                                   ${loginBean.emply_cd}
                              </td>
                              <td class="first style2" width="20%">Employee Name&nbsp;:</td>
                              <td align="left" width="30%">&nbsp;
                                  ${empdetails[1]}
                              </td>
                          </tr>
                          <tr class="bg">
                              <td class="first style2">Cadre&nbsp;:</td>
                              <td align="left">&nbsp;
                                   ${empdetails[4]}
                              </td>
                              <td class="first style2">DOJ&nbsp;:</td>
                              <td align="left">&nbsp;
                                   ${empdetails[7]}
                              </td>
                          </tr>
                          <tr class="bg">
                              <td class="first style2">Department&nbsp;:</td>
                              <td align="left">&nbsp;
                                  ${empdetails[5]}
                              </td>
                              <td class="first style2">Branch&nbsp;:</td>
                              <td align="left">&nbsp;
                                  ${empdetails[12]}
                              </td>
                          </tr>
                      </tbody></table>
                  </div><!-- /.box-body -->
                </div>
	 		</div>
	  	</div>
 		
					<div class="row">
						<div class="col-md-12 col-lg-12 col-xl-12">
							<div class="card">
								<div class="card-body">
 									<br>
 									
 									
 									<div class="col-xl-4">
											<div class="general-label">
												<div class="form-horizontal">
													<input type="hidden" id="id"> <label
														class="col-sm-12 control-label p-0"
														for="example-input-normal">Other Allowances</label>
													<div class="form-group row">
														<div class="col-md-12">
															<select class="form-control" id="other_allowances"  >
																<option value ="" disabled selected>--Select Action--</option>
																<option value="eye_check-up_allowance">Eye Check-up Allowance</option>health_check-up_fields
																<option value="health_check-up_allowance">Health Check-up</option>
																<option value="mobile_reimbursement">Mobile Reimbursement</option>
																<option value="visiting_cards_allowance">Visiting Cards Allowance</option>
																<option value="brief_case/handbag_allowance">Brief Case/Handbag Allowance</option>
																<option value="uniform_allowance">Uniform Allowance</option>
															</select>
															
														</div>

													</div>
												</div>
											</div>
										</div>
										
									</div>
 									
<!--  -----------------------------------------------------------------------------------------------------------------> 
									<br>
									<div id="eye_checkup_fields" style="display: none">
 										<div class="row">
 											<div class="col-xl-1">
 											</div>
 											<div class="col-xl-2">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Allowance Name</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id= "eye_check-up_name"
																	name="example-input1-group1" class="form-control" 
																	value= "Eye check up" readonly="readonly">
															</div>
	
														</div>
													</div>
												</div>
											</div>
											<div class="col-xl-2">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Date of Check up</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id="eye_check_up_date"
																	name="example-input1-group1" class="form-control"
																	placeholder="Check up Date">
															</div>
	
														</div>
													</div>
												</div>
											</div>
											
											<div class="col-xl-2">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Bill Amount</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id="eye_check_up_bill_amount"
																	name="example-input1-group1" class="form-control"
																	placeholder="Bill Amount"
																	onkeypress="return isNumberKeyWithOutDecimal(event)">
															</div>
	
														</div>
													</div>
												</div>
											</div>
											
											<div class="col-xl-2">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Receipt upload</label>
														<div class="form-group row">
															<div class="col-md-12">
															
																<input type="file" id="eye_check_up_receipt" multiple="multiple"
																	 class="form-control" placeholder="Receipt upload">
															</div>
														</div>
													</div>
												</div>
											</div>
											
										</div>
	 									<br>
										
										<div class="row">
											<div class="col-md-12">
												<div class="box box-primary">                 
													<div class="box-body" id="subcat">
													</div>					
													<div class="box-footer" style="text-align: center;">
														<a class="btn btn-primary"  onclick="ADDEyeCheckUpAllowance();">Add Details</a>					
													</div>
												</div>
											</div>
										</div>
										
										<div id="eye_checkup_processTbl" style="display: none">
											<div style="overflow: auto;">
											<input style="display: none" type="text" id="eyeCheckup_max_eligible_amt"  value ='${eyeCheckupLimit}'>
											<table class="table table-bordered table-striped infosec"> 
											<thead style="font-weight: bold">
												<tr>
													<td>Allowance Applied For</td>
													<td>Maximum Eligible Amount</td> 
													<td>Amount applied</td>
												</tr>
												
											</thead>
											<tbody id="eye_checkup_infosectbl"></tbody>
											
											</table>
											</div>
											<br>
											<div class="box-footer" style="text-align: center;">
												<a class="btn btn-primary" onclick="submitEyeCheckUpAllowance();">Submit Allowance</a>
												
											</div>
												
									 	</div>
										
 									</div>
		
 									
 <!-- -------------------------------------------------------------------------------------------------------------------------- -->
									<br>
									<div id="health_check-up_fields" style="display: none">
 										<div class="row">
 											<div class="col-xl-1">
 											</div>
 											<div class="col-xl-2">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Allowance Name</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id= "health_check-up_name"
																	name="example-input1-group1" class="form-control" 
																	value= "Health check up" readonly="readonly">
															</div>
	
														</div>
													</div>
												</div>
											</div>
											
											<div class="col-xl-2">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Applied for</label>
														<div class="form-group row">
															<div class="col-md-12">
																<select class="form-control" id="health_applied_for"  >
																	<option value ="" disabled selected>--Select action--</option>
																	<option value="Self">Self</option>
																	<option value="Spouse">Spouse</option>
																</select>
															</div>
														</div>
													</div>
												</div>
											</div>
											
											<div class="col-xl-2">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Date of Check up</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id="health_check_up_date"
																	name="example-input1-group1" class="form-control"
																	placeholder="Check up Date">
															</div>
														</div>
													</div>
												</div>
											</div>
											
											<div class="col-xl-2">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Bill Amount</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id="health_check_up_bill_amount"
																	name="example-input1-group1" class="form-control"
																	placeholder="Bill Amount"
																	onkeypress="return isNumberKeyWithOutDecimal(event)">
															</div>
	
														</div>
													</div>
												</div>
											</div>
											
											<div class="col-xl-2">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Receipt upload</label>
														<div class="form-group row">
															<div class="col-md-12">
															
																<input type="file" id="health_check_up_receipt" multiple="multiple"
																	 class="form-control" placeholder="Receipt upload">
															</div>
														</div>
													</div>
												</div>
											</div>
											
										</div>
	 									<br>
										
										<div class="row">
											<div class="col-md-12">
												<div class="box box-primary">                 
													<div class="box-body" id="subcat">
													</div>					
													<div class="box-footer" style="text-align: center;">
														<a class="btn btn-primary"  onclick="ADDHealthCheckUpAllowance();">Add Details</a>					
													</div>
												</div>
											</div>
										</div>
										
										<div id="eye_checkup_processTbl" style="display: none">
											<div style="overflow: auto;">
											<input style="display: none" type="text" id="healthCheckup_max_eligible_amt"  value ='${healthCheckupLimit}'>
											<table class="table table-bordered table-striped infosec"> 
											<thead style="font-weight: bold">
												<tr>
													<td>Allowance Applied For</td>
													<td>Maximum Eligible Amount</td> 
													<td>Amount applied</td>
												</tr>
												
											</thead>
											<tbody id="eye_checkup_infosectbl"></tbody>
											
											</table>
											</div>
											<br>
											<div class="box-footer" style="text-align: center;">
												<a class="btn btn-primary" onclick="submitHealthCheckUpAllowance();">Submit Allowance</a>
												
											</div>
												
									 	</div>
										
 									</div>
		
 									
 <!-- -------------------------------------------------------------------------------------------------------------------------- -->
									<br>
									<div id="mobile_fields" style="display: none">
 										<div class="row">
 											<div class="col-xl-1">
 											</div>
 											<div class="col-xl-2">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Allowance Name</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id= "tuition_fee_reimbursement_name"
																	name="example-input1-group1" class="form-control" 
																	value= "Eye check up" readonly="readonly">
															</div>
	
														</div>
													</div>
												</div>
											</div>
											<div class="col-xl-2">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Date of Check up</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="date" id="eye_check_up_date"
																	name="example-input1-group1" class="form-control"
																	placeholder="Check up Date">
															</div>
	
														</div>
													</div>
												</div>
											</div>
											
											<div class="col-xl-2">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Bill Amount</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id="eye_check_up_bill_amount"
																	name="example-input1-group1" class="form-control"
																	placeholder="Bill Amount"
																	onkeypress="return isNumberKeyWithOutDecimal(event)">
															</div>
	
														</div>
													</div>
												</div>
											</div>
											
											<div class="col-xl-2">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Receipt upload</label>
														<div class="form-group row">
															<div class="col-md-12">
															
																<input type="file" id="eye_check_up_receipt" multiple="multiple"
																	 class="form-control" placeholder="Receipt upload">
															</div>
														</div>
													</div>
												</div>
											</div>
											
										</div>
	 									<br>
										
										<div class="row">
											<div class="col-md-12">
												<div class="box box-primary">                 
													<div class="box-body" id="subcat">
													</div>					
													<div class="box-footer" style="text-align: center;">
														<a class="btn btn-primary"  onclick="ADDEyeCheckUpAllowance();">Add Details</a>					
													</div>
												</div>
											</div>
										</div>
										
										<div id="eye_checkup_processTbl" style="display: none">
											<div style="overflow: auto;">
											<input style="display: none" type="text" id="eyeCheckup_max_eligible_amt"  value ='${eyeCheckupLimit}'>
											<table class="table table-bordered table-striped infosec"> 
											<thead style="font-weight: bold">
												<tr>
													<td>Allowance Applied For</td>
													<td>Maximum Eligible Amount</td> 
													<td>Amount applied</td>
												</tr>
												
											</thead>
											<tbody id="eye_checkup_infosectbl"></tbody>
											
											</table>
											</div>
											<br>
											<div class="box-footer" style="text-align: center;">
												<a class="btn btn-primary" onclick="submitEyeCheckUpAllowance();">Submit Allowance</a>
												
											</div>
												
									 	</div>
										
 									</div>
		
 									
 <!-- -------------------------------------------------------------------------------------------------------------------------- -->
									

	