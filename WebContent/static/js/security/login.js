$(document)
		.ready(
				function() {
					$('#endSession')
							.click(
									function() {
										$("#Loader").show();
										debugger;
										try {
											var user_id = $.trim($('#userid')
													.val());
											if (user_id == ''
													|| user_id == undefined) {
												throw new Error(
														"User not available.");
											}
											$('body').find('#sessionForm')
													.remove();
											var myForm = '<form id="sessionForm" name="sessionForm" action="InvalidateSession" method="post">';
											myForm += '<input type="hidden" id="user_id" name="user_id" value="'
													+ user_id + '" >';
											myForm += '</form>';
											$('body').append(myForm);
											$('form#sessionForm').submit();
											$("#Loader").hide();
										} catch (e) {
											alert(e.message);
											$("#Loader").hide();
										}
									});

				});
// functions
function getIEVersion() {
	var match = navigator.userAgent.match(/(?:MSIE |Trident\/.*; rv:)(\d+)/);
	return match ? parseInt(match[1]) : undefined;
}

if (getIEVersion() < 9) {
	document.location = "NewFile.jsp";
}

function isValidLogin() {
	var valid = true;
	$("#auserName").text('');
	$("#apassword").text('');
	$(".valid").each(function() {
		if ((this.value == "") || (this.value == null)) {
			$("#a" + this.id).text("Please enter " + this.id);
			valid = false;
			return valid;
		}
	});
	if (valid) {
		$("#Loader").show();
		debugger;
		var iterationCount = 1000;
		var keySize = 128;
		var password = $('#password').val();
		var passphrase = 'intechsecure';
		var iv = CryptoJS.lib.WordArray.random(128 / 8).toString(
				CryptoJS.enc.Hex);
		var salt = CryptoJS.lib.WordArray.random(128 / 8).toString(
				CryptoJS.enc.Hex);
		var aesUtil = new AesUtil(keySize, iterationCount);
		var ciphertext = aesUtil.encrypt(salt, iv, passphrase, password);
		$('#passphrase').val(passphrase);
		$('#iv').val(iv);
		$('#salt').val(salt);
		$('#ciphertext').val(ciphertext);
		$('#iterationCount').val(iterationCount);
		$('#keySize').val(keySize);
		var uname = $('#userId').val();
		$('#userName').val(uname);
		$('#secureForm').submit();
	}
}
