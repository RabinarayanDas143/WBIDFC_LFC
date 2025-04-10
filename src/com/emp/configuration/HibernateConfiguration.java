package com.emp.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author int6346 vivek
 */

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan({ "com.emp" })
@PropertySource(value = { "classpath:resources/database.properties" })
public class HibernateConfiguration {

	@Autowired
	private Environment environment;

	@Primary
	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.emp.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("hrms.driver"));
		dataSource.setUrl(environment.getRequiredProperty("hrms.url"));
		dataSource.setUsername(environment.getRequiredProperty("hrms.username"));
		dataSource.setPassword(environment.getRequiredProperty("hrms.password"));
		return dataSource;
	}

	@Bean
	@Primary
	public HibernateTransactionManager transactionManager(@Qualifier(value = "sessionFactory") SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory().getObject());
		return txManager;
	}

	@Bean(name = "sessionFactory2")
	public LocalSessionFactoryBean sessionFactory2() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource2());
		sessionFactory.setPackagesToScan(new String[] { "com.emp.modelAllowance" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	public DataSource dataSource2() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("hrms1.driver"));
		dataSource.setUrl(environment.getRequiredProperty("hrms1.url"));
		dataSource.setUsername(environment.getRequiredProperty("hrms1.username"));
		dataSource.setPassword(environment.getRequiredProperty("hrms1.password"));
		return dataSource;
	}

	@Bean
	public HibernateTransactionManager transactionManager2(@Qualifier(value = "sessionFactory2") SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory().getObject());
		return txManager;
	}
	
	
	

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		// hibernate default
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.thread", environment.getRequiredProperty("hibernate.thread"));
		properties.put("hibernate.jdbc.batch_size", "10000");
		// connection pooling
		properties.put("hibernate.c3p0.min_size", environment.getRequiredProperty("hibernate.c3p0.min_size"));
		properties.put("hibernate.c3p0.max_size", environment.getRequiredProperty("hibernate.c3p0.max_size"));
		properties.put("hibernate.c3p0.timeout", environment.getRequiredProperty("hibernate.c3p0.timeout"));
		properties.put("hibernate.c3p0.max_statements",
				environment.getRequiredProperty("hibernate.c3p0.max_statements"));
		properties.put("hibernate.c3p0.idle_test_period",
				environment.getRequiredProperty("hibernate.c3p0.idle_test_period"));
		// batch processing
		// properties.put("hibernate.jdbc.batch_size",
		// environment.getRequiredProperty("hibernate.jdbc.batch_size"));
		// properties.put("hibernate.order_inserts",
		// environment.getRequiredProperty("hibernate.order_inserts"));
		// properties.put("hibernate.order_updates",
		// environment.getRequiredProperty("hibernate.order_updates"));
		// properties.put("hibernate.batch_versioned_data",
		// environment.getRequiredProperty("hibernate.batch_versioned_data"));

		return properties;
	}
}
