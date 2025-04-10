package com.emp.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emp.dao.OtherAllowanceDao;
import com.emp.modelAllowance.Eye_Checkup_All_Users;

@Repository
@Transactional
public class OtherAllowanceDaoImpl implements OtherAllowanceDao {

	@Autowired
	@Qualifier(value = "sessionFactory2")
	private SessionFactory sessionFactory2;

	@Override
	public List<Object> getEmpDetails(Integer emply_cd) {
		List<Object> empDetails = null;
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();
		try {

			String sql = "select * from new_hrms.emp_details where emply_cd=:emply_cd";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("emply_cd", emply_cd);

			empDetails = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		session.close();
		return empDetails;
	}

	@Override
	public String getCadre(Integer emply_cd) {
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();
		List<Object> data = null;
		String cadre = null;
		try {
			String sql = "select cadre from hrms_payroll.cadre_detail where emply_cd=:emply_cd";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("emply_cd", emply_cd);

			data = query.list();
			cadre = (String) data.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		session.close();

		return cadre;
	}

	@Override
	public Integer getEyeCheckUpLimit(String empCadre) {
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();
		List<Object> data = null;
		Integer emplimit = null;
		try {

			if (empCadre.contains("OAS")) {
				String sql = "select oas_limit from hrms_allowance.eye_checkup_limit";
				SQLQuery query = session.createSQLQuery(sql);
				data = query.list();
				emplimit = (Integer) data.get(0);
			} else if (empCadre.contains("OAT")) {
				String sql = "select oat_limit from hrms_allowance.eye_checkup_limit";
				SQLQuery query = session.createSQLQuery(sql);
				data = query.list();
				emplimit = (Integer) data.get(0);
			} else if (empCadre.contains("Officer")) {
				String sql = "select officer_limit from hrms_allowance.eye_checkup_limit";
				SQLQuery query = session.createSQLQuery(sql);
				data = query.list();
				emplimit = (Integer) data.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		session.close();

		return emplimit;
	}

	@Override
	public int submitEyeCheckUpAllowance(Eye_Checkup_All_Users eye_checkup_user) {
		int flag = 0;
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();

		try {
			flag = (int) session.save(eye_checkup_user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		session.close();
		return flag;
	}

}
