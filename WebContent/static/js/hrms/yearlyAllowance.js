$("document")
		.ready(
				function() {
					employeeDetails();
					
					$("#yearly_allowances").change(function(){
						$("#medical_allowance_fields").hide();
						$("#festival_advance_fields").hide();
						$("#annual_pl_encashment_fields").hide();
						$("#tuition_fee_reimbursement_fields").hide();
						debugger;
						var yearlyAllVal = $("#yearly_allowances").val();
						if(yearlyAllVal == "medical_allowance")
						{
							$("#medical_allowance_fields").show();
						}
						else if(yearlyAllVal == "festival_advance")
						{
							$("#festival_advance_fields").show();
						}
						else if(yearlyAllVal == "annual_pl_encashment")
						{
							$("#annual_pl_encashment_fields").show();
						}
						else if(yearlyAllVal == "tuition_fee_reimbursement")
						{
							$("#tuition_fee_reimbursement_fields").show();
						}
						
					})
					
					var todayDate = new Date();
					var currentYear = todayDate.getFullYear();
					$("#festival_calender_year").val(currentYear);
					$("#medical_calender_year").val(currentYear);
					$("#pl_encash_calender_year").val(currentYear);
					$("#tuition_fee_financial_year").val(currentYear);
					
					
				})	
				
function employeeDetails(){
	debugger;
	$.ajax({
		url : 'viewYearlyAllowance',
		data : {
			CSRFToken : $("meta[name='_csrf']")
					.attr("content"),
			
		},
		type : 'POST',
		async : false,
		success : function(data) {
			console.log(data);
		},
		error : function(error) {
			alert(error);
		}
	})
}

function getMedicalLimit()
{
	debugger;
	$.ajax({
		url: 'getMedicalLimit',
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

function ADDdetailsMedicalAllowance(){
	debugger;
	var valid = true;
	var yearMonth = $('#medical_calender_year').val();
	var amount = $('#medical_amount_applied').val();
	
	if((yearMonth == "") || (amount == "")){
		valid = false;
		alert('Fill all details');
		return valid;
	}		
	var mlimit = getMedicalLimit();
	
	if(valid){
    	var infosectbl = document.getElementById("medical_infosectbl");
    	var tr = document.createElement("tr");
    	var td1 =document.createElement("td");
    	var td2 =document.createElement("td");
    	var td3 =document.createElement("td");
    	var td4 =document.createElement("td");
    	
    	var input1 = document.createElement("input");
    	input1.id = "allowance_applied_for";
    	input1.readonly = "readonly";
    	input1.value  = "Medical Reimbursement";     
    	
    	var input2 = document.createElement("input");
    	input2.id = "period_applied_for";
    	input2.readonly = "readonly";
    	input2.value  = $('#medical_calender_year').val();
    	
    	var input3 = document.createElement("input");
    	input3.id = "m_maximum_eligible_amount";
    	input3.readonly = "readonly";
    	input3.value  =	$('#medical_max_eligible_amount').val();
    	
    	var input4 = document.createElement("input");
    	input4.id = "m_amount_applied";
    	input4.readonly = "readonly";
    	input4.value  = $('#medical_amount_applied').val();	 
    	
    	td1.appendChild(input1);
    	td2.appendChild(input2);
    	td3.appendChild(input3);
    	td4.appendChild(input4);
    	
    	tr.appendChild(td1);
    	tr.appendChild(td2);
    	tr.appendChild(td3);
    	tr.appendChild(td4);
    	
    	medical_infosectbl.appendChild(tr)
    	document.getElementById("medical_processTbl").style.display= "";
    	
    	$('#remarks').val("");
    	$('#medical_processTbl').show();
	}
}


function ADDdetailsfestivalAllowance(){
	debugger;
	debugger;
	var valid = true;
	var yearMonth = $('#festival_calender_year').val();
	var type = $('#festival_type').val();
	var name = $('#festival_name').val();
	var amount = $('#festival_amount_applied').val();
	
	
	if((yearMonth == "") || (amount == "") || (type == "") || (name == "")){
		valid = false;
		alert('Fill all details');
		return valid;
	}		
	var flimit = getMedicalLimit();
	
	if(valid){
    	var infosectbl = document.getElementById("festival_infosectbl");
    	var tr = document.createElement("tr");
    	var td1 =document.createElement("td");
    	var td2 =document.createElement("td");
    	var td3 =document.createElement("td");
    	var td4 =document.createElement("td");
    	
    	var input1 = document.createElement("input");
    	input1.id = "allowance_applied_for";
    	input1.readonly = "readonly";
    	input1.value  = "Festival Allowance";     
    	
    	var input2 = document.createElement("input");
    	input2.id = "festival_applied_for";
    	input2.readonly = "readonly";
    	input2.value  = $('#festival_name').val();
    	
    	var input3 = document.createElement("input");
    	input3.id = "maximum_eligible_amount";
    	input3.readonly = "readonly";
    	input3.value  =	"5000";	
    	
    	var input4 = document.createElement("input");
    	input4.id = "amount_applied";
    	input4.readonly = "readonly";
    	input4.value  = $('#festival_amount_applied').val();	 
    	
    	td1.appendChild(input1);
    	td2.appendChild(input2);
    	td3.appendChild(input3);
    	td4.appendChild(input4);
    	
    	tr.appendChild(td1);
    	tr.appendChild(td2);
    	tr.appendChild(td3);
    	tr.appendChild(td4);
    	
    	festival_infosectbl.appendChild(tr)
    	document.getElementById("festival_processTbl").style.display= "";
    	
    	$('#remarks').val("");
    	$('#festival_processTbl').show();
	}
}

function ADDdetailsPLEncashment(){
	debugger;
	var valid = true;
	var year = $('#pl_encash_calender_year').val(); 
	var eligible_days = $('#pl_encash_eligible_days').val();
	var days_applied = $('#pl_encash_days_applied').val();
	var type = $('#pl_encash_festival_type').val();
	var name = $('#pl_encash_festival_name').val();
	
	
	
	if((year == "") || (eligible_days == "") || (days_applied == "") || (type == "") || (name == ""))
	{
		valid = false;
		alert('Fill all details');
		return valid;
	}		
	
	if(days_applied > eligible_days)
	{
		valid = false;
		alert('Applied days Should not be more than Eligible days');
		return valid;
	}
		
	if(valid){
    	var infosectbl = document.getElementById("annual_pl_infosectbl");
    	var tr = document.createElement("tr");
    	var td1 =document.createElement("td");
    	var td2 =document.createElement("td");
    	var td3 =document.createElement("td");
    	var td4 =document.createElement("td");
    	var td5 =document.createElement("td");
    	var td6 =document.createElement("td");
    	
    	
    	var input1 = document.createElement("input");
    	input1.id = "allowance_applied_for";
    	input1.readonly = "readonly";
    	input1.value  = "Annual PL Encashment";     
    	
    	var input2 = document.createElement("input");
    	input2.id = "year_applied_for";
    	input2.readonly = "readonly";
    	input2.value  = $('#pl_encash_calender_year').val();
    	
    	var input3 = document.createElement("input");
    	input3.id = "festival_applied_for";
    	input3.readonly = "readonly";
    	input3.value  =	$('#pl_encash_festival_name').val();
    	
    	var input4 = document.createElement("input");
    	input4.id = "days_eligible";
    	input4.readonly = "readonly";
    	input4.value  = $('#pl_encash_eligible_days').val();
    	
    	var input5 = document.createElement("input");
    	input5.id = "days_applied";
    	input5.readonly = "readonly";
    	input5.value  = $('#pl_encash_days_applied').val();
    	
    	var input6 = document.createElement("input");
    	input6.id = "amount_eligible_for_sanction";
    	input6.readonly = "readonly";
    	input6.value  = $('#pl_encash_days_applied').val();
    	
    	td1.appendChild(input1);
    	td2.appendChild(input2);
    	td3.appendChild(input3);
    	td4.appendChild(input4);
    	td5.appendChild(input5);
    	td6.appendChild(input6);
    	
    	tr.appendChild(td1);
    	tr.appendChild(td2);
    	tr.appendChild(td3);
    	tr.appendChild(td4);
    	tr.appendChild(td5);
    	tr.appendChild(td6);
    	
    	annual_pl_infosectbl.appendChild(tr)
    	document.getElementById("annual_pl_processTbl").style.display= "";
    	
    	$('#remarks').val("");
    	$('#annual_pl_processTbl').show();
	}
}
	

function ADDTuitionFeeAllowance(){
	debugger;
	var valid = true;
	var year = $('#tuition_fee_financial_year').val(); 
	var child_name = $('#child_name').val();
	var standard = $('#standard').val();
	var amount = $('#tuition_fee_amount_applied').val();
	var reciept = $('#tuition_fee_receipt').val();
	
	if((year == "") || (child_name == "") || (standard == "") || (amount == "") || (reciept == ""))
	{
		valid = false;
		alert('Fill all details');
		return valid;
	}		
		
	if(valid){
    	var infosectbl = document.getElementById("tuition_infosectbl");
    	var tr = document.createElement("tr");
    	var td1 =document.createElement("td");
    	var td2 =document.createElement("td");
    	var td3 =document.createElement("td");
    	var td4 =document.createElement("td");
    	var td5 =document.createElement("td");
    	
    	
    	
    	var input1 = document.createElement("input");
    	input1.id = "fee_allowance_applied_for";
    	input1.readonly = "readonly";
    	input1.value  = "Tuition Fee Reimbursement";     
    	
    	var input2 = document.createElement("input");
    	input2.id = "student_applied_for";
    	input2.readonly = "readonly";
    	input2.value  = $('#child_name').val();
    	
    	var input3 = document.createElement("input");
    	input3.id = "standard_applied_for";
    	input3.readonly = "readonly";
    	input3.value  =	$('#standard').val();
    	
    	var input4 = document.createElement("input");
    	input4.id = "tuitionfee_max_eligible_amt";
    	input4.readonly = "readonly";
    	input4.value  = "3000";
    	
    	var input5 = document.createElement("input");
    	input5.id = "Amount applied";
    	input5.readonly = "readonly";
    	input5.value  = $('#tuition_fee_amount_applied').val();
    	
    	td1.appendChild(input1);
    	td2.appendChild(input2);
    	td3.appendChild(input3);
    	td4.appendChild(input4);
    	td5.appendChild(input5);
    	
    	tr.appendChild(td1);
    	tr.appendChild(td2);
    	tr.appendChild(td3);
    	tr.appendChild(td4);
    	tr.appendChild(td5);
    	
    	tuition_infosectbl.appendChild(tr)
    	document.getElementById("tuition_processTbl").style.display= "";
    	
    	$('#remarks').val("");
    	$('#tuition_processTbl').show();
	}
}


function submitMedicalAllowance(){
	debugger;
	var jsonArray = [];
	jsonArray.length = 0
	
	var maxLimit = $("#m_maximum_eligible_amount").val();
	var appliedAmount = $("#medical_amount_applied").val();
	
	if(appliedAmount > maxLimit)
	{
		alert("Applied amount is greater than maximum eligible mount.")
	}
	else
		{
			var jsonObj = {};
			jsonObj["amount_applied"] = $("#medical_amount_applied").val();
			jsonObj["applied_period"] = $("#medical_calender_year").val();
			jsonObj["illness"] = $("#medical_illness").val();
			jsonArray.push(jsonObj);
			
			$.ajax({
				url: 'addMedicalAllowance',
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

function submitFestivalAllowance(){
	debugger;
	var jsonArray = [];
	jsonArray.length = 0
	var jsonObj = {};
	
	jsonObj["lnloan_amt"] = $("#festival_amount_applied").val();
	jsonObj["festAdv_year"] = $("#festival_calender_year").val();
	jsonObj["festival_type"] = $("#festival_type").val();
	jsonObj["festival_name"] = $("#festival_name").val();
	jsonArray.push(jsonObj);
	
	$.ajax({
		url: 'addFestivalAllowance',
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

function submitAnnualPLAllowance(){
	debugger;
	var jsonArray = [];
	jsonArray.length = 0
	var jsonObj = {};
	
	jsonObj["plencash_year"] = $("#pl_encash_calender_year").val();
	jsonObj["eligible_days"] = $("#pl_encash_eligible_days").val();
	jsonObj["days_applied"] = $("#pl_encash_days_applied").val();
	jsonObj["festival_type"] = $("#pl_encash_festival_type").val();
/*	jsonObj["festival_name"] = $("#pl_encash_festival_name").val();
	jsonObj["amt_for_sanction"] = $("#pl_encash_days_applied").val();*/
	jsonArray.push(jsonObj);
	
	$.ajax({
		url: 'addPLAllowance',
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

function submitTuitionFeeAllowance(){
	debugger;
	var jsonArray = [];
	jsonArray.length = 0
	
	var maxLimit = $("#tuitionfee_max_eligible_amt").val();
	var appliedAmount = $("#tuition_fee_amount_applied").val();
	
	if(appliedAmount > maxLimit)
	{
		alert("Applied amount is greater than maximum eligible mount.")
	}
	else
	{
		var jsonObj = {};
		jsonObj["fee_year"] = $("#tuition_fee_financial_year").val();
		jsonObj["child_name"] = $("#child_name").val();
		jsonObj["standard"] = $("#standard").val();
		jsonObj["amount_applied"] = $("#tuition_fee_amount_applied").val();
	
		jsonArray.push(jsonObj);
		
		$.ajax({
			url: 'addTuitionFeeAllowance',
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

