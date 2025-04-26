$("document").ready(function() {
	debugger;
	
	 
	lfcSurrenderHrAdmin();
	lfcSurrenderHrReport();
	lfcSurrenderHrAdminRequest();
});

function showLoader(location) {
	$('#Loader').show();
}

function hideLoader(location) {
	$('#Loader').hide();
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
		beforeSend: function() {
					showLoader();
				},
		success : function(data) {
			 hideLoader();
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
 					                   +"<td><input type='number' id='hrSurr_FinalAmount"+i+"' maxlength='5'  ></td>"
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
			hideLoader();
			console.log("error");
		}
	})
}

function hrsacceptfun(tranid,i){
	debugger;
	var acceptval=$('#hrSurrenderAcceptButton').val();
	var hradminremark=$('#hrSurrenderremark'+i).val();
	var hrSurFinalAmount=$('#hrSurr_FinalAmount'+i).val();
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	formData.append("acceptval", tranid);
	formData.append("hradminremark", hradminremark);
	formData.append("SurFinalAmount", hrSurFinalAmount);
	$.ajax({
		url : 'hrSurAcceptbutton.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		beforeSend: function() {
					showLoader();
				},
		success : function(data){
			hideLoader();
			if(data.status=="SUCCESS"){
				alert(data.message);
				window.location.reload();
			}
			else{
				alert(data.message);
			}
		},
		error : function(error){
			hideLoader();
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
		beforeSend: function() {
					showLoader();
				},
		success : function(data){
			hideLoader();
			if(data.status=="SUCCESS"){
				alert(data.message);
				window.location.reload();
			}
			else{
				hideLoader();
				alert(data.message);
 			}
		},
		error : function(error){
			hideLoader();
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
		beforeSend: function() {
					showLoader();
				},
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
				hideLoader();
			}
			else{
				hideLoader();
			}
		},
		error : function(error){
			hideLoader();
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

function lfcSurrenderHrAdminRequest(){
	debugger;
	
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
 	$.ajax({
		url : 'lfcSurrenderHrAdminRequest.do',
		data : formData,
		type : 'POST',
		cache : false,
		contentType : false,
		processData : false,
		beforeSend: function() {
					showLoader();
				},
		success : function(data) {
			 hideLoader();
			if(data.status == "SUCCESS"){
				$('#Surrender_HrAdmin_table').empty();
				for(var i=0;i<data.body.length;i++){
					if(data.body[i].dependent!=null || data.body[i].occupation!=null || data.body[i].annualIncome!=null){
						var str = data.body[i].dependent;
						var depend = str.replaceAll(",","<br>")
						var str1 = data.body[i].occupation;
						var occupation = str1.replaceAll(",","<br><br>")
						var str2 = data.body[i].annualIncome;
						var annualIncome = str2.replaceAll(",","<br><br>")
					}
					$('#Surrender_HrAdmin_table').append("<tr>"
					
					                   +"<td nowrap='nowrap'>"+data.body[i].id+"</td>"
					                   +"<td nowrap='nowrap'>"+data.body[i].name+"</td>"
                                       +"<td nowrap='nowrap'>"+depend+"</td>"
                                       +"<td nowrap='nowrap'>"+occupation+"</td>"
                                       +"<td nowrap='nowrap'>"+annualIncome+"</td>"
					                   +"<td nowrap='nowrap'>"+data.body[i].leaveTypeStr+"</td>"
 					                   +"<td nowrap='nowrap'>"+data.body[i].numberofDaysStr+"</td>"
 					                   +"<td><input type='number' id='hrSurr_FinalAmount"+i+"' maxlength='5'  ></td>"
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
			hideLoader();
			console.log("error");
		}
	})
}

function HrSurReport() {
	debugger;
	// Get table element
	var table = document.querySelector(".table");

	// Convert table to worksheet
	var wb = XLSX.utils.table_to_book(table, { sheet: "LFC SURRENDER Report" });

	// Export to Excel
	XLSX.writeFile(wb, "LFCSURRENDER_HRReport.xlsx");
}




