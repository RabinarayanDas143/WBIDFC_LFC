package com.emp.service;

import java.util.List;

import com.emp.modelAllowance.Eye_Checkup_All_Users;

public interface OtherAllowanceService {

	List<Object> getEmpDetails(Integer userId);

	String getCadre(Integer userId);

	Integer getEyeCheckUpLimit(String empCadre);

	int submitEyeCheckUpAllowance(Eye_Checkup_All_Users eye_checkup_user);

}
