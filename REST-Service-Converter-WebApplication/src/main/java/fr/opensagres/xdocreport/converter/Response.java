package fr.opensagres.xdocreport.converter;

import java.io.ByteArrayOutputStream;


public class Response {

	private String filename;


	private ByteArrayOutputStream content;


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public ByteArrayOutputStream getContent() {
		return content;
	}


	public void setContent(ByteArrayOutputStream content) {
		this.content = content;
	}


}
