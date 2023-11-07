$("document")
		.ready(
				function() {
					debugger;
					employeeDetails();
					
					$("#eye_check_up_date").datepicker({
						format : 'dd-mm-yyyy'
					});
					
					$("#health_check_up_date").datepicker({
						format : 'dd-mm-yyyy'
					});
					
					$("#other_allowances").change(function(){
						$("#eye_checkup_fields").hide();
						$("#health_check-up_fields").hide();
						$("#mobile_fields").hide();
						$("#visiting_cards_fields").hide();
						$("#brief_case/handbag_fields").hide();
						$("#uniform_fields").hide();
						
						var otherAllwnc = $("#other_allowances").val();
						
						if(otherAllwnc == "eye_check-up_allowance")
						{
							$("#eye_checkup_fields").show();
						}
						else if(otherAllwnc == "health_check-up_allowance")
						{
							$("#health_check-up_fields").show();
						}
						else if(otherAllwnc == "mobile_reimbursement")
						{
							$("#mobile_fields").show();
						}
						else if(otherAllwnc == "visiting_cards_allowance")
						{
							$("#visiting_cards_fields").show();
						}
						else if(otherAllwnc == "brief_case/handbag_allowance")
						{
							$("#brief_case/handbag_fields").show();
						}
						else if(otherAllwnc == "uniform_allowance")
						{
							$("#uniform_fields").show();
						}
					})
					
					$("#btn")
							.on(
									'click',
									function() {
										debugger;
									});
					
					
				})			
					
function employeeDetails(){
	debugger;
	var elimit = getEyeCheckUpLimit();
	
	$.ajax({
		url : 'viewOtherAllowance',
		data : {
			CSRFToken : $("meta[name='_csrf']")
					.attr("content"),
			
		},
		type : 'POST',
		async : false,
		success : function(data) {
			console.log(data)
		},
		error : function(error) {
			
		}
	})
}	

function getEyeCheckUpLimit()
{
	debugger;
	$.ajax({
		url: 'getEyeCheckUpLimit',
		data: {
			CSRFToken: $("meta[name='_csrf']").attr("content"),
			
		},
		async: false,
		type: 'post',
		success: function(data) {
			console.log(data);
			if (data.status == "SUCCESS") {
				var response = data.body;
				var count = 1;
			}
			//window.location.reload();
		},
		error: function(error) {
			//alert("Failed");
		}
	})
}

function ADDEyeCheckUpAllowance(){
	debugger;
	var valid = true;
	var checkup_dt = $('#eye_check_up_date').val();
	var amount = $('#eye_check_up_bill_amount').val();
	var receipt = $('#eye_check_up_receipt').val();
	
	if( (checkup_dt == "") || (amount == "") || (receipt == "") ){
		valid = false;
		alert('Fill all details');
		return valid;
	}		
		
	if(valid){
		var infosectbl = document.getElementById("eye_checkup_infosectbl");
    	var tr = document.createElement("tr");
    	var td1 =document.createElement("td");
    	var td2 =document.createElement("td");
    	var td3 =document.createElement("td");
    	
    	
    	
    	var input1 = document.createElement("input");
    	input1.id = "allowance_applied_for";
    	input1.readonly = "readonly";
    	input1.value  = "Eye Check-up Allowance";     
    	
    	var input2 = document.createElement("input");
    	input2.id = "e_maximum_eligible_amount";
    	input2.readonly = "readonly";
    	input2.value  = $('#eyeCheckup_max_eligible_amt').val();
    	
    	var input3 = document.createElement("input");
    	input3.id = "m_amount_applied";
    	input3.readonly = "readonly";
    	input3.value  =	$('#eye_check_up_bill_amount').val();
    	
    	td1.appendChild(input1);
    	td2.appendChild(input2);
    	td3.appendChild(input3);
    	
    	tr.appendChild(td1);
    	tr.appendChild(td2);
    	tr.appendChild(td3);
    	
    	
    	infosectbl.appendChild(tr);
    	document.getElementById("eye_checkup_processTbl").style.display= "";
    	
    	$('#remarks').val("");
    	$('#eye_checkup_processTbl').show();
	}
}

function submitEyeCheckUpAllowance(){
	debugger;
	var jsonArray = [];
	jsonArray.length = 0
	
	var maxLimit = $("#eyeCheckup_max_eligible_amt").val();
	var appliedAmount = $("#eye_check_up_bill_amount").val();
	
	if(appliedAmount > maxLimit)
	{
		alert("Applied amount is greater than maximum eligible mount.")
	}
	else
		{
			var jsonObj = {};
			jsonObj["amount_applied"] = $("#eye_check_up_bill_amount").val();
			jsonObj["eye_checkup_date"] = $("#eye_check_up_date").val();
			
			jsonArray.push(jsonObj);
			
			$.ajax({
				url: 'submitEyeCheckUpAllowance',
				data: {
					CSRFToken: $("meta[name='_csrf']").attr("content"),
					userdata: JSON.stringify(jsonArray)
				},
				async: false,
				type: 'post',
				success: function(data) {
					alert("Added Successfully");
					window.location.reload();
				},
				error: function(error) {
					alert("Failed");
				}
			})
		}
}
