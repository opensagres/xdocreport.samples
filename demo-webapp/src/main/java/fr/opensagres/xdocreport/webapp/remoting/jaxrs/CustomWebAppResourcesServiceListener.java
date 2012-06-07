package fr.opensagres.xdocreport.webapp.remoting.jaxrs;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;

import fr.opensagres.xdocreport.core.io.IOUtils;
import fr.opensagres.xdocreport.core.io.XDocArchive;
import fr.opensagres.xdocreport.core.logging.LogUtils;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.remoting.resources.domain.BinaryData;
import fr.opensagres.xdocreport.remoting.resources.services.ResourcesException;
import fr.opensagres.xdocreport.remoting.resources.services.web.WebAppResourcesServiceListener;
import fr.opensagres.xdocreport.webapp.defaultreport.DefaultReportController;
import fr.opensagres.xdocreport.webapp.defaultreport.DefaultReportRegistry;

public class CustomWebAppResourcesServiceListener
    extends WebAppResourcesServiceListener
{

    private static final Logger LOGGER = LogUtils.getLogger( CustomWebAppResourcesServiceListener.class.getName() );

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
        throws ResourcesException
    {
        LOGGER.info( "*****************************************" );
        LOGGER.info( "Start download resourceId=" + resourceId );
        String reportId = getReportId( resourceId );
        IXDocReport report = XDocReportRegistry.getRegistry().getReport( reportId );
        if ( report != null )
        {
            try
            {
                BinaryData data =new BinaryData();
//                    new BinaryData( XDocArchive.getInputStream( report.getOriginalDocumentArchive() ), reportId );


           	 data.setContent(IOUtils.toByteArray(XDocArchive.getInputStream( report.getOriginalDocumentArchive() )));


                data.setResourceId( resourceId );
                LOGGER.info( "End download resourceId=" + resourceId + " loaded from IXDocReport." );
                LOGGER.info( "*****************************************" );
                return data;
            }
            catch ( IOException e )
            {
                LOGGER.log( Level.SEVERE, "End download error resourceId=" + resourceId + " loaded from IXDocReport.",
                            e );
                LOGGER.info( "*****************************************" );
                throw new ResourcesException( e );
            }
        }
        else
        {
            DefaultReportController controller = DefaultReportRegistry.INSTANCE.getReportController( reportId );
            if ( controller != null )
            {
                try
                {
                 //   BinaryData data = new BinaryData( controller.getSourceStream(), reportId );
                	BinaryData data =new BinaryData();

                	 data.setContent(IOUtils.toByteArray(controller.getSourceStream()));
                    data.setResourceId( resourceId );
                    LOGGER.info( "End download resourceId=" + resourceId + " loaded from controller." );
                    LOGGER.info( "*****************************************" );
                    return data;
                }
                catch ( IOException e )
                {
                    LOGGER.log( Level.SEVERE, "End download error resourceId=" + resourceId
                        + " loaded from controller.", e );
                    LOGGER.info( "*****************************************" );
                    throw new ResourcesException( e );
                }

            }
        }
        LOGGER.info( "End download resourceId=" + resourceId + " loaded from File." );
        LOGGER.info( "*****************************************" );
        return super.download( resourceId );

    }

    @Override
    public void upload( BinaryData data )
        throws ResourcesException
    {

        String resourceId = data.getResourceId();
        String reportId = getReportId( resourceId );

        LOGGER.info( "*****************************************" );
        LOGGER.info( "Start upload resourceId=" + resourceId + ", reportId=" + reportId );

        IXDocReport report = XDocReportRegistry.getRegistry().getReport( reportId );
        if ( report != null )
        {
            try
            {


                report.load( new ByteArrayInputStream(data.getContent() ));
                LOGGER.info( "*****************************************" );
                LOGGER.info( "End upload resourceId=" + resourceId + ", reportId=" + reportId + " with IXDocReport" );
            }
            catch ( Exception e )
            {
                LOGGER.log( Level.SEVERE, "End upload error resourceId=" + ", reportId=" + reportId
                    + " with IXDocReport", e );
                LOGGER.info( "*****************************************" );
                throw new ResourcesException( e );
            }
        }
        else
        {
            DefaultReportController controller = DefaultReportRegistry.INSTANCE.getReportController( reportId );
            if ( controller != null )
            {


					controller.setSource(  data.getContent() );

                LOGGER.info( "*****************************************" );
                LOGGER.info( "End upload resourceId=" + resourceId + ", reportId=" + reportId + " with IXDocReport" );
            }
            else
            {
                LOGGER.info( "*****************************************" );
                LOGGER.severe( "End upload resourceId=" + resourceId + ", reportId=" + reportId + " with error" );

                throw new ResourcesException( "Impossible to find report for id=" + reportId );
            }
        }
        LOGGER.info( "*****************************************" );
        LOGGER.info( "End upload resourceId=" + resourceId + ", reportId=" + reportId );
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
