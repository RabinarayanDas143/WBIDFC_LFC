package com.emp.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emp.dao.MonthlyAllowanceDao;
import com.emp.modelAllowance.Conveyance_Users;
import com.emp.modelAllowance.FoodBeverages_Users;
import com.emp.modelAllowance.Newspaper_Allowance_Limit;
import com.emp.modelAllowance.Newspaper_Users;
import com.emp.modelAllowance.Refreshments_Users;
import com.emp.modelAllowance.Telephone_Users;

@Repository
@Transactional
public class MonthlyAllowanceDaoImpl implements MonthlyAllowanceDao {

	@Autowired
	@Qualifier(value = "sessionFactory2")
	private SessionFactory sessionFactory2;

	@Override
	public List<Object> getEmployeeDetails(Integer emply_cd) {
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
	public int addNewsPaperAllowance(Newspaper_Users newspaper_user) {
		int flag = 0;
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();

		try {
			flag = (int) session.save(newspaper_user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		session.close();
		return flag;
	}

	@Override
	public int addFoodAllowance(FoodBeverages_Users foodBeverages_user) {
		int flag = 0;
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();

		try {
			flag = (int) session.save(foodBeverages_user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		session.close();
		return flag;
	}

	@Override
	public int addRefreshmentAllowance(Refreshments_Users refreshments_user) {
		int flag = 0;
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();

		try {
			flag = (int) session.save(refreshments_user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		session.close();
		return flag;
	}

	@Override
	public int addConveyanceAllowance(Conveyance_Users conveyance_user) {
		int flag = 0;
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();

		try {
			flag = (int) session.save(conveyance_user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		session.close();
		return flag;
	}

	@Override
	public int addTelephoneAllowance(Telephone_Users telephone_user) {
		int flag = 0;
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();

		try {
			flag = (int) session.save(telephone_user);

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		session.close();
		return flag;
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
	public String getDesignation(Integer emply_cd) {
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();
		List<Object> data = null;
		String cadre = null;
		try {
			String sql = "select designation from hrms_payroll.designation_detail where emply_cd=:emply_cd";
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
	public Integer getNewspaperLimit(String empCadre) {
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();
		List<Object> data = null;
		Integer emplimit = null;
		try {

			if (empCadre.contains("OAS")) {
				String sql = "select oas_limit from hrms_allowance.newspaper_limit";
				SQLQuery query = session.createSQLQuery(sql);
				data = query.list();
				emplimit = (Integer) data.get(0);
			} else if (empCadre.contains("OAT")) {
				String sql = "select oat_limit from hrms_allowance.newspaper_limit";
				SQLQuery query = session.createSQLQuery(sql);
				data = query.list();
				emplimit = (Integer) data.get(0);
			} else if (empCadre.contains("Officer")) {
				String sql = "select officer_limit from hrms_allowance.newspaper_limit";
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
	public Integer getFoodBevLimit(String empCadre) {
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();
		List<Object> data = null;
		Integer emplimit = null;
		try {

			if (empCadre.contains("OAS")) {
				String sql = "select oas_limit from hrms_allowance.foodbeverages_limit";
				SQLQuery query = session.createSQLQuery(sql);
				data = query.list();
				emplimit = (Integer) data.get(0);
			} else if (empCadre.contains("OAT")) {
				String sql = "select oat_limit from hrms_allowance.foodbeverages_limit";
				SQLQuery query = session.createSQLQuery(sql);
				data = query.list();
				emplimit = (Integer) data.get(0);
			} else if (empCadre.contains("Officer")) {
				String sql = "select officer_limit from hrms_allowance.foodbeverages_limit";
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
	public Integer getRefreshmentsLimit(String empCadre) {
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();
		List<Object> data = null;
		Integer emplimit = null;
		try {

			if (empCadre.contains("OAS")) {
				String sql = "select oas_limit from hrms_allowance.refreshments_limit";
				SQLQuery query = session.createSQLQuery(sql);
				data = query.list();
				emplimit = (Integer) data.get(0);
			} else if (empCadre.contains("OAT")) {
				String sql = "select oat_limit from hrms_allowance.refreshments_limit";
				SQLQuery query = session.createSQLQuery(sql);
				data = query.list();
				emplimit = (Integer) data.get(0);
			} else if (empCadre.contains("Officer")) {
				String sql = "select officer_limit from hrms_allowance.refreshments_limit";
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
	public Integer getConveyanceLimit(String empCadre) {
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();
		List<Object> data = null;
		Integer emplimit = null;
		try {

			if (empCadre.contains("OAS")) {
				String sql = "select oas_limit from hrms_allowance.conveyance_limit";
				SQLQuery query = session.createSQLQuery(sql);
				data = query.list();
				emplimit = (Integer) data.get(0);
			} else if (empCadre.contains("OAT")) {
				String sql = "select oat_limit from hrms_allowance.conveyance_limit";
				SQLQuery query = session.createSQLQuery(sql);
				data = query.list();
				emplimit = (Integer) data.get(0);
			} else if (empCadre.contains("Officer")) {
				String sql = "select officer_limit from hrms_allowance.conveyance_limit";
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
	public Integer getTelephoneLimit(String empCadre) {
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();
		List<Object> data = null;
		Integer emplimit = null;
		try {

			if (empCadre.contains("OAS")) {
				String sql = "select oas_limit from hrms_allowance.telephone_limit";
				SQLQuery query = session.createSQLQuery(sql);
				data = query.list();
				emplimit = (Integer) data.get(0);
			} else if (empCadre.contains("OAT")) {
				String sql = "select oat_limit from hrms_allowance.telephone_limit";
				SQLQuery query = session.createSQLQuery(sql);
				data = query.list();
				emplimit = (Integer) data.get(0);
			} else if (empCadre.contains("Officer")) {
				String sql = "select officer_limit from hrms_allowance.telephone_limit";
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
	public Integer getnewspaperAppCount(Date applied_year_month, Integer emply_cd) {
		Session session = sessionFactory2.openSession();
		Transaction tx = session.beginTransaction();

		List<Object> data = null;
		Integer count = 0;
		try {
			String sql = "SELECT count(*) FROM hrms_allowance.newspaper_users where emply_cd=:emply_cd and date_format(applied_year_month,'%Y-%m')= date_format(':applied_year_month','%Y-%m')";
			// String sql = "SELECT count(*) FROM hrms_allowance.newspaper_users where
			// emply_cd=:emply_cd and applied_year_month=:applied_year_month";
			SQLQuery query = session.createSQLQuery(sql);
			/*
			 * query.setParameter("emply_cd", emply_cd);
			 * query.setParameter("applied_year_month", applied_month);
			 */
			data = query.list();
			count = (Integer) data.get(0);

			System.out.println(data);

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		session.close();

		return count;
	}

}
