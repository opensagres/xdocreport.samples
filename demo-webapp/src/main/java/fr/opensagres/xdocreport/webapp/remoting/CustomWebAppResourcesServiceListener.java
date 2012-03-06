package fr.opensagres.xdocreport.webapp.remoting;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.core.io.XDocArchive;
import fr.opensagres.xdocreport.core.io.internal.ByteArrayOutputStream;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.remoting.resources.domain.BinaryData;
import fr.opensagres.xdocreport.remoting.resources.services.web.WebAppResourcesServiceListener;
import fr.opensagres.xdocreport.webapp.defaultreport.DefaultReportController;
import fr.opensagres.xdocreport.webapp.defaultreport.DefaultReportRegistry;

public class CustomWebAppResourcesServiceListener
    extends WebAppResourcesServiceListener
{

    public String getName()
    {
        return "WebApp Demo Resources";
    }

    @Override
    protected File getRootFolder( File webAppFolder )
    {
        return new File( webAppFolder, "resources" );
    }

    @Override
    public BinaryData download( String resourceId )
    {
        String reportId = getReportId( resourceId );
        IXDocReport report = XDocReportRegistry.getRegistry().getReport( reportId );
        if ( report != null )
        {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try
            {
                XDocArchive.writeZip( report.getOriginalDocumentArchive(), out );
            }
            catch ( IOException e )
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            BinaryData data = new BinaryData();
            data.setResourceId( resourceId );
            data.setContent( out.toByteArray() );
            return data;

        }
        return super.download( resourceId );

    }

    @Override
    public void upload( BinaryData data )
    {
        String resourceId = data.getResourceId();
        String reportId = getReportId( resourceId );

        IXDocReport report = XDocReportRegistry.getRegistry().getReport( reportId );
        if ( report != null )
        {
            try
            {
                report.load( new ByteArrayInputStream( data.getContent() ) );
            }
            catch ( IOException e )
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch ( XDocReportException e )
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else
        {
            DefaultReportController controller = DefaultReportRegistry.INSTANCE.getReportController( reportId );
            if ( controller != null )
            {
                controller.setSource( data.getContent() );
            }
        }

    }

    private String getReportId( String resourceId )
    {
        String resourcePath = getResourcePath( resourceId );
        String reportId = resourcePath;
        int index = reportId.lastIndexOf( '/' );
        if ( index != -1 )
        {
            reportId = reportId.substring( index + 1, reportId.length() );
        }
        return reportId;
    }

}
