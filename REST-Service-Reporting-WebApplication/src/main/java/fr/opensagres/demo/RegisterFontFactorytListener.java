package fr.opensagres.demo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.lowagie.text.FontFactory;

public class RegisterFontFactorytListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {}

	public void contextInitialized(ServletContextEvent event) {
		System.out.println(FontFactory.getRegisteredFonts());
		System.out.println("------------------------------");
		System.out.println(FontFactory.getFont("times-roman").hashCode());
		String fontFolder = event.getServletContext().getRealPath("font");
		
		FontFactory.register(fontFolder+"/Aller_Rg.ttf","times-roman");
		FontFactory.register(fontFolder+"/Aller_BdIt.ttf","times-bolditalic");
		FontFactory.register(fontFolder+"/Aller_Bd.ttf","times-bold");
		FontFactory.register(fontFolder+"/Aller_It.ttf","times-italic");
		System.out.println(FontFactory.getFont("times-roman").hashCode());
		System.out.println(FontFactory.getRegisteredFonts());

	}

}
