package fr.opensagres.xdocreport.converter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.opensagres.xdocreport.core.document.DocumentKind;

/**
 * REST Web Service
 *
 * @author pleclercq
 */
@Path("generic")
public class ConverterResourceImpl implements ConverterResource {

	private  static int i=0;



    @GET
    @Path("hello")
    @Produces("text/plain")
    public String getText() {
        return "Hello "+i++;
    }




    public Response convertPDF(  Request request)  {
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	// 1) Create options ODT 2 PDF to select well converter form the registry
    	Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);

    	// 2) Get the converter from the registry
    	IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

    	// 3) Convert ODT 2 PDF

		try {

			converter.convert(new ByteArrayInputStream(request.getContent()), out, options);
		} catch (XDocConverterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Response response = new Response();
		response.setContent(out.toByteArray());
		response.setFilename(request.getFilename()+".pdf");
    	return response;
    }
}