package com.emp.dao;

import java.util.List;

import com.emp.model.Hrms_History;
import com.emp.model.Hrms_Login;

/**
 * @author int6346 vivek
 */
public interface LoginDao {
	public List<Hrms_Login> userAccessWithDetail(int emply_cd);

	public void AddHistory(Hrms_History his);

	public void invalidateUser(Hrms_Login loginBean) throws Exception;
}
