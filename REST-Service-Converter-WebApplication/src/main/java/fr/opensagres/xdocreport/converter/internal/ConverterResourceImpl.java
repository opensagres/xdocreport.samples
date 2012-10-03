package fr.opensagres.xdocreport.converter.internal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.activation.DataSource;
import javax.ws.rs.Path;

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

	private static int i = 0;

	public String getText() {
		return "Hello " + i++;
	}

	public BinaryFile convertPDF(Request request) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// 1) Create options ODT 2 PDF to select well converter form the
		// registry
		Options options = Options.getFrom(DocumentKind.ODT).to(
				ConverterTypeTo.PDF);

		// 2) Get the converter from the registry
		IConverter converter = ConverterRegistry.getRegistry().getConverter(
				options);

		// 3) Convert ODT 2 PDF

		try {

			converter.convert(new ByteArrayInputStream(request.getContent()),
					out, options);
		} catch (XDocConverterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BinaryFile response = new BinaryFile();

		response.setContent(new ByteArrayInputStream(out.toByteArray()));
		response.setFileName(request.getFilename() + ".pdf");

		return response;
	}

	public BinaryFile submitForm(String outputFormat, DataSource content,String operation) {

		System.out.println(operation);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		String filename = content.getName();
		String mimetype = content.getContentType();

		ConverterTypeTo to = ConverterTypeTo.valueOf(outputFormat);
		// 1) Create options ODT 2 PDF to select well converter form the
		// registry
		// DocumentKind.ODT
		Options options = Options.getFrom(DocumentKind.fromMimeType(mimetype))
				.to(to);
		// 2) Get the converter from the registry
		IConverter converter = ConverterRegistry.getRegistry().getConverter(
				options);

		// 3) Convert ODT 2 PDF
		try {
			converter.convert(content.getInputStream(),out, options);

		} catch (XDocConverterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		BinaryFile output = new BinaryFile();
		if("download".equals(operation)){
			output.setFileName(filename.replace('.', '_') + "."+ to.getExtension());
		}

		InputStream result = new ByteArrayInputStream(out.toByteArray());
		output.setContent(result);
		output.setMimeType(to.getMimeType());
		return output;
	}
}