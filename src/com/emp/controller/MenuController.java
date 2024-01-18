package com.emp.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.emp.bean.LoginBean;
import com.emp.service.LfcDetailService;
import com.emp.token.CSRFToken;

@Controller
public class MenuController {

	@Autowired
	private LfcDetailService lfcDetail;

	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView loadLogin(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("login", new LoginBean());
		mv.setViewName("Login");
		String csrf = CSRFToken.getTokenForSession(request.getSession());
		mv.addObject("CSRFToken", csrf);
		request.getSession().setAttribute("CSRFToken", csrf);
		return mv;
	}

	@RequestMapping(value = "empDetail", method = RequestMethod.POST)
	public ModelAndView userCreateMenu() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("empDetail");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}

	@RequestMapping(value = "allowances", method = RequestMethod.POST)
	public ModelAndView allowances() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("allowances");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}

	@RequestMapping(value = "festivalAdvance", method = RequestMethod.POST)
	public ModelAndView festivalAdvance() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("festivalAdvance");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}

	@RequestMapping(value = "festivalAdvance1", method = RequestMethod.POST)
	public ModelAndView festivalAdvance1() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("festivalAdvance1");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}

	@RequestMapping(value = "newspaperAllowance", method = RequestMethod.POST)
	public ModelAndView newspaperAllowance() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("newspaperAllowance");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}

	@RequestMapping(value = "foodAndBeveragesAllowance", method = RequestMethod.POST)
	public ModelAndView foodAndBeveragesAllowance() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("foodAndBeveragesAllowance");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}

	@RequestMapping(value = "refreshmentsAllowance", method = RequestMethod.POST)
	public ModelAndView refreshmentsAllowance() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("refreshmentsAllowance");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}

	@RequestMapping(value = "conveyanceAllowance", method = RequestMethod.POST)
	public ModelAndView conveyanceAllowance() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("conveyanceAllowance");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}

	@RequestMapping(value = "telephoneAllowance", method = RequestMethod.POST)
	public ModelAndView telephoneAllowance() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("telephoneAllowance");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}

	@RequestMapping(value = "tuitionFeeReimbursement", method = RequestMethod.POST)
	public ModelAndView tuitionFeeReimbursement() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("tuitionFeeReimbursement");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}

	@RequestMapping(value = "monthlyAllowance", method = RequestMethod.POST)
	public ModelAndView monthlyAllowance() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("monthlyAllowance");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}

	@RequestMapping(value = "yearlyAllowance", method = RequestMethod.POST)
	public ModelAndView yearlyAllowance() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("yearlyAllowance");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}

	@RequestMapping(value = "otherAllowance", method = RequestMethod.POST)
	public ModelAndView otherAllowance() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("otherAllowance");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}

	@RequestMapping(value = "lfcDetail", method = RequestMethod.POST)
	public ModelAndView lfcDetailUsers(HttpSession session) {
		System.out.println("Data" + (String) session.getAttribute("userID"));
		String userIdStr = (String) session.getAttribute("userID");
		System.out.println("userIdStr in menu controller in lfcDetailUsers------" + userIdStr);
		Integer userId = Integer.parseInt(userIdStr);

		int num = lfcDetail.getLFCAccess(userId);
		 
		System.out.println("Printing num value ********************************* "+num);
		ModelAndView mv = new ModelAndView();
		Map<String, String> modelMap = new HashMap<String, String>();
		modelMap.put("msg", "You have already applied for LFC");
		if (num >= 1) {
			// mv.addAllObjects(modelMap);
			mv.addObject("msg", "You have already applied for LFC");
			mv.setViewName("DashBoard");
			mv.setStatus(HttpStatus.OK);
		} else {
			mv.setViewName("lfcDetail");
			mv.setStatus(HttpStatus.OK);
		}

		return mv;

	}

	@RequestMapping(value = "leaveRequest", method = RequestMethod.POST)
	public ModelAndView leaveRequest(HttpSession session) {
		String userIdStr = (String) session.getAttribute("userID");
		System.out.println("userIdStr in menu controller leaveRequest is ----" + userIdStr);
		Integer userId = Integer.parseInt(userIdStr);
		String result = lfcDetail.checkUser(userId);
		System.out.println("result is Menucontroller :" + result);
		ModelAndView mv = new ModelAndView();
		if (result.equalsIgnoreCase("HR & ADMINISTRATION")) {
			mv.setViewName("lfcHrAdmin");
			mv.setStatus(HttpStatus.OK);
			return mv;
		} else if (result.equalsIgnoreCase("INTERNAL AUDIT")) {
			mv.setViewName("lfcInternalAuditor");
			mv.setStatus(HttpStatus.OK);
			return mv;
		} else if (result.equalsIgnoreCase("COMPANY SECRERTARIAT") || result.equalsIgnoreCase("DIRECTOR")) {
			mv.setViewName("lfcCsAdmin");
			mv.setStatus(HttpStatus.OK);
			return mv;
		}
		return mv;
	}

	@RequestMapping(value = "Report", method = RequestMethod.POST)
	public ModelAndView Report(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String userIdStr = (String) session.getAttribute("userID");

		System.out.println("userIdStr in menu controller Report ----" + userIdStr);

		Integer userId = Integer.parseInt(userIdStr);
		String result = lfcDetail.checkUser(userId);
		if (result.equalsIgnoreCase("HR & ADMINISTRATION")) {
			mv.setViewName("lfcHrReport");
			mv.setStatus(HttpStatus.OK);
			return mv;
		} else if (result.equalsIgnoreCase("INTERNAL AUDIT")) {
			mv.setViewName("lfcInternalAuditReport");
			mv.setStatus(HttpStatus.OK);
			return mv;
		} else if (result.equalsIgnoreCase("COMPANY SECRERTARIAT") || result.equalsIgnoreCase("DIRECTOR")) {
			mv.setViewName("lfcCsReport");
			mv.setStatus(HttpStatus.OK);
			return mv;
		}
		return mv;
	}
	
	@RequestMapping(value = "userReport", method = RequestMethod.POST)
	public ModelAndView userReport(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("lfcUserReport");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}
	
	@RequestMapping(value = "userSurrenderReport", method = RequestMethod.POST)
	public ModelAndView userSurrenderReport(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("lfcUserSurrenderReport");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}

	@RequestMapping(value = "AdminleaveRequest", method = RequestMethod.POST)
	public ModelAndView AdminleaveRequestPost(HttpSession session) {
		ModelAndView mv = new ModelAndView();

		String userIdStr = (String) session.getAttribute("userID");
		System.out.println("userIdStr in menu controller AdminleaveRequest ----" + userIdStr);

		Integer userId = Integer.parseInt(userIdStr);
		String result = lfcDetail.checkUser(userId);
		if (result.equalsIgnoreCase("HR & ADMINISTRATION")) {
			mv.setViewName("lfcHrAdmin_Request"); 
			mv.setStatus(HttpStatus.OK);
			return mv;
		} 
		else if (result.equalsIgnoreCase("INTERNAL AUDIT")) {
			mv.setViewName("lfcInternalAuditAdmin");
			mv.setStatus(HttpStatus.OK);
			return mv;
		} else if (result.equalsIgnoreCase("COMPANY SECRERTARIAT") || result.equalsIgnoreCase("DIRECTOR")) {
			mv.setViewName("lfcCsAdminRequest");
			mv.setStatus(HttpStatus.OK);
			return mv;
		}
		return mv;

	}
	
	@RequestMapping(value = "LFC", method = RequestMethod.POST)
	public ModelAndView lfcApplyPdf(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("LFC");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}
	
	@RequestMapping(value = "adminSurrenderRequest", method = RequestMethod.POST)
	public ModelAndView adminSurrenderRequest(HttpSession session) {
		String userIdStr = (String) session.getAttribute("userID");
		System.out.println("userIdStr in menu controller leaveRequest is ----" + userIdStr);
		Integer userId = Integer.parseInt(userIdStr);
		String result = lfcDetail.checkUser(userId);
		System.out.println("result is Menucontroller :" + result);
		ModelAndView mv = new ModelAndView();
		if (result.equalsIgnoreCase("HR & ADMINISTRATION")) {
			mv.setViewName("lfcSurrenderHrAdmin");
			mv.setStatus(HttpStatus.OK);
			return mv;
		} else if (result.equalsIgnoreCase("INTERNAL AUDIT")) {
			mv.setViewName("lfcSurrenderInternalAdmin");
			mv.setStatus(HttpStatus.OK);
			return mv;
		} else if (result.equalsIgnoreCase("COMPANY SECRERTARIAT") || result.equalsIgnoreCase("DIRECTOR")) {
			mv.setViewName("lfcSurrenderCsAdmin");
			mv.setStatus(HttpStatus.OK);
			return mv;
		}
		return mv;
	}
	
	@RequestMapping(value = "SurrenderReport", method = RequestMethod.POST)
	public ModelAndView SurrenderReport(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String userIdStr = (String) session.getAttribute("userID");

		System.out.println("userIdStr in menu controller Report ----" + userIdStr);

		Integer userId = Integer.parseInt(userIdStr);
		String result = lfcDetail.checkUser(userId);
		if (result.equalsIgnoreCase("HR & ADMINISTRATION")) {
			mv.setViewName("lfcSurrenderHrReport");
			mv.setStatus(HttpStatus.OK);
			return mv;
		} else if (result.equalsIgnoreCase("INTERNAL AUDIT")) {
			mv.setViewName("lfcSurrenderInternalAdminReport");
			mv.setStatus(HttpStatus.OK);
			return mv;
		} else if (result.equalsIgnoreCase("COMPANY SECRERTARIAT") || result.equalsIgnoreCase("DIRECTOR")) {
			mv.setViewName("lfcSurrenderCsAdminReport");
			mv.setStatus(HttpStatus.OK);
			return mv;
		}
		return mv;
	}
	
	@RequestMapping(value = "AdminSurrenderRequest", method = RequestMethod.POST)
	public ModelAndView AdminSurrenderRequestPost(HttpSession session) {
		ModelAndView mv = new ModelAndView();

		String userIdStr = (String) session.getAttribute("userID");
		System.out.println("userIdStr in menu controller AdminleaveRequest ----" + userIdStr);

		Integer userId = Integer.parseInt(userIdStr);
		String result = lfcDetail.checkUser(userId);
		if(result.equalsIgnoreCase("HR & ADMINISTRATION")) {
			mv.setViewName("lfcSurrenderHrAdminRequest");
			mv.setStatus(HttpStatus.OK);
		}
		else if (result.equalsIgnoreCase("INTERNAL AUDIT")) {
			mv.setViewName("lfcSurrenderInternalAdminadmin");
			mv.setStatus(HttpStatus.OK);
			return mv;
		} else if (result.equalsIgnoreCase("COMPANY SECRERTARIAT") || result.equalsIgnoreCase("DIRECTOR")) {
			mv.setViewName("lfcSurCsAdminRequest");
			mv.setStatus(HttpStatus.OK);
			return mv;
		}
		return mv;

	}
	
	@RequestMapping(value = "abc", method = RequestMethod.POST)
	public ModelAndView abcPdf(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("applyLfcPdf");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}
	
	@RequestMapping(value = "surrenderLfcPdfGenerate", method = RequestMethod.POST)
	public ModelAndView surrenderLfcPdfGenerate(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("surrenderLfcPdf");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}
	
	@RequestMapping(value = "juorny_Auth", method = RequestMethod.POST)
	public ModelAndView completionFormUpload(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("journyAuthentication");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}
	
	@RequestMapping(value = "Completion_form_Upload&Download", method = RequestMethod.POST)
	public ModelAndView completionFormUploadDownload(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("CompletionFormUploadDownload");
		mv.setStatus(HttpStatus.OK);
		return mv;
	}

}
