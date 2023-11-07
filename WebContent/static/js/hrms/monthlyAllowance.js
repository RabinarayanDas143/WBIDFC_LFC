$("document")
		.ready(
				function() {
					debugger;
					employeeDetails();
					
					$("#monthly_allowances").change(function(){
						$("#newspaper_fields").hide();
						$("#food_beverages_fields").hide();
						$("#refreshments_fields").hide();
						$("#conveyance_fields").hide();
						$("#telephone_fields").hide();
						var monthlyAll = $("#monthly_allowances").val();
						if(monthlyAll == "newspaper")
						{
							$("#newspaper_fields").show();
						}
						else if(monthlyAll == "food_beverages")
						{
							$("#food_beverages_fields").show();
						}
						else if(monthlyAll == "refreshments")
						{
							$("#refreshments_fields").show();
						}
						else if(monthlyAll == "conveyance")
						{
							$("#conveyance_fields").show();
						}
						else if(monthlyAll == "telephone")
						{
							$("#telephone_fields").show();
						}
					})
					/*$('#purchase_dt').datepicker({
						dateFormat : 'd-M-yy'
					});*/
					

					/*$('#newspaper_month_year').datepicker({
						dateFormat : 'yyyy-mm'
					});*/
					
					$("#btn")
							.on(
									'click',
									function() {
										debugger;
									});
					
					
				})			
					
function employeeDetails(){
	debugger;
	getNewspaperLimit();
	getFoodBeverageLimit();
	getRefreshmentsLimit();
	getConveyanceLimit();
	getTelephoneLimit();
	
	$.ajax({
		url : 'viewMonthlyAllowance',
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

function getNewspaperLimit()
{
	debugger;
	$.ajax({
		url: 'getNewspaperLimit',
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

function getFoodBeverageLimit()
{
	debugger;
	$.ajax({
		url: 'getFoodBevLimit',
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

function getRefreshmentsLimit()
{
	debugger;
	$.ajax({
		url: 'getRefreshmentsLimit',
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

function getConveyanceLimit()
{
	debugger;
	$.ajax({
		url: 'getConveyanceLimit',
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

function getTelephoneLimit()
{
	debugger;
	$.ajax({
		url: 'getTelephoneLimit',
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


function ADDdetailsNewspaperAllowance() {
    	debugger;
    	var valid = true;
    	var yearMonth = $('#newspaper_applied_year_month').val();
    	var amount = $('#newspaper_amount_applied').val();
    	
		if((yearMonth == "") || (amount == "")){
			valid = false;
			alert('Fill all details');
			return valid;
		}		
    //	var nlimit = getNewspaperLimit();
    	
    	if(valid){
	    	var infosectbl = document.getElementById("newspaper_infosectbl");
	    	var tr = document.createElement("tr");
	    	var td =document.createElement("td");
	    	var td1 =document.createElement("td");
	    	var td2 =document.createElement("td");
	    	
	    	
	    	var input1 = document.createElement("input");
	    	input1.id = "allowance_applied_for";
	    	input1.readonly ="readonly";
	    	input1.value  = "Newspaper Allowance";     
	    	
	    	var input2 = document.createElement("input");
	    	input2.id = "n_maximum_eligible_amount";
	    	input2.readonly = "readonly";
	    	input2.value  = $('#newspaper_max_eligible_amount').val();
	    	
	    	var input3 = document.createElement("input");
	    	input3.id = "n_amount_applied";
	    	input3.readonly = "readonly";
	    	input3.value  =	$('#newspaper_amount_applied').val();	
	    	
	    	td.appendChild(input1);
	    	td1.appendChild(input2);
	    	td2.appendChild(input3);
	    	
	    	tr.appendChild(td);
	    	tr.appendChild(td1);
	    	tr.appendChild(td2);
	    	
	    	infosectbl.appendChild(tr)
	    	document.getElementById("newspaper_processTbl").style.display= "";
	    	
	    	$('#remarks').val("");
	    	$('#newspaper_processTbl').show();
    	}
 }




function ADDdetailsFoodAllowance() {
	debugger;
	
	var valid = true;
	var yearMonth = $('#food_beverages_applied_year_month').val();
	var amount = $('#food_beverages_amount_applied').val();
	
	if((yearMonth == "") || (amount == "")){
		valid = false;
		alert('Fill all details');
		return valid;
	}	
//	var foodLimit = getFoodBeverageLimit();
	
	if(valid){
	var infosectbl = document.getElementById("foodallowance_infosectbl");
	var tr = document.createElement("tr");
	var td =document.createElement("td");
	var td1 =document.createElement("td");
	var td2 =document.createElement("td");
	
	
	var input1 = document.createElement("input");
	input1.id = "allowance_applied_for";
	input1.readonly = "readonly";
	input1.value  = "Food & Beverages Allowance";     
	
	var input2 = document.createElement("input");
	input2.id = "f_maximum_eligible_amount";
	input2.readonly = "readonly";
	input2.value = $('#food_max_eligible_amount').val();
	
	var input3 = document.createElement("input");
	input3.id = "f_amount_applied";
	input3.readonly = "readonly";
	input3.value = $('#food_beverages_amount_applied').val();
	
	td.appendChild(input1);
	td1.appendChild(input2);
	td2.appendChild(input3);
	
	tr.appendChild(td);
	tr.appendChild(td1);
	tr.appendChild(td2);
	
	infosectbl.appendChild(tr)
	document.getElementById("foodAllowance_processTbl").style.display= "";
	
	$('#remarks').val("");
	$('#foodAllowance_processTbl').show();
	}
}	



function ADDdetailsRefreshmentAllowance() {
	debugger;
	
	var valid = true;
	var yearMonth = $('#refreshments_applied_year_month').val();
	var amount = $('#refreshments_amount_applied').val();
	
	if((yearMonth == "") || (amount == "")){
		valid = false;
		alert('Fill all details');
		return valid;
	}
//	var rlimit = getRefreshmentsLimit();
	
	if(valid){
	var infosectbl = document.getElementById("refreshments_infosectbl");
	var tr = document.createElement("tr");
	var td =document.createElement("td");
	var td1 =document.createElement("td");
	var td2 =document.createElement("td");
	
	
	var input1 = document.createElement("input");
	input1.id = "allowance_applied_for";
	input1.readonly = "readonly";
	input1.value  = "Refreshment Allowance";    
	
	var input2 = document.createElement("input");
	input2.id = "r_maximum_eligible_amount";
	input2.readonly = "readonly";
	input2.value  = $('#refreshments_max_eligible_amount').val();
	
	var input3 = document.createElement("input");
	input3.id = "r_amount_applied";
	input3.readonly = "readonly";
	input3.value  =	$('#refreshments_amount_applied').val();
	
	td.appendChild(input1);
	td1.appendChild(input2);
	td2.appendChild(input3);
	
	tr.appendChild(td);
	tr.appendChild(td1);
	tr.appendChild(td2);
	
	infosectbl.appendChild(tr)
	document.getElementById("refreshments_processTbl").style.display= "";
	
	$('#remarks').val("");
	$('#refreshments_processTbl').show();
	}
}	


function ADDdetailsConveyanceAllowance() {
	debugger;
	
	var valid = true;
	var yearMonth = $('#conveyance_applied_year_month').val();
	var amount = $('#conveyance_amount_applied').val();
	if((yearMonth == "") || (amount == "")){
		valid = false;
		alert('Fill all details');
		return valid;
	}
//	var climit = getConveyanceLimit();
	
	if(valid){
	var infosectbl = document.getElementById("conveyance_infosectbl");
	var tr = document.createElement("tr");
	var td =document.createElement("td");
	var td1 =document.createElement("td");
	var td2 =document.createElement("td");
	
	
	var input1 = document.createElement("input");
	input1.id = "allowance_applied_for";
	input1.readonly = "readonly";
	input1.value  = "Conveyance Allowance"; 
	
	var input2 = document.createElement("input");
	input2.id = "c_maximum_eligible_amount";
	input2.readonly = "readonly";
	input2.value  = $('#conveyance_max_eligible_amount').val();
	
	var input3 = document.createElement("input");
	input3.id = "c_amount_applied";
	input3.readonly = "readonly";
	input3.value  =	$('#conveyance_amount_applied').val();				
	
	td.appendChild(input1);
	td1.appendChild(input2);
	td2.appendChild(input3);
	
	tr.appendChild(td);
	tr.appendChild(td1);
	tr.appendChild(td2);
	
	infosectbl.appendChild(tr)
	document.getElementById("conveyance_processTbl").style.display= "";
	
	$('#remarks').val("");
	$('#processTbl').show();
	}
}	


function ADDdetailsTelephoneAllowance() {
	debugger;
	
	var valid = true;
	var yearMonth = $('#telephone_applied_year_month').val();
	var amount = $('#telephone_amount_applied').val();
	if((yearMonth == "") || (amount == "")){
		valid = false;
		alert('Fill all details');
		return valid;
	}
//	var tlimit = getTelephoneLimit();
	
	if( valid ){
	var infosectbl = document.getElementById("telephone_infosectbl");
	var tr = document.createElement("tr");
	var td =document.createElement("td");
	var td1 =document.createElement("td");
	var td2 =document.createElement("td");
	
	
	var input1 = document.createElement("input");
	input1.id = "allowance_applied_for";
	input1.readonly = "readonly";
	input1.value  = "Telephone Allowance"; 
	
	var input2 = document.createElement("input");
	input2.id = "t_maximum_eligible_amount";
	input2.readonly = "readonly";
	input2.value  = $('#tele_max_amount_applied').val();
	
	var input3 = document.createElement("input");
	input3.id = "t_amount_applied";
	input3.readonly = "readonly";
	input3.value = $('#telephone_amount_applied').val();
	
	td.appendChild(input1);
	td1.appendChild(input2);
	td2.appendChild(input3);
	
	tr.appendChild(td);
	tr.appendChild(td1);
	tr.appendChild(td2);
	
	infosectbl.appendChild(tr)
	document.getElementById("telephone_processTbl").style.display= "";
	
	$('#remarks').val("");
	$('#processTbl').show();
	}
}	




function submitNewspaperAllowance(){
	debugger;
	var jsonArray = [];
	jsonArray.length = 0
	
	var maxLimit = $("#newspaper_max_eligible_amount").val();
	var appliedAmount = $("#newspaper_amount_applied").val();
	
	if(appliedAmount > maxLimit)
	{
		alert("Applied amount is greater than maximum eligible mount.")
	}
	else
	{	var jsonObj = {};
		jsonObj["amount_applied"] = $("#newspaper_amount_applied").val();
		jsonObj["applied_year_month"] = $("#newspaper_applied_year_month").val();
		jsonArray.push(jsonObj);
		
		$.ajax({
			url: 'addNewspaperAllowance',
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

function submitFoodAllowance(){
	debugger;
	var jsonArray = [];
	jsonArray.length = 0
	
	var valid = true;
	var maxLimit = $("#food_max_eligible_amount").val();
	var appliedAmount = $("#food_beverages_amount_applied").val();
	
	if(appliedAmount > maxLimit)
	{
		alert("Applied amount is greater than maximum eligible mount.")
	}
	else
	{	
		var jsonObj = {};
		jsonObj["amount_applied"] = $("#food_beverages_amount_applied").val();
		jsonObj["applied_year_month"] = $("#food_beverages_applied_year_month").val();
		jsonArray.push(jsonObj);
		
		$.ajax({
			url: 'addFoodAllowance',
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

function submitRefreshmentAllowance(){
	debugger;
	var jsonArray = [];
	jsonArray.length = 0

	var valid = true;
	var maxLimit = $("#refreshments_max_eligible_amount").val();
	var appliedAmount = $("#refreshments_amount_applied").val();
	
	if(appliedAmount > maxLimit)
	{
		alert("Applied amount is greater than maximum eligible mount.")
	}
	else
	{
		var jsonObj = {};
		jsonObj["amount_applied"] = $("#refreshments_amount_applied").val();
		jsonObj["applied_year_month"] = $("#refreshments_applied_year_month").val();
		jsonArray.push(jsonObj);
		$.ajax({
			url: 'addRefreshmentAllowance',
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

function submitConveyanceAllowance(){
	debugger;
	var jsonArray = [];
	jsonArray.length = 0
	
	var maxLimit = $("#conveyance_max_eligible_amount").val();
	var appliedAmount = $("#conveyance_amount_applied").val();
	
	if(appliedAmount > maxLimit)
	{
		alert("Applied amount is greater than maximum eligible mount.")
	}
	else
	{
		var jsonObj = {};
		jsonObj["amount_applied"] = $("#conveyance_amount_applied").val();
		jsonObj["applied_year_month"] = $("#conveyance_applied_year_month").val();
		jsonArray.push(jsonObj);
		
		$.ajax({
			url: 'addConveyanceAllowance',
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

function submitTelephoneAllowance(){
	debugger;
	var jsonArray = [];
	jsonArray.length = 0
	
	var maxLimit = $("#tele_max_amount_applied").val();
	var appliedAmount = $("#telephone_amount_applied").val();
	
	if(appliedAmount > maxLimit)
	{
		alert("Applied amount is greater than maximum eligible mount.")
	}
	else
	{
		var jsonObj = {};
		jsonObj["amount_applied"] = $("#telephone_amount_applied").val();
		jsonObj["applied_year_month"] = $("#telephone_applied_year_month").val();
		jsonArray.push(jsonObj);
		
		$.ajax({
			url: 'addTelephoneAllowance',
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