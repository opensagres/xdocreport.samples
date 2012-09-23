package fr.opensagres.xdocreport.converter;

import java.io.ByteArrayOutputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import fr.opensagres.xdocreport.core.document.DocumentKind;

/**
 * REST Web Service
 *
 * @author pleclercq
 */
@Path("generic")
public class ConverterService {

	private  static int i=0;



    /**
     * Say Hello...
     * @return an instance of java.lang.String
     */
    @GET
    @Path("hello")
    @Produces("text/plain")
    public String getText() {


        return "Hello "+i++;
    }


    @GET
    @Path("convertPDF")
    @Produces("application/pdf")
    public Response convertPDF(Request request)  {
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	// 1) Create options ODT 2 PDF to select well converter form the registry
    	Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);

    	// 2) Get the converter from the registry
    	IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

    	// 3) Convert ODT 2 PDF

		try {
			converter.convert(request.getContent(), out, options);
		} catch (XDocConverterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Response response = new Response();
		response.setContent(out);
		response.setFilename(request.getFilename()+".pdf");
    	return response;
    }
}