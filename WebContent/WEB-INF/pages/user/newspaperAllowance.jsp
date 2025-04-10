<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="static/js/hrms/festival.js?v="+ Date.now() + Math.random()"></script>    
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
				NewsPaper Allowance		
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
                                   ${empId}
                              </td>
                              <td class="first style2" width="20%">Employee Name&nbsp;:</td>
                              <td align="left" width="30%">&nbsp;
                                  ${loginbean.user_name}
                              </td>
                          </tr>
                          <tr class="bg">
                              <td class="first style2">Grade&nbsp;:</td>
                              <td align="left">&nbsp;
                                   ${loginbean.emp_grade}
                              </td>
                              <td class="first style2">DOJ&nbsp;:</td>
                              <td align="left">&nbsp;
                                   ${loginbean.doj}
                              </td>
                          </tr>
                          <tr class="bg">
                              <td class="first style2">Department&nbsp;:</td>
                              <td align="left">&nbsp;
                                  -
                              </td>
                              <td class="first style2">Location&nbsp;:</td>
                              <td align="left">&nbsp;
                                  CBD Belapur
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
 									
 									
 									
<!--  									<div class="row">
										<div class="col-xl-4">
											<div class="general-label">
												<div class="form-horizontal">
													<input type="hidden" id="id"> <label
														class="col-sm-12 control-label p-0"
														for="example-input-normal">Financial Year</label>
													<div class="form-group row">
														<div class="col-md-12">
															<input type="text" id="financial_year"
																name="example-input1-group1" class="form-control"
																placeholder="Financial Year">
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
														for="example-input-normal">Child Name</label>
													<div class="form-group row">
														<div class="col-md-12">
															<input type="text" id="child_name"
																name="example-input1-group1" class="form-control"
																placeholder="Child Name">
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
														for="example-input-normal">Standard Studying</label>
													<div class="form-group row">
														<div class="col-md-12">
															<select class="form-control" id="standard"  >
																<option value="Mini" selected="selected">Nursery</option>
																<option value="Micro" selected="selected">Lower KG</option>
																<option value="Nano" selected="selected">Upper KG</option>
																<option value="Class I" selected="selected">Class I</option>
																<option value="Class II" selected="selected">Class II</option>
																<option value="Class III" selected="selected">Class III</option>
																<option value="Class IV" selected="selected">Class IV</option>
																<option value="Class V" selected="selected">Class V</option>
																<option value="Class VI" selected="selected">Class VI</option>
																<option value="Class VII" selected="selected">Class VII</option>
																<option value="Class VIII" selected="selected">Class VIII</option>
																<option value="Class IX" selected="selected">Class IX</option>
																<option value="Class X" selected="selected">Class X</option>
																
																
															</select>
															
														</div>

													</div>
												</div>
											</div>
										</div>
										
									</div>
 									<br>
 									<div class="row">
										<div class="col-xl-4">
											<div class="general-label">
												<div class="form-horizontal">
													<input type="hidden" id="id"> <label
														class="col-sm-12 control-label p-0"
														for="example-input-normal">Amount Applied</label>
													<div class="form-group row">
														<div class="col-md-12">
															<input type="text" id="amount_applied"
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
										<div class="col-xl-4">
											<div class="general-label">
												<div class="form-horizontal">
													<input type="hidden" id="id"> <label
														class="col-sm-12 control-label p-0"
														for="example-input-normal">Receipt upload</label>
													<div class="form-group row">
														<div class="col-md-12">
														
															<input type="file" id="receipt_upload" multiple="multiple"
																 class="form-control" placeholder="Receipt upload">
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
 	
 -->		
 			
 			
						 			<div>
										<div class="row">
											<!-- left column -->
											<div class="col-md-12">
												<!-- general form elements -->
												<div class="box box-primary">                 
													<div class="box-body" id="subcat">
													</div>					
													<div class="box-footer" style="text-align: center;">
														<a class="btn btn-primary"  onclick="ADDdetails();">Add Details</a>					
													</div>
												</div>
											</div>
													<%-- </form> --%>
										</div>
												<!-- /.box -->			
									</div>
			 			
			 						<br>
			 			
						 			<div id="processTbl" style="display: none">
											<div style="overflow: auto;">
											<table class="table table-bordered table-striped infosec"> 
											<thead style="font-weight: bold">
												<tr>
													<td>Allowance Applied For</td>
													<td>Student Applied For</td>
													<td>Standard applied for</td>
													<td>Maximum Eligible Amount</td>
													<td>Amount applied</td>
												</tr>
												
											</thead>
											<tbody id="infosectbl"></tbody>
											
											</table>
											</div>
											<br>
											<div class="box-footer" style="text-align: center;">
												<a class="btn btn-primary" onclick="submittimesheet();">Submit Allowance</a>
												
											</div>
												
											<div align="center" id="Loader"
												 style="background-color: #ffffff; position: fixed; opacity: 0.7; z-index: 99999; height: 100%; width: 100%; left: 0px; top: 0px; display: none">
						
												<img style="margin-left: 20px; margin-top: 200px;"
													src="images/unnamed.gif" alt="loader">
						
											</div>
									 </div>
 			
 								</div>
 							</div>
 						</div>
 	 			
 					</div>
 		
 		
 	
		
		
	</section>
</div>	



</body>
</html>