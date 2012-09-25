package fr.opensagres.xdocreport.remoting.resources.services.client.jaxws;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.cxf.jaxrs.client.WebClient;

import fr.opensagres.xdocreport.converter.Request;
import fr.opensagres.xdocreport.converter.Response;

public class Main {


	private static final int PORT = 8080;

	private static final String ROOT_ADDRESS = "http://localhost:" + PORT;


	private static final String BASE_ADDRESS = ROOT_ADDRESS + "/rest/generic";

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		WebClient wc=		WebClient.create(BASE_ADDRESS+"/convertPDF");
		FileInputStream fileInputStream = new FileInputStream("ODTCV.odt");

		wc.header("Content-Disposition", "attachement;filename=ODTCV.odt");

		Request request = new Request();
		request.setFilename("test.odt");

byte[] content = new byte[fileInputStream.available()];
fileInputStream.read(content);
fileInputStream.close();

		request.setContent(content);


long start = System.currentTimeMillis();
		Response response=wc.post(request, Response.class);

		System.out.println("%%%%%%%%%%%%%%% Time spent "+(System.currentTimeMillis()-start));


		FileOutputStream fileOutputStream = new FileOutputStream(response.getFilename());
		fileOutputStream.write(response.getContent());
		fileOutputStream.close();


	}

}
