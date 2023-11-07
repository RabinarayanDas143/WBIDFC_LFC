$("document").ready(function() {

});

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
		success: function(data) {
			if (data.status == "FAILED") {
				alert(" You Applied Surrender Cum Encashment , Please Download Surrender Cum Encashment File .");
			} else {
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
		success: function(data) {
			if (data.status == "FAILED") {
				alert(" You Applied LFC Cum Encashment , Please Download LFC Cum Encashment File .");
			} else {
				location.href = "javascript:onSubmitMenu('surrenderLfcPdfGenerate')";
			}
		}
	})

}