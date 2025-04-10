package com.emp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.dao.YearlyAllowanceDao;
import com.emp.modelAllowance.LnAppMast_New;
import com.emp.modelAllowance.LnFest_Apply;
import com.emp.modelAllowance.Medical_Allowance_Users;
import com.emp.modelAllowance.PL_Encashment_Users;
import com.emp.modelAllowance.TuitionFee_Allowance_Users;
import com.emp.service.YearlyAllowanceService;

@Service
public class YearlyAllowanceServiceImpl implements YearlyAllowanceService {

	@Autowired
	private YearlyAllowanceDao dao;

	@Override
	public int addMedicalAllowance(Medical_Allowance_Users medical_allowance_user) {
		int flag = dao.addMedicalAllowance(medical_allowance_user);
		return flag;
	}

	@Override
	public int addFestivalAllowance(LnAppMast_New festAdvUser) {
		int flag = dao.addFestivalAllowance(festAdvUser);
		return flag;
	}

	@Override
	public void addEmplyCdLoanApp(LnFest_Apply festApplyEmp) {
		dao.addEmplyCdLoanApp(festApplyEmp);
	}

	@Override
	public List<Object> getEmpDetails(Integer userId) {
		return dao.getEmpDetails(userId);
	}

	@Override
	public String getCadre(Integer userId) {
		return dao.getCadre(userId);
	}

	@Override
	public Integer getMedicalLimit(String empCadre) {
		return dao.getMedicalLimit(empCadre);
	}

	@Override
	public int addPLAllowance(PL_Encashment_Users pl_encashment_user) {
		return dao.addPLAllowance(pl_encashment_user);
	}

	@Override
	public int addTuitionFeeAllowance(TuitionFee_Allowance_Users tuitionfee_user) {
		return dao.addTuitionFeeAllowance(tuitionfee_user);
	}

	@Override
	public List<Object> getDependant_names(Integer userId) {
		return dao.getDependant_names(userId);
	}

}
