package fr.opensagres.xdocreport.remoting.resources.services.client.jaxws;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.opensagres.xdocreport.converter.ConverterApplication;
import fr.opensagres.xdocreport.converter.ConverterResource;
import fr.opensagres.xdocreport.converter.ConverterResourceImpl;

public class JAXWSResourcesServiceClientTestCase {

	private static final int PORT = 8080;


	private static final String BASE_ADDRESS = "http://localhost:" + PORT+"/generic";



	@BeforeClass
	public static void startServer() throws Exception {

		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
//		sf.setApplication(new ConverterApplication());
		sf.setApplication(new ConverterApplication());
        sf.setResourceClasses(ConverterResourceImpl.class);
        sf.setResourceProvider(ConverterResourceImpl.class,
            new SingletonResourceProvider(new ConverterResourceImpl()));

        sf.setAddress("http://localhost:" + PORT);

       Server server= sf.create();
       System.out.println(server.getEndpoint());
/*
		ServletHolder servlet = new ServletHolder(CXFNonSpringServlet.class);
		 servlet.setInitParameter( "timeout", "60000" );

		servlet.setInitParameter("javax.ws.rs.Application",
				"fr.opensagres.xdocreport.converter.ConverterApplication");

		server = new Server(PORT);

		ServletContextHandler context = new ServletContextHandler(server, "/",
				ServletContextHandler.SESSIONS);

		context.addServlet(servlet, "/*");

		servlet.doStart();

		server.start();
		System.out.println(servlet.getState());*/



	}

	@Test
	public void hello() throws InterruptedException {
		ConverterResource converterService=		JAXRSClientFactory.create(BASE_ADDRESS, ConverterResource.class);
		try{
			String name = converterService.getText();

			Assert.assertEquals("Hello 0", name);
		} catch (Exception e){
			e.printStackTrace();
		}

	}

//	@AfterClass
//	public static void stopServer() throws Exception {
//		server.stop();
//	}

}
