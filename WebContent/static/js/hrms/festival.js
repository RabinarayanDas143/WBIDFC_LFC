$("document")
		.ready(
				function() {
					$("#financial_year").hide();
					$("#allowance_name").hide();
					
					
					$("#monthly_allowances").change(function(){
						/*alert("ok");*/
						var val = $("#monthly_allowances").val();
						if(val == "newspaper")
						{
							$("#financial_year").show();
							$("#allowance_name").show();
							
						}
						else if(val == "food_beverages")
						{
							$("#financial_year").show();
							$("#allowance_name").show();
							
						}
						else if(val == "refreshments")
						{
							$("#financial_year").show();
							$("#allowance_name").show();
							
						}
						else if(val == "conveyance")
						{
							$("#financial_year").show();
							$("#allowance_name").show();
							
						}
						else if(val == "telephone")
						{
							$("#financial_year").show();
							$("#allowance_name").show();
							
						}
					})
					
					
					
					/*if($("#monthly_allowances").val()==""){
						
					}*/
					
					$('#purchase_dt').datepicker({
						dateFormat : 'd-M-yy'
					});
					

					/*$('#month_year').datepicker({
						dateFormat : 'yyyy-mm'
					});*/
					
					$("#btn")
							.on(
									'click',
									function() {
										debugger;
										

									});
					
					$("#add-ecdOffice").on(
							'click',
							function() {
								debugger;
									var jsonArray = [];
									jsonArray.length = 0
									var jsonObj = {};
									
									jsonObj["cd"] = $("#cd").val();
									jsonObj["solid"] = $("#solid").val();
									j
									
									
									jsonArray.push(jsonObj);
									
									$.ajax({
										url: 'addEcdOffice',
										data: {
											CSRFToken: $("meta[name='_csrf']").attr("content"),
											userData: JSON.stringify(jsonArray)
										},
										async: false,
										type: 'post',
										success: function(data) {
											alert("Added Successfully");
											window.location.reload();
										},
										error: function(error) {
											alert("Failed");
										}
									})
								
					});
					
					
					function employeeMaster() {
						alert("emp");
						var valid = true;
						if (valid) {
							$.ajax({
								url : 'employeeDetails',
								data : {
									CSRFToken : $("meta[name='_csrf']").attr("content"),
									//emply_cd : $("#emply_cd").val()
								},
								async : false,
								type : 'post',
								success : function(data) {
									/*if (data.status == "SUCCESS") {
										$("#name").val(data.body.employeeDetail.nm);
										$("#designation").val(data.body.designDetail.dscrptn);
									} else {
										// alert(data.message);
									}*/
								},
								error : function(error) {
									
									alert(error);
								}
							});
						}

					}
					
					
				});


            function click(e) {
                if (document.all) {
                    if (event.button == 2) {
                        alert(message1);
                        return false;
                    }
                }
                if (document.layers) {
                    if (e.which == 3) {
                        alert(message1);
                        return false;
                    }
                }
            }
            if (document.layers) {
                document.captureEvents(Event.MOUSEDOWN);
            }
            document.onmousedown = click;
       

       
            function checkField() {
                var productName = document.getElementById("PrdName").value;

                if (productName == "") {
                    alert("Please enter product Name");
                    return false;
                }
                else {
                    return true;
                }
                return false
            }

            function validate() {
                if (checkField() && checkNumber()) {
                    return true;
                }
                else {
                    return false;
                }
            }

            // -----------------------------------------------------------------------------
           /* function checkAmounts1(elementId) {
                var amount = document.getElementById(elementId).value;
                if (amount == 0 || amount > <%=lnmax_amt%>) {
                    alert("Please enter amount less than or equal to " +<%=lnmax_amt%> + ".");
                    return false;
                }
                else {
                    return true;
                }
                return false;
            }*/
// ----------------------------------------------
            function checkFields(elementId, elementName) {
                var fieldValue = 0;
                fieldValue = document.getElementById(elementId).value;
                if (fieldValue == 0) {
                    alert("Please enter " + elementName + ".");
//                    document.getElementById(elementId).focus();
                    return false;
                }
                else {
                    return true;
                }
                return false;
            }
// ----------------------------------------------
            function checkNumber(elementId, elementName) {
                var chAmount = document.getElementById(elementId).value;
                if (isNaN(chAmount)) {
                    alert("Please enter valid amount");
                    return false;
                }
                else
                    return true;
                return false;
            }
// ----------------------------------------------
            function checkAmount(elementId) {
                var inv_amt = document.getElementById("inv_amt").value;
                var amount = document.getElementById("amount").value;
                var eligAmount = inv_amt / 100.0 * 90;

                if (inv_amt == 0) {
                    alert("Please enter total amount");
//                    document.getElementById("inv_amt").focus();
                    return false;
                }
                else if (amount > eligAmount) {
                    alert("Please enter advance amount less than and equal of 90% of total amount");
//                    document.getElementById("inv_amt").focus();
                    return false;
                }
                else {
                    return true;
                }
                return false;
            }
// ----------------------------------------------
            function Checkdate() {
                var inv_date = document.getElementById("inv_date").value;
                var length = inv_date.toString().length;
                var sap1 = (inv_date.toString().indexOf("/", 0));
                var sap2 = (inv_date.toString().indexOf("/", (sap1 + 1)));
//                alert(sap1+", "+sap2+", "+length);
                var inv_date1 = new Date();
                inv_date1.setDate(inv_date.toString().substring(0, sap1));
                inv_date1.setMonth(inv_date.toString().substring(sap1 + 1, sap2) - 1);
                inv_date1.setYear(inv_date.toString().substring(sap2 + 1, length));

                var todayDate = new Date();
                var checkToday = (todayDate.getTime() - inv_date1.getTime()) / (24 * 60 * 60 * 1000);

                if (checkToday < 0) {
                    alert("Date of invoice/bill can not be grater than today's date.");
                    return false;
                }
                else if (checkToday > 60) {
                    alert("Date of application should be within two months of date of invoice/bill.");
                    return false;
                }
                else if (checkToday <= 60 && checkToday >= 0) {
                    return true;
                }
                return false;
            }
//------------------------------------------------------------------------------
            function ValidateAll() {
                var amount = document.getElementById("amount").value;
                var productName = document.getElementById("productName").value;
                var vendor_name = document.getElementById("vendor_name").value;
                var vendor_add = document.getElementById("vendor_add").value;
                var inv_no = document.getElementById("inv_no").value;
                var inv_date = document.getElementById("inv_date").value;
                var inv_amt = document.getElementById("inv_amt").value;


//                alert(amount+", "+productName+", "+vendor_name+", "+vendor_add+", "+inv_no+", "+inv_date+", "+inv_amt);
                if (amount != 0 && productName != 0 && vendor_name != 0 && vendor_add != 0 && inv_no != 0 && inv_date != 0 && inv_amt != 0) {
                    return true;
                }
                else {
                    alert("Field should not be blank.");
                    return false;
                }
                return false;
            }
// -----------------------------------------------------------------------------
            function loadFunc() {
                //alert("HERE")
                document.getElementById("txtamt").disabled = "true";

            }

/*            function festValidate() {
                var amount = document.festappl.amount;
                //alert(<%=lnmax_amt%>);
                var advanceAmount = 0;
                for (var i = 0; i < amount.length; i++) {
                    if (amount[i].checked) {
                        advanceAmount = amount[i].value;
                       
                        //alert(document.getElementById("txtamt").value)
                        //advanceAmount = document.getElementById("txtamt").value;
                    }

                }
                
                if (advanceAmount === 0) {
                    alert("Please select Advance Amount");
                    return false;
                }
                 if (advanceAmount === "2") {
                    //alert("advanceAmount");
                    advanceAmount = document.getElementById("txtamt").value;
                    }
                alert(<%=amt%> +" : " + <%=lnmax_amt%> + " : "+ advanceAmount );
                
                if(advanceAmount < <%=lnmax_amt%>){
                    alert("Enter amount more than min amount( <%=lnmax_amt%>)");
                    return false;
                }else if (advanceAmount > <%=amt%>){
                    alert(<%=amt%>)
                    alert("Enter amount less than max amount( <%=amt%>)");
                    return false ;
                }else {
                    return true;
                }
                return false;
            }
 */           
            
            function txtEnable() {
                //alert("Hi")
               document.getElementById("txtamt").disabled = false;
            }
            
            function txtDisable(){
                document.getElementById("txtamt").disabled = true;
            }
            
            function isNumberKey(evt) {
            	var charCode = (evt.which) ? evt.which : evt.keyCode
            	if (charCode > 31 && (charCode != 46 && (charCode < 48 || charCode > 57)))
            		return false;
            	return true;
            }
            function isNumberKeyWithOutDecimal(evt) {
            	var charCode = (evt.which) ? evt.which : evt.keyCode
            	if (charCode > 31 && (charCode < 48 || charCode > 57))
            		return false;
            	return true;
            }
           
            function submitTelephoneAllowance(){
            	alert("submit button");

				debugger;
					var jsonArray = [];
					jsonArray.length = 0
					var jsonObj = {};
					
					jsonObj["purchase_dt"] = $("#purchase_dt").val();
					jsonObj["bill_amount"] = $("#bill_amount").val();
					jsonObj["imei_no"] = $("#imei_no").val();
					jsonObj["sim_type"] = $("#sim_type").val();
					jsonObj["ram_rom"] = $("#ram_rom").val();
					jsonObj["operating_system"] = $("#operating_system").val();
					jsonObj["cpu_processor"] = $("#cpu_processor").val();
					jsonObj["network_type"] = $("#network_type").val();
					jsonObj["display_size"] = $("#display_size").val();
					
					jsonArray.push(jsonObj);
					
					$.ajax({
						url: 'submitTeleAllowance',
						data: {
							CSRFToken: $("meta[name='_csrf']").attr("content"),
							userData: JSON.stringify(jsonArray)
						},
						async: false,
						type: 'post',
						success: function(data) {
							alert("Added Successfully");
							window.location.reload();
						},
						error: function(error) {
							alert("Failed");
						}
					})
				
	
            }
            
            function ADDdetails() {
            	debugger;
            	/*$('#processTbl').show();*/
            	var valid = true;
            	$(".req").each(function(){
            		if(this.value == ""){
            			valid = false;
            			alert('Fill all details');
            			return valid;
            		}		
            		
            	})
            	
            	if(valid){
            	var infosectbl = document.getElementById("infosectbl");
            	var tr = document.createElement("tr");
            	var td =document.createElement("td");
            	var td1 =document.createElement("td");
            	var td2 =document.createElement("td");
            	
            	
            	var input1 = document.createElement("input");
            	input1.id = "Client";
            	input1.readonly ="READONLY"
            	input1.value  = "Mobile Phone Reimbursement";      /*document.getElementById("clientID").value;*/
            	
            	var input2 = document.createElement("input");
            	input2.id = "inpprojName";
            	input2.readonly ="READONLY"
            		input2.value  = "1000";
            	
            	var input3 = document.createElement("input");
            	input3.id = "inprojDesc";
            	input3.readonly ="READONLY"
            		input3.value  =	document.getElementById("bill_amount").value;;									/*document.getElementById("projDesc").value;*/
            	
            	td.appendChild(input1);
            	td1.appendChild(input2);
            	td2.appendChild(input3);
            	
            	tr.appendChild(td);
            	tr.appendChild(td1);
            	tr.appendChild(td2);
            	
            	infosectbl.appendChild(tr)
            	
            	
            	document.getElementById("processTbl").style.display= "";
            	
            	$('#remarks').val("");
            	$('#processTbl').show();
            	}
            }
   