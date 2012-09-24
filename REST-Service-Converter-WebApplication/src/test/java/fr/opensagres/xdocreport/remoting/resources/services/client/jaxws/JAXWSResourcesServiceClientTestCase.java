package fr.opensagres.xdocreport.remoting.resources.services.client.jaxws;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.opensagres.xdocreport.converter.ConverterResource;
import fr.opensagres.xdocreport.converter.ConverterResourceImpl;

public class JAXWSResourcesServiceClientTestCase {

	private static final int PORT = 8080;


	private static final String ROOT_ADDRESS = "http://localhost:" + PORT;


	private static final String BASE_ADDRESS = ROOT_ADDRESS + "/generic";



	@BeforeClass
	public static void startServer() throws Exception {

		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();

//		sf.setApplication(new ConverterApplication());

        sf.setResourceClasses(ConverterResourceImpl.class);
        sf.setResourceProvider(ConverterResource.class,
            new SingletonResourceProvider(new ConverterResourceImpl()));

        sf.setAddress(ROOT_ADDRESS);


       Server server= sf.create();
       System.out.println(server.getEndpoint());


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


//	@Ignore
//	@Test
//	public void convertPDF() throws Exception {
//		ConverterResource converterService=		JAXRSClientFactory.create(BASE_ADDRESS, ConverterResource.class);
//
//		FileInputStream fileInputStream = new FileInputStream("ODTCV.odt");
//		Request request = new Request();
//		request.setFilename("test.docx");
//		request.setContent(fileInputStream);
//
//		Response response = converterService.convertPDF(request);
//		System.out.println(response.getFilename());
//
//		FileOutputStream out= new FileOutputStream(response.getFilename());
//
//		out.write(response.getContent().toByteArray());
//		out.close();
//
//	}


}
