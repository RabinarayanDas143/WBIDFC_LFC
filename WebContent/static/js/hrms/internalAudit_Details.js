$("document").ready(function() {
	debugger;
	internalAuditorShow()
     internalAuditReport()
     internalAuditorAdminShow()
});

function showLoader(location) {
	$('#Loader').show();
}

function hideLoader(location) {
	$('#Loader').hide();
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
		beforeSend: function() {
					showLoader();
				},
		success: function(data) {
			 hideLoader();
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
						+ "<td nowrap='nowrap'>" + data.body[i].advanceAmountApproved + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].leaveEncashmentAmountApproved + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].lfcFinalAmount + "</td>"
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
		beforeSend: function() {
					showLoader();
				},
		success: function(data) {
			  hideLoader();
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
		beforeSend: function() {
					showLoader();
				},
		success: function(data) {
			console.log("internal Admin data :")
			console.log(data);
			 hideLoader();
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
						+ "<td><input type='number' id='LfcFinalAmount" + i + "' maxlength='10'><span id = 'LfcFinalAmountError'></span></td>"
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
	var lfcFinalAmount = $('#LfcFinalAmount' + i).val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("acceptval", tranid);
	formData.append("AuditAdminremark", AuditAdminremark);
	formData.append("advanceAmountApproved", advanceAmountApproved);
	formData.append("leaveEncashmentAmountApproved", leaveEncashmentAmountApproved);
	formData.append("lfcFinalAmount", lfcFinalAmount);
	$.ajax({
		url: 'InternalAdminacceptButton.do',
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
			beforeSend: function() {
					showLoader();
				},
			success: function(data) {
				  hideLoader();
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

 function IAReport() {
 	debugger;
 	// Get table element
 	var table = document.querySelector(".table");

 	// Convert table to worksheet
 	var wb = XLSX.utils.table_to_book(table, { sheet: "LFC IA Report" });

 	// Export to Excel
 	XLSX.writeFile(wb, "LFC IA Report.xlsx");
 }

