package fr.opensagres.xdocreport.converter.internal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

import fr.opensagres.xdocreport.converter.BinaryFile;
import fr.opensagres.xdocreport.converter.ConverterRegistry;
import fr.opensagres.xdocreport.converter.ConverterResource;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.IConverter;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.converter.Request;
import fr.opensagres.xdocreport.converter.XDocConverterException;
import fr.opensagres.xdocreport.core.document.DocumentKind;

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


    public  BinaryFile submitForm(MultipartBody file) {

    	ByteArrayOutputStream out = new ByteArrayOutputStream();

    	//List<Attachment> attachments= file.getAllAttachments();

    	Attachment	uploadedFile = file.getAttachment("datafile");

		String filename = "";
		String mimetype ="";

    		MultivaluedMap<String, String>   httpHeaders=	uploadedFile.getHeaders();

            String cd = httpHeaders.getFirst( "Content-Disposition" );
            if ( cd != null )
            {
            	int start=cd.indexOf("filename=");
            	int length=cd.length();
                filename = cd.substring(start+10,length-1);
            }
            mimetype = httpHeaders.getFirst( "Content-Type" );


    	Attachment	outputformat = file.getAttachment("outputformat");

    	System.out.println(outputformat);

    	// 1) Create options ODT 2 PDF to select well converter form the registry
    	//DocumentKind.ODT
    	Options options = Options.getFrom(DocumentKind.fromMimeType(mimetype)).to(ConverterTypeTo.PDF);
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