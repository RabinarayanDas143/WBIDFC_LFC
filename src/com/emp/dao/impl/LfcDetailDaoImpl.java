package com.emp.dao.impl;

import java.math.BigInteger;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.build.AllowSysOut;
import org.hibernate.context.spi.CurrentSessionContext;
import org.hibernate.jdbc.Work;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.procedure.ProcedureParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emp.bean.LfcBean;
import com.emp.dao.LfcDetailDao;
import com.emp.model.Hrms_Dependent;
import com.emp.model.LfcModel;
import com.emp.model.Lfc_Allowence;
import com.emp.model.Lfc_Dependent;
import com.emp.model.Lfc_Surrender;
import com.mysql.cj.jdbc.CallableStatement;

@Repository
@Transactional
public class LfcDetailDaoImpl implements LfcDetailDao {
	@Autowired
	@Qualifier(value = "sessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
	@Qualifier(value = "sessionFactory")
	private static SessionFactory sessionFactory1;

	@SuppressWarnings("deprecation")
	@Override
	public List<Object[]> getDetails(Integer userId) {
		List<Object[]> empDetails = null;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		// '"+userId+"'"
		try {
			String sql = "select a.emply_cd,concat(ifnull(emply_title,''),' ',ifnull(emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',\r\n"
					+ "ifnull(emply_last_name,''))as 'Name', b.designation,c.classification  from \r\n"
					+ "hrms_wbidfc.hrms_employee_detail a join hrms_wbidfc.hrms_designation_detail b on a.emply_cd=b.emply_cd join\r\n"
					+ "  hrms_wbidfc.hrms_classification_details c on a.emply_cd=c.emply_cd where a.emply_cd=:emply_cd";
			NativeQuery query = session.createNativeQuery(sql);
			query.setParameter("emply_cd", userId);
			empDetails = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Excedption occured in LfcDetailDaoImpl : " + e);
		}
		tx.commit();
		session.close();
		return empDetails;
	}

	@Override
	public Map<String, String> getLeaveType() {
		Map<String, String> leaveDetails = new HashMap<String, String>();
		List<Object[]> list = null;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			String sql = "select leave_type,abbrivation from lms_module_wb.hrms_leave_master where abbrivation in('EL','SPL','CL')";
			NativeQuery query = session.createNativeQuery(sql);
			list = query.getResultList();
			for (int i = 0; i < list.size(); i++) {
				leaveDetails.put(list.get(i)[1].toString(), list.get(i)[0].toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in getLeaveType :" + e);
		}
		tx.commit();
		session.close();
		return leaveDetails;
	}

	@Override
	public Map<String, String> getEncashmentLeaveType() {

		Map<String, String> encashmentleave = new HashMap<String, String>();
		List<Object[]> list = null;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			String sql = "select leave_type,abbrivation from lms_module_wb.hrms_leave_master where abbrivation in('EL')";
			NativeQuery query = session.createNativeQuery(sql);
			list = query.getResultList();
			for (int i = 0; i < list.size(); i++) {
				encashmentleave.put(list.get(i)[1].toString(), list.get(i)[0].toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in getLeaveType :" + e);
		}
		tx.commit();
		session.close();
		return encashmentleave;

	}

	@Override
	public List<Integer> getLeaveCount(Integer userId, String lvtype) {
		List<Integer> result = null;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object> data = null;
		try {
			String sql = "select LV_BALANCE from lms_module_wb.leave_balance_new where emply_cd=:userId and leave_type=:lvtype";
			NativeQuery query = session.createNativeQuery(sql);
			query.setParameter("userId", userId);
			query.setParameter("lvtype", lvtype);
			result = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in getLeaveCount method : " + e);
		}
		tx.commit();
		session.close();
		return result;
	}

	@Override
	public List<Integer> getEncashmentLeaveCount(Integer userId, String encashmentLeaveType) {
		List<Integer> result = null;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object> data = null;
		try {
			String sql = "select LV_BALANCE from lms_module_wb.leave_balance_new where emply_cd=:userId and leave_type=:encashmentLeaveType";
			NativeQuery query = session.createNativeQuery(sql);
			query.setParameter("userId", userId);
			query.setParameter("encashmentLeaveType", encashmentLeaveType);
			result = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in getLeaveCount method : " + e);
		}
		tx.commit();
		session.close();
		return result;
	}

//(List<Lfc_Allowence> list)
	@Override
	public int saveInfo(List<Lfc_Allowence> lfc_AllowenceList) {
		System.out.println("Inside saveInfoDaoImpl"+lfc_AllowenceList);
		
		
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			for (Lfc_Allowence allowence : lfc_AllowenceList)
				session.save(allowence);

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in saveInfoDaoImpl :" + e);
		}
		tx.commit();
		session.close();
		return 0;
	}

	public int saveDependentInfo(ArrayList<Lfc_Dependent> dependentList) {
		System.out.println("inside saveDependentInfo method-----------");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			for (Lfc_Dependent dependent : dependentList) {
				session.save(dependent);
			}
		} catch (Exception e) {
			System.out.println("Exception in saveDependentInfo Dao method " + e);
		}
		tx.commit();
		session.close();
		return 0;
	}

	@Override
	public int saveSuurenderInfo(ArrayList<Lfc_Surrender> surrenderList) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			for (Lfc_Surrender surrender : surrenderList)
				session.save(surrender);

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in saveInfoDaoImpl :" + e);
		}
		tx.commit();
		session.close();
		return 0;
	}

	@Override
	public String checkUser(Integer userId) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String categorie = null;
		try {
			String department = "select trim(department) from hrms_wbidfc.hrms_department_detail where emply_cd='"
					+ userId + "'and status='A'";
			NativeQuery query = session.createNativeQuery(department);
			categorie = query.getResultList().get(0).toString();
			// System.out.println("categorie is :"+categorie);

		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		session.close();
		return categorie;
	}

	public static String replaceLast(String text, String regex, String replacement) {
		return text.replaceFirst("(?s)" + regex + "(?!.*?" + regex + ")", replacement);
	}

	@Override
	public List<LfcModel> getAdminData() {
		List<LfcModel> lfcData = new ArrayList<LfcModel>();

		List<Object[]> result = null;
		List<LfcModel> newList = null;
		List<Object[]> dependentdata = null;
		List<String> list = null;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String sql = null;
		String sql1 = null;

		try {

			sql = "select a.EMPLY_CD,(concat(ifnull(b.emply_title,''),' ',ifnull(b.emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,'')))'Name',\r\n"
					+ "	a.LEAVE_TYPE,date_format(a.leave_FROM_DT,'%d-%m-%Y')'leave_FROM_DT',date_format(a.leave_TO_DT,'%d-%m-%Y')'leave_TO_DT',\r\n"
					+ "	a.NUMBER_OF_DAYS,a.PLACE_OF_DESTINATION,a.AMOUNT_OF_ADVANCE,a.tran_id from hrms_wbidfc.hrms_encashment a \r\n"
					+ "	 join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd join hrms_wbidfc.hrms_department_detail c \r\n"
					+ "     on b.emply_cd=c.emply_cd where department  NOT in(TRIM('HR & ADMINISTRATION'),TRIM('INTERNAL AUDIT'),\r\n"
					+ "     TRIM('COMPANY SECRERTARIAT')) and (a.Approval_Level_1 is null OR a.Approval_Level_1='') AND C.STATUS='A';\r\n"
					+ "";

			newList = new ArrayList<LfcModel>();
			List<Integer> list1 = new ArrayList<>();

			NativeQuery query = session.createNativeQuery(sql);
			result = query.getResultList();
			System.out.println("result is :" + result);

			LfcModel l = null;

			for (Object[] obj : result) {

				String dependents = "";
				String occupation = "";
				String annualIncome = "";

				String str = obj[0].toString().trim();
				String transctionId = obj[8].toString().trim();
				sql1 = "select EMPLY_CD,Dependent_Name,annualIncome,occupation from hrms_wbidfc.lfc_dependent where EMPLY_CD ="
						+ str + " and tranId =" + transctionId + " ";

				NativeQuery dependent = session.createNativeQuery(sql1);
				dependentdata = dependent.getResultList();

				for (Object[] obj2 : dependentdata) {

					String str2 = obj2[0].toString().trim();

					if (str.equalsIgnoreCase(str2)) {
						dependents += obj2[1].toString() + ",";
						annualIncome += obj2[2].toString() + ",";
						occupation += obj2[3].toString() + ",";
					}
				}

				dependents = replaceLast(dependents, ",", "");
				annualIncome = replaceLast(annualIncome, ",", "");
				occupation = replaceLast(occupation, ",", "");

				l = new LfcModel();

				l.setId(str != null ? Integer.parseInt(str) : 0);
				l.setName(obj[1] != null ? obj[1].toString() : "");
				l.setDependent(dependents != null ? dependents : "");
				l.setOccupation(occupation != null ? occupation : "");
				l.setAnnualIncome(annualIncome != null ? annualIncome : "");
				l.setLeaveTypeStr(obj[2] != null ? obj[2].toString() : "");
				l.setLeavefromDateStr(obj[3] != null ? obj[3].toString() : "");
				l.setLeavetoDateStr(obj[4] != null ? obj[4].toString() : "");
				String nod = obj[5].toString();
				l.setNumberofDays(nod != null ? Integer.parseInt(nod) : 0);
				l.setPlaceofDestination(obj[6] != null ? obj[6].toString() : "");
				String amount = obj[7].toString();
				l.setAmountofAdvance(amount != null ? Integer.parseInt(amount) : 0);
				String tranId = obj[8].toString();
				l.setTranId(tranId != null ? Integer.parseInt(tranId) : 0);

				lfcData.add(l);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lfcData;
	}

	@Override
	public Object acceptReq(int acceptValue, String hradminremark, int  auditamount, int auditamountLeaveEncash) {
		// System.out.println("Inside acceptReq");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			String sql = "update hrms_wbidfc.hrms_encashment a set a.Approval_Level_1 ='A', a.Remark='" + hradminremark
					+ "' , a.Advance_Amount_Approved = "+auditamount+", a.Leave_Encashment_Amount_Approved = "+auditamountLeaveEncash+" where a.tran_id='" + acceptValue + "'";
			NativeQuery query = session.createNativeQuery(sql);
			int executeUpdate = query.executeUpdate();
			tx.commit();
			// System.out.println("update status: "+executeUpdate);
			// System.out.println("Succesfully updated data");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in acceptReqImpl :"+e);
		}
		return null;
	}

	@Override
	public int rejectReq(int rejectValue, String hradminremark) {

		// System.out.println("Inside rejectReq");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int executeUpdate = 0;
		try {
			String sql = "update hrms_wbidfc.hrms_encashment a set a.Approval_Level_1 ='R',a.Remark='" + hradminremark
					+ "',a.Status='R' where a.tran_id='" + rejectValue + "'";

			NativeQuery query = session.createNativeQuery(sql);
			executeUpdate = query.executeUpdate();

			String sql1 = "select EMPLY_CD from hrms_wbidfc.hrms_encashment where tran_id='" + rejectValue + "'";

			NativeQuery empcd_query = session.createNativeQuery(sql1);
			int emp_cd = (int) empcd_query.uniqueResult();

			String sql2 = "update  hrms_wbidfc.lfc_dependent set Status='R' where EMPLY_CD ='" + emp_cd + "'";
			NativeQuery deletedata = session.createNativeQuery(sql2);
			int deleteupdate = deletedata.executeUpdate();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in acceptReqImpl :" + e);
		}
		return executeUpdate;

	}

	@Override
	public String checkPeriodApply(int empId, String lfcFromDate, String lfctoDate) {
		// System.out.println("Inside checkPeriodApply");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		String result1 = null;
		try {

			StoredProcedureQuery procedureQuery = session.createStoredProcedureQuery("isapplied");
			procedureQuery.registerStoredProcedureParameter("lv_from", String.class, ParameterMode.IN);
			procedureQuery.registerStoredProcedureParameter("lv_to", String.class, ParameterMode.IN);
			procedureQuery.registerStoredProcedureParameter("empcode", Integer.class, ParameterMode.IN);
			procedureQuery.registerStoredProcedureParameter("error_msg", String.class, ParameterMode.OUT);

			procedureQuery.setParameter("lv_from", lfcFromDate);
			procedureQuery.setParameter("lv_to", lfctoDate);
			procedureQuery.setParameter("empcode", empId);
			// procedureQuery.setParameter("error_msg", "");

			procedureQuery.execute();
			String result = (String) procedureQuery.getOutputParameterValue("error_msg");

			// System.out.println("result is === :"+result);
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in checkPeriodApply :"+e);
		}
		return result1;
	}

	public static class CheckApply {
		public static String main(String[] args) {
			Session session = sessionFactory1.openSession();
			Transaction tx = session.beginTransaction();
			String str = "";
			try {
				StoredProcedureQuery procedureQuery = session.createStoredProcedureQuery("hrms_wbidfc.isapplied");
				procedureQuery.registerStoredProcedureParameter("lv_from", String.class, ParameterMode.IN);
				procedureQuery.registerStoredProcedureParameter("lv_to", String.class, ParameterMode.IN);
				procedureQuery.registerStoredProcedureParameter("empcode", Integer.class, ParameterMode.IN);
				procedureQuery.registerStoredProcedureParameter("error_msg", String.class, ParameterMode.OUT);
			} catch (Exception e) {
				e.printStackTrace();
				// System.out.println("Exception in CheckApply class :"+e);
			}
			return str;
		}
	}

	@Override
	public List<LfcModel> getInternalAuditorData() {
		// System.out.println("Inside getInternalAuditorData method ----");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<LfcModel> lfcData = new ArrayList<LfcModel>();
		List<Object[]> result = null;
		List<Object[]> dependentdata = null;
		String value = "A";
		try {
			String sql = "select a.EMPLY_CD,(concat(ifnull(b.emply_title,''),' ',ifnull(b.emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,'')))'Name',\r\n"
					+ " a.LEAVE_TYPE,date_format(a.leave_FROM_DT,'%d-%m-%Y')'leave_FROM_DT',date_format(a.leave_TO_DT,'%d-%m-%Y')'leave_TO_DT',\r\n"
					+ " a.NUMBER_OF_DAYS,a.PLACE_OF_DESTINATION,a.AMOUNT_OF_ADVANCE,a.Remark,a.tran_id from hrms_wbidfc.hrms_encashment a join\r\n"
					+ " hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd where  a.Approval_Level_1='A'and Approval_Level_2 is null OR\r\n"
					+ " a.Approval_Level_2=''";

			NativeQuery query = session.createNativeQuery(sql);
			result = query.getResultList();

			LfcModel lm = null;

			for (Object[] obj : result) {
				String dependents = "";
				String annualIncome = "";
				String occupation = "";
				String str = obj[0].toString().trim();
				String transctionId = obj[9].toString().trim();
				String sql1 = "select EMPLY_CD,Dependent_Name,annualIncome,occupation from hrms_wbidfc.lfc_dependent where EMPLY_CD ="
						+ str + " and tranId =" + transctionId + " ";
				NativeQuery dependent = session.createNativeQuery(sql1);
				dependentdata = dependent.getResultList();

				for (Object[] obj2 : dependentdata) {
					String str2 = obj2[0].toString().trim();

					if (str.equalsIgnoreCase(str2)) {
						dependents += obj2[1].toString() + ",";
						annualIncome += obj2[2].toString() + ",";
						occupation += obj2[3].toString() + ",";
					}
				}
				dependents = replaceLast(dependents, ",", "");
				annualIncome = replaceLast(annualIncome, ",", "");
				occupation = replaceLast(occupation, ",", "");

				lm = new LfcModel();

				lm.setId(str != null ? Integer.parseInt(str) : 0);
				lm.setName(obj[1] != null ? obj[1].toString() : "");
				lm.setDependent(dependents != null ? dependents : "");
				lm.setOccupation(occupation != null ? occupation : "");
				lm.setAnnualIncome(annualIncome != null ? annualIncome : "");
				lm.setLeaveTypeStr(obj[2] != null ? obj[2].toString() : "");
				lm.setLeavefromDateStr(obj[3] != null ? obj[3].toString() : "");
				lm.setLeavetoDateStr(obj[4] != null ? obj[4].toString() : "");
				lm.setNumberofDaysStr(obj[5] != null ? obj[5].toString() : "");
				lm.setPlaceofDestination(obj[6] != null ? obj[6].toString() : "");
				lm.setAmountofAdvanceStr(obj[7] != null ? obj[7].toString() : "");
				lm.setHrRemark(obj[8] != null ? obj[8].toString() : "");
				String tranId = obj[9].toString();
				lm.setTranId(tranId != null ? Integer.parseInt(tranId) : 0);

				lfcData.add(lm);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return lfcData;
	}

	// for Internal Audit accept function.
	@Override
	public void InternalacceptReq(int acceptValue, String auditremark) {

		// System.out.println("Inside InternalacceptReq");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			String sql = "update hrms_wbidfc.hrms_encashment a set a.Approval_Level_2 ='A'," + "a.Audit_Remark='"
					+ auditremark + "' where a.tran_id='" + acceptValue + "'";
			NativeQuery query = session.createNativeQuery(sql);
			int executeUpdate = query.executeUpdate();
			tx.commit();
			// System.out.println("update status: "+executeUpdate);
			// System.out.println("Succesfully updated data");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in acceptReqImpl :"+e);
		}

	}

	@Override
	public int InternalrejectReq(int rejectvalue, String auditremark) {

		// System.out.println("Inside InternalrejectReq");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int executeUpdate = 0;
		try {
			String sql = "update hrms_wbidfc.hrms_encashment a set a.Approval_Level_2 ='R',a.Audit_Remark='"
					+ auditremark + "',a.Status='R' where a.tran_id='" + rejectvalue + "'";
			NativeQuery query = session.createNativeQuery(sql);
			executeUpdate = query.executeUpdate();

			String sql1 = "select EMPLY_CD from hrms_wbidfc.hrms_encashment where tran_id='" + rejectvalue + "'";

			NativeQuery empcd_query = session.createNativeQuery(sql1);
			int emp_cd = (int) empcd_query.uniqueResult();

			String sql2 = "update  hrms_wbidfc.lfc_dependent set Status='R' where EMPLY_CD ='" + emp_cd + "'";
			NativeQuery deletedata = session.createNativeQuery(sql2);
			int deleteupdate = deletedata.executeUpdate();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in acceptReqImpl :"+e);
		}
		return executeUpdate;
	}

	@Override
	public List<LfcModel> getCsAdmindata() {

		// System.out.println("Inside getCsAdmindata method ----");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<LfcModel> lfcData = new ArrayList<LfcModel>();
		List<Object[]> result = null;
		List<Object[]> dependentdata = null;
		String value = "A";
		try {
			String sql = "select a.EMPLY_CD,(concat(ifnull(b.emply_title,''),' ',ifnull(b.emply_first_name,''),' ',"
					+ "ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,'')))'Name',\r\n"
					+ "    a.LEAVE_TYPE,date_format(a.leave_FROM_DT,'%d-%m-%Y')'leave_FROM_DT',"
					+ "date_format(a.leave_TO_DT,'%d-%m-%Y')'leave_TO_DT',\r\n"
					+ "    a.NUMBER_OF_DAYS,a.PLACE_OF_DESTINATION,a.AMOUNT_OF_ADVANCE,a.Audit_Remark,a.tran_id"
					+ " from hrms_wbidfc.hrms_encashment a\r\n"
					+ "    join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd join"
					+ " hrms_wbidfc.hrms_department_detail c on a.emply_cd=c.emply_cd\r\n"
					+ "    where  a.Approval_Level_2='A'and  ( Approval_Level_3 is null OR a.Approval_Level_3='')"
					+ " and c.department not in('HR & ADMINISTRATION','INTERNAL AUDIT','COMPANY SECRERTARIAT')";

			NativeQuery query = session.createNativeQuery(sql);
			result = query.getResultList();

			LfcModel lm = null;

			for (Object[] obj : result) {

				String dependents = "";
				String annualIncome = "";
				String occupation = "";
				String str = obj[0].toString().trim();
				String transctionId = obj[9].toString().trim();
				String sql1 = "select EMPLY_CD,Dependent_Name,annualIncome,occupation from hrms_wbidfc.lfc_dependent where EMPLY_CD ="
						+ str + " and tranId =" + transctionId + " ";
				NativeQuery dependent = session.createNativeQuery(sql1);
				dependentdata = dependent.getResultList();

				for (Object[] obj2 : dependentdata) {
					String str2 = obj2[0].toString().trim();

					if (str.equalsIgnoreCase(str2)) {
						dependents += obj2[1].toString() + ",";
						annualIncome += obj2[2].toString() + ",";
						occupation += obj2[3].toString() + ",";
					}
				}
				dependents = replaceLast(dependents, ",", "");
				annualIncome = replaceLast(annualIncome, ",", "");
				occupation = replaceLast(occupation, ",", "");

				lm = new LfcModel();

				lm.setId(str != null ? Integer.parseInt(str) : 0);
				lm.setName(obj[1] != null ? obj[1].toString() : "");
				lm.setDependent(dependents != null ? dependents : "");
				lm.setOccupation(occupation != null ? occupation : "");
				lm.setAnnualIncome(annualIncome != null ? annualIncome : "");
				lm.setLeaveTypeStr(obj[2] != null ? obj[2].toString() : "");
				lm.setLeavefromDateStr(obj[3] != null ? obj[3].toString() : "");
				lm.setLeavetoDateStr(obj[4] != null ? obj[4].toString() : "");
				lm.setNumberofDaysStr(obj[5] != null ? obj[5].toString() : "");
				lm.setPlaceofDestination(obj[6] != null ? obj[6].toString() : "");
				lm.setAmountofAdvanceStr(obj[7] != null ? obj[7].toString() : "");
				lm.setInternalAuditRemark(obj[8] != null ? obj[8].toString() : "");
				String tranId = obj[9].toString();
				lm.setTranId(tranId != null ? Integer.parseInt(tranId) : 0);

				lfcData.add(lm);

			}
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in getCsAdmindataDaoImpl method :"+e);
		}
		return lfcData;

	}

	@Override
	public int CSacceptReq(int acceptValue, String cSremark, String empCode, String checkerEin) {

		// System.out.println("Inside InternalacceptReq");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> empdetails = null;
		int executeUpdate = 0;
		try {
			LocalDate myObj = LocalDate.now();

			String sql = "update hrms_wbidfc.hrms_encashment a set a.Approval_Level_3 ='A',a.Cs_Remark='" + cSremark
					+ "' where a.tran_id='" + acceptValue + "'";
			String sql2 = "select EMPLY_CD,LFC_FROM_DT,LFC_TO_DT,AMOUNT_OF_ADVANCE,date(SUBMIT_DATE) FROM hrms_wbidfc.hrms_encashment where tran_id='"
					+ acceptValue + "'";
			NativeQuery query1 = session.createNativeQuery(sql);
			executeUpdate = query1.executeUpdate();

			NativeQuery dependent = session.createNativeQuery(sql2);
			empdetails = dependent.getResultList();
			int empcd = (int) empdetails.get(0)[0];
			System.out.println(empcd);

			Date lfd = (Date) empdetails.get(0)[1];

			SimpleDateFormat sf3 = new SimpleDateFormat("yyyyMM");
			String fromDate3 = sf3.format(lfd);
			DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("uuuuMM");
			YearMonth appliedYearMonth3 = YearMonth.parse(fromDate3, dtf3);
			int lfcFromDate3 = Integer.parseInt(appliedYearMonth3.format(DateTimeFormatter.ofPattern("yyyyMM")));

			Date ltd = (Date) empdetails.get(0)[2];

			SimpleDateFormat sf4 = new SimpleDateFormat("yyyyMM");
			String fromDate4 = sf4.format(ltd);
			DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("uuuuMM");
			YearMonth appliedYearMonth4 = YearMonth.parse(fromDate4, dtf4);
			int lfcToDate4 = Integer.parseInt(appliedYearMonth4.format(DateTimeFormatter.ofPattern("yyyyMM")));

			int amt = (int) empdetails.get(0)[3];
			float amount = amt;// (float) amt;

			Date makerDate = (Date) empdetails.get(0)[4];

			Date checkerDate = java.sql.Date.valueOf(myObj);

			String makerFlag = "Y";

			String checkerFlag = "Y";

			String payRecoveryCode = "P050";

			int payRecoverySerial = 11;

			String remarks = "";

			int adj_of_yrmth = 0;

			int ln_snctn_no = 0;

			int rmtng_offc_cd = 0;

			String dscrptn = "";

			String sql1 = "insert into hrms_payroll_wb.eslsaltran (EMPLY_CD,PAY_RCVRY_CD,PAY_RCVRY_SRL,VLD_FRM_YRMTH,VLD_TO_YRMTH,ADJ_OF_YRMTH,DSCRPTN,AMT,LN_SNCTN_NO,RMTNG_OFFC_CD,MAKER_EIN,MAKER_FLG,MAKER_DATE,CHECKER_EIN,CHECKER_FLG,CHECKER_DATE,REMARKS) "
					+ "values(:emply_cd, :pay_rcvry_cd, :pay_rcvry_srl, :vld_frm_yrmth, :vld_to_yrmth, :adj_of_yrmth, :dscrptn, :amt, :ln_snctn_no, :rmtng_offc_cd, :maker_ein, :maker_flg,  :maker_date, :checker_ein, :checker_flg, :checker_date, :remarks)";
			SQLQuery query = session.createSQLQuery(sql1);
			query.setParameter("emply_cd", empcd);
			query.setParameter("pay_rcvry_cd", payRecoveryCode);
			query.setParameter("pay_rcvry_srl", payRecoverySerial);
			query.setParameter("vld_frm_yrmth", lfcFromDate3);
			query.setParameter("vld_to_yrmth", lfcToDate4);
			query.setParameter("adj_of_yrmth", adj_of_yrmth);
			query.setParameter("amt", amount);
			query.setParameter("ln_snctn_no", ln_snctn_no);
			query.setParameter("dscrptn", dscrptn);
			query.setParameter("rmtng_offc_cd", rmtng_offc_cd);
			query.setParameter("maker_ein", empCode);
			query.setParameter("maker_flg", makerFlag);
			query.setParameter("maker_date", makerDate);
			query.setParameter("checker_ein", checkerEin);
			query.setParameter("checker_flg", checkerFlag);
			query.setParameter("checker_date", checkerDate);
			query.setParameter("remarks", remarks);

			query.executeUpdate();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return executeUpdate;
	}

	@Override
	public int CSrejectReq(int rejectvalue, String cSremark) {

		// System.out.println("Inside CSrejectReq");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int executeUpdate = 0;
		try {
			String sql = "update hrms_wbidfc.hrms_encashment a set a.Approval_Level_3 ='R',a.Cs_Remark='" + cSremark
					+ "',a.Status='R' where a.tran_id='" + rejectvalue + "'";
			NativeQuery query = session.createNativeQuery(sql);
			executeUpdate = query.executeUpdate();

			String sql1 = "select EMPLY_CD from hrms_wbidfc.hrms_encashment where tran_id='" + rejectvalue + "'";

			NativeQuery empcd_query = session.createNativeQuery(sql1);
			int emp_cd = (int) empcd_query.uniqueResult();

			String sql2 = "update  hrms_wbidfc.lfc_dependent set Status='R' where EMPLY_CD ='" + emp_cd + "'";
			NativeQuery deletedata = session.createNativeQuery(sql2);
			int deleteupdate = deletedata.executeUpdate();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in acceptReqImpl :"+e);
		}
		return executeUpdate;
	}

	@Override
	public String userFilter(Integer userId) {
		// System.out.println("Inside userFilterDaoImpl");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String result = null;
		try {
			String user = null;
			String a = "A";
			String sql = "select trim(department) from hrms_wbidfc.hrms_department_detail where emply_cd=" + userId
					+ " and status='A'";
			NativeQuery query = session.createNativeQuery(sql);// query.executeUpdate();
			user = query.getResultList().get(0).toString();// .toString();

			System.out.println("user is ------" + user);
			if (user.equalsIgnoreCase("HR & ADMINISTRATION") || user.equalsIgnoreCase("INTERNAL AUDIT")
					|| user.equalsIgnoreCase("COMPANY SECRERTARIAT")) {
				result = user;
			}
			// System.out.println("User in daoimpl :"+result);
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in userFilterDaoImpl :"+e);
		}

		return result;
	}

	@Override
	public List<LfcModel> getLfcHrReport(Integer userId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		List<LfcModel> lfcData = new ArrayList<LfcModel>();
		List<Object[]> result = null;
		List<Object[]> dependentdata = null;
		try {
			String sql = "select a.EMPLY_CD,(concat(ifnull(b.emply_title,''),' ',ifnull(b.emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,'')))'Name',\r\n"
					+ "	 a.LEAVE_TYPE,date_format(a.leave_FROM_DT,'%d-%m-%Y')'leave_FROM_DT',date_format(a.leave_TO_DT,'%d-%m-%Y')'leave_TO_DT',\r\n"
					+ "	a.NUMBER_OF_DAYS,a.PLACE_OF_DESTINATION,a.AMOUNT_OF_ADVANCE, ( case when a.Approval_Level_1='A' Then 'Approved'\r\n"
					+ "	when a.Approval_Level_1='R' Then 'Rejected' when a.Approval_Level_1 is null OR a.Approval_Level_1='' \r\n"
					+ "	Then 'Pending' else '-' end)as'Approval_Level_1',\r\n"
					+ "	( case when a.Approval_Level_1='R' Then '-' when a.Approval_Level_2='A' Then 'Approved'\r\n"
					+ "	when a.Approval_Level_2='R' Then 'Rejected' when a.Approval_Level_2 is null OR a.Approval_Level_2='' \r\n"
					+ "	Then 'Pending' else '-' end)as'Approval_Level_2',\r\n"
					+ "	( case when a.Approval_Level_1='R' Then '-' when a.Approval_Level_3='A' Then 'Approved'\r\n"
					+ "	when a.Approval_Level_3='R' Then 'Rejected' when a.Approval_Level_3 is null OR a.Approval_Level_3='' \r\n"
					+ "	Then 'Pending' else '-' end)as'Approval_Level_3',a.tran_id\r\n"
					+ "	from hrms_wbidfc.hrms_encashment a join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd join\r\n"
					+ "	hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "	where c.department not in('HR & ADMINISTRATION','INTERNAL AUDIT','COMPANY SECRERTARIAT') AND C.STATUS='A'\r\n"
					+ "	union all\r\n"
					+ "	select a.EMPLY_CD,(concat(IFNULL(b.emply_title,''),' ',IFNULL(b.emply_first_name,''),' ',IFNULL(emply_middle_name,''),' ',IFNULL(emply_last_name,'')))'Name',\r\n"
					+ "	 a.LEAVE_TYPE,date_format(a.leave_FROM_DT,'%d-%m-%Y')'leave_FROM_DT',date_format(a.leave_TO_DT,'%d-%m-%Y')'leave_TO_DT',\r\n"
					+ "	a.NUMBER_OF_DAYS,a.PLACE_OF_DESTINATION,a.AMOUNT_OF_ADVANCE, ( case when a.Approval_Level_1='A' Then 'Approved'\r\n"
					+ "	when a.Approval_Level_1='R' Then '-' when a.Approval_Level_1 is null OR a.Approval_Level_1='' \r\n"
					+ "	Then '-' else '-' end)as'Approval_Level_1',\r\n"
					+ "	( case when a.Approval_Level_1='R' Then '-' when a.Approval_Level_2='A' Then 'Approved'\r\n"
					+ "	when a.Approval_Level_2='R' Then 'Rejected' when a.Approval_Level_2 is null OR a.Approval_Level_2='' \r\n"
					+ "	Then 'Pending' else '-' end)as'Approval_Level_2',\r\n"
					+ "	( case when a.Approval_Level_1='R' Then '-' when a.Approval_Level_3='A' Then 'Approved'\r\n"
					+ "	when a.Approval_Level_3='R' Then 'Rejected' when a.Approval_Level_3 is null OR a.Approval_Level_3='' \r\n"
					+ "	Then 'Pending' else '-' end)as'Approval_Level_3',a.tran_id\r\n"
					+ "	from hrms_wbidfc.hrms_encashment a join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd join\r\n"
					+ "	hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "	where c.department in('HR & ADMINISTRATION')AND  C.STATUS='A'";

			NativeQuery query = session.createNativeQuery(sql);
			result = query.getResultList();

			LfcModel lm = null;

			for (Object[] obj : result) {
				String dependents = "";
				String annualIncome = "";
				String occupation = "";
				String str = obj[0].toString().trim();
				String transctionId = obj[11].toString().trim();
				String sql1 = "select EMPLY_CD,Dependent_Name,annualIncome,occupation from hrms_wbidfc.lfc_dependent where EMPLY_CD ="
						+ str + " and tranId=" + transctionId + "";
				NativeQuery dependent = session.createNativeQuery(sql1);
				dependentdata = dependent.getResultList();

				for (Object[] obj2 : dependentdata) {

					String str2 = obj2[0].toString().trim();

					if (str.equalsIgnoreCase(str2)) {
						dependents += obj2[1].toString() + ",";
						annualIncome += obj2[2].toString() + ",";
						occupation += obj2[3].toString() + ",";
					}
				}

				dependents = replaceLast(dependents, ",", "");
				annualIncome = replaceLast(annualIncome, ",", "");
				occupation = replaceLast(occupation, ",", "");

				lm = new LfcModel();

				lm.setId(str != null ? Integer.parseInt(str) : 0);
				lm.setName(obj[1] != null ? obj[1].toString() : "");
				lm.setDependent(dependents != null ? dependents : "");
				lm.setOccupation(occupation != null ? occupation : "");
				lm.setAnnualIncome(annualIncome != null ? annualIncome : "");
				lm.setLeaveTypeStr(obj[2] != null ? obj[2].toString() : "");
				lm.setLeavefromDateStr(obj[3] != null ? obj[3].toString() : "");
				lm.setLeavetoDateStr(obj[4] != null ? obj[4].toString() : "");
				lm.setNumberofDaysStr(obj[5] != null ? obj[5].toString() : "");
				lm.setPlaceofDestination(obj[6] != null ? obj[6].toString() : "");
				lm.setAmountofAdvanceStr(obj[7] != null ? obj[7].toString() : "");
				lm.setHrStatus(obj[8] != null ? obj[8].toString() : "");
				lm.setInternalAuditStatus(obj[9] != null ? obj[9].toString() : "");
				lm.setCsStatus(obj[10] != null ? obj[10].toString() : "");

				lfcData.add(lm);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in getLfcHrReportDaoImpl :"+e);
		}
		tx.commit();
		session.close();
		return lfcData;
	}

	@Override
	public List<LfcModel> getLfcAuditReport(Integer userId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<LfcModel> lfcData = new ArrayList<LfcModel>();
		List<Object[]> result = null;
		List<Object[]> dependentdata = null;
		try {
			String sql = "select a.EMPLY_CD,(concat(ifnull(b.emply_title,''),' ',ifnull(b.emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,'')))'Name', \r\n"
					+ "	a.LEAVE_TYPE,date_format(a.leave_FROM_DT,'%d-%m-%Y')'leave_FROM_DT',\r\n"
					+ "	date_format(a.leave_TO_DT,'%d-%m-%Y')'leave_TO_DT',  a.NUMBER_OF_DAYS,a.PLACE_OF_DESTINATION,a.AMOUNT_OF_ADVANCE, \r\n"
					+ "	( case when a.Approval_Level_1='A' Then 'Approved' when a.Approval_Level_1='R' Then\r\n"
					+ "	 'Reject' when a.Approval_Level_1 OR a.Approval_Level_1='' is null Then 'Pending' else '-' end)as'Level_1 Status', \r\n"
					+ "	 ( case when a.Approval_Level_1='R' Then  'Reject'  when a.Approval_Level_2='A' Then 'Approved' when a.Approval_Level_2='R' Then 'Rejected'\r\n"
					+ "	 when a.Approval_Level_2 is null OR a.Approval_Level_2='' Then 'Pending' when a.Approval_Level_2 \r\n"
					+ "	is null OR a.Approval_Level_1='Reject' Then 'Not Applicable' else '-' end)as'Level_2 Status', \r\n"
					+ "	       ( case when a.Approval_Level_2='R' Then  'Reject'  when a.Approval_Level_3='A' Then 'Approved' when a.Approval_Level_3='R' Then 'Rejected'\r\n"
					+ "		 when a.Approval_Level_3 is null OR a.Approval_Level_3='' Then 'Pending' when a.Approval_Level_3 \r\n"
					+ "		is null OR a.Approval_Level_2='Reject' Then 'Not Applicable' else '-' end)as'Level_3 Status',\r\n"
					+ "        a.tran_id\r\n"
					+ "		from hrms_wbidfc.hrms_encashment a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
					+ "		join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd \r\n"
					+ "		where c.department not in('HR & ADMINISTRATION','INTERNAL AUDIT','COMPANY SECRERTARIAT') and c.status='A'\r\n"
					+ "	     union\r\n"
					+ "	     select a.EMPLY_CD,(concat(IFNULL(b.emply_title,''),' ',IFNULL(b.emply_first_name,''),' ',IFNULL(emply_middle_name,''),' ',IFNULL(emply_last_name,'')))'Name', \r\n"
					+ "		a.LEAVE_TYPE,date_format(a.leave_FROM_DT,'%d-%m-%Y')'leave_FROM_DT', \r\n"
					+ "		date_format(a.leave_TO_DT,'%d-%m-%Y')'leave_TO_DT',  a.NUMBER_OF_DAYS,a.PLACE_OF_DESTINATION,a.AMOUNT_OF_ADVANCE, \r\n"
					+ "		( case when a.Approval_Level_1='A' Then 'Approved' when a.Approval_Level_1='R' Then \r\n"
					+ "		 '-' when a.Approval_Level_1 OR a.Approval_Level_1='' is null Then '-' else '-' end)as'Level_1 Status', \r\n"
					+ "	     ( case when a.Approval_Level_1='R' Then  'Reject'  when a.Approval_Level_2='A' Then 'Approved' when a.Approval_Level_2='R' Then 'Rejected' \r\n"
					+ "		 when a.Approval_Level_2 is null OR a.Approval_Level_2='' Then 'Pending' when a.Approval_Level_2 \r\n"
					+ "		is null OR a.Approval_Level_1='Reject' Then 'Not Applicable' else '-' end)as'Level_2 Status',\r\n"
					+ "	       ( case when a.Approval_Level_2='R' Then  'Reject'  when a.Approval_Level_3='A' Then 'Approved' when a.Approval_Level_3='R' Then 'Rejected' \r\n"
					+ "		 when a.Approval_Level_3 is null OR a.Approval_Level_3='' Then 'Pending' when a.Approval_Level_3 \r\n"
					+ "		is null OR a.Approval_Level_2='Reject' Then 'Not Applicable' else '-' end)as'Level_3 Status',\r\n"
					+ "        a.tran_id\r\n"
					+ "		from hrms_wbidfc.hrms_encashment a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd \r\n"
					+ "		join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "		where c.department  in('HR & ADMINISTRATION') and c.status='A'\r\n" + "	     union\r\n"
					+ "	     select a.EMPLY_CD,(concat(IFNULL(b.emply_title,''),' ',IFNULL(b.emply_first_name,''),' ',IFNULL(emply_middle_name,''),' ',IFNULL(emply_last_name,'')))'Name', \r\n"
					+ "		a.LEAVE_TYPE,date_format(a.leave_FROM_DT,'%d-%m-%Y')'leave_FROM_DT',\r\n"
					+ "				date_format(a.leave_TO_DT,'%d-%m-%Y')'leave_TO_DT',  a.NUMBER_OF_DAYS,a.PLACE_OF_DESTINATION,a.AMOUNT_OF_ADVANCE, \r\n"
					+ "		( case when a.Approval_Level_1='A' Then 'Approved' when a.Approval_Level_1='R' Then\r\n"
					+ "		 '-' when a.Approval_Level_1 OR a.Approval_Level_1='' is null Then '-' else '-' end)as'Level_1 Status', \r\n"
					+ "	       ( case when a.Approval_Level_1='R' Then  'Reject'  when a.Approval_Level_2='A' Then '-' when a.Approval_Level_2='R' Then 'Rejected'\r\n"
					+ "		 when a.Approval_Level_2 is null OR a.Approval_Level_2='' Then '-' when a.Approval_Level_2\r\n"
					+ "		is null OR a.Approval_Level_1='Reject' Then 'Not Applicable' else '-' end)as'Level_2 Status',\r\n"
					+ "	 ( case when a.Approval_Level_2='R' Then  'Reject'  when a.Approval_Level_3='A' Then 'Approved' when a.Approval_Level_3='R' Then 'Rejected'\r\n"
					+ "		 when a.Approval_Level_3 is null OR a.Approval_Level_3='' Then 'Pending' when a.Approval_Level_3 \r\n"
					+ "		is null OR a.Approval_Level_2='Reject' Then 'Not Applicable' else '-' end)as'Level_3 Status',\r\n"
					+ "        a.tran_id\r\n"
					+ "		from hrms_wbidfc.hrms_encashment a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
					+ "		join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "		where c.department  in('INTERNAL AUDIT') and c.status='A'";

			NativeQuery query = session.createNativeQuery(sql);
			result = query.getResultList();

			LfcModel lm = null;

			for (Object[] obj : result) {

				String dependents = "";
				String annualIncome = "";
				String occupation = "";
				String str = obj[0].toString().trim();
				String transctionId = obj[11].toString().trim();
				String sql1 = "select EMPLY_CD,Dependent_Name,annualIncome,occupation from hrms_wbidfc.lfc_dependent where EMPLY_CD ="
						+ str + " and tranId=" + transctionId + "";

				NativeQuery dependent = session.createNativeQuery(sql1);
				dependentdata = dependent.getResultList();

				for (Object[] obj2 : dependentdata) {

					String str2 = obj2[0].toString().trim();

					if (str.equalsIgnoreCase(str2)) {
						dependents += obj2[1].toString() + ",";
						annualIncome += obj2[2].toString() + ",";
						occupation += obj2[3].toString() + ",";
					}
				}

				dependents = replaceLast(dependents, ",", "");
				annualIncome = replaceLast(annualIncome, ",", "");
				occupation = replaceLast(occupation, ",", "");

				lm = new LfcModel();

				lm.setId(str != null ? Integer.parseInt(str) : 0);
				lm.setName(obj[1] != null ? obj[1].toString() : "");
				lm.setDependent(dependents != null ? dependents : "");
				lm.setOccupation(occupation != null ? occupation : "");
				lm.setAnnualIncome(annualIncome != null ? annualIncome : "");
				lm.setLeaveTypeStr(obj[2] != null ? obj[2].toString() : "");
				lm.setLeavefromDateStr(obj[3] != null ? obj[3].toString() : "");
				lm.setLeavetoDateStr(obj[4] != null ? obj[4].toString() : "");
				lm.setNumberofDaysStr(obj[5] != null ? obj[5].toString() : "");
				lm.setPlaceofDestination(obj[6] != null ? obj[6].toString() : "");
				lm.setAmountofAdvanceStr(obj[7] != null ? obj[7].toString() : "");
				lm.setHrStatus(obj[8] != null ? obj[8].toString() : "");
				lm.setInternalAuditStatus(obj[9] != null ? obj[9].toString() : "");
				lm.setCsStatus(obj[10] != null ? obj[10].toString() : "");

				lfcData.add(lm);

			}
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in getLfcAuditReportDaoImpl :"+e);
		}
		tx.commit();
		session.close();
		return lfcData;
	}

	@Override
	public List<LfcModel> getLfcCsReport(Integer userId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<LfcModel> lfcData = new ArrayList<LfcModel>();
		List<Object[]> result = null;
		List<Object[]> dependentdata = null;
		try {
			String sql = "select a.EMPLY_CD,(concat(ifnull(b.emply_title,''),' ',ifnull(b.emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,'')))'Name',\r\n"
					+ "		a.LEAVE_TYPE,date_format(a.leave_FROM_DT,'%d-%m-%Y')'leave_FROM_DT',\r\n"
					+ "		date_format(a.leave_TO_DT,'%d-%m-%Y')'leave_TO_DT',  a.NUMBER_OF_DAYS,a.PLACE_OF_DESTINATION,a.AMOUNT_OF_ADVANCE,\r\n"
					+ "		( case when a.Approval_Level_1='A' Then 'Approved' when a.Approval_Level_1='R' Then \r\n"
					+ "		 'Reject' when a.Approval_Level_1 OR a.Approval_Level_1='' is null Then 'Pending' else '-' end)as'Level_1 Status',\r\n"
					+ "	        ( case when a.Approval_Level_1='R' Then  'Reject'  when a.Approval_Level_2='A' Then 'Approved' when a.Approval_Level_2='R' Then 'Rejected' \r\n"
					+ "		 when a.Approval_Level_2 is null OR a.Approval_Level_2='' Then 'Pending' when a.Approval_Level_2 \r\n"
					+ "		is null OR a.Approval_Level_1='Reject' Then 'Not Applicable' else '-' end)as'Level_2 Status',\r\n"
					+ "	  ( case when a.Approval_Level_2='R' Then  'Reject'  when a.Approval_Level_3='A' Then 'Approved' when a.Approval_Level_3='R' Then 'Rejected' \r\n"
					+ "		 when a.Approval_Level_3 is null OR a.Approval_Level_3='' Then 'Pending' when a.Approval_Level_3 \r\n"
					+ "		is null OR a.Approval_Level_2='Reject' Then 'Not Applicable' else '-' end)as'Level_3 Status',\r\n"
					+ "        a.tran_id\r\n"
					+ "		from hrms_wbidfc.hrms_encashment a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
					+ "		join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "		where c.department not in('HR & ADMINISTRATION','INTERNAL AUDIT','COMPANY SECRERTARIAT') and c.status='A'\r\n"
					+ "	        union\r\n"
					+ "	        select a.EMPLY_CD,(concat(IFNULL(b.emply_title,''),' ',IFNULL(b.emply_first_name,''),' ',IFNULL(emply_middle_name,''),' ',IFNULL(emply_last_name,'')))'Name',\r\n"
					+ "		a.LEAVE_TYPE,date_format(a.leave_FROM_DT,'%d-%m-%Y')'leave_FROM_DT',\r\n"
					+ "		date_format(a.leave_TO_DT,'%d-%m-%Y')'leave_TO_DT',  a.NUMBER_OF_DAYS,a.PLACE_OF_DESTINATION,a.AMOUNT_OF_ADVANCE,\r\n"
					+ "		( case when a.Approval_Level_1='A' Then 'Approved' when a.Approval_Level_1='R' Then \r\n"
					+ "		 '-' when a.Approval_Level_1 OR a.Approval_Level_1='' is null Then '-' else '-' end)as'Level_1 Status',\r\n"
					+ "	        ( case when a.Approval_Level_1='R' Then  'Reject'  when a.Approval_Level_2='A' Then 'Approved' when a.Approval_Level_2='R' Then 'Rejected' \r\n"
					+ "		 when a.Approval_Level_2 is null OR a.Approval_Level_2='' Then 'Pending' when a.Approval_Level_2 \r\n"
					+ "		is null OR a.Approval_Level_1='Reject' Then 'Not Applicable' else '-' end)as'Level_2 Status',\r\n"
					+ "	  ( case when a.Approval_Level_2='R' Then  'Reject'  when a.Approval_Level_3='A' Then 'Approved' when a.Approval_Level_3='R' Then 'Rejected' \r\n"
					+ "		 when a.Approval_Level_3 is null OR a.Approval_Level_3='' Then 'Pending' when a.Approval_Level_3 \r\n"
					+ "		is null OR a.Approval_Level_2='Reject' Then 'Not Applicable' else '-' end)as'Level_3 Status',\r\n"
					+ "        a.tran_id\r\n"
					+ "		from hrms_wbidfc.hrms_encashment a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
					+ "		join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "		where c.department  in('HR & ADMINISTRATION') and c.status='A'\r\n" + "	        union\r\n"
					+ "	        select a.EMPLY_CD,(concat(IFNULL(b.emply_title,''),' ',IFNULL(b.emply_first_name,''),' ',IFNULL(emply_middle_name,''),' ',IFNULL(emply_last_name,'')))'Name',\r\n"
					+ "		a.LEAVE_TYPE,date_format(a.leave_FROM_DT,'%d-%m-%Y')'leave_FROM_DT',\r\n"
					+ "		date_format(a.leave_TO_DT,'%d-%m-%Y')'leave_TO_DT',  a.NUMBER_OF_DAYS,a.PLACE_OF_DESTINATION,a.AMOUNT_OF_ADVANCE,\r\n"
					+ "		( case when a.Approval_Level_1='A' Then 'Approved' when a.Approval_Level_1='R' Then \r\n"
					+ "		 '-' when a.Approval_Level_1 OR a.Approval_Level_1='' is null Then '-' else '-' end)as'Level_1 Status',\r\n"
					+ "	        ( case when a.Approval_Level_1='R' Then  'Reject'  when a.Approval_Level_2='A' Then '-' when a.Approval_Level_2='R' Then 'Rejected' \r\n"
					+ "		 when a.Approval_Level_2 is null OR a.Approval_Level_2='' Then '-' when a.Approval_Level_2 \r\n"
					+ "		is null OR a.Approval_Level_1='Reject' Then 'Not Applicable' else '-' end)as'Level_2 Status',\r\n"
					+ "	  ( case when a.Approval_Level_2='R' Then  'Reject'  when a.Approval_Level_3='A' Then 'Approved' when a.Approval_Level_3='R' Then 'Rejected' \r\n"
					+ "		 when a.Approval_Level_3 is null OR a.Approval_Level_3='' Then 'Pending' when a.Approval_Level_3 \r\n"
					+ "		is null OR a.Approval_Level_2='Reject' Then 'Not Applicable' else '-' end)as'Level_3 Status',\r\n"
					+ "        a.tran_id\r\n"
					+ "		from hrms_wbidfc.hrms_encashment a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
					+ "		join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "		where c.department  in('INTERNAL AUDIT','COMPANY SECRERTARIAT')and c.status='A'";
			NativeQuery query = session.createNativeQuery(sql);
			result = query.getResultList();

			LfcModel lm = null;

			for (Object[] obj : result) {

				String dependents = "";
				String annualIncome = "";
				String occupation = "";
				String str = obj[0].toString().trim();
				String transctionId = obj[11].toString().trim();
				String sql1 = "select EMPLY_CD,Dependent_Name,annualIncome,occupation from hrms_wbidfc.lfc_dependent where EMPLY_CD ="
						+ str + " and tranId =" + transctionId + " ";
				NativeQuery dependent = session.createNativeQuery(sql1);
				dependentdata = dependent.getResultList();

				for (Object[] obj2 : dependentdata) {

					String str2 = obj2[0].toString().trim();

					if (str.equalsIgnoreCase(str2)) {
						dependents += obj2[1].toString() + ",";
						annualIncome += obj2[2].toString() + ",";
						occupation += obj2[3].toString() + ",";
					}
				}

				dependents = replaceLast(dependents, ",", "");
				annualIncome = replaceLast(annualIncome, ",", "");
				occupation = replaceLast(occupation, ",", "");

				lm = new LfcModel();

				lm.setId(str != null ? Integer.parseInt(str) : 0);
				lm.setName(obj[1] != null ? obj[1].toString() : "");
				lm.setDependent(dependents != null ? dependents : "");
				lm.setOccupation(occupation != null ? occupation : "");
				lm.setAnnualIncome(annualIncome != null ? annualIncome : "");
				lm.setLeaveTypeStr(obj[2] != null ? obj[2].toString() : "");
				lm.setLeavefromDateStr(obj[3] != null ? obj[3].toString() : "");
				lm.setLeavetoDateStr(obj[4] != null ? obj[4].toString() : "");
				lm.setNumberofDaysStr(obj[5] != null ? obj[5].toString() : "");
				lm.setPlaceofDestination(obj[6] != null ? obj[6].toString() : "");
				lm.setAmountofAdvanceStr(obj[7] != null ? obj[7].toString() : "");
				lm.setHrStatus(obj[8] != null ? obj[8].toString() : "");
				lm.setInternalAuditStatus(obj[9] != null ? obj[9].toString() : "");
				lm.setCsStatus(obj[10] != null ? obj[10].toString() : "");

				lfcData.add(lm);

			}
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in getLfcCsReportDaoImpl :"+e);
		}
		tx.commit();
		session.close();
		return lfcData;
	}

	@Override
	public int getLFCAccess(Integer userId) {
		// System.out.println("Inside getLFCAccessDao accesssss");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		BigInteger result = null;
		BigInteger result1 = null;
		int rs = 0;
		List<Object> data = null;
		List<Object> data1 = null;
		Date date = new Date();
		String dt = date.toString();
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		DateFormat formatter1 = new SimpleDateFormat("yyy/MM/dd");
		String lfcFromDate = null;
		String st = "S";
		try {
			lfcFromDate = formatter1.format(formatter.parse(dt));
		} catch (ParseException e1) {

			e1.printStackTrace();
		}
		try {
			
			
			System.out.println(st+"------------Printing from date here --------  ********************* "+lfcFromDate);
			
			String sql = "select count(*)  FROM hrms_wbidfc.hrms_encashment a WHERE a.emply_cd = " + userId + "  AND ('"
					+ lfcFromDate + "' BETWEEN DATE_FORMAT(a.submit_date,'%d-%m-%Y')  AND a.LFC_TO_DT) and Status ='" + st + "'";
			
			
			
			
			String sql1 = "select count(*)  FROM hrms_wbidfc.hrms_lfc_surrender a WHERE a.emply_cd = " + userId
					+ "  AND ('" + lfcFromDate + "' BETWEEN DATE_FORMAT(a.submit_date,'%d-%m-%Y')  AND a.LFC_TO_DT) and Status ='" + st + "'";
			
			// DATE_FORMAT(submit_date,'%d-%m-%Y')
			
			/* old queries 5-12-23
			 * 
			String sql = "select count(*)  FROM hrms_wbidfc.hrms_encashment a WHERE a.emply_cd = " + userId + "  AND ('"
					+ lfcFromDate + "' BETWEEN a.LFC_FROM_DT AND a.LFC_TO_DT) and Status ='" + st + "'";
			
			
			
			
			String sql1 = "select count(*)  FROM hrms_wbidfc.hrms_lfc_surrender a WHERE a.emply_cd = " + userId
					+ "  AND ('" + lfcFromDate + "' BETWEEN a.LFC_FROM_DT AND a.LFC_TO_DT) and Status ='" + st + "'";
			*/
			
			SQLQuery query = session.createSQLQuery(sql);
			data = query.list();
			result = (BigInteger) data.get(0);
			int num = result.intValue();
			SQLQuery query1 = session.createSQLQuery(sql1);
			data1 = query1.list();
			result1 = (BigInteger) data1.get(0);
			int num1 = result1.intValue();
			if (num == 0 && num1 == 0) {
				rs = 0;
			} else {
				rs = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in getLFCAccess Dao method :"+e);
		}
		return rs;
	}

	@Override
	public List<LfcModel> getLfcUserReport(Integer userId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<LfcModel> lfcData = new ArrayList<LfcModel>();
		List<Object[]> result = null;
		List<Object[]> dependentdata = null;
		try {
			String sql = "select a.EMPLY_CD,concat(ifnull(emply_title,''),' ',ifnull(emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,''))'Name', \r\n"
					+ "		  a.LEAVE_TYPE,date_format(a.leave_FROM_DT,'%d-%m-%Y')'leave_FROM_DT',\r\n"
					+ "		  date_format(a.leave_TO_DT,'%d-%m-%Y')'leave_TO_DT',  a.NUMBER_OF_DAYS,a.PLACE_OF_DESTINATION,a.AMOUNT_OF_ADVANCE,  \r\n"
					+ "		  ( case when a.Approval_Level_1='A' Then 'Approved' when a.Approval_Level_1='R' Then \r\n"
					+ "		  'Reject' when a.Approval_Level_1 OR a.Approval_Level_1='' is null Then 'Pending' else '-' end)as'Level_1 Status',  \r\n"
					+ "		  ( case when a.Approval_Level_1='R' Then  'Reject'  when a.Approval_Level_2='A' Then 'Approved' when a.Approval_Level_2='R' Then 'Rejected' \r\n"
					+ "		  when a.Approval_Level_2 is null OR a.Approval_Level_2='' Then 'Pending' when a.Approval_Level_2 \r\n"
					+ "		  is null OR a.Approval_Level_1='Reject' Then 'Not Applicable' else '-' end)as'Level_2 Status',\r\n"
					+ "		 ( case when a.Approval_Level_1='R' Then 'Reject' WHEN a.Approval_Level_2='R' Then  'Reject' when a.Approval_Level_3='A' Then 'Approved' when a.Approval_Level_3='R' Then 'Rejected' \r\n"
					+ "		 when a.Approval_Level_3 is null OR a.Approval_Level_3='' Then 'Pending' when a.Approval_Level_3 \r\n"
					+ "		 is null OR a.Approval_Level_3='Reject' Then 'Not Applicable' else '-' end)as'Level_3 Status',\r\n"
					+ "         a.tran_id\r\n"
					+ "		 from hrms_wbidfc.hrms_encashment a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
					+ "		 join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "		 where a.emply_cd ='" + userId + "'";

			// String sql1 = "select EMPLY_CD,Dependent_Name,annualIncome,occupation from
			// hrms_wbidfc.lfc_dependent";

			NativeQuery query = session.createNativeQuery(sql);
			result = query.getResultList();

			// NativeQuery dependent = session.createNativeQuery(sql1);
			// dependentdata = dependent.getResultList();

			LfcModel lm = new LfcModel();

			for (Object[] obj : result) {

				String dependents = "";
				String annualIncome = "";
				String occupation = "";

				String str = obj[0].toString().trim();
				String transctionId = obj[11].toString().trim();
				int tranId = Integer.parseInt(transctionId);
				//////////////////////////////////////////////////////////////////////
				String sql1 = "select EMPLY_CD,Dependent_Name,annualIncome,occupation from hrms_wbidfc.lfc_dependent where EMPLY_CD ="
						+ str + " and tranId =" + transctionId + "";
				NativeQuery dependent = session.createNativeQuery(sql1);
				dependentdata = dependent.getResultList();

				/////////////////////////////////////////////////////////////////////
				for (Object[] obj2 : dependentdata) {

					String str2 = obj2[0].toString().trim();

					if (str.equalsIgnoreCase(str2)) {
						dependents += obj2[1].toString() + ",";
						annualIncome += obj2[2].toString() + ",";
						occupation += obj2[3].toString() + ",";
					}
				}

				dependents = replaceLast(dependents, ",", "");
				annualIncome = replaceLast(annualIncome, ",", "");
				occupation = replaceLast(occupation, ",", "");

				lm = new LfcModel();

				lm.setId(str != null ? Integer.parseInt(str) : 0);
				lm.setName(obj[1] != null ? obj[1].toString() : "");
				lm.setDependent(dependents != null ? dependents : "");
				lm.setOccupation(occupation != null ? occupation : "");
				lm.setAnnualIncome(annualIncome != null ? annualIncome : "");
				lm.setLeaveTypeStr(obj[2] != null ? obj[2].toString() : "");
				lm.setLeavefromDateStr(obj[3] != null ? obj[3].toString() : "");
				lm.setLeavetoDateStr(obj[4] != null ? obj[4].toString() : "");
				lm.setNumberofDaysStr(obj[5] != null ? obj[5].toString() : "");
				lm.setPlaceofDestination(obj[6] != null ? obj[6].toString() : "");
				lm.setAmountofAdvanceStr(obj[7] != null ? obj[7].toString() : "");
				lm.setHrStatus(obj[8] != null ? obj[8].toString() : "");
				lm.setInternalAuditStatus(obj[9] != null ? obj[9].toString() : "");
				lm.setCsStatus(obj[10] != null ? obj[10].toString() : "");

				lfcData.add(lm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lfcData;
	}

	@Override
	public BigInteger getLfcDateDiff(String leavetodate, String leavefrmdate) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		BigInteger count = null;
		List<Object> data = null;
		try {
			String sql = "select (DATEDIFF(str_to_date('" + leavetodate + "','%d-%m-%Y'),str_to_date('" + leavefrmdate
					+ "','%d-%m-%Y')))+1 total_days from dual";
			SQLQuery query = session.createSQLQuery(sql);
			data = query.list();
			count = (BigInteger) data.get(0);
			// System.out.println("Date difference is count---- "+count);
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in getLfcDateDiff Dao impl :"+e);
		}
		return count;
	}

	@Override
	public List<LfcModel> getInternalAuditorAdmin() {
		// System.out.println("Inside getInternalAuditorAdmin");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		List<LfcModel> lfcData = new ArrayList<LfcModel>();
		List<Object[]> result = null;
		List<Object[]> dependentdata = null;
		try {
			String sql = "select a.EMPLY_CD,(concat(IFNULL(b.emply_title,''),' ',IFNULL(b.emply_first_name,''),' ',IFNULL(emply_middle_name,''),' ',IFNULL(emply_last_name,'')))'Name', \r\n"
					+ "              a.LEAVE_TYPE,date_format(a.leave_FROM_DT,'%d-%m-%Y')'leave_FROM_DT',date_format(a.leave_TO_DT,'%d-%m-%Y')'leave_TO_DT',\r\n"
					+ "              a.NUMBER_OF_DAYS,a.PLACE_OF_DESTINATION,a.AMOUNT_OF_ADVANCE,a.tran_id from hrms_wbidfc.hrms_encashment a\r\n"
					+ "              join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd join hrms_wbidfc.hrms_department_detail c  on b.emply_cd=c.emply_cd where \r\n"
					+ "              department  in('HR & ADMINISTRATION') and (a.Approval_Level_2 is null OR a.Approval_Level_2='')\r\n"
					+ "";

			NativeQuery query = session.createNativeQuery(sql);
			result = query.getResultList();

			LfcModel lm = null;

			for (Object[] obj : result) {

				String dependents = "";
				String annualIncome = "";
				String occupation = "";
				String str = obj[0].toString().trim();
				String transctionId = obj[8].toString().trim();
				String sql1 = "select EMPLY_CD,Dependent_Name,annualIncome,occupation from hrms_wbidfc.lfc_dependent where EMPLY_CD ="
						+ str + " and tranId =" + transctionId + " ";

				NativeQuery dependent = session.createNativeQuery(sql1);
				dependentdata = dependent.getResultList();

				for (Object[] obj2 : dependentdata) {
					String str2 = obj2[0].toString().trim();

					if (str.equalsIgnoreCase(str2)) {
						dependents += obj2[1].toString() + ",";
						annualIncome += obj2[2].toString() + ",";
						occupation += obj2[3].toString() + ",";
					}
				}
				dependents = replaceLast(dependents, ",", "");
				annualIncome = replaceLast(annualIncome, ",", "");
				occupation = replaceLast(occupation, ",", "");

				lm = new LfcModel();

				lm.setId(str != null ? Integer.parseInt(str) : 0);
				lm.setName(obj[1] != null ? obj[1].toString() : "");
				lm.setDependent(dependents != null ? dependents : "");
				lm.setOccupation(occupation != null ? occupation : "");
				lm.setAnnualIncome(annualIncome != null ? annualIncome : "");
				lm.setLeaveTypeStr(obj[2] != null ? obj[2].toString() : "");
				lm.setLeavefromDateStr(obj[3] != null ? obj[3].toString() : "");
				lm.setLeavetoDateStr(obj[4] != null ? obj[4].toString() : "");
				lm.setNumberofDaysStr(obj[5] != null ? obj[5].toString() : "");
				lm.setPlaceofDestination(obj[6] != null ? obj[6].toString() : "");
				lm.setAmountofAdvanceStr(obj[7] != null ? obj[7].toString() : "");
				String tranId = obj[8].toString();
				lm.setTranId(tranId != null ? Integer.parseInt(tranId) : 0);

				lfcData.add(lm);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in getInternalAuditorAdmin method :" + e);
		}
		return lfcData;
	}

	// for Internal Audit Admin admin req
	@Override
	public void auditAdminremarkReq(int acceptValue, String auditAdminremark,String advanceAmountApproved, String leaveEncashmentAmountApproved) {
		// System.out.println("Inside auditAdminremarkReq");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			String sql = "update hrms_wbidfc.hrms_encashment a set a.Approval_Level_2 ='A'," + "a.Audit_Remark='"
					+ auditAdminremark + "',a.Advance_Amount_Approved='"+advanceAmountApproved+"',a.Leave_Encashment_Amount_Approved='"+leaveEncashmentAmountApproved+"' where a.tran_id='" + acceptValue + "'";
			NativeQuery query = session.createNativeQuery(sql);
			int executeUpdate = query.executeUpdate();
			tx.commit();
			// System.out.println("update status: "+executeUpdate);
			// System.out.println("Succesfully updated data");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Exception in auditAdminremarkReq :"+e);
		}
	}

	@Override
	public List<LfcModel> getCsAdmin() {
		// System.out.println("Inside getInternalAuditorAdmin");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<LfcModel> lfcData = new ArrayList<LfcModel>();
		List<Object[]> result = null;
		List<Object[]> dependentdata = null;
		try {
			String sql = "select a.EMPLY_CD,(concat(IFNULL(b.emply_title,''),' ',IFNULL(b.emply_first_name,''),' ',IFNULL(emply_middle_name,''),' ',IFNULL(emply_last_name,'')))'Name',  \r\n"
					+ "    a.LEAVE_TYPE,date_format(a.leave_FROM_DT,'%d-%m-%Y')'leave_FROM_DT',date_format(a.leave_TO_DT,'%d-%m-%Y')'leave_TO_DT', \r\n"
					+ "	a.NUMBER_OF_DAYS,a.PLACE_OF_DESTINATION,a.AMOUNT_OF_ADVANCE,a.Audit_Remark,a.tran_id  from hrms_wbidfc.hrms_encashment a \r\n"
					+ "	join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd join hrms_wbidfc.hrms_department_detail c\r\n"
					+ "	on b.emply_cd=c.emply_cd where  department in('HR & ADMINISTRATION','INTERNAL AUDIT') AND (A.Approval_Level_2 IS NULL or A.Approval_Level_2='A') and \r\n"
					+ "    (A.Approval_Level_3 IS NULL or A.Approval_Level_3='')";

			NativeQuery query = session.createNativeQuery(sql);
			result = query.getResultList();

			LfcModel lm = null;

			for (Object[] obj : result) {

				String dependents = "";
				String annualIncome = "";
				String occupation = "";
				String str = obj[0].toString().trim();
				String transctionId = obj[9].toString().trim();
				String sql1 = "select EMPLY_CD,Dependent_Name,annualIncome,occupation from hrms_wbidfc.lfc_dependent where EMPLY_CD ="
						+ str + " and tranId =" + transctionId + " ";

				NativeQuery dependent = session.createNativeQuery(sql1);
				dependentdata = dependent.getResultList();

				for (Object[] obj2 : dependentdata) {
					String str2 = obj2[0].toString().trim();

					if (str.equalsIgnoreCase(str2)) {
						dependents += obj2[1].toString() + ",";
						annualIncome += obj2[2].toString() + ",";
						occupation += obj2[3].toString() + ",";
					}
				}
				dependents = replaceLast(dependents, ",", "");
				annualIncome = replaceLast(annualIncome, ",", "");
				occupation = replaceLast(occupation, ",", "");

				lm = new LfcModel();

				lm.setId(str != null ? Integer.parseInt(str) : 0);
				lm.setName(obj[1] != null ? obj[1].toString() : "");
				lm.setDependent(dependents != null ? dependents : "");
				lm.setOccupation(occupation != null ? occupation : "");
				lm.setAnnualIncome(annualIncome != null ? annualIncome : "");
				lm.setLeaveTypeStr(obj[2] != null ? obj[2].toString() : "");
				lm.setLeavefromDateStr(obj[3] != null ? obj[3].toString() : "");
				lm.setLeavetoDateStr(obj[4] != null ? obj[4].toString() : "");
				lm.setNumberofDaysStr(obj[5] != null ? obj[5].toString() : "");
				lm.setPlaceofDestination(obj[6] != null ? obj[6].toString() : "");
				lm.setAmountofAdvanceStr(obj[7] != null ? obj[7].toString() : "");
				lm.setInternalAuditRemark(obj[8] != null ? obj[8].toString() : "");
				String tranId = obj[9].toString();
				lm.setTranId(tranId != null ? Integer.parseInt(tranId) : 0);

				lfcData.add(lm);

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return lfcData;
	}

	@Override
	public LfcBean userClass(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hrms_Dependent> getdependentList(Integer userId) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Hrms_Dependent.class);
		cr.add(Restrictions.in("emplyCd", userId));
		List<Hrms_Dependent> data = cr.list();
		return data;
	}

	@Override
	public List<String> getDependentName(String emply_cd) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<String> result = null;
		try {
			String sql = " select  concat(Dependent_Name,' (',Relation,')') from hrms_wbidfc.lfc_dependent where emply_cd="
					+ emply_cd + "";
			NativeQuery query = session.createNativeQuery(sql);
			result = query.getResultList();

		} catch (Exception e) {
			System.out.println("Exception in getDependentName Dao impl : " + e);
		}
		return result;
	}

	@Override
	public List<Object> fectchLfcApplyData(Integer userId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		return null;
	}

	@Override
	public List<LfcModel> getLfcUserSurrenderReport(Integer userId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<LfcModel> lfcData = new ArrayList<LfcModel>();
		List<Object[]> result = null;
		List<Object[]> dependentdata = null;
		try {
			String sql = "select a.EMPLY_CD,concat(ifnull(emply_title,''),' ',ifnull(emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,''))'Name',  \r\n"
					+ "  a.EncashmentLEAVE_TYPE,a.EncashmentLeave_Count,   \r\n"
					+ "  ( case when a.Approval_Level_1='A' Then 'Approved' when a.Approval_Level_1='R' Then  \r\n"
					+ "  'Reject' when a.Approval_Level_1 OR a.Approval_Level_1='' is null Then 'Pending' else '-' end)as'Level_1 Status',   \r\n"
					+ "  ( case when a.Approval_Level_1='R' Then  'Reject'  when a.Approval_Level_2='A' Then 'Approved' when a.Approval_Level_2='R' Then 'Rejected'  \r\n"
					+ "  when a.Approval_Level_2 is null OR a.Approval_Level_2='' Then 'Pending' when a.Approval_Level_2  \r\n"
					+ "  is null OR a.Approval_Level_1='Reject' Then 'Not Applicable' else '-' end)as'Level_2 Status', \r\n"
					+ " ( case when a.Approval_Level_1='R' Then 'Reject' WHEN a.Approval_Level_2='R' Then  'Reject' when a.Approval_Level_3='A' Then 'Approved' when a.Approval_Level_3='R' Then 'Rejected'  \r\n"
					+ " when a.Approval_Level_3 is null OR a.Approval_Level_3='' Then 'Pending' when a.Approval_Level_3  \r\n"
					+ " is null OR a.Approval_Level_3='Reject' Then 'Not Applicable' else '-' end)as'Level_3 Status',a.tran_id\r\n"
					+ " from hrms_wbidfc.hrms_Lfc_Surrender a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd \r\n"
					+ " join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd \r\n"
					+ " where a.emply_cd ='" + userId + "'";

			NativeQuery query = session.createNativeQuery(sql);
			result = query.getResultList();

			LfcModel lm = null;

			for (Object[] obj : result) {
				String dependents = "";
				String annualIncome = "";
				String occupation = "";
				String str = obj[0].toString().trim();
				String transctionId = obj[7].toString().trim();
				String sql1 = "select EMPLY_CD,Dependent_Name,annualIncome,occupation from hrms_wbidfc.lfc_dependent where EMPLY_CD ="
						+ str + " and tranId =" + transctionId + " ";

				NativeQuery dependent = session.createNativeQuery(sql1);
				dependentdata = dependent.getResultList();

				for (Object[] obj2 : dependentdata) {
					String str2 = obj2[0].toString().trim();

					if (str.equalsIgnoreCase(str2)) {
						dependents += obj2[1].toString() + ",";
						annualIncome += obj2[2].toString() + ",";
						occupation += obj2[3].toString() + ",";
					}
				}
				dependents = replaceLast(dependents, ",", "");
				annualIncome = replaceLast(annualIncome, ",", "");
				occupation = replaceLast(occupation, ",", "");

				lm = new LfcModel();

				lm.setId(str != null ? Integer.parseInt(str) : 0);
				lm.setName(obj[1] != null ? obj[1].toString() : "");
				lm.setDependent(dependents != null ? dependents : "");
				lm.setOccupation(occupation != null ? occupation : "");
				lm.setAnnualIncome(annualIncome != null ? annualIncome : "");
				lm.setLeaveTypeStr(obj[2] != null ? obj[2].toString() : "");
				lm.setNumberofDaysStr(obj[3] != null ? obj[3].toString() : "");
				lm.setHrStatus(obj[4] != null ? obj[4].toString() : "");
				lm.setInternalAuditStatus(obj[5] != null ? obj[5].toString() : "");
				lm.setCsStatus(obj[6] != null ? obj[6].toString() : "");

				lfcData.add(lm);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lfcData;
	}

	@Override
	public List<LfcModel> getSurrenderHrAdminData() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<LfcModel> lfcData = new ArrayList<LfcModel>();
		List<Object[]> result = null;
		List<Object[]> dependentdata = null;
		try {
			String sql = "select a.EMPLY_CD,(concat(ifnull(b.emply_title,''),' ',ifnull(b.emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,'')))'Name',\r\n"
					+ "		a.EncashmentLEAVE_TYPE,\r\n"
					+ "		a.EncashmentLeave_Count,a.tran_id from hrms_wbidfc.hrms_Lfc_Surrender a \r\n"
					+ "		 join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd join hrms_wbidfc.hrms_department_detail c \r\n"
					+ "	     on b.emply_cd=c.emply_cd where department  NOT in(TRIM('HR & ADMINISTRATION'),TRIM('INTERNAL AUDIT'),\r\n"
					+ "	     TRIM('COMPANY SECRERTARIAT')) and (a.Approval_Level_1 is null OR a.Approval_Level_1='') AND C.STATUS='A'";

			NativeQuery query = session.createNativeQuery(sql);
			result = query.getResultList();

			LfcModel lm = null;

			for (Object[] obj : result) {
				String dependents = "";
				String annualIncome = "";
				String occupation = "";
				String str = obj[0].toString().trim();
				String transctionId = obj[4].toString().trim();
				String sql1 = "select EMPLY_CD,Dependent_Name,annualIncome,occupation from hrms_wbidfc.lfc_dependent where EMPLY_CD ="
						+ str + " and tranId =" + transctionId + " ";
				NativeQuery dependent = session.createNativeQuery(sql1);
				dependentdata = dependent.getResultList();

				for (Object[] obj2 : dependentdata) {
					String str2 = obj2[0].toString().trim();

					if (str.equalsIgnoreCase(str2)) {
						dependents += obj2[1].toString() + ",";
						annualIncome += obj2[2].toString() + ",";
						occupation += obj2[3].toString() + ",";
					}
				}
				dependents = replaceLast(dependents, ",", "");
				annualIncome = replaceLast(annualIncome, ",", "");
				occupation = replaceLast(occupation, ",", "");

				lm = new LfcModel();

				lm.setId(str != null ? Integer.parseInt(str) : 0);
				lm.setName(obj[1] != null ? obj[1].toString() : "");
				lm.setDependent(dependents != null ? dependents : "");
				lm.setOccupation(occupation != null ? occupation : "");
				lm.setAnnualIncome(annualIncome != null ? annualIncome : "");
				lm.setLeaveTypeStr(obj[2] != null ? obj[2].toString() : "");
				lm.setNumberofDaysStr(obj[3] != null ? obj[3].toString() : "");
				String tranId = obj[4].toString();
				lm.setTranId(tranId != null ? Integer.parseInt(tranId) : 0);

				lfcData.add(lm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lfcData;
	}

	@Override
	public int hrSurAcceptReq(int acceptValue, String hradminremark) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int result = 0;
		try {
			String sql = "update hrms_wbidfc.hrms_Lfc_Surrender a set a.Approval_Level_1 ='A', a.Hr_Remark='"
					+ hradminremark + "' where a.tran_id='" + acceptValue + "'";
			NativeQuery query = session.createNativeQuery(sql);
			result = query.executeUpdate();
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return result;
	}

	@Override
	public int hrSurRejectReq(int rejectvalue, String hradminremark) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int executeUpdate = 0;
		String status = "R";
		try {
			String sql = "update hrms_wbidfc.hrms_Lfc_Surrender a set a.Approval_Level_1 ='R',a.Hr_Remark='"
					+ hradminremark + "',a.Status='" + status + "' where a.tran_id='" + rejectvalue + "'";

			NativeQuery query = session.createNativeQuery(sql);
			executeUpdate = query.executeUpdate();

			String sql1 = "select EMPLY_CD from hrms_wbidfc.hrms_Lfc_Surrender where tran_id='" + rejectvalue + "'";

			NativeQuery empcd_query = session.createNativeQuery(sql1);
			int emp_cd = (int) empcd_query.uniqueResult();

			String sql2 = "update  hrms_wbidfc.lfc_dependent set Status='R' where EMPLY_CD ='" + emp_cd + "'";
			NativeQuery deletedata = session.createNativeQuery(sql2);
			int deleteupdate = deletedata.executeUpdate();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in acceptReqImpl :" + e);
		}
		return executeUpdate;
	}

	@Override
	public List<LfcModel> getLfcSurrenderHrReport() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		List<LfcModel> lfcData = new ArrayList<LfcModel>();
		List<Object[]> result = null;
		List<Object[]> dependentdata = null;
		try {
			String sql = "select a.EMPLY_CD,(concat(ifnull(b.emply_title,''),' ',ifnull(b.emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,'')))'Name',\r\n"
					+ "		 a.EncashmentLEAVE_TYPE,\r\n"
					+ "		a.EncashmentLeave_Count,( case when a.Approval_Level_1='A' Then 'Approved'\r\n"
					+ "		when a.Approval_Level_1='R' Then 'Rejected' when a.Approval_Level_1 is null OR a.Approval_Level_1=''\r\n"
					+ "		Then 'Pending' else '-' end)as'Approval_Level_1',\r\n"
					+ "		( case when a.Approval_Level_1='R' Then '-' when a.Approval_Level_2='A' Then 'Approved'\r\n"
					+ "		when a.Approval_Level_2='R' Then 'Rejected' when a.Approval_Level_2 is null OR a.Approval_Level_2=''\r\n"
					+ "		Then 'Pending' else '-' end)as'Approval_Level_2',\r\n"
					+ "		( case when a.Approval_Level_1='R' Then '-' when a.Approval_Level_3='A' Then 'Approved'\r\n"
					+ "		when a.Approval_Level_3='R' Then 'Rejected' when a.Approval_Level_3 is null OR a.Approval_Level_3=''\r\n"
					+ "		Then 'Pending' else '-' end)as'Approval_Level_3',a.tran_id\r\n"
					+ "		from hrms_wbidfc.hrms_Lfc_Surrender a join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd join\r\n"
					+ "		hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "		where c.department not in('HR & ADMINISTRATION','INTERNAL AUDIT','COMPANY SECRERTARIAT') AND C.STATUS='A'\r\n"
					+ "		union all\r\n"
					+ "		select a.EMPLY_CD,(concat(IFNULL(b.emply_title,''),' ',IFNULL(b.emply_first_name,''),' ',IFNULL(emply_middle_name,''),' ',IFNULL(emply_last_name,'')))'Name',\r\n"
					+ "		 a.EncashmentLEAVE_TYPE,\r\n"
					+ "		a.EncashmentLeave_Count,( case when a.Approval_Level_1='A' Then 'Approved'\r\n"
					+ "		when a.Approval_Level_1='R' Then '-' when a.Approval_Level_1 is null OR a.Approval_Level_1=''\r\n"
					+ "		Then '-' else '-' end)as'Approval_Level_1',\r\n"
					+ "		( case when a.Approval_Level_1='R' Then '-' when a.Approval_Level_2='A' Then 'Approved'\r\n"
					+ "		when a.Approval_Level_2='R' Then 'Rejected' when a.Approval_Level_2 is null OR a.Approval_Level_2=''\r\n"
					+ "		Then 'Pending' else '-' end)as'Approval_Level_2',\r\n"
					+ "		( case when a.Approval_Level_1='R' Then '-' when a.Approval_Level_3='A' Then 'Approved'\r\n"
					+ "		when a.Approval_Level_3='R' Then 'Rejected' when a.Approval_Level_3 is null OR a.Approval_Level_3=''\r\n"
					+ "		Then 'Pending' else '-' end)as'Approval_Level_3',a.tran_id\r\n"
					+ "		from hrms_wbidfc.hrms_Lfc_Surrender a join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd join\r\n"
					+ "		hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "		where c.department in('HR & ADMINISTRATION')AND  C.STATUS='A'";

			NativeQuery query = session.createNativeQuery(sql);
			result = query.getResultList();

			LfcModel lm = null;

			for (Object[] obj : result) {
				String dependents = "";
				String annualIncome = "";
				String occupation = "";
				String str = obj[0].toString().trim();
				String transctionId = obj[7].toString().trim();
				String sql1 = "select EMPLY_CD,Dependent_Name,annualIncome,occupation from hrms_wbidfc.lfc_dependent where EMPLY_CD ="
						+ str + " and tranId =" + transctionId + " ";

				NativeQuery dependent = session.createNativeQuery(sql1);
				dependentdata = dependent.getResultList();

				for (Object[] obj2 : dependentdata) {
					String str2 = obj2[0].toString().trim();

					if (str.equalsIgnoreCase(str2)) {
						dependents += obj2[1].toString() + ",";
						annualIncome += obj2[2].toString() + ",";
						occupation += obj2[3].toString() + ",";
					}
				}
				dependents = replaceLast(dependents, ",", "");
				annualIncome = replaceLast(annualIncome, ",", "");
				occupation = replaceLast(occupation, ",", "");

				lm = new LfcModel();

				lm.setId(str != null ? Integer.parseInt(str) : 0);
				lm.setName(obj[1] != null ? obj[1].toString() : "");
				lm.setDependent(dependents != null ? dependents : "");
				lm.setOccupation(occupation != null ? occupation : "");
				lm.setAnnualIncome(annualIncome != null ? annualIncome : "");
				lm.setLeaveTypeStr(obj[2] != null ? obj[2].toString() : "");
				lm.setNumberofDaysStr(obj[3] != null ? obj[3].toString() : "");
				lm.setHrStatus(obj[4] != null ? obj[4].toString() : "");
				lm.setInternalAuditStatus(obj[5] != null ? obj[5].toString() : "");
				lm.setCsStatus(obj[6] != null ? obj[6].toString() : "");

				lfcData.add(lm);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in getLfcSurrenderHrReport :" + e);
		}
		return lfcData;
	}

	@Override
	public List<LfcModel> getSurrenderInternalAuditorAdmin() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		List<LfcModel> lfcData = new ArrayList<LfcModel>();
		List<Object[]> result = null;
		List<Object[]> dependentdata = null;
		try {
			String sql = "select a.EMPLY_CD,(concat(ifnull(b.emply_title,''),' ',ifnull(b.emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,'')))'Name',\r\n"
					+ " a.EncashmentLEAVE_TYPE,\r\n"
					+ " a.EncashmentLeave_Count,a.Hr_Remark,a.tran_id from hrms_wbidfc.hrms_Lfc_Surrender a join\r\n"
					+ " hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd where  a.Approval_Level_1='A'and Approval_Level_2 is null OR\r\n"
					+ " a.Approval_Level_2=''";

			NativeQuery query = session.createNativeQuery(sql);
			result = query.getResultList();

			LfcModel lm = null;

			for (Object[] obj : result) {
				String dependents = "";
				String annualIncome = "";
				String occupation = "";
				String str = obj[0].toString().trim();
				String transctionId = obj[5].toString().trim();
				String sql1 = "select EMPLY_CD,Dependent_Name,annualIncome,occupation from hrms_wbidfc.lfc_dependent where EMPLY_CD ="
						+ str + " and tranId =" + transctionId + " ";
				NativeQuery dependent = session.createNativeQuery(sql1);
				dependentdata = dependent.getResultList();

				for (Object[] obj2 : dependentdata) {
					String str2 = obj2[0].toString().trim();

					if (str.equalsIgnoreCase(str2)) {
						dependents += obj2[1].toString() + ",";
						annualIncome += obj2[2].toString() + ",";
						occupation += obj2[3].toString() + ",";
					}
				}
				dependents = replaceLast(dependents, ",", "");
				annualIncome = replaceLast(annualIncome, ",", "");
				occupation = replaceLast(occupation, ",", "");

				lm = new LfcModel();

				lm.setId(str != null ? Integer.parseInt(str) : 0);
				lm.setName(obj[1] != null ? obj[1].toString() : "");
				lm.setDependent(dependents != null ? dependents : "");
				lm.setOccupation(occupation != null ? occupation : "");
				lm.setAnnualIncome(annualIncome != null ? annualIncome : "");
				lm.setLeaveTypeStr(obj[2] != null ? obj[2].toString() : "");
				lm.setNumberofDaysStr(obj[3] != null ? obj[3].toString() : "");
				lm.setHrRemark(obj[4] != null ? obj[4].toString() : "");
				String tranId = obj[5].toString();
				lm.setTranId(tranId != null ? Integer.parseInt(tranId) : 0);

				lfcData.add(lm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lfcData;
	}

	@Override
	public int surInternalAdminAccept(int acceptValue, String auditAdminremark) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int executeUpdate = 0;
		try {
			String sql = "update hrms_wbidfc.hrms_Lfc_Surrender a set a.Approval_Level_2 ='A'," + "a.Audit_Remark='"
					+ auditAdminremark + "' where a.tran_id='" + acceptValue + "'";
			NativeQuery query = session.createNativeQuery(sql);
			executeUpdate = query.executeUpdate();
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return executeUpdate;
	}

	@Override
	public int surInternalrejectReq(int rejectvalue, String auditremark) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int executeUpdate = 0;
		String status = "R";
		try {
			String sql = "update hrms_wbidfc.hrms_Lfc_Surrender a set a.Approval_Level_2 ='R',a.Audit_Remark='"
					+ auditremark + "',a.Status='" + status + "' where a.tran_id='" + rejectvalue + "'";
			NativeQuery query = session.createNativeQuery(sql);
			executeUpdate = query.executeUpdate();

			String sql1 = "select EMPLY_CD from hrms_wbidfc.hrms_Lfc_Surrender where tran_id='" + rejectvalue + "'";

			NativeQuery empcd_query = session.createNativeQuery(sql1);
			int emp_cd = (int) empcd_query.uniqueResult();

			String sql2 = "update  hrms_wbidfc.lfc_dependent set Status='R' where EMPLY_CD ='" + emp_cd + "'";
			NativeQuery deletedata = session.createNativeQuery(sql2);
			int deleteupdate = deletedata.executeUpdate();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return executeUpdate;
	}

	@Override
	public List<LfcModel> getSurAuditReport() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<LfcModel> lfcData = new ArrayList<LfcModel>();
		List<Object[]> result = null;
		List<Object[]> dependentdata = null;
		try {
			String sql = "select a.EMPLY_CD,(concat(ifnull(b.emply_title,''),' ',ifnull(b.emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,'')))'Name', \r\n"
					+ "		a.EncashmentLEAVE_TYPE,\r\n" + "		  a.EncashmentLeave_Count,  \r\n"
					+ "		( case when a.Approval_Level_1='A' Then 'Approved' when a.Approval_Level_1='R' Then\r\n"
					+ "		 'Reject' when a.Approval_Level_1 OR a.Approval_Level_1='' is null Then 'Pending' else '-' end)as'Level_1 Status', \r\n"
					+ "	     ( case when a.Approval_Level_1='R' Then  'Reject'  when a.Approval_Level_2='A' Then 'Approved' when a.Approval_Level_2='R' Then 'Rejected'\r\n"
					+ "		 when a.Approval_Level_2 is null OR a.Approval_Level_2='' Then 'Pending' when a.Approval_Level_2 \r\n"
					+ "		is null OR a.Approval_Level_1='Reject' Then 'Not Applicable' else '-' end)as'Level_2 Status', \r\n"
					+ "	           ( case when a.Approval_Level_2='R' Then  'Reject'  when a.Approval_Level_3='A' Then 'Approved' when a.Approval_Level_3='R' Then 'Rejected'\r\n"
					+ "			 when a.Approval_Level_3 is null OR a.Approval_Level_3='' Then 'Pending' when a.Approval_Level_3 \r\n"
					+ "			is null OR a.Approval_Level_2='Reject' Then 'Not Applicable' else '-' end)as'Level_3 Status',a.tran_id\r\n"
					+ "			from hrms_wbidfc.hrms_Lfc_Surrender a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
					+ "			join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd \r\n"
					+ "			where c.department not in('HR & ADMINISTRATION','INTERNAL AUDIT','COMPANY SECRERTARIAT') and c.status='A'\r\n"
					+ "	         union\r\n"
					+ "	         select a.EMPLY_CD,(concat(IFNULL(b.emply_title,''),' ',IFNULL(b.emply_first_name,''),' ',IFNULL(emply_middle_name,''),' ',IFNULL(emply_last_name,'')))'Name', \r\n"
					+ "			a.EncashmentLEAVE_TYPE, \r\n" + "			 a.EncashmentLeave_Count, \r\n"
					+ "			( case when a.Approval_Level_1='A' Then 'Approved' when a.Approval_Level_1='R' Then \r\n"
					+ "			 '-' when a.Approval_Level_1 OR a.Approval_Level_1='' is null Then '-' else '-' end)as'Level_1 Status', \r\n"
					+ "	         ( case when a.Approval_Level_1='R' Then  'Reject'  when a.Approval_Level_2='A' Then 'Approved' when a.Approval_Level_2='R' Then 'Rejected' \r\n"
					+ "			 when a.Approval_Level_2 is null OR a.Approval_Level_2='' Then 'Pending' when a.Approval_Level_2 \r\n"
					+ "			is null OR a.Approval_Level_1='Reject' Then 'Not Applicable' else '-' end)as'Level_2 Status',\r\n"
					+ "	           ( case when a.Approval_Level_2='R' Then  'Reject'  when a.Approval_Level_3='A' Then 'Approved' when a.Approval_Level_3='R' Then 'Rejected' \r\n"
					+ "			 when a.Approval_Level_3 is null OR a.Approval_Level_3='' Then 'Pending' when a.Approval_Level_3 \r\n"
					+ "			is null OR a.Approval_Level_2='Reject' Then 'Not Applicable' else '-' end)as'Level_3 Status',a.tran_id\r\n"
					+ "			from hrms_wbidfc.hrms_Lfc_Surrender a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd \r\n"
					+ "			join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "			where c.department  in('HR & ADMINISTRATION') and c.status='A'\r\n"
					+ "	         union\r\n"
					+ "	         select a.EMPLY_CD,(concat(IFNULL(b.emply_title,''),' ',IFNULL(b.emply_first_name,''),' ',IFNULL(emply_middle_name,''),' ',IFNULL(emply_last_name,'')))'Name', \r\n"
					+ "			a.EncashmentLEAVE_TYPE, \r\n" + "			 a.EncashmentLeave_Count,   \r\n"
					+ "			( case when a.Approval_Level_1='A' Then 'Approved' when a.Approval_Level_1='R' Then\r\n"
					+ "			 '-' when a.Approval_Level_1 OR a.Approval_Level_1='' is null Then '-' else '-' end)as'Level_1 Status', \r\n"
					+ "	           ( case when a.Approval_Level_1='R' Then  'Reject'  when a.Approval_Level_2='A' Then '-' when a.Approval_Level_2='R' Then 'Rejected'\r\n"
					+ "			 when a.Approval_Level_2 is null OR a.Approval_Level_2='' Then '-' when a.Approval_Level_2\r\n"
					+ "			is null OR a.Approval_Level_1='Reject' Then 'Not Applicable' else '-' end)as'Level_2 Status',\r\n"
					+ "	     ( case when a.Approval_Level_2='R' Then  'Reject'  when a.Approval_Level_3='A' Then 'Approved' when a.Approval_Level_3='R' Then 'Rejected'\r\n"
					+ "			 when a.Approval_Level_3 is null OR a.Approval_Level_3='' Then 'Pending' when a.Approval_Level_3 \r\n"
					+ "			is null OR a.Approval_Level_2='Reject' Then 'Not Applicable' else '-' end)as'Level_3 Status',a.tran_id\r\n"
					+ "			from hrms_wbidfc.hrms_Lfc_Surrender a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
					+ "			join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "			where c.department  in('INTERNAL AUDIT') and c.status='A'";

			NativeQuery query = session.createNativeQuery(sql);
			result = query.getResultList();

			LfcModel lm = null;

			for (Object[] obj : result) {
				String dependents = "";
				String annualIncome = "";
				String occupation = "";
				String str = obj[0].toString().trim();
				String transctionId = obj[7].toString().trim();
				String sql1 = "select EMPLY_CD,Dependent_Name,annualIncome,occupation from hrms_wbidfc.lfc_dependent where EMPLY_CD ="
						+ str + " and tranId =" + transctionId + "";
				NativeQuery dependent = session.createNativeQuery(sql1);
				dependentdata = dependent.getResultList();

				for (Object[] obj2 : dependentdata) {
					String str2 = obj2[0].toString().trim();

					if (str.equalsIgnoreCase(str2)) {
						dependents += obj2[1].toString() + ",";
						annualIncome += obj2[2].toString() + ",";
						occupation += obj2[3].toString() + ",";
					}
				}
				dependents = replaceLast(dependents, ",", "");
				annualIncome = replaceLast(annualIncome, ",", "");
				occupation = replaceLast(occupation, ",", "");

				lm = new LfcModel();

				lm.setId(str != null ? Integer.parseInt(str) : 0);
				lm.setName(obj[1] != null ? obj[1].toString() : "");
				lm.setDependent(dependents != null ? dependents : "");
				lm.setOccupation(occupation != null ? occupation : "");
				lm.setAnnualIncome(annualIncome != null ? annualIncome : "");
				lm.setLeaveTypeStr(obj[2] != null ? obj[2].toString() : "");
				lm.setNumberofDaysStr(obj[3] != null ? obj[3].toString() : "");
				lm.setHrStatus(obj[4] != null ? obj[4].toString() : "");
				lm.setInternalAuditStatus(obj[5] != null ? obj[5].toString() : "");
				lm.setCsStatus(obj[6] != null ? obj[6].toString() : "");

				lfcData.add(lm);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lfcData;
	}

	@Override
	public List<LfcModel> getSurrenderCsAdmindata() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<LfcModel> lfcData = new ArrayList<LfcModel>();
		List<Object[]> result = null;
		List<Object[]> dependentdata = null;
		try {
			String sql = "select a.EMPLY_CD,(concat(ifnull(b.emply_title,''),' ',ifnull(b.emply_first_name,''),' ',\r\n"
					+ "	ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,'')))'Name',\r\n"
					+ "	    a.EncashmentLEAVE_TYPE,\r\n"
					+ "	    a.EncashmentLeave_Count,a.Audit_Remark,a.tran_id\r\n"
					+ "	 from hrms_wbidfc.hrms_Lfc_Surrender a\r\n"
					+ "	    join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd join\r\n"
					+ "	 hrms_wbidfc.hrms_department_detail c on a.emply_cd=c.emply_cd\r\n"
					+ "	    where  a.Approval_Level_2='A'and  ( Approval_Level_3 is null OR a.Approval_Level_3='')\r\n"
					+ "	 and c.department not in('HR & ADMINISTRATION','INTERNAL AUDIT','COMPANY SECRERTARIAT')";

			NativeQuery query = session.createNativeQuery(sql);
			result = query.getResultList();

			LfcModel lm = null;

			for (Object[] obj : result) {
				String dependents = "";
				String annualIncome = "";
				String occupation = "";
				String str = obj[0].toString().trim();
				String transctionId = obj[5].toString().trim();
				String sql1 = "select EMPLY_CD,Dependent_Name,annualIncome,occupation from hrms_wbidfc.lfc_dependent where EMPLY_CD ="
						+ str + " and tranId =" + transctionId + "";
				NativeQuery dependent = session.createNativeQuery(sql1);
				dependentdata = dependent.getResultList();

				for (Object[] obj2 : dependentdata) {
					String str2 = obj2[0].toString().trim();

					if (str.equalsIgnoreCase(str2)) {
						dependents += obj2[1].toString() + ",";
						annualIncome += obj2[2].toString() + ",";
						occupation += obj2[3].toString() + ",";
					}
				}
				dependents = replaceLast(dependents, ",", "");
				annualIncome = replaceLast(annualIncome, ",", "");
				occupation = replaceLast(occupation, ",", "");

				lm = new LfcModel();

				lm.setId(str != null ? Integer.parseInt(str) : 0);
				lm.setName(obj[1] != null ? obj[1].toString() : "");
				lm.setDependent(dependents != null ? dependents : "");
				lm.setOccupation(occupation != null ? occupation : "");
				lm.setAnnualIncome(annualIncome != null ? annualIncome : "");
				lm.setLeaveTypeStr(obj[2] != null ? obj[2].toString() : "");
				lm.setNumberofDaysStr(obj[3] != null ? obj[3].toString() : "");
				lm.setInternalAuditRemark(obj[4] != null ? obj[4].toString() : "");
				String tranId = obj[5].toString();
				lm.setTranId(tranId != null ? Integer.parseInt(tranId) : 0);

				lfcData.add(lm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lfcData;
	}

	@Override
	public int surCSacceptReq(int acceptValue, String cSremark, String empCode, String checkerEin) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int executeUpdate = 0;
		List<Object[]> empdetails = null;

		try {
			LocalDate myObj = LocalDate.now();
			String sql = "update hrms_wbidfc.hrms_Lfc_Surrender a set a.Approval_Level_3 ='A'," + "a.Cs_Remark='"
					+ cSremark + "' where a.tran_id='" + acceptValue + "'";
			String sql2 = "select EMPLY_CD,LFC_FROM_DT,LFC_TO_DT,date(SUBMIT_DATE) FROM hrms_wbidfc.hrms_Lfc_Surrender where tran_id='"
					+ acceptValue + "'";
			NativeQuery query1 = session.createNativeQuery(sql);
			executeUpdate = query1.executeUpdate();

			NativeQuery dependent = session.createNativeQuery(sql2);
			empdetails = dependent.getResultList();
			int empcd = (int) empdetails.get(0)[0];
			System.out.println(empcd);

			Date lfd = (Date) empdetails.get(0)[1];

			SimpleDateFormat sf3 = new SimpleDateFormat("yyyyMM");
			String fromDate3 = sf3.format(lfd);
			DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("uuuuMM");
			YearMonth appliedYearMonth3 = YearMonth.parse(fromDate3, dtf3);
			int lfcFromDate3 = Integer.parseInt(appliedYearMonth3.format(DateTimeFormatter.ofPattern("yyyyMM")));

			Date ltd = (Date) empdetails.get(0)[2];

			SimpleDateFormat sf4 = new SimpleDateFormat("yyyyMM");
			String fromDate4 = sf4.format(ltd);
			DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("uuuuMM");
			YearMonth appliedYearMonth4 = YearMonth.parse(fromDate4, dtf4);
			int lfcToDate4 = Integer.parseInt(appliedYearMonth4.format(DateTimeFormatter.ofPattern("yyyyMM")));

			float amount = 0.0f;// (float) amt;

			Date makerDate = (Date) empdetails.get(0)[3];

			Date checkerDate = java.sql.Date.valueOf(myObj);

			String makerFlag = "Y";

			String checkerFlag = "Y";

			String payRecoveryCode = "P052";

			int payRecoverySerial = 11;

			String remarks = "";

			int adj_of_yrmth = 0;

			int ln_snctn_no = 0;

			int rmtng_offc_cd = 0;

			String dscrptn = "";

			String sql1 = "insert into hrms_payroll_wb.eslsaltran (EMPLY_CD,PAY_RCVRY_CD,PAY_RCVRY_SRL,VLD_FRM_YRMTH,VLD_TO_YRMTH,ADJ_OF_YRMTH,DSCRPTN,AMT,LN_SNCTN_NO,RMTNG_OFFC_CD,MAKER_EIN,MAKER_FLG,MAKER_DATE,CHECKER_EIN,CHECKER_FLG,CHECKER_DATE,REMARKS) "
					+ "values(:emply_cd, :pay_rcvry_cd, :pay_rcvry_srl, :vld_frm_yrmth, :vld_to_yrmth, :adj_of_yrmth, :dscrptn, :amt, :ln_snctn_no, :rmtng_offc_cd, :maker_ein, :maker_flg,  :maker_date, :checker_ein, :checker_flg, :checker_date, :remarks)";
			SQLQuery query = session.createSQLQuery(sql1);
			query.setParameter("emply_cd", empcd);
			query.setParameter("pay_rcvry_cd", payRecoveryCode);
			query.setParameter("pay_rcvry_srl", payRecoverySerial);
			query.setParameter("vld_frm_yrmth", lfcFromDate3);
			query.setParameter("vld_to_yrmth", lfcToDate4);
			query.setParameter("adj_of_yrmth", adj_of_yrmth);
			query.setParameter("amt", amount);
			query.setParameter("ln_snctn_no", ln_snctn_no);
			query.setParameter("dscrptn", dscrptn);
			query.setParameter("rmtng_offc_cd", rmtng_offc_cd);
			query.setParameter("maker_ein", empCode);
			query.setParameter("maker_flg", makerFlag);
			query.setParameter("maker_date", makerDate);
			query.setParameter("checker_ein", checkerEin);
			query.setParameter("checker_flg", checkerFlag);
			query.setParameter("checker_date", checkerDate);
			query.setParameter("remarks", remarks);

			query.executeUpdate();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return executeUpdate;
	}

	@Override
	public int surCSrejectReq(int rejectvalue, String cSremark) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int executeUpdate = 0;
		String status = "R";
		try {
			String sql = "update hrms_wbidfc.hrms_Lfc_Surrender a set a.Approval_Level_3 ='R',a.Cs_Remark='" + cSremark
					+ "',a.Status='" + status + "' where a.tran_id='" + rejectvalue + "'";
			NativeQuery query = session.createNativeQuery(sql);
			executeUpdate = query.executeUpdate();

			String sql1 = "select EMPLY_CD from hrms_wbidfc.hrms_Lfc_Surrender where tran_id='" + rejectvalue + "'";

			NativeQuery empcd_query = session.createNativeQuery(sql1);
			int emp_cd = (int) empcd_query.uniqueResult();

			String sql2 = "update  hrms_wbidfc.lfc_dependent set Status='R' where EMPLY_CD ='" + emp_cd + "'";
			NativeQuery deletedata = session.createNativeQuery(sql2);
			int deleteupdate = deletedata.executeUpdate();

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return executeUpdate;
	}

	@Override
	public List<LfcModel> getSurCsReport() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<LfcModel> lfcData = new ArrayList<LfcModel>();
		List<Object[]> result = null;
		List<Object[]> dependentdata = null;
		try {
			String sql = "select a.EMPLY_CD,(concat(ifnull(b.emply_title,''),' ',ifnull(b.emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,'')))'Name',\r\n"
					+ "		a.EncashmentLEAVE_TYPE, a.EncashmentLeave_Count, \r\n"
					+ "		( case when a.Approval_Level_1='A' Then 'Approved' when a.Approval_Level_1='R' Then \r\n"
					+ "		 'Reject' when a.Approval_Level_1 OR a.Approval_Level_1='' is null Then 'Pending' else '-' end)as'Level_1 Status',\r\n"
					+ "	        ( case when a.Approval_Level_1='R' Then  'Reject'  when a.Approval_Level_2='A' Then 'Approved' when a.Approval_Level_2='R' Then 'Rejected' \r\n"
					+ "		 when a.Approval_Level_2 is null OR a.Approval_Level_2='' Then 'Pending' when a.Approval_Level_2 \r\n"
					+ "		is null OR a.Approval_Level_1='Reject' Then 'Not Applicable' else '-' end)as'Level_2 Status',\r\n"
					+ "	  ( case when a.Approval_Level_2='R' Then  'Reject'  when a.Approval_Level_3='A' Then 'Approved' when a.Approval_Level_3='R' Then 'Rejected' \r\n"
					+ "		 when a.Approval_Level_3 is null OR a.Approval_Level_3='' Then 'Pending' when a.Approval_Level_3 \r\n"
					+ "		is null OR a.Approval_Level_2='Reject' Then 'Not Applicable' else '-' end)as'Level_3 Status',a.tran_id\r\n"
					+ "		from hrms_wbidfc.hrms_Lfc_Surrender a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
					+ "		join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "		where c.department not in('HR & ADMINISTRATION','INTERNAL AUDIT','COMPANY SECRERTARIAT') and c.status='A'\r\n"
					+ "	        union\r\n"
					+ "	        select a.EMPLY_CD,(concat(IFNULL(b.emply_title,''),' ',IFNULL(b.emply_first_name,''),' ',IFNULL(emply_middle_name,''),' ',IFNULL(emply_last_name,'')))'Name',\r\n"
					+ "		a.EncashmentLEAVE_TYPE, a.EncashmentLeave_Count,\r\n"
					+ "		( case when a.Approval_Level_1='A' Then 'Approved' when a.Approval_Level_1='R' Then \r\n"
					+ "		 '-' when a.Approval_Level_1 OR a.Approval_Level_1='' is null Then '-' else '-' end)as'Level_1 Status',\r\n"
					+ "	        ( case when a.Approval_Level_1='R' Then  'Reject'  when a.Approval_Level_2='A' Then 'Approved' when a.Approval_Level_2='R' Then 'Rejected' \r\n"
					+ "		 when a.Approval_Level_2 is null OR a.Approval_Level_2='' Then 'Pending' when a.Approval_Level_2 \r\n"
					+ "		is null OR a.Approval_Level_1='Reject' Then 'Not Applicable' else '-' end)as'Level_2 Status',\r\n"
					+ "	  ( case when a.Approval_Level_2='R' Then  'Reject'  when a.Approval_Level_3='A' Then 'Approved' when a.Approval_Level_3='R' Then 'Rejected' \r\n"
					+ "		 when a.Approval_Level_3 is null OR a.Approval_Level_3='' Then 'Pending' when a.Approval_Level_3 \r\n"
					+ "		is null OR a.Approval_Level_2='Reject' Then 'Not Applicable' else '-' end)as'Level_3 Status',a.tran_id\r\n"
					+ "		from hrms_wbidfc.hrms_Lfc_Surrender a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
					+ "		join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "		where c.department  in('HR & ADMINISTRATION') and c.status='A'\r\n" + "	        union\r\n"
					+ "	        select a.EMPLY_CD,(concat(IFNULL(b.emply_title,''),' ',IFNULL(b.emply_first_name,''),' ',IFNULL(emply_middle_name,''),' ',IFNULL(emply_last_name,'')))'Name',\r\n"
					+ "		a.EncashmentLEAVE_TYPE, a.EncashmentLeave_Count,\r\n"
					+ "		( case when a.Approval_Level_1='A' Then 'Approved' when a.Approval_Level_1='R' Then \r\n"
					+ "		 '-' when a.Approval_Level_1 OR a.Approval_Level_1='' is null Then '-' else '-' end)as'Level_1 Status',\r\n"
					+ "	        ( case when a.Approval_Level_1='R' Then  'Reject'  when a.Approval_Level_2='A' Then '-' when a.Approval_Level_2='R' Then 'Rejected' \r\n"
					+ "		 when a.Approval_Level_2 is null OR a.Approval_Level_2='' Then '-' when a.Approval_Level_2 \r\n"
					+ "		is null OR a.Approval_Level_1='Reject' Then 'Not Applicable' else '-' end)as'Level_2 Status',\r\n"
					+ "	  ( case when a.Approval_Level_2='R' Then  'Reject'  when a.Approval_Level_3='A' Then 'Approved' when a.Approval_Level_3='R' Then 'Rejected' \r\n"
					+ "		 when a.Approval_Level_3 is null OR a.Approval_Level_3='' Then 'Pending' when a.Approval_Level_3 \r\n"
					+ "		is null OR a.Approval_Level_2='Reject' Then 'Not Applicable' else '-' end)as'Level_3 Status',a.tran_id\r\n"
					+ "		from hrms_wbidfc.hrms_Lfc_Surrender a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
					+ "		join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "		where c.department  in('INTERNAL AUDIT','COMPANY SECRERTARIAT')and c.status='A'";

			NativeQuery query = session.createNativeQuery(sql);
			result = query.getResultList();

			LfcModel lm = null;

			for (Object[] obj : result) {
				String dependents = "";
				String annualIncome = "";
				String occupation = "";
				String str = obj[0].toString().trim();
				String transctionId = obj[7].toString().trim();
				String sql1 = "select EMPLY_CD,Dependent_Name,annualIncome,occupation from hrms_wbidfc.lfc_dependent where EMPLY_CD ="
						+ str + " and tranId =" + transctionId + "";
				NativeQuery dependent = session.createNativeQuery(sql1);
				dependentdata = dependent.getResultList();

				for (Object[] obj2 : dependentdata) {
					String str2 = obj2[0].toString().trim();

					if (str.equalsIgnoreCase(str2)) {
						dependents += obj2[1].toString() + ",";
						annualIncome += obj2[2].toString() + ",";
						occupation += obj2[3].toString() + ",";
					}
				}
				dependents = replaceLast(dependents, ",", "");
				annualIncome = replaceLast(annualIncome, ",", "");
				occupation = replaceLast(occupation, ",", "");

				lm = new LfcModel();

				lm.setId(str != null ? Integer.parseInt(str) : 0);
				lm.setName(obj[1] != null ? obj[1].toString() : "");
				lm.setDependent(dependents != null ? dependents : "");
				lm.setOccupation(occupation != null ? occupation : "");
				lm.setAnnualIncome(annualIncome != null ? annualIncome : "");
				lm.setLeaveTypeStr(obj[2] != null ? obj[2].toString() : "");
				lm.setNumberofDaysStr(obj[3] != null ? obj[3].toString() : "");
				lm.setHrStatus(obj[4] != null ? obj[4].toString() : "");
				lm.setInternalAuditStatus(obj[5] != null ? obj[5].toString() : "");
				lm.setCsStatus(obj[6] != null ? obj[6].toString() : "");

				lfcData.add(lm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lfcData;
	}

	@Override
	public List<LfcModel> getSurrenderInternalAuditorAdminadmin() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<LfcModel> lfcData = new ArrayList<LfcModel>();
		List<Object[]> result = null;
		List<Object[]> dependentdata = null;
		try {
			String sql = "select a.EMPLY_CD,(concat(IFNULL(b.emply_title,''),' ',IFNULL(b.emply_first_name,''),' ',IFNULL(emply_middle_name,''),' ',IFNULL(emply_last_name,'')))'Name', \r\n"
					+ "	  a.EncashmentLEAVE_TYPE,\r\n"
					+ "	  a.EncashmentLeave_Count,a.tran_id from hrms_wbidfc.hrms_Lfc_Surrender a\r\n"
					+ "	  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd join hrms_wbidfc.hrms_department_detail c  on b.emply_cd=c.emply_cd where \r\n"
					+ "	  department  in('HR & ADMINISTRATION') and (a.Approval_Level_2 is null OR a.Approval_Level_2='')";

			NativeQuery query = session.createNativeQuery(sql);
			result = query.getResultList();

			LfcModel lm = null;

			for (Object[] obj : result) {
				String dependents = "";
				String annualIncome = "";
				String occupation = "";
				String str = obj[0].toString().trim();
				String transctionId = obj[4].toString().trim();
				String sql1 = "select EMPLY_CD,Dependent_Name,annualIncome,occupation from hrms_wbidfc.lfc_dependent where EMPLY_CD ="
						+ str + " and tranId =" + transctionId + "";
				NativeQuery dependent = session.createNativeQuery(sql1);
				dependentdata = dependent.getResultList();

				for (Object[] obj2 : dependentdata) {
					String str2 = obj2[0].toString().trim();

					if (str.equalsIgnoreCase(str2)) {
						dependents += obj2[1].toString() + ",";
						annualIncome += obj2[2].toString() + ",";
						occupation += obj2[3].toString() + ",";
					}
				}
				dependents = replaceLast(dependents, ",", "");
				annualIncome = replaceLast(annualIncome, ",", "");
				occupation = replaceLast(occupation, ",", "");

				lm = new LfcModel();

				lm.setId(str != null ? Integer.parseInt(str) : 0);
				lm.setName(obj[1] != null ? obj[1].toString() : "");
				lm.setDependent(dependents != null ? dependents : "");
				lm.setOccupation(occupation != null ? occupation : "");
				lm.setAnnualIncome(annualIncome != null ? annualIncome : "");
				lm.setLeaveTypeStr(obj[2] != null ? obj[2].toString() : "");
				lm.setNumberofDaysStr(obj[3] != null ? obj[3].toString() : "");
				String tranId = obj[4].toString();
				lm.setTranId(tranId != null ? Integer.parseInt(tranId) : 0);

				lfcData.add(lm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lfcData;
	}

	@Override
	public List<LfcModel> surCsAdminRequest() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<LfcModel> lfcData = new ArrayList<LfcModel>();
		List<Object[]> result = null;
		List<Object[]> dependentdata = null;
		try {
			String sql = "select a.EMPLY_CD,(concat(IFNULL(b.emply_title,''),' ',IFNULL(b.emply_first_name,''),' ',IFNULL(emply_middle_name,''),' ',IFNULL(emply_last_name,'')))'Name',  \r\n"
					+ "	   a.EncashmentLEAVE_TYPE, \r\n"
					+ "	a.EncashmentLeave_Count,a.Audit_Remark,a.tran_id  from hrms_wbidfc.hrms_Lfc_Surrender a \r\n"
					+ "	join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd join hrms_wbidfc.hrms_department_detail c\r\n"
					+ "	on b.emply_cd=c.emply_cd where  department in('HR & ADMINISTRATION','INTERNAL AUDIT') AND (A.Approval_Level_2 IS NULL or A.Approval_Level_2='A') and \r\n"
					+ "	   (A.Approval_Level_3 IS NULL or A.Approval_Level_3='')";

			NativeQuery query = session.createNativeQuery(sql);
			result = query.getResultList();

			LfcModel lm = null;

			for (Object[] obj : result) {
				String dependents = "";
				String annualIncome = "";
				String occupation = "";
				String str = obj[0].toString().trim();
				String transctionId = obj[5].toString().trim();
				String sql1 = "select EMPLY_CD,Dependent_Name,annualIncome,occupation from hrms_wbidfc.lfc_dependent where EMPLY_CD ="
						+ str + " and tranId =" + transctionId + "";
				NativeQuery dependent = session.createNativeQuery(sql1);
				dependentdata = dependent.getResultList();

				for (Object[] obj2 : dependentdata) {
					String str2 = obj2[0].toString().trim();

					if (str.equalsIgnoreCase(str2)) {
						dependents += obj2[1].toString() + ",";
						annualIncome += obj2[2].toString() + ",";
						occupation += obj2[3].toString() + ",";
					}
				}
				dependents = replaceLast(dependents, ",", "");
				annualIncome = replaceLast(annualIncome, ",", "");
				occupation = replaceLast(occupation, ",", "");

				lm = new LfcModel();

				lm.setId(str != null ? Integer.parseInt(str) : 0);
				lm.setName(obj[1] != null ? obj[1].toString() : "");
				lm.setDependent(dependents != null ? dependents : "");
				lm.setOccupation(occupation != null ? occupation : "");
				lm.setAnnualIncome(annualIncome != null ? annualIncome : "");
				lm.setLeaveTypeStr(obj[2] != null ? obj[2].toString() : "");
				lm.setNumberofDaysStr(obj[3] != null ? obj[3].toString() : "");
				lm.setInternalAuditRemark(obj[4] != null ? obj[4].toString() : "");
				String tranId = obj[5].toString();
				lm.setTranId(tranId != null ? Integer.parseInt(tranId) : 0);

				lfcData.add(lm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lfcData;
	}

	@Override
	public List<LfcModel> getApplyLfcData(Integer userId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<LfcModel> lfcData = new ArrayList<LfcModel>();
		List<Object[]> result = null;
		try {
			String sql = "select distinct a.emply_cd, CONCAT(IFNULL(b.emply_title, ''),'. ', IFNULL(b.emply_first_name, ''),' ',IFNULL(b.emply_middle_name, ' '),' ',IFNULL(b.emply_last_name, ' ')) AS Name,\r\n"
					+ "c.designation,d.department, date_joining'date_of_joining',a.BLOCK_APPLIED,a.NUMBER_OF_DAYS,date_format(a.leave_FROM_DT,'%d-%m-%Y')'leave_FROM_DT',\r\n"
					+ "date_format(a.leave_TO_DT,'%d-%m-%Y')'leave_TO_DT',a.PLACE_OF_DESTINATION, a.place_of_origination,date_format(a.Commencement_FROM_DT,'%d-%m-%Y')'Commencement_FROM_DT',\r\n"
					+ "date_format(a.Completion_TO_DT,'%d-%m-%Y')'Completion_TO_DT',a.EncashmentLeave_Count,a.AMOUNT_OF_ADVANCE,\r\n"
					+ "(select count(*) from hrms_wbidfc.lfc_dependent where EMPLY_CD='" + userId
					+ "' and Status='S') as COUNT,\r\n"
					+ "(select LV_BALANCE from lms_module_wb.leave_balance_new where emply_cd='" + userId
					+ "' and LEAVE_TYPE='EL')'El_Balance',a.Advance_Amount_Approved, a.Leave_Encashment_Amount_Approved \r\n"
					+ "from hrms_wbidfc.hrms_encashment a join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
					+ "JOIN hrms_wbidfc.hrms_designation_detail c ON a.emply_cd = c.emply_cd\r\n"
					+ "JOIN hrms_wbidfc.hrms_department_detail d ON a.emply_cd = d.emply_cd\r\n" + "where a.EMPLY_CD='"
					+ userId + "'and a.Status='S'";
			NativeQuery query = session.createNativeQuery(sql);
			result = query.getResultList();
			LfcModel lm = null;
			for (Object[] obj : result) {
				lm = new LfcModel();

				String str = obj[0].toString().trim();

				lm.setId(str != null ? Integer.parseInt(str) : 0);
				lm.setName(obj[1] != null ? obj[1].toString() : "");
				lm.setDesignation(obj[2] != null ? obj[2].toString() : "");
				lm.setDepartment(obj[3] != null ? obj[3].toString() : "");
				lm.setDateOfJoining(obj[4] != null ? obj[4].toString() : "");
				lm.setBlockApplied(obj[5] != null ? obj[5].toString() : "");
				lm.setNumberofDaysStr(obj[6] != null ? obj[6].toString() : "");
				lm.setLeavefromDateStr(obj[7] != null ? obj[7].toString() : "");
				lm.setLeavetoDateStr(obj[8] != null ? obj[8].toString() : "");
				lm.setPlaceofDestination(obj[9] != null ? obj[9].toString() : "");
				
				lm.setPlaceofOrigination(obj[10] != null ? obj[10].toString() : "");
				
				lm.setCommencementFromDate(obj[11] != null ? obj[11].toString() : "");
				lm.setComplitionToDate(obj[12] != null ? obj[12].toString() : "");
				lm.setEncashmentLeaveCount(obj[13] != null ? obj[13].toString() : "");
				lm.setAmountofAdvanceStr(obj[14] != null ? obj[14].toString() : "");
				lm.setCount(obj[15] != null ? obj[15].toString() : "");
				lm.setEl_LeaveBalance(obj[16] != null ? obj[16].toString() : "");
				
				lm.setAdvanceAmountApproved(obj[17] != null ? obj[17].toString() : "");
				lm.setLeaveEncashmentAmountApproved(obj[18] != null ? obj[18].toString() : "");
				
				lfcData.add(lm);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lfcData;
	}

	@Override
	public List<Lfc_Dependent> getDependentName(Integer userId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> dependentdata = null;
		List<Lfc_Dependent> DependentData = new ArrayList<Lfc_Dependent>();
		try {
			String sql = "select Dependent_Name,Dob,occupation,annualIncome,Relation from hrms_wbidfc.lfc_dependent where EMPLY_CD='"
					+ userId + "'and Status='S'";
			NativeQuery query = session.createNativeQuery(sql);
			dependentdata = query.getResultList();
			Lfc_Dependent lD = null;

			for (Object[] obj : dependentdata) {
				lD = new Lfc_Dependent();

				lD.setDependentname(obj[0] != null ? obj[0].toString() : "");
				lD.setDateOfBairth(obj[1] != null ? obj[1].toString() : "");
				lD.setOccupation(obj[2] != null ? obj[2].toString() : "");
				String annual_income = obj[3].toString();
				// lD.setAnnualincome(annual_income != null ? Integer.parseInt(annual_income) :
				// 0);
				lD.setAnnualincome(obj[3] != null ? obj[3].toString() : "");
				lD.setRelation(obj[4] != null ? obj[4].toString() : "");

				DependentData.add(lD);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return DependentData;
	}

	@Override
	public List<LfcModel> getLfcSurrenderData(Integer userId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<LfcModel> lfcData = new ArrayList<LfcModel>();
		List<Object[]> result = null;
		try {
			String sql = " select a.emply_cd, CONCAT(IFNULL(b.emply_title, ''),'. ', IFNULL(b.emply_first_name, ''),' ',IFNULL(b.emply_middle_name, ' '),' ',IFNULL(b.emply_last_name, ' ')) AS Name,\r\n"
					+ "			c.designation,d.department, date_format(date_joining,'%d-%m-%Y')'date_of_joining',date_format(a.LFC_TO_DT,'%d-%m-%Y')'LFC_TO_DT',a.BLOCK_APPLIED,a.EncashmentLeave_Count,\r\n"
					+ "            (select count(*) from hrms_wbidfc.lfc_dependent where EMPLY_CD='" + userId
					+ "' and Status='S') as COUNT,\r\n"
					+ "            (select LV_BALANCE from lms_module_wb.leave_balance_new where emply_cd='" + userId
					+ "' and LEAVE_TYPE='EL')'El_Balance'\r\n"
					+ "			from hrms_wbidfc.hrms_lfc_surrender a join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
					+ "			JOIN hrms_wbidfc.hrms_designation_detail c ON a.emply_cd = c.emply_cd\r\n"
					+ "			 JOIN hrms_wbidfc.hrms_department_detail d ON a.emply_cd = d.emply_cd\r\n"
					+ "			 where a.EMPLY_CD='" + userId + "'and a.Status='S'";
			NativeQuery query = session.createNativeQuery(sql);
			result = query.getResultList();
			LfcModel ls = null;
			for (Object[] obj : result) {
				ls = new LfcModel();

				String str = obj[0].toString().trim();
				ls.setId(str != null ? Integer.parseInt(str) : 0);
				ls.setName(obj[1] != null ? obj[1].toString() : "");
				ls.setDesignation(obj[2] != null ? obj[2].toString() : "");
				ls.setDepartment(obj[3] != null ? obj[3].toString() : "");
				ls.setDateOfJoining(obj[4] != null ? obj[4].toString() : "");
				ls.setLastAvailmentLfcDate(obj[5] != null ? obj[5].toString() : "");
				ls.setBlockApplied(obj[6] != null ? obj[6].toString() : "");
				ls.setEncashmentLeaveCount(obj[7] != null ? obj[7].toString() : "");
				ls.setCount(obj[8] != null ? obj[8].toString() : "");
				ls.setEl_LeaveBalance(obj[9] != null ? obj[9].toString() : "");

				lfcData.add(ls);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lfcData;
	}

	@Override
	public int getTranId(int employee_code) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Integer> data = null;
		BigInteger result = null;
		int num = 0;
		try {
			String sql = "select tran_id from hrms_wbidfc.hrms_encashment where EMPLY_CD =" + employee_code
					+ " and Status ='S'";
			SQLQuery query = session.createSQLQuery(sql);
			data = query.list();
			// result = (BigInteger) data.get(0);
			// num = result.intValue();
			num = data.get(0);
			System.out.println("num is :" + num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int getTranIdFromSurrender(int employee_code) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Integer> data = null;
		int num = 0;
		try {
			String sql = "select tran_id from hrms_wbidfc.hrms_Lfc_Surrender where EMPLY_CD =" + employee_code
					+ " and Status ='S'";
			SQLQuery query = session.createSQLQuery(sql);
			data = query.list();

			num = data.get(0);
			System.out.println("num is :" + num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public String getLfcToDate(String blockApplied, String fromDate) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String sql = null;
		String todate = null;
		try {
			if (blockApplied.equalsIgnoreCase("3 years")) {
				sql = "select date_format((date_format(str_to_date('" + fromDate
						+ "','%d-%m-%Y'),'%Y/%m/%d')+interval 3 year - interval 1 day),'%d-%m-%Y') from dual";
			} else {
				sql = "select date_format((date_format(str_to_date('" + fromDate
						+ "','%d-%m-%Y'),'%Y/%m/%d')+interval 5 year - interval 1 day),'%d-%m-%Y') from dual";
			}
			SQLQuery query = session.createSQLQuery(sql);
			List<Object> result = query.list();
			todate = result.get(0).toString();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in getLfcToDate daoIMPL Method :" + e);
		}
		return todate;
	}

	@Override
	public String getUserName(Integer userId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String result = null;
		try {
			String sql = "select CONCAT(IFNULL(emply_title, ''), '. ',IFNULL(emply_first_name, ''), ' ', IFNULL(emply_middle_name, ' '), ' ',IFNULL(emply_last_name, ' ')) AS Name from hrms_wbidfc.hrms_employee_detail where emply_cd= "
					+ userId + "";
			SQLQuery query = session.createSQLQuery(sql);

			result = (String) query.uniqueResult();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in getUserName DaoImpl method :" + e);
		}
		return result;
	}

	@Override
	public String getUserDesignation(Integer userId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String result = null;
		try {
			String sql = "select trim(department) from hrms_wbidfc.hrms_department_detail where emply_cd='" + userId
					+ "'and status='A'";
			SQLQuery query = session.createSQLQuery(sql);
			result = (String) query.uniqueResult();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in getUserName DaoImpl method :" + e);
		}
		return result;
	}

	@Override
	public List<Object[]> getHrAdminModalBoxData(String empId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		final BigInteger bigResult;
		final int result;
		LfcModel model = new LfcModel();
		List<Object[]> outPut = null;
		try {
			String count = "select count(*) from hrms_encashment where EMPLY_CD=" + empId + " and Status='S'";
			SQLQuery query = session.createSQLQuery(count);
			bigResult = (BigInteger) query.uniqueResult();
			result = bigResult.intValue();
			if (result > 0) {
				String sql = "select a.EMPLY_CD,concat(ifnull(emply_title,''),' ',ifnull(emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,''))'Name', \r\n"
						+ "       d.designation , a.LEAVE_ENCASH_BLOCK,a.LFC_FROM_DT,a.LFC_TO_DT,a.LEAVE_TYPE,date_format(a.leave_FROM_DT,'%d-%m-%Y')'leave_FROM_DT',\r\n"
						+ "	 date_format(a.leave_TO_DT,'%d-%m-%Y')'leave_TO_DT',a.NUMBER_OF_DAYS,a.PLACE_OF_DESTINATION,\r\n"
						+ "     a.Commencement_FROM_DT,a.Completion_TO_DT, a.AMOUNT_OF_ADVANCE ,a.EncashmentLeave_Count,a.PLACE_OF_ORIGINATION\r\n"
						+ "	 from hrms_wbidfc.hrms_encashment a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
						+ "	 join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
						+ "     join hrms_wbidfc.hrms_designation_detail d on a.emply_cd=d.emply_cd\r\n"
						+ "	 where a.emply_cd =" + empId + "";
				NativeQuery Details_query = session.createNativeQuery(sql);
				outPut = Details_query.getResultList();

			} else {
				System.out.println("you applied LFC Cum Surrender");
			}

			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in getHrAdminModalBoxData DaoImpl Method :" + e);
		}
		return outPut;
	}

	@Override
	public List<LfcModel> getHrAdminModalDependentData(String empId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> dependentdata = null;
		List<LfcModel> DependentData = new ArrayList<LfcModel>();
		try {
			String sql = "select Dependent_Name,Dob,occupation,annualIncome,Relation from hrms_wbidfc.lfc_dependent where EMPLY_CD='"
					+ empId + "'and Status='S'";
			NativeQuery query = session.createNativeQuery(sql);
			dependentdata = query.getResultList();

			LfcModel lD = null;

			for (Object[] obj : dependentdata) {
				lD = new LfcModel();

				lD.setName(obj[0] != null ? obj[0].toString() : "");
				lD.setDob(obj[1] != null ? obj[1].toString() : "");
				lD.setOccupation(obj[2] != null ? obj[2].toString() : "");
				lD.setAnnualIncome(obj[3] != null ? obj[3].toString() : "");
				lD.setRelation(obj[4] != null ? obj[4].toString() : "");

				DependentData.add(lD);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in getHrAdminModalDependentData dao impl method :" + e);
		}
		return DependentData;
	}

	@Override
	public List<LfcModel> getInternalAuditorModalBoxData(String empId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> dependentdata = null;
		List<LfcModel> InternalAuditData = new ArrayList<LfcModel>();
		try {
			String sql = "select a.EMPLY_CD,concat(ifnull(emply_title,''),' ',ifnull(emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,''))'Name', \r\n"
					+ "       d.designation , a.LEAVE_ENCASH_BLOCK,a.LFC_FROM_DT,a.LFC_TO_DT,a.LEAVE_TYPE,date_format(a.leave_FROM_DT,'%d-%m-%Y')'leave_FROM_DT',\r\n"
					+ "	 date_format(a.leave_TO_DT,'%d-%m-%Y')'leave_TO_DT',a.NUMBER_OF_DAYS,a.PLACE_OF_DESTINATION,\r\n"
					+ "     a.Commencement_FROM_DT,a.Completion_TO_DT, a.AMOUNT_OF_ADVANCE , a.Remark,a.EncashmentLeave_Count,a.PLACE_OF_ORIGINATION\r\n"
					+ "	 from hrms_wbidfc.hrms_encashment a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
					+ "	 join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "     join hrms_wbidfc.hrms_designation_detail d on a.emply_cd=d.emply_cd\r\n"
					+ "	 where a.emply_cd ='" + empId + "'";
			NativeQuery Details_query = session.createNativeQuery(sql);
			dependentdata = Details_query.getResultList();
			LfcModel lD = null;
			for (Object[] obj : dependentdata) {
				lD = new LfcModel();
				lD.setId((Integer) obj[0]);
				lD.setName((String) obj[1]);
				lD.setDesignation((String) obj[2]);
				lD.setLeaveEncashmentBlock((String) obj[3]);

				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				String strFormDate = formatter.format(obj[4]);

				lD.setLfcFromDate(strFormDate);

				SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
				String strtomDate = formatter1.format(obj[5]);

				lD.setLfctoDate(strtomDate);

				lD.setLeaveTypeStr((String) obj[6]);
				lD.setLeavefromDateStr((String) obj[7]);
				lD.setLeavetoDateStr((String) obj[8]);
				lD.setNumberofDays((int) obj[9]);
				lD.setPlaceofDestination((String) obj[10]);

				SimpleDateFormat formatter2 = new SimpleDateFormat("MM/dd/yyyy");
				String commencementFromDate = formatter2.format(obj[11]);

				lD.setCommencementFromDate(commencementFromDate);

				SimpleDateFormat formatter3 = new SimpleDateFormat("MM/dd/yyyy");
				String complitionToDate = formatter3.format(obj[12]);

				lD.setComplitionToDate(complitionToDate);

				lD.setAmountofAdvance((int) obj[13]);
				lD.setHrRemark((String) obj[14]);
				lD.setEncashmentLeaveCount(String.valueOf((int) obj[15]));
				lD.setPlaceofOrigination((String) obj[16]);

				InternalAuditData.add(lD);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in getInternalAuditorModalBoxData dao impl method :" + e);
		}
		return InternalAuditData;
	}

	@Override
	public List<LfcModel> getCsModalData(String empId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> dependentdata = null;
		List<LfcModel> CsData = new ArrayList<LfcModel>();
		try {
			String sql = "select a.EMPLY_CD,concat(ifnull(emply_title,''),' ',ifnull(emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,''))'Name', \r\n"
					+ "	     d.designation , a.LEAVE_ENCASH_BLOCK,a.LFC_FROM_DT,a.LFC_TO_DT,a.LEAVE_TYPE,date_format(a.leave_FROM_DT,'%d-%m-%Y')'leave_FROM_DT',\r\n"
					+ "	date_format(a.leave_TO_DT,'%d-%m-%Y')'leave_TO_DT',a.NUMBER_OF_DAYS,a.PLACE_OF_DESTINATION,\r\n"
					+ "	   a.Commencement_FROM_DT,a.Completion_TO_DT, a.AMOUNT_OF_ADVANCE , a.Remark ,  a.Audit_Remark,a.EncashmentLeave_Count,a.PLACE_OF_ORIGINATION\r\n"
					+ "	from hrms_wbidfc.hrms_encashment a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
					+ "	join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "	   join hrms_wbidfc.hrms_designation_detail d on a.emply_cd=d.emply_cd\r\n"
					+ "	where a.emply_cd ='" + empId + "'";

			NativeQuery Details_query = session.createNativeQuery(sql);
			dependentdata = Details_query.getResultList();
			LfcModel lD = null;
			for (Object[] obj : dependentdata) {
				lD = new LfcModel();
				lD.setId((Integer) obj[0]);
				lD.setName((String) obj[1]);
				lD.setDesignation((String) obj[2]);
				lD.setLeaveEncashmentBlock((String) obj[3]);

				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				String strFormDate = formatter.format(obj[4]);

				lD.setLfcFromDate(strFormDate);

				SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
				String strtomDate = formatter1.format(obj[5]);

				lD.setLfctoDate(strtomDate);

				lD.setLeaveTypeStr((String) obj[6]);
				lD.setLeavefromDateStr((String) obj[7]);
				lD.setLeavetoDateStr((String) obj[8]);
				lD.setNumberofDays((int) obj[9]);
				lD.setPlaceofDestination((String) obj[10]);

				SimpleDateFormat formatter2 = new SimpleDateFormat("MM/dd/yyyy");
				String commencementFromDate = formatter2.format(obj[11]);

				lD.setCommencementFromDate(commencementFromDate);

				SimpleDateFormat formatter3 = new SimpleDateFormat("MM/dd/yyyy");
				String complitionToDate = formatter3.format(obj[12]);

				lD.setComplitionToDate(complitionToDate);

				lD.setAmountofAdvance((int) obj[13]);
				lD.setHrRemark((String) obj[14]);
				lD.setInternalAuditRemark((String) obj[15]);
				lD.setEncashmentLeaveCount(String.valueOf(obj[16]));
				lD.setPlaceofOrigination((String) obj[17]);

				CsData.add(lD);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in getCsModalData DaoImpl method :" + e);
		}
		return CsData;
	}

	@Override
	public List<LfcModel> getCsAdminModalData(String empId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> dependentdata = null;
		List<LfcModel> CsData = new ArrayList<LfcModel>();
		try {
			String sql = "select a.EMPLY_CD,concat(ifnull(emply_title,''),' ',ifnull(emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,''))'Name', \r\n"
					+ "	     d.designation , a.LEAVE_ENCASH_BLOCK,a.LFC_FROM_DT,a.LFC_TO_DT,a.LEAVE_TYPE,date_format(a.leave_FROM_DT,'%d-%m-%Y')'leave_FROM_DT',\r\n"
					+ "	date_format(a.leave_TO_DT,'%d-%m-%Y')'leave_TO_DT',a.NUMBER_OF_DAYS,a.PLACE_OF_DESTINATION,\r\n"
					+ "	   a.Commencement_FROM_DT,a.Completion_TO_DT, a.AMOUNT_OF_ADVANCE , a.Audit_Remark,a.EncashmentLeave_Count,a.PLACE_OF_ORIGINATION\r\n"
					+ "	from hrms_wbidfc.hrms_encashment a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
					+ "	join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "	   join hrms_wbidfc.hrms_designation_detail d on a.emply_cd=d.emply_cd\r\n"
					+ "	where a.emply_cd ='" + empId + "'";
			NativeQuery Details_query = session.createNativeQuery(sql);
			dependentdata = Details_query.getResultList();
			LfcModel lD = null;
			for (Object[] obj : dependentdata) {
				lD = new LfcModel();
				lD.setId((Integer) obj[0]);
				lD.setName((String) obj[1]);
				lD.setDesignation((String) obj[2]);
				lD.setLeaveEncashmentBlock((String) obj[3]);

				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				String strFormDate = formatter.format(obj[4]);

				lD.setLfcFromDate(strFormDate);

				SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
				String strtomDate = formatter1.format(obj[5]);

				lD.setLfctoDate(strtomDate);

				lD.setLeaveTypeStr((String) obj[6]);
				lD.setLeavefromDateStr((String) obj[7]);
				lD.setLeavetoDateStr((String) obj[8]);
				lD.setNumberofDays((int) obj[9]);
				lD.setPlaceofDestination((String) obj[10]);

				SimpleDateFormat formatter2 = new SimpleDateFormat("MM/dd/yyyy");
				String commencementFromDate = formatter2.format(obj[11]);

				lD.setCommencementFromDate(commencementFromDate);

				SimpleDateFormat formatter3 = new SimpleDateFormat("MM/dd/yyyy");
				String complitionToDate = formatter3.format(obj[12]);

				lD.setComplitionToDate(complitionToDate);

				lD.setAmountofAdvance((int) obj[13]);

				lD.setInternalAuditRemark((String) obj[14]);
				lD.setEncashmentLeaveCount(String.valueOf(obj[15]));
				lD.setPlaceofOrigination((String) obj[16]);

				CsData.add(lD);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in getCsAdminModalData Dao impl method :" + e);
		}
		return CsData;
	}

	@Override
	public List<LfcModel> getSurHrModalData(String empId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> dependentdata = null;
		List<LfcModel> Data = new ArrayList<LfcModel>();
		try {
			String sql = "select a.EMPLY_CD,concat(ifnull(emply_title,''),' ',ifnull(emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,''))'Name', \r\n"
					+ "	      d.designation , a.BLOCK_APPLIED,a.LFC_FROM_DT,a.LFC_TO_DT,a.EncashmentLEAVE_TYPE,a.EncashmentLeave_Count\r\n"
					+ " 	 from hrms_wbidfc.hrms_lfc_surrender a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
					+ "	 join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "	    join hrms_wbidfc.hrms_designation_detail d on a.emply_cd=d.emply_cd\r\n"
					+ "	 where a.emply_cd ='" + empId + "'";
			NativeQuery Details_query = session.createNativeQuery(sql);
			dependentdata = Details_query.getResultList();
			LfcModel lD = null;
			for (Object[] obj : dependentdata) {
				lD = new LfcModel();
				lD.setId((Integer) obj[0]);
				lD.setName((String) obj[1]);
				lD.setDesignation((String) obj[2]);
				lD.setLeaveEncashmentBlock((String) obj[3]);

				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				String strFormDate = formatter.format(obj[4]);

				lD.setLfcFromDate(strFormDate);

				SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
				String strtomDate = formatter1.format(obj[5]);

				lD.setLfctoDate(strtomDate);

				lD.setLeaveTypeStr((String) obj[6]);
				lD.setNumberofDays((int) obj[7]);

				Data.add(lD);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in getSurHrModalData DaoIMpl method :" + e);
		}
		return Data;
	}

	@Override
	public List<LfcModel> getSurInternalAuditModalData(String empId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> dependentdata = null;
		List<LfcModel> Data = new ArrayList<LfcModel>();
		try {
			String sql = "select a.EMPLY_CD,concat(ifnull(emply_title,''),' ',ifnull(emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,''))'Name', \r\n"
					+ "	      d.designation , a.BLOCK_APPLIED,a.LFC_FROM_DT,a.LFC_TO_DT,a.EncashmentLEAVE_TYPE,a.EncashmentLeave_Count,a.Hr_Remark\r\n"
					+ " 	 from hrms_wbidfc.hrms_lfc_surrender a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
					+ "	 join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "	    join hrms_wbidfc.hrms_designation_detail d on a.emply_cd=d.emply_cd\r\n"
					+ "	 where a.emply_cd ='" + empId + "'";
			NativeQuery Details_query = session.createNativeQuery(sql);
			dependentdata = Details_query.getResultList();
			LfcModel lD = null;
			for (Object[] obj : dependentdata) {
				lD = new LfcModel();
				lD.setId((Integer) obj[0]);
				lD.setName((String) obj[1]);
				lD.setDesignation((String) obj[2]);
				lD.setLeaveEncashmentBlock((String) obj[3]);

				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				String strFormDate = formatter.format(obj[4]);

				lD.setLfcFromDate(strFormDate);

				SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
				String strtomDate = formatter1.format(obj[5]);

				lD.setLfctoDate(strtomDate);

				lD.setLeaveTypeStr((String) obj[6]);
				lD.setNumberofDays((int) obj[7]);
				lD.setHrRemark((String) obj[8]);

				Data.add(lD);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in getSurInternalAuditModalData in DAO IMPL :" + e);
		}
		return Data;
	}

	@Override
	public List<LfcModel> getSurCsModalData(String empId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> dependentdata = null;
		List<LfcModel> Data = new ArrayList<LfcModel>();
		try {
			String sql = "select a.EMPLY_CD,concat(ifnull(emply_title,''),' ',ifnull(emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,''))'Name', \r\n"
					+ "	      d.designation , a.BLOCK_APPLIED,a.LFC_FROM_DT,a.LFC_TO_DT,a.EncashmentLEAVE_TYPE,a.EncashmentLeave_Count,a.Hr_Remark,a.Audit_Remark\r\n"
					+ " 	 from hrms_wbidfc.hrms_lfc_surrender a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
					+ "	 join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "	    join hrms_wbidfc.hrms_designation_detail d on a.emply_cd=d.emply_cd\r\n"
					+ "	 where a.emply_cd ='" + empId + "'";
			NativeQuery Details_query = session.createNativeQuery(sql);
			dependentdata = Details_query.getResultList();
			LfcModel lD = null;
			for (Object[] obj : dependentdata) {
				lD = new LfcModel();
				lD.setId((Integer) obj[0]);
				lD.setName((String) obj[1]);
				lD.setDesignation((String) obj[2]);
				lD.setLeaveEncashmentBlock((String) obj[3]);

				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				String strFormDate = formatter.format(obj[4]);

				lD.setLfcFromDate(strFormDate);

				SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
				String strtomDate = formatter1.format(obj[5]);

				lD.setLfctoDate(strtomDate);

				lD.setLeaveTypeStr((String) obj[6]);
				lD.setNumberofDays((int) obj[7]);
				lD.setHrRemark((String) obj[8]);
				lD.setInternalAuditRemark((String) obj[9]);

				Data.add(lD);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in getSurCsModalData daoImpl method :" + e);
		}
		return Data;
	}

	@Override
	public List<LfcModel> getSurInternalAuditAdminModalData(String empId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> dependentdata = null;
		List<LfcModel> Data = new ArrayList<LfcModel>();
		try {
			String sql = "select a.EMPLY_CD,concat(ifnull(emply_title,''),' ',ifnull(emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,''))'Name', \r\n"
					+ "	      d.designation , a.BLOCK_APPLIED,a.LFC_FROM_DT,a.LFC_TO_DT,a.EncashmentLEAVE_TYPE,a.EncashmentLeave_Count\r\n"
					+ " 	 from hrms_wbidfc.hrms_lfc_surrender a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
					+ "	 join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "	    join hrms_wbidfc.hrms_designation_detail d on a.emply_cd=d.emply_cd\r\n"
					+ "	 where a.emply_cd ='" + empId + "'";
			NativeQuery Details_query = session.createNativeQuery(sql);
			dependentdata = Details_query.getResultList();
			LfcModel lD = null;
			for (Object[] obj : dependentdata) {
				lD = new LfcModel();
				lD.setId((Integer) obj[0]);
				lD.setName((String) obj[1]);
				lD.setDesignation((String) obj[2]);
				lD.setLeaveEncashmentBlock((String) obj[3]);

				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				String strFormDate = formatter.format(obj[4]);

				lD.setLfcFromDate(strFormDate);

				SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
				String strtomDate = formatter1.format(obj[5]);

				lD.setLfctoDate(strtomDate);

				lD.setLeaveTypeStr((String) obj[6]);
				lD.setNumberofDays((int) obj[7]);

				Data.add(lD);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in getSurInternalAuditAdminModalData Dao IMPL method :" + e);
		}

		return Data;
	}

	@Override
	public List<LfcModel> getSurCsAdminModalData(String empId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<Object[]> dependentdata = null;
		List<LfcModel> Data = new ArrayList<LfcModel>();
		try {
			String sql = "select a.EMPLY_CD,concat(ifnull(emply_title,''),' ',ifnull(emply_first_name,''),' ',ifnull(emply_middle_name,''),' ',ifnull(emply_last_name,''))'Name', \r\n"
					+ "	      d.designation , a.BLOCK_APPLIED,a.LFC_FROM_DT,a.LFC_TO_DT,a.EncashmentLEAVE_TYPE,a.EncashmentLeave_Count,a.Audit_Remark\r\n"
					+ " 	 from hrms_wbidfc.hrms_lfc_surrender a  join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
					+ "	 join hrms_wbidfc.hrms_department_detail c  on a.emply_cd=c.emply_cd\r\n"
					+ "	    join hrms_wbidfc.hrms_designation_detail d on a.emply_cd=d.emply_cd\r\n"
					+ "	 where a.emply_cd ='" + empId + "'";
			NativeQuery Details_query = session.createNativeQuery(sql);
			dependentdata = Details_query.getResultList();
			LfcModel lD = null;
			for (Object[] obj : dependentdata) {
				lD = new LfcModel();
				lD.setId((Integer) obj[0]);
				lD.setName((String) obj[1]);
				lD.setDesignation((String) obj[2]);
				lD.setLeaveEncashmentBlock((String) obj[3]);

				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				String strFormDate = formatter.format(obj[4]);

				lD.setLfcFromDate(strFormDate);

				SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
				String strtomDate = formatter1.format(obj[5]);

				lD.setLfctoDate(strtomDate);

				lD.setLeaveTypeStr((String) obj[6]);
				lD.setNumberofDays((int) obj[7]);
				lD.setInternalAuditRemark((String) obj[8]);

				Data.add(lD);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in getSurCsAdminModalData Dao Impl method :" + e);
		}
		return Data;
	}

	@Override
	public List<LfcModel> getOfcUseData(Integer userId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<LfcModel> lfcData = new ArrayList<LfcModel>();
		lfcData.clear();
		List<Object[]> result = null;
		String designation = null;
		try {
			String sql1 = "select department from hrms_wbidfc.hrms_department_detail where emply_cd='" + userId + "'";
			SQLQuery query1 = session.createSQLQuery(sql1);
			designation = (String) query1.uniqueResult();
			String desi = designation.toUpperCase();
			if (desi.equalsIgnoreCase("HR & ADMINISTRATION")) {
				String sql = "select distinct a.emply_cd, CONCAT(IFNULL(b.emply_title, ''),'. ', IFNULL(b.emply_first_name, ''),' ',IFNULL(b.emply_middle_name, ' '),' ',IFNULL(b.emply_last_name, ' ')) AS Name,\r\n"
						+ "		a.BLOCK_APPLIED,a.EncashmentLeave_Count,a.AMOUNT_OF_ADVANCE,\r\n"
						+ "		(select count(*) from hrms_wbidfc.lfc_dependent where EMPLY_CD='" + userId
						+ "' and Status='S') as COUNT,\r\n"
						+ "		(select LV_BALANCE from lms_module_wb.leave_balance_new where emply_cd='" + userId
						+ "' and LEAVE_TYPE='EL')'El_Balance'\r\n"
						+ "		from hrms_wbidfc.hrms_encashment a join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
						+ "		JOIN hrms_wbidfc.hrms_designation_detail c ON a.emply_cd = c.emply_cd\r\n"
						+ "		JOIN hrms_wbidfc.hrms_department_detail d ON a.emply_cd = d.emply_cd\r\n"
						+ "		where a.EMPLY_CD='" + userId + "'and a.Status='S' and a.Approval_Level_2='A'";
				NativeQuery query = session.createNativeQuery(sql);
				result = query.getResultList();
				LfcModel lm = null;
				for (Object[] obj : result) {
					lm = new LfcModel();

					String str = obj[0].toString().trim();

					lm.setId(str != null ? Integer.parseInt(str) : 0);
					lm.setName(obj[1] != null ? obj[1].toString() : "");
					lm.setBlockApplied(obj[2] != null ? obj[2].toString() : "");
					lm.setEncashmentLeaveCount(obj[3] != null ? obj[3].toString() : "");
					lm.setAmountofAdvanceStr(obj[4] != null ? obj[4].toString() : "");
					lm.setCount(obj[5] != null ? obj[5].toString() : "");
					lm.setEl_LeaveBalance(obj[6] != null ? obj[6].toString() : "");
					lfcData.add(lm);
				}
			} else if (desi.equalsIgnoreCase("INTERNAL AUDIT")) {
				String sql = "select distinct a.emply_cd, CONCAT(IFNULL(b.emply_title, ''),'. ', IFNULL(b.emply_first_name, ''),' ',IFNULL(b.emply_middle_name, ' '),' ',IFNULL(b.emply_last_name, ' ')) AS Name,\r\n"
						+ "		a.BLOCK_APPLIED,a.EncashmentLeave_Count,a.AMOUNT_OF_ADVANCE,\r\n"
						+ "		(select count(*) from hrms_wbidfc.lfc_dependent where EMPLY_CD='" + userId
						+ "' and Status='S') as COUNT,\r\n"
						+ "		(select LV_BALANCE from lms_module_wb.leave_balance_new where emply_cd='" + userId
						+ "' and LEAVE_TYPE='EL')'El_Balance'\r\n"
						+ "		from hrms_wbidfc.hrms_encashment a join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
						+ "		JOIN hrms_wbidfc.hrms_designation_detail c ON a.emply_cd = c.emply_cd\r\n"
						+ "		JOIN hrms_wbidfc.hrms_department_detail d ON a.emply_cd = d.emply_cd\r\n"
						+ "		where a.EMPLY_CD='" + userId + "'and a.Status='S' and a.Approval_Level_3='A'";
				NativeQuery query = session.createNativeQuery(sql);
				result = query.getResultList();
				LfcModel lm = null;
				for (Object[] obj : result) {
					lm = new LfcModel();

					String str = obj[0].toString().trim();

					lm.setId(str != null ? Integer.parseInt(str) : 0);
					lm.setName(obj[1] != null ? obj[1].toString() : "");
					lm.setBlockApplied(obj[2] != null ? obj[2].toString() : "");
					lm.setEncashmentLeaveCount(obj[3] != null ? obj[3].toString() : "");
					lm.setAmountofAdvanceStr(obj[4] != null ? obj[4].toString() : "");
					lm.setCount(obj[5] != null ? obj[5].toString() : "");
					lm.setEl_LeaveBalance(obj[6] != null ? obj[6].toString() : "");
					lfcData.add(lm);
				}
			} else if (desi.equalsIgnoreCase("COMPANY SECRERTARIAT")) {
				String sql = "select distinct a.emply_cd, CONCAT(IFNULL(b.emply_title, ''),'. ', IFNULL(b.emply_first_name, ''),' ',IFNULL(b.emply_middle_name, ' '),' ',IFNULL(b.emply_last_name, ' ')) AS Name,\r\n"
						+ "		a.BLOCK_APPLIED,a.EncashmentLeave_Count,a.AMOUNT_OF_ADVANCE,\r\n"
						+ "		(select count(*) from hrms_wbidfc.lfc_dependent where EMPLY_CD='" + userId
						+ "' and Status='S') as COUNT,\r\n"
						+ "		(select LV_BALANCE from lms_module_wb.leave_balance_new where emply_cd='" + userId
						+ "' and LEAVE_TYPE='EL')'El_Balance'\r\n"
						+ "		from hrms_wbidfc.hrms_encashment a join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
						+ "		JOIN hrms_wbidfc.hrms_designation_detail c ON a.emply_cd = c.emply_cd\r\n"
						+ "		JOIN hrms_wbidfc.hrms_department_detail d ON a.emply_cd = d.emply_cd\r\n"
						+ "		where a.EMPLY_CD='" + userId + "'and a.Status='S'";
				NativeQuery query = session.createNativeQuery(sql);
				result = query.getResultList();
				LfcModel lm = null;
				for (Object[] obj : result) {
					lm = new LfcModel();

					String str = obj[0].toString().trim();

					lm.setId(str != null ? Integer.parseInt(str) : 0);
					lm.setName(obj[1] != null ? obj[1].toString() : "");
					lm.setBlockApplied(obj[2] != null ? obj[2].toString() : "");
					lm.setEncashmentLeaveCount(obj[3] != null ? obj[3].toString() : "");
					lm.setAmountofAdvanceStr(obj[4] != null ? obj[4].toString() : "");
					lm.setCount(obj[5] != null ? obj[5].toString() : "");
					lm.setEl_LeaveBalance(obj[6] != null ? obj[6].toString() : "");
					lfcData.add(lm);
				}
			} else {
				String sql = "select distinct a.emply_cd, CONCAT(IFNULL(b.emply_title, ''),'. ', IFNULL(b.emply_first_name, ''),' ',IFNULL(b.emply_middle_name, ' '),' ',IFNULL(b.emply_last_name, ' ')) AS Name,\r\n"
						+ "		a.BLOCK_APPLIED,a.EncashmentLeave_Count,a.AMOUNT_OF_ADVANCE,\r\n"
						+ "		(select count(*) from hrms_wbidfc.lfc_dependent where EMPLY_CD='" + userId
						+ "' and Status='S') as COUNT,\r\n"
						+ "		(select LV_BALANCE from lms_module_wb.leave_balance_new where emply_cd='" + userId
						+ "' and LEAVE_TYPE='EL')'El_Balance'\r\n"
						+ "		from hrms_wbidfc.hrms_encashment a join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
						+ "		JOIN hrms_wbidfc.hrms_designation_detail c ON a.emply_cd = c.emply_cd\r\n"
						+ "		JOIN hrms_wbidfc.hrms_department_detail d ON a.emply_cd = d.emply_cd\r\n"
						+ "		where a.EMPLY_CD='" + userId + "'and a.Status='S' and a.Approval_Level_1='A'";
				NativeQuery query = session.createNativeQuery(sql);
				result = query.getResultList();
				LfcModel lm = null;
				for (Object[] obj : result) {
					lm = new LfcModel();

					String str = obj[0].toString().trim();

					lm.setId(str != null ? Integer.parseInt(str) : 0);
					lm.setName(obj[1] != null ? obj[1].toString() : "");
					lm.setBlockApplied(obj[2] != null ? obj[2].toString() : "");
					lm.setEncashmentLeaveCount(obj[3] != null ? obj[3].toString() : "");
					lm.setAmountofAdvanceStr(obj[4] != null ? obj[4].toString() : "");
					lm.setCount(obj[5] != null ? obj[5].toString() : "");
					lm.setEl_LeaveBalance(obj[6] != null ? obj[6].toString() : "");
					lfcData.add(lm);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lfcData;
	}

	@Override
	public List<LfcModel> getOfcUseDataSur(Integer userId) {

		List<LfcModel> lfcData = new ArrayList<LfcModel>();
		lfcData.clear();
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			List<Object[]> result = null;
			String designation = null;

			String sql1 = "select department from hrms_wbidfc.hrms_department_detail where emply_cd='" + userId + "'";
			SQLQuery query = session.createSQLQuery(sql1);
			designation = (String) query.uniqueResult();
			String desi = designation.toUpperCase();
			if (desi.equalsIgnoreCase("HR & ADMINISTRATION")) {
				String sql = "select a.emply_cd, CONCAT(IFNULL(b.emply_title, ''),'. ', IFNULL(b.emply_first_name, ''),' ',IFNULL(b.emply_middle_name, ' '),' ',IFNULL(b.emply_last_name, ' ')) AS Name,\r\n"
						+ "		a.BLOCK_APPLIED,a.EncashmentLeave_Count,\r\n"
						+ "	       (select count(*) from hrms_wbidfc.lfc_dependent where EMPLY_CD='" + userId
						+ "' and Status='S') as COUNT,\r\n"
						+ "	       (select LV_BALANCE from lms_module_wb.leave_balance_new where emply_cd='" + userId
						+ "' and LEAVE_TYPE='EL')'El_Balance'\r\n"
						+ "		from hrms_wbidfc.hrms_lfc_surrender a join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
						+ "		JOIN hrms_wbidfc.hrms_designation_detail c ON a.emply_cd = c.emply_cd\r\n"
						+ "		 JOIN hrms_wbidfc.hrms_department_detail d ON a.emply_cd = d.emply_cd\r\n"
						+ "		 where a.EMPLY_CD='" + userId + "'and a.Status='S' and a.Approval_Level_2='A'";
				NativeQuery query1 = session.createNativeQuery(sql);
				result = query1.getResultList();
				LfcModel ls = null;
				for (Object[] obj : result) {
					ls = new LfcModel();

					String str = obj[0].toString().trim();
					ls.setId(str != null ? Integer.parseInt(str) : 0);
					ls.setName(obj[1] != null ? obj[1].toString() : "");
					System.out.println(ls.getName());
					ls.setBlockApplied(obj[2] != null ? obj[2].toString() : "");
					ls.setEncashmentLeaveCount(obj[3] != null ? obj[3].toString() : "");
					ls.setCount(obj[4] != null ? obj[4].toString() : "");
					ls.setEl_LeaveBalance(obj[5] != null ? obj[5].toString() : "");

					lfcData.add(ls);
				}

			} else if (desi.equalsIgnoreCase("INTERNAL AUDIT")) {
				String sql = "select a.emply_cd, CONCAT(IFNULL(b.emply_title, ''),'. ', IFNULL(b.emply_first_name, ''),' ',IFNULL(b.emply_middle_name, ' '),' ',IFNULL(b.emply_last_name, ' ')) AS Name,\r\n"
						+ "		a.BLOCK_APPLIED,a.EncashmentLeave_Count,\r\n"
						+ "	       (select count(*) from hrms_wbidfc.lfc_dependent where EMPLY_CD='" + userId
						+ "' and Status='S') as COUNT,\r\n"
						+ "	       (select LV_BALANCE from lms_module_wb.leave_balance_new where emply_cd='" + userId
						+ "' and LEAVE_TYPE='EL')'El_Balance'\r\n"
						+ "		from hrms_wbidfc.hrms_lfc_surrender a join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
						+ "		JOIN hrms_wbidfc.hrms_designation_detail c ON a.emply_cd = c.emply_cd\r\n"
						+ "		 JOIN hrms_wbidfc.hrms_department_detail d ON a.emply_cd = d.emply_cd\r\n"
						+ "		 where a.EMPLY_CD='" + userId + "'and a.Status='S' and a.Approval_Level_3='A'";
				NativeQuery query1 = session.createNativeQuery(sql);
				result = query1.getResultList();
				LfcModel ls = null;
				for (Object[] obj : result) {
					ls = new LfcModel();

					String str = obj[0].toString().trim();
					ls.setId(str != null ? Integer.parseInt(str) : 0);
					ls.setName(obj[1] != null ? obj[1].toString() : "");
					System.out.println(ls.getName());
					ls.setBlockApplied(obj[2] != null ? obj[2].toString() : "");
					ls.setEncashmentLeaveCount(obj[3] != null ? obj[3].toString() : "");
					ls.setCount(obj[4] != null ? obj[4].toString() : "");
					ls.setEl_LeaveBalance(obj[5] != null ? obj[5].toString() : "");

					lfcData.add(ls);
				}

			} else if (desi.equalsIgnoreCase("COMPANY SECRERTARIAT")) {

				String sql = "select a.emply_cd, CONCAT(IFNULL(b.emply_title, ''),'. ', IFNULL(b.emply_first_name, ''),' ',IFNULL(b.emply_middle_name, ' '),' ',IFNULL(b.emply_last_name, ' ')) AS Name,\r\n"
						+ "		a.BLOCK_APPLIED,a.EncashmentLeave_Count,\r\n"
						+ "	       (select count(*) from hrms_wbidfc.lfc_dependent where EMPLY_CD='" + userId
						+ "' and Status='S') as COUNT,\r\n"
						+ "	       (select LV_BALANCE from lms_module_wb.leave_balance_new where emply_cd='" + userId
						+ "' and LEAVE_TYPE='EL')'El_Balance'\r\n"
						+ "		from hrms_wbidfc.hrms_lfc_surrender a join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
						+ "		JOIN hrms_wbidfc.hrms_designation_detail c ON a.emply_cd = c.emply_cd\r\n"
						+ "		 JOIN hrms_wbidfc.hrms_department_detail d ON a.emply_cd = d.emply_cd\r\n"
						+ "		 where a.EMPLY_CD='" + userId + "'and a.Status='S'";
				NativeQuery query1 = session.createNativeQuery(sql);
				result = query1.getResultList();
				LfcModel ls = null;
				for (Object[] obj : result) {
					ls = new LfcModel();

					String str = obj[0].toString().trim();
					ls.setId(str != null ? Integer.parseInt(str) : 0);
					ls.setName(obj[1] != null ? obj[1].toString() : "");
					System.out.println(ls.getName());
					ls.setBlockApplied(obj[2] != null ? obj[2].toString() : "");
					ls.setEncashmentLeaveCount(obj[3] != null ? obj[3].toString() : "");
					ls.setCount(obj[4] != null ? obj[4].toString() : "");
					ls.setEl_LeaveBalance(obj[5] != null ? obj[5].toString() : "");

					lfcData.add(ls);
				}

			} else {
				String sql = "select a.emply_cd, CONCAT(IFNULL(b.emply_title, ''),'. ', IFNULL(b.emply_first_name, ''),' ',IFNULL(b.emply_middle_name, ' '),' ',IFNULL(b.emply_last_name, ' ')) AS Name,\r\n"
						+ "		a.BLOCK_APPLIED,a.EncashmentLeave_Count,\r\n"
						+ "	       (select count(*) from hrms_wbidfc.lfc_dependent where EMPLY_CD='" + userId
						+ "' and Status='S') as COUNT,\r\n"
						+ "	       (select LV_BALANCE from lms_module_wb.leave_balance_new where emply_cd='" + userId
						+ "' and LEAVE_TYPE='EL')'El_Balance'\r\n"
						+ "		from hrms_wbidfc.hrms_lfc_surrender a join hrms_wbidfc.hrms_employee_detail b on a.emply_cd=b.emply_cd\r\n"
						+ "		JOIN hrms_wbidfc.hrms_designation_detail c ON a.emply_cd = c.emply_cd\r\n"
						+ "		 JOIN hrms_wbidfc.hrms_department_detail d ON a.emply_cd = d.emply_cd\r\n"
						+ "		 where a.EMPLY_CD='" + userId + "'and a.Status='S' and a.Approval_Level_1='A'";
				NativeQuery query1 = session.createNativeQuery(sql);
				result = query1.getResultList();
				LfcModel ls = null;
				for (Object[] obj : result) {
					ls = new LfcModel();

					String str = obj[0].toString().trim();
					ls.setId(str != null ? Integer.parseInt(str) : 0);
					ls.setName(obj[1] != null ? obj[1].toString() : "");
					System.out.println(ls.getName());
					ls.setBlockApplied(obj[2] != null ? obj[2].toString() : "");
					ls.setEncashmentLeaveCount(obj[3] != null ? obj[3].toString() : "");
					ls.setCount(obj[4] != null ? obj[4].toString() : "");
					ls.setEl_LeaveBalance(obj[5] != null ? obj[5].toString() : "");

					lfcData.add(ls);
				}
			}
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lfcData;
	}

	@Override
	public int isLeaveApplied(Integer userId) {
		int leaveCount=0;
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			BigInteger count = null;
			List<Object> data = null;
			String sql = "select  count(*) from  hrms_wbidfc.hrms_encashment where LEAVE_TYPE!='' and Status='S' and EMPLY_CD='"+userId+"'";
			SQLQuery query = session.createSQLQuery(sql);
			data = query.list();
			count = (BigInteger) data.get(0);
		    leaveCount = count.intValue();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in isLeaveApplied Dao method :"+e);
		}
		 
		return leaveCount;
	}

}
