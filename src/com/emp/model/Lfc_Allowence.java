package com.emp.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "hrms_encashment")
public class Lfc_Allowence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "hrms_encashmentSeq")
	@SequenceGenerator(name = "hrms_encashmentSeq", sequenceName = "hrms_encashmentSeq", allocationSize = 1, initialValue = 1)
	private int id;

	@Column(name = "EMPLY_CD")
	private int empcd;

	@Column(name = "BLOCK_APPLIED")
	private String blockapplied;

	@Column(name = "LEAVE_TYPE")
	private String leave_type;
	
	@Column(name = "EncashmentLEAVE_TYPE")
	private String encashmentleave_type;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "IST")
	@Column(name = "LFC_FROM_DT")
	private Date fromdate = new Date();

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "IST")
	@Column(name = "leave_FROM_DT")
	private Date fdate = new Date();
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "IST")
	@Column(name = "Commencement_FROM_DT")
	private Date commencement_fromdate = new Date();

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "IST")
	@Column(name = "LFC_TO_DT")
	private Date todatepicker = new Date();

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "IST")
	@Column(name = "leave_TO_DT")
	private Date tdate = new Date();
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "IST")
	@Column(name = "Completion_TO_DT")
	private Date completion_todate = new Date();

	@Column(name = "PLACE_OF_DESTINATION")
	private String destinationplace;

	@Column(name = "AMOUNT_OF_ADVANCE")
	private int amountAdvance;

	@Column(name = "LEAVE_ENCASH_BLOCK")
	private String leaveEncashBlock;

	@Column(name = "NUMBER_OF_DAYS")
	private int numberOfDays;
	
	@Column(name = "EncashmentLeave_Count")
	private int Encasmentleavecount;
	
	@Column(name = "Approval_Level_1")
	private String hrDepartment;

	@Column(name = "Approval_Level_2")
	private String internalAuditor;

	@Column(name = "Approval_Level_3")
	private String company_Secretary;

	@Column(name = "Remark", length = 100, columnDefinition = "TEXT")
	private String remark;

	@Column(name = "Audit_Remark", length = 100, columnDefinition = "TEXT")
	private String auditremark;

	@Column(name = "Cs_Remark", length = 100, columnDefinition = "TEXT")
	private String csremark;

	@Column(name = "tran_id") // , unique = true
	private int tranId;
	
	@Column(name = "Status")
	private String status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "IST")
	@Column(name = "SUBMIT_DATE")
	private Date submitDate = new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmpcd() {
		return empcd;
	}

	public void setEmpcd(int empcd) {
		this.empcd = empcd;
	}

	public String getBlockapplied() {
		return blockapplied;
	}

	public void setBlockapplied(String blockapplied) {
		this.blockapplied = blockapplied;
	}

	public String getLeave_type() {
		return leave_type;
	}

	public void setLeave_type(String leave_type) {
		this.leave_type = leave_type;
	}

	public Date getFromdate() {
		return fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public Date getFdate() {
		return fdate;
	}

	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}

	public Date getTodatepicker() {
		return todatepicker;
	}

	public void setTodatepicker(Date todatepicker) {
		this.todatepicker = todatepicker;
	}

	public Date getTdate() {
		return tdate;
	}

	public void setTdate(Date tdate) {
		this.tdate = tdate;
	}

	public String getDestinationplace() {
		return destinationplace;
	}

	public void setDestinationplace(String destinationplace) {
		this.destinationplace = destinationplace;
	}

	public int getAmountAdvance() {
		return amountAdvance;
	}

	public void setAmountAdvance(int amountAdvance) {
		this.amountAdvance = amountAdvance;
	}

	public String getLeaveEncashBlock() {
		return leaveEncashBlock;
	}

	public void setLeaveEncashBlock(String leaveEncashBlock) {
		this.leaveEncashBlock = leaveEncashBlock;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public String getHrDepartment() {
		return hrDepartment;
	}

	public void setHrDepartment(String hrDepartment) {
		this.hrDepartment = hrDepartment;
	}

	public String getInternalAuditor() {
		return internalAuditor;
	}

	public void setInternalAuditor(String internalAuditor) {
		this.internalAuditor = internalAuditor;
	}

	public String getCompany_Secretary() {
		return company_Secretary;
	}

	public void setCompany_Secretary(String company_Secretary) {
		this.company_Secretary = company_Secretary;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getTranId() {
		return tranId;
	}

	public void setTranId(int tranId) {
		this.tranId = tranId;
	}

	public String getAuditremark() {
		return auditremark;
	}

	public void setAuditremark(String auditremark) {
		this.auditremark = auditremark;
	}

	public String getCsremark() {
		return csremark;
	}

	public void setCsremark(String csremark) {
		this.csremark = csremark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEncashmentleave_type() {
		return encashmentleave_type;
	}

	public void setEncashmentleave_type(String encashmentleave_type) {
		this.encashmentleave_type = encashmentleave_type;
	}
 
	public int getEncasmentleavecount() {
		return Encasmentleavecount;
	}

	public void setEncasmentleavecount(int encasmentleavecount) {
		Encasmentleavecount = encasmentleavecount;
	}

	public Date getCommencement_fromdate() {
		return commencement_fromdate;
	}

	public void setCommencement_fromdate(Date commencement_fromdate) {
		this.commencement_fromdate = commencement_fromdate;
	}

	public Date getCompletion_todate() {
		return completion_todate;
	}

	public void setCompletion_todate(Date completion_todate) {
		this.completion_todate = completion_todate;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	
	

}
