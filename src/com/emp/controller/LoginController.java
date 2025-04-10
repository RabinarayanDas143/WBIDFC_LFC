package com.emp.controller;

import static com.emp.constant.EmployeeServiceConstant.ERROR_MSG;
import static com.emp.constant.EmployeeServiceConstant.SUCCESS_MSG;
import static com.emp.constant.EmployeeServiceConstant.isNullOrEmpty;
//import static com.emp.constant.EmployeeServiceConstant.lOGOUT_URL;
import static com.emp.constant.EmployeeServiceConstant.staticGarbageCollection;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.emp.bean.LoginBean;
import com.emp.constant.AesUtil;
import com.emp.model.Hrms_History;
import com.emp.model.Hrms_Login;
import com.emp.service.LfcDetailService;
import com.emp.service.LoginService;
import com.emp.token.CSRFToken;

/**
 * @author int6346 vivek
 */
@Controller
public class LoginController {
	//public static final String lOGOUT_URL = "http://localhost:7077/iHRMS";
	//public static final String lOGOUT_URL = "http://192.168.1.234:8080/iHRMS/user-main-dashboard"; 
	public static final String lOGOUT_URL = "http://192.168.1.236:8080/iHRMS/user-main-dashboard";
	@Autowired
	private LoginService service;

	@Autowired
	private LfcDetailService lfcDetail;

	@RequestMapping(value = "Login", method = RequestMethod.POST)
	public ModelAndView postLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("login") @Valid LoginBean loginBean, Model model) {

		System.out.println("test");
		List<Hrms_Login> login = service.userAccessWithDetail(Integer.parseInt(loginBean.getUserid()));
		
		session.setAttribute("userID", loginBean.getUserid());
		String userid = loginBean.getUserid();
		Integer userId = Integer.parseInt(userid);
		String user = null;
		user = lfcDetail.userFilter(userId);
		
		
        String userName = lfcDetail.getUserName(userId);
        String Designation = lfcDetail.getUserDesignation(userId);
		session.setAttribute("role", user);
		session.setAttribute("userName", userName);
		session.setAttribute("Designation", Designation);
		//session.setAttribute("employeCode", userId);
		System.out.println("user is --------------------:" + user);
		model.addAttribute("user1", user);
		System.out.println("userid is ----:" + userid);

		ModelAndView mv = new ModelAndView();
		AesUtil aesUtil = new AesUtil(Integer.parseInt(request.getParameter("keySize")),
				Integer.parseInt(request.getParameter("iterationCount")));
		loginBean.setPassword(aesUtil.decrypt(request.getParameter("salt"), request.getParameter("iv"),
				request.getParameter("passphrase"), request.getParameter("ciphertext")));
		try {
			// .validateADLogin(loginBean, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		List<Hrms_Login> login = service.userAccessWithDetail(Integer
//				.parseInt(loginBean.getUserid()));
//		session.setAttribute("userID", loginBean.getUserid());
//		String userid=loginBean.getUserid();
//		System.out.println("userid is ----:"+userid);

		if (!isNullOrEmpty(login)) {

			Hrms_History addhistory = new Hrms_History(Integer.parseInt(loginBean.getUserid()), new Date(), null,
					request.getRemoteAddr(), request.getSession().getId());
			service.AddHistory(addhistory);
			request.getSession().setAttribute("loginBean", login.get(0));
			request.getSession().setAttribute("CSRFToken", CSRFToken.getTokenForSession(request.getSession()));
			mv.setViewName("DashBoard");
		} else {
			mv.setViewName("Login");
			mv.addObject(ERROR_MSG, "<br/>kindly contact admin to access the system.<br/>");
		}
		staticGarbageCollection();
		return mv;
	}

	@RequestMapping(value = "AccessLFC", method = RequestMethod.POST)
	public ModelAndView loadLoginAccess(HttpServletRequest request, HttpSession session, Model model) {
		ModelAndView mv = new ModelAndView();

		String token = request.getParameter("CSRFToken");
		String sessinid = request.getParameter("CURRENTSESSION");
		String loginsession = request.getParameter("LOGINSESSION");
		System.out.println("sessinid is " + sessinid);
		List<Hrms_Login> login = service.userAccessWithDetail(Integer.parseInt(loginsession));
		session.setAttribute("userID", loginsession);
		String userid = loginsession;
		Integer userId = Integer.parseInt(loginsession);
		String user = null;
		user = lfcDetail.userFilter(userId);
		String userName = lfcDetail.getUserName(userId);
        String Designation = lfcDetail.getUserDesignation(userId);
		session.setAttribute("userName", userName);
		session.setAttribute("Designation", Designation);
		session.setAttribute("role", user);
		System.out.println("user is --------------------:" + user);
		model.addAttribute("user1", user);
		System.out.println("userid is ----:" + userid);
		session.setAttribute("userId", userId);

		try {
			// .validateADLogin(loginBean, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		List<Hrms_Login> login = service.userAccessWithDetail(Integer
//				.parseInt(loginBean.getUserid()));
//		session.setAttribute("userID", loginBean.getUserid());
//		String userid=loginBean.getUserid();
//		System.out.println("userid is ----:"+userid);

		if (!isNullOrEmpty(login)) {

			Hrms_History addhistory = new Hrms_History(Integer.parseInt(loginsession), new Date(), null,
					request.getRemoteAddr(), request.getSession().getId());
			service.AddHistory(addhistory);
			request.getSession().setAttribute("loginBean", login.get(0));
			session.setAttribute("userID", loginsession);
			request.getSession().setAttribute("CSRFToken", CSRFToken.getTokenForSession(request.getSession()));
			mv.setViewName("DashBoard");
		} else {
			mv.setViewName("Login");
			mv.addObject(ERROR_MSG, "<br/>kindly contact admin to access the system.<br/>");
		}

		/*
		 * List<Hrms_Login> login =
		 * service.userAccessWithDetail(Integer.parseInt(loginsession)); if
		 * (!isNullOrEmpty(login)) { Hrms_History addhistory = new
		 * Hrms_History(Integer.parseInt(loginsession), new Date(), null,
		 * request.getRemoteAddr(), sessinid); service.AddHistory(addhistory);
		 * request.getSession().setAttribute("loginBean", login.get(0));
		 * request.setAttribute("userID", login.get(0).getEmply_cd());
		 * request.getSession().setAttribute("CSRFToken",
		 * CSRFToken.getTokenForSession(request.getSession()));
		 * mv.setViewName("DashBoard"); } else { mv.setViewName("Login");
		 * mv.addObject(ERROR_MSG,
		 * "<br/>kindly contact admin to access the system.<br/>"); }
		 */

		staticGarbageCollection();
		return mv;
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public ModelAndView logout(HttpSession session, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("login", new LoginBean());
		mv.setViewName("Login");
		try {
			Hrms_Login loginBean = (Hrms_Login) session.getAttribute("loginBean");
			service.invalidateUser(loginBean);
			session.invalidate();
			mv.addObject(SUCCESS_MSG, "Logged Out Successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject(ERROR_MSG, "Error Logging Out.");
		}
		staticGarbageCollection();
		return mv;
	}

	@RequestMapping(value = "Logout", method = RequestMethod.POST)
	public String logoutOnus(Hrms_Login loginBean, BindingResult result, Model model, HttpSession session,
			HttpServletRequest request) {
		try {
			loginBean = (Hrms_Login) session.getAttribute("loginBean");
			//service.invalidateUser(loginBean);
			//session.invalidate();

		} catch (Exception e) {
			model.addAttribute("login", loginBean);
			model.addAttribute(ERROR_MSG, "Error Logging Out.");

			return "redirect:" + lOGOUT_URL;
		}
		return "redirect:" + lOGOUT_URL;
	}
}
