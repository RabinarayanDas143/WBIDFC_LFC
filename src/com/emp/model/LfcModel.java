package com.emp.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Transient;

public class LfcModel {

	private Integer id;
	private String name;
	private String designation;
	private String classification;
	private List<String> leaveType;
	private Date leavefromDate;
	private Date leavetoDate;
	private int numberofDays;
	private String placeofDestination;
	private String placeofOrigination;
	private int amountofAdvance;
	private int status;
	private String dependent;
	private String hrStatus;
	private String internalAuditStatus;
	private String csStatus;
	private String hrRemark;
	private String internalAuditRemark;
	private String csRemark;
	
	private String advanceAmountApproved;
	private String leaveEncashmentAmountApproved;
	
	private LocalDate pdfDate;
	private int lfcFinalAmount;
	private String prevDate;
	 
	
	public String getAdvanceAmountApproved() {
		return advanceAmountApproved;
	}

	public void setAdvanceAmountApproved(String advanceAmountApproved) {
		this.advanceAmountApproved = advanceAmountApproved;
	}

	public String getLeaveEncashmentAmountApproved() {
		return leaveEncashmentAmountApproved;
	}

	public void setLeaveEncashmentAmountApproved(String leaveEncashmentAmountApproved) {
		this.leaveEncashmentAmountApproved = leaveEncashmentAmountApproved;
	}

	@Transient
	private int tranId;
	private String leaveEncashmentBlock;

	//extra
	@Transient
	private String leaveTypeStr;
	
	@Transient
	private String leavefromDateStr;
	
	@Transient
	private String leavetoDateStr;
	
	@Transient
	private String NumberofDaysStr;
	
	@Transient
	private String AmountofAdvanceStr;
	
	@Transient
	private String annualIncome;
	
	@Transient
	private String occupation;
	
	@Transient
	private String department;
	
	@Transient
	private String dateOfJoining;
	
	@Transient
	private String blockApplied;
	
	@Transient
	private String lfcFromDate;
	
	@Transient
	private String lfctoDate;
	
	@Transient
	private String commencementFromDate;
	
	@Transient
	private String complitionToDate;
	
	@Transient
	private String encashmentLeaveCount;
	
	@Transient
	private String lastAvailmentLfcDate;
	
	private String dob;
	private String relation;
	
	private String count;
	private String El_LeaveBalance;
	
	private String localDateStr;

	@Override
	public String toString() {
		return "LfcModel [id=" + id + ", name=" + name + ", designation=" + designation + ", classification="
				+ classification + ", leaveType=" + leaveType + ", leavefromDate=" + leavefromDate + ", leavetoDate="
				+ leavetoDate + ", numberofDays=" + numberofDays + ", placeofDestination=" + placeofDestination
				+ ", placeofOrigination=" + placeofOrigination + ", amountofAdvance=" + amountofAdvance + ", status="
				+ status + ", dependent=" + dependent + ", hrStatus=" + hrStatus + ", internalAuditStatus="
				+ internalAuditStatus + ", csStatus=" + csStatus + ", hrRemark=" + hrRemark + ", internalAuditRemark="
				+ internalAuditRemark + ", csRemark=" + csRemark + ", tranId=" + tranId + ", leaveEncashmentBlock="
				+ leaveEncashmentBlock + ", leaveTypeStr=" + leaveTypeStr + ", leavefromDateStr=" + leavefromDateStr
				+ ", leavetoDateStr=" + leavetoDateStr + ", NumberofDaysStr=" + NumberofDaysStr
				+ ", AmountofAdvanceStr=" + AmountofAdvanceStr + ", annualIncome=" + annualIncome + ", occupation="
				+ occupation + ", department=" + department + ", dateOfJoining=" + dateOfJoining + ", blockApplied="
				+ blockApplied + ", lfcFromDate=" + lfcFromDate + ", lfctoDate=" + lfctoDate + ", commencementFromDate="
				+ commencementFromDate + ", complitionToDate=" + complitionToDate + ", encashmentLeaveCount="
				+ encashmentLeaveCount + ", lastAvailmentLfcDate=" + lastAvailmentLfcDate + ", dob=" + dob
				+ ", relation=" + relation + ", count=" + count + ", El_LeaveBalance=" + El_LeaveBalance + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public List<String> getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(List<String> leaveType) {
		this.leaveType = leaveType;
	}

	public Date getLeavefromDate() {
		return leavefromDate;
	}

	public void setLeavefromDate(Date leavefromDate) {
		this.leavefromDate = leavefromDate;
	}

	public Date getLeavetoDate() {
		return leavetoDate;
	}

	public void setLeavetoDate(Date leavetoDate) {
		this.leavetoDate = leavetoDate;
	}

	public int getNumberofDays() {
		return numberofDays;
	}

	public void setNumberofDays(int numberofDays) {
		this.numberofDays = numberofDays;
	}

	public String getPlaceofDestination() {
		return placeofDestination;
	}

	public String getPlaceofOrigination() {
		return placeofOrigination;
	}

	public void setPlaceofOrigination(String placeofOrigination) {
		this.placeofOrigination = placeofOrigination;
	}

	public void setPlaceofDestination(String placeofDestination) {
		this.placeofDestination = placeofDestination;
	}

	public int getAmountofAdvance() {
		return amountofAdvance;
	}

	public void setAmountofAdvance(int amountofAdvance) {
		this.amountofAdvance = amountofAdvance;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Transient
	public String getDependent() {
		return dependent;
	}

	public void setDependent(String dependent) {
		this.dependent = dependent;
	}

	public String getLeaveTypeStr() {
		return leaveTypeStr;
	}

	public void setLeaveTypeStr(String leaveTypeStr) {
		this.leaveTypeStr = leaveTypeStr;
	}

	public String getLeavefromDateStr() {
		return leavefromDateStr;
	}

	public void setLeavefromDateStr(String leavefromDateStr) {
		this.leavefromDateStr = leavefromDateStr;
	}

	public String getLeavetoDateStr() {
		return leavetoDateStr;
	}

	public void setLeavetoDateStr(String leavetoDateStr) {
		this.leavetoDateStr = leavetoDateStr;
	}

	public int getTranId() {
		return tranId;
	}

	public void setTranId(int tranId) {
		this.tranId = tranId;
	}

	public String getNumberofDaysStr() {
		return NumberofDaysStr;
	}

	public void setNumberofDaysStr(String numberofDaysStr) {
		NumberofDaysStr = numberofDaysStr;
	}

	public String getAmountofAdvanceStr() {
		return AmountofAdvanceStr;
	}

	public void setAmountofAdvanceStr(String amountofAdvanceStr) {
		AmountofAdvanceStr = amountofAdvanceStr;
	}

	public String getHrStatus() {
		return hrStatus;
	}

	public void setHrStatus(String hrStatus) {
		this.hrStatus = hrStatus;
	}

	public String getInternalAuditStatus() {
		return internalAuditStatus;
	}

	public void setInternalAuditStatus(String internalAuditStatus) {
		this.internalAuditStatus = internalAuditStatus;
	}

	public String getCsStatus() {
		return csStatus;
	}

	public void setCsStatus(String csStatus) {
		this.csStatus = csStatus;
	}

	public String getHrRemark() {
		return hrRemark;
	}

	public void setHrRemark(String hrRemark) {
		this.hrRemark = hrRemark;
	}

	public String getInternalAuditRemark() {
		return internalAuditRemark;
	}

	public void setInternalAuditRemark(String internalAuditRemark) {
		this.internalAuditRemark = internalAuditRemark;
	}

	public String getCsRemark() {
		return csRemark;
	}

	public void setCsRemark(String csRemark) {
		this.csRemark = csRemark;
	}

	public String getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getBlockApplied() {
		return blockApplied;
	}

	public void setBlockApplied(String blockApplied) {
		this.blockApplied = blockApplied;
	}

	public String getLfcFromDate() {
		return lfcFromDate;
	}

	public void setLfcFromDate(String lfcFromDate) {
		this.lfcFromDate = lfcFromDate;
	}

	public String getLfctoDate() {
		return lfctoDate;
	}

	public void setLfctoDate(String lfctoDate) {
		this.lfctoDate = lfctoDate;
	}

	public String getCommencementFromDate() {
		return commencementFromDate;
	}

	public void setCommencementFromDate(String commencementFromDate) {
		this.commencementFromDate = commencementFromDate;
	}

	public String getComplitionToDate() {
		return complitionToDate;
	}

	public void setComplitionToDate(String complitionToDate) {
		this.complitionToDate = complitionToDate;
	}

	public String getEncashmentLeaveCount() {
		return encashmentLeaveCount;
	}

	public void setEncashmentLeaveCount(String encashmentLeaveCount) {
		this.encashmentLeaveCount = encashmentLeaveCount;
	}

	public String getLastAvailmentLfcDate() {
		return lastAvailmentLfcDate;
	}

	public void setLastAvailmentLfcDate(String lastAvailmentLfcDate) {
		this.lastAvailmentLfcDate = lastAvailmentLfcDate;
	}

	public String getLeaveEncashmentBlock() {
		return leaveEncashmentBlock;
	}

	public void setLeaveEncashmentBlock(String leaveEncashmentBlock) {
		this.leaveEncashmentBlock = leaveEncashmentBlock;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getEl_LeaveBalance() {
		return El_LeaveBalance;
	}

	public void setEl_LeaveBalance(String el_LeaveBalance) {
		El_LeaveBalance = el_LeaveBalance;
	}

	public LocalDate getPdfDate() {
		return pdfDate;
	}

	public void setPdfDate(LocalDate myObj) {
		this.pdfDate = myObj;
	}

	public int getLfcFinalAmount() {
		return lfcFinalAmount;
	}

	public void setLfcFinalAmount(int lfcFinalAmount) {
		this.lfcFinalAmount = lfcFinalAmount;
	}

	public String getLocalDateStr() {
		return localDateStr;
	}

	public void setLocalDateStr(String localDateStr) {
		this.localDateStr = localDateStr;
	}

	public String getPrevDate() {
		return prevDate;
	}

	public void setPrevDate(String prevDate) {
		this.prevDate = prevDate;
	}

	 

	
	
	
	
	

}
