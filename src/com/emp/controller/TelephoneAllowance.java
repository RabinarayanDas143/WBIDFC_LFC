package com.emp.controller;

import static com.emp.constant.EmployeeServiceConstant.ERROR_MSG;
import static com.emp.constant.EmployeeServiceConstant.isNullOrEmpty;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.emp.bean.LoginBean;
import com.emp.model.Hrms_History;
import com.emp.model.Hrms_Login;
import com.emp.service.LoginService;

@Controller
public class TelephoneAllowance {

	@Autowired
	private LoginService service;

	@RequestMapping(value = "employeeDetails", method = RequestMethod.POST)
	public String viewAllowance(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("login") @Valid LoginBean loginBean) {
		ModelAndView mv = new ModelAndView();

		List<Hrms_Login> login = service.userAccessWithDetail(Integer.parseInt(loginBean.getUserid()));

		request.getSession().setAttribute("loginBean", login.get(0));

		request.getSession().setAttribute("loginBean", loginBean);
		return "redirect:telephoneAllowance";
	}

}
