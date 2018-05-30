package com.rafaelsonego.brewer.config.init;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.rafaelsonego.brewer.config.WebConfig;

/***
 * @author rafael 
 * Configure DispatchServlet
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	/***
	 * Configure the class with spring congiuration
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// Define the class with all configuration
		return new Class<?>[] { WebConfig.class };
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
		return new Filter[] {characterEncodingFilter};
	}

}
