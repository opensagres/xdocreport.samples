package fr.opensagres.xdocreport.webapp.remoting.jaxws;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.xml.ws.Endpoint;

import org.apache.cxf.transport.servlet.CXFNonSpringServlet;

import fr.opensagres.xdocreport.remoting.resources.services.ResourcesService;
import fr.opensagres.xdocreport.remoting.resources.services.ResourcesServicesRegistry;
import fr.opensagres.xdocreport.remoting.resources.services.ws.server.JAXWSResourcesServiceImpl;

@Deprecated
public class JAXWSCXFNonSpringServlet
    extends CXFNonSpringServlet
{

    private static final long serialVersionUID = -8675595068401322396L;

    @Override
    public void init( ServletConfig sc )
        throws ServletException
    {
        super.init( sc );
        
        // Registered by CustomWebAppResourcesServiceListener
        ResourcesService  service = ResourcesServicesRegistry.getRegistry().getServices().get( 0 );
        String address = "/resources";
        Endpoint e = javax.xml.ws.Endpoint.publish( address,
                                       new JAXWSResourcesServiceImpl(service) );
        System.err.println(e);

    }
}
