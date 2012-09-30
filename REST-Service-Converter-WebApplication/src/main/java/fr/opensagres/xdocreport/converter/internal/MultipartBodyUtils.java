package fr.opensagres.xdocreport.converter.internal;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.cxf.attachment.AttachmentDataSource;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

public class MultipartBodyUtils {

	public static String extractValue(MultipartBody file, String name) {
		Attachment outputformat = file.getAttachment(name);

		AttachmentDataSource dataSource = (AttachmentDataSource) outputformat
				.getDataHandler().getDataSource();

		ByteArrayInputStream in = ((ByteArrayInputStream) dataSource
				.getInputStream());
		byte[] bytes = new byte[in.available()];
		try {
			in.read(bytes);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		return new String(bytes);
	}
}
