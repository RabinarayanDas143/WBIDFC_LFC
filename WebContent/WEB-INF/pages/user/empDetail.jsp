<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<script>
	$('document')
			.ready(
					function() {

						$('#dob').datepicker({
							dateFormat : 'd-M-yy',
							changeMonth : true,
							changeYear : true,
						// 			yearRange : "c:c+5"
						});
						$('#doj').datepicker({
							dateFormat : 'd-M-yy',
							changeMonth : true,
							changeYear : true,
						// 			yearRange : "c:c+5"
						});
						$('#doc').datepicker({
							dateFormat : 'd-M-yy',
							changeMonth : true,
							changeYear : true,
						// 			yearRange : "c:c+5"
						});

						$("#empDetailSubmit").on('click', function() {

						});

						$("#TranBtn-Add")
								.on(
										'click',
										function() {
											var rowcount = $(
													"#dependentTable >tbody")
													.children().length + 1;
											$('<tr>')
													.html(
															"<td><select id='dtitle"+rowcount+"'><option value='Mr'>Mr</option><option value='Ms'>Ms</option><option value='Master'>Master</option><option value='Baby'>Baby</option></select></td><td><input type='text' id='dname"+rowcount+"'></td><td><input type='text' id='ddob"+rowcount+"'></td><td><input type='text' id='dage"+rowcount+"'></td><td><select id='dgender"+rowcount+"'><option value='Male'>Male</option><option value='Female'>Female</option><option value='Others'>Others</option></select></td><td><select id='relation"+rowcount+"'><option value='Self'>Self</option><option value='Spouse'>Spouse</option><option value='Father'>Father</option></select></td><td><select id='doccupation"+rowcount+"'><option value='Public'>Public</option></select></td><td><input type='text' id='dincome"+rowcount+"'></td>")
													.appendTo(
															"#dependentTableBody");
											$('#ddob' + rowcount).datepicker({
												dateFormat : 'd-M-yy'
											});
										});
						$("#TranBtn-Remove").on('click', function() {
							$("#dependentTableBody tr:last").remove();
						});

						$("#ConBtn-Add")
								.on(
										'click',
										function() {
											var rowcount = $(
													"#contactTable >tbody")
													.children().length + 1;
											$('<tr>')
													.html(
															"<td><select id='addresstype"+rowcount+"'><option value='Communication'>Communication</option><option value='Permanent'>Permanent</option></select></td><td><input type='text'  id='addr1"+rowcount+"'></td><td><input type='text'  id='addr2"+rowcount+"'></td><td><input type='text'  id='addr3"+rowcount+"'></td><td><input type='text'  id='city"+rowcount+"'></td><td><input type='text'  id='state"+rowcount+"'><td><input type='text'  id='pincode"+rowcount+"'></td></td>")
													.appendTo(
															"#contactTableBody");
											$('#ddob' + rowcount).datepicker({
												dateFormat : 'd-M-yy'
											});
										});
						$("#ConBtn-Remove").on('click', function() {
							$("#contactTableBody tr:last").remove();
						});
					});
</script>
<!-- content @s -->
<div class="nk-content ">
	<div class="container-fluid">
		<div class="nk-content-inner">
			<div class="nk-content-body">
				<div class="components-preview wide-lg mx-auto">
					<div class="nk-block-head nk-block-head-lg wide-sm">
						<!--  <div class="nk-block-head-content">
                                        <div class="nk-block-head-sub"><a class="back-to" href="html/components.html"><em class="icon ni ni-arrow-left"></em><span>Components</span></a></div>
                                        <h2 class="nk-block-title fw-normal">Form Layouts</h2>
                                        <div class="nk-block-des">
                                            <p class="lead">Form is most esential part of your project. We styled out nicely so you can build your form so quickly.</p>
                                        </div>
                                    </div> -->
					</div>
					<!-- .nk-block -->
					<div class="nk-block nk-block-lg">
						<div class="nk-block-head">
							<div class="nk-block-head-content">
								<h4 class="title nk-block-title">Employee Details</h4>
								<div class="nk-block-des">
									<p>Add employee details.</p>
								</div>
							</div>
						</div>
						<div class="card card-bordered">
							<div class="card-inner">

								<form action="#">
									<div class="row g-4">
										<div class="card-head">
											<h5 class="card-title">Basic Details</h5>
										</div>

										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="full-name-1">Employee
													Code</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required"
														id="emply_cd">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="full-name-1">TITLE</label>
												<div class="form-control-wrap">
													<select class="form-control required" id="title">
														<option value="Mr">Mr</option>
														<option value="Ms">Ms</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="email-address-1">
													FIRST NAME NAME</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="fname">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="email-address-1">MIDDLE
													NAME</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id=mname">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="email-address-1">LAST
													NAME</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id=lname">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="pay-amount-1">GENDER</label>
												<div class="form-control-wrap">
													<select class="form-control required" id="gender">
														<option value="Male">Male</option>
														<option value="Female">Female</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">DATE OF
													BIRTH</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="dob">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">AGE</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="age">
												</div>
											</div>
										</div>

										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="pay-amount-1">Email
													Address</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="email">
												</div>
											</div>
										</div>

										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="pay-amount-1">MOBILE
													NO.</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required"
														id="mobileno">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="pay-amount-1">ALTERNATE
													PHOME NO.</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required"
														id="altmobileno">
												</div>
											</div>
										</div>

										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="pay-amount-1">MARITAL
													STATUS</label>
												<div class="form-control-wrap">
													<select class="form-control required" id="maritalstatus">
														<option value="Married">Married</option>
														<option value="Single">Single</option>
														<option value="Widow">Widow</option>
														<option value="Legally Separated / Divorced">Legally
															Separated / Divorced</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="pay-amount-1">RELIGION</label>
												<div class="form-control-wrap">
													<select class="form-control required" id="religion">
														<option value="Hindu">Hindu</option>
														<option value="Muslim">Muslim</option>
														<option value="Christian">Christian</option>
														<option value="Sikh">Sikh</option>
														<option value="Jain">Jain</option>
														<option value="Others">Others</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="pay-amount-1">CATEGORY</label>
												<div class="form-control-wrap">
													<select class="form-control required" id="category">
														<option value="General">General</option>
														<option value="OBC">OBC</option>
														<option value="EWS">EWS</option>
														<option value="SC">SC</option>
														<option value="ST">ST</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="pay-amount-1">SUB
													CATEGORY</label>
												<div class="form-control-wrap">
													<select class="form-control required" id="subcategory">
														<option value="Ex-serviceman">Ex-serviceman</option>
														<option value="Visually Impaired">Visually
															Impaired</option>
														<option value="Orthopedically Challenged">Orthopedically
															Challenged</option>
														<option value="Hearing Impaired">Hearing Impaired</option>
														<option value="Physically Handicapped">Physically
															Handicapped</option>
														<option value="Intellectual Disability">Intellectual
															Disability</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="pay-amount-1">ALLOTED
													CATEGORY</label>
												<div class="form-control-wrap">
													<select class="form-control required" id="allotedcategory">
														<option value="General">General</option>
														<option value="OBC">OBC</option>
														<option value="EWS">EWS</option>
														<option value="SC">SC</option>
														<option value="ST">ST</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="pay-amount-1">ALLOTED
													SUB CATEGORY</label>
												<div class="form-control-wrap">
													<select class="form-control required"
														id="allotedsubcategory">
														<option value="Ex-serviceman">Ex-serviceman</option>
														<option value="Visually Impaired">Visually
															Impaired</option>
														<option value="Orthopedically Challenged">Orthopedically
															Challenged</option>
														<option value="Hearing Impaired">Hearing Impaired</option>
														<option value="Physically Handicapped">Physically
															Handicapped</option>
														<option value="Intellectual Disability">Intellectual
															Disability</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">DATE OF
													JOINING</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="doj">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">DATE OF
													CONFIRMATION</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="doc">
												</div>
											</div>
										</div>

										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">DATE OF
													RETIREMENT</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="dor">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">Salary
													Account No. (SB)</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="accSB">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">Salary
													Account No. (COD)</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required"
														id="accCod">
												</div>
											</div>
										</div>
										<!-- branch  -->
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="pay-amount-1">BRANCH</label>
												<div class="form-control-wrap">
													<select class="form-control required" id="branch">
													</select>
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">BRANCH
													EFFECTIVE DATE </label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required"
														id="branchdate">
												</div>
											</div>
										</div>
										<!-- department  -->
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="pay-amount-1">DEPARTMENT</label>
												<div class="form-control-wrap">
													<select class="form-control required" id="department">
													</select>
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">DEPARTMENT
													EFFECTIVE DATE </label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required"
														id="departmentdate">
												</div>
											</div>
										</div>

										<!-- cadre  -->
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="pay-amount-1">CADRE</label>
												<div class="form-control-wrap">
													<select class="form-control required" id="cadre">
													</select>
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">CADRE
													EFFECTIVE DATE </label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required"
														id="cadredate">
												</div>
											</div>
										</div>

										<!-- designation  -->
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="pay-amount-1">DESIGNATION</label>
												<div class="form-control-wrap">
													<select class="form-control required" id="designation">
													</select>
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">DESIGNATION
													EFFECTIVE DATE </label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required"
														id="designationdate">
												</div>
											</div>
										</div>

										<!-- scale basic  -->
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="pay-amount-1">SCALE
													BASIC</label>
												<div class="form-control-wrap">
													<select class="form-control required" id="basic">
													</select>
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">BASIC
													EFFECTIVE DATE </label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required"
														id="basicdate">
												</div>
											</div>
										</div>

										<!-- stagnation limit  -->
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="pay-amount-1">STAGNATION
													LIMIT</label>
												<div class="form-control-wrap">
													<select class="form-control required" id="staglimit">
													</select>
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">STAGNATION
													EFFECTIVE DATE </label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required"
														id="stagdate">
												</div>
											</div>
										</div>
										<!-- emp stat   -->
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="pay-amount-1">
													RESIGNATION STATUS</label>
												<div class="form-control-wrap">
													<select class="form-control required" id="regstatus">
													</select>
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">RESIGNATION/TERMINATION/VRS
													DATE </label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required"
														id="regdate">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">EFFECTIVE
													DATE DATE </label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required"
														id="regeffdate">
												</div>
											</div>
										</div>

										<!--  increment  -->
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">NEXT
													INCREMENT EFFECTIVE DATE </label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required"
														id="incrementdate">
												</div>
											</div>
										</div>


										<!--  	 isjoined custodian -->
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="pay-amount-1">Is
													Joint Custodian</label>
												<div class="form-control-wrap">
													<select class="form-control required" id="custodian">
														<option value="Yes">Yes</option>
														<option value="No">No</option>
													</select>
												</div>
											</div>
										</div>
										<div id="CustodianDiv">
											<div class="col-lg-6">
												<div class="form-group">
													<label class="form-label" for="phone-no-1">EFFECTIVE
														FROM DATE </label>
													<div class="form-control-wrap">
														<input type="text" class="form-control required"
															id="custfrom">
													</div>
												</div>
											</div>
											<div class="col-lg-6">
												<div class="form-group">
													<label class="form-label" for="phone-no-1">EFFECTIVE
														TO DATE</label>
													<div class="form-control-wrap">
														<input type="text" class="form-control required"
															id="custto">
													</div>
												</div>
											</div>
										</div>

										<!-- <!-- 										contact div -->
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">ADDRESS
													TYPE</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="ctype"
														value="COMMUNICATION">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">ADDRESS
													LINE 1</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="cadd1">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">ADDRESS
													LINE 2</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="cadd2">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">ADDRESS
													LINE 3</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="cadd3">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">CITY</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="ccity">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">STATE</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required"
														id="cstate">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">PINCODE</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="cpin">
												</div>
											</div>
										</div>

										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">ADDRESS
													TYPE</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="ptype"
														value="PERMANENT">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">ADDRESS
													LINE 1</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="padd1">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">ADDRESS
													LINE 2</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="padd2">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">ADDRESS
													LINE 3</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="padd3">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">CITY</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="pcity">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">STATE</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required"
														id="pstate">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">PINCODE</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="ppin">
												</div>
											</div>
										</div>
										-->
										<!--  end  contact detail -->
										<!-- dependend detail -->
										<div>
											<div class="input-group">
												<button type="button" id="TranBtn-Add"
													class="btn btn-gradient-success" style="width: 100px">
													Add Dependent</button>
												<button type="button" id="TranBtn-Remove"
													class="btn btn-gradient-warning" style="width: 100px">
													Remove Dependent</button>
											</div>
											<table id="dependentTable">
												<thead>
													<tr>
														<th>Dependent Title</th>
														<th>Dependent Name</th>
														<th>Date of Birth</th>
														<th>Age</th>
														<th>Gender</th>
														<th>Relation</th>
														<th>Occupation</th>
														<th>Annual Income</th>
													</tr>
												</thead>
												<tbody id="dependentTableBody">
												</tbody>
											</table>

										</div>
										<!-- 	end dependend detail -->

										<!-- contact detail -->
										<div>
											<div class="input-group">
												<button type="button" id="ConBtn-Add"
													class="btn btn-gradient-success" style="width: 100px">
													Add Address</button>
												<button type="button" id="ConBtn-Remove"
													class="btn btn-gradient-warning" style="width: 100px">
													Remove Address</button>
											</div>
											<table id="contactTable">
												<thead>
													<tr>
														<th>Address Type</th>
														<th>Address Line 1</th>
														<th>Address Line 2</th>
														<th>Address Line 3</th>
														<th>City</th>
														<th>State</th>
														<th>Pincode</th>
													</tr>
												</thead>
												<tbody id="contactTableBody">
												</tbody>
											</table>

										</div>
										<!-- 	end contact detail -->

										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">PAN NO. </label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="panno">
												</div>
											</div>
										</div>

										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">PF NO. </label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="pfno">
												</div>
											</div>
										</div>

										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">EPS
													Account No. </label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required"
														id="epsaccno">
												</div>
											</div>
										</div>

										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">UAN No.</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required" id="uanno">
												</div>
											</div>
										</div>

										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">PRAN No.</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required"
														id="pranno">
												</div>
											</div>
										</div>

										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">AADHAAR
													No.</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required"
														id="aadhaarno">
												</div>
											</div>
										</div>

										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1">DRIVING
													LICENSE No.</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required"
														id="drivingno">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1"> EPIC No.</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required"
														id="epicno">
												</div>
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-label" for="phone-no-1"> PASSPORT
													No.</label>
												<div class="form-control-wrap">
													<input type="text" class="form-control required"
														id="passportno">
												</div>
											</div>
										</div>
										<div class="col-12">
											<div class="form-group">
												<input type="button" id="empDetailSubmit"
													class="btn btn-lg btn-primary" value="Save Informations">
											</div>
										</div>
									</div>
								</form>
							</div>
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