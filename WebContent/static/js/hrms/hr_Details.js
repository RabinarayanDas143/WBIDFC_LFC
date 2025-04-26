$("document").ready(function() {
	debugger;
	hrAdminReport();
	hrAdminRequestData();
	HrRequestData();
});

function showLoader(location) {
	$('#Loader').show();
}

function hideLoader(location) {
	$('#Loader').hide();
}


function HrRequestData() {
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
		beforeSend: function() {
			showLoader();
		},
		success: function(data) {
			hideLoader();
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
						+ "<td><input type='number' id='hr_lfcFinalAmount" + i + "' maxlength='10'><span id = 'hrlfcFinalAmountError'></span></td>"
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

function acceptfun(tranid, i) {
	debugger;
	var acceptval = $('#acceptButton').val();
	var hradminremark = $('#hradminremark' + i).val();

	var Auditamount = $('#Auditamount' + i).val();
	var AuditamountLeaveEncash = $('#AuditamountLeaveEncash' + i).val();
	var lfcFinalAmount = $('#hr_lfcFinalAmount' + i).val();


	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("acceptval", tranid);
	formData.append("hradminremark", hradminremark);
	formData.append("Auditamount", Auditamount);
	formData.append("AuditamountLeaveEncash", AuditamountLeaveEncash);
	formData.append("lfcFinalAmount", lfcFinalAmount);

	$.ajax({
		url: 'acceptbutton.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		beforeSend: function() {
			showLoader();
		},
		success: function(data) {
			if (data.status == "SUCCESS") {
				alert(data.message);
				hideLoader();
				window.location.reload();
			}
		},
		error: function(error) {
			alert(error);
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
			beforeSend: function() {
				showLoader();
			},
			success: function(data) {
				if (data.status == "SUCCESS") {
					alert(data.message);
					hideLoader();
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


function hrAdminReport() {
	debugger;

	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	$.ajax({
		url: 'lfcHrreport.do',
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
						+ "<td nowrap='nowrap'>" + data.body[i].lfcFinalAmount + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].hrStatus + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].internalAuditStatus + "</td>"
						+ "<td nowrap='nowrap'>" + data.body[i].csStatus + "</td>"
						+ "</tr>"
					)
				}

			} else {
				alert("Data Not Found !!")
			}
		},
		error: function(data) {
			console.log(data);
		}
	})
}

function hrAdminRequestData() {
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));

	$.ajax({
		url: 'lfcHrAdminRequest_data.do',
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
				$('#lfc_Hradmin_table').empty();
				for (var i = 0; i < data.body.length; i++) {
					if (data.body[i].dependent != null || data.body[i].occupation != null || data.body[i].annualIncome != null) {
						var str = data.body[i].dependent;
						var depend = str.replaceAll(",", "<br>")
						var str1 = data.body[i].occupation;
						var occupation = str1.replaceAll(",", "<br><br>")
						var str2 = data.body[i].annualIncome;
						var annualIncome = str2.replaceAll(",", "<br><br>")
					}
					$('#lfc_Hradmin_table').append("<tr>"

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

						+ "<td><input type='number' id='Hramount" + i + "' maxlength='10'><span id = 'auditamountError'></span></td>"
						+ "<td><input type='number' id='HrLeaveEncash" + i + "' maxlength='10'><span id = 'AuditamountLeaveEncash'></span></td>"
						+ "<td><input type='number' id='hr_lfcFinalAmount" + i + "' maxlength='10'><span id = 'hr_lfcFinalAmountError'></span></td>"
						+ "<td><input type='text' id='hradminremark" + i + "' maxlength='50'  ></td>"
						+ "<td><a href='javascript:hrModalData(" + data.body[i].id + "," + i + ")'>view</a></td>"
						+ "<td style='white-space: nowrap'><button class='btn btn-success' value=" + data.body[i].tranId + " id='acceptButton' onclick='hrAdmin_acceptfun(" + data.body[i].tranId + "," + i + ")'> Accept </button>&nbsp&nbsp"
						+ "<button class='btn btn-danger' value=" + data.body[i].tranId + " id='rejectButton' onclick='hrAdmin_rejectfun(" + data.body[i].tranId + "," + i + ")'>Reject</button>&nbsp&nbsp</td>"
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

function hrModalData(empId) {
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
				$("#encashmentLeave").val(data.body.encashmentLeaveCount);
				$("#origination").val(data.body.placeofOrigination);

				$('#HrAdminModalData').empty();

				for (var i = 0; i < data.tablebody.length; i++) {
					$('#HrAdminModalData').append(
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
			hideLoader();
		},
		error: function(error) {
			hideLoader();
			alert(error);
		}
	})
}

function hrAdmin_acceptfun(tranId, i) {
	var remark = $('#hradminremark' + i).val();
	var amountApproved = $('#Hramount' + i).val();
	var LeaveEncashApproved = $('#HrLeaveEncash' + i).val();
	var lfcFinalAmount = $('#hr_lfcFinalAmount' + i).val();

	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("tranId", tranId);
	formData.append("hradminremark", remark);
	formData.append("hrAmountApproved", amountApproved);
	formData.append("hrLeaveEncashApproved", LeaveEncashApproved);
	formData.append("lfcFinalAmount", lfcFinalAmount);

	$.ajax({
		url: 'HrAdminAcceptButton.do',
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
				window.location.reload();
			}
		},
		error: function(error) {
			alert(error);
		}
	})
}

function hrAdmin_rejectfun(tranId, i) {
	debugger;
	var remark = $('#hradminremark' + i).val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("tranId", tranId);
	formData.append("hradminremark", remark);
	if (remark == "") {
		alert("Please provide the remark for rejection");
		return false;
	} else {
		$.ajax({
			url: 'HrAdminRejectButton.do',
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
				else {
					alert(data.message);
					window.location.reload();
				}
			},
			error: function(error) {
				alert(error);
			}
		})
	}
}

function HrReport() {
	debugger;
	// Get table element
	var table = document.querySelector(".table");

	// Convert table to worksheet
	var wb = XLSX.utils.table_to_book(table, { sheet: "LFC Report" });

	// Export to Excel
	XLSX.writeFile(wb, "LFC_HRReport.xlsx");
}
