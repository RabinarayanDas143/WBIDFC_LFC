<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
<meta content="Admin Dashboard" name="description" />
<meta content="Mannatthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />


</head>


<body>

	<!-- Begin page -->
	<div class="accountbg"></div>
	<div class="wrapper-page">

		<div class="card">
			<div class="card-block">

				<div class="ex-page-content text-center">
					<h2>Application Error, please contact support.</h2>
					<h3>Debug Information:</h3>
					Requested URL= ${url} <br> <br> Exception=
					${exception.message} <br> <br> <strong>Exception
						Stack Trace</strong> <br>
					<c:forEach items="${exception.stackTrace}" var="ste">ss
	${ste}
</c:forEach>
					<br> <a class="btn btn-danger mb-5 waves-effect waves-light"
						href="index.html">Back to Dashboard</a>
				</div>

			</div>
		</div>
	</div>
</body>
</html>