package com.emp.interceptor;

import static com.emp.constant.EmployeeServiceConstant.ERROR_MSG;
import static com.emp.constant.EmployeeServiceConstant.skipToken;
import static com.emp.constant.EmployeeServiceConstant.staticGarbageCollection;
import static com.emp.constant.EmployeeServiceConstant.APPLICATION_NAME;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.emp.model.Hrms_Login;
import com.emp.token.CSRFToken;

/**
 * @author int6346 vivek
 */
public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) throws Exception {

		if (request.getSession().getAttribute(ERROR_MSG) != null) {
			request.getSession().removeAttribute(ERROR_MSG);
		}
		staticGarbageCollection();
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o,
			org.springframework.web.servlet.ModelAndView mav) throws Exception {
		staticGarbageCollection();
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("X-Frame-Options", "DENY");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("X-XSS-Protection", "0");
		response.setHeader("X-Content-Type-Options", "nosniff");
		String sessionToken = CSRFToken.getTokenForSession(request.getSession());
		String requestToken = CSRFToken.getTokenFromRequest(request);
		String uri = request.getRequestURI();
		String method = request.getMethod();
		List<String> inputString = skipToken();
		Hrms_Login loginBean = null;
		if (!inputString.contains(uri.replace("/" + APPLICATION_NAME + "/", ""))) {
			if (method.equalsIgnoreCase("POST")) {
				loginBean = (Hrms_Login) request.getSession(false).getAttribute("loginBean");
				if (loginBean == null) {
					request.getSession().setAttribute(ERROR_MSG, "Invalid Session, Login to continue..");
					RequestDispatcher dd = request.getRequestDispatcher("/");
					dd.include(request, response);
					return false;
				}
				if (sessionToken.equals(requestToken)) {

					request.getSession().setAttribute("CSRFToken", CSRFToken.getTokenForSession(request.getSession()));
					return true;
				} else {
					response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing CSRF value");
					request.getSession().setAttribute(ERROR_MSG, "Bad or missing CSRF value");
					RequestDispatcher dd = request.getRequestDispatcher("/Error");
					dd.include(request, response);
					return false;
				}
			}
		}

		return true;
	}

}
