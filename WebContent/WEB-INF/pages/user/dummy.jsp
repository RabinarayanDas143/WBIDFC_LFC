<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<script>
	$('document').ready(function() {
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
                                </div><!-- .nk-block -->
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
                                                            <label class="form-label" for="full-name-1">EIN</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="ein">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="email-address-1">Full Name</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="fullname">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="phone-no-1">Date of birth</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="dob">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="pay-amount-1">Gender</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="gender">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="pay-amount-1">Email Address</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="email">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    
                                                    <div class="card-head">
                                                <h5 class="card-title">Address Details</h5>
                                            	</div>
                                            	
                                            	 <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="pay-amount-1">Address Line 1</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="address1">
                                                            </div>
                                                        </div>
                                                    </div>
                                                     <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="pay-amount-1">Address Line 2</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="address2">
                                                            </div>
                                                        </div>
                                                    </div>
                                                     
                                                     <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="pay-amount-1">City</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="city">
                                                            </div>
                                                        </div>
                                                    </div>
                                                     <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="pay-amount-1">Country</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="country">
                                                            </div>
                                                        </div>
                                                    </div>
                                                     <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="pay-amount-1">Postal Code</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="postalCode">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    
                                                    <div class="card-head">
                                                <h5 class="card-title">Other Details</h5>
                                            	</div>
                                            	
                                            	 <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="pay-amount-1">Account</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="account">
                                                            </div>
                                                        </div>
                                                    </div>
                                                     <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="pay-amount-1">Owner</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="owner">
                                                            </div>
                                                        </div>
                                                    </div>
                                                     <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="pay-amount-1">Date of Joining</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="doj">
                                                            </div>
                                                        </div>
                                                    </div>
                                                     <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="pay-amount-1">Date of Confirmation</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="doc">
                                                            </div>
                                                        </div>
                                                    </div>
                                                     <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="pay-amount-1">Employee Work Class</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="emplyworkclass">
                                                            </div>
                                                        </div>
                                                    </div>
                                                     <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="pay-amount-1">Grade Id</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="gradeid">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="pay-amount-1">Org Id</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="positionid">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="pay-amount-1">Position Id</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="position">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="pay-amount-1">Location Id</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="locationid">
                                                            </div>
                                                        </div>
                                                    </div>
                                                     <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="pay-amount-1">SOL Id</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="solid">
                                                            </div>
                                                        </div>
                                                    </div>
                                                     <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="pay-amount-1">SUP EIN</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="supein">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="pay-amount-1">Conf Status</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="confirmationstat">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label" for="wfh">WFH</label>
                                                            <div class="form-control-wrap">
                                                                <input type="text" class="form-control required" id="wfh">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    
                                                    <!-- <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label">Communication</label>
                                                            <ul class="custom-control-group g-3 align-center">
                                                                <li>
                                                                    <div class="custom-control custom-control-sm custom-checkbox">
                                                                        <input type="checkbox" class="custom-control-input" id="com-email-1">
                                                                        <label class="custom-control-label" for="com-email-1">Email</label>
                                                                    </div>
                                                                </li>
                                                                <li>
                                                                    <div class="custom-control custom-control-sm custom-checkbox">
                                                                        <input type="checkbox" class="custom-control-input" id="com-sms-1">
                                                                        <label class="custom-control-label" for="com-sms-1">SMS</label>
                                                                    </div>
                                                                </li>
                                                                <li>
                                                                    <div class="custom-control custom-control-sm custom-checkbox">
                                                                        <input type="checkbox" class="custom-control-input" id="com-phone-1">
                                                                        <label class="custom-control-label" for="com-phone-1">Phone</label>
                                                                    </div>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="form-group">
                                                            <label class="form-label">Payment Methods</label>
                                                            <ul class="custom-control-group g-3 align-center">
                                                                <li>
                                                                    <div class="custom-control custom-control-sm custom-checkbox">
                                                                        <input type="checkbox" class="custom-control-input" id="pay-card-1">
                                                                        <label class="custom-control-label" for="pay-card-1">Card</label>
                                                                    </div>
                                                                </li>
                                                                <li>
                                                                    <div class="custom-control custom-control-sm custom-checkbox">
                                                                        <input type="checkbox" class="custom-control-input" id="pay-bitcoin-1">
                                                                        <label class="custom-control-label" for="pay-bitcoin-1">Bitcoin</label>
                                                                    </div>
                                                                </li>
                                                                <li>
                                                                    <div class="custom-control custom-control-sm custom-checkbox">
                                                                        <input type="checkbox" class="custom-control-input" id="pay-cash-1">
                                                                        <label class="custom-control-label" for="pay-cash-1">Cash</label>
                                                                    </div>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </div> -->
                                                    <div class="col-12">
                                                        <div class="form-group">
                                                            <input type="button"  id="empDetailSubmit" class="btn btn-lg btn-primary" value="Save Informations">
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div><!-- .nk-block -->
                                </div><!-- .components-preview wide-lg mx-auto -->
                        </div>
                    </div>
                </div>
            </div>
            <!-- content @e -->