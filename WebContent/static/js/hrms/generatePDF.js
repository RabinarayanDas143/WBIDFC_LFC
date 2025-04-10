
function showLoader(location) {
	$('#Loader').show();
}

function hideLoader(location) {
	$('#Loader').hide();
}

function applyLFC() {
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	$.ajax({
		url: 'ApplyLfcPdf.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		beforeSend: function() {
			showLoader();
		},
		success: function(data) {
			if (data.status == "FAILED") {
				alert(" You Applied Surrender Cum Encashment , Please Download Surrender Cum Encashment File .");
				hideLoader();
			} else {
				hideLoader();
				location.href = "javascript:onSubmitMenu('abc')";
			}
		}
	})
}

function surrenderLFC() {
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	$.ajax({
		url: 'SurrenderLfcPdf.do',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		beforeSend: function() {
			showLoader();
		},
		success: function(data) {
			if (data.status == "FAILED") {
				alert(" You Applied LFC Cum Encashment , Please Download LFC Cum Encashment File .");
				hideLoader();
			} else {
				hideLoader();
				location.href = "javascript:onSubmitMenu('surrenderLfcPdfGenerate')";
			}
		}
	})
}


$("document").ready(function() {

	// This Code for sample document file downloading for every employee .
	$('#docDownload').on('click', function() {
		debugger;
		alert("File are Downloading Please wait ....");
		var token = $("meta[name='_csrf']").attr("content");
		var url = "SampleFileDownload";
		var form = $('<form action="' + url + '" method="post">' +
			'<input type="hidden" id="CSRFToken" name="CSRFToken" value="' + token + '">' +
			'</form>');
		$('body').append(form);
		form.submit();

	});
	// This code for document file downloading employee code basis .
	$('#userFileDownload').on('click', function() {
		debugger;
		var empCode = $('#emp_Code').val();
		if (empCode == "") {
			alert("Please provide the employee code .")
		} else {
			alert("File are Downloading Please wait ....");
			var token = $("meta[name='_csrf']").attr("content");
			var url = "UserAuthFileDownload";
			var form = $('<form action="' + url + '" method="post">' +
				'<input type="hidden" id="CSRFToken" name="CSRFToken" value="' + token + '">' +
				'<input type="hidden" id="userId" name="UserId" value="' + empCode + '">' +
				'</form>');
			$('body').append(form);
			form.submit();
		}

	});

	$("#uploadDocument").on('click', function() {
		debugger;
		var file = $('#file').prop('files');
		var formData = new FormData();
		formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
		if (file.length > 0) {
			for (var i = 0; i < file.length; i++) {
				formData.append('documentFile', file[i]);
			}
		}

		$.ajax({
			url: 'lfcCretificateRawFile.do',
			data: formData,
			type: 'POST',
			cache: false,
			contentType: false,
			processData: false,
			beforeSend: function() {
				alert("File Uplaoded Successfully");
				location.reload();
			},
			success: function(data) {
				if (data.status == "SUCCESS") {
					alert("Sucessfully File Uploaded....!!");
					location.reload();
				} else {
					alert(data.status);
					hideLoader();
					location.reload();
				}
			},
			error: function(error) {
				alert(error);
				location.reload();
			}
		});
	});

});

function UploadView() {
	
	debugger;
	var formData = new FormData();
	formData.append("CSRFToken", $("meta[name='_csrf']").attr("content"));
	$.ajax({
		url: 'previousFileUploadView',
		data: formData,
		type: 'POST',
		cache: false,
		contentType: false,
		processData: false,
		beforeSend: function() {
				showLoader();
			},
		success: function(data) {
 			 if(data.status=="SUCCESS"){
				$('#PrevDataTable').empty();
 				for (var i = 0; i < data.body.length; i++) {
 					$('#PrevDataTable').append("<tr>"
					+ "<td nowrap='nowrap' value=" + data.body[i].prevDate + " id='prevDate" + i + "'>" + data.body[i].prevDate + "</td>"
					+ "<td><a href='javascript:prevFileDownload(" + data.body[i].tranId + "," + i + ")'>Download</a></td>"
					+ "</tr>")
			$('#PrevDataTable').show();
			hideLoader();
				}
			}else{
				alert("No Data Found ..")
				hideLoader();
 			}
 		}
	})
}

function prevFileDownload(tranId,i){
	debugger;
	    alert("File are Downloading Please wait ....");
		var token = $("meta[name='_csrf']").attr("content");
		var prevDate = $('#prevDate' + i).text();
 		var url = "previousFileUploadDownload";
		var form = $('<form action="' + url + '" method="post">' +
			'<input type="hidden" id="CSRFToken" name="CSRFToken" value="' + token + '">' +
			'<input type="hidden" id="tranId" name="tranId" value="' + tranId + '">' +
			'<input type="hidden" id="prevDate" name="prevDate" value="' + prevDate + '">' +
			'</form>');
		$('body').append(form);
		form.submit();
}


