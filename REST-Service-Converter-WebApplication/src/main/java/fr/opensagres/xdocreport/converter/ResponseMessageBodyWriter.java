package fr.opensagres.xdocreport.converter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
public class ResponseMessageBodyWriter
    implements MessageBodyWriter<Response>
{

    public boolean isWriteable( Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType )
    {
        return Response.class.isAssignableFrom( type );
    }

    public long getSize( Response t, Class<?> type, Type genericType, Annotation[] annotations,
                         MediaType mediaType )
    {

        long n = t.getContent().size();
//        // allow Streaming if we don't know the size of the Binary Data
        return n <= 0 ? -1 : n;
    }

    public void writeTo( Response t, Class<?> type, Type genericType, Annotation[] annotations,
                         MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream )
        throws IOException, WebApplicationException
    {
    	ByteArrayOutputStream content = t.getContent();
        httpHeaders.add( "Content-Disposition", "attachement;filename=" + t.getFilename() );
        entityStream.write(content.toByteArray());
        entityStream.flush();
    }


}
