<%@ page language="java"  import="java.util.*, java.util.Calendar" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="static/js/hrms/festival.js?v="+ Date.now() + Math.random()"></script>          

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOAN APPLICATION DB</title>
        <script type="text/javascript" src="../js/datepicker.js"></script>
    </head>
    
    <!-- Festival advance start -->
    <body bgcolor="#fof8f7" onload="loadFunc()" >
        <form name="festappl" method="post" action="SaveApplcn.jsp" >
            <blockquote>
                <div align="justify">
                	<div align="center"><b><u>Festival Advance</u></b></div><br/>
                   
                    The General Manager,<br>
                    Chaitanya Godavari Grameena Bank<br>
                    Administration Department,<br>
                    Guntur.<br>&nbsp;<br>
                    Dear Sir,
                    <!-- <div align="center"><b><u>Festival Advance</u></b></div><br/> -->
                    <input type=hidden name="lncode" value="lnrcvry_cd" />
                    <input type=hidden name="classId" value = "classId" />
                    <input type=hidden name="lnclass_cd" value = "lnclass_cd" />
                    <input type=hidden name="lnstts" value = "N" />

                    I request that I may be granted an advance 
                    <input type="hidden" name="festivalDate" value="0" />
                    <input type="hidden" name="festivalDate1" value="1" />
                    <input type="hidden" name="festivalDate2" value="2" />
                    of 
                    <br>Rs. 
                   <%--  <%if (lnmax_amt != basicSalary) {
                            // System.out.println(lnmax_amt + " : "+basicSalary + " : " +amt);
                    %> <input type="radio" name="amount" checked ="true" onclick="txtDisalbe()" value="<%=lnmax_amt%>" ><b><%=lnmax_amt%> </b> --%>
                    (Minimum Amount ) Or
                    <br>
                    <br>
                    Rs. <input type="radio" name="amount" value="2" onclick="txtEnable()" > <input type="text" id ="txtamt" name ="txtamt">(Multiples of 1000)
                    Or <br>
                    <br> Rs. 
                    <%-- <input type="radio" name="amount" value="<%=amt%> "  onclick="txtDisable()"><b><%=amt%></b> 
                    (Maximum Amount Equivalent to One Pay) <%} else if (lnmax_amt == amt) {%>
                    Rs. <input type="radio" name="amount" value="<%=lnmax_amt%>" checked="true"><b><%=lnmax_amt%></b> (Minimum Amount)
                    Rs. <input type="hidden" name="amount" value="<%=lnmax_amt%>"  />
                    <%}%> --%>
                    </p>

                    <br/>

                    <p><b><u>I declare that</u></b> I haven not availed any Festival advance during the current calendar year 
                        and the previous Festival Advance granted to me during the last calendar year has been fully repaid.</p>
                    <p><b><u>I hereby authorize</b></u> for recovery of pending installments if any, 
                        from my salary/ saving bank account with the bank. </p>

                    <br/>

                    <p>Yours faithfully</p>
                    Name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input readonly="true" type="text" name="emplName" size="80" value="objBasicDetail.getEmply_nm()" /><br>

                    Employee Code&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input readonly="true" type="text" name="lnempcd" size="8" value="objBasicDetail.getEmply_cd()" /><br>

                    Date of Appointment&nbsp;
                    <input readonly="true" type="text" name="T1" size="10" value="objBasicDetail.getPrevJnngDt()" /><br>

                    Office / Department&nbsp;&nbsp;&nbsp;
                    <input readonly="true" type="text" name="T1" size="20" value="Corporate Center." /><br>
                    <p align="center"><input type="submit" id="saveFestApplication" name="sub" value="Save Application" ></p>
                </div>
            </blockquote>
        </form>
    </body>
    <!-- Festival advance end -->
   
</body>
</html>

