$("document").ready(function() {
	debugger;
	csAdminShow();
     csReport();
     csAdminLfcRequest(); 
});

function showLoader(location) {
	$('#Loader').show();
}

function hideLoader(location) {
	$('#Loader').hide();
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
		beforeSend: function() {
					showLoader();
				},
		success: function(data) {
			 hideLoader();
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
						+ "<td nowrap='nowrap'>" + data.body[i].advanceAmountApproved + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leaveEncashmentAmountApproved + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].lfcFinalAmount + "</td>"
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
		beforeSend: function() {
					showLoader();
				},
		success: function(data) {
			hideLoader();
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
			beforeSend: function() {
					showLoader();
				},
			success: function(data) {
				hideLoader();
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
			beforeSend: function() {
					showLoader();
				},
			success: function(data) {
				 hideLoader();
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
		beforeSend: function() {
					showLoader();
				},
		success: function(data) {
			hideLoader();
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
						+ "<td nowrap='nowrap'>" + data.body[i].lfcFinalAmount + "</td>"
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
		beforeSend: function() {
					showLoader();
				},
		success: function(data) {
			hideLoader();
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
						+ "<td nowrap='nowrap'>" + data.body[i].advanceAmountApproved + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leaveEncashmentAmountApproved + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].lfcFinalAmount + "</td>"
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
		beforeSend: function() {
					showLoader();
				},
		success: function(data) {
			hideLoader();
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
			beforeSend: function() {
					showLoader();
				},
			success: function(data) {
				hideLoader();
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
			beforeSend: function() {
					showLoader();
				},
			success: function(data) {
				  hideLoader();
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
 				$("#hrRemark").val(data.body[0].hrRemark);

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






