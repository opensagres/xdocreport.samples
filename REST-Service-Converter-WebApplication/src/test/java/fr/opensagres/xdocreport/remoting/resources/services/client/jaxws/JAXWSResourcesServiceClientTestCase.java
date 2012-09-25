package fr.opensagres.xdocreport.remoting.resources.services.client.jaxws;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.opensagres.xdocreport.converter.ConverterResource;
import fr.opensagres.xdocreport.converter.ConverterResourceImpl;
import fr.opensagres.xdocreport.converter.Request;
import fr.opensagres.xdocreport.converter.Response;

public class JAXWSResourcesServiceClientTestCase {

	private static final int PORT = 8080;

	private static final String ROOT_ADDRESS = "http://localhost:" + PORT;

	private static final String BASE_ADDRESS = ROOT_ADDRESS + "/generic";

	@BeforeClass
	public static void startServer() throws Exception {

		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();

		// sf.setApplication(new ConverterApplication());

		sf.setResourceClasses(ConverterResourceImpl.class);
		sf.setResourceProvider(ConverterResource.class,
				new SingletonResourceProvider(new ConverterResourceImpl()));

		sf.setAddress(ROOT_ADDRESS);

		Server server = sf.create();
		System.out.println(server.getEndpoint());

	}

	@Test
	public void hello() throws InterruptedException {
		ConverterResource converterService = JAXRSClientFactory.create(
				BASE_ADDRESS, ConverterResource.class);
		try {
			String name = converterService.getText();

			Assert.assertEquals("Hello 0", name);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void convertPDF() throws Exception {
		ConverterResource converterService = JAXRSClientFactory.create(BASE_ADDRESS, ConverterResource.class);


		String fileName = "ODTCV.odt";
		String root = JAXWSResourcesServiceClientTestCase.class.getClassLoader().getResource(".").getFile();

		FileInputStream fileInputStream = new FileInputStream(new File(root,fileName));
		Request request = new Request();
		request.setFilename(fileName);

		int size = fileInputStream.available();
		byte[] bytes = new byte[size];
		fileInputStream.read(bytes);
		fileInputStream.close();
		request.setContent(bytes);

		Response response = converterService.convertPDF(request);
		Assert.assertEquals(fileName + ".pdf", response.getFilename());

		FileOutputStream out = new FileOutputStream(new File(root,response.getFilename()));

		out.write(response.getContent());
		out.close();
		System.out.println(size);
	}

}
