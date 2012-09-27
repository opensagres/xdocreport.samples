package fr.opensagres.xdocreport.converter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

import fr.opensagres.xdocreport.core.document.DocumentKind;
import fr.opensagres.xdocreport.core.io.IOUtils;

/**
 * REST Web Service
 *
 * @author pleclercq
 */
@Path("generic")
public class ConverterResourceImpl implements ConverterResource {

	private  static int i=0;

    public String getText() {
        return "Hello "+i++;
    }


 public    BinaryFile submitForm(MultipartBody file) {

    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	// 1) Create options ODT 2 PDF to select well converter form the registry
    	Options options = Options.getFrom(DocumentKind.ODT).to(ConverterTypeTo.PDF);
    	List<Attachment> attachments= file.getAllAttachments();

    	Attachment uploadedFile=null;;

		String filename = "";

    	for (Attachment attachment : attachments) {
    		MultivaluedMap<String, String>   httpHeaders=	attachment.getHeaders();

            String cd = httpHeaders.getFirst( "Content-Disposition" );
            if ( cd != null )
            {
                filename = cd.replace( "attachement;filename=", "" );
                uploadedFile=attachment;
            }
            //String mimetype = httpHeaders.getFirst( "Content-Type" );
		}

    	// 2) Get the converter from the registry
    	IConverter converter = ConverterRegistry.getRegistry().getConverter(options);

    	// 3) Convert ODT 2 PDF

		try {

			converter.convert(uploadedFile.getDataHandler().getInputStream(), out, options);

		} catch (XDocConverterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		BinaryFile output = new BinaryFile();
		output.setFileName(filename.replace('.', '_')+".pdf");

		InputStream content = new ByteArrayInputStream(out.toByteArray());
		output.setContent(content);
		output.setMimeType("application/pdf");
		return output;

    }



    public BinaryFile convertPDF(  Request request)  {
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

		BinaryFile response = new BinaryFile();

		response.setContent(new ByteArrayInputStream(out.toByteArray()));
		response.setFileName(request.getFilename()+".pdf");

    	return response;
    }
}