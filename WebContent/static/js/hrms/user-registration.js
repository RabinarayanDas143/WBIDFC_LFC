$('document').ready(function() {
	$('#date_str').datepicker({
		dateFormat : 'd-M-yy',
		changeMonth : true,
		changeYear : true,
		yearRange : "c:c+5"
	});
	$('#date_end').datepicker({
		dateFormat : 'd-M-yy',
		changeMonth : true,
		changeYear : true,
		yearRange : "c:c+5"
	});
	fetchApplicationList();
	viewRegistration();
	var jsonArray = [];
	$("#user_reg").on('click', function() {
		var valid = true;
		$(".req").each(function() {
			if (this.value == "") {
				alert("please enter detail " + this.id);
				valid = false;
				return valid;
			}
		});
		if (valid) {
			$("#Loader").show();
			jsonArray.length = 0;
			var jsonObj = {};
			jsonObj["emply_cd"] = $("#emply_cd").val();
			jsonObj["name"] = $("#emply_nm").val();
			jsonObj["dateStart"] = $("#date_str").val();
			jsonObj["dateEnd"] = $("#date_end").val();
			var test = new Array();
			$("input[name='checkBoxApp']:checked").each(function() {
				test.push($(this).val());
			});
			jsonObj["rolelist"] = test.toString();
			jsonArray.push(jsonObj);
			$.ajax({
				url : 'reg-create',
				data : {
					CSRFToken : $("meta[name='_csrf']").attr("content"),
					userdata : JSON.stringify(jsonArray)
				},
				async : false,
				type : 'post',
				success : function(data) {
					alert(data.message);
					$("#Loader").hide();
					window.location.reload();
				},
				error : function(error) {
					alert(error);
					$("#Loader").hide();
				}
			});
		}
	});
});

function fetchApplicationList() {
	$.ajax({
		url : 'reg-app',
		data : {
			CSRFToken : $("meta[name='_csrf']").attr("content")
		},
		async : false,
		type : 'post',
		success : function(data) {
			// console.log("data", data);
			$("#Loader").hide();
			var master = data.body;
			for (var i = 0; i < master.length; i++) {
				$("#accessDiv")
						.append(
								"<input class=''custom-control-input' type='checkbox' id='app"
										+ i + "' name='checkBoxApp'  value='"
										+ master[i].appname + "'> "
										+ master[i].appname);
			}
		},
		error : function(error) {
			alert(error);
			$("#Loader").hide();
		}

	});
}

function viewRegistration() {
	$.ajax({
		url : 'reg-view',
		data : {
			CSRFToken : $("meta[name='_csrf']").attr("content")
		},
		async : false,
		type : 'post',
		success : function(data) {
			console.log("data", data);
			if (data.status == "SUCCESS") {
				var count = 1;
				var response = data.body;
				$.each(response, function(i, item) {
					$('<tr>').html(
							"<td><span id='stt" + i + "' style='display:none'>"
									+ callData(response[i].status)
									+ "</span><span id='emp" + i + "'>"
									+ callData(response[i].emply_cd)
									+ "</span></td><td>"
									+ callData(response[i].name) + "</td><td>"
									+ callData(response[i].dateStart)
									+ "</td><td>"
									+ callData(response[i].dateEnd)
									+ "</td><td>"
									+ status(response[i].status, i) + "</td>")
							.appendTo("#table_idViewBody");
					count++;

				});
				/*
				 * var table = $('#table_idView').DataTable({ paging : false,
				 * ordering : false });
				 * $('#table_idView').DataTable().columns.adjust();
				 */
			}

		},
		error : function(error) {
			alert(error);
			$("#Loader").hide();
		}
	});
}

function viewProcess(id) {
	if (confirm("do you want to proceed")) {
		var emp = $("#emp" + id).text();
		var st = $("#stt" + id).text();
		// alert(st);
		$.ajax({
			url : 'reg-process',
			data : {
				CSRFToken : $("meta[name='_csrf']").attr("content"),
				emplycd : emp,
				stat : st
			},
			async : false,
			type : 'post',
			success : function(data) {
				// console.log("data", data);
				alert(data.message);
				viewRegistration();
				$("#Loader").hide();
			},
			error : function(error) {
				alert(error);
				$("#Loader").hide();
			}
		});
	}
}

function status(stat, id) {
	if (stat == "A") {
		return '<input type="button" value="Disabled" onclick="viewProcess('
				+ id + ')">';
	} else {
		return '<input type="button" value="Enabled"  onclick="viewProcess('
				+ id + ')">';
	}
}
function callData(d) {
	var data = "";
	if (d != null) {
		data = d;
	}
	return data
}