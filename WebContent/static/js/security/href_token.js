$('document').ready(
	function() {
//			alert("load token")
	});

function onSubmitMenu(self) {
	debugger;
	var token = $("meta[name='_csrf']").attr("content")
//	alert(token)
	$('body').find('#sessionMenus').remove();
	var myForm = '<form id="sessionMenus" name="sessionMenus" action="' + self + '" method="post">';
	myForm += '<input type="hidden" id="CSRFToken" name="CSRFToken" value="' + token + '" >';
	myForm += '</form>';
	$('body').append(myForm);
	$('form#sessionMenus').submit();
}

//href="javascript:onSubmitMenu('addUserRequest.do');