<%@page import="com.itextpdf.text.log.SysoCounter"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="static/js/hrms/lfcDetail.js"></script>
<%@page language="java" session="true"%>
<%@page import="java.util.*"%>
<div class="nk-header is-light" style="background-color: darkblue;">
	<div class="container-fluid">
		<div class="nk-header-wrap">
			<div class="nk-menu-trigger me-sm-2 d-lg-none">
				<a href="#" class="nk-nav-toggle nk-quick-nav-icon"
					data-target="headerNav"><em class="icon ni ni-menu"></em></a>
			</div>
			<div class="nk-header-brand">
				<a href="http://192.168.1.234:8080/iHRMS/user-main-dashboard"
					class="logo-link"> <img class="logo-img"
					src="static/resources/assets/images/logo_1.jpg"
					srcset="./images/logo2x.png 2x" alt="logo"> <!--  <img class="logo-dark logo-img" src="./images/logo-dark.png" srcset="./images/logo-dark2x.png 2x" alt="logo-dark">  -->
				</a>
			</div>

			<%
			String a = (String) session.getAttribute("role");

			if (a == null) {
				a = "rabi";
			}

			//System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa :"+a+":Ending hereeeeeeeeeeeeeee");
			%>
			<!-- .nk-header-brand -->
			<div class="nk-header-menu ms-auto" data-content="headerNav">
				<div class="nk-header-mobile">
					<div class="nk-header-brand">
						<a href="html/index.html" class="logo-link"> <img
							class="logo-light logo-img"
							src="static/resources/assets/images/logo_1.jpg"
							srcset="./images/logo2x.png 2x" alt="logo"> <img
							class="logo-dark logo-img" src="./images/logo-dark.png"
							srcset="./images/logo-dark2x.png 2x" alt="logo-dark">
						</a>
					</div>
					<div class="nk-menu-trigger me-n2">
						<a href="#" class="nk-nav-toggle nk-quick-nav-icon"
							data-target="headerNav"><em class="icon ni ni-arrow-left"></em></a>
					</div>
				</div>

				<!-- Menu -->

				<ul class="nk-menu nk-menu-main ui-s2">

					<li class="nk-menu-item has-sub"><a href="#"
						class="nk-menu-link nk-menu-toggle"> <span
							class="nk-menu-text" style="color: white;">LFC/Surrender
								Apply</span>
					</a>
						<ul class="nk-menu-sub">

							<li class="nk-menu-item"><a
								href="javascript:onSubmitMenu('lfcDetail');"
								class="nk-menu-link"> <span class="nk-menu-text">Apply
										LFC/Surrender Cum Encashment</span>
							</a></li>


							<%-- <%
							if (a.equalsIgnoreCase("rabi")) {
							%>
							<li class="nk-menu-item"><a
								href="javascript:onSubmitMenu('userReport');"
								class="nk-menu-link"> <span class="nk-menu-text">Encashment
										Report</span>
							</a></li>
							<li class="nk-menu-item"><a
								href="javascript:onSubmitMenu('userSurrenderReport');"
								class="nk-menu-link"> <span class="nk-menu-text">Surrender
										Report</span>
							</a></li>
							<%
							}
							%> --%>
							<%
							if (a.equalsIgnoreCase("INTERNAL AUDIT") || a.equalsIgnoreCase("HR & ADMINISTRATION")
									|| a.equalsIgnoreCase("COMPANY SECRERTARIAT")) {
							%>

                    <!--  --------------------- 3 level Admin Authorization for user --------------- -->
							<!-- <li class="nk-menu-item"><a
								href="javascript:onSubmitMenu('leaveRequest');"
								class="nk-menu-link"> <span class="nk-menu-text">Encashment
										Request Authorization</span>
							</a></li>

							<li class="nk-menu-item"><a
								href="javascript:onSubmitMenu('adminSurrenderRequest');"
								class="nk-menu-link"> <span class="nk-menu-text">Surrender
										Request Authorization</span>
							</a></li> -->
							
					<!--  --------------------- 3 level Admin Authorization for user End--------------- -->
                   <!-- ------------Report ___ Gone-----------  -->
							<!-- <li class="nk-menu-item"><a
								href="javascript:onSubmitMenu('Report');" class="nk-menu-link">
									<span class="nk-menu-text">Encashment Report</span>
							</a></li>

							<li class="nk-menu-item"><a
								href="javascript:onSubmitMenu('SurrenderReport');"
								class="nk-menu-link"> <span class="nk-menu-text">Surrender
										Report</span>
							</a></li> -->
                    <!--  --------------------------Report Gone End---------------- -->
							<!-- </ul> -->

							<%
							}
							%>
							<%-- <%
							if (a.equalsIgnoreCase("INTERNAL AUDIT") || a.equalsIgnoreCase("COMPANY SECRERTARIAT")) {
							%>
							<li class="nk-menu-item"><a
								href="javascript:onSubmitMenu('AdminleaveRequest');"
								class="nk-menu-link"> <span class="nk-menu-text">Admin
										Encashment Request Authorization</span>
							</a></li>

							<li class="nk-menu-item"><a
								href="javascript:onSubmitMenu('AdminSurrenderRequest');"
								class="nk-menu-link"> <span class="nk-menu-text">Admin
										Encashment Surrender Request Authorization</span>
							</a></li>

							<%
							}
							%> --%>
						</ul></li>
                   <!--  ------------------------------ Separation headerWise ------------------------ -->
                   
                   
					<li class="nk-menu-item has-sub">
					<%   if (a.equalsIgnoreCase("INTERNAL AUDIT") || a.equalsIgnoreCase("HR & ADMINISTRATION")
									|| a.equalsIgnoreCase("COMPANY SECRERTARIAT")) {
							%>
					<a href="#"
						class="nk-menu-link nk-menu-toggle"> <span
							class="nk-menu-text" style="color: white;">LFC/Surrender
								Authorization</span></a>
					<%}	%>
					   <ul class="nk-menu-sub">
					     <%
							if (a.equalsIgnoreCase("INTERNAL AUDIT") || a.equalsIgnoreCase("HR & ADMINISTRATION")
									|| a.equalsIgnoreCase("COMPANY SECRERTARIAT")) {
							%>
							  <li class="nk-menu-item"><a
								href="javascript:onSubmitMenu('leaveRequest');"
								class="nk-menu-link"> <span class="nk-menu-text">LFC Cum Encashment
										  Authorization</span>
							</a></li>

							<li class="nk-menu-item"><a
								href="javascript:onSubmitMenu('adminSurrenderRequest');"
								class="nk-menu-link"> <span class="nk-menu-text">Surrender Cum Encashment
										  Authorization</span>
							</a></li>
							<%
							}
							%>
							
							<%
							if (a.equalsIgnoreCase("INTERNAL AUDIT") || a.equalsIgnoreCase("COMPANY SECRERTARIAT")) {
							%>
							<li class="nk-menu-item"><a
								href="javascript:onSubmitMenu('AdminleaveRequest');"
								class="nk-menu-link"> <span class="nk-menu-text">Admin
										LFC Cum Encashment Authorization</span>
							</a></li>

							<li class="nk-menu-item"><a
								href="javascript:onSubmitMenu('AdminSurrenderRequest');"
								class="nk-menu-link"> <span class="nk-menu-text">Admin
										Surrender Cum Encashment Authorization</span>
							</a></li>

							<%
							}
							%>
					   
					   </ul>
					</li>

					<li class="nk-menu-item has-sub"><a href="#"
						class="nk-menu-link nk-menu-toggle"> <span
							class="nk-menu-text" style="color: white;">Report</span>
					</a>
					
					   <ul class="nk-menu-sub">
					     <%
							if (a.equalsIgnoreCase("rabi")) {
							%>
							<li class="nk-menu-item"><a
								href="javascript:onSubmitMenu('userReport');"
								class="nk-menu-link"> <span class="nk-menu-text">LFC Cum Encashment
										Report</span>
							</a></li>
							<li class="nk-menu-item"><a
								href="javascript:onSubmitMenu('userSurrenderReport');"
								class="nk-menu-link"> <span class="nk-menu-text">Surrender Cum Encashment
										Report</span>
							</a></li>
							<%
							}
							%>
							
							<%
							if (a.equalsIgnoreCase("INTERNAL AUDIT") || a.equalsIgnoreCase("HR & ADMINISTRATION")
									|| a.equalsIgnoreCase("COMPANY SECRERTARIAT")) {
							%>
							  <li class="nk-menu-item"><a
								href="javascript:onSubmitMenu('Report');" class="nk-menu-link">
									<span class="nk-menu-text">LFC Cum Encashment Report</span>
							</a></li>

							<li class="nk-menu-item"><a
								href="javascript:onSubmitMenu('SurrenderReport');"
								class="nk-menu-link"> <span class="nk-menu-text">Surrender Cum Encashment
										Report</span>
							</a></li>
							
							<%
							}
							%>
					   
					   </ul>
					
					</li>

					<li class="nk-menu-item has-sub"><a href="#"
						class="nk-menu-link nk-menu-toggle"> <span
							class="nk-menu-text" style="color: white;">Pdf Download</span>
					</a>
						<ul class="nk-menu-sub">
							<li class="nk-menu-item"><a
								href="javascript:onSubmitMenu('LFC');" class="nk-menu-link">
									<span class="nk-menu-text"> PDF Generation</span>
							</a></li>
						</ul></li>

				</ul>
				<!-- .nk-menu -->

				<!-- Menu -->



			</div>
			<!-- .nk-header-menu -->
			<div class="nk-header-tools">
				<ul class="nk-quick-nav">

					<li class="dropdown user-dropdown"><!-- <div>Rabi</div> --><a href="#"
						class="dropdown-toggle" data-bs-toggle="dropdown">
							<div class="user-toggle">
								<div class="user-avatar sm">
									<em class="icon ni ni-user-alt"></em>
								</div>
								
							</div>
					</a><div style="color: white;">${userName}<br>${Designation}</div>
						<div
							class="dropdown-menu dropdown-menu-md dropdown-menu-end dropdown-menu-s1 is-light">

                           

							<div class="dropdown-inner">
								<ul class="link-list">
									<li><a href="javascript:onSubmitMenu('Logout');"><em
											class="icon ni ni-signout"></em><span>Home</span></a></li>
								</ul>
							</div>
						</div></li>
					<!-- .dropdown -->
				</ul>
				<!-- .nk-quick-nav -->
			</div>
			<!-- .nk-header-tools -->
		</div>
		<!-- .nk-header-wrap -->
	</div>
	<!-- .container-fliud -->
</div>
<!-- main header @e -->