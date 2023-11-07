<!DOCTYPE html>
<html lang="en">
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<title>i-HRMS</title>
<meta content="Admin Dashboard" name="description" />
<meta content="Mannatthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<meta name="_csrf" content="${CSRFToken}" />
<meta name="_csrf_header" content="CSRFToken" />
<spring:htmlEscape defaultHtmlEscape="true" />
<script type="text/javascript">
	document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
</script>
<script type="text/javascript" src="static/js/security/login.js"></script>
<script type="text/javascript" src="static/js/security/aes.js"></script>
<script type="text/javascript" src="static/js/security/AesUtil.js"></script>
<script type="text/javascript" src="static/js/security/pbkdf2.js"></script>

</head>

<body>


	<!-- Begin page -->
	<div class="accountbg"></div>
	<div class="wrapper-page">

		<div class="card">
			<div class="card-body">
				<div class="p-3">
					<form:form action="Login" class="form-horizontal m-t-20"
						method="post" modelAttribute="login" id="secureForm">
						<div class="form-group row">
							<div class="col-12">

								<form:input path="userid" placeholder="emplycode" id="userid"
									required="required" class="form-control input-txt  valid"
									autocomplete="off" style="text-transform: uppercase;" />
								<spring:bind path="userid">
									<c:if test="${status.error}">
										<img src="images/error.png" style="vertical-align: center;"
											title="${status.errorMessage}" />
									</c:if>
								</spring:bind>
								<span id="auserName" style="color: red; font-size: 12px;"></span>


								<!-- <input class="form-control" type="text" required="" placeholder="Username"> -->
							</div></div>

			<div class="form-group row">
				<div class="col-12">

					<form:password path="password" placeholder="Password"
						required="required" id="password" autocomplete="off"
						class="form-control input-txt valid" />
					<spring:bind path="password">
						<c:if test="${status.error}">
							<img src="images/error.png" style="vertical-align: middle;"
								title="${status.errorMessage}" />
						</c:if>
					</spring:bind>
					<span id="apassword" style="color: red; font-size: 12px;"></span>

					<!-- <input class="form-control" type="password" required="" placeholder="Password"> -->
				</div>
			</div>


			<div class="form-group text-center row m-t-20">
				<div class="col-12">
					<button id="signIn"
						class="btn btn-danger btn-block waves-effect waves-light"
						type="button" onclick="isValidLogin()">Sign in</button>
					<!-- <button class="btn btn-danger btn-block waves-effect waves-light" type="submit">Log In</button> -->
				</div>
			</div>

			<div style="margin-top: 5px; margin-bottom: 5px">
				<c:if test="${error_msg != null }">
					<div align="center" style="color: red" class="errorMsg">
						<c:out value="${error_msg}" escapeXml="false" />
					</div>
				</c:if>
				<c:if test="${success_msg != null}">
					<div align="center" style="color: green" class="successMsg">
						<c:out value="${success_msg}" escapeXml="false" />
					</div>
				</c:if>
			</div>
			<input type="hidden" id="passphrase" name="passphrase"> <input
				type="hidden" id="iv" name="iv"> <input type="hidden"
				id="salt" name="salt"> <input type="hidden" id="ciphertext"
				name="ciphertext"> <input type="hidden" id="keySize"
				name="keySize"> <input type="hidden" id="iterationCount"
				name="iterationCount"> <input type="hidden" name="CSRFToken"
				value="${CSRFToken}" />
			</form:form>
		</div>

	</div>
	</div>
	</div>


</body>
</html>
