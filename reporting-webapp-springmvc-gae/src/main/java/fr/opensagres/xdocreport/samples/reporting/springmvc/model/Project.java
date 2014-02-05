package fr.opensagres.xdocreport.samples.reporting.springmvc.model;

public class Project {

	private final String name;
	private String url;

	public Project(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setURL(String url) {
		this.url = url;
	}

	public String getURL() {
		return url;
	}
}
