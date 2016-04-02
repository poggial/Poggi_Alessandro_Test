package com.faac.soa.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.faac.soa.dao.UserDAO;
import com.faac.soa.dao.UserDAOImpl;
import com.faac.soa.model.User;

@Configuration
@ComponentScan("com.faac.soa")
@EnableTransactionManagement
public class ApplicationContextConfig {
	private static final Logger logger = LogManager.getLogger(ApplicationContextConfig.class);
	private final String CONFIG_FILE = "config.properties";
	protected Properties properties;

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		try {
			if (properties == null) {
				properties = getPropertiesFile();
			}

			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName(properties.getProperty("config.driver"));
			dataSource.setUrl(properties.getProperty("config.url"));
			dataSource.setUsername(properties.getProperty("config.username"));
			dataSource.setPassword(properties.getProperty("config.password"));
			return dataSource;
		} catch (FileNotFoundException fnfe) {
			logger.error(fnfe.getMessage());
		} catch (IOException ioe) {
			logger.error(ioe.getMessage());
		}

		return null;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);

		sessionBuilder.addAnnotatedClasses(User.class);

		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	@Autowired
	@Bean(name = "userDao")
	public UserDAO getUserDao(SessionFactory sessionFactory) {
		return new UserDAOImpl(sessionFactory);
	}

	protected Properties getPropertiesFile() throws IOException, FileNotFoundException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		InputStream resourceStream = loader.getResourceAsStream(CONFIG_FILE);
		properties.load(resourceStream);
		resourceStream.close();
		return properties;
	}
}