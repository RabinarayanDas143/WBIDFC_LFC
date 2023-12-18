<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="java.util.ArrayList" %>
<%@ page import="com.emp.model.Lfc_Dependent" %>
<%ArrayList dependentData = (ArrayList)request.getSession().getAttribute("dependent"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"> </script>
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>
 	<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>
<script type="text/javascript">
function printDiv(){
 	debugger;	
 	window.jsPDF = window.jspdf.jsPDF;
 		var doc = new jsPDF();
 		var elementHTML = document.querySelector("#SurrenderLfcPdf");
 		doc.html(elementHTML, {
 			callback : function(doc) {
 				doc.save('Lfc cum surrender.pdf');
 			},
 			margin : [ 10, 10, 10, 10 ],
 			autoPaging : 'text',
 			x : 0,
 			y : 0,
 			width : 190, //target width in the PDF document
 			windowWidth : 675
		//window width in CSS pixels
 		});
	}
</script>
</head>
<body>
	<div class="container" id="SurrenderLfcPdf"
		style="border: 1px solid black; padding: 20px;">
		<div align="center">
			<b>Application for Encashment of Leave Fare Concession</b><br>
<b>---------------------------------------------------------------------------------------</b>
		</div>
		<div>
		<br>
		<div align="right">Date : _______________</div>
		The Head-HR
		<br>
		WBIDFCL
		<br>
		Hemanta Basu Sarani
		<br>
		Kolkata  700001
		<br>
		<div align="center"><b>REG: ENCASHMENT FOR LEAVE FARE CONCESSION</b></div>
		<br>
		1.	Name of the Employee : <b><u>${lfcModel.getName()}</u></b> <span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</span>
				
				 Employee No : <b><u>${lfcModel.getId()}</u></b>
		<br><br>
		2.	Employee Type : Regular / Contractual
		<br><br>
		3.	Designation : <b><u>${lfcModel.getDesignation()}</u></b>
		    <span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</span>
				Cadre : <b><u>${lfcModel.getDepartment()}</u></b>
		<br><br>
		4.	Date of joining in WBIDFCL : <b><u>${lfcModel.getDateOfJoining()}</u></b>
		<br><br>
		5.	Date of last availment of LFC : <b><u>${lfcModel.getLastAvailmentLfcDate()}</u></b>
		<br><br>
		6.	LFC Block period for which LFC encashment to be availed : <b><u>${lfcModel.getBlockApplied()}</u></b>
	    <br><br>
	    7.	Number of days for which encashment of Earned Leave sought (Maximum 30 days) : <b><u>${lfcModel.getEncashmentLeaveCount()}</u></b>
	    <br><br>
	    8.	Details of dependants in respect of whom LFC encashment is proposed to be availed :
	    <br>
	    <table id="dependentTable"  class="table table-striped">
															<thead class="thead-dark">
																<tr style='border: 1px solid black'>
																	 
																	<th style="color: green">Sr.No</th>
																	<th style="color: green">Dependent Name</th>
																	<th style="color: green">DOB</th>
																	<th style="color: green">Occupation</th>
 																	<th style="color: green">Annual Income</th>
																	<th style="color: green">Relation</th>
																</tr>
															</thead>
															<tbody id="#">
															   <% int j=1;
    for(int i=0; i<dependentData.size();i++){%>
    <tr>
    <% Lfc_Dependent model = (Lfc_Dependent)dependentData.get(i); %>
           <td>
              <%= j%>
           </td>
           <td>
              <%= model.getDependentname()%>
           </td>
           <td>
              <%= model.getDateOfBairth()%>
           </td>
           <td>
              <%= model.getOccupation()%>
           </td>
           <td>
              <%= model.getAnnualincome()%>
           </td>
           <td>
              <%= model.getRelation()%>
           </td>
		</tr>													
								<% j++;}%>							
															</tbody>
														</table>
			<br><br>
			I declare that the particulars furnished above are true and correct to the best of my knowledge. 
			<br><br><br>
			<div align="right"><b>Signature of the Employee</b></div>
			<br><br><br><br><br><br>
			<div align="center"><b>For Office Use</b><br>
			          <b>-------------------------------------------</b>
 			</div>
			<br>
	    1.	The particulars furnished by the applicant has been verified. Sri/Smt : <b><u>${ofcUseData.getName()}</u></b> &nbsp&nbsp&nbsp 
	        along with : <b><u>${ofcUseData.getCount()}</u></b> &nbsp&nbsp&nbsp&nbsp numbers of Dependent family members may be allowed to avail LFC encashment during 
	        the block period : <b><u>${ofcUseData.getBlockApplied()}</u></b>
	    <br> 
         2.	The net entitlement on account of LFC encashment works out to Rs. .............. As detailed below.
			<br><br><br><br><br><br><br><br><br><br><br><br>
		3.	Sri/Smt : <b><u>${ofcUseData.getName()}</u></b> &nbsp&nbsp&nbsp	 has &nbsp&nbsp<b><u>${ofcUseData.getEl_LeaveBalance()}</u></b>&nbsp&nbsp days of Earned leave to his/her credit as on date.
		     Earned leave encashment for the block period : <b><u>${ofcUseData.getBlockApplied()}</u></b> &nbsp&nbsp&nbsp for &nbsp&nbsp<b><u>${ofcUseData.getEncashmentLeaveCount()}</u></b>&nbsp&nbsp days  amounting Rs. ............ as detailed 
		     below may be paid.  
		     <br><br><br><br><br><br>
		     <b>Name of the Processing Officer</b>
		      <div align="right"><b>Name of the Sanctioning Officer</b></div>
		</div>
		 
	</div>
	<div align="center">
	  <button type="button" class="btn btn-success" onclick="printDiv()">Generate Pdf</button>
	</div>
</body>
</html>