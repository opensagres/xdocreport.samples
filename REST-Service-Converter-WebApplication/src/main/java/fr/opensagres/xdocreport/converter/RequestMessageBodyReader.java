package fr.opensagres.xdocreport.converter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;


@Provider
public class RequestMessageBodyReader
    implements MessageBodyReader<Request>
{

    public boolean isReadable( Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType )
    {
        return Request.class.isAssignableFrom( type );
    }

    public Request readFrom( Class<Request> type, Type genericType, Annotation[] annotations,
                                MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
                                InputStream entityStream )
        throws IOException, WebApplicationException
    {

        String filename = "";
        String cd = httpHeaders.getFirst( "Content-Disposition" );
        if ( cd != null )
        {
            filename = cd.replace( "attachement;filename=", "" );
        }


        Request data = new Request( );
        data.setContent(entityStream);
        data.setFilename(filename);

        return data;
    }
}
