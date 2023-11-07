package com.emp.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.emp.bean.LoginBean;
import com.emp.constant.ResponseBean;
import com.emp.model.Hrms_Dependent;
import com.emp.model.Hrms_Login;
import com.emp.model.LfcModel;
import com.emp.model.Lfc_Allowence;
import com.emp.model.Lfc_Dependent;
import com.emp.model.Lfc_Surrender;
import com.emp.service.LfcDetailService;
import com.emp.service.LoginService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class LfcController {

	@Autowired
	private LoginService loginService;
	@Autowired
	private LfcDetailService lfcDetail;
	/*
	 * @Autowired private LfcDetailDaoImpl daoImpl;
	 */

	@PostMapping("lfcDetailsPost")
	@ResponseBody
	public ResponseBean viewAllowance(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("login") @Valid LoginBean loginBean, Model model) {
		String userIdStr = (String) session.getAttribute("userID");
		Integer userId = Integer.parseInt(userIdStr);

		ResponseBean bean = new ResponseBean();
		List<Object[]> login = lfcDetail.getDetails(userId);
		LfcModel lfcModel = new LfcModel();
		if (login.size() == 0) {
			bean.setMessage("NO DATA FOUND");
			bean.setStatus("NOT_FOUND");
		} else {
			int id = (int) login.get(0)[0];
			// System.out.println("id is :" + id);
			String name = (String) login.get(0)[1];
			// System.out.println("name is :" + name);
			String designation = (String) login.get(0)[2];
			// System.out.println("Designation is :" + designation);
			String classification = (String) login.get(0)[3];
			// System.out.println("Classification is :" + classification);

			lfcModel.setId(id);
			lfcModel.setName(name);
			lfcModel.setDesignation(designation);
			lfcModel.setClassification(classification);

			bean.setBody(lfcModel);
			bean.setMessage("DATA FOUND");
			bean.setStatus("FOUND");

		}

		return bean;
	}

	@PostMapping("lfcLeaveTypePost")
	@ResponseBody
	public ResponseBean lfcLeaveType(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("login") @Valid LoginBean loginBean, Model model) throws ServletException, IOException {
		ResponseBean resBean = new ResponseBean();
		Map<String, String> leaveType = lfcDetail.getLeaveType();
		if (leaveType.size() == 0) {
			resBean.setMessage("DATA NOT FOUND");
			resBean.setStatus("NOT_FOUND");
		} else {
			resBean.setBody(leaveType);
			resBean.setMessage("DATA_FOUND");
			resBean.setStatus("FOUND");
		}

		request.setAttribute("leaveType", leaveType);
		// System.out.println("this"+leaveType.get(1));

		return resBean;
	}
	
	@PostMapping("encashmentLeaveDropDownPost")
	@ResponseBody
	public ResponseBean encashmentLeaveDropDownPost(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("login") @Valid LoginBean loginBean, Model model) throws ServletException, IOException {
		System.out.println("Inside encashmentLeaveDropDownPost controller");
		ResponseBean resBean = new ResponseBean();
		Map<String, String> leaveType = lfcDetail.getEncashmentLeaveType();
		if (leaveType.size() == 0) {
			resBean.setMessage("DATA NOT FOUND");
			resBean.setStatus("NOT_FOUND");
		} else {
			resBean.setBody(leaveType);
			resBean.setMessage("DATA_FOUND");
			resBean.setStatus("FOUND");
		}

		request.setAttribute("leaveType", leaveType);
		// System.out.println("this"+leaveType.get(1));

		return resBean;
	}
	

	@ResponseBody
	@RequestMapping(value = "leaveAvailable", method = RequestMethod.POST)
	public ResponseBean LeaveTypePost(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("login") @Valid LoginBean loginBean, Model model,
			@RequestParam(required = false) String lvtype, @RequestParam(required = false) String CSRFToken) {

		String userIdStr = (String) session.getAttribute("userID");
		Integer userId = Integer.parseInt(userIdStr);

		ResponseBean lvBean = new ResponseBean();
		List<Integer> leaveCount = lfcDetail.getLeaveCount(userId, lvtype);

		if (leaveCount.size() <= 0) {
			int count = 0;
			lvBean.setBody(count);
			lvBean.setMessage("DATA FOUND");
			lvBean.setStatus("FOUND");
		} else {
			int count = leaveCount.get(0);
			lvBean.setBody(count);
			lvBean.setMessage("DATA FOUND");
			lvBean.setStatus("FOUND");
		}
		return lvBean;
	}
	
	@ResponseBody
	@RequestMapping(value = "encashmentLeaveCount", method = RequestMethod.POST)
	public ResponseBean encashmentLeaveCountPost(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("login") @Valid LoginBean loginBean, Model model,
			@RequestParam(required = false) String encashmentLeaveType, @RequestParam(required = false) String CSRFToken) {
        System.out.println("inside encashmentLeaveCount controller ");
		String userIdStr = (String) session.getAttribute("userID");
		Integer userId = Integer.parseInt(userIdStr);

		ResponseBean lvBean = new ResponseBean();
		List<Integer> leaveCount = lfcDetail.getEncashmentLeaveCount(userId, encashmentLeaveType);

		if (leaveCount.size() <= 0) {
			int count = 0;
			lvBean.setBody(count);
			lvBean.setMessage("DATA FOUND");
			lvBean.setStatus("FOUND");
		} else {
			int count = leaveCount.get(0);
			lvBean.setBody(count);
			lvBean.setMessage("DATA FOUND");
			lvBean.setStatus("FOUND");
		}
		return lvBean;
	}
	

	@ResponseBody
	@PostMapping(value = "saveInfo")
	public ResponseBean saveInfoPost(@RequestParam String data, HttpServletRequest request,
			@RequestParam(required = false) String dependentvalue) {
		ResponseBean bean = new ResponseBean();
		 
        Hrms_Login loginBean = (Hrms_Login) request.getSession(false).getAttribute("loginBean");
		 
		try {
			
			JSONArray arrayEmp = new JSONArray(data);
			JSONArray arrayDependent = new JSONArray(dependentvalue);
			
 			lfcDetail.saveInfo(arrayEmp,arrayDependent,loginBean);
			bean.setStatus("SUCCESS");
			bean.setMessage("LFC Data Added Sucessfully");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in saveInfoPost :"+e);
			bean.setStatus("FAILED");
			bean.setMessage("Failed to added LFC Data");
		}

		return bean;
	}
	
	@ResponseBody
	@PostMapping(value = "saveSurrenderData")
	public ResponseBean saveSurrenderDataPost(@RequestParam String SurrenderData, HttpServletRequest request,
			@RequestParam(required = false) String dependentvalue) {
		ResponseBean bean = new ResponseBean();
		 System.out.println("================== Inside Surrender Controller ===============");
        Hrms_Login loginBean = (Hrms_Login) request.getSession(false).getAttribute("loginBean");
		 
		try {
			
			JSONArray arrayEmp = new JSONArray(SurrenderData);
			JSONArray arrayDependent = new JSONArray(dependentvalue);
			
 			lfcDetail.saveSurrenderInfo(arrayEmp,arrayDependent,loginBean);
			bean.setStatus("SUCCESS");
			bean.setMessage("LFC Surrender Added Sucessfully");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in saveInfoPost :"+e);
			bean.setStatus("FAILED");
			bean.setMessage("Failed to added LFC Surrender");
		}

		return bean;
	}

	@ResponseBody
	@PostMapping(value = "lfcHrAdmin")
	public ResponseBean LfcHrAdminPost(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(required = false) String fromdate, @RequestParam(required = false) String todate,
			@RequestParam(required = false) String empid, ModelAndView model) {
		
		String userIdStr = (String) session.getAttribute("userID");
		Integer userId = Integer.parseInt(userIdStr);

		List<LfcModel> admindata = lfcDetail.getAdminData();

		ResponseBean bean = new ResponseBean();

		bean.setBody(admindata);
		bean.setStatus("SUCCESS");
		bean.setMessage("Data Found");

		return bean;
	}

	@ResponseBody
	@PostMapping(value = "acceptbutton")
	public ResponseBean acceptbuttonpost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String acceptval, @RequestParam(required = false) String hradminremark) {
		// System.out.println("Inside accept button controller");
		ResponseBean bean = new ResponseBean();
		try {
			int acceptValue = Integer.parseInt(acceptval);
			lfcDetail.acceptReq(acceptValue, hradminremark);
			bean.setStatus("SUCCESS");
			bean.setMessage("Request Accepted");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in :"+e);
			bean.setStatus("FAILED");
			bean.setMessage("Request is not Accepted");
		}

		return bean;
	}
	// rejectbutton

	@ResponseBody
	@PostMapping(value = "rejectbutton")
	public ResponseBean rejectbuttonpost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String rejectval, @RequestParam(required = false) String hradminremark) {
		// System.out.println("Inside reject button controller");
		ResponseBean bean = new ResponseBean();
		try {
			int rejectValue = Integer.parseInt(rejectval);
			int complete= lfcDetail.rejectReq(rejectValue, hradminremark);
			if(complete!=0) {
			bean.setStatus("SUCCESS");
			bean.setMessage("Request Rejected");
			}else {
				bean.setStatus("FAILED");
				bean.setMessage("Request is not Rejected");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in :"+e);
			bean.setStatus("FAILED");
			bean.setMessage("Request is not Rejected");
		}

		return bean;
	}

	@ResponseBody
	@PostMapping(value = "internalAuditor")
	public ResponseBean internalAuditorpost(HttpServletRequest request, HttpServletResponse response) {
		// System.out.println("Inside internalAuditorpost controller ");
		ResponseBean bean = new ResponseBean();

		try {
			List<LfcModel> internalAuditordata = lfcDetail.getInternalAuditor();

			bean.setBody(internalAuditordata);
			bean.setStatus("SUCCESS");
			bean.setMessage("Data Found");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in internalAuditorpost method :"+e);
			bean.setStatus("FAILED");
			bean.setMessage("Data Not Found");
		}

		return bean;
	}

	@ResponseBody
	@PostMapping(value = "internalacceptbutton")
	public ResponseBean internalacceptbuttonpost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String acceptval, @RequestParam(required = false) String Auditremark) {
		// System.out.println("Inside internalacceptbuttonaccept button controller");
		ResponseBean bean = new ResponseBean();
		try {
			int acceptValue = Integer.parseInt(acceptval);
			lfcDetail.InternalacceptReq(acceptValue, Auditremark);
			bean.setStatus("SUCCESS");
			bean.setMessage("Request Accepted");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in :"+e);
			bean.setStatus("FAILED");
			bean.setMessage("Request is not Accepted");
		}

		return bean;
	}

	@ResponseBody
	@PostMapping(value = "internalrejectbutton")
	public ResponseBean internalrejectbuttonpost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String rejectval, @RequestParam(required = false) String Auditremark) {
		// System.out.println("Inside internalacceptbuttonaccept button controller");
		ResponseBean bean = new ResponseBean();
		try {
			int rejectvalue = Integer.parseInt(rejectval);
			int complete=lfcDetail.InternalrejectReq(rejectvalue, Auditremark);
			if(complete!=0) {
			bean.setStatus("SUCCESS");
			bean.setMessage("Request Rejected");
			}
			else {
				bean.setStatus("FAILED");
				bean.setMessage("Request is not Rejected");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in :"+e);
			
		}

		return bean;
	}

	@ResponseBody
	@PostMapping(value = "csAdminDate")
	public ResponseBean csAdminDatepost(HttpServletRequest request, HttpServletResponse response) {
		// System.out.println("Inside csAdminDatepost controller ");
		ResponseBean bean = new ResponseBean();

		try {
			List<LfcModel> csAdmindata = lfcDetail.getCsAdmindata();

			bean.setBody(csAdmindata);
			bean.setStatus("SUCCESS");
			bean.setMessage("Data Found");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in csAdminDatepost method :"+e);
			bean.setStatus("FAILED");
			bean.setMessage("Data Not Found");
		}

		return bean;
	}

	@ResponseBody
	@PostMapping(value = "CSacceptbutton")
	public ResponseBean CSacceptbuttonpost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String acceptval, @RequestParam(required = false) String cSremark,
			@RequestParam(required = false) String empCode,HttpSession session) {
 		ResponseBean bean = new ResponseBean();
		try {
			String checkerEin = (String) session.getAttribute("userID");
			int acceptValue = Integer.parseInt(acceptval);
			int result = lfcDetail.CSacceptReq(acceptValue, cSremark,empCode,checkerEin);
			if(result!=0) {
			bean.setStatus("SUCCESS");
			bean.setMessage("Request Accepted");
			}else {
				bean.setStatus("FAILED");
				bean.setMessage("Request is not Accepted");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in :"+e);
			
		}

		return bean;
	}

	@ResponseBody
	@PostMapping(value = "CSrejectbutton")
	public ResponseBean CSrejectbuttonpost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String rejectval, @RequestParam(required = false) String cSremark) {
		// System.out.println("Inside CSrejectbuttonpost button controller");
		ResponseBean bean = new ResponseBean();
		try {
			int rejectvalue = Integer.parseInt(rejectval);
			int complete = lfcDetail.CSrejectReq(rejectvalue, cSremark);
			if(complete!=0) {
			bean.setStatus("SUCCESS");
			bean.setMessage("Request Rejected");
			}else {
				bean.setStatus("FAILED");
				bean.setMessage("Request is not Rejected");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in :"+e);
			
		}

		return bean;
	}

	@ResponseBody
	@PostMapping(value = "userFilter")
	public String userFilterpost(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("login") @Valid LoginBean loginBean, Model model) {
		String userIdStr = (String) session.getAttribute("userID");
		Integer userId = Integer.parseInt(userIdStr);
		String user = null;

		user = lfcDetail.userFilter(userId);
		// System.out.println("user"+user);
		model.addAttribute("user1", user);
		return "defaultHeader.jsp";
	}

	@ResponseBody
	@PostMapping(value = "lfcreport")
	public ResponseBean lfcreportpost(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// System.out.println("Inside report controller");
		String userIdStr = (String) session.getAttribute("userID");
		Integer userId = Integer.parseInt(userIdStr);
		ResponseBean bean = new ResponseBean();
		try {
			List<LfcModel> report = lfcDetail.getLfcHrReport(userId);
			bean.setBody(report);
			bean.setStatus("SUCCESS");
			bean.setMessage("FOUND");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in lfcreportpost Controller :"+e);
			bean.setStatus("FAILED");
			bean.setMessage("NOT FOUND");
		}
		// System.out.println("Bean Return Count-----------");
		return bean;
	}

	@ResponseBody
	@PostMapping(value = "lfcInternalAuditreport")
	public ResponseBean lfcInternalAuditreportpost(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		// System.out.println("Inside lfcInternalAuditreportpost controller");
		String userIdStr = (String) session.getAttribute("userID");
		Integer userId = Integer.parseInt(userIdStr);
		ResponseBean bean = new ResponseBean();
		try {
			List<LfcModel> report = lfcDetail.getLfcAuditReport(userId);
			bean.setBody(report);
			bean.setStatus("SUCCESS");
			bean.setMessage("FOUND");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in lfcInternalAuditreportpost Controller :"+e);
			bean.setStatus("FAILED");
			bean.setMessage("NOT FOUND");
		}
		return bean;
	}

	@ResponseBody
	@PostMapping(value = "lfcCsreport")
	public ResponseBean lfcCsreportpost(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// System.out.println("Inside lfcCsreportpost controller");
		String userIdStr = (String) session.getAttribute("userID");
		Integer userId = Integer.parseInt(userIdStr);
		ResponseBean bean = new ResponseBean();
		try {
			List<LfcModel> report = lfcDetail.getLfcCsReport(userId);
			bean.setBody(report);
			bean.setStatus("SUCCESS");
			bean.setMessage("FOUND");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in lfcInternalAuditreportpost Controller :"+e);
			bean.setStatus("FAILED");
			bean.setMessage("NOT FOUND");
		}
		return bean;
	}

	@ResponseBody
	@PostMapping(value = "lfcUserreport")
	public ResponseBean lfcUserreporttpost(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		// System.out.println("Inside lfcUserreporttpost controller");
		String userIdStr = (String) session.getAttribute("userID");
		Integer userId = Integer.parseInt(userIdStr);
		ResponseBean bean = new ResponseBean();
		try {
			List<LfcModel> report = lfcDetail.getLfcUserReport(userId);
			bean.setBody(report);
			bean.setStatus("SUCCESS");
			bean.setMessage("FOUND");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in lfcInternalAuditreportpost Controller :"+e);
			bean.setStatus("FAILED");
			bean.setMessage("NOT FOUND");
		}
		return bean;
	}

	@ResponseBody
	@PostMapping(value = "lfcLeaveDateDiff")
	public ResponseBean lfcLeaveDateDiffpost(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @RequestParam(required = false) String leavefrmdate,
			@RequestParam(required = false) String leavetodate) {
		ResponseBean bean = new ResponseBean();
		try {
			BigInteger diff = lfcDetail.getLfcDateDiff(leavetodate, leavefrmdate);
			int count = diff.intValue();
			bean.setBody(count);
			bean.setStatus("SUCCESS");
			bean.setMessage("FOUND");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in lfcLeaveDateDiffpost controller :"+e);
			bean.setStatus("FAILED");
			bean.setMessage("NOT FOUND");
		}

		return bean;
	}
	
	@ResponseBody
	@PostMapping(value = "encashmentLeaveDateDiff")
	public ResponseBean encashmentLeaveDateDiffpost(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String todate) {
		ResponseBean bean = new ResponseBean();
		try {
			BigInteger diff = lfcDetail.getLfcDateDiff(todate, fromDate);
			int count = diff.intValue();
			bean.setBody(count);
			bean.setStatus("SUCCESS");
			bean.setMessage("FOUND");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in lfcLeaveDateDiffpost controller :"+e);
			bean.setStatus("FAILED");
			bean.setMessage("NOT FOUND");
		}

		return bean;
	}

	@ResponseBody
	@PostMapping(value = "internalAuditorAdmin")
	public ResponseBean InternalAuditorAdminpost(HttpServletRequest request, HttpServletResponse response) {
		// System.out.println("Inside InternalAuditorAdminpost controller ");
		ResponseBean bean = new ResponseBean();

		try {
			List<LfcModel> internalAuditorAdmindata = lfcDetail.getInternalAuditorAdmin();

			bean.setBody(internalAuditorAdmindata);
			bean.setStatus("SUCCESS");
			bean.setMessage("Data Found");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in InternalAuditorAdminpost method :"+e);
			bean.setStatus("FAILED");
			bean.setMessage("Data Not Found");
		}

		return bean;
	}

	@ResponseBody
	@PostMapping(value = "InternalAdminacceptButton")
	public ResponseBean InternalAdminacceptButtonpost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String acceptval, @RequestParam(required = false) String AuditAdminremark) {
		// System.out.println("Inside InternalAdminacceptButton button controller");
		ResponseBean bean = new ResponseBean();
		try {
			int acceptValue = Integer.parseInt(acceptval);
			lfcDetail.auditAdminremarkReq(acceptValue, AuditAdminremark);
			bean.setStatus("SUCCESS");
			bean.setMessage("Request Accepted");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in :"+e);
			bean.setStatus("FAILED");
			bean.setMessage("Request is not Accepted");
		}

		return bean;
	}

	@ResponseBody
	@PostMapping(value = "internalAdminrejectbutton")
	public ResponseBean internalAdminrejectbuttonpost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String rejectval, @RequestParam(required = false) String Auditremark) {
		// System.out.println("Inside internalacceptbuttonaccept button controller");
		ResponseBean bean = new ResponseBean();
		try {
			int rejectvalue = Integer.parseInt(rejectval);
			int complete = lfcDetail.InternalrejectReq(rejectvalue, Auditremark);
			if(complete!=0) {
			bean.setStatus("SUCCESS");
			bean.setMessage("Request Rejected");
			}else {
				bean.setStatus("FAILED");
				bean.setMessage("Request is not Rejected");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in :"+e);
			
		}

		return bean;
	}

	@ResponseBody
	@PostMapping(value = "csAdminLfcReqDate")
	public ResponseBean csAdminLfcReqDatepost(HttpServletRequest request, HttpServletResponse response) {
		// System.out.println("Inside InternalAuditorAdminpost controller ");
		ResponseBean bean = new ResponseBean();

		try {
			List<LfcModel> internalAuditorAdmindata = lfcDetail.getCsAdmin();

			bean.setBody(internalAuditorAdmindata);
			bean.setStatus("SUCCESS");
			bean.setMessage("Data Found");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in InternalAuditorAdminpost method :"+e);
			bean.setStatus("FAILED");
			bean.setMessage("Data Not Found");
		}

		return bean;
	}

	@ResponseBody
	@PostMapping(value = "CSAdminacceptbutton")
	public ResponseBean CSAdminacceptbuttonpost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String acceptval, @RequestParam(required = false) String cSremark,
			@RequestParam(required = false) String empCode,HttpSession session) {
		// System.out.println("Inside CSAdminacceptbuttonpost button controller");
		ResponseBean bean = new ResponseBean();
		try {
			String checkerEin = (String) session.getAttribute("userID");
			int acceptValue = Integer.parseInt(acceptval);
			int result = lfcDetail.CSacceptReq(acceptValue, cSremark,empCode,checkerEin);
			if(result!=0) {
			bean.setStatus("SUCCESS");
			bean.setMessage("Request Accepted");
			}else {
				bean.setStatus("FAILED");
				bean.setMessage("Request is not Accepted");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in :"+e);
 		}

		return bean;
	}

	@ResponseBody
	@PostMapping(value = "CSAdminrejectbutton")
	public ResponseBean CSAdminrejectbuttonpost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String rejectval, @RequestParam(required = false) String cSremark) {
		// System.out.println("Inside CSrejectbuttonpost button controller");
		ResponseBean bean = new ResponseBean();
		try {
			int rejectvalue = Integer.parseInt(rejectval);
			int complete=lfcDetail.CSrejectReq(rejectvalue, cSremark);
			bean.setStatus("SUCCESS");
			bean.setMessage("Request Rejected");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in :"+e);
			bean.setStatus("FAILED");
			bean.setMessage("Request is not Rejected");
		}

		return bean;
	}
	
	
	@ResponseBody  
     @PostMapping(value = "depenedentListPost")
     public ResponseBean depenedentListPost(HttpServletRequest request, HttpServletResponse response,
                  HttpSession session) {
          String userIdStr = (String) session.getAttribute("userID");
           Integer userId = Integer.parseInt(userIdStr);
           ResponseBean bean = new ResponseBean();
          List<Hrms_Dependent> data = new ArrayList<Hrms_Dependent>();
           data = lfcDetail.getdepenedentList(userId);
 
          if (data.size() == 0) {
                  bean.setMessage("DATA NOT FOUND");
                  bean.setStatus("NOT_FOUND");
           } else {
                  bean.setBody(data);
                  bean.setMessage("DATA_FOUND");
                  bean.setStatus("FOUND");
           }
 
          return bean;
     }
	
     @ResponseBody  
     @PostMapping(value = "lfcHrAdminDependentApproval")
     public ResponseBean lfcHrAdminDependentApprovalPost(HttpServletRequest request, HttpServletResponse response,
                  HttpSession session,@RequestParam(required = false) String emply_cd) {
    	 System.out.println("employe code inside success function is ===========" + emply_cd);
         ResponseBean bean = new ResponseBean();
        List<String> dependent = lfcDetail.getDependentName(emply_cd);
        if (dependent.size() == 0) {
            bean.setMessage("DATA NOT FOUND");
            bean.setStatus("NOT_FOUND");
     } else {
            bean.setBody(dependent);
            bean.setMessage("DATA_FOUND");
            bean.setStatus("FOUND");
     }
    	 return bean;
     }
     
     
     @ResponseBody  
     @PostMapping(value = "lfcCumnEncashmentpdfDownload")
     public ResponseBean lfcCumnEncashmentpdfDownloadPost(HttpServletRequest request, HttpServletResponse response,
                  HttpSession session,@RequestParam(required = false) String emply_cd) {
    	 System.out.println("employe code inside success function is ===========" + emply_cd);
         ResponseBean bean = new ResponseBean();
         String userIdStr = (String) session.getAttribute("userID");
 		Integer userId = Integer.parseInt(userIdStr);
 		lfcDetail.fectchLfcApplyData(userId);

    	 return bean;
     }

     @ResponseBody
 	@PostMapping(value = "lfcSurrenderUserReport")
 	public ResponseBean lfcSurrenderUserReporttpost(HttpServletRequest request, HttpServletResponse response,
 			HttpSession session) {
 		 System.out.println("Inside lfcSurrenderUserReport controller");
 		String userIdStr = (String) session.getAttribute("userID");
 		Integer userId = Integer.parseInt(userIdStr);
 		ResponseBean bean = new ResponseBean();
 		try {
 			List<LfcModel> report = lfcDetail.getLfcUserSurrenderReport(userId);
 			if(report!=null) {
 				
 				bean.setBody(report);
 				bean.setStatus("SUCCESS");
 				bean.setMessage("FOUND");
 			}else {
 				
 				bean.setStatus("FAILED");
 				bean.setMessage("NOT FOUND");
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
  		}
 		return bean;
 	}
     
     
     @ResponseBody
 	@PostMapping(value = "lfcSurrenderHrAdmin")
 	public ResponseBean lfcSurrenderHrAdminPost(HttpServletRequest request, HttpServletResponse response, HttpSession session,
 			 ModelAndView model) {
 
 		List<LfcModel> hradmindata = lfcDetail.getSurrenderHrAdminData();

 		ResponseBean bean = new ResponseBean();

 		try {
			if(hradmindata!=null) {
				bean.setBody(hradmindata);
		 		bean.setStatus("SUCCESS");
		 		bean.setMessage("Data Found");
			}else {
				bean.setStatus("FAIELD");
		 		bean.setMessage("Data not Found");
			}
		} catch (Exception e) {
			
		}

 		return bean;
 	}
     
    
    @ResponseBody
 	@PostMapping(value = "hrSurAcceptbutton")
 	public ResponseBean hrSurAcceptbuttonpost(HttpServletRequest request, HttpServletResponse response,
 			@RequestParam(required = false) String acceptval, @RequestParam(required = false) String hradminremark) {
 		// System.out.println("Inside accept button controller");
 		ResponseBean bean = new ResponseBean();
 		try {
 			int acceptValue = Integer.parseInt(acceptval);
 			int result = lfcDetail.hrSurAcceptReq(acceptValue, hradminremark);
 			if(result!=0) {
 				bean.setStatus("SUCCESS");
 	 			bean.setMessage("Request Accepted");
 			}else {
 				bean.setStatus("FAILED");
 	 			bean.setMessage("Request is not Accepted");
 			}
  		} catch (Exception e) {
 			e.printStackTrace();
   		}

 		return bean;
 	}
    
    @ResponseBody
 	@PostMapping(value = "hrSurRejectbutton")
 	public ResponseBean hrSurRejectbuttonpost(HttpServletRequest request, HttpServletResponse response,
 			@RequestParam(required = false) String rejectval, @RequestParam(required = false) String hradminremark) {
 		// System.out.println("Inside accept button controller");
 		ResponseBean bean = new ResponseBean();
 		try {
 			int rejectvalue = Integer.parseInt(rejectval);
 			int result = lfcDetail.hrSurRejectReq(rejectvalue, hradminremark);
 			if(result!=0) {
 				bean.setStatus("SUCCESS");
 	 			bean.setMessage("Request Rejected");
 			}else {
 				bean.setStatus("FAILED");
 	 			bean.setMessage("Request is not Rejected");
 			}
  		} catch (Exception e) {
 			e.printStackTrace();
   		}

 		return bean;
 	}
    
    @ResponseBody
	@PostMapping(value = "lfcSurrenderHrReport")
	public ResponseBean lfcSurrenderHrReportpost(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		 
		 System.out.println("=================inside lfcSurrenderHrReport Controller====================");
		ResponseBean bean = new ResponseBean();
		try {
			List<LfcModel> report = lfcDetail.getLfcSurrenderHrReport();
			if(report!=null) {
				bean.setBody(report);
				bean.setStatus("SUCCESS");
				bean.setMessage("FOUND");
			}else {
				bean.setStatus("FAILED");
				bean.setMessage("NOT FOUND");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
 			
		}
 		return bean;
	}
    
    @ResponseBody
	@PostMapping(value = "lfcSurrenderinternalAuditor")
	public ResponseBean lfcSurrenderinternalAuditorpost(HttpServletRequest request, HttpServletResponse response) {
		 
		ResponseBean bean = new ResponseBean();

		try {
			List<LfcModel> internalAuditorAdmindata = lfcDetail.getSurrenderInternalAuditorAdmin();
            if(internalAuditorAdmindata!=null) {
            	
            	bean.setBody(internalAuditorAdmindata);
            	bean.setStatus("SUCCESS");
            	bean.setMessage("Data Found");
            }else {
             	bean.setStatus("FAILED");
            	bean.setMessage("Data Not Found");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bean;
	}
    
    @ResponseBody
	@PostMapping(value = "Surinternalacceptbutton")
	public ResponseBean Surinternalacceptbuttonpost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String acceptval, @RequestParam(required = false) String AuditAdminremark) {
		// System.out.println("Inside InternalAdminacceptButton button controller");
		ResponseBean bean = new ResponseBean();
		try {
			int acceptValue = Integer.parseInt(acceptval);
			int result=lfcDetail.surInternalAdminAccept(acceptValue, AuditAdminremark);
			if(result!=0) {
				
				bean.setStatus("SUCCESS");
				bean.setMessage("Request Accepted");
			}else {
				
				bean.setStatus("FAILED");
				bean.setMessage("Request is not Accepted");
			}
		} catch (Exception e) {
			e.printStackTrace();
 		}

		return bean;
	}

    @ResponseBody
	@PostMapping(value = "Surinternalrejectbutton")
	public ResponseBean Surinternalrejectbuttonpost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String rejectval, @RequestParam(required = false) String Auditremark) {
 		ResponseBean bean = new ResponseBean();
		try {
			int rejectvalue = Integer.parseInt(rejectval);
			int complete=lfcDetail.surInternalrejectReq(rejectvalue, Auditremark);
			if(complete!=0) {
			bean.setStatus("SUCCESS");
			bean.setMessage("Request Rejected");
			}
			else {
				bean.setStatus("FAILED");
				bean.setMessage("Request is not Rejected");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in :"+e);
			
		}

		return bean;
	}
    
    @ResponseBody
	@PostMapping(value = "lfcSurrenderInternalAdminReport")
	public ResponseBean lfcSurrenderInternalAdminReportpost(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
 		 
		ResponseBean bean = new ResponseBean();
		try {
			List<LfcModel> report = lfcDetail.getSurAuditReport();
			if(report!=null) {
				
				bean.setBody(report);
				bean.setStatus("SUCCESS");
				bean.setMessage("FOUND");
			}else {
				
				bean.setStatus("FAILED");
				bean.setMessage("NOT FOUND");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
    
    @ResponseBody
	@PostMapping(value = "lfcSurrenderCsAuditor")
	public ResponseBean lfcSurrenderCsAuditorpost(HttpServletRequest request, HttpServletResponse response) {
 		ResponseBean bean = new ResponseBean();

		try {
			List<LfcModel> csAdmindata = lfcDetail.getSurrenderCsAdmindata();
            if(csAdmindata!=null) {
             	bean.setBody(csAdmindata);
            	bean.setStatus("SUCCESS");
            	bean.setMessage("Data Found");
            }else {
             	bean.setStatus("FAILED");
            	bean.setMessage("Data Not Found");
            }
		} catch (Exception e) {
			e.printStackTrace();
 		}

		return bean;
	}
    
    @ResponseBody
	@PostMapping(value = "surCScceptbutton")
	public ResponseBean surCScceptbuttonpost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String acceptval, @RequestParam(required = false) String cSremark,
			@RequestParam(required = false) String empCode , HttpSession session) {
 		ResponseBean bean = new ResponseBean();
		try {
			String checkerEin = (String) session.getAttribute("userID");
			int acceptValue = Integer.parseInt(acceptval);
			int complete = lfcDetail.surCSacceptReq(acceptValue, cSremark,empCode,checkerEin);
			if(complete!=0) {
				
				bean.setStatus("SUCCESS");
				bean.setMessage("Request Accepted");
			}else {
				
				bean.setStatus("FAILED");
				bean.setMessage("Request is not Accepted");
			}
		} catch (Exception e) {
			e.printStackTrace();
 		}

		return bean;
	}


    @ResponseBody
	@PostMapping(value = "surCSrejectbutton")
	public ResponseBean surCSrejectbuttonpost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String rejectval, @RequestParam(required = false) String cSremark) {
 		ResponseBean bean = new ResponseBean();
		try {
			int rejectvalue = Integer.parseInt(rejectval);
			int complete = lfcDetail.surCSrejectReq(rejectvalue, cSremark);
			if(complete!=0) {
			bean.setStatus("SUCCESS");
			bean.setMessage("Request Rejected");
			}else {
				bean.setStatus("FAILED");
				bean.setMessage("Request is not Rejected");
			}
		} catch (Exception e) {
			e.printStackTrace();
			 
		}
 		return bean;
	}
    
    @ResponseBody
	@PostMapping(value = "surCsreport")
	public ResponseBean surCsreportpost(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
 		 
		ResponseBean bean = new ResponseBean();
		try {
			List<LfcModel> report = lfcDetail.getSurCsReport();
			if(report!=null) {
				
				bean.setBody(report);
				bean.setStatus("SUCCESS");
				bean.setMessage("FOUND");
			}else {
				
				bean.setStatus("FAILED");
				bean.setMessage("NOT FOUND");
			}
		} catch (Exception e) {
			e.printStackTrace();
 		}
		return bean;
	}
    
    @ResponseBody
   	@PostMapping(value = "surIAadmin")
   	public ResponseBean surIAadminpost(HttpServletRequest request, HttpServletResponse response) {
   		 
   		ResponseBean bean = new ResponseBean();

   		try {
   			List<LfcModel> internalAuditorAdmindata = lfcDetail.getSurrenderInternalAuditorAdminadmin();
               if(internalAuditorAdmindata!=null) {
               	
               	bean.setBody(internalAuditorAdmindata);
               	bean.setStatus("SUCCESS");
               	bean.setMessage("Data Found");
               }else {
                	bean.setStatus("FAILED");
               	bean.setMessage("Data Not Found");
               }
   		} catch (Exception e) {
   			e.printStackTrace();
   		}

   		return bean;
   	}
    
    @ResponseBody
   	@PostMapping(value = "surInternalAdminacceptButton")
   	public ResponseBean surInternalAdminacceptButtonpost(HttpServletRequest request, HttpServletResponse response,
   			@RequestParam(required = false) String acceptval, @RequestParam(required = false) String AuditAdminremark) {
    	
    		ResponseBean bean = new ResponseBean();
   		try {
   			int acceptValue = Integer.parseInt(acceptval);
   			int result=lfcDetail.surInternalAdminAccept(acceptValue, AuditAdminremark);
   			if(result!=0) {
   				
   				bean.setStatus("SUCCESS");
   				bean.setMessage("Request Accepted");
   			}else {
   				
   				bean.setStatus("FAILED");
   				bean.setMessage("Request is not Accepted");
   			}
   		} catch (Exception e) {
   			e.printStackTrace();
    		}

   		return bean;
   	}
    
    @ResponseBody
	@PostMapping(value = "surinternalAdminrejectbutton")
	public ResponseBean SurinternalAdminrejectbuttonpost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String rejectval, @RequestParam(required = false) String Auditremark) {
 		ResponseBean bean = new ResponseBean();
		try {
			int rejectvalue = Integer.parseInt(rejectval);
			int complete=lfcDetail.surInternalrejectReq(rejectvalue, Auditremark);
			if(complete!=0) {
			bean.setStatus("SUCCESS");
			bean.setMessage("Request Rejected");
			}
			else {
				bean.setStatus("FAILED");
				bean.setMessage("Request is not Rejected");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in :"+e);
			
		}

		return bean;
	}
    
    @ResponseBody
	@PostMapping(value = "SurcsAdminLfcReqAdmin")
	public ResponseBean SurcsAdminLfcReqAdminpost(HttpServletRequest request, HttpServletResponse response) {
    	 
 		ResponseBean bean = new ResponseBean();

		try {
			List<LfcModel> internalAuditorAdmindata = lfcDetail.surCsAdminRequest();
            if(internalAuditorAdmindata!=null) {
            	
            	bean.setBody(internalAuditorAdmindata);
            	bean.setStatus("SUCCESS");
            	bean.setMessage("Data Found");
            }else {
            	
            	bean.setStatus("FAILED");
            	bean.setMessage("Data Not Found");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bean;
	}
    
   
    @ResponseBody
	@PostMapping(value = "SurCSAdminacceptbutton")
	public ResponseBean SurCSAdminacceptbuttonpost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String acceptval, @RequestParam(required = false) String cSremark,
			@RequestParam(required = false) String empCode , HttpSession session) {
 		ResponseBean bean = new ResponseBean();
		try {
			String checkerEin = (String) session.getAttribute("userID");
			int acceptValue = Integer.parseInt(acceptval);
			int complete = lfcDetail.surCSacceptReq(acceptValue, cSremark,empCode,checkerEin);
			if(complete!=0) {
				
				bean.setStatus("SUCCESS");
				bean.setMessage("Request Accepted");
			}else {
				
				bean.setStatus("FAILED");
				bean.setMessage("Request is not Accepted");
			}
		} catch (Exception e) {
			e.printStackTrace();
 		}

		return bean;
	}
    
  
    @ResponseBody
	@PostMapping(value = "SurCSAdminrejectbutton")
	public ResponseBean SurCSAdminrejectbuttonpost(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) String rejectval, @RequestParam(required = false) String cSremark) {
 		ResponseBean bean = new ResponseBean();
		try {
			int rejectvalue = Integer.parseInt(rejectval);
			int complete = lfcDetail.surCSrejectReq(rejectvalue, cSremark);
			if(complete!=0) {
			bean.setStatus("SUCCESS");
			bean.setMessage("Request Rejected");
			}else {
				bean.setStatus("FAILED");
				bean.setMessage("Request is not Rejected");
			}
		} catch (Exception e) {
			e.printStackTrace();
			 
		}
 		return bean;
	}  
    
    @ResponseBody
	@PostMapping(value = "ApplyLfcPdf")
	public ResponseBean ApplyLfcPdfpost(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
    	String userIdStr = (String) session.getAttribute("userID");
		Integer userId = Integer.parseInt(userIdStr);
		ResponseBean bean = new ResponseBean();
		try {
			 
			 List<LfcModel> lfcModel = lfcDetail.getApplyLfcData(userId);
			 if(lfcModel.size()!=0) {
				 bean.setStatus("FOUND");
			 List<Lfc_Dependent> dependent = lfcDetail.getDependentName(userId);
 			 request.getSession().setAttribute("lfcModel", lfcModel.get(0));
			 request.getSession().setAttribute("dependent", dependent);
			 }else {
				 bean.setStatus("FAILED");
					bean.setMessage("Request is not Rejected");
			 }
		} catch (Exception e) {
			e.printStackTrace();
			 
		}
 		 
		return bean;
	} 
    
    @ResponseBody
	@PostMapping(value = "SurrenderLfcPdf")
	public ResponseBean SurrenderLfcPdfpost(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
    	String userIdStr = (String) session.getAttribute("userID");
		Integer userId = Integer.parseInt(userIdStr);
		List<Lfc_Dependent> dependent=null;
		ResponseBean bean = new ResponseBean();
		try {
			 List<LfcModel> lfcModel = lfcDetail.getLfcSurrenderData(userId);
			 if(lfcModel.size()!=0) {
				 bean.setStatus("FOUND");
			  dependent = lfcDetail.getDependentName(userId);
			 
			 request.getSession().setAttribute("lfcModel", lfcModel.get(0));
			 request.getSession().setAttribute("dependent", dependent);
			 }else {
				 bean.setStatus("FAILED");
				bean.setMessage("Request is not Rejected");
			 }
		} catch (Exception e) {
			e.printStackTrace();
			 
		}
 		 
		return bean;
	}  
    
    
    @ResponseBody
	@PostMapping(value = "dateChangeForBlockApplied")
	public ResponseBean DateChangeForBlockAppliedpost(HttpServletRequest request, HttpServletResponse response,
			HttpSession session,@RequestParam(required = false) String blockApplied,@RequestParam(required = false) String fromDate) {
    	ResponseBean bean = new ResponseBean();
    	try {
    		String todate = lfcDetail.getLfcToDate(blockApplied,fromDate);
    		if(todate!=null) {
    		bean.setBody(todate);
        	bean.setStatus("SUCCESS");
        	bean.setMessage("Data Found");
    		}else {
    			bean.setStatus("FAIL");
            	bean.setMessage("Data Not Found");
    		}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in DateChangeForBlockAppliedpost controller :"+e);
		}
    	return bean;
    }
    
    
    @ResponseBody
   	@PostMapping(value = "hrAdminModalBoxData")
   	public ResponseBean HrAdminModalBoxDatapost(HttpServletRequest request, HttpServletResponse response,
   			HttpSession session,@RequestParam(required = false) String empId) {
       	ResponseBean bean = new ResponseBean();
       	LfcModel model = new LfcModel();
        	try {
       		 List<Object[]> outPut = lfcDetail.getHrAdminModalBoxData(empId);
       		 List<LfcModel> depdentOutput = lfcDetail.getHrAdminModalDependentData(empId);
       		model.setId((int) outPut.get(0)[0]);
 			model.setName((String)outPut.get(0)[1]);
			model.setDesignation((String)outPut.get(0)[2]);
 			model.setLeaveEncashmentBlock((String)outPut.get(0)[3]);
 			
 			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
		    String strFormDate = formatter.format(outPut.get(0)[4]);
 			model.setLfcFromDate(strFormDate);
 			
			SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");  
		    String strtomDate = formatter1.format(outPut.get(0)[5]);
 			model.setLfctoDate(strtomDate);
			 
 			model.setLeaveTypeStr((String)outPut.get(0)[6]);
 			model.setLeavefromDateStr((String)outPut.get(0)[7]);
 			model.setLeavetoDateStr((String)outPut.get(0)[8]);
 			model.setNumberofDays((int) outPut.get(0)[9]);
			model.setPlaceofDestination((String)outPut.get(0)[10]);
			
			SimpleDateFormat formatter2 = new SimpleDateFormat("MM/dd/yyyy");  
		    String commencementFromDate = formatter2.format(outPut.get(0)[11]);
 			model.setCommencementFromDate(commencementFromDate);
			
			SimpleDateFormat formatter3 = new SimpleDateFormat("MM/dd/yyyy");  
		    String complitionToDate = formatter3.format(outPut.get(0)[12]);
 			model.setComplitionToDate(complitionToDate);
			
			model.setAmountofAdvance((int)outPut.get(0)[13]);
			
			if(outPut.size()!=0) {
				bean.setBody(model);
				bean.setStatus("FOUND");
				bean.setTablebody(depdentOutput);
			}else {
 				bean.setStatus("FAILED");
			}
        		  
   		} catch (Exception e) {
   			e.printStackTrace();
   			System.out.println("Exception in HrAdminModalBoxDatapost controller :"+e);
   		}
       	return bean;
       }
    
    
    @ResponseBody
   	@PostMapping(value = "internalAdminModalBox")
   	public ResponseBean InternalAdminModalBoxpost(HttpServletRequest request, HttpServletResponse response,
   			HttpSession session,@RequestParam(required = false) String empId) {
    	System.out.println("InternalAdminModalBoxpost controller start");
    	ResponseBean bean = new ResponseBean();
    	try {
    		List<LfcModel> outPut = lfcDetail.getInternalAuditorModalBoxData(empId);
      		List<LfcModel> depdentOutput = lfcDetail.getHrAdminModalDependentData(empId);
      		if(outPut.size()!=0) {
      			bean.setStatus("FOUND");
      			bean.setBody(outPut);
      			bean.setTablebody(depdentOutput);
      		}else {
      			bean.setStatus("FAILED");
      		}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in InternalAdminModalBoxpost controller :"+e);
		}
    	return bean;
    }
    
 
    @ResponseBody
   	@PostMapping(value = "internalAdminModalData")
   	public ResponseBean InternalAdminModalDatapost(HttpServletRequest request, HttpServletResponse response,
   			HttpSession session,@RequestParam(required = false) String empId) {
       	ResponseBean bean = new ResponseBean();
       	LfcModel model = new LfcModel();
        	try {
       		 List<Object[]> outPut = lfcDetail.getHrAdminModalBoxData(empId);
       		 List<LfcModel> depdentOutput = lfcDetail.getHrAdminModalDependentData(empId);
       		model.setId((int) outPut.get(0)[0]);
 			model.setName((String)outPut.get(0)[1]);
			model.setDesignation((String)outPut.get(0)[2]);
 			model.setLeaveEncashmentBlock((String)outPut.get(0)[3]);
 			
 			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
		    String strFormDate = formatter.format(outPut.get(0)[4]);
 			model.setLfcFromDate(strFormDate);
 			
			SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");  
		    String strtomDate = formatter1.format(outPut.get(0)[5]);
 			model.setLfctoDate(strtomDate);
			 
 			model.setLeaveTypeStr((String)outPut.get(0)[6]);
 			model.setLeavefromDateStr((String)outPut.get(0)[7]);
 			model.setLeavetoDateStr((String)outPut.get(0)[8]);
 			model.setNumberofDays((int) outPut.get(0)[9]);
			model.setPlaceofDestination((String)outPut.get(0)[10]);
			
			SimpleDateFormat formatter2 = new SimpleDateFormat("MM/dd/yyyy");  
		    String commencementFromDate = formatter2.format(outPut.get(0)[11]);
 			model.setCommencementFromDate(commencementFromDate);
			
			SimpleDateFormat formatter3 = new SimpleDateFormat("MM/dd/yyyy");  
		    String complitionToDate = formatter3.format(outPut.get(0)[12]);
 			model.setComplitionToDate(complitionToDate);
			
			model.setAmountofAdvance((int)outPut.get(0)[13]);
			
			if(outPut.size()!=0) {
				bean.setBody(model);
				bean.setStatus("FOUND");
				bean.setTablebody(depdentOutput);
			}else {
 				bean.setStatus("FAILED");
			}
        		  
   		} catch (Exception e) {
   			e.printStackTrace();
   			System.out.println("Exception in InternalAdminModalDatapost controller :"+e);
   		}
       	return bean;
       }
    
    
    @ResponseBody
   	@PostMapping(value = "CSModalData")
   	public ResponseBean CSModalDatapost(HttpServletRequest request, HttpServletResponse response,
   			HttpSession session,@RequestParam(required = false) String empId) {
    	ResponseBean bean = new ResponseBean();
    	try {
			List<LfcModel> model = lfcDetail.getCsModalData(empId);
			List<LfcModel> depdentOutput = lfcDetail.getHrAdminModalDependentData(empId);
			if(model.size()!=0) {
				bean.setStatus("FOUND");
				bean.setBody(model);
				bean.setTablebody(depdentOutput);
			}else {
				bean.setStatus("FAILED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in CSModalDatapost controller :"+e);
		}
    	return bean;
    }
    
    
    @ResponseBody
   	@PostMapping(value = "CSAdminModalData")
   	public ResponseBean CSAdminModalDatapost(HttpServletRequest request, HttpServletResponse response,
   			HttpSession session,@RequestParam(required = false) String empId) {
    	ResponseBean bean = new ResponseBean();
    	try {
    		List<LfcModel> model = lfcDetail.getCsAdminModalData(empId);
			List<LfcModel> depdentOutput = lfcDetail.getHrAdminModalDependentData(empId);
			if(model.size()!=0) {
				bean.setStatus("FOUND");
				bean.setBody(model);
				bean.setTablebody(depdentOutput);
			}
			else {
				bean.setStatus("FAILED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in CSAdminModalDatapost controller :"+e);
		}
    	return bean;
    }
    

    @ResponseBody
   	@PostMapping(value = "hrSurModalData")
   	public ResponseBean HrSurModalDatapost(HttpServletRequest request, HttpServletResponse response,
   			HttpSession session,@RequestParam(required = false) String empId) {
    	ResponseBean bean = new ResponseBean();
    	try {
    		List<LfcModel> model = lfcDetail.getSurHrModalData(empId);
			List<LfcModel> depdentOutput = lfcDetail.getHrAdminModalDependentData(empId);
			if(model.size()!=0) {
				bean.setStatus("FOUND");
				bean.setBody(model);
				bean.setTablebody(depdentOutput);
			}
			else {
				bean.setStatus("FAILED");
			}
		} catch (Exception e) {
			 e.printStackTrace();
			 System.out.println("Exception in HrSurModalDatapost controller :"+e);
		}
    	return bean;
    }
    
    
    @ResponseBody
   	@PostMapping(value = "SurinternalAuditModalData")
   	public ResponseBean SurinternalAuditModalDataPost(HttpServletRequest request, HttpServletResponse response,
   			HttpSession session,@RequestParam(required = false) String empId) {
    	ResponseBean bean = new ResponseBean();
    	try {
    		List<LfcModel> model = lfcDetail.getSurInternalAuditModalData(empId);
			List<LfcModel> depdentOutput = lfcDetail.getHrAdminModalDependentData(empId);
			if(model.size()!=0) {
				bean.setStatus("FOUND");
				bean.setBody(model);
				bean.setTablebody(depdentOutput);
			}
			else {
				bean.setStatus("FAILED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in SurinternalAuditModalDataPost controller :"+e);
		}
    	return bean;
    }
    
    @ResponseBody
   	@PostMapping(value = "surCSModal")
   	public ResponseBean surCSModalDataPost(HttpServletRequest request, HttpServletResponse response,
   			HttpSession session,@RequestParam(required = false) String empId) {
    	ResponseBean bean = new ResponseBean();
    	try {
    		List<LfcModel> model = lfcDetail.getSurCsModalData(empId);
			List<LfcModel> depdentOutput = lfcDetail.getHrAdminModalDependentData(empId);
			if(model.size()!=0) {
				bean.setStatus("FOUND");
				bean.setBody(model);
				bean.setTablebody(depdentOutput);
			}
			else {
				bean.setStatus("FAILED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in surCSModalDataPost controller :"+e);
		}
    	return bean;
    }
    
    @ResponseBody
   	@PostMapping(value = "surinternalAdminModalData")
   	public ResponseBean SurinternalAdminModalDataPost(HttpServletRequest request, HttpServletResponse response,
   			HttpSession session,@RequestParam(required = false) String empId) {
    	ResponseBean bean = new ResponseBean();
    	try {
    		List<LfcModel> model = lfcDetail.getSurInternalAuditAdminModalData(empId);
			List<LfcModel> depdentOutput = lfcDetail.getHrAdminModalDependentData(empId);
			if(model.size()!=0) {
				bean.setStatus("FOUND");
				bean.setBody(model);
				bean.setTablebody(depdentOutput);
			}
			else {
				bean.setStatus("FAILED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in surCSModalDataPost controller :"+e);
		}
    	return bean;
    }
    
    
    @ResponseBody
   	@PostMapping(value = "SurCSAdminModalData")
   	public ResponseBean SurCSAdminModalDataPost(HttpServletRequest request, HttpServletResponse response,
   			HttpSession session,@RequestParam(required = false) String empId) {
    	ResponseBean bean = new ResponseBean();
    	try {
    		List<LfcModel> model = lfcDetail.getSurCsAdminModalData(empId);
			List<LfcModel> depdentOutput = lfcDetail.getHrAdminModalDependentData(empId);
			if(model.size()!=0) {
				bean.setStatus("FOUND");
				bean.setBody(model);
				bean.setTablebody(depdentOutput);
			}
			else {
				bean.setStatus("FAILED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in surCSModalDataPost controller :"+e);
		}
    	return bean;
    }
    
    
}
