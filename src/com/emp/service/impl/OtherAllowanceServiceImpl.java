package com.emp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.dao.OtherAllowanceDao;
import com.emp.modelAllowance.Eye_Checkup_All_Users;
import com.emp.service.OtherAllowanceService;

@Service
public class OtherAllowanceServiceImpl implements OtherAllowanceService {

	@Autowired
	private OtherAllowanceDao dao;

	@Override
	public List<Object> getEmpDetails(Integer userId) {
		return dao.getEmpDetails(userId);
	}

	@Override
	public String getCadre(Integer userId) {
		return dao.getCadre(userId);
	}

	@Override
	public Integer getEyeCheckUpLimit(String empCadre) {
		return dao.getEyeCheckUpLimit(empCadre);
	}

	@Override
	public int submitEyeCheckUpAllowance(Eye_Checkup_All_Users eye_checkup_user) {
		return dao.submitEyeCheckUpAllowance(eye_checkup_user);
	}

}
