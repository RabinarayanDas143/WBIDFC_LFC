package com.emp.controller;

import static com.emp.constant.EmployeeServiceConstant.isNullOrEmpty;
import static com.emp.constant.EmployeeServiceConstant.staticGarbageCollection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emp.constant.ResponseBean;
import com.emp.model.hrms_Type;
import com.emp.service.EmpDetailService;

/**
 * @author int6346 vivek
 */
@Controller
public class EmpDetailController {

	@Autowired
	private EmpDetailService service;

	public EmpDetailController(EmpDetailService service) {
		super();
		this.service = service;
	}

	@ResponseBody
	@PostMapping(value = "type-view")
	public ResponseBean viewRegistrationUser() {
		ResponseBean bean = new ResponseBean();
		List<hrms_Type> detail = service.getType();
		System.out.println(detail.size());
		if (!isNullOrEmpty(detail)) {
			bean.setBody(detail);
			bean.setStatus("SUCCESS");
			bean.setMessage("HRMS TYPE DATA FOUND");
		} else {
			bean.setStatus("FAILED");
			bean.setMessage("HRMS TYPE DATA NOT FOUND");
		}
		staticGarbageCollection();
		return bean;
	}

}
