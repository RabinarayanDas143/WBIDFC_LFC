<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="static/js/hrms/festival.js?v="+ Date.now() + Math.random()"></script>
<script type="text/javascript" src="static/js/hrms/monthlyAllowance.js?v="+ Date.now() + Math.random()"></script>   
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
				Monthly Allowances		
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
                              <td id=emply_cd align="left" width="28%">&nbsp;
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
														for="example-input-normal">Monthly Allowances</label>
													<div class="form-group row">
														<div class="col-md-12">
															<select class="form-control" id="monthly_allowances"  >
																<option value ="" disabled selected>--Select Action--</option>
																<option value="newspaper">Newspaper</option>
																<option value="food_beverages">Food & Beverages</option>
																<option value="refreshments">Refreshments</option>
																<option value="conveyance">Conveyance</option>
																<option value="telephone">Telephone</option>
																
															</select>
															
														</div>

													</div>
												</div>
											</div>
										</div>
										
									</div>
 									
 	<!-- ---------------------------------------------------------------------------------------------------------------- -->									
 									<div id="newspaper_fields" style="display: none">
	 									<div class="row">
	 										<div class="col-xl-4">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Allowance Name</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id= "newspaper_Allowance_Name"
																	name="example-input1-group1" class="form-control" 
																	value= "Newspaper Allowance" readonly="readonly">
															</div>
	
														</div>
													</div>
												</div>
											</div>
	 									
											<div class="col-xl-4">
												<div class="general-label">
													<div class="form-horizontal" id="financial_year">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Month Year</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="month"  id="newspaper_applied_year_month"
																	name="example-input1-group1" class="form-control"
																	>
															</div>
	
														</div>
													</div>
												</div>
											</div>
											
											<div class="col-xl-4">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Amount applied</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id="newspaper_amount_applied"
																	name="example-input1-group1" class="form-control"
																	placeholder="Amount applied"
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
														<a class="btn btn-primary"  onclick="ADDdetailsNewspaperAllowance();">Add Details</a>					
													</div>
												</div>
											</div>
										</div>
										
										<div id="newspaper_processTbl" style="display: none">
											<div style="overflow: auto;">
											<input style="display: none" type="text" id="newspaper_max_eligible_amount"  value = '${limitvalue}'>
											<table class="table table-bordered table-striped infosec"> 
											<thead style="font-weight: bold">
												<tr>
													<td>Allowance Applied For</td>
													<td>Maximum Eligible Amount</td>
													<td>Amount applied</td>
												</tr>
												
											</thead>
											<tbody id="newspaper_infosectbl"></tbody>
											
											</table>
											</div>
											<br>
											<div class="box-footer" style="text-align: center;">
												<a class="btn btn-primary" onclick="submitNewspaperAllowance();">Submit Allowance</a>
												
											</div>
												
											<div align="center" id="Loader"
												 style="background-color: #ffffff; position: fixed; opacity: 0.7; z-index: 99999; height: 100%; width: 100%; left: 0px; top: 0px; display: none">
						
												<img style="margin-left: 20px; margin-top: 200px;"
													src="images/unnamed.gif" alt="loader">
						
											</div>
									 	</div>
									 
 									</div>
 									
 <!-- ---------------------------------------------------------------------------------------------------------------- -->									
 									
 									<div id="food_beverages_fields" style="display: none">
	 									<div class="row">
	 										<div class="col-xl-4">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Allowance Name</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" 
																	name="example-input1-group1" class="form-control"
																	value= "Food & Beverages Allowance" readonly="readonly">
															</div>
	
														</div>
													</div>
												</div>
											</div>
	 									
											<div class="col-xl-4">
												<div class="general-label">
													<div class="form-horizontal" id="financial_year">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Month Year</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="month"  id="food_beverages_applied_year_month"
																	name="example-input1-group1" class="form-control"
																	>
															</div>
	
														</div>
													</div>
												</div>
											</div>
											
											<div class="col-xl-4">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Amount applied</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id="food_beverages_amount_applied"
																	name="example-input1-group1" class="form-control"
																	placeholder="Amount applied"
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
														<a class="btn btn-primary"  onclick="ADDdetailsFoodAllowance();">Add Details</a>					
													</div>
												</div>
											</div>
										</div>
										
										<div id="foodAllowance_processTbl" style="display: none">
											<div style="overflow: auto;">
											<input style="display: none" type="text" id="food_max_eligible_amount"  value ='${foodLimitValue}'>
											<table class="table table-bordered table-striped infosec"> 
											<thead style="font-weight: bold">
												<tr>
													<td>Allowance Applied For</td>
													<td>Maximum Eligible Amount</td>
													<td>Amount applied</td>
												</tr>
												
											</thead>
											<tbody id="foodallowance_infosectbl"></tbody>
											
											</table>
											</div>
											<br>
											<div class="box-footer" style="text-align: center;">
												<a class="btn btn-primary" onclick="submitFoodAllowance();">Submit Allowance</a>
												
											</div>
												
									 	</div>
									 
 									</div>
 									
 <!-- ---------------------------------------------------------------------------------------------------------------- -->									
 									
 									<div id="refreshments_fields" style="display: none">
	 									<div class="row">
	 										<div class="col-xl-4">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Allowance Name</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" 
																	name="example-input1-group1" class="form-control"
																	value= "Refreshments Allowance" readonly="readonly">
															</div>
	
														</div>
													</div>
												</div>
											</div>
	 									
											<div class="col-xl-4">
												<div class="general-label">
													<div class="form-horizontal" id="financial_year">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Month Year</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="month"  id="refreshments_applied_year_month"
																	name="example-input1-group1" class="form-control"
																	>
															</div>
	
														</div>
													</div>
												</div>
											</div>
											
											<div class="col-xl-4">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Amount applied</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id="refreshments_amount_applied"
																	name="example-input1-group1" class="form-control"
																	placeholder="Amount applied"
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
														<a class="btn btn-primary"  onclick="ADDdetailsRefreshmentAllowance();">Add Details</a>					
													</div>
												</div>
											</div>
										</div>
										
										<div id="refreshments_processTbl" style="display: none">
											<div style="overflow: auto;">
											<input style="display: none" type="text" id="refreshments_max_eligible_amount"  value = '${refLimitValue}'>
											<table class="table table-bordered table-striped infosec"> 
											<thead style="font-weight: bold">
												<tr>
													<td>Allowance Applied For</td>
													<td>Maximum Eligible Amount</td>
													<td>Amount applied</td>
												</tr>
												
											</thead>
											<tbody id="refreshments_infosectbl"></tbody>
											
											</table>
											</div>
											<br>
											<div class="box-footer" style="text-align: center;">
												<a class="btn btn-primary" onclick="submitRefreshmentAllowance();">Submit Allowance</a>
												
											</div>
												
									 	</div>
									 
 									</div>
 									
 <!-- ---------------------------------------------------------------------------------------------------------------- -->									
 									
 									<div id="conveyance_fields" style="display: none">
	 									<div class="row">
	 										<div class="col-xl-4">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Allowance Name</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" 
																	name="example-input1-group1" class="form-control"
																	value= "Conveyance Allowance" readonly="readonly">
															</div>
	
														</div>
													</div>
												</div>
											</div>
	 									
											<div class="col-xl-4">
												<div class="general-label">
													<div class="form-horizontal" id="financial_year">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Month Year</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="month"  id="conveyance_applied_year_month"
																	name="example-input1-group1" class="form-control"
																	>
															</div>
	
														</div>
													</div>
												</div>
											</div>
											
											<div class="col-xl-4">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Amount applied</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id="conveyance_amount_applied"
																	name="example-input1-group1" class="form-control"
																	placeholder="Amount applied"
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
														<a class="btn btn-primary"  onclick="ADDdetailsConveyanceAllowance();">Add Details</a>					
													</div>
												</div>
											</div>
										</div>
										
										<div id="conveyance_processTbl" style="display: none">
											<div style="overflow: auto;">
											<input style="display: none" type="text" id="conveyance_max_eligible_amount"  value = '${convLimitValue}'>
											<table class="table table-bordered table-striped infosec"> 
											<thead style="font-weight: bold">
												<tr>
													<td>Allowance Applied For</td>
													<td>Maximum Eligible Amount</td>
													<td>Amount applied</td>
												</tr>
												
											</thead>
											<tbody id="conveyance_infosectbl"></tbody>
											
											</table>
											</div>
											<br>
											<div class="box-footer" style="text-align: center;">
												<a class="btn btn-primary" onclick="submitConveyanceAllowance();">Submit Allowance</a>
												
											</div>
												
									 	</div>
									 
 									</div>
 									
 <!-- ---------------------------------------------------------------------------------------------------------------- -->									
 									
 									<div id="telephone_fields" style="display: none">
	 									<div class="row">
	 										<div class="col-xl-4">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Allowance Name</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" 
																	name="example-input1-group1" class="form-control"
																	value= "Telephone Allowance" readonly="readonly">
															</div>
	
														</div>
													</div>
												</div>
											</div>
	 									
											<div class="col-xl-4">
												<div class="general-label">
													<div class="form-horizontal" id="financial_year">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Month Year</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="month"  id="telephone_applied_year_month"
																	name="example-input1-group1" class="form-control"
																	>
															</div>
	
														</div>
													</div>
												</div>
											</div>
											
											<div class="col-xl-4">
												<div class="general-label">
													<div class="form-horizontal">
														<input type="hidden" id="id"> <label
															class="col-sm-12 control-label p-0"
															for="example-input-normal">Amount applied</label>
														<div class="form-group row">
															<div class="col-md-12">
																<input type="text" id="telephone_amount_applied"
																	name="example-input1-group1" class="form-control"
																	placeholder="Amount applied"
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
														<a class="btn btn-primary"  onclick="ADDdetailsTelephoneAllowance();">Add Details</a>					
													</div>
												</div>
											</div>
										</div>
										
										<div id="telephone_processTbl" style="display: none">
											<div style="overflow: auto;">
											<input style="display: none" type="text" id="tele_max_amount_applied"  value = '${teleLimitValue}'>
											<table class="table table-bordered table-striped infosec"> 
											<thead style="font-weight: bold">
												<tr>
													<td>Allowance Applied For</td>
													<td>Maximum Eligible Amount</td>
													<td>Amount applied</td>
												</tr>
												
											</thead>
											<tbody id="telephone_infosectbl"></tbody>
											
											</table>
											</div>
											<br>
											<div class="box-footer" style="text-align: center;">
												<a class="btn btn-primary" onclick="submitTelephoneAllowance();">Submit Allowance</a>
												
											</div>
												
									 	</div>
									 
 									</div>
 									
 <!-- ---------------------------------------------------------------------------------------------------------------- -->									
 									
			 						<br>
			 			
						 			
 			
 								</div>
 							</div>
 						</div>
 	 			
 					</div>
 		
 		
 	
		
		
	</section>
</div>	



</body>
</html>