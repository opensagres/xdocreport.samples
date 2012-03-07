package fr.opensagres.xdocreport.webapp.remoting;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.xml.ws.Endpoint;

import org.apache.cxf.jaxrs.servlet.CXFNonSpringJaxrsServlet;

import fr.opensagres.xdocreport.remoting.resources.services.ResourcesService;
import fr.opensagres.xdocreport.remoting.resources.services.ResourcesServicesRegistry;
import fr.opensagres.xdocreport.remoting.resources.services.ws.server.JAXWSResourcesServiceImpl;

public class JAXWSAndJAXRSCXFNonSpringServlet
    extends CXFNonSpringJaxrsServlet
{

    private static final long serialVersionUID = -3757049489755212484L;

    @Override
    public void init( ServletConfig servletConfig )
        throws ServletException
    {
        super.init( servletConfig );

        // Registered by CustomWebAppResourcesServiceListener
        ResourcesService service = ResourcesServicesRegistry.getRegistry().getServices().get( 0 );
        String address = "/resources";
        Endpoint.publish( address, new JAXWSResourcesServiceImpl( service ) );
    }
}
