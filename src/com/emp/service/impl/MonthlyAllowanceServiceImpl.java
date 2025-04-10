package com.emp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emp.dao.MonthlyAllowanceDao;
import com.emp.modelAllowance.Conveyance_Users;
import com.emp.modelAllowance.FoodBeverages_Users;
import com.emp.modelAllowance.Newspaper_Allowance_Limit;
import com.emp.modelAllowance.Newspaper_Users;
import com.emp.modelAllowance.Refreshments_Users;
import com.emp.modelAllowance.Telephone_Users;
import com.emp.service.MonthlyAllowanceService;

@Service
public class MonthlyAllowanceServiceImpl implements MonthlyAllowanceService {

	@Autowired
	private MonthlyAllowanceDao dao;

	@Override
	public int addNewsPaperAllowance(Newspaper_Users newspaper_user) {
		int flag = dao.addNewsPaperAllowance(newspaper_user);
		return flag;
	}

	@Override
	public int addFoodAllowance(FoodBeverages_Users foodBeverages_user) {
		int flag = dao.addFoodAllowance(foodBeverages_user);
		return flag;
	}

	@Override
	public int addRefreshmentAllowance(Refreshments_Users refreshments_user) {
		int flag = dao.addRefreshmentAllowance(refreshments_user);
		return flag;
	}

	@Override
	public int addConveyanceAllowance(Conveyance_Users conveyance_user) {
		int flag = dao.addConveyanceAllowance(conveyance_user);
		return flag;
	}

	@Override
	public int addTelephoneAllowance(Telephone_Users telephone_user) {
		int flag = dao.addTelephoneAllowance(telephone_user);
		return flag;
	}

	@Override
	public List<Object> getEmployeeDetails(Integer userId) {
		return dao.getEmployeeDetails(userId);
	}

	@Override
	public String getCadre(Integer userId) {
		return dao.getCadre(userId);
	}

	@Override
	public Integer getNewspaperLimit(String empCadre) {
		return dao.getNewspaperLimit(empCadre);
	}

	@Override
	public Integer getFoodBevLimit(String empCadre) {
		return dao.getFoodBevLimit(empCadre);
	}

	@Override
	public Integer getRefreshmentsLimit(String empCadre) {
		return dao.getRefreshmentsLimit(empCadre);
	}

	@Override
	public Integer getConveyanceLimit(String empCadre) {
		return dao.getConveyanceLimit(empCadre);
	}

	@Override
	public Integer getTelephoneLimit(String empCadre) {
		return dao.getTelephoneLimit(empCadre);
	}

	@Override
	public String getDesignation(Integer userId) {
		return dao.getDesignation(userId);
	}

	@Override
	public Integer getnewspaperAppCount(Date applied_year_month, Integer emply_cd) {
		return dao.getnewspaperAppCount(applied_year_month, emply_cd);
	}

}
