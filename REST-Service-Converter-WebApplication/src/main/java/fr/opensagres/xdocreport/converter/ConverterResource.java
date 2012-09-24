package fr.opensagres.xdocreport.converter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ConverterResource {

	/**
	 * Say Hello...
	 * @return an instance of java.lang.String
	 */
	@GET
	@Path("hello")
	@Produces("text/plain")
	String getText();

    @POST
    @Path("convertPDF")
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.WILDCARD)
	Response convertPDF(Request request);

}