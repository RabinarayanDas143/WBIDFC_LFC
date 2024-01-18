package com.emp.service.impl;

import static com.emp.constant.EmployeeServiceConstant.DateConvertJsonObject;
import static com.emp.constant.EmployeeServiceConstant.DateIsNullJsonObject;
import static com.emp.constant.EmployeeServiceConstant.StringIsNullJsonObject;
import static com.emp.constant.EmployeeServiceConstant.getExtenionFromMultiart;
import static com.emp.constant.EmployeeServiceConstant.isNullOrEmpty;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.emp.bean.LfcBean;
import com.emp.dao.LfcDetailDao;
import com.emp.model.Hrms_Dependent;
import com.emp.model.Hrms_Login;
import com.emp.model.LfcDocument;
import com.emp.model.LfcModel;
import com.emp.model.Lfc_Allowence;
import com.emp.model.Lfc_Dependent;
import com.emp.model.Lfc_Surrender;
import com.emp.service.LfcDetailService;
@Service
public class LfcDetailServiceImpl implements LfcDetailService {

	@Autowired
	LfcDetailDao lfcdetail;
	public static String message = null;
	@Override
	public List<Object[]> getDetails(Integer userId) {

		return lfcdetail.getDetails(userId);
	}

	@Override
	public Map<String, String> getLeaveType() {

		return lfcdetail.getLeaveType();
	}

	@Override
	public List<Integer> getLeaveCount(Integer userId, String lvtype) {

		return lfcdetail.getLeaveCount(userId, lvtype);
	}

//(List<Lfc_Allowence> list)
	@Override
	public void saveInfo(JSONArray arrayEmp, JSONArray arrayDependent, Hrms_Login masterUser) {
		System.out.println("Inside serviceImpl =====================");
		String message = null;
		try {
			ArrayList<Lfc_Allowence> allowenceList = new ArrayList<Lfc_Allowence>();
			ArrayList<Lfc_Dependent> dependentList = new ArrayList<Lfc_Dependent>();
			Lfc_Allowence allowence = null;
			Lfc_Dependent dependent = null;
			int stat = 0;
			for (int i = 0; i < arrayEmp.length(); i++) {
				allowence = new Lfc_Allowence();
				JSONObject obj = arrayEmp.getJSONObject(i);
				allowence.setEmpcd(Integer.parseInt(StringIsNullJsonObject(obj, "empcd")));
				allowence.setBlockapplied(StringIsNullJsonObject(obj, "blockapplied"));
				allowence.setLeave_type(StringIsNullJsonObject(obj, "leave_type"));
				try {
					allowence.setFromdate(
							DateIsNullJsonObject(obj, "fromdate") != null ? DateIsNullJsonObject(obj, "fromdate")
									: null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					allowence.setTodatepicker(DateIsNullJsonObject(obj, "todatepicker") != null
							? DateIsNullJsonObject(obj, "todatepicker")
							: null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				allowence.setDestinationplace(StringIsNullJsonObject(obj, "destinationplace"));
				
				allowence.setOriginationplace(StringIsNullJsonObject(obj, "originationplace"));
				
				allowence.setAmountAdvance(Integer.parseInt(StringIsNullJsonObject(obj, "amountAdvance")));
				allowence.setLeaveEncashBlock(StringIsNullJsonObject(obj, "leaveEncashBlock"));
				try {
					allowence.setFdate(
							DateIsNullJsonObject(obj, "fdate") != null ? DateIsNullJsonObject(obj, "fdate") : null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					allowence.setTdate(
							DateIsNullJsonObject(obj, "tdate") != null ? DateIsNullJsonObject(obj, "tdate") : null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				allowence.setNumberOfDays(Integer.parseInt(StringIsNullJsonObject(obj, "numberOfDays")));
				allowence.setEncashmentleave_type(StringIsNullJsonObject(obj, "EncasmentleaveType"));
				try {
					allowence.setCommencement_fromdate(DateIsNullJsonObject(obj, "travelfromdate") != null
							? DateIsNullJsonObject(obj, "travelfromdate")
							: null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					allowence.setCompletion_todate(DateIsNullJsonObject(obj, "traveltodate") != null
							? DateIsNullJsonObject(obj, "traveltodate")
							: null);
				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("----------------obj---------------------" + obj);

				// allowence.setEncasmentleavecount(Integer.parseInt(StringIsNullJsonObject(obj,
				// "Encasmentleavecount")));

				allowence.setEncasmentleavecount(StringIsNullJsonObject(obj, "Encasmentleavecount") != ""
						? Integer.parseInt(StringIsNullJsonObject(obj, "Encasmentleavecount"))
						: 0);

				allowence.setStatus("S");
				allowenceList.add(allowence);
				allowence = null;
			}

			if (!isNullOrEmpty(allowenceList)) {
				stat = lfcdetail.saveInfo(allowenceList);
			}
			int employee_code = masterUser.getEmply_cd();
			int tranId = lfcdetail.getTranId(employee_code);
			for (int i = 0; i < arrayDependent.length(); i++) {
				dependent = new Lfc_Dependent();
				int empcd = masterUser.getEmply_cd();
				JSONObject obj = arrayDependent.getJSONObject(i);
				dependent.setEmplycd(empcd);
				dependent.setDependentname(StringIsNullJsonObject(obj, "dependentname"));
				dependent.setDesrno(Integer.parseInt(StringIsNullJsonObject(obj, "desrNo")));
				dependent.setRelation(StringIsNullJsonObject(obj, "relation"));
				try {
					dependent.setDob(
							DateConvertJsonObject(obj, "dob") != null ? DateConvertJsonObject(obj, "dob") : null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				dependent.setGender(StringIsNullJsonObject(obj, "gender"));
				/* String annualIncome = */
				dependent.setAnnualincome(StringIsNullJsonObject(obj, "annualincome"));
				dependent.setOccupation(StringIsNullJsonObject(obj, "occupation"));
				dependent.setStatus("S");
				dependent.setTranId(tranId);
				dependentList.add(dependent);
				dependent = null;
			}
			// int stat = 0;
//        if(!isNullOrEmpty(allowenceList)) {
//             stat = lfcdetail.saveInfo(allowenceList);
//        }
			if (!isNullOrEmpty(dependentList)) {
				stat = lfcdetail.saveDependentInfo(dependentList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in ServiceImpl Class :" + e);
		}
	}

	@Override
	public void saveSurrenderInfo(JSONArray arrayEmp, JSONArray arrayDependent, Hrms_Login masterUser) {

		try {
			ArrayList<Lfc_Surrender> surrenderList = new ArrayList<Lfc_Surrender>();
			ArrayList<Lfc_Dependent> dependentList = new ArrayList<Lfc_Dependent>();
			Lfc_Surrender surrender = null;
			Lfc_Dependent dependent = null;
			int stat = 0;
			for (int i = 0; i < arrayEmp.length(); i++) {
				surrender = new Lfc_Surrender();
				JSONObject obj = arrayEmp.getJSONObject(i);

				surrender.setEmpcd(Integer.parseInt(StringIsNullJsonObject(obj, "empcd")));
				surrender.setBlockapplied(StringIsNullJsonObject(obj, "blockapplied"));

				try {
					surrender.setFromdate(
							DateIsNullJsonObject(obj, "fromdate") != null ? DateIsNullJsonObject(obj, "fromdate")
									: null);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					surrender.setTodatepicker(DateIsNullJsonObject(obj, "todatepicker") != null
							? DateIsNullJsonObject(obj, "todatepicker")
							: null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				surrender.setEncashmentleave_type(StringIsNullJsonObject(obj, "EncasmentleaveType"));

				surrender.setEncasmentleavecount(Integer.parseInt(StringIsNullJsonObject(obj, "Encasmentleavecount")));
				surrender.setStatus("S");

				surrenderList.add(surrender);
				surrender = null;
			}

			if (!isNullOrEmpty(surrenderList)) {
				stat = lfcdetail.saveSuurenderInfo(surrenderList);
			}
			int employee_code = masterUser.getEmply_cd();
			int tranId = lfcdetail.getTranIdFromSurrender(employee_code);

			for (int i = 0; i < arrayDependent.length(); i++) {
				dependent = new Lfc_Dependent();
				int empcd = masterUser.getEmply_cd();
				JSONObject obj = arrayDependent.getJSONObject(i);
				dependent.setEmplycd(empcd);
				dependent.setDependentname(StringIsNullJsonObject(obj, "dependentname"));
				dependent.setDesrno(Integer.parseInt(StringIsNullJsonObject(obj, "desrNo")));
				dependent.setRelation(StringIsNullJsonObject(obj, "relation"));
				try {
					dependent.setDob(
							DateConvertJsonObject(obj, "dob") != null ? DateConvertJsonObject(obj, "dob") : null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				dependent.setGender(StringIsNullJsonObject(obj, "gender"));
				dependent.setAnnualincome(StringIsNullJsonObject(obj, "annualincome"));
				dependent.setOccupation(StringIsNullJsonObject(obj, "occupation"));
				dependent.setStatus("S");
				dependent.setTranId(tranId);
				dependentList.add(dependent);
				dependent = null;
			}

			if (!isNullOrEmpty(dependentList)) {
				stat = lfcdetail.saveDependentInfo(dependentList);
			}
		} catch (Exception e) {

		}
	}

	@Override
	public String checkUser(Integer userId) {

		return lfcdetail.checkUser(userId);
	}

	@Override
	public List<LfcModel> getAdminData() {

		return lfcdetail.getAdminData();
	}

	@Override
	public void acceptReq(int acceptValue, String hradminremark , int auditamount, int auditamountLeaveEncash,int lfc_FinalAmount) {

		lfcdetail.acceptReq(acceptValue, hradminremark, auditamount,  auditamountLeaveEncash,lfc_FinalAmount);
		
		//lfcdetail.acceptReq(acceptValue, hradminremark);
	}

	@Override
	public int rejectReq(int rejectValue, String hradminremark) {

		return lfcdetail.rejectReq(rejectValue, hradminremark);
	}

	@Override
	public String checkPeriodApply(int empId, String lfcFromDate, String lfctoDate) {

		return lfcdetail.checkPeriodApply(empId, lfcFromDate, lfctoDate);
	}

	@Override
	public List<LfcModel> getInternalAuditor() {

		return lfcdetail.getInternalAuditorData();
	}

	//for Internal Audit accept function
	@Override
	public void InternalacceptReq(int acceptValue, String auditremark) {
		lfcdetail.InternalacceptReq(acceptValue, auditremark);

	}

	@Override
	public int InternalrejectReq(int rejectvalue, String auditremark) {
		return lfcdetail.InternalrejectReq(rejectvalue, auditremark);

	}

	@Override
	public List<LfcModel> getCsAdmindata() {

		return lfcdetail.getCsAdmindata();
	}

	@Override
	public int CSacceptReq(int acceptValue, String cSremark, String empCode, String checkerEin) {
		return lfcdetail.CSacceptReq(acceptValue, cSremark, empCode, checkerEin);

	}

	@Override
	public int CSrejectReq(int rejectvalue, String cSremark) {
		return lfcdetail.CSrejectReq(rejectvalue, cSremark);

	}

	@Override
	public String userFilter(Integer userId) {

		return lfcdetail.userFilter(userId);
	}

	@Override
	public List<LfcModel> getLfcHrReport(Integer userId) {

		return lfcdetail.getLfcHrReport(userId);
	}

	@Override
	public List<LfcModel> getLfcAuditReport(Integer userId) {

		return lfcdetail.getLfcAuditReport(userId);
	}

	@Override
	public List<LfcModel> getLfcCsReport(Integer userId) {

		return lfcdetail.getLfcCsReport(userId);
	}

	@Override
	public int getLFCAccess(Integer userId) {

		return lfcdetail.getLFCAccess(userId);
	}

	@Override
	public List<LfcModel> getLfcUserReport(Integer userId) {

		return lfcdetail.getLfcUserReport(userId);
	}

	@Override
	public BigInteger getLfcDateDiff(String leavetodate, String leavefrmdate) {

		return lfcdetail.getLfcDateDiff(leavetodate, leavefrmdate);
	}

	@Override
	public List<LfcModel> getInternalAuditorAdmin() {

		return lfcdetail.getInternalAuditorAdmin();
	}

	// for Internal Audit Admin admin req
	@Override
	public void auditAdminremarkReq(int acceptValue, String auditAdminremark,String advanceAmountApproved, String leaveEncashmentAmountApproved, int lfcFinalamount) {
		lfcdetail.auditAdminremarkReq(acceptValue, auditAdminremark,advanceAmountApproved,leaveEncashmentAmountApproved,lfcFinalamount);

	}

	@Override
	public List<LfcModel> getCsAdmin() {

		return lfcdetail.getCsAdmin();
	}

	@Override
	public LfcBean userClass(Integer userId) {

		return lfcdetail.userClass(userId);
	}

	@Override
	public List<Hrms_Dependent> getdepenedentList(Integer userId) {

		return lfcdetail.getdependentList(userId);
	}

	@Override
	public List<String> getDependentName(String emply_cd) {

		return lfcdetail.getDependentName(emply_cd);
	}

	@Override
	public Map<String, String> getEncashmentLeaveType() {

		return lfcdetail.getEncashmentLeaveType();
	}

	@Override
	public List<Integer> getEncashmentLeaveCount(Integer userId, String encashmentLeaveType) {

		return lfcdetail.getEncashmentLeaveCount(userId, encashmentLeaveType);
	}

//	@Override
//	public void fectchLfcApplyData(Integer userId) {
//		List<Object> data = lfcdetail.fectchLfcApplyData(userId);
//	}

	@Override
	public List<LfcModel> getLfcUserSurrenderReport(Integer userId) {

		return lfcdetail.getLfcUserSurrenderReport(userId);
	}

	@Override
	public List<LfcModel> getSurrenderHrAdminData() {

		return lfcdetail.getSurrenderHrAdminData();
	}

	@Override
	public int hrSurAcceptReq(int acceptValue, String hradminremark) {

		return lfcdetail.hrSurAcceptReq(acceptValue, hradminremark);
	}

	@Override
	public int hrSurRejectReq(int rejectvalue, String hradminremark) {

		return lfcdetail.hrSurRejectReq(rejectvalue, hradminremark);
	}

	@Override
	public List<LfcModel> getLfcSurrenderHrReport() {

		return lfcdetail.getLfcSurrenderHrReport();
	}

	@Override
	public List<LfcModel> getSurrenderInternalAuditorAdmin() {

		return lfcdetail.getSurrenderInternalAuditorAdmin();
	}

	@Override
	public int surInternalAdminAccept(int acceptValue, String auditAdminremark) {

		return lfcdetail.surInternalAdminAccept(acceptValue, auditAdminremark);
	}

	@Override
	public int surInternalrejectReq(int rejectvalue, String auditremark) {

		return lfcdetail.surInternalrejectReq(rejectvalue, auditremark);
	}

	@Override
	public List<LfcModel> getSurAuditReport() {

		return lfcdetail.getSurAuditReport();
	}

	@Override
	public List<LfcModel> getSurrenderCsAdmindata() {

		return lfcdetail.getSurrenderCsAdmindata();
	}

	@Override
	public int surCSacceptReq(int acceptValue, String cSremark, String empCode, String checkerEin) {

		return lfcdetail.surCSacceptReq(acceptValue, cSremark, empCode, checkerEin);
	}

	@Override
	public int surCSrejectReq(int rejectvalue, String cSremark) {

		return lfcdetail.surCSrejectReq(rejectvalue, cSremark);
	}

	@Override
	public List<LfcModel> getSurCsReport() {

		return lfcdetail.getSurCsReport();
	}

	@Override
	public List<LfcModel> getSurrenderInternalAuditorAdminadmin() {

		return lfcdetail.getSurrenderInternalAuditorAdminadmin();
	}

	@Override
	public List<LfcModel> surCsAdminRequest() {

		return lfcdetail.surCsAdminRequest();
	}

	@Override
	public List<LfcModel> getApplyLfcData(Integer userId) {
		List<LfcModel> lfcData = lfcdetail.getApplyLfcData(userId);
		return lfcData;
	}

	@Override
	public List<Lfc_Dependent> getDependentName(Integer userId) {

		return lfcdetail.getDependentName(userId);
	}

	@Override
	public List<LfcModel> getLfcSurrenderData(Integer userId) {

		return lfcdetail.getLfcSurrenderData(userId);
	}

	@Override
	public String getLfcToDate(String blockApplied, String fromDate) {
		// TODO Auto-generated method stub
		return lfcdetail.getLfcToDate(blockApplied, fromDate);
	}

	@Override
	public String getUserName(Integer userId) {
		// TODO Auto-generated method stub
		return lfcdetail.getUserName(userId);
	}

	@Override
	public String getUserDesignation(Integer userId) {
		// TODO Auto-generated method stub
		return lfcdetail.getUserDesignation(userId);
	}

	@Override
	public List<Object[]> getHrAdminModalBoxData(String empId) {
		// TODO Auto-generated method stub
		return lfcdetail.getHrAdminModalBoxData(empId);
	}

	@Override
	public List<LfcModel> getHrAdminModalDependentData(String empId) {

		return lfcdetail.getHrAdminModalDependentData(empId);
	}

	@Override
	public List<LfcModel> getInternalAuditorModalBoxData(String empId) {

		return lfcdetail.getInternalAuditorModalBoxData(empId);
	}

	@Override
	public List<LfcModel> getCsModalData(String empId) {
		// TODO Auto-generated method stub
		return lfcdetail.getCsModalData(empId);
	}

	@Override
	public List<LfcModel> getCsAdminModalData(String empId) {

		return lfcdetail.getCsAdminModalData(empId);
	}

	@Override
	public List<LfcModel> getSurHrModalData(String empId) {
		// TODO Auto-generated method stub
		return lfcdetail.getSurHrModalData(empId);
	}

	@Override
	public List<LfcModel> getSurInternalAuditModalData(String empId) {
		// TODO Auto-generated method stub
		return lfcdetail.getSurInternalAuditModalData(empId);
	}

	@Override
	public List<LfcModel> getSurCsModalData(String empId) {
		// TODO Auto-generated method stub
		return lfcdetail.getSurCsModalData(empId);
	}

	@Override
	public List<LfcModel> getSurInternalAuditAdminModalData(String empId) {
		// TODO Auto-generated method stub
		return lfcdetail.getSurInternalAuditAdminModalData(empId);
	}

	@Override
	public List<LfcModel> getSurCsAdminModalData(String empId) {
		// TODO Auto-generated method stub
		return lfcdetail.getSurCsAdminModalData(empId);
	}

	@Override
	public List<LfcModel> getOfcUseData(Integer userId) {
		// TODO Auto-generated method stub
		return lfcdetail.getOfcUseData(userId);
	}

	@Override
	public List<LfcModel> getOfcUseDataSur(Integer userId) {
		// TODO Auto-generated method stub
		return lfcdetail.getOfcUseDataSur(userId);
	}

	@Override
	public int isLeaveApplied(Integer userId) {
		// TODO Auto-generated method stub
		return lfcdetail.isLeaveApplied(userId);
	}

	@Override
	public List<LfcModel> getHrAdminRequestData() {
		// TODO Auto-generated method stub
		return lfcdetail.getHrAdminRequestData();
	}

	@Override
	public int hrAdminAcceptReq(int emp_tranId, String hradminremark, String hrAmountApproved,
			String hrLeaveEncashApproved,int lfcFinalamount) {
		return lfcdetail.hrAdminAcceptReq(emp_tranId,hradminremark,hrAmountApproved,hrLeaveEncashApproved,lfcFinalamount);
		
	}

	@Override
	public int hrAdminrejectReq(int emp_tranId, String hradminremark) {
		
		return lfcdetail.hrAdminrejectReq(emp_tranId,hradminremark);
	}

	@Override
	public String uploadLfcRawFile(MultipartFile[] documentFile,Hrms_Login loginBean) {
		Lfc_Allowence docModel = new Lfc_Allowence();
		List<Lfc_Allowence> document = new ArrayList<Lfc_Allowence>();//Lfc_Allowence
		
		try {
			for(MultipartFile docFile : documentFile) {
				try {
					docModel.setDocFile(docFile.getBytes());
				} catch (Exception e) {
					e.printStackTrace();
				}
				docModel.setFileExtension(getExtenionFromMultiart(docFile.getOriginalFilename()));
 				docModel.setEmpcd(loginBean.getEmply_cd());
				docModel.setEntryDt(new Date());
				
				document.add(docModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int stat = 0;
		if(!isNullOrEmpty(document)) {
			stat = lfcdetail.uploadLfcRawFile(document);
		}
		if(stat > 0) {
			message = "SUCCESS";
		}else {
			message = "Failed";
		}
		return message;
 	}

	@Override
	public byte[] getSampleFileOFLfc(String UserId) {
		
		return lfcdetail.getSampleFileOFLfc(UserId);
	}

	@Override
	public List<LfcModel> getPreviousUploadDate(int emp_cd) {
		// TODO Auto-generated method stub
		return lfcdetail.getPreviousUploadDate(emp_cd);
	}

	@Override
	public byte[] getPrevFile(String tranId, String prevDate) {
		// TODO Auto-generated method stub
		return lfcdetail.getPrevFile(tranId,prevDate);
	}

	

	

}
