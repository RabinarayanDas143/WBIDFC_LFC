package com.emp.service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.emp.bean.LfcBean;
import com.emp.model.Hrms_Dependent;
import com.emp.model.Hrms_Login;
import com.emp.model.LfcDocument;
import com.emp.model.LfcModel;
import com.emp.model.Lfc_Allowence;
import com.emp.model.Lfc_Dependent;
import com.emp.model.Lfc_Surrender;

public interface LfcDetailService {

	List<Object[]> getDetails(Integer userId);

	Map<String, String> getLeaveType();

	List<Object> getLeaveCount(Integer userId, String lvtype);

//(List<Lfc_Allowence> list)
	void saveInfo(JSONArray arrayEmp , JSONArray arrayDependent , Hrms_Login masterUser);

	String checkUser(Integer userId);

	List<LfcModel> getAdminData();

	void acceptReq(int acceptValue, String hradminremark, int auditamount, int auditamountLeaveEncash, int lfc_FinalAmount);
	//void acceptReq(int acceptValue, String hradminremark);

	int rejectReq(int rejectValue, String hradminremark);

	String checkPeriodApply(int empId, String lfcFromDate, String lfctoDate);

	List<LfcModel> getInternalAuditor();

	void InternalacceptReq(int acceptValue, String auditremark);
	
	//void InternalacceptReq(int acceptValue, String auditremark)

	int InternalrejectReq(int rejectvalue, String auditremark);

	List<LfcModel> getCsAdmindata();

	int CSacceptReq(int acceptValue, String cSremark, String empCode, String checkerEin);

	int CSrejectReq(int rejectvalue, String cSremark);

	String userFilter(Integer userId);

	List<LfcModel> getLfcHrReport(Integer userId);

	List<LfcModel> getLfcAuditReport(Integer userId);

	List<LfcModel> getLfcCsReport(Integer userId);

	int getLFCAccess(Integer userId);

	List<LfcModel> getLfcUserReport(Integer userId);

	BigInteger getLfcDateDiff(String leavetodate, String leavefrmdate);

	List<LfcModel> getInternalAuditorAdmin();
	
    // for Intenal Audit Admin admin req.
	void auditAdminremarkReq(int acceptValue, String auditAdminremark, String advanceAmountApproved, String leaveEncashmentAmountApproved, int lfcFinalamount);

	List<LfcModel> getCsAdmin();

	LfcBean userClass(Integer userId);

	List<Hrms_Dependent> getdepenedentList(Integer userId);

	List<String> getDependentName(String emply_cd);

	Map<String, String> getEncashmentLeaveType();

	List<Integer> getEncashmentLeaveCount(Integer userId, String encashmentLeaveType);

	void saveSurrenderInfo(JSONArray arrayEmp, JSONArray arrayDependent, Hrms_Login loginBean);

	//void fectchLfcApplyData(Integer userId);

	List<LfcModel> getLfcUserSurrenderReport(Integer userId);

	List<LfcModel> getSurrenderHrAdminData();

	int hrSurAcceptReq(int acceptValue, String hradminremark, int finalAmount);

	int hrSurRejectReq(int rejectvalue, String hradminremark);

	List<LfcModel> getLfcSurrenderHrReport();

	List<LfcModel> getSurrenderInternalAuditorAdmin();

	int surInternalAdminAccept(int acceptValue, String auditAdminremark);

	int surInternalrejectReq(int rejectvalue, String auditremark);

	List<LfcModel> getSurAuditReport();

	List<LfcModel> getSurrenderCsAdmindata();

	int surCSacceptReq(int acceptValue, String cSremark, String empCode, String checkerEin);

	int surCSrejectReq(int rejectvalue, String cSremark);

	List<LfcModel> getSurCsReport();

	List<LfcModel> getSurrenderInternalAuditorAdminadmin();

	List<LfcModel> surCsAdminRequest();

	List<LfcModel> getApplyLfcData(Integer userId);

	List<Lfc_Dependent> getDependentName(Integer userId);

	List<LfcModel> getLfcSurrenderData(Integer userId);

	String getLfcToDate(String blockApplied, String fromDate);

	String getUserName(Integer userId);

	String getUserDesignation(Integer userId);

	List<Object[]> getHrAdminModalBoxData(String empId);

	List<LfcModel> getHrAdminModalDependentData(String empId);

	List<LfcModel> getInternalAuditorModalBoxData(String empId);

	List<LfcModel> getCsModalData(String empId);

	List<LfcModel> getCsAdminModalData(String empId);

	List<LfcModel> getSurHrModalData(String empId);

	List<LfcModel> getSurInternalAuditModalData(String empId);

	List<LfcModel> getSurCsModalData(String empId);

	List<LfcModel> getSurInternalAuditAdminModalData(String empId);

	List<LfcModel> getSurCsAdminModalData(String empId);

	List<LfcModel> getOfcUseData(Integer userId);

	List<LfcModel> getOfcUseDataSur(Integer userId);

	int isLeaveApplied(Integer userId);

	List<LfcModel> getHrAdminRequestData();

	int hrAdminAcceptReq(int emp_tranId, String hradminremark, String hrAmountApproved, String hrLeaveEncashApproved, int lfcFinalamount);

	int hrAdminrejectReq(int emp_tranId, String hradminremark);

	String uploadLfcRawFile(MultipartFile[] documentFile, Hrms_Login loginBean);

	byte[] getSampleFileOFLfc(String userId);

	List<LfcModel> getPreviousUploadDate(int emp_cd);

	byte[] getPrevFile(String tranId, String prevDate);

	List<LfcModel> getSurrenderHrAdminRequestData();

}
