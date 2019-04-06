package com.karki.ashish.productfindr.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.karki.ashish.productfindr")
@PropertySource({ "classpath:mysql-connection.properties" })
public class ProductFindrConfig implements WebMvcConfigurer {

	@Autowired
	private Environment environment;

	private Logger logger = Logger.getLogger(getClass().getName());

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// WebMvcConfigurer.super.addCorsMappings(registry);
		registry.addMapping("/**");
	}

	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();

		try {
			pooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}

		// quick check to see if things work
		logger.info("jdbc.url=" + environment.getProperty("jdbc.url"));
		logger.info("jdbc.user=" + environment.getProperty("jdbc.user"));

		// database connection properties
		pooledDataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
		pooledDataSource.setUser(environment.getProperty("jdbc.user"));
		pooledDataSource.setPassword(environment.getProperty("jdbc.password"));

		// connection pool properties
		pooledDataSource.setInitialPoolSize(getEquivalentIntValue("connection.pool.initialPoolSize"));
		pooledDataSource.setMinPoolSize(getEquivalentIntValue("connection.pool.minPoolSize"));
		pooledDataSource.setMaxPoolSize(getEquivalentIntValue("connection.pool.maxPoolSize"));
		pooledDataSource.setMaxIdleTime(getEquivalentIntValue("connection.pool.maxIdleTime"));

		return pooledDataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean localSessionFactory = new LocalSessionFactoryBean();

		localSessionFactory.setDataSource(dataSource());
		localSessionFactory.setPackagesToScan(environment.getProperty("hibernate.packagesToScan"));
		localSessionFactory.setHibernateProperties(getHibernateProperties());

		return localSessionFactory;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}

	private Properties getHibernateProperties() {
		Properties props = new Properties();

		props.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));

		return props;
	}

	private int getEquivalentIntValue(String propName) {
		String stringPropName = environment.getProperty(propName);
		int equivalentIntProp = Integer.parseInt(stringPropName);

		return equivalentIntProp;
	}
}
