package fr.opensagres.xdocreport.webapp;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.opensagres.xdocreport.webapp.defaultreport.DefaultReportRegistry;

public class ProcessXDocReporContextListener implements ServletContextListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessXDocReporContextListener.class);

	public void contextDestroyed(ServletContextEvent event) {}

	public void contextInitialized(ServletContextEvent event) {
		String resourcesFolder = event.getServletContext().getRealPath("resources");
		LOGGER.info("resourcesFolder1 {}" + resourcesFolder);
		DefaultReportRegistry.INSTANCE.setResourcesFolder(new File(resourcesFolder));
	}

}
