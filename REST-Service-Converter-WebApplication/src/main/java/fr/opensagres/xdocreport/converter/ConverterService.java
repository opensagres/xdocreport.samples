package fr.opensagres.xdocreport.converter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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

	private  int i=0;



    /**
     * Say Hello...
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getText() {


        return "Hello "+i++;
    }


    @GET
    @Path("convertPDF")
    @Produces("application/pdf")
    public byte[] convertPDF()  {
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	// 1) Create options ODT 2 PDF to select well converter form the registry
    	Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);

    	// 2) Get the converter from the registry
    	IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

    	// 3) Convert ODT 2 PDF

		try {
			InputStream in = new FileInputStream(new File("/Users/pascalleclercq/git/xdocreport.samples/REST-Service-Converter-WebApplication/ODTCV.odt"));
			converter.convert(in, out, options);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XDocConverterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return out.toByteArray();
    }
}