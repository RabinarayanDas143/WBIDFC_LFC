$("document").ready(function() {
	debugger;
	// alert("Inside Js Class");
	
	
	
	lfcDetails();
	dropDown();
	encashmentLeaveDropDown();
	internalAuditorShow();
	csAdminShow();
	SubmitInfo();
	lfcreport();
	internalAuditReport();
	csReport();
	userReport();
	internalAuditorAdminShow();
	csAdminLfcRequest();
	depenedentList();
	hrAcceptReject();


	$(".selectall").click(function(event) {
		if (this.checked) {
			$('.checkbox1').each(function() {
				this.checked = true;
			})
		} else {
			$('.checkbox1').each(function() {
				this.checked = false;
			})
		}
	});

	$('#distancepermis').on('click', function() {
		$("#modalWindow").modal('show');
	})





	var auditError = false;

	function validate_remark() {
		var val = $("#Auditremark").val();
		var exp = /[A-Za-z]/

		if (val == '') {
			$('#auditError').show();
			$('#auditError').html("required");
			auditError = false;
		} else if (!exp.test(val)) {
			$('#auditError').show();
			$('#auditError').html("Not allowed");
			auditError = false;
		} else {
			$('#auditError').hide();
			auditError = true;
		}
	}
	$('#auditError').keyup(function() {
		validate_remark();
	});


	
});

function showLoader(location) {
	$('#Loader').show();
}

function hideLoader(location) {
	$('#Loader').hide();
}

function checknumberofDays() {
	debugger;
	var leavebalance = $('#Encasmentleavebalance').val();
	var balance = parseInt(leavebalance);
	var leaveCount = $('#Encasmentleavecount').val();
	var count = parseInt(leaveCount);
	if (count < balance && count <= 30) {
		$('#Encasmentleavecount').val(leaveCount);
	} else {
		alert("Insufficient Leave balance");
		event.returnValue = false;
		document.getElementById("Encasmentleavecount").value = "";
	}

}

function lfcDetails() {
	debugger;
	$.ajax({
		url: 'lfcDetailsPost',
		type: 'POST',
		data: {
			CSRFToken: $("meta[name='_csrf']").attr("content"),
		},
		// async : false,
		dataType: 'json',
		success: function(data) {
			 
			if (data.status == "FOUND") {
				$('#empcd').val(data.body.id);
				$('#emply_name').val(data.body.name);
				$('#emply_desig').val(data.body.designation);
				if (data.body.classification == 'Contractual') {
					$('#BA').val('5 years');
					$('#leaveEncashBlock').val('5 years');
				} else {
					$('#BA').val('3 years');
					$('#leaveEncashBlock').val('3 years');
				}
			}
		},
		error: function(error) {
			console.log(error)
		}

	});
}



function dateChangeFunction() {
	debugger;
	var blockApplied = $('#BA').val();
	var fromDate = $('#frmdatepicker').val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("blockApplied", blockApplied);
	formData.append("fromDate", fromDate);
	$.ajax({
		url: 'dateChangeForBlockApplied.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			 
			if (data.status == "SUCCESS") {
				$('#todatepicker').val(data.body);
			}
		},
		error: function(error) {
			console.log("error");
		}
	})
}

function dropDown() {
	debugger;

	$.ajax({
		url: 'lfcLeaveTypePost',
		type: 'POST',
		data: {
			CSRFToken: $("meta[name='_csrf']").attr("content"),
		},
		dataType: 'json',
		success: function(data) {
			 
			var drop = data.body;
			$('#leave_type').empty();

			$("#leave_type").append($('<option value="" selected="selected" >-- Select leave --</option>'));

			$.each(drop, function(key, value) {
				$("#leave_type").append(
					$("<option></option>").attr("value", key).text(value));
			})

		},
		error: function(error) {
			console.log(error);
		}
	})
}

function encashmentLeaveDropDown() {
	debugger;
	$.ajax({
		url: 'encashmentLeaveDropDownPost',
		type: 'POST',
		data: {
			CSRFToken: $("meta[name='_csrf']").attr("content"),
		},
		dataType: 'json',
		success: function(data) {

			 
			var drop = data.body;
			$('#EncasmentleaveType').empty();

			$("#EncasmentleaveType").append($('<option value="" selected="selected" >-- Select leave --</option>'));

			$.each(drop, function(key, value) {
				$("#EncasmentleaveType").append(
					$("<option></option>").attr("value", key).text(value));
			})
		},
		error: function(error) {
			console.log(error);
		}
	})
}


function depenedentList() {
	debugger;
	$.ajax({
		url: 'depenedentListPost',
		type: 'POST',
		data: {
			CSRFToken: $("meta[name='_csrf']").attr("content"),
		},
		dataType: 'json',
		success: function(data) {
			debugger;
			 var j=1;
			var drop = data.body;
			$('#dependentTableBody').empty();
			$.each(drop, function(i, item,j) {
				debugger;
				$("<tr>").html("<td style='border:1px solid black'><input type='checkbox' name = 'checkboxName'  class='checkbox1' value='"
					+ i
					+ "'><span style = 'display:none' id = 'idUnique"
					+ i
					+ "'>"
					+ drop[i].id + "</span></td><td style='border:1px solid black'><span id = 'dsrid"
					+ i
					+ "'>"
					+ i
					+ "</span></td>"
					+ "<td style='border:1px solid black'><span id = 'dependentnameid" + i + "'>" + [drop[i].dependentname] + "</span></td>"
					+ "<td style='border:1px solid black'><span id = 'relationid" + i + "'>" + [drop[i].relation] + "</span></td>"
					+ "<td style='border:1px solid black'><span id = 'dobid" + i + "'>" + [drop[i].dob] + "</span></td>"
					+ "<td style='border:1px solid black'><span id = 'genderid" + i + "'>" + [drop[i].gender] + "</span></td>"
					+ "<td style='border:1px solid black'><span id = 'annualincomeid" + i + "'>" + [drop[i].annualincome] + "</span></td>"
					+ "<td style='border:1px solid black'><span id = 'occupationid" + i + "'>" + [drop[i].occupation] + "</span></td>").appendTo('#dependentTableBody');
					j++;
			});
		},
		error: function(error) {
			console.log(error);
		}
	})
}



function leaveTypeFunction() {
	debugger;
	var lvtype = $('#leave_type').val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("lvtype", lvtype);

	$.ajax({
		url: 'leaveAvailable.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {

			 
			if (data.status == "FOUND") {
				console.log(data.body);
				$('#count').val(data.body);
			}
		},
		error: function(error) {
			console.log("error");
		}
	})
}

function encashmentLeaveTypeFunction() {
	var encashmentLeaveType = $('#EncasmentleaveType').val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("encashmentLeaveType", encashmentLeaveType);
	$.ajax({
		url: 'encashmentLeaveCount.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {

			if (data.status == "FOUND") {
				 
				$('#Encasmentleavebalance').val(data.body);
			}
		},
		error: function(error) {
			console.log("error");
		}
	})
}


function fieldCheck() {
	debugger;
	var lfcfromdate = $('#frmdatepicker').val();
	var lfctodate = $('#todatepicker').val();
	var leaveType = $('#leave_type').val();
	var leavefromdate = $('#fodatepic').val();
	var leaveTodate = $('#todatepic').val();
	var placeDestination = $('#destinplace').val();
	var amountAdavance = $('#amount_advance').val();
	var numberOfDays = $('#NOD').val();
	var EncasmentleaveType = $('#EncasmentleaveType').val();
	var travelfromdate = $('#travelfromdate').val();
	var traveltodate = $('#traveltodate').val();
	var Encasmentleavecount = $('#Encasmentleavecount').val();
	
	// changes done by sp {
	var leaveAvailable =  $('#LA').val();
	
	
	var leaveEncashmentNeeded =  $('#pl_encashment').val();
	
	var placeoforigination = $('#originplace').val();
	//alert("placeoforigination in field check  - "+placeoforigination);
	
	//}
	
//	alert(leaveEncashmentNeeded+ "-----yes or no value of this js - leaveAvailable --- "+leaveAvailable);


	if (lfcfromdate == "") {
		alert("Select LFC from Date");
		return false;
	}
	if (lfctodate == "") {
		alert("Select LFC To date");
		return false;
	}
	
	if(leaveAvailable == "Yes")
	{
		if (leaveType == "") {
			alert("Select Leave type");
			return false;
		}
		
		if (leavefromdate == "") {
			alert("Select Leave from date");
			return false;
		}
		if (leaveTodate == "") {
			alert("Select Leave to date");
			return false;
		}
		

	}
	
	if(leaveEncashmentNeeded == "Yes")
	{
		
		if (EncasmentleaveType == "") {
			alert("Enter EncasmentleaveType");
			return false;
		}
		
		if (Encasmentleavecount == "") {
			alert("Enter Encasmentleavecount");
			return false;
		}
		
		
		
	}
	
	
	

	if (placeDestination == "") {
		alert("Select Place of Destination");
		return false;
	}
	
	if(placeoforigination == ""){
		alert("Select Place of Origination");
		return false;
		
	}
	if (amountAdavance == "") {
		alert("Enter Amount of advance");
		return false;
	}
	if (numberOfDays == "") {
		alert("Enter Number of Days");
		return false;
	}
	
	if (travelfromdate == "") {
		alert("Enter Date of Commencement");
		return false;
	}
	if (traveltodate == "") {
		alert("Enter Date of Compltion");
		return false;
	}
	
	return true;
}

function surrenderDataCheck() {
	debugger;

// changes done by sp

	var lfcfromdate = $('#frmdatepicker').val();
	var lfctodate = $('#todatepicker').val();
	var EncasmentleaveType = $('#EncasmentleaveType').val();
	var Encasmentleavecount = $('#Encasmentleavecount').val();


	if (lfcfromdate == "") {
		alert("Select LFC from Date");
		return false;
	}
	if (lfctodate == "") {
		alert("Select LFC To date");
		return false;
	}
	if (EncasmentleaveType == "") {
		alert("Enter EncasmentleaveType");
		return false;
	}

	if (Encasmentleavecount == "") {
		alert("Enter Encasmentleavecount");
		return false;
	}
	return true;

}


function saveinfo() {
	debugger;

	var jsonArray = [];
	jsonArray.length = 0;

	var jsonArray1 = [];
	jsonArray1.length = 0;

	var jsonArrayList = new DependentListChekJson();


	if (document.getElementById('encashmentApply').checked) {
		var submission = "encashmentApply";
	} else if (document.getElementById('encashmentSurrender').checked) {
		var submission = "encashmentSurrender";
	}
	 
	var jsonObj = {};
	jsonObj["empcd"] = $('#empcd').val();
	jsonObj["blockapplied"] = $('#BA').val();
	jsonObj["leave_type"] = $('#leave_type').val();
	jsonObj["fromdate"] = $('#frmdatepicker').val();
	jsonObj["todatepicker"] = $('#todatepicker').val();
	jsonObj["destinationplace"] = $('#destinplace').val();
	jsonObj["originationplace"] = $('#originplace').val();
	
	jsonObj["amountAdvance"] = $('#amount_advance').val();
	jsonObj["leaveEncashBlock"] = $('#leaveEncashBlock').val();
	jsonObj["fdate"] = $('#fodatepic').val();
	jsonObj["tdate"] = $('#todatepic').val();
	jsonObj["numberOfDays"] = $('#NOD').val();
	jsonObj["EncasmentleaveType"] = $('#EncasmentleaveType').val();
	jsonObj["travelfromdate"] = $('#travelfromdate').val();
	jsonObj["traveltodate"] = $('#traveltodate').val();
	jsonObj["Encasmentleavecount"] = $('#Encasmentleavecount').val();
	
	// changes done by sp 
	
	

	jsonArray.push(jsonObj);

	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("dependentvalue", JSON.stringify(jsonArrayList));
	formData.append("data", JSON.stringify(jsonArray));

	var jsonObj1 = {};
	jsonObj1["empcd"] = $('#empcd').val();
	jsonObj1["blockapplied"] = $('#BA').val();
	jsonObj1["fromdate"] = $('#frmdatepicker').val();
	jsonObj1["todatepicker"] = $('#todatepicker').val();
	jsonObj1["EncasmentleaveType"] = $('#EncasmentleaveType').val();
	jsonObj1["Encasmentleavecount"] = $('#Encasmentleavecount').val();

	jsonArray1.push(jsonObj1)

	var surrenderformData = new FormData();
	surrenderformData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	surrenderformData.append("dependentvalue", JSON.stringify(jsonArrayList));
	surrenderformData.append("SurrenderData", JSON.stringify(jsonArray1));

	if (submission == "encashmentApply") {
		if (fieldCheck()) {
			$.ajax({
				url: 'saveInfo.do',
				data: formData,
				dataType: "JSON",
				cache: false,
				processData: false,
				contentType: false,
				type: 'POST',
				beforeSend: function() {
					showLoader();
				},
				success: function(data) {
					hideLoader();
					alert(data.message);
					window.location.reload();
				},
				error: function(error) {
					console.log("error in ajaxx  :" + error)
					hideLoader();
					alert(error);
				}
			})
		}
	} else if (surrenderDataCheck()) {
		$.ajax({
			url: 'saveSurrenderData.do',
			data: surrenderformData,
			dataType: "JSON",
			cache: false,
			processData: false,
			contentType: false,
			type: 'POST',
			beforeSend: function() {
				showLoader();
			},
			success: function(data) {
				hideLoader();
				alert(data.message);
				window.location.reload();
			},
			error: function(error) {
				console.log("error in ajaxx  :" + error)
				hideLoader();
				alert(error);
			}
		})
	}
}

function validatetext() {
	var textinput = $('#destinplace').val();
	textinput = textinput.replace(/[^A-Za-z]/, "");
	document.getElementById("destinplace").value = textinput;
}



function hrvalidatetext() {
	var hrspanerror = false;
	var val = $('#hradminremark').val();
	var exp = /^[A-Za-z]*$/g;
	if (val == '') {
		$('#hrspanerror').show();
		$('#hrspanerror').html("required");
		hrspanerror = false;
	}
	else if (!exp.test(val)) {
		$('#hrspanerror').show();
		$('#hrspanerror').html("Not allowed");
		hrspanerror = false;
	}
	else {
		$('#hrspanerror').hide();
		hrspanerror = true;
	}
}



function hrAdminReportValid() {
	 
	var fieldvalue = $('#hradminremark');
	var hrspanerror = false;
	var exp = /^[a-zA-Z]+$/;
	if (fieldvalue == "") {
		$('#hrspanerror').show();
		$('#hrspanerror').html("required");
		hrspanerror = false;
	}
	else if (!exp.test(fieldvalue)) {

		$('#hrspanerror').show();
		$('#hrspanerror').html("required");
		hrspanerror = false;
	}
	else {
		$('#hrspanerror').hide();
		hrspanerror = true;
	}
}

 

function SubmitInfo() {
	debugger;

	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));


	$.ajax({
		url: 'lfcHrAdmin.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			 
			if (data.status == "SUCCESS") {

				$('#table').empty();

				for (var i = 0; i < data.body.length; i++) {
					if (data.body[i].dependent != null || data.body[i].occupation != null || data.body[i].annualIncome != null) {
						var str = data.body[i].dependent;
						var depend = str.replaceAll(",", "<br>")
						var str1 = data.body[i].occupation;
						var occupation = str1.replaceAll(",", "<br><br>")
						var str2 = data.body[i].annualIncome;
						var annualIncome = str2.replaceAll(",", "<br><br>")
					}

					$('#table').append("<tr>"

						+ "<td nowrap='nowrap'>" + data.body[i].id + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].name + "</td>"
						+ "<td nowrap='nowrap'>" + depend + "</td>"
						+ "<td nowrap='nowrap'>" + occupation + "</td>"
						+ "<td nowrap='nowrap'>" + annualIncome + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leaveTypeStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leavefromDateStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leavetoDateStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].numberofDays + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].placeofDestination + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].amountofAdvance + "</td>"
						
						+ "<td><input type='number' id='Auditamount" + i + "' maxlength='10'><span id = 'auditamountError'></span></td>"
						+ "<td><input type='number' id='AuditamountLeaveEncash" + i + "' maxlength='10'><span id = 'AuditamountLeaveEncash'></span></td>"
						
						+ "<td><input type='text' id='hradminremark" + i + "' maxlength='50'  ></td>"
						+ "<td><a href='javascript:hrModalData(" + data.body[i].id + "," + i + ")'>view</a></td>"
						+ "<td style='white-space: nowrap'><button class='btn btn-success' value=" + data.body[i].tranId + " id='acceptButton' onclick='acceptfun(" + data.body[i].tranId + "," + i + ")'> Accept </button>&nbsp&nbsp"
						+ "<button class='btn btn-danger' value=" + data.body[i].tranId + " id='rejectButton' onclick='rejectfun(" + data.body[i].tranId + "," + i + ")'>Reject</button>&nbsp&nbsp</td>"
						+ "</tr>"
					)



				}
			}
		},
		error: function(error) {
			console.log("error");
		}
	})

}

function rejectfun(tranid, i) {
	debugger;
	var rejectval = $('#rejectButton').val();
	var hradminremark = $('#hradminremark' + i).val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("rejectval", rejectval);
	formData.append("hradminremark", hradminremark);
	if (hradminremark == "") {
		alert("Please provide the remark for rejection");
		return false;
	}
	else {
		$.ajax({
			url: 'rejectbutton.do',
			data: formData,
			type: 'POST',
			cache: false,
			contentType: false,
			processData: false,
			success: function(data) {
				if (data.status == "SUCCESS") {
					alert(data.message);
					window.location.reload();
				}
				else {
					alert(data.message);
				}
			},
			error: function(error) {
				alert(error);
			}
		})
		return true;
	}

}


function acceptfun(tranid, i) {
	debugger;
	var acceptval = $('#acceptButton').val();
	var hradminremark = $('#hradminremark' + i).val();
	
	var Auditamount = $('#Auditamount' + i).val();
	var AuditamountLeaveEncash = $('#AuditamountLeaveEncash' + i).val();
	
	
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("acceptval", tranid);
	formData.append("hradminremark", hradminremark);
	formData.append("Auditamount", Auditamount);
	formData.append("AuditamountLeaveEncash", AuditamountLeaveEncash);


	
	
	$.ajax({
		url: 'acceptbutton.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			if (data.status == "SUCCESS") {
				alert(data.message);
				window.location.reload();
			}
		},
		error: function(error) {
			alert(error);
		}
	})
}


function hrModalData(empId, rownum) {
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("empId", empId);
	$.ajax({
		url: 'hrAdminModalBoxData.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			 
			if (data.status == "FOUND") {

				$("#empId").val(data.body.id);
				$("#empName").val(data.body.name);
				$("#blockPeriod").val(data.body.leaveEncashmentBlock);
				$("#blockStartDate").val(data.body.lfcFromDate);
				$("#blockEndDate").val(data.body.lfctoDate);
				$("#leaveType").val(data.body.leaveTypeStr);
				$("#leavefromDate").val(data.body.leavefromDateStr);
				$("#leaveToDate").val(data.body.leavetoDateStr);
				$("#numberOfDays").val(data.body.numberofDays);
				$("#destination").val(data.body.placeofDestination);
				$("#dateOfCommencement").val(data.body.commencementFromDate);
				$("#dateOfCompletion").val(data.body.complitionToDate);
				$("#amountOfAdvance").val(data.body.amountofAdvance);
				$("#encashmentLeaveSought").val(data.body.encashmentLeaveCount);
				$("#origination").val(data.body.placeofOrigination);

				$('#hrAdminModals').empty();

				for (var i = 0; i < data.tablebody.length; i++) {
					$('#hrAdminModals').append(
						"<tr>"

						+ "<td nowrap='nowrap'>" + data.tablebody[i].name + "</td>"
						+ "<td nowrap='nowrap'>" + data.tablebody[i].relation + "</td>"
						+ "<td nowrap='nowrap'>" + data.tablebody[i].dob + "</td>"
						+ "<td nowrap='nowrap'>" + data.tablebody[i].annualIncome + "</td>"
						+ "<td nowrap='nowrap'>" + data.tablebody[i].occupation + "</td>"
						+ "</tr>"
					);
				}
				$("#exampleModalLong").modal('show');
			}
		},
		error: function(error) {
			alert(error);
		}
	})

}

function internalAuditorShow() {
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	$.ajax({
		url: 'internalAuditor.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			 
			if (data.status == "SUCCESS") {
				$('#table_Auditor').empty();
				for (var i = 0; i < data.body.length; i++) {
					if (data.body[i].dependent != null || data.body[i].occupation != null || data.body[i].annualIncome != null) {
						var str = data.body[i].dependent;
						var depend = str.replaceAll(",", "<br>");
						var str1 = data.body[i].occupation;
						var occupation = str1.replaceAll(",", "<br><br>")
						var str2 = data.body[i].annualIncome;
						var annualIncome = str2.replaceAll(",", "<br><br>")
					}
					$('#table_Auditor').append(
						"<tr>"
						+ "<td nowrap='nowrap'>" + data.body[i].id + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].name + "</td>"
						+ "<td nowrap='nowrap'>" + depend + "</td>"
						+ "<td nowrap='nowrap'>" + occupation + "</td>"
						+ "<td nowrap='nowrap'>" + annualIncome + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leaveTypeStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leavefromDateStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leavetoDateStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].numberofDaysStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].placeofDestination + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].amountofAdvanceStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].hrRemark + "</td>"
						+ "<td><input type='text' id='Auditremark" + i + "' maxlength='50'><span id = 'auditError'></span></td>"
						+ "<td><a href='javascript:intenalAuditorModalData(" + data.body[i].id + "," + i + ")'>view</a></td>"
						+ "<td style='white-space: nowrap'><button class='btn  btn-success' value=" + data.body[i].tranId + " id='InternalacceptButton' onclick='Internalacceptfun(" + data.body[i].tranId + "," + i + ")'> Accept </button>&nbsp&nbsp"
						+ "<button class='btn btn-danger' value=" + data.body[i].tranId + " id='InternalrejectButton' onclick='Internalrejectfun(" + data.body[i].tranId + "," + i + ")'>Reject</button></td>"
						+ "</tr>"

					)
				}
			}
		},
		error: function(error) {
			console.log(error);
		}
	})
}

function Internalacceptfun(tranid, i) {
	debugger;
	var acceptval = $('#InternalacceptButton').val();
	var Auditremark = $('#Auditremark' + i).val();
	
	var Auditamount = $('#Auditamount' + i).val();
	var AuditamountLeaveEncash = $('#AuditamountLeaveEncash' + i).val();

	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("acceptval", tranid);
	formData.append("Auditamount", Auditamount);
	formData.append("AuditamountLeaveEncash", AuditamountLeaveEncash);
	formData.append("Auditremark", Auditremark);
	
	$.ajax({
		url: 'internalacceptbutton.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			if (data.status == "SUCCESS") {
				alert(data.message);
				window.location.reload();
			}
		},
		error: function(error) {
			alert(error);
		}
	})
}


function Internalrejectfun(tranid, i) {
	debugger;
	var rejectval = $('#InternalrejectButton').val();
	var Auditremark = $('#Auditremark' + i).val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("rejectval", tranid);
	formData.append("Auditremark", Auditremark);
	if (Auditremark == "") {
		alert("Please provide the remark for rejection");
		return false;
	}
	else {
		$.ajax({
			url: 'internalrejectbutton.do',
			data: formData,
			type: 'POST',
			cache: false,
			contentType: false,
			processData: false,
			success: function(data) {
				if (data.status == "SUCCESS") {
					alert(data.message);
					window.location.reload();
				} else {
					alert(data.message);
				}
			},
			error: function(error) {
				alert(error);
			}
		})
		return true;
	}
}


function intenalAuditorModalData(empId, i) {
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("empId", empId);

	$.ajax({
		url: 'internalAdminModalBox.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			if (data.status == "FOUND") {
				$("#empId").val(data.body[0].id);
				$("#empName").val(data.body[0].name);
				$("#blockPeriod").val(data.body[0].leaveEncashmentBlock);
				$("#blockStartDate").val(data.body[0].lfcFromDate);
				$("#blockEndDate").val(data.body[0].lfctoDate);
				$("#leaveType").val(data.body[0].leaveTypeStr);
				$("#leavefromDate").val(data.body[0].leavefromDateStr);
				$("#leaveToDate").val(data.body[0].leavetoDateStr);
				$("#numberOfDays").val(data.body[0].numberofDays);
				$("#destination").val(data.body[0].placeofDestination);
				$("#dateOfCommencement").val(data.body[0].commencementFromDate);
				$("#dateOfCompletion").val(data.body[0].complitionToDate);
				$("#amountOfAdvance").val(data.body[0].amountofAdvance);
				$("#hrRemark").val(data.body[0].hrRemark);
				$("#origination").val(data.body[0].placeofOrigination);
				$("#encashmentLeave").val(data.body[0].encashmentLeaveCount);

				$('#internalAuditorModals').empty();
				for (var i = 0; i < data.tablebody.length; i++) {
					$('#internalAuditorModals').append(
						"<tr>"
 						+ "<td nowrap='nowrap'>" + data.tablebody[i].name + "</td>"
						+ "<td nowrap='nowrap'>" + data.tablebody[i].relation + "</td>"
						+ "<td nowrap='nowrap'>" + data.tablebody[i].dob + "</td>"
						+ "<td nowrap='nowrap'>" + data.tablebody[i].annualIncome + "</td>"
						+ "<td nowrap='nowrap'>" + data.tablebody[i].occupation + "</td>"
						+ "</tr>"
					);
				}
			}
	       $("#exampleModalLong").modal('show');
		},
		error: function(error) {
			alert(error);
		}
	})

}



function csAdminShow() {
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	$.ajax({
		url: 'csAdminDate.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			 
			if (data.status == "SUCCESS") {
				$('#table_cs').empty();
				var response = data.body;
				 
				for (var i = 0; i < data.body.length; i++) {
					if (data.body[i].dependent != null || data.body[i].occupation != null || data.body[i].annualIncome != null) {
						var str = data.body[i].dependent;
						var depend = str.replaceAll(",", "<br>");
						var str1 = data.body[i].occupation;
						var occupation = str1.replaceAll(",", "<br><br>")
						var str2 = data.body[i].annualIncome;
						var annualIncome = str2.replaceAll(",", "<br><br>")
					}
					$('#table_cs').append(
						"<tr>"
						+ "<td nowrap='nowrap' id='leID" + i + "'>" + data.body[i].id + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].name + "</td>"
						+ "<td nowrap='nowrap'>" + depend + "</td>"
						+ "<td nowrap='nowrap'>" + occupation + "</td>"
						+ "<td nowrap='nowrap'>" + annualIncome + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leaveTypeStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leavefromDateStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leavetoDateStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].numberofDaysStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].placeofDestination + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].amountofAdvanceStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].internalAuditRemark + "</td>"
						+ "<td><input type='text' id='CSremark" + i + "' maxlength='50'></td>"
						+ "<td><a href='javascript:csModalData(" + data.body[i].id + "," + i + ")'>view</a></td>"
						+ "<td style='white-space: nowrap'><button class='btn  btn-success' value=" + data.body[i].tranId + " id='CSacceptButton' onclick='CSacceptfun(" + data.body[i].id + "," + data.body[i].tranId + "," + i + ")'> Accept </button>&nbsp&nbsp"
						+ "<button class='btn btn-danger' value=" + data.body[i].tranId + " id='CSrejectButton' onclick='CSrejectfun(" + data.body[i].tranId + "," + i + ")'>Reject</button></td>"
						+ "</tr>"

					)
				}
			}
		},
		error: function(error) {
			console.log(error);
		}
	})
}

function CSacceptfun(empCode, tranid, i) {
	debugger;
	var acceptval = $('#CSacceptButton').val();
	var cSremark = $('#CSremark' + i).val();
	//var empCode = $('#leID'+i).val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("acceptval", tranid);
	formData.append("cSremark", cSremark);
	formData.append("empCode", empCode);

	$.ajax({
		url: 'CSacceptbutton.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			if (data.status == "SUCCESS") {
				alert(data.message);
				window.location.reload();
			}
		},
		error: function(error) {
			alert(error);
		}
	})
}

function CSrejectfun(tranid, i) {
	debugger;
	var rejectval = $('#CSrejectButton').val();
	var cSremark = $('#CSremark' + i).val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("rejectval", tranid);
	formData.append("cSremark", cSremark);
	if (cSremark == "") {
		alert("Please provide the remark for rejection");
		return false;
	}
	else {
		$.ajax({
			url: 'CSrejectbutton.do',
			data: formData,
			type: 'POST',
			cache: false,
			contentType: false,
			processData: false,
			success: function(data) {
				if (data.status == "SUCCESS") {
					alert(data.message);
					window.location.reload();
				} else {
					alert(data.message);
				}
			},
			error: function(error) {
				alert(error);
			}
		})
		return true;
	}
}


function csModalData(empId,i){
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("empId", empId);
	$.ajax({
			url: 'CSModalData.do',
			data: formData,
			type: 'POST',
			cache: false,
			contentType: false,
			processData: false,
			success: function(data) {
				 
				 if (data.status == "FOUND") {
				$("#empId").val(data.body[0].id);
				$("#empName").val(data.body[0].name);
				$("#blockPeriod").val(data.body[0].leaveEncashmentBlock);
				$("#blockStartDate").val(data.body[0].lfcFromDate);
				$("#blockEndDate").val(data.body[0].lfctoDate);
				$("#leaveType").val(data.body[0].leaveTypeStr);
				$("#leavefromDate").val(data.body[0].leavefromDateStr);
				$("#leaveToDate").val(data.body[0].leavetoDateStr);
				$("#numberOfDays").val(data.body[0].numberofDays);
				$("#destination").val(data.body[0].placeofDestination);
				$("#dateOfCommencement").val(data.body[0].commencementFromDate);
				$("#dateOfCompletion").val(data.body[0].complitionToDate);
				$("#amountOfAdvance").val(data.body[0].amountofAdvance);
				$("#hrRemark").val(data.body[0].hrRemark);
				$("#internalAuditRemark").val(data.body[0].internalAuditRemark);
				$("#origination").val(data.body[0].placeofOrigination);
				$("#encashmentLeave").val(data.body[0].encashmentLeaveCount);

				$('#csModalData').empty();
				for (var i = 0; i < data.tablebody.length; i++) {
					$('#csModalData').append(
						"<tr>"
 						+ "<td nowrap='nowrap'>" + data.tablebody[i].name + "</td>"
						+ "<td nowrap='nowrap'>" + data.tablebody[i].relation + "</td>"
						+ "<td nowrap='nowrap'>" + data.tablebody[i].dob + "</td>"
						+ "<td nowrap='nowrap'>" + data.tablebody[i].annualIncome + "</td>"
						+ "<td nowrap='nowrap'>" + data.tablebody[i].occupation + "</td>"
						+ "</tr>"
					);
				}
			}
	       $("#exampleModalLong").modal('show');
				 
			},
			error: function(error) {
				alert(error);
			}
		})
}


function userfilter() {
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	$.ajax({
		url: 'userFilter.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {

		},
		error: function(data) { }
	})
}

function lfcreport() {
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	$.ajax({
		url: 'lfcreport.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			 
			if (data.status == "SUCCESS") {
				$('#HrReport_table').empty();
				for (var i = 0; i < data.body.length; i++) {
					if (data.body[i].dependent != null || data.body[i].occupation != null || data.body[i].annualIncome != null) {
						var str = data.body[i].dependent;
						var depend = str.replaceAll(",", "<br>");
						var str1 = data.body[i].occupation;
						var occupation = str1.replaceAll(",", "<br><br>");
						var str2 = data.body[i].annualIncome;
						var annualIncome = str2.replaceAll(",", "<br><br>")
					}

					$('#HrReport_table').append(
						"<tr>"
						+ "<td nowrap='nowrap'>" + data.body[i].id + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].name + "</td>"
						+ "<td nowrap='nowrap'>" + depend + "</td>"
						+ "<td nowrap='nowrap'>" + occupation + "</td>"
						+ "<td nowrap='nowrap'>" + annualIncome + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leaveTypeStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leavefromDateStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leavetoDateStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].numberofDaysStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].placeofDestination + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].amountofAdvanceStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].hrStatus + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].internalAuditStatus + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].csStatus + "</td>"
 						+ "</tr>"
					)
				}

			}
		},
		error: function(data) {
			console.log(data);
		}
	})
}

function internalAuditReport() {
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	$.ajax({
		url: 'lfcInternalAuditreport.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			 
			if (data.status == "SUCCESS") {
				$('#InternalAuditReport_table').empty();
				for (var i = 0; i < data.body.length; i++) {
					if (data.body[i].dependent != null || data.body[i].occupation != null || data.body[i].annualIncome != null) {
						var str = data.body[i].dependent;
						var depend = str.replaceAll(",", "<br>")
						var str = data.body[i].occupation;
						var occupation = str.replaceAll(",", "<br><br>");
						var str = data.body[i].annualIncome;
						var annualIncome = str.replaceAll(",", "<br><br>");
					}
					$('#InternalAuditReport_table').append(
						"<tr>"
						+ "<td nowrap='nowrap'>" + data.body[i].id + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].name + "</td>"
						+ "<td nowrap='nowrap'>" + depend + "</td>"
						+ "<td nowrap='nowrap'>" + occupation + "</td>"
						+ "<td nowrap='nowrap'>" + annualIncome + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leaveTypeStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leavefromDateStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leavetoDateStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].numberofDaysStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].placeofDestination + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].amountofAdvanceStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].hrStatus + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].internalAuditStatus + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].csStatus + "</td>"
						+ "</tr>"
					)
				}
			}
		},
		error: function(data) {
			console.log(data);
		}
	})
}

function csReport() {
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	$.ajax({
		url: 'lfcCsreport.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			if (data.status == "SUCCESS") {
				$('#CsReport_table').empty();
				for (var i = 0; i < data.body.length; i++) {
					if (data.body[i].dependent != null || data.body[i].occupation != null || data.body[i].annualIncome != null) {
						var str = data.body[i].dependent;
						var depend = str.replaceAll(",", "<br>");
						var str1 = data.body[i].occupation;
						var occupation = str1.replaceAll(",", "<br><br>");
						var str2 = data.body[i].annualIncome;
						var annualIncome = str2.replaceAll(",", "<br><br>");
					}
					$('#CsReport_table').append(
						"<tr>"
						+ "<td nowrap='nowrap'>" + data.body[i].id + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].name + "</td>"
						+ "<td nowrap='nowrap'>" + depend + "</td>"
						+ "<td nowrap='nowrap'>" + occupation + "</td>"
						+ "<td nowrap='nowrap'>" + annualIncome + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leaveTypeStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leavefromDateStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leavetoDateStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].numberofDaysStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].placeofDestination + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].amountofAdvanceStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].hrStatus + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].internalAuditStatus + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].csStatus + "</td>"
						+ "</tr>"
					)
				}
			}
		},
		error: function(data) {
			console.log(data);
		}
	})
}

function lfcController() {
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	$.ajax({
		url: 'lfcController.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			 
		},
		error: function(data) {
			console.log(data);
		}
	})
}

function userReport() {
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	$.ajax({
		url: 'lfcUserreport.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			 
			if (data.status == "SUCCESS") {
				$('#userReport_table').empty();
				 

				for (var i = 0; i < data.body.length; i++) {
					if (data.body[i].dependent != null || data.body[i].occupation != null || data.body[i].annualIncome != null) {
						var str = data.body[i].dependent;
						var depend = str.replaceAll(",", "<br>");
						var str1 = data.body[i].occupation;
						var occupation = str1.replaceAll(",", "<br><br>");
						var str2 = data.body[i].annualIncome;
						var annualIncome = str2.replaceAll(",", "<br><br>");
					}
					$('#userReport_table').append(
						"<tr>"
						+ "<td nowrap='nowrap'>" + data.body[i].id + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].name + "</td>"
						+ "<td nowrap='nowrap'>" + depend + "</td>"
						+ "<td nowrap='nowrap'>" + occupation + "</td>"
						+ "<td nowrap='nowrap'>" + annualIncome + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leaveTypeStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leavefromDateStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leavetoDateStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].numberofDaysStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].placeofDestination + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].amountofAdvanceStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].hrStatus + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].internalAuditStatus + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].csStatus + "</td>"
						+ "</tr>"
					)
				}
			}

		},
		error: function(data) {
			console.log(data)
		}
	})
}


function dateDiffCount() {
	debugger;
	var formData = new FormData();
	var leavefrmdate = $('#fodatepic').val();
	var leavetodate = $('#todatepic').val();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("leavefrmdate", leavefrmdate);
	formData.append("leavetodate", leavetodate);
	var c = $('#count').val();
	var count = parseInt(c);
	var n = $('#NOD').val();
	var numberOfDays = parseInt(n);
	$.ajax({
		url: 'lfcLeaveDateDiff.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			 
			if (data.status == "SUCCESS" && data.body < count && data.body < 30) {

				if (data.body < 0) {
					alert("Check 'to date'");
					event.returnValue = false;
					document.getElementById("NOD").value = "";
					document.getElementById("todatepic").value = "";
				} else {

					$('#NOD').val(data.body);
				}
			}
			else {
				alert("Insufficient LFC balance");
				event.returnValue = false;
				document.getElementById("NOD").value = "";
			}
		},
		error: function(data) {
			console.log(error)
		}
	})
}

function encashmentLeavedateDiffCount() {
	debugger;
	var fromDate = $('#leave_fromdate').val();
	var todate = $('#leave_todate').val();

	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("fromDate", fromDate);
	formData.append("todate", todate);
	var c = $('#Encasmentleavebalance').val();
	var count = parseInt(c);
	var n = $('#Encasmentleavecount').val();
	var numberOfDays = parseInt(n);
	$.ajax({
		url: 'encashmentLeaveDateDiff.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			if (data.status == "SUCCESS" && data.body < count && data.body < 30) {
				if (data.body < 0) {
					alert("Check 'to date'");
					event.returnValue = false;
					document.getElementById("Encasmentleavecount").value = "";
					document.getElementById("todate").value = "";
				} else {
					$('#Encasmentleavecount').val(data.body);
				}
			} else {
				alert("Insufficient LFC balance");
				event.returnValue = false;
				document.getElementById("Encasmentleavecount").value = "";
			}
		},
		error: function(data) {
			console.log(error)
		}
	})
}

function internalAuditorAdminShow() {
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	$.ajax({
		url: 'internalAuditorAdmin.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			 
			if (data.status == "SUCCESS") {
				$('#lfcadmin_table').empty();
				for (var i = 0; i < data.body.length; i++) {
					if (data.body[i].dependent != null || data.body[i].occupation != null || data.body[i].annualIncome != null) {
						var str = data.body[i].dependent;
						var depend = str.replaceAll(",", "<br>");
						var str1 = data.body[i].occupation;
						var occupation = str1.replaceAll(",", "<br><br>");
						var str2 = data.body[i].annualIncome;
						var annualIncome = str2.replaceAll(",", "<br><br>");
					}
					$('#lfcadmin_table').append(
						"<tr>"
						+ "<td nowrap='nowrap'>" + data.body[i].id + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].name + "</td>"
						+ "<td nowrap='nowrap'>" + depend + "</td>"
						+ "<td nowrap='nowrap'>" + occupation + "</td>"
						+ "<td nowrap='nowrap'>" + annualIncome + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leaveTypeStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leavefromDateStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leavetoDateStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].numberofDaysStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].placeofDestination + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].amountofAdvanceStr + "</td>"
						+ "<td><input type='number' id='Auditamount" + i + "' maxlength='10'><span id = 'auditamountError'></span></td>"
						+ "<td><input type='number' id='AuditamountLeaveEncash" + i + "' maxlength='10'><span id = 'AuditamountLeaveEncash'></span></td>"
						+ "<td><input type='text' id='AuditAdminremark" + i + "' maxlength='50'><span id = 'auditError'></span></td>"
						+ "<td><a href='javascript:intenalAuditAdminNodalData(" + data.body[i].id + "," + i + ")'>view</a></td>"
						+ "<td style='white-space: nowrap'><button class='btn  btn-success' value=" + data.body[i].tranId + " id='InternalacceptButton' onclick='InternalAdminacceptButtonfun(" + data.body[i].tranId + "," + i + ")'> Accept </button>&nbsp&nbsp"
						+ "<button class='btn btn-danger' value=" + data.body[i].tranId + " id='InternalrejectButton' onclick='InternalAdminrejectButtonfun(" + data.body[i].tranId + "," + i + ")'>Reject</button></td>"
						+ "</tr>"

					)
				}
			}
		},
		error: function(error) {
			console.log(error);
		}
	})
}

function InternalAdminacceptButtonfun(tranid, i) {
	debugger;
	
	var AuditAdminremark = $('#AuditAdminremark' + i).val();
	var advanceAmountApproved = $('#Auditamount' + i).val();
	var leaveEncashmentAmountApproved = $('#AuditamountLeaveEncash' + i).val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("acceptval", tranid);
	formData.append("AuditAdminremark", AuditAdminremark);
	formData.append("advanceAmountApproved", advanceAmountApproved);
	formData.append("leaveEncashmentAmountApproved", leaveEncashmentAmountApproved);
	$.ajax({
		url: 'InternalAdminacceptButton.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			if (data.status == "SUCCESS") {
				alert(data.message);
				window.location.reload();
			}
		},
		error: function(error) {
			alert(error);
		}
	})
}


function InternalAdminrejectButtonfun() {
	var rejectval = $('#InternalAdminrejectButton').val();
	var Auditremark = $('#AuditAdminremark').val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("rejectval", rejectval);
	formData.append("Auditremark", Auditremark);
	if (Auditremark == "") {
		alert("Please provide the remark for rejection");
		return false;
	}
	else {
		$.ajax({
			url: 'internalAdminrejectbutton.do',
			data: formData,
			type: 'POST',
			cache: false,
			contentType: false,
			processData: false,
			success: function(data) {
				if (data.status == "SUCCESS") {
					alert(data.message);
					window.location.reload();
				} else {
					alert(data.message);
				}
			},
			error: function(error) {
				alert(error);
			}
		});
		return true;
	}
}


function intenalAuditAdminNodalData(empId , i){
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("empId", empId);
	$.ajax({
			url: 'internalAdminModalData.do',
			data: formData,
			type: 'POST',
			cache: false,
			contentType: false,
			processData: false,
			success: function(data) {
				  
				 if (data.status == "FOUND") {

				$("#empId").val(data.body.id);
				$("#empName").val(data.body.name);
				$("#blockPeriod").val(data.body.leaveEncashmentBlock);
				$("#blockStartDate").val(data.body.lfcFromDate);
				$("#blockEndDate").val(data.body.lfctoDate);
				$("#leaveType").val(data.body.leaveTypeStr);
				$("#leavefromDate").val(data.body.leavefromDateStr);
				$("#leaveToDate").val(data.body.leavetoDateStr);
				$("#numberOfDays").val(data.body.numberofDays);
				$("#destination").val(data.body.placeofDestination);
				$("#dateOfCommencement").val(data.body.commencementFromDate);
				$("#dateOfCompletion").val(data.body.complitionToDate);
				$("#amountOfAdvance").val(data.body.amountofAdvance);
				$("#origination").val(data.body.placeofOrigination);
				$("#encashmentLeave").val(data.body.encashmentLeaveCount);

				$('#internalAuditorModals').empty();

				for (var i = 0; i < data.tablebody.length; i++) {
					$('#internalAuditorModals').append(
						"<tr>"
 						+ "<td nowrap='nowrap'>" + data.tablebody[i].name + "</td>"
						+ "<td nowrap='nowrap'>" + data.tablebody[i].relation + "</td>"
						+ "<td nowrap='nowrap'>" + data.tablebody[i].dob + "</td>"
						+ "<td nowrap='nowrap'>" + data.tablebody[i].annualIncome + "</td>"
						+ "<td nowrap='nowrap'>" + data.tablebody[i].occupation + "</td>"
						+ "</tr>"
					);
				}
				$("#exampleModalLong").modal('show');
			}
			},
			error: function(error) {
				alert(error);
			}
		});
 }


function csAdminLfcRequest() {
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	$.ajax({
		url: 'csAdminLfcReqDate.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			if (data.status == "SUCCESS") {
				$('#csAdmin_Lfcreq').empty();
				for (var i = 0; i < data.body.length; i++) {
					if (data.body[i].dependent != null || data.body[i].occupation != null || data.body[i].annualIncome != null) {
						var str = data.body[i].dependent;
						var depend = str.replaceAll(",", "<br>");
						var str1 = data.body[i].occupation;
						var occupation = str1.replaceAll(",", "<br><br>");
						var str2 = data.body[i].annualIncome;
						var annualIncome = str2.replaceAll(",", "<br><br>");
					}
					$('#csAdmin_Lfcreq').append(
						"<tr>"
						+ "<td nowrap='nowrap'>" + data.body[i].id + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].name + "</td>"
						+ "<td nowrap='nowrap'>" + depend + "</td>"
						+ "<td nowrap='nowrap'>" + occupation + "</td>"
						+ "<td nowrap='nowrap'>" + annualIncome + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leaveTypeStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leavefromDateStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leavetoDateStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].numberofDaysStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].placeofDestination + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].amountofAdvanceStr + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].internalAuditRemark + "</td>"
						+ "<td><input type='text' id='CSAdminremark" + i + "' maxlength='50'></td>"
						+ "<td><a href='javascript:csAdminModalData(" + data.body[i].id + "," + i + ")'>view</a></td>"
						+ "<td style='white-space: nowrap'><button class='btn  btn-success' value=" + data.body[i].tranId + " id='CSadminacceptButton' onclick='CSAdminacceptfun(" + data.body[i].id + "," + data.body[i].tranId + "," + i + ")'> Accept </button>&nbsp&nbsp"
						+ "<button class='btn btn-danger' value=" + data.body[i].tranId + " id='CSadminrejectButton' onclick='CSAdminrejectfun(" + data.body[i].tranId + "," + i + ")'>Reject</button></td>"
						+ "</tr>"

					)
				}
			}
		},
		error: function(error) {
			console.log(error);
		}
	})
}

function CSAdminacceptfun(empCode, tranid, i) {
	debugger;
	var acceptval = $('#CSadminacceptButton').val();
	var cSremark = $('#CSAdminremark' + i).val();

	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("acceptval", tranid);
	formData.append("cSremark", cSremark);
	formData.append("empCode", empCode);
	$.ajax({
		url: 'CSAdminacceptbutton.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {
			if (data.status == "SUCCESS") {
				alert(data.message);
				window.location.reload();
			}
		},
		error: function(error) {
			alert(error);
		}
	})

}

function CSAdminrejectfun(tranid, i) {
	debugger;

	var cSremark = $('#CSAdminremark' + i).val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("rejectval", tranid);
	formData.append("cSremark", cSremark);
	if (cSremark == "") {
		alert("Please provide the remark for rejection");
		return false;
	}
	else {
		$.ajax({
			url: 'CSAdminrejectbutton.do',
			data: formData,
			type: 'POST',
			cache: false,
			contentType: false,
			processData: false,
			success: function(data) {
				if (data.status == "SUCCESS") {
					alert(data.message);
					window.location.reload();
				}
			},
			error: function(error) {
				alert(error);
			}
		})
		return true;
	}

}


function csAdminModalData(empId,i){
	 debugger;
	 var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("empId", empId);
	$.ajax({
			url: 'CSAdminModalData.do',
			data: formData,
			type: 'POST',
			cache: false,
			contentType: false,
			processData: false,
			success: function(data) {
				  
				 if (data.status == "FOUND") {

				$("#empId").val(data.body[0].id);
				$("#empName").val(data.body[0].name);
				$("#blockPeriod").val(data.body[0].leaveEncashmentBlock);
				$("#blockStartDate").val(data.body[0].lfcFromDate);
				$("#blockEndDate").val(data.body[0].lfctoDate);
				$("#leaveType").val(data.body[0].leaveTypeStr);
				$("#leavefromDate").val(data.body[0].leavefromDateStr);
				$("#leaveToDate").val(data.body[0].leavetoDateStr);
				$("#numberOfDays").val(data.body[0].numberofDays);
				$("#destination").val(data.body[0].placeofDestination);
				$("#dateOfCommencement").val(data.body[0].commencementFromDate);
				$("#dateOfCompletion").val(data.body[0].complitionToDate);
				$("#amountOfAdvance").val(data.body[0].amountofAdvance);
 				$("#internalAudit").val(data.body[0].internalAuditRemark);
 				$("#origination").val(data.body[0].placeofOrigination);
 				$("#encashmentLeave").val(data.body[0].encashmentLeaveCount);

				$('#csModalData').empty();

				for (var i = 0; i < data.tablebody.length; i++) {
					$('#csModalData').append(
						"<tr>"

						+ "<td nowrap='nowrap'>" + data.tablebody[i].name + "</td>"
						+ "<td nowrap='nowrap'>" + data.tablebody[i].relation + "</td>"
						+ "<td nowrap='nowrap'>" + data.tablebody[i].dob + "</td>"
						+ "<td nowrap='nowrap'>" + data.tablebody[i].annualIncome + "</td>"
						+ "<td nowrap='nowrap'>" + data.tablebody[i].occupation + "</td>"
						+ "</tr>"
					);
				}
				$("#exampleModalLong").modal('show');
			}
			},
			error: function(error) {
				alert(error);
			}
		})
}

jsonArraylist = [];
function DependentListChekJson() {
	debugger;
	jsonArraylist.length = 0;
	$("input:checkbox[name=checkboxName]:checked").each(function() {
		debugger;
		var jsonObj = {};
		var rowId = $(this).val();
		jsonObj["id"] = $("#idUnique" + rowId).text();
		jsonObj["desrNo"] = $("#dsrid" + rowId).text();
		jsonObj["dependentname"] = $("#dependentnameid" + rowId).text();
		jsonObj["relation"] = $("#relationid" + rowId).text();
		jsonObj["dob"] = $("#dobid" + rowId).text();
		jsonObj["gender"] = $("#genderid" + rowId).text();
		jsonObj["annualincome"] = $("#annualincomeid" + rowId).text();
		jsonObj["occupation"] = $("#occupationid" + rowId).text();
		jsonArraylist.push(jsonObj);
	});
	return jsonArraylist;
}

function hrAcceptReject() {
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	$.ajax({
		url: 'hrAcceptReject.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {

		}
	})
}


function lfcCumnEncashmentpdfDownload() {
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	$.ajax({
		url: 'lfcCumnEncashmentpdfDownload.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		success: function(data) {

		}
	})
}




