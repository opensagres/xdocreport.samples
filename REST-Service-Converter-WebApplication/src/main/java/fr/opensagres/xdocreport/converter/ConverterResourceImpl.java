package fr.opensagres.xdocreport.converter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

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


    public String createMessage(String name,  MultipartBody file) {
    	System.out.println(name);
 //   	System.out.println(datafile);


    	System.out.println(file);
  List<Attachment> attachments= 	file.getAllAttachments();


  for (Attachment attachment : attachments) {
	System.out.println(attachment.getContentId());
	System.out.println(attachment.getContentType());
	System.out.println(attachment.getHeader("Content-Disposition"));
}
//    	System.out.println(datafile);
//        if(name.trim().length() > 0 ) {
//            // Note 1: Normally you would persist the new message to a datastore
//            // of some sort. I'm going to pretend I've done that and
//            // use a unique id for it that obviously points to nothing in
//            // this case.
//            // Note 2: The way I'm returning the data should be more like the commented
//            // out piece, I am being verbose for the sake of showing you how to
//            // get the values and show that it was read.
//            return javax.ws.rs.core.Response.created(URI.create("/messages/" + String.valueOf(UUID.randomUUID()))).entity(
//                    name+ ": " + datafile ).build();
//
//            // This is a more real world "return"
//            //return Response.created(URI.create("/messages/" + String.valueOf(UUID.randomUUID()))).build();
//        }
        return "Coucou "+name;
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