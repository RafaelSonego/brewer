package com.rafaelsonego.brewer.config.init;

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

}
