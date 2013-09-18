package fr.opensagres.demo;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.FontFactory;

public class RegisterFontFactorytListener implements ServletContextListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterFontFactorytListener.class);
	
	public void contextDestroyed(ServletContextEvent event) {}

	public void contextInitialized(ServletContextEvent event) {
		LOGGER.info(FontFactory.getRegisteredFonts().toString());
		
//		File aFile = new File("/private/opensagres/fonts");
//		LOGGER.info("Folder exists ? "+ aFile.exists());
//		if(aFile.exists()) {
//			File font = new File(aFile,"calibri.ttf");
//			LOGGER.info("font exists ? "+ font.exists());
//		}
//		System.out.println("------------------------------");
//		System.out.println(FontFactory.getFont("times-roman").hashCode());
		String fontFolder = event.getServletContext().getRealPath("font");
		
		FontFactory.register(fontFolder+"/GandhiSans-Regular.otf","calibri");
//		FontFactory.register(fontFolder+"/Aller_BdIt.ttf","times-bolditalic");
//		FontFactory.register(fontFolder+"/Aller_Bd.ttf","times-bold");
//		FontFactory.register(fontFolder+"/Aller_It.ttf","times-italic");
//		System.out.println(FontFactory.getFont("times-roman").hashCode());
		System.out.println(FontFactory.getRegisteredFonts());

	}

}
