$("document").ready(function() {
	debugger;
	
	lfcSurrenderUserReport();
	lfcSurrenderHrAdmin();
	lfcSurrenderHrReport();
	lfcSurrenderInternalAdmin();
	lfcSurrenderInternalAdminReport();
	lfcSurrenderCsAdmin();
	lfcSurrenderCsAdminReport();
	lfcSurrenderInternalAdminadmin();
	surCsAdminLfcRequest();
});


function lfcSurrenderUserReport(){
	debugger;
	
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	$.ajax({
		url : 'lfcSurrenderUserReport.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			
			$('#user_Surrender_Report_table').empty();
			
			for(var i=0;i<data.body.length;i++){
				if(data.body[i].dependent!=null || data.body[i].occupation!=null || data.body[i].annualIncome!=null){
						var str = data.body[i].dependent;
						var depend = str.replaceAll(",","<br>");
						var str1 = data.body[i].occupation;
						var occupation = str1.replaceAll(",","<br><br>");
						var str2 = data.body[i].annualIncome;
						var annualIncome = str2.replaceAll(",","<br><br>")
					}
				$('#user_Surrender_Report_table').append(
					 "<tr>"
			                   +"<td nowrap='nowrap'>"+data.body[i].id+"</td>"
			                   +"<td nowrap='nowrap'>"+data.body[i].name+"</td>"
			                   +"<td nowrap='nowrap'>"+depend+"</td>"
                               +"<td nowrap='nowrap'>"+occupation+"</td>"
                               +"<td nowrap='nowrap'>"+annualIncome+"</td>"
			                   +"<td nowrap='nowrap'>"+data.body[i].leaveTypeStr+"</td>"
 			                   +"<td nowrap='nowrap'>"+data.body[i].numberofDaysStr+"</td>"
 			                   +"<td nowrap='nowrap'>"+data.body[i].hrStatus+"</td>"
			                   +"<td nowrap='nowrap'>"+data.body[i].internalAuditStatus+"</td>"
                               +"<td nowrap='nowrap'>"+data.body[i].csStatus+"</td>"
                           
 		                   +"</tr>"
				)
			}
			
		},
		error : function(data){
			console.log(data);
		}
	})
}

function lfcSurrenderHrAdmin(){
	debugger;
	
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	
	$.ajax({
		url : 'lfcSurrenderHrAdmin.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data) {
			console.log("Inside Hr Surrender Data")
			console.log(data);
			if(data.status == "SUCCESS"){
				$('#lfc_Surrender_HrAdmin_table').empty();
				for(var i=0;i<data.body.length;i++){
					if(data.body[i].dependent!=null || data.body[i].occupation!=null || data.body[i].annualIncome!=null){
						var str = data.body[i].dependent;
						var depend = str.replaceAll(",","<br>")
						var str1 = data.body[i].occupation;
						var occupation = str1.replaceAll(",","<br><br>")
						var str2 = data.body[i].annualIncome;
						var annualIncome = str2.replaceAll(",","<br><br>")
					}
					$('#lfc_Surrender_HrAdmin_table').append("<tr>"
					
					                   +"<td nowrap='nowrap'>"+data.body[i].id+"</td>"
					                   +"<td nowrap='nowrap'>"+data.body[i].name+"</td>"
                                       +"<td nowrap='nowrap'>"+depend+"</td>"
                                       +"<td nowrap='nowrap'>"+occupation+"</td>"
                                       +"<td nowrap='nowrap'>"+annualIncome+"</td>"
					                   +"<td nowrap='nowrap'>"+data.body[i].leaveTypeStr+"</td>"
 					                   +"<td nowrap='nowrap'>"+data.body[i].numberofDaysStr+"</td>"
   					                   +"<td><input type='text' id='hrSurrenderremark"+i+"' maxlength='50'  ></td>"
   					                   +"<td><a href='javascript:hrModalData(" + data.body[i].id + "," + i + ")'>view</a></td>"
					                   +"<td style='white-space: nowrap'><button class='btn  btn-success' value="+data.body[i].tranId+" id='hrSurrenderAcceptButton' onclick='hrsacceptfun("+data.body[i].tranId+","+i+")'> Accept </button>&nbsp&nbsp"
					                   +"<button class='btn btn-danger' value="+data.body[i].tranId+" id='hrSurrenderRejectButton' onclick='hrsrejectfun("+data.body[i].tranId+","+i+")'>Reject</button></td>"
					                   +"</tr>"
					)
				}
			}
		},
		error : function(error) {
			console.log("error");
		}
	})
}

function hrsacceptfun(tranid,i){
	debugger;
	var acceptval=$('#hrSurrenderAcceptButton').val();
	var hradminremark=$('#hrSurrenderremark'+i).val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("acceptval", tranid);
	formData.append("hradminremark", hradminremark);
	$.ajax({
		url : 'hrSurAcceptbutton.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			if(data.status=="SUCCESS"){
				alert(data.message);
				window.location.reload();
			}
			else{
				alert(data.message);
			}
		},
		error : function(error){
			alert(error);
		}
	})
 }

function hrsrejectfun(tranid,i){
	debugger;
	var rejectval=$('#hrSurrenderRejectButton').val();
	var hradminremark=$('#hrSurrenderremark'+i).val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("rejectval", rejectval);
	formData.append("hradminremark", hradminremark);
	if(hradminremark==""){
		alert("Please provide the remark for rejection");
		return false;
	}
	else{
	$.ajax({
		url : 'hrSurRejectbutton.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			if(data.status=="SUCCESS"){
				alert(data.message);
				window.location.reload();
			}
			else{
				alert(data.message);
 			}
		},
		error : function(error){
			alert(error);
		}
	})
	return true;
}
	
}

function hrModalData(empId,i){
	debugger;
	 var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("empId", empId);
	$.ajax({
		url : 'hrSurModalData.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			 if(data.status=="FOUND"){
				$("#empId").val(data.body[0].id);
				$("#empName").val(data.body[0].name);
				$("#blockPeriod").val(data.body[0].leaveEncashmentBlock);
				$("#blockStartDate").val(data.body[0].lfcFromDate);
				$("#blockEndDate").val(data.body[0].lfctoDate);
				$("#leaveType").val(data.body[0].leaveTypeStr);
				$("#numberOfDays").val(data.body[0].numberofDays);
				
				$('#HRModalData').empty();
 				for (var i = 0; i < data.tablebody.length; i++) {
					$('#HRModalData').append(
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
		error : function(error){
			alert(error);
		}
	})
}

function lfcSurrenderHrReport(){
	 
 	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	 
	$.ajax({
		url : 'lfcSurrenderHrReport.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			console.log("Surrender hr Report")
			console.log(data);
			if(data.status=="SUCCESS"){
				$('#HrSurrender_Report_table').empty();
				for(var i=0;i<data.body.length;i++){
					if(data.body[i].dependent!=null || data.body[i].occupation!=null || data.body[i].annualIncome!=null){
						var str = data.body[i].dependent;
						var depend = str.replaceAll(",","<br>");
						var str1 = data.body[i].occupation;
						var occupation = str1.replaceAll(",","<br>");
						var str2 = data.body[i].annualIncome;
						var annualIncome = str2.replaceAll(",","<br>")
					}
					
					$('#HrSurrender_Report_table').append(
						   "<tr>"
			                   +"<td nowrap='nowrap'>"+data.body[i].id+"</td>"
			                   +"<td nowrap='nowrap'>"+data.body[i].name+"</td>"
			                   +"<td nowrap='nowrap'>"+depend+"</td>"
                               +"<td nowrap='nowrap'>"+occupation+"</td>"
                               +"<td nowrap='nowrap'>"+annualIncome+"</td>"
			                   +"<td nowrap='nowrap'>"+data.body[i].leaveTypeStr+"</td>"
 			                   +"<td nowrap='nowrap'>"+data.body[i].numberofDaysStr+"</td>"
 			                   +"<td nowrap='nowrap'>"+data.body[i].hrStatus+"</td>"
			                   +"<td nowrap='nowrap'>"+data.body[i].internalAuditStatus+"</td>"
                               +"<td nowrap='nowrap'>"+data.body[i].csStatus+"</td>"
  		                   +"</tr>"
 						)
				}
			}
		}
	})
}

function lfcSurrenderInternalAdmin(){
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	$.ajax({
		url : 'lfcSurrenderinternalAuditor.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			if(data.status=="SUCCESS"){
				$('#lfc_Surrender_InternalAdmin_table').empty();
				for(var i=0;i<data.body.length;i++){
					if(data.body[i].dependent!=null || data.body[i].occupation!=null || data.body[i].annualIncome!=null){
						var str = data.body[i].dependent;
						var depend = str.replaceAll(",","<br>");
						var str1 = data.body[i].occupation;
						var occupation = str1.replaceAll(",","<br><br>")
						var str2 = data.body[i].annualIncome;
						var annualIncome = str2.replaceAll(",","<br><br>")
					}
					$('#lfc_Surrender_InternalAdmin_table').append(
							   "<tr>"
 			                   +"<td nowrap='nowrap'>"+data.body[i].id+"</td>"
					           +"<td nowrap='nowrap'>"+data.body[i].name+"</td>"
                               +"<td nowrap='nowrap'>"+depend+"</td>"
                               +"<td nowrap='nowrap'>"+occupation+"</td>"
                               +"<td nowrap='nowrap'>"+annualIncome+"</td>"
			                   +"<td nowrap='nowrap'>"+data.body[i].leaveTypeStr+"</td>"
 			                   +"<td nowrap='nowrap'>"+data.body[i].numberofDaysStr+"</td>"
                               +"<td nowrap='nowrap'>"+data.body[i].hrRemark+"</td>"
			                   +"<td><input type='text' id='SurAuditremark"+i+"' maxlength='50'><span id = 'auditError'></span></td>"
			                   +"<td><a href='javascript:internalAuditModalData(" + data.body[i].id + "," + i + ")'>view</a></td>"
			                   +"<td style='white-space: nowrap'><button class='btn  btn-success' value="+data.body[i].tranId+" id='SurInternalacceptButton' onclick='SurInternalacceptfun("+data.body[i].tranId+","+i+")'> Accept </button>&nbsp&nbsp"
			                   +"<button class='btn btn-danger' value="+data.body[i].tranId+" id='SurInternalrejectButton' onclick='SurInternalrejectfun("+data.body[i].tranId+","+i+")'>Reject</button></td>"
			                   +"</tr>"
 							)
				}
			}
		}
	})
}

function SurInternalacceptfun(tranid,i){
	debugger;
	var acceptval=$('#SurInternalacceptButton').val();
	var Auditremark=$('#SurAuditremark'+i).val();
	
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("acceptval", tranid);
	formData.append("AuditAdminremark", Auditremark);
	$.ajax({
		url : 'Surinternalacceptbutton.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			if(data.status=="SUCCESS"){
				alert(data.message);
				window.location.reload();
			}else{
				alert(data.message);
			}
		},
		error : function(error){
			console.log(error);
		}
	})
 }

function SurInternalrejectfun(tranid,i){
	debugger;
	var rejectval=$('#SurInternalrejectButton').val();
	var Auditremark=$('#SurAuditremark'+i).val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("rejectval", tranid);
	formData.append("Auditremark", Auditremark);
	if(Auditremark==""){
		alert("Please provide the remark for rejection");
		return false;
	}
	else{
	$.ajax({
		url : 'Surinternalrejectbutton.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			if(data.status=="SUCCESS"){
				alert(data.message);
				window.location.reload();
			}else{
				alert(data.message);
 			}
		},
		error : function(error){
			alert(error);
		}
	})
	return true;
}
}

function internalAuditModalData(empId,i){
	 debugger;
	 var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("empId", empId);
	 $.ajax({
		url : 'SurinternalAuditModalData.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			 if(data.status=="FOUND"){
				$("#empId").val(data.body[0].id);
				$("#empName").val(data.body[0].name);
				$("#blockPeriod").val(data.body[0].leaveEncashmentBlock);
				$("#blockStartDate").val(data.body[0].lfcFromDate);
				$("#blockEndDate").val(data.body[0].lfctoDate);
				$("#leaveType").val(data.body[0].leaveTypeStr);
				$("#numberOfDays").val(data.body[0].numberofDays);
				$("#hrremark").val(data.body[0].hrRemark);
				
				$('#InternalAuditModalData').empty();
 				for (var i = 0; i < data.tablebody.length; i++) {
					$('#InternalAuditModalData').append(
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
		error : function(error){
			alert(error);
		}
	})
}



function lfcSurrenderInternalAdminReport(){
	debugger;
 	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	 
	$.ajax({
		url : 'lfcSurrenderInternalAdminReport.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			console.log("Internal Audit Report");
			console.log(data);
			if(data.status=="SUCCESS"){
				$('#InternalAdminSurrender_Report_table').empty();
				for(var i=0;i<data.body.length;i++){
					if(data.body[i].dependent!=null || data.body[i].occupation!=null || data.body[i].annualIncome!=null){
						var str = data.body[i].dependent;
						var depend = str.replaceAll(",","<br>")
						var str = data.body[i].occupation;
						var occupation = str.replaceAll(",","<br>");
						var str = data.body[i].annualIncome;
						var annualIncome = str.replaceAll(",","<br>");
					}
					$('#InternalAdminSurrender_Report_table').append(
							   "<tr>"
			                   +"<td nowrap='nowrap'>"+data.body[i].id+"</td>"
			                   +"<td nowrap='nowrap'>"+data.body[i].name+"</td>"
			                   +"<td nowrap='nowrap'>"+depend+"</td>"
                               +"<td nowrap='nowrap'>"+occupation+"</td>"
                               +"<td nowrap='nowrap'>"+annualIncome+"</td>"
			                   +"<td nowrap='nowrap'>"+data.body[i].leaveTypeStr+"</td>"
 			                   +"<td nowrap='nowrap'>"+data.body[i].numberofDaysStr+"</td>"
 			                   +"<td nowrap='nowrap'>"+data.body[i].hrStatus+"</td>"
			                   +"<td nowrap='nowrap'>"+data.body[i].internalAuditStatus+"</td>"
                               +"<td nowrap='nowrap'>"+data.body[i].csStatus+"</td>"
  		                       +"</tr>"
	 						)
				}
			}
		},
		error : function(error){
			alert(error);
		}
	});
}

function lfcSurrenderCsAdmin(){
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	 
	$.ajax({
		url : 'lfcSurrenderCsAuditor.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			if(data.status=="SUCCESS"){
				$('#lfc_Surrender_CsAdmin_table').empty();
				for(var i=0;i<data.body.length;i++){
					if(data.body[i].dependent!=null || data.body[i].occupation!=null || data.body[i].annualIncome!=null){
						var str = data.body[i].dependent;
						var depend = str.replaceAll(",","<br>");
						var str1 = data.body[i].occupation;
						var occupation = str1.replaceAll(",","<br><br>");
						var str2 = data.body[i].annualIncome;
						var annualIncome = str2.replaceAll(",","<br><br>");
					}
					$('#lfc_Surrender_CsAdmin_table').append(
							   "<tr>"
 			                   +"<td nowrap='nowrap'>"+data.body[i].id+"</td>"
					           +"<td nowrap='nowrap'>"+data.body[i].name+"</td>"
                               +"<td nowrap='nowrap'>"+depend+"</td>"
                               +"<td nowrap='nowrap'>"+occupation+"</td>"
                               +"<td nowrap='nowrap'>"+annualIncome+"</td>"
			                   +"<td nowrap='nowrap'>"+data.body[i].leaveTypeStr+"</td>"
 			                   +"<td nowrap='nowrap'>"+data.body[i].numberofDaysStr+"</td>"
                                +"<td nowrap='nowrap'>"+data.body[i].internalAuditRemark+"</td>"
			                   +"<td><input type='text' id='SurCsremark"+i+"' maxlength='50'><span id = 'auditError'></span></td>"
			                   +"<td><a href='javascript:csModalData(" + data.body[i].id + "," + i + ")'>view</a></td>"
			                   +"<td style='white-space: nowrap'><button class='btn  btn-success' value="+data.body[i].tranId+" id='SurCsacceptButton' onclick='SurCsacceptfun("+data.body[i].id+","+data.body[i].tranId+","+i+")'> Accept </button>&nbsp&nbsp"
			                   +"<button class='btn btn-danger' value="+data.body[i].tranId+" id='SurCsrejectButton' onclick='SurCsrejectfun("+data.body[i].tranId+","+i+")'>Reject</button></td>"
			                   +"</tr>"
 							)
			}
		}
		},
		error : function(error){
			alert(error);
		}
	})
}

function SurCsacceptfun(empCode,tranid,i){
 	debugger;
	var acceptval=$('#SurCsacceptButton').val();
	var cSremark=$('#SurCsremark'+i).val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("acceptval", tranid);
	formData.append("cSremark", cSremark);
	formData.append("empCode",empCode)
	$.ajax({
		url : 'surCScceptbutton.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			if(data.status=="SUCCESS"){
				alert(data.message);
				window.location.reload();
			}else{
				alert(data.message);
			}
		},
		error : function(error){
			alert(error);
		}
	})
 
}

function SurCsrejectfun(tranid,i){
	debugger;
	
	var cSremark=$('#SurCsremark'+i).val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("rejectval", tranid);
	formData.append("cSremark", cSremark);
	if(cSremark==""){
		alert("Please provide the remark for rejection");
		return false;
	}
	else{
	$.ajax({
		url : 'surCSrejectbutton.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			if(data.status=="SUCCESS"){
				alert(data.message);
				window.location.reload();
			}else{
				alert(data.message);
			}
		},
		error : function(error){
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
		url : 'surCSModal.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			console.log(data)
			  if(data.status=="FOUND"){
				$("#empId").val(data.body[0].id);
				$("#empName").val(data.body[0].name);
				$("#blockPeriod").val(data.body[0].leaveEncashmentBlock);
				$("#blockStartDate").val(data.body[0].lfcFromDate);
				$("#blockEndDate").val(data.body[0].lfctoDate);
				$("#leaveType").val(data.body[0].leaveTypeStr);
				$("#numberOfDays").val(data.body[0].numberofDays);
				$("#hrremark").val(data.body[0].hrRemark);
				$("#internalAuditremark").val(data.body[0].internalAuditRemark);
				
				$('#CSModalData').empty();
 				for (var i = 0; i < data.tablebody.length; i++) {
					$('#CSModalData').append(
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
		error : function(error){
			alert(error);
		}
	})
}


function lfcSurrenderCsAdminReport(){
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	$.ajax({
		url : 'surCsreport.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			if(data.status=="SUCCESS"){
				$('#CsAdminSurrender_Report_table').empty();
				for(var i=0;i<data.body.length;i++){
					if(data.body[i].dependent!=null || data.body[i].occupation!=null || data.body[i].annualIncome!=null){
						var str = data.body[i].dependent;
						var depend = str.replaceAll(",","<br>");
						var str1 = data.body[i].occupation;
						var occupation = str1.replaceAll(",","<br>");
						var str2 = data.body[i].annualIncome;
						var annualIncome = str2.replaceAll(",","<br>");
					}
					$('#CsAdminSurrender_Report_table').append(
							   "<tr>"
			                   +"<td nowrap='nowrap'>"+data.body[i].id+"</td>"
			                   +"<td nowrap='nowrap'>"+data.body[i].name+"</td>"
			                   +"<td nowrap='nowrap'>"+depend+"</td>"
                               +"<td nowrap='nowrap'>"+occupation+"</td>"
                               +"<td nowrap='nowrap'>"+annualIncome+"</td>"
			                   +"<td nowrap='nowrap'>"+data.body[i].leaveTypeStr+"</td>"
 			                   +"<td nowrap='nowrap'>"+data.body[i].numberofDaysStr+"</td>"
 			                   +"<td nowrap='nowrap'>"+data.body[i].hrStatus+"</td>"
			                   +"<td nowrap='nowrap'>"+data.body[i].internalAuditStatus+"</td>"
                               +"<td nowrap='nowrap'>"+data.body[i].csStatus+"</td>"
  		                       +"</tr>"
	 						)
				}
			}
		},
		error : function(error){
			alert(error);
		}
	})
}

function lfcSurrenderInternalAdminadmin(){
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	$.ajax({
		url : 'surIAadmin.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			if(data.status=="SUCCESS"){
				$('#Surrender_InternalAdmin_admin_table').empty();
				for(var i=0;i<data.body.length;i++){
					if(data.body[i].dependent!=null || data.body[i].occupation!=null || data.body[i].annualIncome!=null){
						var str = data.body[i].dependent;
						var depend = str.replaceAll(",","<br>");
						var str1 = data.body[i].occupation;
						var occupation = str1.replaceAll(",","<br><br>");
						var str2 = data.body[i].annualIncome;
						var annualIncome = str2.replaceAll(",","<br><br>");
					}
					$('#Surrender_InternalAdmin_admin_table').append(
							   "<tr>"
 			                   +"<td nowrap='nowrap'>"+data.body[i].id+"</td>"
					           +"<td nowrap='nowrap'>"+data.body[i].name+"</td>"
                               +"<td nowrap='nowrap'>"+depend+"</td>"
                               +"<td nowrap='nowrap'>"+occupation+"</td>"
                               +"<td nowrap='nowrap'>"+annualIncome+"</td>"
			                   +"<td nowrap='nowrap'>"+data.body[i].leaveTypeStr+"</td>"
 			                   +"<td nowrap='nowrap'>"+data.body[i].numberofDaysStr+"</td>"
  			                   +"<td><input type='text' id='SurAuditAdmin"+i+"' maxlength='50'><span id = 'auditError'></span></td>"
  			                   +"<td><a href='javascript:InternalAuditAdminModalData(" + data.body[i].id + "," + i + ")'>view</a></td>"
			                   +"<td style='white-space: nowrap'><button class='btn  btn-success' value="+data.body[i].tranId+" id='surInternalaAdmincceptButton' onclick='surInternalAdminacceptfun("+data.body[i].tranId+","+i+")'> Accept </button>&nbsp&nbsp"
			                   +"<button class='btn btn-danger' value="+data.body[i].tranId+" id='surInternalAdminrejectButton' onclick='surInternalAdminrejectfun("+data.body[i].tranId+","+i+")'>Reject</button></td>"
			                   +"</tr>"
			
							)
				}
			}
		},
		error : function(error){
			alert(error);
		}
	})
}

function surInternalAdminacceptfun(tranid,i){
	debugger;
 	var acceptval=$('#surInternalaAdmincceptButton').val();
	var AuditAdminremark=$('#SurAuditAdmin'+i).val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("acceptval", tranid);
	formData.append("AuditAdminremark", AuditAdminremark);
	$.ajax({
		url : 'surInternalAdminacceptButton.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			if(data.status=="SUCCESS"){
				alert(data.message);
				window.location.reload();
			}
		},
		error : function(error){
			alert(error);
		}
	})
}

function surInternalAdminrejectfun(tranid,i){
	var rejectval=$('#surInternalAdminrejectButton').val();
	var Auditremark=$('#SurAuditAdmin').val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("rejectval", tranid);
	formData.append("Auditremark", Auditremark);
	if(Auditremark==""){
		alert("Please provide the remark for rejection");
		return false;
	}
	else{
 		$.ajax({
 			url : 'surinternalAdminrejectbutton.do',
 			data : formData,
 			type : 'POST',
 			cache : false,
 			contentType : false,
 			processData : false,
 			success : function(data){
 				if(data.status=="SUCCESS"){
 					alert(data.message);
 					window.location.reload();
 				}else{
	                alert(data.message);   
                }
 			},
 			error : function(error){
 				alert(error);
 			}
		});
 		return true;
	}
}

function InternalAuditAdminModalData(empId,i){
	debugger;
	
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("empId", empId);
	$.ajax({
 			url : 'surinternalAdminModalData.do',
 			data : formData,
 			type : 'POST',
 			cache : false,
 			contentType : false,
 			processData : false,
 			success : function(data){
 				 if(data.status=="FOUND"){
				$("#empId").val(data.body[0].id);
				$("#empName").val(data.body[0].name);
				$("#blockPeriod").val(data.body[0].leaveEncashmentBlock);
				$("#blockStartDate").val(data.body[0].lfcFromDate);
				$("#blockEndDate").val(data.body[0].lfctoDate);
				$("#leaveType").val(data.body[0].leaveTypeStr);
				$("#numberOfDays").val(data.body[0].numberofDays);
				 
				
				$('#SurInternalAdminModalData').empty();
 				for (var i = 0; i < data.tablebody.length; i++) {
					$('#SurInternalAdminModalData').append(
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
 			error : function(error){
 				alert(error);
 			}
		});
	
}


function surCsAdminLfcRequest(){
 	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	$.ajax({
		url : 'SurcsAdminLfcReqAdmin.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			if(data.status=="SUCCESS"){
				$('#Sur_csAdmin_Admin').empty();
				for(var i=0;i<data.body.length;i++){
					if(data.body[i].dependent!=null || data.body[i].occupation!=null || data.body[i].annualIncome!=null){
						var str = data.body[i].dependent;
						var depend = str.replaceAll(",","<br>");
						var str1 = data.body[i].occupation;
						var occupation = str1.replaceAll(",","<br><br>");
						var str2 = data.body[i].annualIncome;
						var annualIncome = str2.replaceAll(",","<br><br>");
					}
					$('#Sur_csAdmin_Admin').append(
							   "<tr>"
 			                    +"<td nowrap='nowrap'>"+data.body[i].id+"</td>"
					           +"<td nowrap='nowrap'>"+data.body[i].name+"</td>"
                               +"<td nowrap='nowrap'>"+depend+"</td>"
                               +"<td nowrap='nowrap'>"+occupation+"</td>"
                               +"<td nowrap='nowrap'>"+annualIncome+"</td>"
			                   +"<td nowrap='nowrap'>"+data.body[i].leaveTypeStr+"</td>"
 			                   +"<td nowrap='nowrap'>"+data.body[i].numberofDaysStr+"</td>"
                                +"<td nowrap='nowrap'>"+data.body[i].internalAuditRemark+"</td>"
			                   +"<td><input type='text' id='SurCSAdminremark"+i+"' maxlength='50'></td>"
			                   +"<td><a href='javascript:CsAdminModalData(" + data.body[i].id + "," + i + ")'>view</a></td>"
			                   +"<td style='white-space: nowrap'><button class='btn  btn-success' value="+data.body[i].tranId+" id='SurCSadminacceptButton' onclick='SurCSAdminacceptfun("+data.body[i].id+","+data.body[i].tranId+","+i+")'> Accept </button>&nbsp&nbsp"
			                   +"<button class='btn btn-danger' value="+data.body[i].tranId+" id='SurCSadminrejectButton' onclick='SurCSAdminrejectfun("+data.body[i].tranId+","+i+")'>Reject</button></td>"
			                   +"</tr>"
			
							)
				}
			}
		},
		error : function(error){
			console.log(error);
		}
	})
 }

function SurCSAdminacceptfun(empCode,tranid,i){
 	debugger;
	var acceptval=$('#SurCSadminacceptButton').val();
	var cSremark=$('#SurCSAdminremark'+i).val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("acceptval", tranid);
	formData.append("cSremark", cSremark);
	formData.append("empCode", empCode);
	$.ajax({
		url : 'SurCSAdminacceptbutton.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			if(data.status=="SUCCESS"){
				alert(data.message);
				window.location.reload();
			}
		},
		error : function(error){
			alert(error);
		}
	})
 
}

function SurCSAdminrejectfun(tranid,i){
	debugger;
	
	var cSremark=$('#SurCSAdminremark'+i).val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("rejectval", tranid);
	formData.append("cSremark", cSremark);
	if(cSremark==""){
		alert("Please provide the remark for rejection");
		return false;
	}
	else{
	$.ajax({
		url : 'SurCSAdminrejectbutton.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			if(data.status=="SUCCESS"){
				alert(data.message);
				window.location.reload();
			}
		},
		error : function(error){
			alert(error);
		}
	})
	return true;
}

}

function CsAdminModalData(empId,i){
	 debugger;
	 var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("empId", empId);
	$.ajax({
		url : 'SurCSAdminModalData.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data){
			  if(data.status=="FOUND"){
				$("#empId").val(data.body[0].id);
				$("#empName").val(data.body[0].name);
				$("#blockPeriod").val(data.body[0].leaveEncashmentBlock);
				$("#blockStartDate").val(data.body[0].lfcFromDate);
				$("#blockEndDate").val(data.body[0].lfctoDate);
				$("#leaveType").val(data.body[0].leaveTypeStr);
				$("#numberOfDays").val(data.body[0].numberofDays);
				$("#internalAudit").val(data.body[0].internalAuditRemark); 
				
				$('#SurCsAdminModalData').empty();
 				for (var i = 0; i < data.tablebody.length; i++) {
					$('#SurCsAdminModalData').append(
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
		error : function(error){
			alert(error);
		}
	})
}




