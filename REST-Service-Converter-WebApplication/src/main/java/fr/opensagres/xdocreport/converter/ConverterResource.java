package fr.opensagres.xdocreport.converter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

public interface ConverterResource {

	/**
	 * Say Hello...
	 * @return an instance of java.lang.String
	 */
	@GET
	@Path("hello")
	@Produces("text/plain")
	String getText();

	@GET
	@Path("convertPDF")
	@Produces("application/pdf")
	Response convertPDF(Request request);

}