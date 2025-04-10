<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="static/js/hrms/festival.js?v="+ Date.now() + Math.random()"></script> 
<script type="text/javascript" src="static/js/hrms/yearlyAllowance.js?v="+ Date.now() + Math.random()"></script>   
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
				Yearly Allowances		
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
														for="example-input-normal">Yearly Allowances</label>
													<div class="form-group row">
														<div class="col-md-12">
															<select class="form-control" id="yearly_allowances"  >
																<option value ="" disabled selected>--Select Action--</option>
																<option value="medical_allowance">Medical Allowance</option>
																<option value="festival_advance">Festival Advance</option>
																<option value="annual_pl_encashment">Annual PL Encashment</option>
																<option value="tuition_fee_reimbursement">Tuition Fee Reimbursement</option>
															</select>
															
														</div>

													</div>
												</div>
											</div>
										</div>
										
									</div>
 									
<!--  -----------------------------------------------------------------------------------------------------------------> 									
 									<div  id="medical_allowance_fields" style="display: none">
	 									<div class="row">
	 									
	 										<div class="col-xl-3">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Allowance Name</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id= "medical_Allowance_Name"
																	name="example-input1-group1" class="form-control" 
																	value= "Medical Allowance" readonly="readonly">
															</div>
	
														</div>
													</div>
												</div>
											</div>
											<div class="col-xl-3">
												<div class="general-label">
													<div class="form-horizontal" >
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Year</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text"  id="medical_calender_year"
																	name="example-input1-group1" class="form-control"
																	placeholder="Year">
															</div>
														</div>
													</div>
												</div>
											</div>
											
											<div class="col-xl-3">
												<div class="general-label" >
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Amount Applied</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id="medical_amount_applied" 
																	name="example-input1-group1" class="form-control"
																	placeholder="Amount">
															</div>
														</div>
													</div>
												</div>
											</div>
											
											<div class="col-xl-3">
												<div class="general-label" >
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Nature of illness</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id="medical_illness" 
																	name="example-input1-group1" class="form-control"
																	placeholder="Nature of illness">
															</div>
														</div>
													</div>
												</div>
											</div>
											
										</div>
 										<br>
 										<div class="row">
											<!-- left column -->
											<div class="col-md-12">
												<!-- general form elements -->
												<div class="box box-primary">                 
													<div class="box-body" id="subcat">
													</div>					
													<div class="box-footer" style="text-align: center;">
														<a class="btn btn-primary"  onclick="ADDdetailsMedicalAllowance();">Add Details</a>					
													</div>
												</div>
											</div>
										</div>
										<br>
										<div id="medical_processTbl" style="display: none">
											<div style="overflow: auto;">
											<input style="display: none" type="text" id="medical_max_eligible_amount"  value = '${medLimitValue}'>
											<table class="table table-bordered table-striped infosec"> 
											<thead style="font-weight: bold">
												<tr>
													<td>Allowance Applied For</td>
													<td>Period Applied For</td>
													<td>Maximum Eligible Amount</td>
													<td>Amount applied</td>
												</tr>
												
											</thead>
											<tbody id="medical_infosectbl"></tbody>
											
											</table>
											</div>
											<br>
											<div class="box-footer" style="text-align: center;">
												<a class="btn btn-primary" onclick="submitMedicalAllowance();">Submit Allowance</a>
												
											</div>
												
											
									 	</div>
 									</div>
 	<!-- -------------------------------------------------------------------------------------------- -->		
 									<div id="festival_advance_fields" style="display: none">
	 									<div class="row">
	 										<div class="col-xl-2">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Allowance Name</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id= "medical_Allowance_Name"
																	name="example-input1-group1" class="form-control" 
																	value= "Festival Allowance" readonly="readonly">
															</div>
	
														</div>
													</div>
												</div>
											</div>
											<div class="col-xl-2">
												<div class="general-label">
													<div class="form-horizontal" >
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Year</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text"  id="festival_calender_year" readonly="readonly"
																	name="example-input1-group1" class="form-control"
																	>
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
															for="example-input-normal">Festival type</label>
														<div class="form-group row">
															<div class="col-md-12">
																<select class="form-control" id="festival_type"  >
																	<option value ="" disabled selected>--Select action--</option>
																	<option value="Religious">Religious</option>
																	<option value="Local">Local</option>
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
															for="example-input-normal">Festival Name</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id="festival_name"
																	name="example-input1-group1" class="form-control"
																	placeholder="Festival Name">
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
															for="example-input-normal">Amount Applied</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id="festival_amount_applied"
																	name="example-input1-group1" class="form-control"
																	placeholder="no_of_days"
																	onkeypress="return isNumberKeyWithOutDecimal(event)">
															</div>
	
														</div>
													</div>
												</div>
											</div>
											
										</div>
										<br>
										<div class="row">
											<!-- left column -->
											<div class="col-md-12">
												<!-- general form elements -->
												<div class="box box-primary">                 
													<div class="box-body" id="subcat">
													</div>					
													<div class="box-footer" style="text-align: center;">
														<a class="btn btn-primary"  onclick="ADDdetailsfestivalAllowance();">Add Details</a>					
													</div>
												</div>
											</div>
										</div>
										<br>
										<div id="festival_processTbl" style="display: none">
											<div style="overflow: auto;">
											<table class="table table-bordered table-striped infosec"> 
											<thead style="font-weight: bold">
												<tr>
													<td>Allowance Applied For</td>
													<td>Festival Applied For</td>
													<td>Maximum Eligible Amount</td>
													<td>Amount applied</td>
												</tr>
												
											</thead>
											<tbody id="festival_infosectbl"></tbody>
											
											</table>
											</div>
											<br>
											<div class="box-footer" style="text-align: center;">
												<a class="btn btn-primary" onclick="submitFestivalAllowance();">Submit Allowance</a>
												
											</div>
												
											<!-- <div align="center" id="Loader"
												 style="background-color: #ffffff; position: fixed; opacity: 0.7; z-index: 99999; height: 100%; width: 100%; left: 0px; top: 0px; display: none">
						
												<img style="margin-left: 20px; margin-top: 200px;"
													src="images/unnamed.gif" alt="loader">
						
											</div> -->
									 	</div>
										
									</div>
									
 									<br>
<!-- ------------------------------------------------------------------------------------------------------------------ -->
 									<div id="annual_pl_encashment_fields" style="display: none">
	 									<div class="row" >
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
																<input type="text" id= "pl_encash_Allowance_Name"
																	name="example-input1-group1" class="form-control" 
																	value= "Annual PL Encashment" readonly="readonly">
															</div>
	
														</div>
													</div>
												</div>
											</div>												
											<div class="col-xl-2">
												<div class="general-label">
													<div class="form-horizontal" >
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Year</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text"  id="pl_encash_calender_year"
																	name="example-input1-group1" class="form-control"
																	>
															</div>
														</div>
													</div>
												</div>
											</div>
											
											<div class="col-xl-2">
												<div class="general-label" >
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">No. of Eligible Days</label>
														<div class="form-group row">
															<div class="col-md-12">
																<select class="form-control" id="pl_encash_eligible_days"  >
																	<option value ="" disabled selected>--Select Eligible Days--</option>
																	<option value="5">5</option>
																	<option value="7">7</option>
																</select>
															</div>
														</div>
													</div>
												</div>
											</div>
											
											<div class="col-xl-2">
												<div class="general-label" >
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">No. of Days Applied</label>
														<div class="form-group row">
															<div class="col-md-12">
																<select class="form-control" id="pl_encash_days_applied"  >
																	<option value ="" disabled selected>--Select Days to Apply--</option>
																	<option value="1">1</option>
																	<option value="2">2</option>
																	<option value="3">3</option>
																	<option value="4">4</option>
																	<option value="5">5</option>
																	<option value="6">6</option>
																	<option value="7">7</option>
																	
																</select>
															</div>
														</div>
													</div>
												</div>
											</div>
											
										</div>
										<br>
										<div class="row" >
											<div class="col-xl-1">
											</div>
											
											<div class="col-xl-2">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Festival type</label>
														<div class="form-group row">
															<div class="col-md-12">
																<select class="form-control" id="pl_encash_festival_type"  >
																	<option value ="" disabled selected>--Select action--</option>
																	<option value="Religious">Religious</option>
																	<option value="Local">Local</option>
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
															for="example-input-normal">Festival Name</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id="pl_encash_festival_name"
																	name="example-input1-group1" class="form-control"
																	placeholder="Festival Name">
															</div>
	
														</div>
													</div>
												</div>
											</div>
											
											
											
										</div>
										
										<div class="row">
											<div class="col-md-12">
												<div class="box box-primary">                 
													<div class="box-body" id="subcat">
													</div>					
													<div class="box-footer" style="text-align: center;">
														<a class="btn btn-primary"  onclick="ADDdetailsPLEncashment();">Add Details</a>					
													</div>
												</div>
											</div>
										</div>
										
										<div id="annual_pl_processTbl" style="display: none">
											<div style="overflow: auto;">
											<table class="table table-bordered table-striped infosec"> 
											<thead style="font-weight: bold">
												<tr>
													<td>Allowance Applied For</td>
													<td>Year Applied For</td>
													<td>Festival Applied For</td>
													<td>No. of days Eligible</td>
													<td>No. of days Applied</td>
													<td>Amount Eligible for Sanction</td>
												</tr>
												
											</thead>
											<tbody id="annual_pl_infosectbl"></tbody>
											
											</table>
											</div>
											<br>
											<div class="box-footer" style="text-align: center;">
												<a class="btn btn-primary" onclick="submitAnnualPLAllowance();">Submit Allowance</a>
												
											</div>
												
									 	</div>
										
 									</div>
 									<br>
 <!-- ------------------------------------------------------------------------------------------------------------------ -->
 									<div id="tuition_fee_reimbursement_fields" style="display: none">
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
																	value= "Tuition Fee Reimbursement" readonly="readonly">
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
															for="example-input-normal">Financial Year</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id="tuition_fee_financial_year"
																	name="example-input1-group1" class="form-control"
																	placeholder="Financial Year">
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
															for="example-input-normal">For which Child applying</label>
														<div class="form-group row">
															<div class="col-md-12">
																<!-- <input type="text" id="child_name"
																	name="example-input1-group1" class="form-control"
																	placeholder="Child Name"> -->
																
																<select class="form-control" id="child_name" placeholder="--Select Child Name--">
																	<option value ="" disabled selected>--Select Child Name--</option>
																	<option value="${empDpndtNames[0]}">${empDpndtNames[0]}</option>
																	<option value="${empDpndtNames[1]}">${empDpndtNames[1]}</option>
																	<option value="${empDpndtNames[2]}">${empDpndtNames[2]}</option>
																	
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
															for="example-input-normal">Standard Studying</label>
														<div class="form-group row">
															<div class="col-md-12">
																<select class="form-control" id="standard"  >
																	<option value ="" disabled selected>--Select Standard--</option>
																	<option value="Nursery">Nursery</option>
																	<option value="Lower KG">Lower KG</option>
																	<option value="Upper KG">Upper KG</option>
																	<option value="Class I">Class I</option>
																	<option value="Class II">Class II</option>
																	<option value="Class III">Class III</option>
																	<option value="Class IV">Class IV</option>
																	<option value="Class V">Class V</option>
																	<option value="Class VI">Class VI</option>
																	<option value="Class VII">Class VII</option>
																	<option value="Class VIII">Class VIII</option>
																	<option value="Class IX">Class IX</option>
																	<option value="Class X">Class X</option>
																</select>
																
															</div>
	
														</div>
													</div>
												</div>
											</div>
											
										</div>
	 									<br>
	 									<div class="row">
	 										<div class="col-xl-1">
	 										</div>
											<div class="col-xl-2">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Amount Applied</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id="tuition_fee_amount_applied"
																	name="example-input1-group1" class="form-control"
																	placeholder="no_of_days"
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
															
																<input type="file" id="tuition_fee_receipt" multiple="multiple"
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
														<a class="btn btn-primary"  onclick="ADDTuitionFeeAllowance();">Add Details</a>					
													</div>
												</div>
											</div>
										</div>
										
										<div id="tuition_processTbl" style="display: none">
											<div style="overflow: auto;">
											<table class="table table-bordered table-striped infosec"> 
											<thead style="font-weight: bold">
												<tr>
													<td>Allowance Applied For</td>
													<td>Student Applied For</td> 
													<td>Standard applied for </td>
													<td>Maximum Eligible Amount</td>
													<td>Amount applied</td>
												</tr>
												
											</thead>
											<tbody id="tuition_infosectbl"></tbody>
											
											</table>
											</div>
											<br>
											<div class="box-footer" style="text-align: center;">
												<a class="btn btn-primary" onclick="submitTuitionFeeAllowance();">Submit Allowance</a>
												
											</div>
												
									 	</div>
										
 									</div>
		
 									
 <!-- -------------------------------------------------------------------------------------------------------------------------- -->			
 								</div>
 							</div>
 						</div>
 					</div>
		
	</section>
</div>	



</body>
</html>