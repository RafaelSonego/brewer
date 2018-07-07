package com.rafaelsonego.brewer.config.init;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.rafaelsonego.brewer.config.JPAConfig;
import com.rafaelsonego.brewer.config.ServiceConfig;
import com.rafaelsonego.brewer.config.WebConfig;

/***
 * @author rafael Configure DispatchServlet
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/***
	 * This configuration is always started before Servlet Config. 
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { JPAConfig.class, ServiceConfig.class };
	}

	/***
	 * Configure the class with Spring configurations for Servlets
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class }; // Define the class with all configuration
	}

	/***
	 * Mapping everything After Application URL (rafaelsonego.com/...)
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[] { characterEncodingFilter };
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement("")); //Empty string means de spring will define de path location
	}

}
