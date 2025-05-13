package com.emp.service;

import java.util.Date;
import java.util.List;

import com.emp.model.Hrms_Login;
import com.emp.modelAllowance.Conveyance_Users;
import com.emp.modelAllowance.FoodBeverages_Users;
import com.emp.modelAllowance.Newspaper_Allowance_Limit;
import com.emp.modelAllowance.Newspaper_Users;
import com.emp.modelAllowance.Refreshments_Users;
import com.emp.modelAllowance.Telephone_Users;

public interface MonthlyAllowanceService {

	int addNewsPaperAllowance(Newspaper_Users newspaper_user);

	int addFoodAllowance(FoodBeverages_Users foodBeverages_user);

	int addRefreshmentAllowance(Refreshments_Users refreshments_user);

	int addConveyanceAllowance(Conveyance_Users conveyance_Users);

	int addTelephoneAllowance(Telephone_Users telephone_Users);

	List<Object> getEmployeeDetails(Integer userId);

	String getCadre(Integer userId);

	String getDesignation(Integer userId);

	Integer getNewspaperLimit(String empCadre);

	Integer getFoodBevLimit(String empCadre);

	Integer getRefreshmentsLimit(String empCadre);

	Integer getConveyanceLimit(String empCadre);

	Integer getTelephoneLimit(String empCadre);

	Integer getnewspaperAppCount(Date applied_year_month, Integer emply_cd);

}
