package com.emp.dao;

import java.util.Date;
import java.util.List;

import com.emp.modelAllowance.Conveyance_Users;
import com.emp.modelAllowance.FoodBeverages_Users;
import com.emp.modelAllowance.Newspaper_Allowance_Limit;
import com.emp.modelAllowance.Newspaper_Users;
import com.emp.modelAllowance.Refreshments_Users;
import com.emp.modelAllowance.Telephone_Users;

public interface MonthlyAllowanceDao {

	public int addNewsPaperAllowance(Newspaper_Users newspaper_user);

	public int addFoodAllowance(FoodBeverages_Users foodBeverages_user);

	public int addRefreshmentAllowance(Refreshments_Users refreshments_user);

	public int addConveyanceAllowance(Conveyance_Users conveyance_user);

	public int addTelephoneAllowance(Telephone_Users telephone_user);

	public List<Object> getEmployeeDetails(Integer userId);

	public String getCadre(Integer userId);

	public String getDesignation(Integer userId);

	public Integer getNewspaperLimit(String empCadre);

	public Integer getFoodBevLimit(String empCadre);

	public Integer getRefreshmentsLimit(String empCadre);

	public Integer getConveyanceLimit(String empCadre);

	public Integer getTelephoneLimit(String empCadre);

	public Integer getnewspaperAppCount(Date applied_year_month, Integer emply_cd);

}
