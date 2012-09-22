package fr.opensagres.xdocreport.converter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author duncan
 */
@Path("generic")
public class ConverterService {

	private  int i=0;

    /**
     * Creates a new instance of GenericResource
     */
    public ConverterService() {
    }

    /**
     * Retrieves representation of an instance of nl.dabloem.jaxrs.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getText() {
        return "Hello "+i++;
    }

}