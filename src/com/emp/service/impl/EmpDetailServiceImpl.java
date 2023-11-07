package com.emp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.dao.EmpDetailDao;
import com.emp.model.hrms_Type;
import com.emp.service.EmpDetailService;

/**
 * @author int6346 vivek
 */
@Service
public class EmpDetailServiceImpl implements EmpDetailService {

	@Autowired
	private EmpDetailDao dao;

	@Override
	public List<hrms_Type> getType() {
		// TODO Auto-generated method stub
		return dao.getType();
	}

}
