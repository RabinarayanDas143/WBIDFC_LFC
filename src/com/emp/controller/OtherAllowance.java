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
import com.emp.modelAllowance.Eye_Checkup_All_Users;
import com.emp.service.LoginService;
import com.emp.service.OtherAllowanceService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class OtherAllowance {

	@Autowired
	private OtherAllowanceService service;

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "viewOtherAllowance", method = RequestMethod.POST)
	public String viewOtherAllowance(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("login") @Valid LoginBean loginBean) {
		ModelAndView mv = new ModelAndView();
		String userIdStr = (String) session.getAttribute("userID");
		Integer userId = Integer.parseInt(userIdStr);
		List<Hrms_Login> login = loginService.userAccessWithDetail(userId);
		List<Object> empdetails = service.getEmpDetails(userId);

		request.getSession().setAttribute("loginBean", login.get(0));
		request.getSession().setAttribute("empdetails", empdetails.get(0));

		return "redirect:yearlyAllowance";

	}

	@ResponseBody
	@PostMapping(value = "getEyeCheckUpLimit")
	public String getEyeCheckUpLimit(HttpServletRequest request, HttpSession session) {

		Integer eyeCheckupLimit = null;
		try {
			String userIdStr = (String) session.getAttribute("userID");
			Integer userId = Integer.parseInt(userIdStr);
			String empCadre = service.getCadre(userId);
			eyeCheckupLimit = service.getEyeCheckUpLimit(empCadre);

			request.getSession().setAttribute("eyeCheckupLimit", eyeCheckupLimit);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect : monthlyAllowance";
		// return "monthlyAllowance";
	}

	@ResponseBody
	@PostMapping(value = "submitEyeCheckUpAllowance")
	public ResponseBean submitEyeCheckUpAllowance(@RequestParam String userdata, HttpSession session) {

		ResponseBean bean = new ResponseBean();
		try {
			Hrms_Login loginBean = (Hrms_Login) session.getAttribute("loginBean");
			System.out.println(userdata);

			ObjectMapper objectMapper = new ObjectMapper();
			List<Eye_Checkup_All_Users> eyeCheckupUserList = null;
			eyeCheckupUserList = objectMapper.readValue(userdata, new TypeReference<List<Eye_Checkup_All_Users>>() {
			});

			eyeCheckupUserList.get(0).setEmply_cd(loginBean.getEmply_cd());
			eyeCheckupUserList.get(0).setMaker(loginBean.getEmply_cd());
			eyeCheckupUserList.get(0).setMaker_flag("Y");

			int flag = service.submitEyeCheckUpAllowance(eyeCheckupUserList.get(0));
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
