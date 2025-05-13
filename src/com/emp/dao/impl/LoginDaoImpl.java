package com.emp.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emp.dao.LoginDao;
import com.emp.model.Hrms_History;
import com.emp.model.Hrms_Login;

/**
 * @author int6346 vivek
 */
@Repository
@Transactional
public class LoginDaoImpl implements LoginDao {
	@Autowired
	@Qualifier(value = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public void AddHistory(Hrms_History his) {
		sessionFactory.getCurrentSession().persist(his);
	}

	@Override
	public List<Hrms_Login> userAccessWithDetail(int emply_cd) {
		return sessionFactory.getCurrentSession().createQuery(
				"from Hrms_Login a  where a.emply_cd=:emply_cd and DATE_FORMAT(sysdate(),'%Y-%m-%d') between dateStart and dateEnd",
				Hrms_Login.class).setParameter("emply_cd", emply_cd).getResultList();
		// Hrms_Login
		// hrms_wbidfc.hrms_login
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.emp.dao.LoginDao#invalidateUser(com.emp.model.Hrms_Login)
	 */
	@Override
	public void invalidateUser(Hrms_Login loginBean) throws Exception {
		int data = sessionFactory.getCurrentSession().createQuery(
				"update  Hrms_History set outtime =DATE_FORMAT(sysdate(),'%Y-%m-%d') where emply_cd=:emply_cd  and outtime is null ")
				.setParameter("emply_cd", loginBean.getEmply_cd()).executeUpdate();

		data = sessionFactory.getCurrentSession().createSQLQuery(
				"update  hrms_history set out_time =DATE_FORMAT(sysdate(),'%Y-%m-%d') where emply_cd=:emply_cd  and out_time is null ")
				.setParameter("emply_cd", loginBean.getEmply_cd()).executeUpdate();

	}

}
