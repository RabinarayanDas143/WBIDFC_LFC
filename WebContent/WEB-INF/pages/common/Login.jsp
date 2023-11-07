<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!-- Login_old -->



<!DOCTYPE html>
<html lang="zxx" class="js">
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<head>
    
    <meta charset="utf-8">
    <meta name="author" content="Softnio">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="A powerful and conceptual apps base dashboard template that especially build for developers and programmers.">
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
    
    <!-- Fav Icon  -->
    <link rel="shortcut icon" href="./images/favicon.png">
    <!-- Page Title  -->
    <title>Login | iHRMS</title>
    <!-- StyleSheets  -->
    <link rel="stylesheet" href="static/resources/assets/css/dashlite.css?ver=3.1.1">
    <link id="skin-default" rel="stylesheet" href="static/resources/assets/css/theme.css?ver=3.1.1">
</head>

<body class="nk-body bg-white npc-general pg-auth">
    <div class="nk-app-root">
        <!-- main @s -->
        <div class="nk-main ">
            <!-- wrap @s -->
            <div class="nk-wrap nk-wrap-nosidebar">
                <!-- content @s -->
                <div class="nk-content ">
                    <div class="nk-split nk-split-page nk-split-md">
                        <div class="nk-split-content nk-block-area nk-block-area-column nk-auth-container bg-white">
                            <div class="nk-block nk-block-middle nk-auth-body">
                                <div class="brand-logo pb-5">
                                    <a href="html/index.html" class="logo-link">i-HRMS</a>
                                </div>
                                <div class="nk-block-head">
                                    <div class="nk-block-head-content">
                                        <h5 class="nk-block-title">Sign-In</h5>
                                        <div class="nk-block-des">
                                            <p>i-HRMS Employee Self Services</p>
                                        </div>
                                    </div>
                                </div><!-- .nk-block-head -->
                                <form:form action="Login" class="form-horizontal m-t-20"
						method="post" modelAttribute="login" id="secureForm">
                                    <div class="form-group">
                                        <div class="form-label-group">
                                            <label class="form-label" for="default-01">Email or Username</label>
                                            <!-- <a class="link link-primary link-sm" tabindex="-1" href="#">Need Help?</a> -->
                                        </div>
                                        <div class="form-control-wrap">
                                            <form:input path="userid" placeholder="emplycode" id="userid"
									required="required" class="form-control form-control-lg input-txt  valid"
									autocomplete="off" style="text-transform: uppercase;" />
								<spring:bind path="userid">
									<c:if test="${status.error}">
										<img src="images/error.png" style="vertical-align: center;"
											title="${status.errorMessage}" />
									</c:if>
								</spring:bind>
								<span id="auserName" style="color: red; font-size: 12px;"></span>
                                            
                                           <!--  
                                            <input type="text" class="form-control form-control-lg" id="default-01" placeholder="Enter your email address or username"> -->
                                        </div>
                                    </div><!-- .form-group -->
                                    <div class="form-group">
                                        <div class="form-label-group">
                                            <label class="form-label" for="password">Passcode</label>
                                            <a class="link link-primary link-sm" tabindex="-1" href="html/pages/auths/auth-reset-v3.html">Forgot Code?</a>
                                        </div>
                                        <div class="form-control-wrap">
                                           <!--  <a tabindex="-1" href="#" class="form-icon form-icon-right passcode-switch lg" data-target="password">
                                                <em class="passcode-icon icon-show icon ni ni-eye"></em>
                                                <em class="passcode-icon icon-hide icon ni ni-eye-off"></em>
                                            </a> -->
                                            
                                            <form:password path="password" placeholder="Password"
						required="required" id="password" autocomplete="off"
						class="form-control form-control-lg input-txt valid" />
					<spring:bind path="password">
						<c:if test="${status.error}">
							<img src="images/error.png" style="vertical-align: middle;"
								title="${status.errorMessage}" />
						</c:if>
					</spring:bind>
					<span id="apassword" style="color: red; font-size: 12px;"></span>
                                            
                                            <!-- <input type="password" class="form-control form-control-lg" id="password" placeholder="Enter your passcode"> -->
                                        </div>
                                    </div><!-- .form-group -->
                                    <div class="form-group">
                                        <button id="signIn" class="btn btn-lg btn-primary btn-block" onclick="isValidLogin()">Sign in</button>
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
                                    <!-- form -->
                                <!-- <div class="form-note-s2 pt-4"> New on our platform? <a href="html/pages/auths/auth-register-v3.html">Create an account</a>
                                </div>
                                <div class="text-center pt-4 pb-3">
                                    <h6 class="overline-title overline-title-sap"><span>OR</span></h6>
                                </div>
                                <ul class="nav justify-center gx-4">
                                    <li class="nav-item"><a class="nav-link" href="#">Facebook</a></li>
                                    <li class="nav-item"><a class="nav-link" href="#">Google</a></li>
                                </ul>
                                <div class="text-center mt-5">
                                    <span class="fw-500">I don't have an account? <a href="#">Try 15 days free</a></span>
                                </div> -->
                            </div><!-- .nk-block -->
                            <div class="nk-block nk-auth-footer">
                                <!-- <div class="nk-block-between">
                                    <ul class="nav nav-sm">
                                        <li class="nav-item">
                                            <a class="nav-link" href="#">Terms & Condition</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#">Privacy Policy</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#">Help</a>
                                        </li>
                                        
                                    </ul>.nav
                                </div> -->
                                <div class="mt-3">
                                    <p>&copy; 2023 iHRMS - Design and developed by IDBI Intech LTD.</p>
                                </div>
                            </div><!-- .nk-block -->
                        </div><!-- .nk-split-content -->
                        <div class="nk-split-content nk-split-stretch bg-abstract"></div><!-- .nk-split-content -->
                    </div><!-- .nk-split -->
                </div>
                <!-- wrap @e -->
            </div>
            <!-- content @e -->
        </div>
        <!-- main @e -->
    </div>
    <!-- app-root @e -->
    <!-- JavaScript -->
    <script src="static/resources/assets/js/bundle.js?ver=3.1.1"></script>
    <script src="static/resources/assets/js/scripts.js?ver=3.1.1"></script>
    
</html>