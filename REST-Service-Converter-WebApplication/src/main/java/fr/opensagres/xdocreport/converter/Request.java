package fr.opensagres.xdocreport.converter;

import java.io.InputStream;

public class Request {

	private String filename;


	private InputStream content;


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public InputStream getContent() {
		return content;
	}


	public void setContent(InputStream content) {
		this.content = content;
	}


}
