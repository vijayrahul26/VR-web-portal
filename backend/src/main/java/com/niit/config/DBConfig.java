package com.niit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class DBConfig {
	@Bean(name="sessionFactory")
	@Autowired
    public LocalSessionFactoryBean sessionFactory() 
	{
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.niit.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        System.out.println("SessionCreated");
        return sessionFactory;
     }
	@Bean(name="dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("hr");
        dataSource.setPassword("oracle");
		return dataSource;
}

	 private Properties hibernateProperties() {
	        Properties properties = new Properties();
	        properties.setProperty("hibernate.show_sql","true");
			properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
			properties.setProperty("hibernate.hbm2ddl.auto","update");
			
	        return properties;        
	    }

	 @Bean
	    @Autowired
	    public HibernateTransactionManager transactionManager(SessionFactory s)
	 {
	       HibernateTransactionManager txManager = new HibernateTransactionManager();
	       txManager.setSessionFactory(s);
	       return txManager;
	    }

}
