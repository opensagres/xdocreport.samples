package fr.opensagres.demo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.FontFactory;

public class RegisterFontFactorytListener implements ServletContextListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterFontFactorytListener.class);

	public void contextDestroyed(ServletContextEvent event) {
	}

	public void contextInitialized(ServletContextEvent event) {
		LOGGER.debug(FontFactory.getRegisteredFonts().toString());

		String fontFolder = event.getServletContext().getRealPath("font");
		LOGGER.debug("fontFolder  {}", fontFolder);
		FontFactory.registerDirectory(fontFolder);

		LOGGER.info(FontFactory.getRegisteredFonts().toString());
	}

}
