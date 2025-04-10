package com.emp.dao;

import java.util.List;

import com.emp.modelAllowance.LnAppMast_New;
import com.emp.modelAllowance.LnFest_Apply;
import com.emp.modelAllowance.Medical_Allowance_Users;
import com.emp.modelAllowance.PL_Encashment_Users;
import com.emp.modelAllowance.TuitionFee_Allowance_Users;

public interface YearlyAllowanceDao {

	int addMedicalAllowance(Medical_Allowance_Users medical_allowance_user);

	int addFestivalAllowance(LnAppMast_New festAdvUser);

	void addEmplyCdLoanApp(LnFest_Apply festApplyEmp);

	List<Object> getEmpDetails(Integer userId);

	String getCadre(Integer userId);

	Integer getMedicalLimit(String empCadre);

	int addPLAllowance(PL_Encashment_Users pl_encashment_user);

	int addTuitionFeeAllowance(TuitionFee_Allowance_Users tuitionfee_user);

	List<Object> getDependant_names(Integer userId);

}
