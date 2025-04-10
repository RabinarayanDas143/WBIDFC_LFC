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
 		var elementHTML = document.querySelector("#applyLfcPdf");
 		doc.html(elementHTML, {
 			callback : function(doc) {
 				doc.save('Lfc cum encashment.pdf');
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
 
    <div>
	<div class="container" id="applyLfcPdf" style="border: 1px solid black; padding: 20px;">
		<div align="center">
			<b>Application for granting Leave Fare Concession advance and Encashment of Earned Leave</b><br>
  <b>------------------------------------------------------------------------------------------------------------------</b>
		</div>
		<div>
			<br> 
			<div align="right">Date : <b><u>${lfcModel.getLocalDateStr()}</u></b></div>
			The HR Dept.
 			<br> WBIDFCL<br> 36A, H.B. Sarani,
			Kolkata   700001
		    <br> <br> - &nbsp Name of the Employee : <b><u>${lfcModel.getName()}</u></b>
		    <span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</span>
				 Employee No: <b><u>${lfcModel.getId()}</u></b>
			<br><br>
			   - &nbsp	Employee Type : <b><u>${lfcModel.getClassification()}</u></b>
			<br><br>
			- &nbsp	Designation : <b><u>${lfcModel.getDesignation()}</u></b>
			   <span>&emsp;&emsp;&emsp;&emsp;</span>
				Cadre: <b><u>${lfcModel.getDepartment()}</u></b>
			<br><br>
			- &nbsp	Date of joining in WBIDFCL : <b><u>${lfcModel.getDateOfJoining()}</u></b>
			<br><br>
			- &nbsp	Block for which LFC is to be availed : <b><u>${lfcModel.getBlockApplied()}</u></b>
			<br><br>
			<!-- This method check the user is choose is leave Applied or Not -->
			<%
			int leaveApplied = (Integer)session.getAttribute("leaveApplied");
			if(leaveApplied!=0){
 			%>
			- &nbsp	Leave Sanctioned : <b><u>${lfcModel.getNumberofDaysStr()}</u></b>
			  <span>&emsp;&emsp;&emsp;</span> 
				days from  : <b><u>${lfcModel.getLeavefromDateStr()}</u></b> &nbsp&nbsp
				to  : <b><u>${lfcModel.getLeavetoDateStr()}</u></b>
			<br><br>
			<%} %>
			- &nbsp	The place to be visited in India by shortest route for rest & recuperation : <b><u><span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
			           &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</span></u></b>
 			<br><br>
			<%-- 8.	Date & Place of commencement of journey : Date : <b><u>${lfcModel.getCommencementFromDate()}</u></b>
			  <span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</span>
				Place : <b><u>${lfcModel.getPlaceofDestination()}</u></b>
			<br><br>
			9.	Date & Place of return journey : Date : <b><u>${lfcModel.getComplitionToDate()}</u></b>
			    <span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</span>
				Place : <b><u><span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</span></u></b>
			<br><br> --%>
			
			- &nbsp	Date & Place of commencement of journey : Date : <b><u>${lfcModel.getCommencementFromDate()}</u></b>
			  <span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</span>
				Place : <b><u>${lfcModel.getPlaceofOrigination()}</u></b>
			<br><br>
			- &nbsp	Date & Place of return journey : Date : <b><u>${lfcModel.getComplitionToDate()}</u></b>
			    <span>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;</span>
				Place : <b><u>${lfcModel.getPlaceofDestination()}</u></b>
			<br><br>
			
			
			
			
			- &nbsp	Number of days for which encashment of Earned Leave sought (Maximum 30 days) : <b><u>${lfcModel.getEncashmentLeaveCount()}</u></b>
			<br><br>
			- &nbsp	Amount of LFC advance required for purchasing tickets of journey: <b><u>${lfcModel.getAmountofAdvanceStr()}</u></b>
			<br><br>
			- &nbsp	Details of dependants in respect of whom LFC is proposed to be availed:
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
    for(int i=0; i<dependentData.size();i++){ %>
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
								<%j++;}%>							
															</tbody>
														</table>
			<br>
			I declare that the particulars furnished above are true and correct to the best of my knowledge.
		    I undertake to produce the tickets for the outward journey within seven days of receipt of advance.
		    In the event of cancellation of the journey or if I fail to produce the tickets within seven days of receipt of advance,
		    I undertake to refund the entire advance in one lump sum.
		    <br><br><br>
		    <div align="right"><b>Signature of the Employee</b></div>
		    <br><br><br><br><br>
		    <div align="center"><b>For Office Use</b><br>
		             <b>-------------------------------------------</b>
		    
		    </div>
		    <br><br>
		    1.	The particulars furnished by the applicant have been verified. 
		    <br>
		    2.	Date of last availment of LFC by the applicant : ...........................
			<br> 
			3.	LFC Block period for which LFC encashment to be granted : <b><u>${ofcUseData.getBlockApplied()}</u></b>
			<br> 
			4.	Sri / Smt : <b><u>${ofcUseData.getName()}</u></b> &nbsp&nbsp&nbsp along with : <b><u>${ofcUseData.getCount()}</u></b> &nbsp&nbsp&nbsp
			    numbers of Dependent family members may be allowed to avail Leave Fare Concession along with
			    encashment of Earned leave during the block period of <b><u>${ofcUseData.getBlockApplied()}</u></b> &nbsp&nbsp for visiting  <b><u>${lfcModel.getPlaceofDestination()}</u></b>
			    for rest and recuperation.
			<br> 
			5.	Sri/Smt : <b><u>${ofcUseData.getName()}</u></b> &nbsp&nbsp&nbsp has : <b><u>${lfcModel.getEl_LeaveBalance()}</u></b> &nbsp&nbsp&nbsp days of Earned 
			    leave to his/her credit as on date. 
			<br> 
			6.	Earned leave encashment (Max. 30 days salary) for <b><u>${ofcUseData.getEncashmentLeaveCount()}</u></b> &nbsp&nbsp&nbsp days amounting Rs. : <b><u>${lfcModel.getLeaveEncashmentAmountApproved()}</u></b>
			<br> 
			7.	An amount for Rs. : <b><u>${lfcModel.getAdvanceAmountApproved()}</u></b> &nbsp&nbsp&nbsp may be granted as advance for purchasing outward journey ticket.
				Remaining entitlement of LFC may be granted after the tour as per rules.
			<br> 
			8.	Remarks, if any:
			<br><br><br><br><br><br> 
		<div class="row"> <div class="col-xl-6"><b>Signature &Name<br>of the Processing Official</b></div> <div class="col-xl-6"><div ><b align="right">Signature & Name<br>of the Sanctioning Authority</b></div></div></div>
			 
		</div>
		<br>
	</div>
	<div align="center">
	  <button type="button" class="btn btn-success" onclick="printDiv()">Generate Pdf</button>
	</div>
	</div>
</body>
</html>