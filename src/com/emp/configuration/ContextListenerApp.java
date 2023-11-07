package com.emp.configuration;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class ContextListenerApp implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(ContextListenerApp.class);

	// not used
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		logger.info("STOP");
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		System.out.println("more ===" + drivers.hasMoreElements());
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);
			} catch (SQLException e) {
			}
		}
		try {
			Thread.sleep(2000L);
		} catch (Exception e) {
		} // Use this thread sleep

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("START");

	}
}
