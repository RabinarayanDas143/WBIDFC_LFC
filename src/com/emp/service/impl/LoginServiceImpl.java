package com.emp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.dao.LoginDao;
import com.emp.model.Hrms_History;
import com.emp.model.Hrms_Login;
import com.emp.service.LoginService;

/**
 * @author int6346 vivek
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao dao;

	@Override
	public void AddHistory(Hrms_History his) {
		dao.AddHistory(his);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.emp.service.LoginService#userAccessWithDetail(int)
	 */
	@Override
	public List<Hrms_Login> userAccessWithDetail(int emply_cd) {
		// TODO Auto-generated method stub
		return dao.userAccessWithDetail(emply_cd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.emp.service.LoginService#invalidateUser(com.emp.model.Hrms_Login)
	 */
	@Override
	public void invalidateUser(Hrms_Login loginBean) throws Exception {
		dao.invalidateUser(loginBean);

	}

}
