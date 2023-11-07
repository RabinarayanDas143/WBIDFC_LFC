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

import com.emp.dao.YearlyAllowanceDao;
import com.emp.modelAllowance.LnAppMast_New;
import com.emp.modelAllowance.LnFest_Apply;
import com.emp.modelAllowance.Medical_Allowance_Users;
import com.emp.modelAllowance.PL_Encashment_Users;
import com.emp.modelAllowance.TuitionFee_Allowance_Users;

@Repository
@Transactional
public class YearlyAllowanceDaoImpl implements YearlyAllowanceDao {

	@Autowired
	@Qualifier(value = "sessionFactory2")
	private SessionFactory sessionFactory2;

	@Override
	public int addMedicalAllowance(Medical_Allowance_Users medical_allowance_user) {
		int flag = 0;
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();

		try {
			flag = (int) session.save(medical_allowance_user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		session.close();
		return flag;
	}

	@Override
	public int addFestivalAllowance(LnAppMast_New festAdvUser) {
		int flag = 0;
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();

		try {
			flag = (int) session.save(festAdvUser);

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		session.close();
		return flag;
	}

	@Override
	public void addEmplyCdLoanApp(LnFest_Apply festApplyEmp) {
		int flag = 0;
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();

		try {
			flag = (int) session.save(festApplyEmp);

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		session.close();

	}

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
	public Integer getMedicalLimit(String empCadre) {
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();
		List<Object> data = null;
		Integer emplimit = null;
		try {

			if (empCadre.contains("OAS")) {
				String sql = "select oas_limit from hrms_allowance.medical_limit";
				SQLQuery query = session.createSQLQuery(sql);
				data = query.list();
				emplimit = (Integer) data.get(0);
			} else if (empCadre.contains("OAT")) {
				String sql = "select oat_limit from hrms_allowance.medical_limit";
				SQLQuery query = session.createSQLQuery(sql);
				data = query.list();
				emplimit = (Integer) data.get(0);
			} else if (empCadre.contains("Officer")) {
				String sql = "select officer_limit from hrms_allowance.medical_limit";
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
	public int addPLAllowance(PL_Encashment_Users pl_encashment_user) {
		int flag = 0;
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();

		try {
			flag = (int) session.save(pl_encashment_user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		session.close();
		return flag;
	}

	@Override
	public int addTuitionFeeAllowance(TuitionFee_Allowance_Users tuitionfee_user) {
		int flag = 0;
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();

		try {
			flag = (int) session.save(tuitionfee_user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		session.close();
		return flag;
	}

	@Override
	public List<Object> getDependant_names(Integer emply_cd) {
		List<Object> empDpndtNames = null;
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();
		try {

			String sql = "select depnd_name from new_hrms.dependent_detail where emply_cd=:emply_cd";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("emply_cd", emply_cd);

			empDpndtNames = query.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		session.close();
		return empDpndtNames;
	}

}
