package fr.opensagres.xdocreport.converter;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

public interface ConverterResource {


	@GET
	@Path("hello")
	@Produces("text/plain")
	String getText();

    @POST
    @Path("convertPDF")
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.WILDCARD)
	Response convertPDF(Request request);


    @POST
    @Consumes("multipart/form-data")
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/createMessage")
    String createMessage(@Multipart("name") String name,@Multipart("datafile") MultipartBody file);

}