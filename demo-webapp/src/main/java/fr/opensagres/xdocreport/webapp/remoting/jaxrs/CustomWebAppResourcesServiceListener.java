package fr.opensagres.xdocreport.webapp.remoting.jaxrs;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.opensagres.xdocreport.core.io.IOUtils;
import fr.opensagres.xdocreport.core.io.XDocArchive;
import fr.opensagres.xdocreport.core.logging.LogUtils;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.remoting.resources.domain.BinaryData;
import fr.opensagres.xdocreport.remoting.resources.domain.LargeBinaryData;
import fr.opensagres.xdocreport.remoting.resources.domain.ResourceFactory;
import fr.opensagres.xdocreport.remoting.resources.domain.ResourceHelper;
import fr.opensagres.xdocreport.remoting.resources.services.ResourcesException;
import fr.opensagres.xdocreport.remoting.resources.services.server.web.WebAppResourcesServiceListener;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import fr.opensagres.xdocreport.webapp.defaultreport.DefaultReportController;
import fr.opensagres.xdocreport.webapp.defaultreport.DefaultReportRegistry;

public class CustomWebAppResourcesServiceListener
    extends WebAppResourcesServiceListener
{

    public CustomWebAppResourcesServiceListener()
    {
        // Generate META-INF folder for each files
        super( true );
    }

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
        boolean fieldsMetadata = ResourceHelper.isFieldsMetadata( resourceId );
        if ( fieldsMetadata )
        {
            // Awful code to retrieve report id from fields metadata
            reportId = reportId.substring( 0, reportId.length() - ResourceFactory.FIELDS_XML.length() );
            String testReportId = reportId + ".docx";
            if ( existsReport( testReportId ) )
            {
                reportId = testReportId;
            }
            else
            {
                testReportId = reportId + ".odt";
                if ( existsReport( testReportId ) )
                {
                    reportId = testReportId;
                }
            }
        }

        IXDocReport report = XDocReportRegistry.getRegistry().getReport( reportId );
        if ( report != null )
        {
            try
            {
                BinaryData data = new BinaryData();
                data.setResourceId( resourceId );

                if ( fieldsMetadata )
                {
                    FieldsMetadata metadata = report.getFieldsMetadata();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    metadata.saveXML( out );
                    data.setContent( out.toByteArray() );
                }
                else
                {
                    data.setContent( IOUtils.toByteArray( XDocArchive.getInputStream( report.getOriginalDocumentArchive() ) ) );
                }

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
                    // BinaryData data = new BinaryData( controller.getSourceStream(), reportId );
                    BinaryData data = new BinaryData();
                    data.setResourceId( resourceId );
                    
                    if ( fieldsMetadata )
                    {
                        FieldsMetadata metadata = controller.getFieldsMetadata();
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        metadata.saveXML( out );
                        data.setContent( out.toByteArray() );
                    }
                    else
                    {
                    data.setContent( IOUtils.toByteArray( controller.getSourceStream() ) );
                    }
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

    private boolean existsReport( String reportId )
    {
        if ( XDocReportRegistry.getRegistry().existsReport( reportId ) )
        {
            return true;
        }
        return DefaultReportRegistry.INSTANCE.getReportController( reportId ) != null;
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

                report.load( new ByteArrayInputStream( data.getContent() ) );
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

                controller.setSource( data.getContent() );

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

    @Override
    public LargeBinaryData downloadLarge( String resourceId )
        throws ResourcesException
    {
        LOGGER.info( "*****************************************" );
        LOGGER.info( "Start downloadLarge resourceId=" + resourceId );
        String reportId = getReportId( resourceId );
        boolean fieldsMetadata = ResourceHelper.isFieldsMetadata( resourceId );
        if ( fieldsMetadata )
        {
            // Awful code to retrieve report id from fields metadata
            reportId = reportId.substring( 0, reportId.length() - ResourceFactory.FIELDS_XML.length() );
            String testReportId = reportId + ".docx";
            if ( existsReport( testReportId ) )
            {
                reportId = testReportId;
            }
            else
            {
                testReportId = reportId + ".odt";
                if ( existsReport( testReportId ) )
                {
                    reportId = testReportId;
                }
            }
        }
        
        IXDocReport report = XDocReportRegistry.getRegistry().getReport( reportId );
        if ( report != null )
        {
            try
            {
                LargeBinaryData data = new LargeBinaryData();
                data.setResourceId( resourceId );
                if ( fieldsMetadata )
                {
                    FieldsMetadata metadata = report.getFieldsMetadata();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    metadata.saveXML( out );
                    data.setContent( new ByteArrayInputStream( out.toByteArray()) );
                }
                else
                {
                    data.setContent( XDocArchive.getInputStream( report.getOriginalDocumentArchive() ) );
                }
                
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
                    LargeBinaryData data = new LargeBinaryData();
                    data.setResourceId( resourceId );
                    if ( fieldsMetadata )
                    {
                        FieldsMetadata metadata = controller.getFieldsMetadata();
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        metadata.saveXML( out );
                        data.setContent( new ByteArrayInputStream( out.toByteArray()) );
                    }
                    else
                    {
                        data.setContent( controller.getSourceStream() );
                    }
                    LOGGER.info( "End downloadLarge resourceId=" + resourceId + " loaded from controller." );
                    LOGGER.info( "*****************************************" );
                    return data;
                }
                catch ( IOException e )
                {
                    LOGGER.log( Level.SEVERE, "End downloadLarge error resourceId=" + resourceId
                        + " loaded from controller.", e );
                    LOGGER.info( "*****************************************" );
                    throw new ResourcesException( e );
                }

            }
        }
        LOGGER.info( "End downloadLarge resourceId=" + resourceId + " loaded from File." );
        LOGGER.info( "*****************************************" );
        return super.downloadLarge( resourceId );
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
