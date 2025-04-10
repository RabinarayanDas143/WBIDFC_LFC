package com.emp.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.provider.HibernateUtils;

import com.mysql.cj.Session;

public class LfcProcImpl {
	@Autowired
	@Qualifier(value = "sessionFactory")
	private static SessionFactory sessionFactory;

	public static void main(String[] args) {
		try {
			org.hibernate.Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in LfcProcImpl class :" + e);
		}
	}
}
