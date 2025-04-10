package com.emp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.emp.bean.LoginBean;
import com.emp.constant.ResponseBean;
import com.emp.model.Hrms_Login;
import com.emp.modelAllowance.LnAppMast_New;
import com.emp.modelAllowance.LnFest_Apply;
import com.emp.modelAllowance.Medical_Allowance_Users;
import com.emp.modelAllowance.PL_Encashment_Users;
import com.emp.modelAllowance.TuitionFee_Allowance_Users;
import com.emp.service.LoginService;
import com.emp.service.YearlyAllowanceService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class YearlyAllowance {

	@Autowired
	private YearlyAllowanceService service;

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "viewYearlyAllowance", method = RequestMethod.POST)
	public String viewYearlyAllowance(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("login") @Valid LoginBean loginBean) {
		ModelAndView mv = new ModelAndView();
		String userIdStr = (String) session.getAttribute("userID");
		Integer userId = Integer.parseInt(userIdStr);
		List<Hrms_Login> login = loginService.userAccessWithDetail(userId);
		List<Object> empdetails = service.getEmpDetails(userId);
		List<Object> empDpndtNames = service.getDependant_names(userId);
		request.getSession().setAttribute("loginBean", login.get(0));
		request.getSession().setAttribute("empdetails", empdetails.get(0));
		request.getSession().setAttribute("empDpndtNames", empDpndtNames);
		return "redirect:yearlyAllowance";

	}

	@ResponseBody
	@PostMapping(value = "getMedicalLimit")
	public String getMedicalLimit(HttpServletRequest request, HttpSession session) {

		Integer medLimitValue = null;
		try {
			String userIdStr = (String) session.getAttribute("userID");
			Integer userId = Integer.parseInt(userIdStr);
			String empCadre = service.getCadre(userId);
			medLimitValue = service.getMedicalLimit(empCadre);
			request.getSession().setAttribute("medLimitValue", medLimitValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:monthlyAllowance";
	}

	@ResponseBody
	@PostMapping(value = "addMedicalAllowance")
	public ResponseBean addMedicalAllowance(@RequestParam String userdata, HttpSession session) {

		ResponseBean bean = new ResponseBean();
		try {
			Hrms_Login loginBean = (Hrms_Login) session.getAttribute("loginBean");
			System.out.println(userdata);

			ObjectMapper objectMapper = new ObjectMapper();
			List<Medical_Allowance_Users> MedicalUserList = null;
			MedicalUserList = objectMapper.readValue(userdata, new TypeReference<List<Medical_Allowance_Users>>() {
			});

			MedicalUserList.get(0).setEmply_cd(loginBean.getEmply_cd());
			MedicalUserList.get(0).setMaker(loginBean.getEmply_cd());
			MedicalUserList.get(0).setMaker_flag("Y");

			int flag = service.addMedicalAllowance(MedicalUserList.get(0));
			if (flag > 0) {
				bean.setStatus("SUCCESS");
				bean.setMessage("DESIGNATION ADDED SUCCEFULLY");
			} else {
				bean.setMessage("FAILED");
				bean.setMessage("DESIGNATION ENTRY FAILED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			bean.setMessage("FAILED");
			bean.setStatus(e.getMessage());
		}
		return bean;
	}

	@ResponseBody
	@PostMapping(value = "addFestivalAllowance")
	public ResponseBean addFestivalAllowance(@RequestParam String userdata, HttpSession session) {

		ResponseBean bean = new ResponseBean();
		try {
			Hrms_Login loginBean = (Hrms_Login) session.getAttribute("loginBean");
			ObjectMapper objectMapper = new ObjectMapper();
			List<LnAppMast_New> festAdvList = null;
			festAdvList = objectMapper.readValue(userdata, new TypeReference<List<LnAppMast_New>>() {
			});

			festAdvList.get(0).setLnempcd(loginBean.getEmply_cd());
			festAdvList.get(0).setFstvldt(null);

			int flag = service.addFestivalAllowance(festAdvList.get(0));
			LnFest_Apply festApplyEmp = new LnFest_Apply();
			festApplyEmp.setLnEmpCd(loginBean.getEmply_cd());
			if (flag > 0) {

				service.addEmplyCdLoanApp(festApplyEmp);
				bean.setStatus("SUCCESS");
				bean.setMessage("DESIGNATION ADDED SUCCEFULLY");
			} else {
				bean.setMessage("FAILED");
				bean.setMessage("DESIGNATION ENTRY FAILED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			bean.setMessage("FAILED");
			bean.setStatus(e.getMessage());
		}
		return bean;
	}

	@ResponseBody
	@PostMapping(value = "addPLAllowance")
	public ResponseBean addPLAllowance(@RequestParam String userdata, HttpSession session) {

		ResponseBean bean = new ResponseBean();
		try {
			Hrms_Login loginBean = (Hrms_Login) session.getAttribute("loginBean");

			ObjectMapper objectMapper = new ObjectMapper();
			List<PL_Encashment_Users> plEnUserList = null;
			plEnUserList = objectMapper.readValue(userdata, new TypeReference<List<PL_Encashment_Users>>() {
			});

			plEnUserList.get(0).setEmply_cd(loginBean.getEmply_cd());
			plEnUserList.get(0).setMaker(loginBean.getEmply_cd());
			plEnUserList.get(0).setMaker_flag("Y");

			int flag = service.addPLAllowance(plEnUserList.get(0));
			if (flag > 0) {
				bean.setStatus("SUCCESS");
				bean.setMessage("DESIGNATION ADDED SUCCEFULLY");
			} else {
				bean.setMessage("FAILED");
				bean.setMessage("DESIGNATION ENTRY FAILED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			bean.setMessage("FAILED");
			bean.setStatus(e.getMessage());
		}
		return bean;
	}

	@ResponseBody
	@PostMapping(value = "addTuitionFeeAllowance")
	public ResponseBean addTuitionFeeAllowance(@RequestParam String userdata, HttpSession session) {

		ResponseBean bean = new ResponseBean();
		try {
			Hrms_Login loginBean = (Hrms_Login) session.getAttribute("loginBean");

			ObjectMapper objectMapper = new ObjectMapper();
			List<TuitionFee_Allowance_Users> feeUserList = null;
			feeUserList = objectMapper.readValue(userdata, new TypeReference<List<TuitionFee_Allowance_Users>>() {
			});

			feeUserList.get(0).setEmply_cd(loginBean.getEmply_cd());
			feeUserList.get(0).setMaker(loginBean.getEmply_cd());
			feeUserList.get(0).setMaker_flag("Y");

			int flag = service.addTuitionFeeAllowance(feeUserList.get(0));
			if (flag > 0) {
				bean.setStatus("SUCCESS");
				bean.setMessage("DESIGNATION ADDED SUCCEFULLY");
			} else {
				bean.setMessage("FAILED");
				bean.setMessage("DESIGNATION ENTRY FAILED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			bean.setMessage("FAILED");
			bean.setStatus(e.getMessage());
		}
		return bean;
	}

}
