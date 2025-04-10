package com.emp.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emp.dao.EmpDetailDao;
import com.emp.model.hrms_Type;

/**
 * @author int6346 vivek
 */
@Transactional
@Repository
public class EmpDetailDaoImpl implements EmpDetailDao {
	@Autowired
	@Qualifier(value = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public List<hrms_Type> getType() {
		return sessionFactory.getCurrentSession().createQuery(" from hrms_Type").getResultList();

	}

}
