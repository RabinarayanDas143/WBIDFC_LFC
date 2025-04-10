package com.emp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import com.emp.model.Hrms_Login;
import com.emp.modelAllowance.Conveyance_Users;
import com.emp.modelAllowance.FoodBeverages_Users;
import com.emp.modelAllowance.Newspaper_Allowance_Limit;
import com.emp.modelAllowance.Newspaper_Users;
import com.emp.modelAllowance.Refreshments_Users;
import com.emp.modelAllowance.Telephone_Users;
import com.emp.service.LoginService;
import com.emp.service.MonthlyAllowanceService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MonthlyAllowance {

	@Autowired
	private MonthlyAllowanceService service;

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "viewMonthlyAllowance", method = RequestMethod.POST)
	public String viewAllowance(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("login") @Valid LoginBean loginBean, Model model) {

		String userIdStr = (String) session.getAttribute("userID");
		Integer userId = Integer.parseInt(userIdStr);
		List<Hrms_Login> login = loginService.userAccessWithDetail(userId);
		List<Object> empDetails = service.getEmployeeDetails(userId);

		// List<Object> empdetails = service.getEmpDetails(userId);

		String empCadre = service.getCadre(userId);
		String empDesignation = service.getDesignation(userId);

		request.getSession().setAttribute("loginBean", login.get(0));
		request.getSession().setAttribute("empDetails", empDetails.get(0));

		/*
		 * request.getSession().setAttribute("empCadre", empCadre);
		 * request.getSession().setAttribute("empDesignation", empDesignation);
		 */

		/*
		 * model.addAttribute("empCadre", empCadre);
		 * model.addAttribute("empDesignation", empDesignation);
		 */

		return "redirect:monthlyAllowance";

	}

	@ResponseBody
	@PostMapping(value = "getNewspaperLimit")
	public String getNewspaperLimit(HttpServletRequest request, HttpSession session) {

		Integer limitvalue = null;
		try {
			String userIdStr = (String) session.getAttribute("userID");
			Integer userId = Integer.parseInt(userIdStr);
			String empCadre = service.getCadre(userId);
			limitvalue = service.getNewspaperLimit(empCadre);

			request.getSession().setAttribute("limitvalue", limitvalue);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:monthlyAllowance";
	}

	@ResponseBody
	@PostMapping(value = "addNewspaperAllowance")
	public ResponseBean addNewsPaperAllowance(@RequestParam String userdata, HttpSession session) {

		ResponseBean bean = new ResponseBean();
		try {
			Hrms_Login loginBean = (Hrms_Login) session.getAttribute("loginBean");

			ObjectMapper objectMapper = new ObjectMapper();
			List<Newspaper_Users> newspaperUserList = null;
			newspaperUserList = objectMapper.readValue(userdata, new TypeReference<List<Newspaper_Users>>() {
			});

			newspaperUserList.get(0).setEmply_cd(loginBean.getEmply_cd());
			newspaperUserList.get(0).setMaker(loginBean.getEmply_cd());
			newspaperUserList.get(0).setMaker_flag("Y");

			Integer a = service.getnewspaperAppCount(newspaperUserList.get(0).getApplied_year_month(),
					loginBean.getEmply_cd());

			int flag = service.addNewsPaperAllowance(newspaperUserList.get(0));
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
	@PostMapping(value = "getFoodBevLimit")
	public String getFoodBevLimit(HttpServletRequest request, HttpSession session) {

		Integer foodLimitValue = null;
		try {
			String userIdStr = (String) session.getAttribute("userID");
			Integer userId = Integer.parseInt(userIdStr);
			String empCadre = service.getCadre(userId);
			foodLimitValue = service.getFoodBevLimit(empCadre);

			request.getSession().setAttribute("foodLimitValue", foodLimitValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect : monthlyAllowance";
		// return "monthlyAllowance";
	}

	@ResponseBody
	@PostMapping(value = "addFoodAllowance")
	public ResponseBean addFoodAllowance(@RequestParam String userdata, HttpSession session) {

		ResponseBean bean = new ResponseBean();
		try {
			Hrms_Login loginBean = (Hrms_Login) session.getAttribute("loginBean");
			System.out.println(userdata);

			ObjectMapper objectMapper = new ObjectMapper();
			List<FoodBeverages_Users> foodBeveragesUserList = null;
			foodBeveragesUserList = objectMapper.readValue(userdata, new TypeReference<List<FoodBeverages_Users>>() {
			});

			foodBeveragesUserList.get(0).setEmply_cd(loginBean.getEmply_cd());
			foodBeveragesUserList.get(0).setMaker(loginBean.getEmply_cd());
			foodBeveragesUserList.get(0).setMaker_flag("Y");

			int flag = service.addFoodAllowance(foodBeveragesUserList.get(0));
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
	@PostMapping(value = "getRefreshmentsLimit")
	public String getRefreshmentsLimit(HttpServletRequest request, HttpSession session) {

		Integer refLimitValue = null;
		try {
			String userIdStr = (String) session.getAttribute("userID");
			Integer userId = Integer.parseInt(userIdStr);
			String empCadre = service.getCadre(userId);
			refLimitValue = service.getRefreshmentsLimit(empCadre);

			request.getSession().setAttribute("refLimitValue", refLimitValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:monthlyAllowance";
	}

	@ResponseBody
	@PostMapping(value = "addRefreshmentAllowance")
	public ResponseBean addRefreshmentAllowance(@RequestParam String userdata, HttpSession session) {

		ResponseBean bean = new ResponseBean();
		try {
			Hrms_Login loginBean = (Hrms_Login) session.getAttribute("loginBean");
			System.out.println(userdata);

			ObjectMapper objectMapper = new ObjectMapper();
			List<Refreshments_Users> refreshmentsUserList = null;
			refreshmentsUserList = objectMapper.readValue(userdata, new TypeReference<List<Refreshments_Users>>() {
			});

			refreshmentsUserList.get(0).setEmply_cd(loginBean.getEmply_cd());
			refreshmentsUserList.get(0).setMaker(loginBean.getEmply_cd());
			refreshmentsUserList.get(0).setMaker_flag("Y");

			int flag = service.addRefreshmentAllowance(refreshmentsUserList.get(0));
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
	@PostMapping(value = "getConveyanceLimit")
	public String getConveyanceLimit(HttpServletRequest request, HttpSession session) {

		Integer convLimitValue = null;
		try {
			String userIdStr = (String) session.getAttribute("userID");
			Integer userId = Integer.parseInt(userIdStr);
			String empCadre = service.getCadre(userId);
			convLimitValue = service.getConveyanceLimit(empCadre);

			request.getSession().setAttribute("convLimitValue", convLimitValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:monthlyAllowance";
	}

	@ResponseBody
	@PostMapping(value = "addConveyanceAllowance")
	public ResponseBean addConveyanceAllowance(@RequestParam String userdata, HttpSession session) {

		ResponseBean bean = new ResponseBean();
		try {
			Hrms_Login loginBean = (Hrms_Login) session.getAttribute("loginBean");
			System.out.println(userdata);

			ObjectMapper objectMapper = new ObjectMapper();
			List<Conveyance_Users> conveyanceUserList = null;
			conveyanceUserList = objectMapper.readValue(userdata, new TypeReference<List<Conveyance_Users>>() {
			});

			conveyanceUserList.get(0).setEmply_cd(loginBean.getEmply_cd());
			conveyanceUserList.get(0).setMaker(loginBean.getEmply_cd());
			conveyanceUserList.get(0).setMaker_flag("Y");

			int flag = service.addConveyanceAllowance(conveyanceUserList.get(0));
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
	@PostMapping(value = "getTelephoneLimit")
	public String getTelephoneLimit(HttpServletRequest request, HttpSession session) {

		Integer teleLimitValue = null;
		try {
			String userIdStr = (String) session.getAttribute("userID");
			Integer userId = Integer.parseInt(userIdStr);
			String empCadre = service.getCadre(userId);
			teleLimitValue = service.getTelephoneLimit(empCadre);

			request.getSession().setAttribute("teleLimitValue", teleLimitValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:monthlyAllowance";
	}

	@ResponseBody
	@PostMapping(value = "addTelephoneAllowance")
	public ResponseBean addTelephoneAllowance(@RequestParam String userdata, HttpSession session) {

		ResponseBean bean = new ResponseBean();
		try {
			Hrms_Login loginBean = (Hrms_Login) session.getAttribute("loginBean");
			System.out.println(userdata);

			ObjectMapper objectMapper = new ObjectMapper();
			List<Telephone_Users> telephoneUserList = null;
			telephoneUserList = objectMapper.readValue(userdata, new TypeReference<List<Telephone_Users>>() {
			});

			telephoneUserList.get(0).setEmply_cd(loginBean.getEmply_cd());
			telephoneUserList.get(0).setMaker(loginBean.getEmply_cd());
			telephoneUserList.get(0).setMaker_flag("Y");

			int flag = service.addTelephoneAllowance(telephoneUserList.get(0));
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
