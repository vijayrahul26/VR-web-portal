package com.niit.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
	@Override
	protected Class<?>[] getRootConfigClasses() {
		//create beans for DataSource,SessionFactory,HibernateTransactionManager
		return new Class[]{DBConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		//Enable webmvc, handler mapping,scan all the components in the base package "com.niit"
		// TODO Auto-generated method stub
		return new Class[]{WebAppConfig.class};
	}

	//forward all the incoming requests to DispatcherServlet by specifying the url pattern as '/'
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"}; //url-pattern in web.xml file for the servlet dispatcher
	}

}
