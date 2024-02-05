package com.emp.model;

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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "hrms_Lfc_Surrender")
public class Lfc_Surrender {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "hrms_Lfc_SurrenderSeq")
	@SequenceGenerator(name = "hrms_Lfc_SurrenderSeq", sequenceName = "hrms_Lfc_SurrenderSeq", allocationSize = 1, initialValue = 1)
	private int id;
	
	@Column(name = "EMPLY_CD")
	private int empcd;
	
	@Column(name = "BLOCK_APPLIED")
	private String blockapplied;
	
	@Column(name = "EncashmentLEAVE_TYPE")
	private String encashmentleave_type;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "IST")
	@Column(name = "LFC_FROM_DT")
	private Date fromdate = new Date();
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "IST")
	@Column(name = "LFC_TO_DT")
	private Date todatepicker = new Date();
	
	
	@Column(name = "EncashmentLeave_Count")
	private int Encasmentleavecount;
	
	@Column(name = "Approval_Level_1")
	private String hrDepartment;

	@Column(name = "Approval_Level_2")
	private String internalAuditor;

	@Column(name = "Approval_Level_3")
	private String company_Secretary;

	@Column(name = "Hr_Remark", length = 100, columnDefinition = "TEXT")
	private String hrremark;

	@Column(name = "Audit_Remark", length = 100, columnDefinition = "TEXT")
	private String auditremark;

	@Column(name = "Cs_Remark", length = 100, columnDefinition = "TEXT")
	private String csremark;
	
	@Column(name = "tran_id")  
	private int tranId;
	
	@Column(name = "Status")
	private String status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "IST")
	@Column(name = "SUBMIT_DATE")
	private Date submitDate = new Date();
	
	@Column(name = "surr_FinalAmt")  
	private int surrenderFinalAmount;
 
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

	public String getEncashmentleave_type() {
		return encashmentleave_type;
	}

	public void setEncashmentleave_type(String encashmentleave_type) {
		this.encashmentleave_type = encashmentleave_type;
	}

	public Date getFromdate() {
		return fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public Date getTodatepicker() {
		return todatepicker;
	}

	public void setTodatepicker(Date todatepicker) {
		this.todatepicker = todatepicker;
	}

	

	public int getEncasmentleavecount() {
		return Encasmentleavecount;
	}

	public void setEncasmentleavecount(int encasmentleavecount) {
		Encasmentleavecount = encasmentleavecount;
	}

	public int getTranId() {
		return tranId;
	}

	public void setTranId(int tranId) {
		this.tranId = tranId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getHrremark() {
		return hrremark;
	}

	public void setHrremark(String hrremark) {
		this.hrremark = hrremark;
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

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public int getSurrenderFinalAmount() {
		return surrenderFinalAmount;
	}

	public void setSurrenderFinalAmount(int surrenderFinalAmount) {
		this.surrenderFinalAmount = surrenderFinalAmount;
	}

	
	
	
}
