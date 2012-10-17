package fr.opensagres.xdocreport.converter;

import java.io.IOException;
import java.io.OutputStream;

import javax.activation.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.transform.stream.StreamResult;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.opensagres.xdocreport.converter.internal.ConverterResourceImpl;
import fr.opensagres.xdocreport.core.io.IOUtils;
import fr.opensagres.xdocreport.core.utils.HttpHeaderUtils;

/**
 * REST Web Service
 *
 * @author pleclercq
 */
@Path("/")
public class GenericConverter  {

	private static final Logger LOGGER =LoggerFactory.getLogger(GenericConverter.class);

	ConverterResourceImpl xdocReportJaxRsConverter = new ConverterResourceImpl();


	private static final String DOWNLOAD_OPERATION = "download";

	@POST
	@Consumes(MediaType.WILDCARD)
	@Produces(MediaType.WILDCARD)
	@Path("/convert")
	public Response convert(@Multipart("outputFormat") String outputFormat,
			@Multipart("datafile") final DataSource content,
			@Multipart("operation") String operation, @Multipart("framework") String framework) {
		LOGGER.info("framework selected {}",framework);
		long start =System.currentTimeMillis();
		Response response;
		if("Docx4j".equals(framework)){
			response= doDocx4j(outputFormat, content, operation);

		} else {
			response=doXDocReport(outputFormat, content, operation);
		}
		LOGGER.info("Time spent {}",(System.currentTimeMillis()-start));
		return response;

	}

	private Response doXDocReport(String outputFormat, final DataSource content,
			String operation) {
		return	xdocReportJaxRsConverter.convert(outputFormat, content, operation);
	}

	private Response doDocx4j(String outputFormat, final DataSource content,
			String operation) {
		try {
			ConverterTypeTo to = ConverterTypeTo.valueOf(outputFormat);
			// Fonts identity mapping – best on Microsoft Windows
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
					.load(content.getInputStream());
			wordMLPackage.setFontMapper(new IdentityPlusMapper());
			StreamingOutput output;
			if("PDF".equals(outputFormat)){
				output = doDocx4jPdfConversion(wordMLPackage);
			} else {
				output = doDocx4jHTMLConversion(wordMLPackage);
			}
			// 5) Create the JAX-RS response builder.
			ResponseBuilder responseBuilder = Response.ok(output,
					MediaType.valueOf(to.getMimeType()));
			if (isDownload(operation)) {
				// The converted document must be downloaded, add teh well
				// content-disposition header.
				String fileName = content.getName();
				responseBuilder.header(
						HttpHeaderUtils.CONTENT_DISPOSITION_HEADER,
						HttpHeaderUtils
								.getAttachmentFileName(getOutputFileName(
										fileName, to)));
			}
			return responseBuilder.build();

		} catch (XDocConverterException e) {
			throw new WebApplicationException(e);

		} catch (Docx4JException e) {
			throw new WebApplicationException(e);
		} catch (IOException e) {
			throw new WebApplicationException(e);
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}

	public StreamingOutput doDocx4jHTMLConversion(
			WordprocessingMLPackage wordMLPackage) {
		StreamingOutput output;
		final org.docx4j.convert.out.html.HtmlExporterNG2 c2 = new org.docx4j.convert.out.html.HtmlExporterNG2();
		// Write to output stream
		c2.setWmlPackage(wordMLPackage);
		 output = new StreamingOutput() {
			public void write(OutputStream out) throws IOException,
					WebApplicationException {
				try {
					//c.output(out, new PdfSettings());
					c2.output(new StreamResult(out));
				} catch (Docx4JException e) {

					throw new WebApplicationException(e);
				} finally {
					IOUtils.closeQuietly(out);
				}

			}
		};
		return output;
	}

	public StreamingOutput doDocx4jPdfConversion(
			WordprocessingMLPackage wordMLPackage) {
		StreamingOutput output;
		// Set up converter
		final org.docx4j.convert.out.pdf.PdfConversion c = new org.docx4j.convert.out.pdf.viaIText.Conversion(
				wordMLPackage);
		 output = new StreamingOutput() {
			public void write(OutputStream out) throws IOException,
					WebApplicationException {
				try {
					c.output(out, new PdfSettings());

				} catch (Docx4JException e) {

					throw new WebApplicationException(e);
				} finally {
					IOUtils.closeQuietly(out);
				}

			}
		};
		return output;
	}

	/**
	 * Returns true if operation is download and false otherwise.
	 *
	 * @param operation
	 * @return
	 */
	private boolean isDownload(String operation) {
		return DOWNLOAD_OPERATION.equals(operation);
	}

	/**
	 * Returns the output file name.
	 *
	 * @param filename
	 * @param to
	 * @return
	 */
	protected String getOutputFileName(String filename, ConverterTypeTo to) {
		StringBuilder name = new StringBuilder(filename.replace('.', '_'));
		name.append('.');
		name.append(to.getExtension());
		return name.toString();
	}
}