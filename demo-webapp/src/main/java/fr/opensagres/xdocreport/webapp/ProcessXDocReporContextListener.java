package fr.opensagres.xdocreport.webapp;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.opensagres.xdocreport.webapp.defaultreport.DefaultReportRegistry;

public class ProcessXDocReporContextListener implements ServletContextListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessXDocReporContextListener.class);

	public void contextDestroyed(ServletContextEvent event) {
		
	}

	public void contextInitialized(ServletContextEvent event) {
			LOGGER.info( "event "+event );
			LOGGER.info( "event.getServletContext() "+event.getServletContext() );
			String resourcesFolder0 = event.getServletContext().getRealPath(".");
	        LOGGER.info( "resourcesFolder0 "+resourcesFolder0 );
	        String resourcesFolder1 = event.getServletContext().getRealPath("resources");
	        LOGGER.info( "resourcesFolder1 "+resourcesFolder1 );
	        String resourcesFolder = event.getServletContext().getRealPath("resources/Opensagres");
	        LOGGER.info( "resourcesFolder "+resourcesFolder );
	        
	        DefaultReportRegistry.INSTANCE.setResourcesFolder( new File(resourcesFolder1) );
		
	}

}
